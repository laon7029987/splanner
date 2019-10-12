package com.timeisall.mstudyplanner.registration.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.timeisall.mstudyplanner.registration.database.StudyDbSchema.UserTable;

import java.util.ArrayList;
import java.util.List;


public class StudyDbHandler {
    private static StudyDbHandler sStudyDbHandler;//sStudyDbHandler에서 s는 static 변수의 접두사(안드로이드 작면 규칙 준수)-2019-10-12(금)
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static StudyDbHandler get(Context context) {//다른 클래스에서 StudyDbHandler의 인스턴스를 생성할 때는 이 생성자를 호출할 수 없으므로(private 선언) 반드시 get()메소드를 호출해야 한다.
                                                          //get()메소드에서는 Context 객체를 인자로 받는다. Context 객체는 Activity 또는 Service와 같은 객체를 나타냄.
        if(sStudyDbHandler == null) {
            sStudyDbHandler = new StudyDbHandler(context);
        }
        return sStudyDbHandler;
    }

    private StudyDbHandler(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new StudyDbHelper(mContext).getWritableDatabase();
    }//21라인에서 부터 여기까지 싱글톤 만들기

    //ContentsValue 생성하기
    private static ContentValues getContentValues(UserDao user) {
        ContentValues values = new ContentValues();
        values.put(UserTable.Cols.USERID, user.getUserID());
        values.put(UserTable.Cols.USERPASSWORD, user.getUserPassword());
        values.put(UserTable.Cols.USERGENDER, user.getUserGender());
        values.put(UserTable.Cols.USERGROUP, user.getUserGroup());
        values.put(UserTable.Cols.USEREMAIL, user.getUserEmail());

        return values;
    }

    /*행 추가 하기 */
    public void addUser(UserDao u) {
        ContentValues values = getContentValues(u);
        mDatabase.insert(UserTable.NAME, null, values);//insert(String-테이블이름, String-nullColumnHack/생략가능, ContentValues-ContentValues 인스턴스)
    }

    /*행 갱신 하기 */
    public void updateUser(UserDao u) {
        String userIDString = u.getUserID().toString();
        ContentValues values = getContentValues(u);

        /*update(String-테이블이름, ContentValues-ContentValues 인스턴스, String-SQL의 Where절, String-where절에 지정할 값)
        *
        * */
        mDatabase.update(UserTable.NAME, values, UserTable.Cols.USERID + " =?",
                new String[] {userIDString});
    }

    /*행 삭제 하기
    public void deleteUser(UserDao u) {
        //String userIDString = u.getUserID().toString();
        //ContentValues values = getContentValues(u);
        //mDatabase.delete(UserTable.NAME, values, UserTable.Cols.USERID + " =?",
        //        new String[] {userIDString});
    }
    */

    /* 행 조회(Select) 하기 */
    //private Cursor queryUsers(String whereClause, String[] whereArgs) {//user 데이터 쿼리하기.
    private UserCursorWrapper queryUsers(String whereClause, String[] whereArgs) {//커서 래퍼(UserCursorWrapper)를 사용하도록 수정하기
        Cursor cursor = mDatabase.query(
                UserTable.NAME,
                null, //테이블 열(Columns) : null인 경우 테이블의 모든 열을 의미
                whereClause,
                whereArgs,
                null, //SQL Select 몀령문의 groupBY
                null,  //SQL Select 몀령문의 having
                null  //SQL Select 몀령문의 orderBy
        );
        //return cursor;
        return new UserCursorWrapper(cursor);//커서 래퍼(UserCursorWrapper)를 사용하도록 수정하기
    }

    /*User리스트 반환하기 */
    public List<UserDao> getUsers() {

        List<UserDao> users = new ArrayList<>();

        UserCursorWrapper cursor = queryUsers(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                users.add(cursor.getUser());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return users;
    }

    /*쿼리로 찾은 특정 UserID 한 건만 반환하기*/

    public UserDao getUserID(UserDao userID) {
        UserCursorWrapper cursor = queryUsers(
                UserTable.Cols.USERID + "= ? ",
                new String[] { userID.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getUser();

        } finally {

            cursor.close();
        }
    }

}

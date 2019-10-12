package com.timeisall.mstudyplanner.registration.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.timeisall.mstudyplanner.registration.database.StudyDbSchema.UserTable;

import static com.timeisall.mstudyplanner.registration.database.StudyDbSchema.UserTable.Cols.USERID;

/* CursorWrapper 사용하기
* 커서는 쿼리된 결과 데이터를 가져오는 데 사용된다.
* 커서는 테이블의 각 열에 사용하여 데이터를 가져오며,
* 테이블 데이터를 읽을 때마다 매번 이 코드를 작성해야 한다.
* 따라서, 한 곳에서 이 코드들을 관리하게 해야 한다.
* 이때 CursorWrapper를 사용하는 것이 가장 쉬운 방법이다.
* CursorWrapper를 사용하면 Cursor를 래핑(Wrapping)하여 원하는
* 테이블로부터 데이터를 읽을 수 있고 새로운 메서드도 추가할 수 있다.
* 이렇게 하면 Cursor와 동일한 메서드를 갖고 똑같은 일을 할 수 있는
* 래퍼(wrapper)가 생성된다.
* 그러나, 이렇게만 하면 의미가 없고 Cursor를 사용하는 메서드를 추가해야 한다.
* getUser() 메서드를 추가한다.
* */

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public UserDao getUser() {
        String userid = getString(getColumnIndex(USERID));
        String userpassword = getString(getColumnIndex(UserTable.Cols.USERPASSWORD));
        String usergender = getString(getColumnIndex(UserTable.Cols.USERGENDER));
        String usergroup = getString(getColumnIndex(UserTable.Cols.USERGROUP));
        String useremail = getString(getColumnIndex(UserTable.Cols.USEREMAIL));

        UserDao user = new UserDao(userid,userpassword,usergender,usergroup,useremail);
        user.setUserID(userid);
        user.setUserPassword(userpassword);
        user.setUserGender(usergender);
        user.setUserGroup(usergroup);
        user.setUserEmail(useremail);

        return user;
    }
}

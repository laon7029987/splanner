package com.timeisall.mstudyplanner.registration.database;

public class StudyDbSchema {
    public static final class UserTable {
        public static final String NAME = "users";//테이블 이름 정의. users 테이블 이름을 나타낼때:UserTable.NAME으로 사용

        //users 테이블 열 정의. USERID 참조 방법 -> StudyTable.Cols.USERID
        public static final class Cols {
                public static final String USERID = "userid";
                public static final String USERPASSWORD = "userpassword";
                public static final String USERGENDER = "usergender";
                public static final String USERGROUP = "usergroup";
                public static final String USEREMAIL = "useremail";
        }
    }
}
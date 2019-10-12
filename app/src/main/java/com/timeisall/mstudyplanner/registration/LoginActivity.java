package com.timeisall.mstudyplanner.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.timeisall.mstudyplanner.registration.database.UserDao;

public class LoginActivity extends AppCompatActivity {

    private String userid;
    private String userpassword;
    private String usergender;
    private String usergroup;
    private String useremail;

    /*2. 로그인 처리 로직 구현 - 2019-10-10(목) */
    private AlertDialog dialog;

    /* A-1 DB 처리*/
    private UserDao mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* A-2 DB 처리*/
        mUser = new UserDao(userid, userpassword, usergender, usergroup, useremail);

        /*1. 회원 가입 버튼을 눌렀을때 이벤트 처리하는 로직 구현- */
        TextView registerButton = (TextView) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                /*2. registerIntent를 이용해서 새로운 화면( RegisterActivity)로 이동한다. */
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final Button loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();

                if (userID == mUser.getUserID() && userPassword == mUser.getUserPassword()) {
                 /* 로직 구현 다시 검토 필요*/
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    dialog = builder.setMessage("계정을 다시 확인하세요.")
                            .setNegativeButton("다시 시도", null)
                            .create();
                    dialog.show();

                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    dialog = builder.setMessage("로그인에 성공했습니다.")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(intent);
                    finish();

                }
            }
        });
    }

    //로그인 후 종료 메소드 구현
    @Override
    protected void onStop() {
        super.onStop();
        if(dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}

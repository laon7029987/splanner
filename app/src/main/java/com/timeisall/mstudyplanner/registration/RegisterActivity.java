package com.timeisall.mstudyplanner.registration;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.timeisall.mstudyplanner.registration.database.UserDao;

public class RegisterActivity extends AppCompatActivity {

    /*1-1. 로그인시 회원 가입 처리 위한 spinner 구성(변수 정의) : 2019-10-10(목) */
    private ArrayAdapter adapter;
    private Spinner spinner;

    /*2-1. UserID 중복 처리를 위한 변수 정의 : 2019-10-10(목)*/
    private String userID;
    private String userPassword;
    private String userGender;
    private String userGroup;
    private String userEmail;
    private AlertDialog dialog;
    private boolean validate = false;//사용자 ID가 유효한지(사용할 수 있는 것인지) 검증

    /* A-1 DB 처리*/
    private UserDao mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /* A-2 DB 처리*/
        mUser = new UserDao(userID,userPassword,userGender,userGroup,userEmail);

        /*1-2. 로그인시 회원 가입 처리 위한 spinner 메소드 구성(intent을 통한 화면간 이동) : 2019-10-10(목) */
        spinner = (Spinner) findViewById(R.id.usergroupSpiner);
        adapter = ArrayAdapter.createFromResource(this, R.array.usergroup, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        /*2-2. UserID 중복 처리를 위한 기능(메소스) 정의 */
        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText emailText = (EditText) findViewById(R.id.emailText);

        RadioGroup genderGroup = (RadioGroup) findViewById(R.id.genderGroup);
        int genderGroupID = genderGroup.getCheckedRadioButtonId();
        userGender = ((RadioButton) findViewById(genderGroupID)).getText().toString();// 남자 인지 여자 인지 구분하기 위한 값(value)

        /*2-2-1 RadioButton 처리  */
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                RadioButton  genderButton = (RadioButton) findViewById(i);
                userGender = genderButton.getText().toString();
            }
        });

        /*2-2-2 회원중복 체크 버튼 처리  */
        final Button validateButton = (Button) findViewById(R.id.validateButton);
        validateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();//버튼 클릭시 회원 ID 중복 체크 진행
                if(validate) {
                    return;
                }
                if(userID.equals("")) {/*2-2-2-1 회원 아이디가 존재하지 않는 비정상적인 경우 처리-*/
                   AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                   dialog = builder.setMessage("아이디는 빈 칸일 수 없습니다.").setPositiveButton("확인", null).create();
                   dialog.show();
                   return;
                }
                if(userID == mUser.getUserID()) {//중복체크에 실패할 경우의 처리(즉, 사용할 수 없는 userID인 경우 처리)

                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("사용할 수 없는 아이디 입니다.").setNegativeButton("확인", null).create();
                    dialog.show();

                }else {/*2-2-2-2 회원 아이디가 존재하는 정상적인 경우 처리-2019-10-10(목)*/

                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("사용할 수 있는 아이디 입니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    idText.setEnabled(false);//userID 값을 더 이상 수정할 수 없도록 고정
                    validate = true;
                    idText.setBackgroundColor(getResources().getColor(R.color.colorGray));
                    validateButton.setBackgroundColor(getResources().getColor(R.color.colorGray));
                }
            }
        });// 회원 가입 버튼 처리

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();
                String userGroup = spinner.getSelectedItem().toString();//spinner에서 가져옴
                String userEmail = emailText.getText().toString();

                //중복 체크가 안되었다면 수행 요청
                if(!validate) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("먼저 중복 체크를 해 주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                if(userID.equals("") || userPassword.equals("") || userGender.equals("")|| userGroup.equals("") || userEmail.equals("")) {//빈 공간이 있을 경우 오류 발생 처리
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("빈 칸 없이 입력해 주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                           if(validate) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            dialog = builder.setMessage("회원 등록에 성공했습니다.").setPositiveButton("확인", null).create();
                            dialog.show();
                            finish(); // 회원 등록 종료하도록 처리

                            }else {//회원등록 실패할 경우의 처리(즉, 사용할 수 없는 userID인 경우 처리)

                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("회원 등록에 실패했습니다.").setNegativeButton("확인", null).create();
                                dialog.show();

                            }
            }
        });
    }

    //회원 등록후 종료 메소드 구현
    @Override
    protected void onStop() {
        super.onStop();
        if(dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    /* 다시 검토-
    @Override
    public void onPause() {
        super.onPause();

        StudyDbHandler.get(getActivity).updateUser(UserDao);
    }
     */
}

package com.example.mp_20212979;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SecondActivity extends AppCompatActivity {
    Button btn_signup;
    Button btn_idCheck;
    Button btn_pwCheck;

    EditText editText_id;
    EditText editText_pw;
    EditText editText_name;
    EditText editText_tel;
    EditText editText_address;
    RadioButton radioBtn;

    String agree;
    Boolean idCheck = false;
    Boolean pwCheck = false;

    private Context mContext;

    private static final String SETTINGS_PLAYER_JSON = "settings_item_json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mContext = this;

        editText_id = (EditText) findViewById(R.id.editText_id);
        editText_pw = (EditText) findViewById(R.id.editText_pw);
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_tel = (EditText) findViewById(R.id.editText_tel);
        editText_address = (EditText) findViewById(R.id.editText_address);
        radioBtn = (RadioButton) findViewById(R.id.radioBtn);


        // 아이디 체크 버튼
        btn_idCheck = (Button) findViewById(R.id.btn_idCheck);
        btn_idCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PreferenceManager.getString(mContext, editText_id.getText().toString()) == "") {
                    Toast.makeText(getApplicationContext(), "중복된 아이디가 아닙니다.", Toast.LENGTH_SHORT).show();
                    idCheck = true;
                } else {
                    Toast.makeText(getApplicationContext(), "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
                    idCheck = false;
                }
            }
        });


        // 비밀번호 체크 버튼
        btn_pwCheck = (Button) findViewById(R.id.btn_pwCheck);
        btn_pwCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p = editText_pw.getText().toString();

                // 한글, 영어, 특수문자 허용하지 않고 숫자만 허용함
                String pwPattern = "^[0-9]*$";
                Boolean pCheck = Pattern.matches(pwPattern, p);

                if (p.length() < 4 || pCheck == false) {
                    pwCheck = false;
                    Toast.makeText(getApplicationContext(), "비밀번호는 4자 이상이며 숫자로만 구성되어야 합니다.", Toast.LENGTH_SHORT).show();
                } else {
                    pwCheck = true;
                    Toast.makeText(getApplicationContext(), "비밀번호가 조건에 만족합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // 회원가입 완료 버튼
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radioBtn.isChecked()==true) {
                    agree = "Accept";
                } else {
                    agree = "Decline";
                }

                // 입력 안된 정보 확인
                if (TextUtils.isEmpty(editText_id.getText().toString()) || TextUtils.isEmpty(editText_pw.getText().toString()) || TextUtils.isEmpty(editText_name.getText().toString()) || TextUtils.isEmpty(editText_tel.getText().toString()) || TextUtils.isEmpty(editText_address.getText().toString()) || (agree == "Decline")) {
                    Toast.makeText(getApplicationContext(), "입력 안된 정보가 있습니다.", Toast.LENGTH_SHORT).show();
                } else if (idCheck && pwCheck) {
                    // 회원 정보 저장하기
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(editText_pw.getText().toString());
                    list.add(editText_name.getText().toString());
                    list.add(editText_tel.getText().toString());
                    list.add(editText_address.getText().toString());
                    PreferenceManager.setStringArrayPref(mContext, editText_id.getText().toString(), list);

                    // 디버깅용 코드
                    Log.d(PreferenceManager.getString(mContext, editText_id.getText().toString()), "Put json");
                    Log.d(PreferenceManager.getStringArrayPref(mContext, editText_id.getText().toString()).get(0), "Check pw");

                    /*PreferenceManager.setString(mContext, "id", editText_id.getText().toString());
                    PreferenceManager.setString(mContext, "pw", editText_pw.getText().toString());
                    PreferenceManager.setString(mContext, "name", editText_name.getText().toString());
                    PreferenceManager.setString(mContext, "tel", editText_tel.getText().toString());
                    PreferenceManager.setString(mContext, "address", editText_address.getText().toString());*/

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "유효하지 않은 아이디나 비밀번호가 있습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
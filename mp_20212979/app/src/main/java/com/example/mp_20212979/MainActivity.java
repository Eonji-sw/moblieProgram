package com.example.mp_20212979;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_signup;
    ImageButton img_home;
    Button btn_login;

    EditText editText_id;
    EditText editText_pw;

    Boolean loginCheck = false;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        // 초기화용 코드
        // PreferenceManager.clear(mContext);

        editText_id = (EditText) findViewById(R.id.editText_id);
        editText_pw = (EditText) findViewById(R.id.editText_pw);


        // 회원가입 버튼
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });


        // 홈 버튼
        img_home = (ImageButton) findViewById(R.id.img_home);
        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                startActivity(intent);
            }
        });


        // 로그인 버튼
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // 디버깅용 코드
                    Log.d(PreferenceManager.getStringArrayPref(mContext, editText_id.getText().toString()).get(0), "Check id");
                    Log.d(editText_pw.getText().toString(), "Check pw");

                    if (PreferenceManager.getString(mContext, editText_id.getText().toString()) == "") {
                        Toast.makeText(getApplicationContext(), "유효하지 않은 아이디나 비밀번호가 있습니다!", Toast.LENGTH_SHORT).show();
                    } else if ((PreferenceManager.getStringArrayPref(mContext, editText_id.getText().toString()).get(0)).equals(editText_pw.getText().toString())) {
                        loginCheck = true;
                        Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                        intent.putExtra("id", editText_id.getText().toString());
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "로그인에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        loginCheck = false;
                        Toast.makeText(getApplicationContext(), "유효하지 않은 아이디나 비밀번호가 있습니다.", Toast.LENGTH_SHORT).show();
                    }
                } catch (IndexOutOfBoundsException e) {
                    Toast.makeText(getApplicationContext(), "유효하지 않은 아이디나 비밀번호가 있습니다!!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}
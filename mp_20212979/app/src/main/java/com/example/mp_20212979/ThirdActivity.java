package com.example.mp_20212979;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
    Button btn_profile;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mContext = this;

        ListView listView = findViewById(R.id.listView);
        SingleAdapter adapter = new SingleAdapter();
        adapter.addItem(new singleItem("데미안", "헤르만 헤세", R.drawable.demian));
        adapter.addItem(new singleItem("변신", "프란츠 카프카", R.drawable.die_verwandlung));
        adapter.addItem(new singleItem("주홍글씨", "너새니얼 호손", R.drawable.the_scarlet_letter));
        adapter.addItem(new singleItem("종의 기원", "찰스 다윈", R.drawable.origin_of_species));
        adapter.addItem(new singleItem("이기적 유전자", "리처드 도킨스", R.drawable.the_selfish_gene));
        listView.setAdapter(adapter);


        // 회원정보 버튼
        btn_profile = (Button) findViewById(R.id.btn_profile);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String id = intent.getStringExtra("id");

                System.out.println(id);

                if (TextUtils.isEmpty(id)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ThirdActivity.this);
                    builder.setTitle("회원가입을 진행하시겠습니까?").setMessage("기존 회원이 아닙니다. 회원가입을 진행하시겠습니까?").setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                            startActivity(intent);
                        }
                    }).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).show();
                } else {
                    String name = PreferenceManager.getStringArrayPref(mContext, id).get(1);
                    String tel = PreferenceManager.getStringArrayPref(mContext, id).get(2);
                    String address = PreferenceManager.getStringArrayPref(mContext, id).get(3);

                    AlertDialog.Builder builder = new AlertDialog.Builder(ThirdActivity.this);
                    builder.setTitle("회원정보").setMessage("name : " + name + "\n" + "tel : " + tel + "\n" + "address : " + address).create().show();
                }
            }
        });
    }

    class SingleAdapter extends BaseAdapter {
        ArrayList<singleItem> items = new ArrayList<singleItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(singleItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            singleItemView singleItemView = null;

            if (convertView == null) {
                singleItemView = new singleItemView(getApplicationContext());
            } else {
                singleItemView = (singleItemView) convertView;
            }
            singleItem item = items.get(position);
            singleItemView.setName(item.getName());
            singleItemView.setAuthor(item.getAuthor());
            singleItemView.setImage(item.getResld());

            return singleItemView;
        }
    }
}
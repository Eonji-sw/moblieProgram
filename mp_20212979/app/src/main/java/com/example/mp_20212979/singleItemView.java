package com.example.mp_20212979;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class singleItemView extends LinearLayout {
    TextView textView_name, textView_author;
    ImageView imageView;

    public singleItemView(Context context) {
        super(context);
        init(context);
    }

    public singleItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.single_item_list, this, true);

        textView_name = (TextView) findViewById(R.id.textView_name);
        textView_author = (TextView) findViewById(R.id.textView_author);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void setName(String name) {
        textView_name.setText(name);
    }

    public void setAuthor(String author) {
        textView_author.setText(author);
    }

    public void setImage(int resld) {
        imageView.setImageResource(resld);
    }
}

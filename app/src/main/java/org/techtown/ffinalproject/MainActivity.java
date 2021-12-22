package org.techtown.ffinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv_diary;
    Button bt_back_l;
    Button bt_write_l;


    int color = 0xFF000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // [w] 날짜
        TextView tv_date = findViewById(R.id.tv_date);

        long now = System.currentTimeMillis();
        Date todayDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일");
        String getTime = simpleDate.format(todayDate);


        tv_date.setText("" + getTime);


        // [w] 오늘의 색깔
        TextView tv_colorCircle = findViewById(R.id.tv_colorCircle);
        SeekBar sk_red = findViewById(R.id.sk_red);
        SeekBar sk_green = findViewById(R.id.sk_green);
        SeekBar sk_blue = findViewById(R.id.sk_blue);

        sk_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int red, boolean b) {
                color = 0xFF000000 + red * 0x10000 + sk_green.getProgress() * 0x100 + sk_blue.getProgress();
                TextView tv_colorCircle = findViewById(R.id.tv_colorCircle);
                tv_colorCircle.setTextColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sk_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int green, boolean b) {
                color = 0xFF000000 + sk_red.getProgress() * 0x10000 + green * 0x100 + sk_blue.getProgress();
                TextView tv_colorCircle = findViewById(R.id.tv_colorCircle);
                tv_colorCircle.setTextColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sk_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int blue, boolean b) {
                color = 0xFF000000 + sk_red.getProgress() * 0x10000 + sk_green.getProgress()*0x100 + blue;
                TextView tv_colorCircle = findViewById(R.id.tv_colorCircle);
                tv_colorCircle.setTextColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        color = 0xFF000000 + sk_red.getProgress() * 0x10000 + sk_green.getProgress()*0x100 + sk_blue.getProgress();



        lv_diary = (ListView)findViewById(R.id.lv_diary);
        bt_back_l = (Button)findViewById(R.id.bt_back_l);
        bt_write_l = (Button)findViewById(R.id.bt_write_l);
        Button bt_save = findViewById(R.id.bt_save);

        displayList();


        bt_save.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // [w] 오늘 기분 좋았던 일
                EditText et_happyThings = findViewById(R.id.et_happyThings);
                String happyThings = et_happyThings.getText().toString();


                // [w] 오늘 기분 나빴던 일
                EditText et_sadThings = findViewById(R.id.et_sadThings);
                String sadThings = et_sadThings.getText().toString();

                insertItems(getTime, happyThings, sadThings, todayEmotion, color);

                ScrollView sv_write = findViewById(R.id.sv_write);
                LinearLayout layout_list = findViewById(R.id.layout_list);

                sv_write.setVisibility(View.GONE);
                layout_list.setVisibility(View.VISIBLE);
            }
        });

    }

    void displayList(){
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM student",null);


        ListViewAdapter adapter = new ListViewAdapter();

        while(cursor.moveToNext()){
            adapter.addItemToList(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5));
        }

        lv_diary.setAdapter(adapter);

    }

    void insertItems(String date, String happy, String sad, String emotion, int color){
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();

        String qry = "INSERT INTO student(date, happy, sad, emotion, color) VALUES('" + date + "', '" + happy + "', '" + sad + "', '" + emotion + "', " + color + ")";

        database.execSQL(qry);
        displayList();

    }

    // [w] 오늘의 감정
    String emotion1 = "";
    int num1 = 0;
    String emotion2 = "";
    int num2 = 0;
    String emotion3 = "";
    int num3 = 0;
    String emotion4 = "";
    int num4 = 0;
    String emotion5 = "";
    int num5 = 0;
    String emotion6 = "";
    int num6 = 0;
    String emotion7 = "";
    int num7 = 0;
    String emotion8 = "";
    int num8 = 0;
    String emotion9 = "";
    int num9 = 0;
    String todayEmotion = "";


    // [w] 감정1 버튼
    public void bt_emotion1_onClick(View v){
        Button bt_emotion1 = findViewById(R.id.bt_emotion1);
        num1++;

        if(num1 % 2 == 0){
            bt_emotion1.getBackground().setColorFilter(0x4DE36037,PorterDuff.Mode.MULTIPLY);
        }else{
            bt_emotion1.getBackground().setColorFilter(0xABE36037,PorterDuff.Mode.MULTIPLY);
        }

        if (num1 % 2 != 0) {
            if (todayEmotion.length() < 2) {
                emotion1 = emotion1 + "기쁨";
            } else {
                emotion1 = emotion1 + ", 기쁨";
            }
        }
        else if (num1 > 1 && num1 % 2 == 0) {
            emotion1 = emotion1.substring(0, emotion1.length() - 4);
        }

        todayEmotion = emotion1 + emotion2 + emotion3 + emotion4 + emotion5 + emotion6 + emotion7 + emotion8 + emotion9;
    }



    // [w] 감정2 버튼
    public void bt_emotion2_onClick(View v){
        Button bt_emotion2 = findViewById(R.id.bt_emotion2);
        num2++;

        if(num2 % 2 == 0){
            bt_emotion2.getBackground().setColorFilter(0x4DE36037,PorterDuff.Mode.MULTIPLY);
        }else{
            bt_emotion2.getBackground().setColorFilter(0xABE36037,PorterDuff.Mode.MULTIPLY);
        }

        if (num2 % 2 != 0) {
            if (todayEmotion.length() < 2) {
                emotion2 = emotion2 + "분노";
            } else {
                emotion2 = emotion2 + ", 분노";
            }
        }
        else if (num2 > 1 && num2 % 2 == 0) {
            emotion2 = emotion2.substring(0, emotion2.length() - 4);
        }

        todayEmotion = emotion1 + emotion2 + emotion3 + emotion4 + emotion5 + emotion6 + emotion7 + emotion8 + emotion9;
    }

    // [w] 감정3 버튼
    public void bt_emotion3_onClick(View v){
        Button bt_emotion3 = findViewById(R.id.bt_emotion3);
        num3++;

        if(num3 % 2 == 0){
            bt_emotion3.getBackground().setColorFilter(0x4DE36037,PorterDuff.Mode.MULTIPLY);
        }else{
            bt_emotion3.getBackground().setColorFilter(0xABE36037,PorterDuff.Mode.MULTIPLY);
        }

        if (num3 % 2 != 0) {
            if (todayEmotion.length() < 2) {
                emotion3 = emotion3 + "슬픔";
            } else {
                emotion3 = emotion3 + ", 슬픔";
            }
        }
        else if (num3 > 1 && num3 % 2 == 0) {
            emotion3 = emotion3.substring(0, emotion3.length() - 4);
        }

        todayEmotion = emotion1 + emotion2 + emotion3 + emotion4 + emotion5 + emotion6 + emotion7 + emotion8 + emotion9;
    }

    // [w] 감정4 버튼
    public void bt_emotion4_onClick(View v){
        Button bt_emotion4 = findViewById(R.id.bt_emotion4);
        num4++;

        if(num4 % 2 == 0){
            bt_emotion4.getBackground().setColorFilter(0x4DE36037,PorterDuff.Mode.MULTIPLY);
        }else{
            bt_emotion4.getBackground().setColorFilter(0xABE36037,PorterDuff.Mode.MULTIPLY);
        }

        if (num4 % 2 != 0) {
            if (todayEmotion.length() < 2) {
                emotion4 = emotion4 + "짜증";
            } else {
                emotion4 = emotion4 + ", 짜증";
            }
        }
        else if (num4 > 1 && num4 % 2 == 0) {
            emotion4 = emotion4.substring(0, emotion4.length() - 4);
        }

        todayEmotion = emotion1 + emotion2 + emotion3 + emotion4 + emotion5 + emotion6 + emotion7 + emotion8 + emotion9;
    }

    // [w] 감정5 버튼
    public void bt_emotion5_onClick(View v){
        Button bt_emotion5 = findViewById(R.id.bt_emotion5);
        num5++;

        if(num5 % 2 == 0){
            bt_emotion5.getBackground().setColorFilter(0x4DE36037,PorterDuff.Mode.MULTIPLY);
        }else{
            bt_emotion5.getBackground().setColorFilter(0xABE36037,PorterDuff.Mode.MULTIPLY);
        }

        if (num5 % 2 != 0) {
            if (todayEmotion.length() < 2) {
                emotion5 = emotion5 + "설레임";
            } else {
                emotion5 = emotion5 + ", 설레임";
            }
        }
        else if (num5 > 1 && num5 % 2 == 0) {
            emotion5 = emotion5.substring(0, emotion5.length() - 5);
        }

        todayEmotion = emotion1 + emotion2 + emotion3 + emotion4 + emotion5 + emotion6 + emotion7 + emotion8 + emotion9;
    }

    // [w] 감정6 버튼
    public void bt_emotion6_onClick(View v){
        Button bt_emotion6 = findViewById(R.id.bt_emotion6);
        num6++;

        if(num6 % 2 == 0){
            bt_emotion6.getBackground().setColorFilter(0x4DE36037,PorterDuff.Mode.MULTIPLY);
        }else{
            bt_emotion6.getBackground().setColorFilter(0xABE36037,PorterDuff.Mode.MULTIPLY);
        }

        if (num6 % 2 != 0) {
            if (todayEmotion.length() < 2) {
                emotion6 = emotion6 + "사랑";
            } else {
                emotion6 = emotion6 + ", 사랑";
            }
        }
        else if (num6 > 1 && num6 % 2 == 0) {
            emotion6 = emotion6.substring(0, emotion6.length() - 4);
        }

        todayEmotion = emotion1 + emotion2 + emotion3 + emotion4 + emotion5 + emotion6 + emotion7 + emotion8 + emotion9;
    }

    // [w] 감정7 버튼
    public void bt_emotion7_onClick(View v){
        Button bt_emotion7 = findViewById(R.id.bt_emotion7);
        num7++;

        if(num7 % 2 == 0){
            bt_emotion7.getBackground().setColorFilter(0x4DE36037,PorterDuff.Mode.MULTIPLY);
        }else{
            bt_emotion7.getBackground().setColorFilter(0xABE36037,PorterDuff.Mode.MULTIPLY);
        }

        if (num7 % 2 != 0) {
            if (todayEmotion.length() < 2) {
                emotion7 = emotion7 + "자신감";
            } else {
                emotion7 = emotion7 + ", 자신감";
            }
        }
        else if (num7 > 1 && num7 % 2 == 0) {
            emotion7 = emotion7.substring(0, emotion7.length() - 5);
        }

        todayEmotion = emotion1 + emotion2 + emotion3 + emotion4 + emotion5 + emotion6 + emotion7 + emotion8 + emotion9;
    }

    // [w] 감정8 버튼
    public void bt_emotion8_onClick(View v){
        Button bt_emotion8 = findViewById(R.id.bt_emotion8);
        num8++;

        if(num8 % 2 == 0){
            bt_emotion8.getBackground().setColorFilter(0x4DE36037,PorterDuff.Mode.MULTIPLY);
        }else{
            bt_emotion8.getBackground().setColorFilter(0xABE36037,PorterDuff.Mode.MULTIPLY);
        }

        if (num8 % 2 != 0) {
            if (todayEmotion.length() < 2) {
                emotion8 = emotion8 + "걱정";
            } else {
                emotion8 = emotion8 + ", 걱정";
            }
        }
        else if (num8 > 1 && num8 % 2 == 0) {
            emotion8 = emotion8.substring(0, emotion8.length() - 4);
        }

        todayEmotion = emotion1 + emotion2 + emotion3 + emotion4 + emotion5 + emotion6 + emotion7 + emotion8 + emotion9;
    }

    // [w] 감정9 버튼
    public void bt_emotion9_onClick(View v){
        Button bt_emotion9 = findViewById(R.id.bt_emotion9);
        num9++;

        if(num9 % 2 == 0){
            bt_emotion9.getBackground().setColorFilter(0x4DE36037,PorterDuff.Mode.MULTIPLY);
        }else{
            bt_emotion9.getBackground().setColorFilter(0xABE36037,PorterDuff.Mode.MULTIPLY);
        }

        if (num9 % 2 != 0 ) {
            if (todayEmotion.length() < 2) {
                emotion9 = emotion9 + "행복";
            } else {
                emotion9 = emotion9 + ", 행복";
            }
        }
        else if (num9 > 1 && num9 % 2 == 0) {
            emotion9 = emotion9.substring(0, emotion9.length() - 4);
        }

       todayEmotion = emotion1 + emotion2 + emotion3 + emotion4 + emotion5 + emotion6 + emotion7 + emotion8 + emotion9;

    }


    // [m] 리스트 버튼
    public void bt_goList_onClick(View v) {
        LinearLayout layout_main = findViewById(R.id.layout_main);
        LinearLayout layout_list = findViewById(R.id.layout_list);

        layout_main.setVisibility(View.GONE);
        layout_list.setVisibility(View.VISIBLE);
    }


    public void bt_back_w_onClick(View v) {
        ScrollView sv_write = findViewById(R.id.sv_write);
        LinearLayout layout_list = findViewById(R.id.layout_list);

        sv_write.setVisibility(View.GONE);
        layout_list.setVisibility(View.VISIBLE);
    }

    public void bt_back_l_onClick(View v) {
        LinearLayout layout_main = findViewById(R.id.layout_main);
        LinearLayout layout_list = findViewById(R.id.layout_list);

        layout_main.setVisibility(View.VISIBLE);
        layout_list.setVisibility(View.GONE);
    }

    public void bt_write_l_onClick (View v) {
        ScrollView sv_write = findViewById(R.id.sv_write);
        LinearLayout layout_list = findViewById(R.id.layout_list);

        layout_list.setVisibility(View.GONE);
        sv_write.setVisibility(View.VISIBLE);
    }


}



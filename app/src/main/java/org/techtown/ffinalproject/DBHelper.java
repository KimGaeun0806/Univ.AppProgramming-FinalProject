package org.techtown.ffinalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    final static String DB_NAME = "data.db";
    final static int DB_VERSION = 2;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String qry = "CREATE TABLE student(num INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT NOT NULL, happy TEXT NOT NULL, sad TEXT NOT NULL, emotion TEXT NOT NULL, color INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(qry);


        qry = "INSERT INTO student(date, happy, sad, emotion, color) VALUES('2021년 12월 18일', '예시', '예시', '설레임', '●')";
        sqLiteDatabase.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String qry = "DROP TABLE IF EXISTS student";
        sqLiteDatabase.execSQL(qry);

        onCreate(sqLiteDatabase);

    }
}

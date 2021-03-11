package com.example.rajme.triviaapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{
    SessionManager session;
    private TextView txt2;
    private Button btn1;
    public static int dbres = 0;

    MyDatabaseHelper myDatabaseHelper, myDatabaseHelper1;
    SQLiteDatabase sqLiteDatabase, sqLiteDatabase1;
    public static String uscore ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new SessionManager(getApplicationContext());

        setContentView(R.layout.activity_result);
        txt2 = (TextView) findViewById(R.id.txt2);
        btn1 = (Button) findViewById(R.id.btn1);

        myDatabaseHelper = new MyDatabaseHelper(this);
        sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        myDatabaseHelper1 = new MyDatabaseHelper(this);
        sqLiteDatabase1 = myDatabaseHelper1.getReadableDatabase();

        String res = "";

        Intent i = getIntent();
        res = i.getStringExtra(PlayActivity.EXTRA_TEXT9);

        txt2.setText("" + res);
        btn1.setOnClickListener(this);

        HashMap<String, String> user = session.getUserDetails();

        String name = user.get(SessionManager.KEY_NAME);
        String email = user.get(SessionManager.KEY_EMAIL);
        String password = user.get(SessionManager.KEY_PASS);
        String score = user.get(SessionManager.KEY_SCORE);

        Cursor cursor = sqLiteDatabase1.rawQuery("SELECT USER_NAME, USER_EMAIL, USER_PASS, USER_SCORE FROM USERS WHERE USER_EMAIL = ?", new String[]{email});

        while (cursor.moveToNext()){
           String uname = cursor.getString(0);
           String uemail = cursor.getString(1);
           String upass = cursor.getString(2);
           uscore = cursor.getString(3);
        }

        dbres = Integer.parseInt(res);
        if (dbres>Integer.parseInt(uscore)) {
            ContentValues values = new ContentValues();
            values.put("USER_SCORE", dbres);
            sqLiteDatabase.update("USERS", values, "USER_EMAIL = ?", new String[]{email});
            dbres = 0;
        }
        else {
            Toast.makeText(this, "Better luck next time", Toast.LENGTH_SHORT).show();
            dbres = 0;
        }
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(ResultActivity.this,ProfileActivity.class));
        finish();
    }
}

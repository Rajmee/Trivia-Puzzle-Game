package com.example.rajme.triviaapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edName, edEmail, edPass, edConPass;
    private Button btnReg;
    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        edName = (EditText) findViewById(R.id.edtxt3);
        edEmail = (EditText) findViewById(R.id.edtxt4);
        edPass = (EditText) findViewById(R.id.edtxt5);
        edConPass = (EditText) findViewById(R.id.edtxt6);

        btnReg = (Button) findViewById(R.id.btn2);
        myDatabaseHelper = new MyDatabaseHelper(this);
        sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        btnReg.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String name = edName.getText().toString();
        String email = edEmail.getText().toString();
        String pass = edPass.getText().toString();
        ContentValues cv = new ContentValues();
        int score = 0;
        String conpass = edConPass.getText().toString();
        if (name.equals("")) {
            Toast.makeText(this, "Email Required !!!", Toast.LENGTH_SHORT).show();
        } else if (email.equals("")) {
            Toast.makeText(this, "Password Required !!!", Toast.LENGTH_SHORT).show();
        } else if (pass.equals("")) {
            Toast.makeText(this, "Password Required !!!", Toast.LENGTH_SHORT).show();
        } else if (conpass.equals("")) {
            Toast.makeText(this, "Password Required !!!", Toast.LENGTH_SHORT).show();
        } else if (!conpass.equals(pass)) {
            Toast.makeText(this, "Password Not Matching !!!", Toast.LENGTH_SHORT).show();
        } else {

            cv.put("USER_NAME", name);
            cv.put("USER_EMAIL", email);
            cv.put("USER_PASS", pass);
            cv.put("USER_SCORE", score);
            Long rowId = sqLiteDatabase.insert("USERS", null, cv);
//            Toast.makeText(this, "Row " +rowId+ " inserted", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(RegisActivity.this, MainActivity.class);
            Toast.makeText(getApplicationContext(), "Thank you for Register", Toast.LENGTH_SHORT).show();
            startActivity(i);
            finish();
        }

    }

}

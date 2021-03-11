package com.example.rajme.triviaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edEmail, edPass;
    private Button btnLogin;
    private TextView txtReg;
    SessionManager session;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Pass = "passKey";
    public static final String Score = "scoreKey";


    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(getApplicationContext());

        edEmail = (EditText) findViewById(R.id.edtxt1);
        edPass = (EditText) findViewById(R.id.edtxt2);

//        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        btnLogin = (Button) findViewById(R.id.btn1);
        txtReg = (TextView) findViewById(R.id.txt2);

        myDatabaseHelper = new MyDatabaseHelper(this);
        db1 = myDatabaseHelper.getReadableDatabase();

        btnLogin.setOnClickListener(this);
        txtReg.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btn1:
                String email = edEmail.getText().toString();
                String pass = edPass.getText().toString();

                if (email.equals(""))
                {
                    Toast.makeText(this, "Email Required !!!", Toast.LENGTH_SHORT).show();
                }
                else if (pass.equals(""))
                {
                    Toast.makeText(this, "Password Required !!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean chkemailpass = myDatabaseHelper.emailpass(email,pass);
                    if (chkemailpass==true)
                    {

                        String uname = "";
                        String uemail = "";
                        String uscore = "";
                        String upass = "";

                        Cursor cursor = db1.rawQuery("SELECT USER_NAME, USER_EMAIL, USER_PASS, USER_SCORE FROM USERS WHERE USER_EMAIL = ?", new String[]{email});

                    while (cursor.moveToNext()){
                        uname = cursor.getString(0);
                        uemail = cursor.getString(1);
                        upass = cursor.getString(2);
                        uscore = cursor.getString(3);
                    }
                        session.createLoginSession( uname, uemail, upass, uscore);

                        Intent inn = new Intent(getApplicationContext(),ProfileActivity.class);
                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(inn);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
                    }
                }


                break;
            case R.id.txt2:
                Intent i = new Intent(MainActivity.this, RegisActivity.class);
                startActivity(i);
                finish();;
        }
    }
}

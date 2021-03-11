package com.example.rajme.triviaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;
    SessionManager session;
    private Button btnStart, btnScore;
    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        session = new SessionManager(getApplicationContext());

        btnStart = (Button) findViewById(R.id.btnStart);
        btnScore = (Button) findViewById(R.id.btnScore);
        txtName = (TextView) findViewById(R.id.txtName);

        myDatabaseHelper = new MyDatabaseHelper(this);
        sqLiteDatabase = myDatabaseHelper.getWritableDatabase();


//        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        session.checkLogin();

        HashMap<String, String> user = session.getUserDetails();

        String name = user.get(SessionManager.KEY_NAME);
        String email = user.get(SessionManager.KEY_EMAIL);
        String password = user.get(SessionManager.KEY_PASS);
        String score = user.get(SessionManager.KEY_SCORE);


        txtName.setText("" + name);


        btnStart.setOnClickListener(this);
        btnScore.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item1:
                session.logoutUser();
                Toast.makeText(getApplicationContext(), "Good bye", Toast.LENGTH_SHORT).show();
            case R.id.item2:
                Intent ab = new Intent(ProfileActivity.this, AboutActivity.class);
                startActivity(ab);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btnStart:
                Intent i = new Intent(this, PlayActivity.class);
                startActivity(i);
                break;

            case R.id.btnScore:
                Cursor cursor = sqLiteDatabase.rawQuery("SELECT USER_NAME, USER_SCORE FROM USERS ORDER BY USER_SCORE DESC", null);
                String sb = "";
                int h=1;
                ListView listView = new ListView(this);
                List<String> data = new ArrayList<>();
                while (cursor.moveToNext())
                {
                    sb = h + ". "+ "NAME: " + cursor.getString(0) + "\n    " + "BEST SCORE: " + cursor.getString(1);
                    data.add(sb);
                    h++;
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
                listView.setAdapter(adapter);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setView(listView);
//                builder.show();
                final AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }

    }
}

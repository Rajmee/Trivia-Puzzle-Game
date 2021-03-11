package com.example.rajme.triviaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class PlayActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView rview;
    public static EditText etxt2;
    private Button btn1;
    private ArrayList<Countryinfo> arrayList;
    private ArrayList<Countryinfo> arrayList1;
    private ArrayList<Countryinfo> arrayList2;
    private MyAdapter myAdapter,myAdapter1,myAdapter2;
    private TextView txtview3;
    private TextView txtview4;
    private String cname;
    private static int poss = 0;
    public static String rond;
    public static String cy = "";
    public static int rn;
    private static int point = 0;
    public static final String EXTRA_TEXT9 = "Result";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        rview = (RecyclerView) findViewById(R.id.rView);
        btn1 = (Button) findViewById(R.id.button1);
        etxt2 = (EditText) findViewById(R.id.etxt2);
        txtview3 = (TextView) findViewById(R.id.txt3);
        txtview4 = (TextView) findViewById(R.id.txt4);
        btn1.setOnClickListener(this);
        txtview3.setText("0");
        txtview4.setText("1");
        rond = txtview4.getText().toString();

        arrayList = new ArrayList<>();

        arrayList.add(new Countryinfo("Bangladesh","dhaka"));
        arrayList.add(new Countryinfo("India","new delhi"));
        arrayList.add(new Countryinfo("USA","washington"));
        arrayList.add(new Countryinfo("Japan","tokyo"));
        arrayList.add(new Countryinfo("China","beijing"));

        arrayList1 = new ArrayList<>();

        arrayList1.add(new Countryinfo("Nepal","kathmandu"));
        arrayList1.add(new Countryinfo("Malaysia","kuala lumpur"));
        arrayList1.add(new Countryinfo("Canada","ottawa"));
        arrayList1.add(new Countryinfo("Italy","rome"));
        arrayList1.add(new Countryinfo("Spain","madrid"));


        arrayList2 = new ArrayList<>();

        arrayList2.add(new Countryinfo("England","london"));
        arrayList2.add(new Countryinfo("Australia","canberra"));
        arrayList2.add(new Countryinfo("Saudi Arabia","riyadh"));
        arrayList2.add(new Countryinfo("Brazil","brasilia"));
        arrayList2.add(new Countryinfo("Pakistan","islamabad"));


        myAdapter = new MyAdapter(this, arrayList);
        myAdapter1 = new MyAdapter(this, arrayList1);
        myAdapter2 = new MyAdapter(this, arrayList2);

        LinearLayoutManager la = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rview.setLayoutManager(la);
        rview.setAdapter(myAdapter);


        LinearLayoutManager la1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rview.setLayoutManager(la1);


        LinearLayoutManager la2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rview.setLayoutManager(la2);

        rn = Integer.parseInt(rond);

        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            public void run(){
                txtview3.post(new Runnable() {
                    @Override

                    public void run() {
                        String str = txtview3.getText().toString();
                        int ct = Integer.parseInt(str);
                        ct++;
                        txtview3.setText(ct + "");
                        if(ct == 21){
                            rn++;
                            txtview4.setText(rn + "");
                            if(rn == 2){
                                etxt2.setFocusable(false);
                                etxt2.setFocusableInTouchMode(false);
                                cy = "";
                                etxt2.setText("");
                                rview.setAdapter(myAdapter1);
                                txtview3.setText("0");

                            }
                            else if(rn == 3){
                                etxt2.setFocusable(false);
                                etxt2.setFocusableInTouchMode(false);
                                cy = "";
                                etxt2.setText("");
                                rview.setAdapter(myAdapter2);
                                txtview3.setText("0");
                            }
                            else {
                                resultshow();
                            }
                        }

                    }
                });
            }
        };
        t.schedule(tt, 0, 1000);
    }

    public void resultshow(){
        Intent in = new Intent(this, ResultActivity.class);
        String p = Integer.toString(point);
        in.putExtra(EXTRA_TEXT9, p);
        startActivity(in);
        point = 0;
    }

    public static void getEnable(){
        etxt2.setFocusable(true);
        etxt2.setFocusableInTouchMode(true);
    }

    @Override
    public void onClick(View view) {
        if (rn == 1) {
            String ct = etxt2.getText().toString();
            String cons = MyAdapter.getCon();
            poss = MyAdapter.getPos();
            cy = MyAdapter.getCit();
            if (ct.equals(cy)) {
                point++;
                arrayList.remove(poss);
                Toast.makeText(this, "Correct answer\nYou have earn one point", Toast.LENGTH_SHORT).show();
                myAdapter.notifyItemRemoved(poss);
                myAdapter.notifyDataSetChanged();
                ct = "";
                etxt2.setText("");
                etxt2.setFocusable(false);
                etxt2.setFocusableInTouchMode(false);
                cy = "";
            } else {
                Toast.makeText(this, "Oops!! Wrong answer\nTry again", Toast.LENGTH_SHORT).show();
                myAdapter.notifyDataSetChanged();
                ct = "";
            }
            etxt2.setText("");
        }
        else if (rn == 2) {
            String ct = etxt2.getText().toString();
            String cons = MyAdapter.getCon();
            poss = MyAdapter.getPos();
            cy = MyAdapter.getCit();
            if (ct.equals(cy)) {
                point++;
                arrayList1.remove(poss);
                Toast.makeText(this, "Correct answer\nYou have earn one point", Toast.LENGTH_SHORT).show();
                myAdapter1.notifyItemRemoved(poss);
                myAdapter1.notifyDataSetChanged();
                ct = "";
                etxt2.setText("");
                etxt2.setFocusable(false);
                etxt2.setFocusableInTouchMode(false);
                cy = "";
            } else {
                Toast.makeText(this, " Oops!! Wrong answer\nTry again", Toast.LENGTH_SHORT).show();
                myAdapter1.notifyDataSetChanged();
                ct = "";
            }
            etxt2.setText("");
        }
        else if (rn == 3) {
            String ct = etxt2.getText().toString();
            String cons = MyAdapter.getCon();
            poss = MyAdapter.getPos();
            String cy = MyAdapter.getCit();
            if (ct.equals(cy)) {
                point++;
                arrayList2.remove(poss);
                Toast.makeText(this, "Correct answer\nYou have earn one point", Toast.LENGTH_SHORT).show();
                myAdapter2.notifyItemRemoved(poss);
                myAdapter2.notifyDataSetChanged();
                ct = "";
                etxt2.setText("");
                etxt2.setFocusable(false);
                etxt2.setFocusableInTouchMode(false);
                cy = "";
            } else {
                Toast.makeText(this, "Oops!! Wrong answer\nTry again", Toast.LENGTH_SHORT).show();
                myAdapter2.notifyDataSetChanged();
                ct = "";
            }
            etxt2.setText("");
        }
    }
}

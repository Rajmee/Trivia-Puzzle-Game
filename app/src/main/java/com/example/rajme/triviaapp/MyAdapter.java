package com.example.rajme.triviaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by RAJME on 4/21/2020.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private Context mcontext;
    private ArrayList<Countryinfo> mArray;
    private static String con;
    private static String c;
    private static int pos = 0;
    private static int cpos = 0;


    public MyAdapter(Context mcontext, ArrayList<Countryinfo> mArray){
        this.mcontext = mcontext;
        this.mArray = mArray;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.recycler_row, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position) {
//        Countryinfo s = mArray.get(position);
        holder.country.setText(mArray.get(position).getCountry());
//        holder.city.setText(mArray.get(position).getCity());
        holder.country.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                TextView temp = (TextView) view;
                String s = temp.getText().toString();
                Toast t = Toast.makeText(mcontext, s + " Selected",
                        Toast.LENGTH_SHORT);
                t.show();
                PlayActivity.getEnable();
                con = s.toString();
                pos = position;
                cpos = position;
                c = mArray.get(position).getCity();
            }
        });
    }

    public static String getCon(){
        return con;
    }

    public static int getPos(){
        return pos;
    }

    public static String getCit(){
        return c;
    }

    public static int getCPos(){
        return cpos;
    }

    @Override
    public int getItemCount() {
        return mArray.size();
    }

    public void delete(int position){

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView country;
//        TextView city;

        public MyViewHolder(View itemView) {
            super(itemView);
            country = (TextView) itemView.findViewById(R.id.txtView);
        }
    }
}

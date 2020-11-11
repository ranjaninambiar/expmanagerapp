package com.example.expmanager2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class User_profile extends AppCompatActivity {

    TextView tvR, tvPython, tvCPP, tvc1,tvc2,tvc3;
    PieChart pieChart,pieChart1,pieChart2;
    SQLiteDatabase db2,db3;
    public String name;
    final int MYREQUEST3 = 117;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Bundle b=getIntent().getExtras();
        name = b.getString("name");
        // Link those objects with their
        // respective id's that
        // we have given in .XML file
        tvR = findViewById(R.id.tvR);
        tvPython = findViewById(R.id.tvPython);
        tvCPP = findViewById(R.id.tvCPP);
        //tvc1=findViewById(R.id.tvc1);
        //tvc2=findViewById(R.id.tvc2);
        //tvc3=findViewById(R.id.tvc3);
        //tvJava = findViewById(R.id.tvJava);
        pieChart = findViewById(R.id.piechart);
        pieChart1 = findViewById(R.id.piechart1);
        pieChart2 = findViewById(R.id.piechart2);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_home1);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.action_home1:
                        return true;
                    case R.id.action_rem:
                        Intent in=new Intent(getApplicationContext(),catagorylist.class);
                        //startActivity(new Intent(getApplicationContext(),catagorylist.class));
                        in.putExtra("name", name);
                        startActivityForResult(in,MYREQUEST3);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.action_settings1:
                        startActivity(new Intent(getApplicationContext(),About_us.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.action_cal:
                        startActivity(new Intent(getApplicationContext(),login.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        // Creating a method setData()
        // to set the text in text view and pie chart
        db2 = openOrCreateDatabase("UsersDB", Context.MODE_PRIVATE, null);
        db3 = openOrCreateDatabase(name, Context.MODE_PRIVATE, null);
        setData();
        setData1();
        setData2();
        //db2 = openOrCreateDatabase("UsersDB", Context.MODE_PRIVATE, null);
    }

    private void setData()
    {
        Cursor cursor1 = db2.rawQuery("select * from expensedets where price = 'Below 1000' and uname ='"+name+"';", null);
        int c1=cursor1.getCount();
        Cursor cursor2 = db2.rawQuery("select * from expensedets where price = 'Below 5000' and uname ='"+name+"';", null);
        int c2=cursor2.getCount();
        Cursor cursor3 = db2.rawQuery("select * from expensedets where price = 'Above 5000 ' and uname ='"+name+"';", null);
        int c3=cursor3.getCount();
        //int c01=(c1/(c1+c2+c3))*100;
        //int c02=(c2/(c1+c2+c3))*100;
        //int c03=100-(c01+c02);
        // Set the percentage of language used
        tvR.setText(Integer.toString(c1));
        tvPython.setText(Integer.toString(c2));
        tvCPP.setText(Integer.toString(c3));
        //tvJava.setText(Integer.toString(25));

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Below 1000",
                        Integer.parseInt(tvR.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Below 5000",
                        Integer.parseInt(tvPython.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Above 5000",
                        Integer.parseInt(tvCPP.getText().toString()),
                        Color.parseColor("#EF5350")));


        // To animate the pie chart
        pieChart.startAnimation();
    }
    private void setData1()
    {

        Cursor cursors1 = db3.rawQuery("select * from remdb where name = 'ELECTRICITY';", null);
        int c1=cursors1.getCount();
        Cursor cursors2 = db3.rawQuery("select * from remdb where name = 'DTH/CABLE';", null);
        int c2=cursors2.getCount();
        Cursor cursors3 = db3.rawQuery("select * from remdb where name = 'GROCERY';", null);
        int c3=cursors3.getCount();
        Cursor cursors4 = db3.rawQuery("select * from remdb where name = 'ONLINE SHOPPING';", null);
        int c4=cursors4.getCount();
        Cursor cursors5 = db3.rawQuery("select * from remdb where name = 'GYM';", null);
        int c5=cursors5.getCount();
        Cursor cursors6 = db3.rawQuery("select * from remdb where name = 'FOOD';", null);
        int c6=cursors6.getCount();
        Cursor cursors7 = db3.rawQuery("select * from remdb where name = 'RETAIL';", null);
        int c7=cursors7.getCount();
        Cursor cursors8 = db3.rawQuery("select * from remdb where name = 'TELEPHONE';", null);
        int c8=cursors8.getCount();
        Cursor cursors9 = db3.rawQuery("select * from remdb where name = 'TRAVEL';", null);
        int c9=cursors9.getCount();
        Cursor cursors10 = db3.rawQuery("select * from remdb where name = 'MEDICINE';", null);
        int c10=cursors10.getCount();
        Cursor cursors11 = db3.rawQuery("select * from remdb where name = 'INSURANCE';", null);
        int c11=cursors11.getCount();
        Cursor cursors12 = db3.rawQuery("select * from remdb where name = 'LOAN';", null);
        int c12=cursors12.getCount();
        Cursor cursors13 = db3.rawQuery("select * from remdb where name = 'OTHERS';", null);
        int c13=cursors13.getCount();



        // Set the data and color to the pie chart
        pieChart1.addPieSlice(
                new PieModel(
                        "Electricity",
                        Integer.parseInt(Integer.toString(c1)),
                        Color.parseColor("#FFA726")));
        pieChart1.addPieSlice(
                new PieModel(
                        "DTH/Cable",
                        Integer.parseInt(Integer.toString(c2)),
                        Color.parseColor("#66BB6A")));
        pieChart1.addPieSlice(
                new PieModel(
                        "Grocery",
                        Integer.parseInt(Integer.toString(c3)),
                        Color.parseColor("#EF5350")));
        pieChart1.addPieSlice(
                new PieModel(
                        "Online Shopping",
                        Integer.parseInt(Integer.toString(c4)),
                        Color.parseColor("#29B6F6")));
        pieChart1.addPieSlice(
                new PieModel(
                        "Gym",
                        Integer.parseInt(Integer.toString(c5)),
                        Color.parseColor("#DE25E4")));
        pieChart1.addPieSlice(
                new PieModel(
                        "Food delivery",
                        Integer.parseInt(Integer.toString(c6)),
                        Color.parseColor("#00796B")));
        pieChart1.addPieSlice(
                new PieModel(
                        "Retail",
                        Integer.parseInt(Integer.toString(c7)),
                        Color.parseColor("#E64A19")));
        pieChart1.addPieSlice(
                new PieModel(
                        "Telephone",
                        Integer.parseInt(Integer.toString(c8)),
                        Color.parseColor("#689F38")));
        pieChart1.addPieSlice(
                new PieModel(
                        "Travel",
                        Integer.parseInt(Integer.toString(c9)),
                        Color.parseColor("#FF9E80")));
        pieChart1.addPieSlice(
                new PieModel(
                        "Medicines",
                        Integer.parseInt(Integer.toString(c10)),
                        Color.parseColor("#F4FF81")));
        pieChart1.addPieSlice(
                new PieModel(
                        "Insurance",
                        Integer.parseInt(Integer.toString(c11)),
                        Color.parseColor("#D50000")));
        pieChart1.addPieSlice(
                new PieModel(
                        "Loan",
                        Integer.parseInt(Integer.toString(c12)),
                        Color.parseColor("#AD1457")));
        pieChart1.addPieSlice(
                new PieModel(
                        "Other Services",
                        Integer.parseInt(Integer.toString(c13)),
                        Color.parseColor("#673AB7")));
        // To animate the pie chart
        pieChart1.startAnimation();

    }
    private void setData2()
    {


        Cursor cursor1 = db3.rawQuery("select total from expend where catagory = 'ELECTRICITY';", null);
        String a1="0";
        if (cursor1.moveToFirst()) {
            a1=cursor1.getString(cursor1.getColumnIndex("total"));
        }
        //String a1=cursor1.getString(cursor1.getColumnIndex("total"));
        Cursor cursor2 = db3.rawQuery("select total from expend where catagory = 'DTH/CABLE';", null);
        String a2="0";
        if (cursor2.moveToFirst()) {
            a2=cursor1.getString(cursor2.getColumnIndex("total"));
        }
        //String a2=cursor2.getString(cursor2.getColumnIndex("total"));
        Cursor cursor3 = db3.rawQuery("select total from expend where catagory = 'GROCERY';", null);
        String a3="0";
        if (cursor3.moveToFirst()) {
            a3=cursor3.getString(cursor3.getColumnIndex("total"));
        }
       // String a3=cursor3.getString(cursor3.getColumnIndex("total"));
        Cursor cursor4 = db3.rawQuery("select total from expend where catagory = 'ONLINE SHOPPING';", null);
        String a4="0";
        if (cursor4.moveToFirst()) {
            a4=cursor4.getString(cursor4.getColumnIndex("total"));
        }
        //String a4=cursor4.getString(cursor4.getColumnIndex("total"));
        Cursor cursor5 = db3.rawQuery("select total from expend where catagory = 'GYM';", null);
        String a5="0";
        if (cursor5.moveToFirst()) {
            a5=cursor5.getString(cursor5.getColumnIndex("total"));
        }
       // String a5=cursor5.getString(cursor5.getColumnIndex("total"));
        Cursor cursor6 = db3.rawQuery("select total from expend where catagory = 'FOOD';", null);
        String a6="0";
        if (cursor6.moveToFirst()) {
            a6=cursor6.getString(cursor6.getColumnIndex("total"));
        }
        //String a6=cursor6.getString(cursor6.getColumnIndex("total"));
        Cursor cursor7 = db3.rawQuery("select total from expend where catagory = 'RETAIL';", null);
        String a7="0";
        if (cursor7.moveToFirst()) {
            a7=cursor7.getString(cursor7.getColumnIndex("total"));
        }
        //String a7=cursor7.getString(cursor7.getColumnIndex("total"));
        Cursor cursor8 = db3.rawQuery("select total from expend where catagory = 'TELEPHONE';", null);
        String a8="0";
        if (cursor8.moveToFirst()) {
            a8=cursor8.getString(cursor8.getColumnIndex("total"));
        }
        //String a8=cursor8.getString(cursor8.getColumnIndex("total"));
        Cursor cursor9 = db3.rawQuery("select total from expend where catagory = 'TRAVEL';", null);
        String a9="0";
        if (cursor9.moveToFirst()) {
            a9=cursor9.getString(cursor9.getColumnIndex("total"));
        }
        //String a9=cursor9.getString(cursor9.getColumnIndex("total"));
        Cursor cursor10 = db3.rawQuery("select total from expend where catagory = 'MEDICINE';", null);
        String a10="0";
        if (cursor10.moveToFirst()) {
            a10=cursor10.getString(cursor10.getColumnIndex("total"));
        }
        //String a10=cursor10.getString(cursor10.getColumnIndex("total"));
        Cursor cursor11 = db3.rawQuery("select total from expend where catagory = 'INSURANCE';", null);
        String a11="0";
        if (cursor11.moveToFirst()) {
            a11=cursor11.getString(cursor11.getColumnIndex("total"));
        }
        //String a11=cursor11.getString(cursor11.getColumnIndex("total"));
        Cursor cursor12 = db3.rawQuery("select total from expend where catagory = 'LOAN';", null);
        String a12="0";
        if (cursor12.moveToFirst()) {
            a12=cursor12.getString(cursor12.getColumnIndex("total"));
        }
        //String a12=cursor12.getString(cursor12.getColumnIndex("total"));
        Cursor cursor13 = db3.rawQuery("select total from expend where catagory = 'OTHERS';", null);
        String a13="0";
        if (cursor13.moveToFirst()) {
            a13=cursor13.getString(cursor13.getColumnIndex("total"));
        }
        //String a13=cursor13.getString(cursor13.getColumnIndex("total"));

        pieChart2.addPieSlice(
                new PieModel(
                        "Electricity",
                        Integer.parseInt(a1),
                        Color.parseColor("#FFA726")));
        pieChart2.addPieSlice(
                new PieModel(
                        "DTH/Cable",
                        Integer.parseInt(a2),
                        Color.parseColor("#66BB6A")));
        pieChart2.addPieSlice(
                new PieModel(
                        "Grocery",
                        Integer.parseInt(a3),
                        Color.parseColor("#EF5350")));
        pieChart2.addPieSlice(
                new PieModel(
                        "Online Shopping",
                        Integer.parseInt(a4),
                        Color.parseColor("#29B6F6")));
        pieChart2.addPieSlice(
                new PieModel(
                        "Gym",
                        Integer.parseInt(a5),
                        Color.parseColor("#DE25E4")));
        pieChart2.addPieSlice(
                new PieModel(
                        "Food delivery",
                        Integer.parseInt(a6),
                        Color.parseColor("#00796B")));
        pieChart2.addPieSlice(
                new PieModel(
                        "Retail",
                        Integer.parseInt(a7),
                        Color.parseColor("#E64A19")));
        pieChart2.addPieSlice(
                new PieModel(
                        "Telephone",
                        Integer.parseInt(a8),
                        Color.parseColor("#689F38")));
        pieChart2.addPieSlice(
                new PieModel(
                        "Travel",
                        Integer.parseInt(a9),
                        Color.parseColor("#FF9E80")));
        pieChart2.addPieSlice(
                new PieModel(
                        "Medicines",
                        Integer.parseInt(a10),
                        Color.parseColor("#F4FF81")));
        pieChart2.addPieSlice(
                new PieModel(
                        "Insurance",
                        Integer.parseInt(a11),
                        Color.parseColor("#D50000")));
        pieChart2.addPieSlice(
                new PieModel(
                        "Loan",
                        Integer.parseInt(a12),
                        Color.parseColor("#AD1457")));
        pieChart2.addPieSlice(
                new PieModel(
                        "Other Services",
                        Integer.parseInt(a13),
                        Color.parseColor("#673AB7")));
        // To animate the pie chart
        pieChart2.startAnimation();

    }
}
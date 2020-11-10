package com.example.expmanager2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class User_profile extends AppCompatActivity {

    TextView tvR, tvPython, tvCPP, tvJava;
    PieChart pieChart,pieChart1;
    SQLiteDatabase db2;
    public String name;

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
        //tvJava = findViewById(R.id.tvJava);
        pieChart = findViewById(R.id.piechart);

        // Creating a method setData()
        // to set the text in text view and pie chart
        db2 = openOrCreateDatabase("UsersDB", Context.MODE_PRIVATE, null);
        setData();
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

        Cursor cursors1 = db2.rawQuery("select * from expend where catagory = 'Below 1000' and uname ='"+name+"';", null);
        int c1=cursors1.getCount();
        Cursor cursors2 = db2.rawQuery("select * from expend where price = 'Below 5000' and uname ='"+name+"';", null);
        int c2=cursors2.getCount();
        Cursor cursors3 = db2.rawQuery("select * from expend where price = 'Above 5000 ' and uname ='"+name+"';", null);
        int c3=cursors3.getCount();
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
        pieChart.addPieSlice(
                new PieModel(
                        "Java",
                        Integer.parseInt(tvJava.getText().toString()),
                        Color.parseColor("#29B6F6")));

        // To animate the pie chart
        pieChart.startAnimation();

    }
}
package com.example.expmanager2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity implements View.OnClickListener {

    Button log;
    EditText un,passw;
    SQLiteDatabase db1;
    final int MYREQUEST1 = 111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        log=(Button)findViewById(R.id.b1);
        un= (EditText) findViewById(R.id.ed1);
        passw= (EditText) findViewById(R.id.ed2); //mobile no.
        db1 = openOrCreateDatabase("UsersDB", Context.MODE_PRIVATE, null);
        log.setOnClickListener(this);

    }
    public void onClick(View view) {
        // Adding a record
        if (view == log) {
            Cursor c = db1.rawQuery("SELECT * FROM userdet WHERE uname='" + un.getText() + "'", null);
            if (c.moveToFirst() ){
                if (c.getString(1).matches(String.valueOf(passw.getText()))) {
                    Toast.makeText(getApplicationContext(),"Login successful", Toast.LENGTH_SHORT).show();
                   Intent in=new Intent(this,form.class);
                   //in.putExtra("name","Login");
                   in.putExtra("name", String.valueOf(un.getText()));


                   startActivityForResult(in,MYREQUEST1);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Uname or pwd incorrect", Toast.LENGTH_SHORT).show();
                }
            }}

    }

}

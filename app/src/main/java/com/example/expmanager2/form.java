package com.example.expmanager2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class form extends AppCompatActivity implements View.OnClickListener {
    CheckBox dth,groc,gym,food,retail,shopping,eb,services,travel,medicine,insurance,loan;
    SQLiteDatabase db1;
    public static String name = "";
    private String  test="";
    RadioGroup rg;
    RelativeLayout rl;
    RadioButton rb1,rb2,rb3;
    Button mButton;
    final int MYREQUEST2 = 131;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Bundle b=getIntent().getExtras();
        mButton = findViewById(R.id.btnSend);
        mButton.setOnClickListener(this);
        name = b.getString("name");

        dth=(CheckBox)findViewById(R.id.check_dth) ;
        groc=(CheckBox)findViewById(R.id.check_groceries) ;
        gym=(CheckBox)findViewById(R.id.check_gym) ;
        food=(CheckBox)findViewById(R.id.check_food) ;
        retail=(CheckBox)findViewById(R.id.check_retail) ;
        shopping=(CheckBox)findViewById(R.id.check_shopping) ;
        eb=(CheckBox)findViewById(R.id.check_eb) ;
        services=(CheckBox)findViewById(R.id.check_services) ;
        travel=(CheckBox)findViewById(R.id.check_travel) ;
        medicine=(CheckBox)findViewById(R.id.check_medicine) ;
        insurance=(CheckBox)findViewById(R.id.check_insurance) ;
        loan=(CheckBox)findViewById(R.id.check_loan) ;

        db1 = openOrCreateDatabase("UsersDB", Context.MODE_PRIVATE, null);
        db1.execSQL("CREATE TABLE IF NOT EXISTS expensedets(uname VARCHAR primary key,expense VARCHAR primary key,price VARCHAR);");
        //db1.execSQL("DELETE FROM expensedets;");
        createradiogroup();
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        //boolean checked = ((CheckBox) view).isChecked();
        if(dth.isChecked())
        {
            // true,do the task
            createradiogroup();
            if(test!="") {
                Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "' ;", null);
                if (c.moveToFirst()) {

                    // Deleting record if found 
                    //showMessage("Success", "Record Deleted");
                    db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='DTH';");
                    //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','DTH'," +"'" + test + "'); ");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                else{
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','DTH'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                }
               dth.setChecked(false);
            }}
        else if(gym.isChecked()){
            createradiogroup();
            if (test != "") {
                Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "' AND expenses='GYM';", null);

                if (c.moveToFirst()) {

                    // Deleting record if found 
                    //showMessage("Success", "Record Deleted");
                    db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='GYM';");
                    //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','GYM'," + "'" + test + "');");
                    //db1.execSQL("UPDATE expensedets SET PRICE='"+ test +"' WHERE EXPENSES='GYM' AND uname='" + name + "';");
                    Toast.makeText(getApplicationContext(), "Inserted successfully" + test, Toast.LENGTH_SHORT).show();
                } else {
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','GYM'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted separately successfully"+test, Toast.LENGTH_SHORT).show();

                }
                gym.setChecked(false);
        }}
        else if(groc.isChecked()){
            createradiogroup();
            if(test!="") {
                Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "'AND expenses='Groceries' ;", null);
                if (c.moveToFirst()) {

                    // Deleting record if found 
                    //showMessage("Success", "Record Deleted");
                    db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Groceries';");
                    //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Groceries'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();

                        }
                        else{
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Groceries'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                        }

                groc.setChecked(false);
            }}
        else if(food.isChecked()){
            createradiogroup();
            if(test!=""){
                Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "' AND expenses='Food';", null);
                if (c.moveToFirst()) {

                    // Deleting record if found 
                    //showMessage("Success", "Record Deleted");
                    db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Food';");
                    //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Food'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                else{
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Food'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                }
                food.setChecked(false);
            }}
        else if(retail.isChecked()){
            createradiogroup();
            if(test!=""){
                Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "' AND expenses='Retail';", null);
                if (c.moveToFirst()) {

                    // Deleting record if found 
                    //showMessage("Success", "Record Deleted");
                    db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Retail';");
                    //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Retail'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                else{
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Retail'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                }
                retail.setChecked(false);
            }
        }
        else if(shopping.isChecked()){
            createradiogroup();
            if(test!=""){
                Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "' AND expenses='Shopping';", null);
                if (c.moveToFirst()) {

                    // Deleting record if found 
                    //showMessage("Success", "Record Deleted");
                    db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Shopping';");
                    //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Shopping'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                else{
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Shopping'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                }
                shopping.setChecked(false);
            }}
        else if(eb.isChecked()){
            createradiogroup();
            if(test!=""){
                Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "'AND expenses='EB';", null);
                if (c.moveToFirst()) {

                    // Deleting record if found 
                    //showMessage("Success", "Record Deleted");
                    db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='EB';");
                    //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','EB'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                else{
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','EB'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                }
                eb.setChecked(false);
            }}

        else if(services.isChecked()){
            createradiogroup();
            if(test!=""){
                Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "' AND expenses='Other Services';", null);
                if (c.moveToFirst()) {

                    // Deleting record if found 
                    //showMessage("Success", "Record Deleted");
                    db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Other Services';");
                    //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Other Services'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                else{
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Other Services'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                }
                services.setChecked(false);
            }}
            else if(travel.isChecked()){
            createradiogroup();
            if(test!=""){
                Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "' AND expenses='Travel';", null);
                if (c.moveToFirst()) {

                    // Deleting record if found 
                    //showMessage("Success", "Record Deleted");
                    db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Travel';");
                    //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Travel'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                else{
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Travel'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                }
                travel.setChecked(false);
            }}
            else if(medicine.isChecked()){
            createradiogroup();
            if(test!=""){
                Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "' AND expenses='Medicine' ;", null);
                if (c.moveToFirst()) {

                    // Deleting record if found 
                    //showMessage("Success", "Record Deleted");
                    db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Medicine';");
                    //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Medicine'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                else{
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Medicine'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                }
                medicine.setChecked(false);
            }}

        else if(insurance.isChecked()){
            createradiogroup();
            if(test!=""){
                Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "'AND expenses='Insurance';", null);
                if (c.moveToFirst()) {

                    // Deleting record if found 
                    //showMessage("Success", "Record Deleted");
                    db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Insurance';");
                    //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Insurance'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                else{
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Insurance'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                }
                insurance.setChecked(false);
            }
        }

        else if(loan.isChecked()){
            createradiogroup();
            if(test!=""){
                Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "'AND expenses='Loan';", null);
                if (c.moveToFirst()) {

                    // Deleting record if found 
                    //showMessage("Success", "Record Deleted");
                    db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Loan';");
                    //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Loan'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                else{
                    db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Loan'," +"'" + test + "');");
                    Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                }
                loan.setChecked(false);
            }
        }

        /*

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.check_dth:
                if (checked){
                    createradiogroup();
                    if(test!="") {
                        Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "' ;", null);
                        if (c.moveToFirst()) {

                            // Deleting record if found 
                            //showMessage("Success", "Record Deleted");
                            db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='DTH';");
                            //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','DTH'," +"'" + test + "'); ");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                        else{
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','DTH'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                        }

                }}
                // Put some meat on the sandwich
            else
                break;
            case R.id.check_gym:
                if (checked) {
                    createradiogroup();
                    if (test != "") {
                        Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "' AND expenses='GYM';", null);

                        if (c.moveToFirst()) {

                            // Deleting record if found 
                            //showMessage("Success", "Record Deleted");
                            db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='GYM';");
                            //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','GYM'," + "'" + test + "');");
                            //db1.execSQL("UPDATE expensedets SET PRICE='"+ test +"' WHERE EXPENSES='GYM' AND uname='" + name + "';");
                            Toast.makeText(getApplicationContext(), "Inserted successfully" + test, Toast.LENGTH_SHORT).show();
                        } else {
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','GYM'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted separately successfully"+test, Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            else
                break;
            case R.id.check_groceries:
                if (checked){
                    createradiogroup();
                    int trap=0;
                    if(test!="") {
                        Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "'AND expenses='Groceries' ;", null);
                        if (c.moveToFirst()) {

                                // Deleting record if found 
                                //showMessage("Success", "Record Deleted");
                                db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Groceries';");
                            //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Groceries'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();*/

                            /*
                            // Modifying record if found 
                            while (c.moveToNext()) {
                                if (c.getString(1) == "Groceries") {
                                    db1.execSQL("UPDATE expensedets SET price='" + test +
                                            "' WHERE expense='Groceries';");
                                    //showMessage("Success", "Record Modified");
                                    Toast.makeText(getApplicationContext(),"Updated successfully"+test, Toast.LENGTH_SHORT).show();
                                    trap=1;
                                    break;
                                }
                            }
                            if(trap==0){
                                db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Groceries'," +"'" + test + "');");
                                Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}*/
                            /*
                        }
                        else{
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Groceries'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                        }


                    }}
                    // Put some meat on the sandwich
                    else
                        break;
            case R.id.check_food:
                if (checked){
                    createradiogroup();
                    if(test!=""){
                        Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "' AND expenses='Food';", null);
                        if (c.moveToFirst()) {

                            // Deleting record if found 
                            //showMessage("Success", "Record Deleted");
                            db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Food';");
                            //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Food'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                        else{
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Food'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                        }
                    }}
                    else
                        break;
            case R.id.check_eb:
                if (checked){
                    createradiogroup();
                    if(test!=""){
                        Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "'AND expenses='EB';", null);
                        if (c.moveToFirst()) {

                            // Deleting record if found 
                            //showMessage("Success", "Record Deleted");
                            db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='EB';");
                            //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','EB'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                        else{
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','EB'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                        }
                    }}
                    else
                        break;
            case R.id.check_insurance:
                if (checked){
                    createradiogroup();
                    if(test!=""){
                        Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "'AND expenses='Insurance';", null);
                        if (c.moveToFirst()) {

                            // Deleting record if found 
                            //showMessage("Success", "Record Deleted");
                            db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Insurance';");
                            //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Insurance'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                        else{
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Insurance'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                        }
                    }}
                    else
                        break;
            case R.id.check_loan:
                if (checked){
                    createradiogroup();
                    if(test!=""){
                        Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "'AND expenses='Loan';", null);
                        if (c.moveToFirst()) {

                            // Deleting record if found 
                            //showMessage("Success", "Record Deleted");
                            db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Loan';");
                            //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Loan'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                        else{
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Loan'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                        }
                    }}
                    else
                        break;
            case R.id.check_medicine:
                if (checked){
                    createradiogroup();
                    if(test!=""){
                        Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "' AND expenses='Medicine' ;", null);
                        if (c.moveToFirst()) {

                            // Deleting record if found 
                            //showMessage("Success", "Record Deleted");
                            db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Medicine';");
                            //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Medicine'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                        else{
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Medicine'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                        }
                    }}
                    else
                        break;
            case R.id.check_retail:
                if (checked){
                    createradiogroup();
                    if(test!=""){
                        Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "' AND expenses='Retail';", null);
                        if (c.moveToFirst()) {

                            // Deleting record if found 
                            //showMessage("Success", "Record Deleted");
                            db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Retail';");
                            //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Retail'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                        else{
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Retail'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                        }
                    }}
                    else
                        break;
            case R.id.check_services:
                if (checked){
                    createradiogroup();
                    if(test!=""){
                        Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "' AND expenses='Other Services';", null);
                        if (c.moveToFirst()) {

                            // Deleting record if found 
                            //showMessage("Success", "Record Deleted");
                            db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Other Services';");
                            //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Other Services'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                        else{
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Other Services'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                        }
                    }}
                    else
                        break;
            case R.id.check_shopping:
                if (checked){
                    createradiogroup();
                    if(test!=""){
                        Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "' AND expenses='Shopping';", null);
                        if (c.moveToFirst()) {

                            // Deleting record if found 
                            //showMessage("Success", "Record Deleted");
                            db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Shopping';");
                            //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Shopping'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                        else{
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Shopping'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                        }
                    }}
                    else
                        break;
            case R.id.check_travel:
                if (checked){
                    createradiogroup();
                    if(test!=""){
                        Cursor c = db1.rawQuery("SELECT * FROM expensedets WHERE uname='" + name + "' AND expenses='Travel';", null);
                        if (c.moveToFirst()) {

                            // Deleting record if found 
                            //showMessage("Success", "Record Deleted");
                            db1.execSQL("DELETE FROM expensedets WHERE uname='" + name + "' AND expenses='Travel';");
                            //Toast.makeText(getApplicationContext(),"Deleted successfully"+test, Toast.LENGTH_SHORT).show();
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Travel'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();}
                        else{
                            db1.execSQL("INSERT INTO expensedets VALUES('" + name + "','Travel'," +"'" + test + "');");
                            Toast.makeText(getApplicationContext(),"Inserted successfully"+test, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),"Nothing to insert"+test, Toast.LENGTH_SHORT).show();
                        }
                    }}
                    else
                        break;

        }*/
    }


    public void createradiogroup(){
        rg = new RadioGroup(this);
        rl = (RelativeLayout) findViewById(R.id.relativeLayout);
        rb1 = new RadioButton(this);
        rb2 = new RadioButton(this);
        rb3 = new RadioButton(this);

        rb1.setText("Below 1000");
        rb2.setText("Below 5000");
        rb3.setText("Above 5000 ");
        rg.addView(rb1);
        rg.addView(rb2);
        rg.addView(rb3);
        rg.setOrientation(RadioGroup.HORIZONTAL);


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) RadioGroup.LayoutParams.WRAP_CONTENT,(int) RadioGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin =20;
        params.topMargin = 20;

        rg.setLayoutParams(params);
        rl.addView(rg);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                if (rg.getCheckedRadioButtonId()<= 0) {
                    Toast.makeText(getApplicationContext(), "Choose Radio Button Please", Toast.LENGTH_SHORT).show();
                }
                else{
                test = (String) radioButton.getText();
                rg.clearCheck();}
            }
        });
    }
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.btnSend:
                // Do something
                Toast.makeText(getApplicationContext(),"Saving all changes..", Toast.LENGTH_SHORT).show();
                Intent in=new Intent(this,User_profile.class);
                //in.putExtra("name","Login");
                in.putExtra("name", String.valueOf(name));
                startActivityForResult(in,MYREQUEST2);

        }
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
package com.example.expmanager2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,b3;
    EditText un,em,mno,pwd;
    SQLiteDatabase db;
    private int mYear, mMonth, mDay;
    TextView tx1;
    int c=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx1=(TextView)findViewById(R.id.textview) ;
        b1 = (Button)findViewById(R.id.button1);
        b2= (Button)findViewById(R.id.button2);
        un = (EditText)findViewById(R.id.editText1);
        em = (EditText)findViewById(R.id.editText2);
        //dob = (EditText)findViewById(R.id.editText3);
        pwd= (EditText)findViewById(R.id.editText4);
        mno = (EditText)findViewById(R.id.editText5);
        //dob.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);



        db = openOrCreateDatabase("UsersDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS userdet(uname VARCHAR,pwd VARCHAR,mobileno VARCHAR,emailid VARCHAR);");



    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View view) {
        // Adding a record
        /*
        if (view == dob) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }*/
        if (view == b1) {//register button
            //sendSMSMessage();
            if (un.getText().toString().isEmpty() || em.getText().toString().isEmpty() ||
                    pwd.getText().toString().isEmpty() || pwd.getText().toString().isEmpty()
                    || mno.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(),"Please Fill All the Details", Toast.LENGTH_SHORT).show();
                c=1;
            }
            if(isValidEmail(em.getText())==false){
                Toast.makeText(getApplicationContext(),"Please enter a valid email", Toast.LENGTH_SHORT).show();
                c=1;
            }
            if(isValidMobile(String.valueOf(mno.getText()))==false){
                Toast.makeText(getApplicationContext(), "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                c=1;}
            if(c==0){
                //db.execSQL("DELETE FROM userdet;");
                String sql = "SELECT * FROM userdet WHERE uname='"+un.getText()+"';";
                Cursor cursor = db.rawQuery(sql, null);
                if(cursor.getCount() <= 0){
                    db.execSQL("INSERT INTO userdet VALUES('" + un.getText() + "','" + pwd.getText() + "','" + mno.getText() +
                            "','" + em.getText() + "');");
                    Toast.makeText(getApplicationContext(),"User registration success", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
                    //Get the SmsManager instance and call the sendTextMessage method to send message
                    SmsManager sms=SmsManager.getDefault();


                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.SEND_SMS)
                            != PackageManager.PERMISSION_GRANTED) {
                        // if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        //       Manifest.permission.SEND_SMS)) {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.SEND_SMS},
                                111);
                    } else {
                        // Permission already granted
                        // }
                    }

                    sms.sendTextMessage(String.valueOf(mno), null, "Hey "+un.getText()+"! Welcome to Chits", pi,null);
                    Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                            Toast.LENGTH_LONG).show();
                    cursor.close();

                }
                else{
                    Toast.makeText(getApplicationContext(),"User name  already taken", Toast.LENGTH_SHORT).show();
                    cursor.close();
                }

                }



                /*
                db.execSQL("INSERT INTO userdet VALUES('" + un.getText() + "','" + pwd.getText() + "','" + mno.getText() +
                        "','" + em.getText() + "');");
                Toast.makeText(getApplicationContext(),"User registration success", Toast.LENGTH_SHORT).show();*/
                //Getting intent and PendingIntent instance

            }







        if (view == b2){ //login  button
            Intent i=new Intent(this,login.class);
            i.putExtra("name","Login");
            startActivity(i);
        }


    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

}

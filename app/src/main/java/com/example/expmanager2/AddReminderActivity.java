package com.example.expmanager2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class AddReminderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener,  View.OnClickListener,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {
    Spinner s1;
    EditText title;
    ImageView v1, v2;
    SQLiteDatabase db;

    Button btnDatePicker, btnTimePicker, add, modify, delete;
    EditText txtDate, txtTime, txtAmt;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        s1 = (Spinner) findViewById(R.id.spinner1);
        // Small, Medium, Large
        // size = s1.getSelectedItem().toString();
        //simple_list_item_checked
        //simple_list_item_activated_1
        /*createNotificationChannel();*/
        ArrayAdapter adap1 = ArrayAdapter.createFromResource(this, R.array.list, android.R.layout.simple_list_item_checked);
        s1.setAdapter(adap1);
        s1.setOnItemSelectedListener(this);
        add = (Button) findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        modify = findViewById(R.id.modify);
        title = findViewById(R.id.reminder_title);
        txtAmt = (EditText) findViewById(R.id.edttxtamt);
        btnDatePicker = (Button) findViewById(R.id.btnDate);
        btnTimePicker = (Button) findViewById(R.id.btnTime);
        txtDate = (EditText) findViewById(R.id.edttxtDate);
        txtTime = (EditText) findViewById(R.id.edttxtTime);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        add.setOnClickListener(this);
        modify.setOnClickListener(this);
        delete.setOnClickListener(this);
        db = openOrCreateDatabase("UsersDB", Context.MODE_PRIVATE, null);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.spinner1) {
            Log.d("MainActivity", "item selected");
            TextView txt = (TextView) view;
            //Checked Text view
            Log.d("spinner1", txt.getText().toString());
            name = txt.getText().toString();
            Toast.makeText(getApplicationContext(), "You have selected " + txt.getText(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
    }


    @Override
    public void onClick(View view) {

        if (view == btnDatePicker) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog
                    (this, this, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (view == btnTimePicker) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, mHour, mMinute, true);
            timePickerDialog.show();
        }
        if (view == add) {
            if (txtDate.getText().toString().trim().length() == 0 || txtTime.getText().toString().trim().length() == 0 || txtAmt.getText().toString().trim().length() == 0 ||
                    title.getText().toString().trim().length() == 0) {
                Toast.makeText(getApplicationContext(), "enter all values    ", Toast.LENGTH_SHORT).show();
                return;
            }
            // Inserting record 
            db.execSQL("INSERT INTO remdb VALUES('" + name + "','" + title.getText() + "','" + txtAmt.getText() + "','" + txtDate.getText() +
                    "','" + txtTime.getText() + "');");
            Toast.makeText(getApplicationContext(), " values added    ", Toast.LENGTH_SHORT).show();
            db.execSQL("UPDATE expend SET total= (select sum(amount) from remdb where name= '" + name + "')" + "where catagory = '" + name + "';");


           /* Intent intent = new Intent(MainActivity.this, BroadcastNotify.class);
            PendingIntent pendingIntent =
                    PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            long currentTime = System.currentTimeMillis();
            //1000 milli= 1 sec
            long tenSeconds = 10 * 1000;

            alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    currentTime + tenSeconds,
                    pendingIntent); */

            clearText();
        }
        if (view == modify) {
            int found = 0;
            if (title.getText().toString().trim().length() == 0) {
                Toast.makeText(getApplicationContext(), "enter reminder name for which contents are to be modified    ", Toast.LENGTH_SHORT).show();
                return;
            }

            if (txtDate.getText().toString().trim().length() == 0 && txtTime.getText().toString().trim().length() == 0 && txtAmt.getText().toString().trim().length() == 0) {
                Toast.makeText(getApplicationContext(), "enter values of either day or time or amount to be modified    ", Toast.LENGTH_SHORT).show();
                return;
            }
            // Inserting record 

            Cursor c = db.rawQuery("SELECT * FROM remdb WHERE descp='" + title.getText() + "' and name='" + name + "'", null);
            // Checking if no records found 
            if (c.getCount() == 0) {
                Toast.makeText(getApplicationContext(), "INVALID ENTRY!!! \n " + "CHECK NAME AND CATAGORY", Toast.LENGTH_SHORT).show();
                return;
            }

            if ((txtAmt.getText().toString().trim().length() != 0)) {
                db.execSQL("UPDATE remdb SET amount='" + txtAmt.getText() + "' WHERE descp='" + title.getText() + "' and name='" + name + "'");
                db.execSQL("UPDATE expend SET total= (select sum(amount) from remdb where name= '" + name + "')" + "where catagory = '" + name + "'");

            }
            if ((txtDate.getText().toString().trim().length() != 0)) {
                db.execSQL("UPDATE remdb SET date='" + txtDate.getText() + "' WHERE descp='" + title.getText() + "' and name='" + name + "'");
            }
            if ((txtTime.getText().toString().trim().length() != 0)) {
                db.execSQL("UPDATE remdb SET time='" + txtTime.getText() + "' WHERE descp='" + title.getText() + "' and name='" + name + "'");
            }

            clearText();
        }
        if (view == delete) {
            if (title.getText().toString().trim().length() == 0) {
                Toast.makeText(getApplicationContext(), "enter reminder name for which contents are to be modified    ", Toast.LENGTH_SHORT).show();
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM remdb WHERE descp='" + title.getText() + "' and name='" + name + "'", null);
            // Checking if no records found 
            if (c.getCount() == 0) {
                Toast.makeText(getApplicationContext(), "INVALID ENTRY!!! \n " + "CHECK NAME AND CATAGORY", Toast.LENGTH_SHORT).show();
                return;
            }

            db.execSQL("DELETE FROM remdb WHERE descp='" + title.getText() + "' and name='" + name + "'");
            db.execSQL("UPDATE expend SET total= (select sum(amount) from remdb where name= '" + name + "')" + "where catagory = '" + name + "'");

            clearText();
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int dayOfMonth, int monthOfYear, int year) {
        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
        Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        /*String s = yy + " -"+ mm + "-"+dd;
       Toast.makeText(getApplicationContext(),s , Toast.LENGTH_SHORT).show();*/
        if ((dayOfMonth < yy) || (dayOfMonth == yy && monthOfYear < mm) || (dayOfMonth == yy && monthOfYear == mm && year < dd)) {
            Toast.makeText(getApplicationContext(), "INVALID ENTRY!!! \n " + "ENTER VALID DATE \n", Toast.LENGTH_SHORT).show();
            txtDate.setText("");
        }

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        txtTime.setText(hourOfDay + ":" + minute);

    }

    public void clearText() {
        title.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtAmt.setText("");
    }
            /* private void createNotificationChannel() {
                 // Create the NotificationChannel, but only on API 26+ because
                 // the NotificationChannel class is new and not in the support library
                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                     CharSequence name = "espense tracker reminder Channel";
                     String description = "You have a reminder";
                     int importance = NotificationManager.IMPORTANCE_DEFAULT;
                     NotificationChannel channel = new NotificationChannel("lemubitNotify", name, importance);
                     channel.setDescription(description);
                     // Register the channel with the system; you can't change the importance
                     // or other notification behaviors after this
                     NotificationManager notificationManager = getSystemService(NotificationManager.class);
                     notificationManager.createNotificationChannel(channel);
                 }
                 */

}

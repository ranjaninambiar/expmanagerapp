package com.example.expmanager2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class catagorylist extends AppCompatActivity implements AdapterView.OnItemClickListener  {
    ListView lv,listView;
    SQLiteDatabase db;
    private Integer imageid[] = {
            R.drawable.electricity,
            R.drawable.dth,
            R.drawable.telephone,
            R.drawable.grocery,
            R.drawable.other,
            R.drawable.onlineshopping,
            R.drawable.alert,
            R.drawable.gym,
            R.drawable.food,
            R.drawable.insurance,
            R.drawable.loan1,
            R.drawable.medicine,
            R.drawable.retail,
            R.drawable.service,
            R.drawable.travel

    };
    String[] clist = {"ELECTRICITY", "DTH/CABLE", "TELEPHONE", "GROCERY", "OTHERS", "ONLINE SHOPPING","ALERT","GYM","FOOD","INSURANCE","LOAN","MEDICINE","RETAIL","SERVICE","TRAVEL"};
    String[] csublist = {"monthly deadlines and transaction ", " monthly deadline and transaction", "monthly deadline and transaction"," monthly deadline for groceries" ,"for other purposes","online order delivery payment" ,"expense exceeds budget" ,
            " "," "," "," "," "," "," "," "};

    FloatingActionButton mAddReminderButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagorylist);
        // Setting header

        ListView listView=(ListView)findViewById(R.id.list);
        // For populating list data
        CustomCountryList customCountryList = new CustomCountryList(this, clist, csublist, imageid);
        listView.setAdapter(customCountryList);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) this);
       /* lv=findViewById(R.id.list);
        List<Map<String, String>> messages = new ArrayList<>();
        HashMap<String,String> content ;
        for(int i = 0 ; i < clist.length; i++) {
            content = new HashMap<String, String>();
            content.put("catagory", clist[i]);
            content.put("description", csublist[i]);
            messages.add(content);
        }
        String[] entry = new String[] {"catagory", "description"};
        SimpleAdapter adapter = new SimpleAdapter(this, messages,
                android.R.layout.simple_list_item_2,
                entry,
                new int[] {android.R.id.text1,
                        android.R.id.text2,
                });
        lv.setAdapter(adapter);

        lv.setOnItemClickListener((AdapterView.OnItemClickListener) this);*/

        FloatingActionButton mAddReminderButton = (FloatingActionButton) findViewById(R.id.fab);

        mAddReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddReminderActivity.class);
                startActivity(intent);
            }
        });
        db = openOrCreateDatabase("UsersDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS expend(catagory VARCHAR,total int(10));");
        db.execSQL("CREATE TABLE IF NOT EXISTS remdb(name VARCHAR,descp VARCHAR,amount int(10),date VARCHAR,time VARCHAR );");

    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        StringBuilder sb = new StringBuilder("");
        //By position in array
        sb.append(clist[i]+" : ");
        sb.append(csublist[i]+"\n");
        Toast.makeText(getApplicationContext(), sb.toString(),
                Toast.LENGTH_SHORT).show();

      /* AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        // Setting Dialog Title
        alertDialog.setTitle("REMINDER DETAILS OF  :" + countries[i]);
        // Setting Dialog Message
        alertDialog.setMessage("");
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.ic_launcher_background);
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You clicked on YES :   "+which, Toast.LENGTH_SHORT).show();
            }
        });
        // Showing Alert Message
       alertDialog.show(); */
        Cursor c = db.rawQuery("SELECT * FROM remdb WHERE name='" + clist[i] + "'", null);
        // Checking if no records found 
        if (c.getCount() == 0) {
            showMessage( clist[i] , "No deadlines for "+ clist[i], 0 );
            return;
        }
        // Appending records to a string buffer 
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext())
        {   int valid_entry=1;
            /*String dobComp[] = c.getString(3).split("/");
            Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH) ;
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
             int valid_entry=1;
            int dayOfMonth = Integer.parseInt(dobComp[2]);
                int monthOfYear = Integer.parseInt(dobComp[1]);
                int year = Integer.parseInt(dobComp[0]);
                if(year< yy || (year==yy && monthOfYear <mm) || (year==yy && monthOfYear ==mm &&  dayOfMonth<dd)){
                      valid_entry=0;
                    }*/
            if(valid_entry==1) {
                buffer.append("description: " + c.getString(1) + "\n");
                buffer.append("amount: " + c.getString(2) + "\n");
                buffer.append("date: " + c.getString(3) + "\n");
                buffer.append("time: " + c.getString(4) + "\n\n");
            }
        }
        // Displaying all records 
        showMessage("ALL DEADLINES", buffer.toString(),1);

    }

    public void showMessage(String title, String message, int i){
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.jobdone1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.MyDialogTheme);
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setCancelable(true);
        builder.setTitle(title);
        if(i==0)
        {builder.setView(image);}
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        builder.show();

    }
}
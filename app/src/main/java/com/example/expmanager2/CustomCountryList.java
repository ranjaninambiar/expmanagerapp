package com.example.expmanager2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomCountryList extends ArrayAdapter {
    private String[] clist;
    private String[] csublist;
    private Integer[] imageid;
    private Activity context;

    public CustomCountryList(Activity context, String[] clist, String[] csublist, Integer[] imageid) {
        super(context, R.layout.row_item, clist);
        this.context = context;
        this.clist = clist;
        this.csublist = csublist;
        this.imageid = imageid;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.row_item, null, true);
        TextView textViewCountry = (TextView) row.findViewById(R.id.textViewClist);
        TextView textViewCapital = (TextView) row.findViewById(R.id.textViewCsublist);
        ImageView imageFlag = (ImageView) row.findViewById(R.id.imageView);

        textViewCountry.setText(clist[position]);
        textViewCapital.setText(csublist[position]);
        imageFlag.setImageResource(imageid[position]);
        return  row;
    }
}

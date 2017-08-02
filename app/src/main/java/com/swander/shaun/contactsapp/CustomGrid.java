package com.swander.shaun.contactsapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class CustomGrid extends BaseAdapter{
    private Context mContext;

    private List<Contact> contacts;

    public CustomGrid(Context c ) {
        mContext = c;
        contacts = MainActivity.contacts;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_cell, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            Log.d("", "getView: " + position);
            textView.setText(contacts.get(position).name);
            //imageView.setImageResource(contacts[position].imageNum);

            grid.setBackgroundColor(MainActivity.contacts.get(position).backgroundColor);

        if(MainActivity.contacts.size() != 0)
        {
            /*
            no friends? i'm lonely'
            'no friends? that's ok, i have crippling social anxiety anyway'
            no friends:( aw'
            'no friends:( sad 4 u'
             */
            MainActivity.noContactsText.setVisibility(View.GONE);
        }
        else
        {
            MainActivity.noContactsText.setVisibility(View.VISIBLE);
        }



        return grid;
    }

}
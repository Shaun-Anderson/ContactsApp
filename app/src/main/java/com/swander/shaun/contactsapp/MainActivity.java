package com.swander.shaun.contactsapp;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends Activity  {
    static GridView grid;
    static TextView noContactsText;
    static List<Contact> _contacts = new ArrayList<Contact>();
    static List<Contact> contacts = new ArrayList<Contact>();
    static DatabaseReader myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseReader(this);

        noContactsText = findViewById(R.id.noContactsText);
        CustomGrid adapter = new CustomGrid(MainActivity.this);
        adapter.notifyDataSetChanged();
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                intent.putExtra("Contact", position);
                startActivity(intent);
            }


        });
        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {
                Toast.makeText(MainActivity.this, "Removed " + contacts.get(position).name, Toast.LENGTH_SHORT).show();
                myDB.DB_DeleteEntry(contacts.get(position).name);
                GetData(getBaseContext());
                Log.d("DB", contacts.toString());
                grid.invalidateViews();
                if(MainActivity.contacts.size() != 0)
                {
                    MainActivity.noContactsText.setVisibility(View.GONE);
                }
                else
                {
                    MainActivity.noContactsText.setVisibility(View.VISIBLE);
                }
                //set the image as wallpaper
                return true;
            }
        });

        ImageView addImage = (ImageView) findViewById(R.id.addImage);
        addImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //v.getId() will give you the image id
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                // String message = editText.getText().toString();
                startActivity(intent);
            }
        });

        final ImageView sortImage = (ImageView) findViewById(R.id.FilterButton);
        sortImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showPopup(sortImage);
            }
        });

        GetData(this);


    }

    public static void GetData(Context context)
    {
        contacts.clear();
            Cursor res = myDB.DB_ReadData();
            if(res.getCount() == 0)
            {
                //NO DATA
                return;
            }

            while(res.moveToNext())
            {
                Log.d("DB", "GetData: " + res.getString(0) + res.getString(1) + res.getString(2) + res.getString(3) + res.getString(4) +res.getString(5));
                contacts.add(new Contact(context,res.getInt(0), res.getString(1), res.getString(2), res.getString(3), res.getString(4),res.getString(5)));
            }

        Sort_Alphabetical();


        grid.invalidateViews();

    }

    public static void GetDataTag(Context context,String tag)
    {
        contacts.clear();
        Cursor res = myDB.DB_ReadData_Tag(tag);
        if(res.getCount() == 0)
        {
            //NO DATA
            return;
        }

        while(res.moveToNext())
        {
            Log.d("DB", "GetData: " + res.getString(0) + res.getString(1) + res.getString(2) + res.getString(3) + res.getString(4) +res.getString(5));
            contacts.add(new Contact(context,res.getInt(0), res.getString(1), res.getString(2), res.getString(3), res.getString(4),res.getString(5)));
        }

        Sort_Alphabetical();

        grid.invalidateViews();

    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v, Gravity.BOTTOM);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.sort_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                SortContacts(item.getTitle().toString());
                return true;
            }
        });

        popup.show();
    }

    public void SortContacts(String type)
    {
        Log.d("SORT", "SortContacts: " + type);

        switch (type)
        {
            case "FRIEND":
                GetDataTag(this, "Friend");
                break;
            case "FAMILY":
                GetDataTag(this, "Family");
                break;
            case "COWORKER":
                GetDataTag(this, "CoWorker");
                break;
            case "ALL":
                GetData(this);
                break;
        }


    }

    static void Sort_Alphabetical()
    {
        Log.d("SORT", "ALPHA");

        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact item, Contact t1) {
                String s1 = item.getName();
                String s2 = t1.getName();
                return s1.compareToIgnoreCase(s2);
            }

        });

    }
}



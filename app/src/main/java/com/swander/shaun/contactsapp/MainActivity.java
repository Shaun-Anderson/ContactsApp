package com.swander.shaun.contactsapp;

import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends Activity  {
    static GridView grid;
    static TextView noContactsText;
    static List<Contact> contacts = new ArrayList<Contact>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts.add(new Contact(this));

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
                contacts.remove(position);

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
    }
}

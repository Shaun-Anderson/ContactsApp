package com.swander.shaun.contactsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

/**
 * Created by Shaun on 8/2/2017.
 */

public class AddActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);

        Intent intent = getIntent();


        ImageView addImage = (ImageView) findViewById(R.id.exitButton);
        addImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setText("ADD");
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddContact();
                finish();
            }
        });

    }

    public void AddContact()
    {
        EditText contactName = (EditText) findViewById(R.id.inputName);
        EditText contactNumber = (EditText) findViewById(R.id.inputNumber);
        EditText contactEmail = (EditText) findViewById(R.id.inputEmail);
//add LOCATION
      //  EditText contactAddress = (EditText) findViewById(R.id.inputEmail);
        MainActivity.contacts.add(new Contact(this));
        MainActivity.grid.invalidateViews();

        //MainActivity.contacts.add(new Contact(0,contactName.getText().toString(),contactNumber.getText().toString(),contactEmail.getText().toString()));
        Log.d("BALH", "AddContact: " + MainActivity.contacts.size());
        //EditText contactName = (EditText) findViewById(R.id.inputName);
    }
}

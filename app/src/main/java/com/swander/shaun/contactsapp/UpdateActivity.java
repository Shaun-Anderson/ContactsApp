package com.swander.shaun.contactsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Shaun on 8/2/2017.
 */

public class UpdateActivity extends Activity {

    int contactNum;

    EditText contactName;
    EditText contactNumber;
    EditText contactEmail;
    EditText contactAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);

        Intent intent = getIntent();
        Log.d("BALH", "onCreate: " + intent.getIntExtra("Contact", 0));
        contactNum = intent.getIntExtra("Contact", 0);

        ImageView addImage = (ImageView) findViewById(R.id.exitButton);
        addImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setText("UPDATE");
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UpdateContact();
                finish();
            }
        });

        contactName = (EditText) findViewById(R.id.inputName);
        contactNumber = (EditText) findViewById(R.id.inputNumber);
        contactEmail = (EditText) findViewById(R.id.inputEmail);
        contactAddress = (EditText) findViewById(R.id.inputAddress);

        contactName.setText(MainActivity.contacts.get(contactNum).name);
        contactNumber.setText(MainActivity.contacts.get(contactNum).number);
        contactEmail.setText(MainActivity.contacts.get(contactNum).email);
        contactAddress.setText(MainActivity.contacts.get(contactNum).address);



    }

    public void UpdateContact()
    {
        Contact updatedContact = new Contact(this, 0, contactName.getText().toString(), contactNumber.getText().toString(), contactEmail.getText().toString(), contactAddress.getText().toString(), "Friend");
        MainActivity.contacts.set(contactNum, updatedContact);
    }

}

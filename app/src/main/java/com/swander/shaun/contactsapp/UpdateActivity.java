package com.swander.shaun.contactsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Shaun on 8/2/2017.
 */

public class UpdateActivity extends Activity {

    int contactNum;
    String orginalName;

    EditText contactName;
    EditText contactNumber;
    EditText contactEmail;
    EditText contactAddress;

    String tag = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);

        Intent intent = getIntent();
        contactNum = intent.getIntExtra("Contact", 0);

        Log.d("BALH", "onCreate: " + intent.getIntExtra("Contact", 0));

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

        tag = MainActivity.contacts.get(contactNum).tag;

        final Button tagButton = (Button) findViewById(R.id.tagButton);
        tagButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showPopup(tagButton);
            }
        });
        tagButton.setText(tag);

        orginalName = contactName.getText().toString();

    }

    public void UpdateContact()
    {
        String name = ((EditText) findViewById(R.id.inputName)).getText().toString();
        String number = ((EditText) findViewById(R.id.inputNumber)).getText().toString();
        String email = ((EditText) findViewById(R.id.inputEmail)).getText().toString();
        String address = ((EditText) findViewById(R.id.inputAddress)).getText().toString();

        //add LOCATION
        //  EditText contactAddress = (EditText) findViewById(R.id.inputEmail);

        //MainActivity.contacts.add(new Contact(0,contactName.getText().toString(),contactNumber.getText().toString(),contactEmail.getText().toString()));
        //EditText contactName = (EditText) findViewById(R.id.inputName);

        DatabaseReader myDB = new DatabaseReader(this);
        myDB.DB_UpdateData(orginalName, name, number, email, address, tag);
        MainActivity.GetData(this);
        MainActivity.grid.invalidateViews();
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v, Gravity.BOTTOM);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.tags_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(UpdateActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                tag = item.getTitle().toString();
                Button p1_button = (Button)findViewById(R.id.tagButton);
                p1_button.setText(item.getTitle());
                return true;
            }
        });

        popup.show();
    }

}

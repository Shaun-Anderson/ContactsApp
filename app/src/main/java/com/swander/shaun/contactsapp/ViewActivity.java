package com.swander.shaun.contactsapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class ViewActivity extends Activity {

    int contactNum;

    @Override
    protected void onResume() {
        super.onResume();

        TextView textView = (TextView) findViewById(R.id.contactName);
        textView.setText(MainActivity.contacts.get(contactNum).name);

        TextView numberText = (TextView) findViewById(R.id.contactNumber);
        numberText.setText(MainActivity.contacts.get(contactNum).number);

        TextView emailText = (TextView) findViewById(R.id.contactEmail);
        emailText.setText(MainActivity.contacts.get(contactNum).email);

        TextView addressNumber = (TextView) findViewById(R.id.contactAddress);
        addressNumber.setText(MainActivity.contacts.get(contactNum).address);

        MainActivity.grid.invalidateViews();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_layout);

        Intent intent = getIntent();
        contactNum = intent.getIntExtra("Contact", 0);

        TextView textView = (TextView) findViewById(R.id.contactName);
        textView.setText(MainActivity.contacts.get(intent.getIntExtra("Contact", 0)).name);

        TextView tagText = (TextView) findViewById(R.id.contactTag);
        tagText.setText(MainActivity.contacts.get(intent.getIntExtra("Contact", 0)).tag);

        TextView numberText = (TextView) findViewById(R.id.contactNumber);
        numberText.setText(MainActivity.contacts.get(intent.getIntExtra("Contact", 0)).number);

        TextView emailText = (TextView) findViewById(R.id.contactEmail);
        emailText.setText(MainActivity.contacts.get(intent.getIntExtra("Contact", 0)).email);

        TextView addressNumber = (TextView) findViewById(R.id.contactAddress);
        addressNumber.setText(MainActivity.contacts.get(intent.getIntExtra("Contact", 0)).address);

        ImageView addImage = (ImageView) findViewById(R.id.exitButton);
        addImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        ImageView updateButton = (ImageView) findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //v.getId() will give you the image id
                Intent newIntent = new Intent(getApplicationContext(), UpdateActivity.class);
                newIntent.putExtra("Contact", contactNum);
                startActivity(newIntent);
            }
        });
    }
}
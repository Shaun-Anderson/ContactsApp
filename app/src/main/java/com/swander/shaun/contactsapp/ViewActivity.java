package com.swander.shaun.contactsapp;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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

        TextView tagText = (TextView) findViewById(R.id.contactTag);
        tagText.setText(MainActivity.contacts.get(contactNum).tag);

        MainActivity.grid.invalidateViews();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_layout);

        Intent intent = getIntent();
        contactNum = intent.getIntExtra("Contact", 0);

        Log.d("BALH", "onCreate: " + intent.getIntExtra("Contact", 0));


        TextView textView = (TextView) findViewById(R.id.contactName);
        textView.setText(MainActivity.contacts.get(intent.getIntExtra("Contact", 0)).name);

        TextView tagText = (TextView) findViewById(R.id.contactTag);
        tagText.setText(MainActivity.contacts.get(intent.getIntExtra("Contact", 0)).tag);

        final TextView numberText = (TextView) findViewById(R.id.contactNumber);
        numberText.setText(MainActivity.contacts.get(intent.getIntExtra("Contact", 0)).number);

        final TextView emailText = (TextView) findViewById(R.id.contactEmail);
        emailText.setText(MainActivity.contacts.get(intent.getIntExtra("Contact", 0)).email);

        final TextView addressNumber = (TextView) findViewById(R.id.contactAddress);
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

        //Add events on click.
        ImageView callImage = (ImageView) findViewById(R.id.action_call);
        callImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + numberText.getText()));
                startActivity(i);
            }
        });

        ImageView gpsImage = (ImageView) findViewById(R.id.action_gps);
        gpsImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + addressNumber.getText()));
                startActivity(i);
            }
        });

        ImageView emailImage = (ImageView) findViewById(R.id.action_email);
        emailImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + emailText.getText() +"?subject=" + "&body="));
                startActivity(i);
            }
        });



    }
}
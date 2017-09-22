package com.swander.shaun.contactsapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import org.w3c.dom.Text;

/**
 * Created by Shaun on 8/2/2017.
 */

public class AddActivity extends Activity {

    String tag = "Friend";

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
            }
        });

        final Button tagButton = (Button) findViewById(R.id.tagButton);
        tagButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showPopup(tagButton);
            }
        });

    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.tags_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(AddActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                tag = item.getTitle().toString();
                Button p1_button = (Button)findViewById(R.id.tagButton);
                p1_button.setText(item.getTitle());
                return true;
            }
        });

        popup.show();
    }

    //Gets the data for each of the
    public void AddContact()
    {
        String name = ((EditText) findViewById(R.id.inputName)).getText().toString();
        String number = ((EditText) findViewById(R.id.inputNumber)).getText().toString();
        String email = ((EditText) findViewById(R.id.inputEmail)).getText().toString();
        String address = ((EditText) findViewById(R.id.inputAddress)).getText().toString();

        //Validation checks for number and email
        if(name == "")
        {
            Toast.makeText(AddActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
        }
        if(!isValidEmail(email))
        {
            Toast.makeText(AddActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!isValidPhone(number))
        {
            Toast.makeText(AddActivity.this, "Invalid Number", Toast.LENGTH_SHORT).show();
            return;
        }


        DatabaseReader myDB = new DatabaseReader(this);
        myDB.DB_AddData(name, number, email, address, tag);
        MainActivity.GetData(this);
        MainActivity.grid.invalidateViews();

        finish();

    }


    //Validation Checks

    //Taken from: https://stackoverflow.com/questions/12947620/email-address-validation-in-android-on-edittext
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    //End of taken section

    public final static boolean isValidPhone(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();

        }
    }
}

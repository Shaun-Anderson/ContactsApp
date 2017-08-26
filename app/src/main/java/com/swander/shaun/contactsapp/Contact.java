package com.swander.shaun.contactsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * Created by Shaun on 7/31/2017.
 */

public class Contact {

    // variable to hold context
    private Context context;


    int imageNum;
    String name;
    String number;
    String email;
    String address;
    int backgroundColor;
    String tag;

    public Contact(Context context, int imageNum, String name, String number, String email, String address, String tag) {
        this.context = context;
        this.imageNum = imageNum;
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
        this.tag = tag;
        this.backgroundColor = setColor();

    }
    public Contact(Context context) {
        this.context = context;
        this.imageNum = 0;
        this.name = "Example";
        this.number = "1234567890";
        this.email = "example@outlook.com";
        this.address = "123 Fake Street";
        this.tag = "Friend";
        this.backgroundColor = setColor();

    }

    public int setColor()
    {
        int[] rainbow = context.getResources().getIntArray(R.array.gridCellColors);


        int min = 0;
        int max = rainbow.length;

        Random r = new Random();
        int i1 = r.nextInt(max - min) ;
        Log.d("DICEROLL", "setColor: " + i1);

        int color = rainbow[i1];
        return color;
    }
}

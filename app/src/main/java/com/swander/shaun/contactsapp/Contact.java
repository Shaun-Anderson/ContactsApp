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

    int id;
    int imageNum;
    String name;
    String number;
    String email;
    String address;
    int backgroundColor;
    String tag;

    public Contact(Context context, int id, String name, String number, String email, String address, String tag) {
        this.context = context;
        this.id = id;
        this.imageNum = 0;
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
        this.tag = tag;
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

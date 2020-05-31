package com.example.wordtool;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayPhrase extends AppCompatActivity {          //Class for displying the user entered words
    WordDatabaseHelper myDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_phrase);

        ListView listView = (ListView)findViewById(R.id.DisplayListView);   //Creating a list View and defining the ID of listView in XML
        myDatabase = new WordDatabaseHelper(this);

        ArrayList<String> phrasesList = new ArrayList<>();      //Creating an arraylist to get the words from database and store it.
        Cursor phrasesData = myDatabase.viewTableData();        //retriving  words from the database

        if(phrasesData.getCount() == 0 ){       //Validation of data
            Toast.makeText(DisplayPhrase.this,"List is Empty",Toast.LENGTH_SHORT).show();
        }
        else{                                   //if everything ok, add the table column 1 data to the arraylist
            while(phrasesData.moveToNext()){
                phrasesList.add(phrasesData.getString(1));      //display the words into the List View
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,phrasesList);
                listView.setAdapter(listAdapter);
            }
        }




    }
}

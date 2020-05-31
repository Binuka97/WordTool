package com.example.wordtool;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LanguageSubscription extends AppCompatActivity {       //class for subscribing to the languages
    WordDatabaseHelper langDb;

    private static final String TAG = "LanguageDisplayActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_subscription);

        ListView listView = (ListView)findViewById(R.id.listViewLanguages); //creating a listview
        langDb = new WordDatabaseHelper(this);

        ArrayList<String> theList = new ArrayList<>();  //creating an arraylist to get the data from the datbase
        Cursor data = langDb.ViewLanguageData();    //retireing DB data using cursor object

        if(data.getCount() == 0 ){                //validation of the data of the Database retrieve
            Toast.makeText(LanguageSubscription.this,"put data to the table",Toast.LENGTH_SHORT).show();
        }
        else{                               //if data retrieve is ok, get the languages from the databse to array and display in
            while(data.moveToNext()){                           //the list view
                theList.add(data.getString(0));                     //using multiple checkers for subcription of the languages
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                ListAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.checker_layout, R.id.checked_text, theList);
                listView.setAdapter(listAdapter);
            }
        }



    }

}

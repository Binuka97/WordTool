package com.example.wordtool;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Translate extends AppCompatActivity {      //class for the translation of data
    WordDatabaseHelper myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        ListView listView = (ListView)findViewById(R.id.DisplayListView); //creating the listview
        myDatabase = new WordDatabaseHelper(this);

        ArrayList<String> phrasesList = new ArrayList<>(); //creating an arraylist to get the data from the database
        Cursor phrasesData = myDatabase.viewTableData();        //retrieve DB values using cursor object

        if(phrasesData.getCount() == 0 ){       //validation of data retrieve
            Toast.makeText(Translate.this,"List is Empty",Toast.LENGTH_SHORT).show();
        }
        else{
            while(phrasesData.moveToNext()){        //if it's ok, then display data to the list view
                phrasesList.add(phrasesData.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,phrasesList);
                listView.setAdapter(listAdapter);
            }
        }
    }
}

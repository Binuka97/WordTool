package com.example.wordtool;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddPhrases extends AppCompatActivity {     //class for the adding words/phrases and saving to the Database
    WordDatabaseHelper wordsDb;             //creating a new instance of the Database helper class
    EditText addWord;               //declaring buttons and edit texts
    Button btnSaveWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phrases);
        wordsDb = new WordDatabaseHelper(this);



        addWord = (EditText)findViewById(R.id.editText_addWord);    //defining the ids of the buttons
        btnSaveWord = (Button)findViewById(R.id.btn_save);
        saveWords();
        wordsDb = new WordDatabaseHelper(this);

    }

    public void saveWords() {           //method for saving buttons
        btnSaveWord.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {          //checking whether the data is inserted , then get the String of it
                        boolean isDataInserted = wordsDb.insertTableData(addWord.getText().toString());

                        if(isDataInserted == true)      //toast messages for the result of the word insertion
                            Toast.makeText(AddPhrases.this,"Data inserted sucessfully!",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddPhrases.this,"Data insertion Failed",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


}

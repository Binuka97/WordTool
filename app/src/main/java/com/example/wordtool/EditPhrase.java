package com.example.wordtool;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditPhrase extends AppCompatActivity {            //class for editing the words entered by the user
    private static final String TAG = "WordToolActivity";
    WordDatabaseHelper dbhelper;
    private int selectedPosition = -1;

    EditText editWord;     //defining the buttons textviews
    Button editBtn;
    Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phrase);

        editWord = (EditText)findViewById(R.id.editTextWord);   //defining the IDs of the buttons and textViews
        editBtn = (Button)findViewById(R.id.btn_Edit);
        saveBtn = (Button)findViewById(R.id.btn_saveWord);

        final ArrayList<String> wordsList = new ArrayList<>();      //creating an array to get the words from Database

        dbhelper = new WordDatabaseHelper(getApplicationContext());

        Cursor cursor = dbhelper.viewTableData();    //creating a cursor object

        if (cursor.getCount() == 0)             //Validation of data
            Toast.makeText(EditPhrase.this, "The database is empty! ", Toast.LENGTH_SHORT).show();
        else                                    //if data is ok, retrieve words to the arrylist from DB
            while (cursor.moveToNext())
                wordsList.add(cursor.getString(1));         //getting column 1 data from the database
        final ListView listView = (ListView) findViewById(R.id.listViewWords);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);            //Setting single choice checker for select one Word to edit
        final ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.checker_layout, R.id.checked_text, wordsList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,long i) {
                final String word = adapterView.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemClick: You clicked on " + word);

                Cursor wordData = dbhelper.getWordId(word);         //get the id of the word/Phrase
                int wordId = -1;
                while(wordData.moveToNext()){
                    wordId = wordData.getInt(0);    //getting the word ID from the ID column of the database
                }
                if(wordId > -1){        //using LOG cat for debugging purposes
                    Log.d(TAG,"onItemClick: The wordId is: "+ wordId);


                    final int finalWordId = wordId;

                    editBtn.setOnClickListener(new View.OnClickListener() {     //setting the edit word, to edit the word
                        @Override
                        public void onClick(View v) {
                            editWord.setText(word);

                        }
                    });

                    saveBtn.setOnClickListener(new View.OnClickListener() {        //saving the edited text
                        @Override
                        public void onClick(View v) {
                            String OneWordItem = editWord.getText().toString();
                            if(!OneWordItem.equals("")){
                                dbhelper.updateTableData(OneWordItem, finalWordId,word);    //update the database by clicking the save button
                                toastMessage("Successfully Updated the Word!");
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);
                            }else{
                                toastMessage("You must enter a name");            //setting toasts for Validation
                            }

                        }
                    });
                }
                else{
                    toastMessage("NO ID found with the Word");

                }


            }
        });




    }






    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}


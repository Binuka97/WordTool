package com.example.wordtool;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class WordDatabaseHelper extends SQLiteOpenHelper {      //Main Class for handling All the SQLite values and manage them

    public static final String Db_name = "wordToolDb.db";           //creating the main Database (initilizing variables)
    public static final String Table_name = "wordsManager_table";      //creating the words table (initilizing variables)
    public static final String column_1 =  "ID";                        //setting up the columns of it
    public static final String column_2 =  "words";

    public static final String TableName_language = "languages_table";  //initializing the second table
    public static final String langTable_col_1 = "language_Name";           //setting up columns of the table
    public static final String langTable_col_2 = "language_Code";
    public static final String langTable_col_3 = "isSubscribed";

    private static final String TAG = "WordToolDatabaseHelper";

    public WordDatabaseHelper(@Nullable Context context) {
        super(context, Db_name, null, 1);
      //  SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {       // DB query for creating the tables
        db.execSQL("create table " + Table_name +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, words TEXT)");
        db.execSQL("create table " + TableName_language + "(language_Name TEXT, language_Code TEXT, isSubscribed INTEGER DEFAULT 0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {      //onUpgrade method
        db.execSQL("DROP TABLE  IF EXISTS " + Table_name );
        db.execSQL("DROP TABLE  IF EXISTS " + TableName_language );
        onCreate(db);

    }
    public boolean insertTableData(String words) {      //method for inserting words table data
        SQLiteDatabase db = this.getWritableDatabase();     //using the writableDatabase
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_2, words);                 //using content values, adding wrods for the column 2
        long result = db.insert(Table_name,null,contentValues);
        if( (result) == -1)
            return false;
        else
            return true;
    }

    public boolean insertLanguageData(String language_Name){        //method for inserting the languges to the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(langTable_col_1, language_Name);
        //  contentValues.put(langTable_col_2, language_Code);
        // db.execSQL(query);
        long result = db.insert(TableName_language,null,contentValues);
        if( (result) == -1)
            return false;
        else
            return true;

    }

    public Cursor viewTableData(){      //method for viewing the words table data, for disply words and edit words classes.
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Table_name + " ORDER BY "+ column_2+" ASC",null);
        return cursor;                  //using a readabledatabase  and the SQL query getting all the data of the Table
    }

    public Cursor ViewLanguageData(){               //method for viewing the langugage data
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TableName_language ,null); //SQL query for getting all the data
        return cursor;
    }

    public void updateTableData(String newWord, int id, String oldWord) {   //updating the table data for Edit words class.
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + Table_name + " SET " + column_2 +            //when the updated text came, using the SQL query to update
                " = '" + newWord + "' WHERE " + column_1 + " = '" + id + "'" +      //it to the database
                " AND " + column_2 + " = '" + oldWord + "'";
        Log.d(TAG, "updateName: query: " + query);              //adding log tags
        Log.d(TAG, "updateName: Setting name to " + newWord);
        db.execSQL(query);
    }

    public Cursor getWordId(String word){                   //getting wordID of the table to get the iD to edit the words
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " +column_1 + " FROM " + Table_name + " WHERE " + column_2 + " = '" + word + "'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }


}

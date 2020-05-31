package com.example.wordtool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {       //main activity of handling the other activities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchAddPhrasesActivity(View view) {                          //in here, launching different activities based on use clicks
        Intent intent = new Intent(this, AddPhrases.class);
        startActivity(intent);

    }

    public void launchDisplayPhrasesActivity(View view) {
        Intent intent = new Intent(this, DisplayPhrase.class);
        startActivity(intent);
    }

    public void launchEditPhraseActivity(View view) {
        Intent intent = new Intent(this, EditPhrase.class);
        startActivity(intent);
    }

    public void launchLanguageSubActivity(View view) {
        Intent intent = new Intent(this, LanguageSubscription.class);
        startActivity(intent);
    }

    public void launchTranslateACtivity(View view) {
        Intent intent = new Intent(this, Translate.class);
        startActivity(intent);

    }
}

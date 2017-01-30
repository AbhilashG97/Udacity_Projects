package com.example.abhilashg.learnmiwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class familyNames extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_names);

        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("Father" , "әpә", R.drawable.family_father));
        words.add(new Word("Mother" , "әṭa", R.drawable.family_mother));
        words.add(new Word("Son" , "angsi", R.drawable.family_son));
        words.add(new Word("Daughter" , "tune", R.drawable.family_daughter));
        words.add(new Word("Older Brother" , "taachi", R.drawable.family_older_brother));
        words.add(new Word("Younger Brother" , "chalitti", R.drawable.family_younger_brother));
        words.add(new Word("Older Sister" , "teṭe", R.drawable.family_older_sister));
        words.add(new Word("Younger Sister" , "Kolliti", R.drawable.family_younger_sister));
        words.add(new Word("Grandmother" , "Ama", R.drawable.family_grandmother));
        words.add(new Word("Grandfather" , "Paapa", R.drawable.family_grandfather));


        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.Family);

        ListView listView = (ListView) findViewById(R.id.activity_family_names);

        listView.setAdapter(itemsAdapter);

        /**for (int i=0; i < words.size(); i++)
         {
         TextView wordText = new TextView(this);
         wordText.setText(words.get(i));
         rootView.addView(wordText);
         } **/
    }
}


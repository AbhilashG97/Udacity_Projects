package com.example.abhilashg.learnmiwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class colors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("red","weṭeṭṭi", R.drawable.color_red));
        words.add(new Word("green","weṭeṭṭi",R.drawable.color_green));
        words.add(new Word("brown","weṭeṭṭi", R.drawable.color_brown));
        words.add(new Word("gray","weṭeṭṭi",R.drawable.color_gray));
        words.add(new Word("black","weṭeṭṭi",R.drawable.color_black));
        words.add(new Word("white","weṭeṭṭi",R.drawable.color_white));
        words.add(new Word("dusty yellow","weṭeṭṭi",R.drawable.color_dusty_yellow));
        words.add(new Word("mustard yellow","weṭeṭṭi", R.drawable.color_mustard_yellow));

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.Colors);

        ListView listView = (ListView) findViewById(R.id.activity_colors);

        listView.setAdapter(itemsAdapter);
    }
}

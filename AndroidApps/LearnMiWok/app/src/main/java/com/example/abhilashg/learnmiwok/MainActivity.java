package com.example.abhilashg.learnmiwok;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        CategoryAdapter adapter = new CategoryAdapter(getSupportFragmentManager());
//
        viewPager.setAdapter(adapter);
//        final TextView numbers = (TextView) findViewById(R.id.number_text_view);
//        numbers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent Numbers = new Intent(MainActivity.this, Numbers.class);
//                startActivity(Numbers);
//            }
//        });
//
//        // for the Family-Names category.
//
//        TextView familyNames = (TextView) findViewById(R.id.familyNames_text_view);
//        familyNames.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent familyNames = new Intent(MainActivity.this, familyNames.class);
//                startActivity(familyNames);
//            }
//        });
//        // For the colors category.
//
//        TextView color = (TextView) findViewById(R.id.colors_text_view);
//        color.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent colors = new Intent(MainActivity.this, colors.class);
//                startActivity(colors);
//            }
//        });
//
//        // For the phrases category.
//
//        TextView phrase = (TextView) findViewById(R.id.phrases_text_view);
//        phrase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent phrase = new Intent(MainActivity.this, phrases.class);
//                startActivity(phrase);
//            }
//        });
    }


}

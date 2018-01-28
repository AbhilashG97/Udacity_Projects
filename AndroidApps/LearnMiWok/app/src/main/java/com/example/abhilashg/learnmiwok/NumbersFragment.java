package com.example.abhilashg.learnmiwok;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {


    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_numbers, container, false);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("One" , "Lutti", R.drawable.number_one));
        words.add(new Word("Two" , "Otiiko", R.drawable.number_two));
        words.add(new Word("Three" , "Tolookosu",R.drawable.number_three));
        words.add(new Word("Four" , "Oyyisa", R.drawable.number_four));
        words.add(new Word("Five" , "Massokka", R.drawable.number_five));
        words.add(new Word("Six" , "Temmokka", R.drawable.number_six));
        words.add(new Word("Seven" , "Kenekaku", R.drawable.number_seven));
        words.add(new Word("Eight" , "Kawinta", R.drawable.number_eight));
        words.add(new Word("Nine" , "Wo’e", R.drawable.number_nine));
        words.add(new Word("Ten" , "Na’aacha", R.drawable.number_ten));


        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.Numbers);

        ListView listView = (ListView) rootView.findViewById(R.id.activity_numbers);

        listView.setAdapter(itemsAdapter);
        return rootView;
    }

}

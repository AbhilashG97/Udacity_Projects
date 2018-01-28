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
public class ColorsFragment extends Fragment {


    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_colors, container, false);
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("red","weṭeṭṭi", R.drawable.color_red));
        words.add(new Word("green","weṭeṭṭi",R.drawable.color_green));
        words.add(new Word("brown","weṭeṭṭi", R.drawable.color_brown));
        words.add(new Word("gray","weṭeṭṭi",R.drawable.color_gray));
        words.add(new Word("black","weṭeṭṭi",R.drawable.color_black));
        words.add(new Word("white","weṭeṭṭi",R.drawable.color_white));
        words.add(new Word("dusty yellow","weṭeṭṭi",R.drawable.color_dusty_yellow));
        words.add(new Word("mustard yellow","weṭeṭṭi", R.drawable.color_mustard_yellow));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.Colors);

        ListView listView = (ListView) rootView.findViewById(R.id.activity_colors);

        listView.setAdapter(itemsAdapter);
        return rootView;
    }

}

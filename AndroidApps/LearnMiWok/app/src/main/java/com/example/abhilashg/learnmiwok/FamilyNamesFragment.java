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
public class FamilyNamesFragment extends Fragment {


    public FamilyNamesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_family_names, container, false);
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


        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.Family);

        ListView listView = (ListView) rootView.findViewById(R.id.activity_family_names);

        listView.setAdapter(itemsAdapter);
        return rootView;
    }

}

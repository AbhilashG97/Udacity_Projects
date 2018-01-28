package com.example.abhilashg.learnmiwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by abhilashg on 22/1/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(Context context, ArrayList<Word> words, int backgroundColorId) {
        super(context, 0, words);
        mColorResourceId = backgroundColorId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Word textWords = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.miwok_text_view);
        ImageView ivName = (ImageView) convertView.findViewById(R.id.image);
        TextView tvHome = (TextView) convertView.findViewById(R.id.default_text_view);
        // Populate the data into the template view using the data object
        tvName.setText(textWords.getDefaultTranslation());
        //ivName.setImageResource(textWords.getImageResourceId());
        tvHome.setText(textWords.getMiwokTranslation());

        if (textWords.hasImage())
        {
            ivName.setImageResource(textWords.getImageResourceId());
            ivName.setVisibility(View.VISIBLE);
        } else
        {
            ivName.setVisibility(View.GONE);
        }
        // Set the theme color for the list item
        View textContainer = convertView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);
        // Return the completed view to render on screen
        return convertView;
    }
}


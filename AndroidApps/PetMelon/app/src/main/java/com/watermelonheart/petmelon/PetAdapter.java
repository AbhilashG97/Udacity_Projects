package com.watermelonheart.petmelon;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    private Cursor cursor;
    private Context context;

    public PetAdapter () {
        // Default constructor
    }

    public PetAdapter (Context context) {
        this.context = context;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PetViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.content_main, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder petViewHolder, int position) {
        petViewHolder.petName.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_pet_name)
        TextView petName;

        public PetViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

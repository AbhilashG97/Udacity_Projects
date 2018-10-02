package com.watermelonheart.petmelon;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    private Cursor cursor;
    private Context context;

    public PetAdapter(Cursor cursor, Context context) {
        this.context = context;
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder petViewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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

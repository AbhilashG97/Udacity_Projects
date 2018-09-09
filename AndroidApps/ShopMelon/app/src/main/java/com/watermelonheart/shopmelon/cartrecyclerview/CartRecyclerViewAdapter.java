package com.watermelonheart.shopmelon.cartrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.watermelonheart.shopmelon.R;
import com.watermelonheart.shopmelon.model.Phone;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartRecyclerViewAdapter extends Adapter<CartRecyclerViewAdapter.CartViewHolder> {

    private ArrayList<Phone> phoneList;
    private Context context;
    private ClickListener clickListener;

    public CartRecyclerViewAdapter(ArrayList<Phone> phoneList, Context context) {
        this.phoneList = phoneList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CartViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cart_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int position) {
        cartViewHolder.manufacturer.setText(phoneList.get(position).getManufacturer());
        cartViewHolder.model.setText(phoneList.get(position).getModel());
        cartViewHolder.price.setText(String.valueOf(phoneList.get(position).getPrice()));
        Picasso.get()
               .load(phoneList.get(position).getImage())
               .into(cartViewHolder.phoneImage);
    }

    @Override
    public int getItemCount() {
        return phoneList.size();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        @BindView(R.id.tv_manufacturer)
        TextView manufacturer;

        @BindView(R.id.tv_model)
        TextView model;

        @BindView(R.id.tv_price)
        TextView price;

        @BindView(R.id.iv_cart_item_image)
        ImageView phoneImage;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onItemLongClick(getAdapterPosition(), view);
            return false;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}

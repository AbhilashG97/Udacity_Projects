package com.watermelonheart.shopmelon.transactionrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.watermelonheart.shopmelon.R;
import com.watermelonheart.shopmelon.model.Transaction;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private ArrayList<Transaction> transactions;
    private Context context;

    public TransactionAdapter(ArrayList<Transaction> transactions, Context context) {
        this.transactions = transactions;
        this.context = context;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TransactionViewHolder(LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.transaction_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder transactionViewHolder, int position) {
        transactionViewHolder.username.setText(transactions.get(position).getUsername());
        transactionViewHolder.quantity.setText(String.valueOf(transactions.get(position).getQuantity()));
        transactionViewHolder.model.setText(transactions.get(position).getModel());
        transactionViewHolder.invoiceNumber.setText(transactions.get(position).getInvoiceNumber());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_username)
        TextView username;

        @BindView(R.id.tv_quantity)
        TextView quantity;

        @BindView(R.id.tv_model)
        TextView model;

        @BindView(R.id.tv_invoiceNumber)
        TextView invoiceNumber;

        public TransactionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

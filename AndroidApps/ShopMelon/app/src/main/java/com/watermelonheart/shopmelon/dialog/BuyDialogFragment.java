package com.watermelonheart.shopmelon.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.watermelonheart.shopmelon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyDialogFragment extends DialogFragment {

    @BindView(R.id.et_username)
    EditText username;

    @BindView(R.id.et_quantity)
    EditText quantity;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final View view = getLayoutInflater().inflate(R.layout.buy_custom_dialog, null);
        ButterKnife.bind(this, view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Buy a product");
        builder.setCancelable(false);
        builder.setMessage("Please enter these details")
               .setPositiveButton("Buy", (dialog, id) -> {
                   // handle transaction
                   Log.v("TAG", "Something");
               })
                .setNegativeButton("Cancwel", (dialog, id) -> dialog.dismiss());
        builder.create().show();
        return builder.create();

    }
}

package com.watermelonheart.shopmelon.cartrecyclerview;

import android.view.View;

public interface ClickListener {
    void onItemClick(int position, View view);

    void onItemLongClick(int position, View view);
}

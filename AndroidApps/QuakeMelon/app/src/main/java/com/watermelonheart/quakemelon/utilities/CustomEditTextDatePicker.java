package com.watermelonheart.quakemelon.utilities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.TimeZone;

public class CustomEditTextDatePicker implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private EditText editText;
    private Context context;
    private int day, month, year;

    public CustomEditTextDatePicker(Context context, int editTextResourceId) {
        this.context = context;
        Activity currentActivity = (Activity) context;
        editText = currentActivity.findViewById(editTextResourceId);
        this.editText.setOnClickListener(this);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        month = monthOfYear;
        day = dayOfMonth;
        updateDisplay();
    }

    @Override
    public void onClick(View view) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(context, this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void updateDisplay() {
        editText.setText(new StringBuffer().append(year)
                .append("-")
                .append(month + 1)
                .append("-")
                .append(day));
    }
}

package com.watermelonheart.shopmelon.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("quantity")
    @Expose
    private long quantity;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("invoiceNumber")
    @Expose
    private String invoiceNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String toString() {
        return username+" "+quantity+" "+model+" "+invoiceNumber;
    }

}

package com.pluralsight;
import java.util.ArrayList;
import java.util.List;

public class Transactions {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private String amount;


    public Transactions(String date, String time, String description, String vendor, String amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public String getAmount() {
        return amount;
    }

    // create a method to display the ledger with the newest entries first
    public static void displayLedger(boolean shoAll) {
        // Create a list to hold all the ledger entries


    }



}


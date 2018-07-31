package com.example.home.planner.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class EarningCard {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String title;
    private double amount;

    public EarningCard() {
    }

    @Ignore
    public EarningCard(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

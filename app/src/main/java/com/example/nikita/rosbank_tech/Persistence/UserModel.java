package com.example.nikita.rosbank_tech.Persistence;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "user_info")
public class UserModel implements Serializable {

    @PrimaryKey
    private Long id;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "CardNumber")
    private Long cardNumber;

    @ColumnInfo(name = "Balance")
    private Double balance;

    @ColumnInfo(name = "Work")
    private String work;

    @ColumnInfo(name = "OKVED")
    private ArrayList<String> okved;

    public ArrayList<String> getOkved() {
        return okved;
    }

    public void setOkved(ArrayList<String> okved) {
        this.okved = okved;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}

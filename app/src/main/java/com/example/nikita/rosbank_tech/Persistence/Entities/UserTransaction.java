package com.example.nikita.rosbank_tech.Persistence.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "user_transaction")
public class UserTransaction {

    @PrimaryKey
    private Integer id;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Price")
    private Integer price;

    @ColumnInfo(name = "Count")
    private Integer count;

    @ColumnInfo(name = "Category")
    private String category;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

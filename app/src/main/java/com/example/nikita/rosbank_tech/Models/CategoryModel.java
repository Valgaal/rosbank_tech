package com.example.nikita.rosbank_tech.Models;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class CategoryModel extends ExpandableGroup<ProductModel>{

    public CategoryModel(String title, List<ProductModel> items) {
        super(title, items);
    }
}

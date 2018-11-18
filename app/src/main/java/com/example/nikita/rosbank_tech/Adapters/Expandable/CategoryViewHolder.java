package com.example.nikita.rosbank_tech.Adapters.Expandable;

import android.view.View;
import android.widget.TextView;

import com.example.nikita.rosbank_tech.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class CategoryViewHolder extends GroupViewHolder {
    private TextView title;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.categoryName);
    }

    public void setCategory(ExpandableGroup group) {
        title.setText(group.getTitle());
    }
}

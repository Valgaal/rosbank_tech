package com.example.nikita.rosbank_tech.Adapters.Expandable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;

import com.example.nikita.rosbank_tech.Models.CategoryModel;
import com.example.nikita.rosbank_tech.Models.ProductModel;
import com.example.nikita.rosbank_tech.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class ProductsAdapter extends ExpandableRecyclerViewAdapter<CategoryViewHolder, ProductViewHolder> {

    private UserClicked mCallback;

    public interface UserClicked{
        void userClicked(ProductModel product);
    }

    public ProductsAdapter(List<? extends ExpandableGroup> groups, UserClicked mCallback) {
        super(groups);
        this.mCallback = mCallback;
    }

    @Override
    public CategoryViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public ProductViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.changes_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ProductViewHolder holder, int flatPosition,
                                      ExpandableGroup group, int childIndex) {

        final ProductModel product = ((CategoryModel) group).getItems().get(childIndex);
        holder.itemView.setOnClickListener(view -> {
            mCallback.userClicked(product);
        });
        holder.onBind(product);
    }

    @Override
    public void onBindGroupViewHolder(CategoryViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {

        holder.setCategory(group);
    }
}

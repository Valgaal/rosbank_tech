package com.example.nikita.rosbank_tech.Adapters.Expandable;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikita.rosbank_tech.Models.ProductModel;
import com.example.nikita.rosbank_tech.R;
import com.squareup.picasso.Picasso;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductViewHolder extends ChildViewHolder {

    CircleImageView photo;
    TextView productName;
    TextView categoryName;
    TextView count;
    TextView price;

    ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        photo = itemView.findViewById(R.id.photoImageView);
        productName = itemView.findViewById(R.id.productName);
        categoryName = itemView.findViewById(R.id.categoryName);
        count = itemView.findViewById(R.id.count);
        price = itemView.findViewById(R.id.price);
    }

    public void onBind(ProductModel productModel) {
        productName.setText(productModel.getName());
        categoryName.setText(productModel.getPartner());
        count.setText(Integer.toString(productModel.getCount()));
        price.setText(String.format(itemView.getContext().getString(R.string.balance), productModel.getAmount()));
        Picasso.get().load(productModel.getImage1()).into(photo);
    }
}

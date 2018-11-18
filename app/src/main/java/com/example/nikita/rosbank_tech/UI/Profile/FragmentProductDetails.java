package com.example.nikita.rosbank_tech.UI.Profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikita.rosbank_tech.Models.ItemOrder;
import com.example.nikita.rosbank_tech.Models.ProductModel;
import com.example.nikita.rosbank_tech.R;
import com.squareup.picasso.Picasso;

public class FragmentProductDetails extends Fragment {

    private static final String PRODUCT = "PRODUCT_MODEL";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        TextView name = view.findViewById(R.id.nameTextView);
        ImageView imageView = view.findViewById(R.id.avatarImageView);
        TextView description = view.findViewById(R.id.description);
        TextView partner = view.findViewById(R.id.partner);
        Button buyButton = view.findViewById(R.id.button);
        EditText editText = view.findViewById(R.id.countEdit);

        Bundle bundle = this.getArguments();
        ProductModel productModel = bundle.getParcelable(PRODUCT);

        name.setText(productModel.getName());
        Picasso.get().load(productModel.getImage1()).into(imageView);
        description.setText(productModel.getDescription());
        partner.setText(productModel.getPartner());
        buyButton.setOnClickListener(view1 -> {
            ItemOrder itemOrder = new ItemOrder();
            itemOrder.setCount(Integer.valueOf(editText.getText().toString()));
            itemOrder.setId(productModel.getId());
            Fragment fragment = FragmentPayment.newInstance(itemOrder);
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        });


        return view;
    }

    public static FragmentProductDetails newInstance(ProductModel productModel){
        FragmentProductDetails fragmentProductDetails = new FragmentProductDetails();
        Bundle args = new Bundle();
        args.putParcelable(PRODUCT , productModel);
        fragmentProductDetails.setArguments(args);
        return fragmentProductDetails;
    }
}

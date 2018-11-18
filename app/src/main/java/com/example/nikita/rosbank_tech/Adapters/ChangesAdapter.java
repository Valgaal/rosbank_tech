package com.example.nikita.rosbank_tech.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikita.rosbank_tech.Models.OperationsResponseDTO;
import com.example.nikita.rosbank_tech.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChangesAdapter extends RecyclerView.Adapter<ChangesAdapter.ViewHolder> {

    private List<OperationsResponseDTO> userTransactions;

    public ChangesAdapter(List<OperationsResponseDTO> userTransactions){
            this.userTransactions = userTransactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.changes_item, viewGroup, false);
        return new ChangesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        OperationsResponseDTO operationResponse = userTransactions.get(i);
        Picasso.get().load(operationResponse.getImage()).into(viewHolder.photo);
        viewHolder.productName.setText(operationResponse.getProduct());
        viewHolder.categoryName.setText(operationResponse.getDate());
        viewHolder.count.setText("1");
        viewHolder.setPrice(operationResponse.getAmount());
    }

    @Override
    public int getItemCount() {
        return userTransactions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView photo;
        TextView productName;
        TextView categoryName;
        TextView count;
        TextView price;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photoImageView);
            productName = itemView.findViewById(R.id.productName);
            categoryName = itemView.findViewById(R.id.categoryName);
            count = itemView.findViewById(R.id.count);
            price = itemView.findViewById(R.id.price);
        }

        void setPrice(Double value){
            price.setText(String.format(itemView.getContext().getString(R.string.balance), value));
        }
    }

}

package com.example.nikita.rosbank_tech.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.nikita.rosbank_tech.R;

import java.util.List;

public class OkvedAdapter extends RecyclerView.Adapter<OkvedAdapter.ViewHolder> {

    private List<String> okveds;
    private CheckBoxListener mCallback;

    public interface CheckBoxListener{
        void addToList(String okved);
        void deleteFromList(String okved);
    }

    public OkvedAdapter(List<String> okveds, CheckBoxListener listener){
        this.okveds = okveds;
        this.mCallback = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.okved_item, viewGroup, false);
        return new OkvedAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText("Код ОКВЭД " + okveds.get(i));
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewHolder.checkBox.isChecked()){
                    mCallback.addToList(okveds.get(i));
                }else{
                    mCallback.deleteFromList(okveds.get(i));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return okveds.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        CheckBox checkBox;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.okvedTextView);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}

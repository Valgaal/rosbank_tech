package com.example.nikita.rosbank_tech.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.nikita.rosbank_tech.Adapters.OkvedAdapter;
import com.example.nikita.rosbank_tech.Persistence.Entities.UserModel;
import com.example.nikita.rosbank_tech.R;

import java.io.Serializable;
import java.util.ArrayList;

public class FragmentSelectCategory extends MvpAppCompatFragment implements OkvedAdapter.CheckBoxListener{

    private static final String USER = "UserModel";
    private static final String CALLBACK = "Callback";

    private ArrayList<String> tempList = new ArrayList<>();
    private OkvedCallback mCallback;

    public interface OkvedCallback extends Serializable {
        void okvedChosen(UserModel userModel);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selected_category, container, false);
        Bundle bundle = this.getArguments();
        UserModel userModel = (UserModel) bundle.getSerializable(USER);
        mCallback = (OkvedCallback) bundle.getSerializable(CALLBACK);

        EditText work = view.findViewById(R.id.workEditText);
        TextView nameTextView = view.findViewById(R.id.greetings);
        nameTextView.setText(String.format(getString(R.string.greeting), userModel.getName()));
        work.setText(userModel.getWork());

        RecyclerView recyclerView = view.findViewById(R.id.rvOkved);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        OkvedAdapter okvedAdapter = new OkvedAdapter(userModel.getOkved(), this);
        recyclerView.setAdapter(okvedAdapter);

        Button done = view.findViewById(R.id.done);
        done.setOnClickListener(view1 -> {
            userModel.setWork(work.getText().toString());
            userModel.setOkved(tempList);
            mCallback.okvedChosen(userModel);
        });
        return view;
    }

    public static FragmentSelectCategory newInstance(UserModel userModel, OkvedCallback callback ){
        FragmentSelectCategory fragment = new FragmentSelectCategory();
        Bundle args = new Bundle();
        args.putSerializable(CALLBACK, callback);
        args.putSerializable(USER, userModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void addToList(String okved) {
        tempList.add(okved);
    }

    @Override
    public void deleteFromList(String okved) {
        tempList.remove(okved);
    }
}

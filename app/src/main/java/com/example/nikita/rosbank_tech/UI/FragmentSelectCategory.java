package com.example.nikita.rosbank_tech.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.nikita.rosbank_tech.Persistence.UserModel;
import com.example.nikita.rosbank_tech.R;

public class FragmentSelectCategory extends MvpAppCompatFragment {

    private static final String USER = "UserModel";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selected_category, container, false);
        Bundle bundle = this.getArguments();
        UserModel userModel = (UserModel) bundle.getSerializable(USER);
        EditText work = view.findViewById(R.id.workEditText);
        Button done = view.findViewById(R.id.done);
        done.setOnClickListener(view1 -> {
            work.getText().toString();
        });
        return view;
    }

    public static FragmentSelectCategory newInstance(UserModel userModel){
        FragmentSelectCategory fragment = new FragmentSelectCategory();
        Bundle args = new Bundle();
        args.putSerializable(USER, userModel);
        fragment.setArguments(args);
        return fragment;
    }
}

package com.example.nikita.rosbank_tech.UI.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.nikita.rosbank_tech.Adapters.ChangesAdapter;
import com.example.nikita.rosbank_tech.Persistence.Entities.UserModel;
import com.example.nikita.rosbank_tech.R;

import java.util.ArrayList;

public class ProfileFragment extends MvpAppCompatFragment {

    public static final String PROFILE = "Profile";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView balance = view.findViewById(R.id.balance);
        RecyclerView recyclerView = view.findViewById(R.id.rv_changes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Bundle bundle = this.getArguments();
        UserModel userModel = (UserModel) bundle.getSerializable(PROFILE);
        ChangesAdapter changesAdapter = new ChangesAdapter(new ArrayList<>());
        recyclerView.setAdapter(changesAdapter);
        balance.setText(String.format(getString(R.string.balance), userModel.getBalance()));
        return view;
    }

    public static ProfileFragment newInstance(UserModel userModel){
        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ProfileFragment.PROFILE, userModel );
        fragment.setArguments(bundle);
        return fragment;
    }
}

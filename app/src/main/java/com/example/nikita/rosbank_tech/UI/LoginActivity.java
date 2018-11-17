package com.example.nikita.rosbank_tech.UI;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.example.nikita.rosbank_tech.Persistence.Entities.UserModel;
import com.example.nikita.rosbank_tech.R;
import com.example.nikita.rosbank_tech.UI.Profile.ProfileActivity;

public class LoginActivity extends AppCompatActivity implements
        FragmentLogin.LoginCallback,
        FragmentSelectCategory.OkvedCallback{

    public static final String PROFILE = "Profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Fragment fragment = new FragmentLogin();
        ((FragmentLogin) fragment).setLoginCallback(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void loginSuccess(UserModel userModel) {
        Fragment fragment = FragmentSelectCategory.newInstance(userModel, this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void okvedChosen(UserModel userModel) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(PROFILE, userModel);
        startActivity(intent);
    }
}

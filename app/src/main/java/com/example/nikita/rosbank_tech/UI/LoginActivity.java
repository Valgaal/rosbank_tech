package com.example.nikita.rosbank_tech.UI;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.example.nikita.rosbank_tech.Persistence.Entities.UserModel;
import com.example.nikita.rosbank_tech.R;

public class LoginActivity extends AppCompatActivity implements FragmentLogin.LoginCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        Fragment fragment = new FragmentLogin();
        ((FragmentLogin) fragment).setLoginCallback(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void loginSuccess(UserModel userModel) {
        Fragment fragment = FragmentSelectCategory.newInstance(userModel);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}

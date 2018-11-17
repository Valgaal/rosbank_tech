package com.example.nikita.rosbank_tech.UI;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.nikita.rosbank_tech.Persistence.DataRepository;
import com.example.nikita.rosbank_tech.Presenters.LoginPresenter;
import com.example.nikita.rosbank_tech.R;
import com.example.nikita.rosbank_tech.Utils.NetworkUtils;
import com.example.nikita.rosbank_tech.views.LoginView;

import javax.inject.Inject;

public class LoginActivity extends MvpAppCompatActivity implements LoginView {

    @InjectPresenter
    LoginPresenter mLoginPresenter;

    ProgressBar mProgressBar;

    @ProvidePresenter
    LoginPresenter provideLoginPresenter(){
        return new LoginPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);
        mProgressBar = findViewById(R.id.progressBar);

        loginButton.setOnClickListener(view -> {
            mLoginPresenter.buttonClicked(email.getText().toString(), password.getText().toString());
        });
    }

    @Override
    public void login() {
        Fragment fragment = new FragmentSelectCategory();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishLoading() {
        mProgressBar.setVisibility(View.GONE);
    }
}

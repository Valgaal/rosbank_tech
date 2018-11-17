package com.example.nikita.rosbank_tech.UI;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;

import android.support.v7.app.AppCompatActivity;
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
    public void loginSuccess() {
        Fragment fragment = new FragmentSelectCategory();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}

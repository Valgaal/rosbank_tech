package com.example.nikita.rosbank_tech.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.nikita.rosbank_tech.Persistence.Entities.UserModel;
import com.example.nikita.rosbank_tech.Presenters.LoginPresenter;
import com.example.nikita.rosbank_tech.R;
import com.example.nikita.rosbank_tech.views.LoginView;

public class FragmentLogin extends MvpAppCompatFragment implements LoginView {

    @InjectPresenter
    LoginPresenter mLoginPresenter;

    ProgressBar mProgressBar;
    LoginCallback loginCallback;

    @ProvidePresenter
    LoginPresenter provideLoginPresenter(){
        return new LoginPresenter();
    }

    public interface LoginCallback{
        void loginSuccess(UserModel userModel);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container, false);

        EditText email = view.findViewById(R.id.email);
        EditText password = view.findViewById(R.id.password);
        Button loginButton = view.findViewById(R.id.loginButton);
        mProgressBar = view.findViewById(R.id.progressBar);

        loginButton.setOnClickListener(view1 -> {
            mLoginPresenter.buttonClicked(email.getText().toString(), password.getText().toString());
        });

        return view;
    }

    @Override
    public void login(UserModel userModel) {
        loginCallback.loginSuccess(userModel);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    public void setLoginCallback(LoginCallback callback) {
        loginCallback = callback;
    }

}

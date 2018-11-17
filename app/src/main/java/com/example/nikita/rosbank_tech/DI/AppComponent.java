package com.example.nikita.rosbank_tech.DI;

import com.example.nikita.rosbank_tech.Presenters.LoginPresenter;
import com.example.nikita.rosbank_tech.UI.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {NetworkModule.class, DbModule.class, DataModule.class})
@Singleton
public interface AppComponent {
    void inject(LoginPresenter loginPresenter);
}
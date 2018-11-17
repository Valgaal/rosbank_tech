package com.example.nikita.rosbank_tech.UI;

import android.app.Application;

import com.example.nikita.rosbank_tech.DI.AppComponent;
import com.example.nikita.rosbank_tech.DI.DaggerAppComponent;
import com.example.nikita.rosbank_tech.DI.DbModule;
import com.example.nikita.rosbank_tech.DI.NetworkModule;

public class App extends Application {
    private static AppComponent component;

    public static  AppComponent getComponent(){
        return component;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent(){
        return DaggerAppComponent.builder()
                .networkModule(new NetworkModule())
                .dbModule(new DbModule(getApplicationContext()))
                .build();
    }

}

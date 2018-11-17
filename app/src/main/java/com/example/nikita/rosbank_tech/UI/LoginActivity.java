package com.example.nikita.rosbank_tech.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nikita.rosbank_tech.Persistence.RoomDb;
import com.example.nikita.rosbank_tech.R;
import com.example.nikita.rosbank_tech.Utils.NetworkUtils;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {

    @Inject
    public RoomDb roomDb;

    @Inject
    public NetworkUtils networkUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        App.getComponent().inject(this);
    }
}

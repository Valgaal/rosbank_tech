package com.example.nikita.rosbank_tech.UI.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.nikita.rosbank_tech.Persistence.Entities.UserModel;
import com.example.nikita.rosbank_tech.R;
import com.example.nikita.rosbank_tech.UI.LoginActivity;

public class ProfileActivity extends MvpAppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        Intent intent = getIntent();
        UserModel userModel = (UserModel) intent.getSerializableExtra(LoginActivity.PROFILE);

        NavigationView navigationView = findViewById(R.id.nav_view);
        if(savedInstanceState == null){
            selectItem(0, userModel);
        }

        toolbar.setTitle(getResources().getString(R.string.profile));
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            switch (id){
                case R.id.profile:
                    selectItem(0, userModel);
                    toolbar.setTitle(getResources().getString(R.string.profile));
                    break;
                case R.id.marketplace:
                    selectItem(1, userModel);
                    toolbar.setTitle(getResources().getString(R.string.marketplace));
                    break;
                case R.id.events:
                    selectItem(2, userModel);
                    toolbar.setTitle(getResources().getString(R.string.events));
                    break;
                case R.id.chat:
                    selectItem(3, userModel);
                    toolbar.setTitle(getResources().getString(R.string.chat));
                    break;
            }
            menuItem.setChecked(true);
            mDrawerLayout.closeDrawers();
            return true;
        });
    }

    private void selectItem(int position, UserModel userModel){

        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = ProfileFragment.newInstance(userModel);
                break;
            case 1:
                fragment = new MarketPlaceFragment();
                break;
            case 2:
                Toast.makeText(this,"Click", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this,"Click", Toast.LENGTH_SHORT).show();
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        }
    }
}

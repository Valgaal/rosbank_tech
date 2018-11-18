package com.example.nikita.rosbank_tech.DI;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.nikita.rosbank_tech.Persistence.DataRepository;
import com.example.nikita.rosbank_tech.Persistence.RoomDb;
import com.example.nikita.rosbank_tech.Utils.NetworkUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    private Context mContext;

    public DataModule(Context context){
        mContext = context;
    }

    @Provides
    @Singleton
    public DataRepository provideDataRepository(NetworkUtils networkUtils, RoomDb roomDb, SharedPreferences sharedPreferences){
        return new DataRepository(networkUtils, roomDb, mContext, sharedPreferences);
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return mContext.getSharedPreferences("PrefName",Context.MODE_PRIVATE);
    }

}

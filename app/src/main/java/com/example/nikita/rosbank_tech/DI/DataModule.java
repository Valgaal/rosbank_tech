package com.example.nikita.rosbank_tech.DI;

import com.example.nikita.rosbank_tech.Persistence.DataRepository;
import com.example.nikita.rosbank_tech.Persistence.RoomDb;
import com.example.nikita.rosbank_tech.Utils.NetworkUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    public DataRepository provideDataRepository(NetworkUtils networkUtils, RoomDb roomDb){
        return new DataRepository(networkUtils, roomDb);
    }
}

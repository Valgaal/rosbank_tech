package com.example.nikita.rosbank_tech.DI;

import com.example.nikita.rosbank_tech.Utils.NetworkUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public NetworkUtils provideNetworkModule(){
        return new NetworkUtils();
    }
}

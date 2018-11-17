package com.example.nikita.rosbank_tech.DI;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.nikita.rosbank_tech.Persistence.RoomDb;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    private Context mContext;

    public DbModule(Context context){
        mContext = context;
    }

    @Provides
    @Singleton
    public RoomDb provideRoomDb(){
        return Room.databaseBuilder(mContext.getApplicationContext() ,
                RoomDb.class, "rosbank_db").build();
    }
}

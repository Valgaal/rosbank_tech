package com.example.nikita.rosbank_tech.Persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {UserModel.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class RoomDb extends RoomDatabase {
    public abstract UserDao userDao();
}

package com.example.home.planner.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.home.planner.model.EarningCard;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {EarningCard.class}, version = 1,exportSchema = false)
public abstract class LibDataBase extends RoomDatabase {

    private static LibDataBase INSTANCE;
    private static final Object lock = new Object();


    public abstract EarningCardDao getEarningCardDao();

    public static LibDataBase getInstance(Context context) {
        synchronized (lock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LibDataBase.class, "Cards.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}

package com.example.aplicacionactividad3.model;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class, History.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();
    public abstract HistoryDao historyDao();

    private static volatile AppDatabase INSTANCE;

    // Patrón Singleton para asegurar que solo haya una instancia de la BD
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "action_history_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
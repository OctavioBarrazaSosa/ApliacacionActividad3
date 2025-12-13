package com.example.aplicacionactividad3.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert
    void insert(History history);

    @Query("SELECT * FROM history ORDER BY created_at DESC")
    LiveData<List<History>> getAllHistory();

    @Query("SELECT * FROM history WHERE action LIKE :actionType ORDER BY created_at DESC")
    LiveData<List<History>> getHistoryByActionType(String actionType);

    @Query("SELECT * FROM history WHERE created_at LIKE :dateQuery || '%' ORDER BY created_at DESC")
    LiveData<List<History>> getHistoryByDate(String dateQuery);
}
package com.example.aplicacionactividad3.controller;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.aplicacionactividad3.model.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryRepository {
    private HistoryDao historyDao;
    private LiveData<List<History>> allHistory;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public HistoryRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        historyDao = db.historyDao();
        allHistory = historyDao.getAllHistory();
    }

    public LiveData<List<History>> getAllHistory() {
        return allHistory;
    }

    public LiveData<List<History>> getHistoryByActionType(String actionType) {
        return historyDao.getHistoryByActionType(actionType);
    }

    public LiveData<List<History>> getHistoryByDate(String dateQuery) {
        return historyDao.getHistoryByDate(dateQuery);
    }
}
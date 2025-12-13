package com.example.aplicacionactividad3.controller;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.aplicacionactividad3.model.Task;
import com.example.aplicacionactividad3.model.History;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    private HistoryRepository repository;

    public HistoryViewModel(Application application) {
        super(application);
        repository = new HistoryRepository(application);
    }

    public LiveData<List<History>> getAllHistory() {
        return repository.getAllHistory();
    }

    public LiveData<List<History>> getHistoryByActionType(String actionType) {
        return repository.getHistoryByActionType(actionType);
    }

    public LiveData<List<History>> getHistoryByDate(String dateQuery) {
        return repository.getHistoryByDate(dateQuery);
    }
}
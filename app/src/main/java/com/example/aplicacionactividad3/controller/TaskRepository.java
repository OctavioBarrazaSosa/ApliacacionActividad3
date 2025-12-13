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

public class TaskRepository {

    private TaskDao taskDao;
    private HistoryDao historyDao;
    private LiveData<List<Task>> allTasks;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public TaskRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        taskDao = db.taskDao();
        historyDao = db.historyDao();
        allTasks = taskDao.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }


    public void insert(Task task) {
        executorService.execute(() -> {
            taskDao.insert(task);
            logAction("insert_task", "Tarea: " + task.getTaskTitle());
        });
    }

    public void update(Task task) {
        executorService.execute(() -> {
            taskDao.update(task);
            logAction("update_task", "Tarea ID " + task.getTaskId() + ": " + task.getTaskTitle());
        });
    }

    public void delete(Task task) {
        executorService.execute(() -> {
            taskDao.delete(task);
            logAction("delete_task", "Tarea eliminada: " + task.getTaskTitle());
        });
    }

    private void logAction(String action, String details) {
        String timestamp = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(new Date());


        if (action == null || timestamp == null || action.isEmpty() || timestamp.isEmpty()) {
            return;
        }

        History history = new History(action, timestamp, details);
        historyDao.insert(history);
    }
}
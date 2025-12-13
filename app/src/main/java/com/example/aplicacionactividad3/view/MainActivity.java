package com.example.aplicacionactividad3.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aplicacionactividad3.R;
import com.example.aplicacionactividad3.controller.TaskViewModel;
import com.example.aplicacionactividad3.model.Task;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    private TaskAdapter taskAdapter;
    private EditText editTextNewTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNewTask = findViewById(R.id.editTextNewTask);
        Button buttonAddTask = findViewById(R.id.buttonAddTask);
        Button buttonViewHistory = findViewById(R.id.buttonViewHistory);
        RecyclerView tasksRecyclerView = findViewById(R.id.tasksRecyclerView);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        taskAdapter = new TaskAdapter(taskViewModel);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksRecyclerView.setAdapter(taskAdapter);

        taskViewModel.getAllTasks().observe(this, tasks -> {
            taskAdapter.setTasks(tasks);
        });

        buttonAddTask.setOnClickListener(v -> {
            String taskTitle = editTextNewTask.getText().toString().trim();

            if (TextUtils.isEmpty(taskTitle)) {
                Toast.makeText(MainActivity.this, "Por favor, ingresa el título de la tarea.", Toast.LENGTH_SHORT).show();
                return;
            }

            Task newTask = new Task(taskTitle, false);
            taskViewModel.insert(newTask);

            editTextNewTask.setText("");
        });


        buttonViewHistory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
    }
}
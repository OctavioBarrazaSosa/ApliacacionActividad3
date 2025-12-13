package com.example.aplicacionactividad3.view;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aplicacionactividad3.R;
import com.example.aplicacionactividad3.model.Task;
import com.example.aplicacionactividad3.controller.TaskViewModel;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList = new ArrayList<>();
    private TaskViewModel taskViewModel;

    public TaskAdapter(TaskViewModel viewModel) {
        this.taskViewModel = viewModel;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task currentTask = taskList.get(position);
        holder.textViewTaskTitle.setText(currentTask.getTaskTitle());
        holder.checkBoxCompleted.setChecked(currentTask.isCompleted());

        if (currentTask.isCompleted()) {
            holder.textViewTaskTitle.setPaintFlags(holder.textViewTaskTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.textViewTaskTitle.setPaintFlags(holder.textViewTaskTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        holder.checkBoxCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentTask.setCompleted(isChecked);
            taskViewModel.update(currentTask);
        });

        // Listener para DELETE
        holder.buttonDeleteTask.setOnClickListener(v -> {
            taskViewModel.delete(currentTask);
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    // Método para actualizar la lista desde el LiveData
    public void setTasks(List<Task> tasks) {
        this.taskList = tasks;
        notifyDataSetChanged();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTaskTitle;
        private final CheckBox checkBoxCompleted;
        private final ImageButton buttonDeleteTask;

        public TaskViewHolder(View itemView) {
            super(itemView);
            textViewTaskTitle = itemView.findViewById(R.id.textViewTaskTitle);
            checkBoxCompleted = itemView.findViewById(R.id.checkBoxCompleted);
            buttonDeleteTask = itemView.findViewById(R.id.buttonDeleteTask);
        }
    }
}
package com.example.aplicacionactividad3.view;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aplicacionactividad3.R;
import com.example.aplicacionactividad3.controller.HistoryViewModel;

public class HistoryActivity extends AppCompatActivity {

    private HistoryViewModel historyViewModel;
    private HistoryAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Necesitas un layout para esta actividad (ej: activity_history.xml)
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.historyRecyclerView);
        Spinner filterSpinner = findViewById(R.id.actionFilterSpinner);

        adapter = new HistoryAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);


        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                String selectedAction = parent.getItemAtPosition(position).toString();

                if (selectedAction.equals("Todos los tipos")) {
                    historyViewModel.getAllHistory().observe(HistoryActivity.this, adapter::setHistory);
                } else {
                    historyViewModel.getHistoryByActionType(selectedAction).observe(HistoryActivity.this, adapter::setHistory);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        historyViewModel.getAllHistory().observe(this, adapter::setHistory);
    }
}
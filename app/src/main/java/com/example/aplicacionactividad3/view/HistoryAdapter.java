package com.example.aplicacionactividad3.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aplicacionactividad3.R;
import com.example.aplicacionactividad3.model.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<History> historyList = new ArrayList<>();

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History currentHistory = historyList.get(position);

        String actionDisplay = currentHistory.getAction().replace("_", " ").toUpperCase();

        holder.textViewAction.setText(actionDisplay);
        holder.textViewDetails.setText(currentHistory.getDetails());
        holder.textViewTimestamp.setText(currentHistory.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public void setHistory(List<History> history) {
        this.historyList = history;
        notifyDataSetChanged();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewAction;
        private final TextView textViewDetails;
        private final TextView textViewTimestamp;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            textViewAction = itemView.findViewById(R.id.textViewAction);
            textViewDetails = itemView.findViewById(R.id.textViewDetails);
            textViewTimestamp = itemView.findViewById(R.id.textViewTimestamp);
        }
    }
}
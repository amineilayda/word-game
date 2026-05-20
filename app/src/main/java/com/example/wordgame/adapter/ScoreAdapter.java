package com.example.wordgame.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordgame.R;
import com.example.wordgame.model.GameScore;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    List<GameScore> list;

    public ScoreAdapter(List<GameScore> list) {
        this.list = list;
    }


    //XML (item_score) alır ve ekranda kullanılacak View nesnesine çevirir.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_score, parent, false);
        return new ViewHolder(v);
    }
    //sürekli find bt ıd çevirmemek için recycle

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GameScore s = list.get(position);

        holder.txtName.setText(s.getUsername());
        holder.txtScore.setText("Puan: " + s.getScore());
        holder.txtDate.setText("Tarih: " + s.getDate());
        holder.txtGrid.setText("Grid: " + s.getGridSize() + "x" + s.getGridSize());
        holder.txtWords.setText("Kelime: " + s.getWordCount());
        holder.txtLongest.setText("En uzun: " + s.getLongestWord());
        holder.txtTime.setText("Süre: " + (s.getDuration() / 60000) + " dk");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtScore, txtDate, txtGrid, txtWords, txtLongest, txtTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtScore = itemView.findViewById(R.id.txtScore);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtGrid = itemView.findViewById(R.id.txtGrid);
            txtWords = itemView.findViewById(R.id.txtWords);
            txtLongest = itemView.findViewById(R.id.txtLongest);
            txtTime = itemView.findViewById(R.id.txtTime);
        }
    }
}
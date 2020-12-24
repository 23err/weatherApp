package ru.geekbrains;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityViewHolder extends RecyclerView.ViewHolder {
    final TextView cityTextView;

    public CityViewHolder(@NonNull View itemView) {
        super(itemView);
        cityTextView = itemView.findViewById(R.id.cityTextView);

    }
}

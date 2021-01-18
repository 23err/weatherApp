package ru.geekbrains;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityViewHolder extends RecyclerView.ViewHolder {
    private final static String CITY = "city";
    final TextView cityTextView;

    public CityViewHolder(@NonNull View itemView, View.OnClickListener onClickListener) {
        super(itemView);
        cityTextView = itemView.findViewById(R.id.cityTextView);
        itemView.setOnClickListener(onClickListener);

    }
}

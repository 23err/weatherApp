package ru.geekbrains;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CityRecyclerViewAdapter extends RecyclerView.Adapter<CityViewHolder> {

    private List<String> citiesList;
    private LayoutInflater layoutInflater;
    private View.OnClickListener onClickListener;

    public CityRecyclerViewAdapter(Context context, List<String> data, View.OnClickListener onClickListener){
        citiesList = data;
        layoutInflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.city_recyclerview_item, parent, false);
        return new CityViewHolder(view, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.cityTextView.setText(citiesList.get(position));

    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }
}

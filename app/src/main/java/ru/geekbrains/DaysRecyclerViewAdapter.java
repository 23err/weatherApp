package ru.geekbrains;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DaysRecyclerViewAdapter extends RecyclerView.Adapter<DaysRecyclerViewAdapter.ViewHolder> {

    private ArrayList<DayTemp> temperatureByDayList;

    public DaysRecyclerViewAdapter(ArrayList<DayTemp> temperatureByDayList) {
        this.temperatureByDayList = temperatureByDayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_temp_rv_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DayTemp dayTemp = temperatureByDayList.get(position);
        holder.dayTempPic.setImageDrawable(dayTemp.getPic());
//        holder.dayTemp.setText(dayTemp.getTemperature());
        holder.dayTemp.setText(Integer.toString(dayTemp.getTemperature()));
        holder.dayTV.setText(dayTemp.getDay());

    }





    @Override
    public int getItemCount() {
        return temperatureByDayList == null ? 0 : temperatureByDayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView dayTempPic;
        TextView dayTemp;
        TextView dayTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayTempPic = itemView.findViewById(R.id.day_temp_pic);
            dayTemp = itemView.findViewById(R.id.day_temp);
            dayTV = itemView.findViewById(R.id.day_tv);
        }
    }

}

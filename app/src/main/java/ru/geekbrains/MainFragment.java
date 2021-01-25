package ru.geekbrains;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainFragment extends Fragment implements Observer, PublisherGetter {
    private static int REQUEST_CODE_SECOND_ACTIVITY = 123;
    private Publisher publisher;

    private ImageView cityButton;
    private ImageView infoButton;
    private TextView temperatureTextView;
    private TextView cityTextView;
    private RecyclerView daysRV;

    private ImageView settingBtn;
    private ConstraintLayout layoutMainFragment;

    private String currentCity = "Москва";
    private ArrayList<DayTemp> dayTempList;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setClickListeners();

        initData();

        setAdapterRV();
        setPublisher();

        changeViewInLandscapeOrientation();
    }

    private void setAdapterRV() {
        DaysRecyclerViewAdapter DRAdapter;
        DRAdapter = new DaysRecyclerViewAdapter(dayTempList);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        daysRV.setLayoutManager(linearLayout);
        daysRV.setAdapter(DRAdapter);
    }

    private void changeViewInLandscapeOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            cityButton.setVisibility(View.INVISIBLE);
            CitiesFragment citiesFragment = CitiesFragment.create(publisher);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.cities_container, citiesFragment);
            ft.commit();
        }
    }

    private void setPublisher() {
        publisher = new Publisher();
        publisher.subscribe(this);
    }

    private void initData() {
        dayTempList = new ArrayList<>(Arrays.asList(
                new DayTemp("чт", 24, ContextCompat.getDrawable(getContext(), R.drawable.sun)),
                new DayTemp("пт", 23, ContextCompat.getDrawable(getContext(), R.drawable.sun)),
                new DayTemp("сб", 22, ContextCompat.getDrawable(getContext(), R.drawable.sun)),
                new DayTemp("вс", 24, ContextCompat.getDrawable(getContext(), R.drawable.sun)),
                new DayTemp("пн", 25, ContextCompat.getDrawable(getContext(), R.drawable.sun)),
                new DayTemp("вт", 25, ContextCompat.getDrawable(getContext(), R.drawable.sun)),
                new DayTemp("ср", 24, ContextCompat.getDrawable(getContext(), R.drawable.sun))
        ));

    }

    private void setClickListeners() {
        cityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CitiesFragment citiesFragment = CitiesFragment.create(publisher);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.addToBackStack("main");
                ft.replace(R.id.frame_layout, citiesFragment);
                ft.commit();
            }
        });

        infoButton.setOnClickListener(v -> {
            String url = "https://ru.wikipedia.org/wiki/" + cityTextView.getText().toString();
            Uri uri = Uri.parse(url);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browserIntent);
        });

        settingBtn.setOnClickListener(l -> {
            SettingFragment sf = new SettingFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.addToBackStack("main");
            ft.replace(R.id.frame_layout, sf);
            ft.commit();
        });
    }

    private void findViews(View view) {
        infoButton = view.findViewById(R.id.infoBtn);
        cityTextView = view.findViewById(R.id.cityTextView);
        temperatureTextView = view.findViewById(R.id.temperature);
        cityButton = view.findViewById(R.id.cityButton);
        daysRV = view.findViewById(R.id.days_rv);
        settingBtn = view.findViewById(R.id.settingBtn);
        layoutMainFragment = view.findViewById(R.id.layout_main_fragment);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void updateCity(String city) {
        cityTextView.setText(city);

    }

    @Override
    public Publisher getPublisher() {
        return publisher;
    }
}
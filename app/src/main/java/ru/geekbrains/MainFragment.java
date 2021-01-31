package ru.geekbrains;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;

import ru.geekbrains.model.WeatherRequest;

public class MainFragment extends Fragment implements Observer, PublisherGetter {
    private final static int SETTING_ACTIVITY_CODE = 7777;
    public final static String CELCIUS = " C°";
    public static final int CITIES_ACTIVITY_CODE = 1111;
    private static int REQUEST_CODE_SECOND_ACTIVITY = 123;
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q={city}&lang=ru&units=metric&appid=";
    private static final String API_KEY = "987da5318dced5efcd4845fd60160afd";


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.d("Test123", "main Fragment create");

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        setClickListeners();

        initData();

        setAdapterRV();
        setPublisher();

        changeViewInLandscapeOrientation();
        Log.d("onViewCreated", "onViewCreated: mainfragment");
        updateTemperature(currentCity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private void updateTemperature(String city) {
        try {
            URL uri = new URL(WEATHER_URL.replace("{city}", city) + API_KEY);
            Handler handler = new Handler(Looper.getMainLooper());
            new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void run() {
                    HttpsURLConnection urlConnection = null;
                    try {
                        urlConnection = (HttpsURLConnection) uri.openConnection();
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setReadTimeout(10000);
                        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        String result = getLines(in);
                        Gson gson = new Gson();
                        WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                displayWeather(weatherRequest, city);
                            }
                        });

                    } catch (SSLHandshakeException e) {
                        Log.d("Соединение", "Возникла проблема с подключением к сетевым ресурсам.");
                        handler.post(() -> {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Ошибка");
                            builder.setMessage("Не можем подключиться к сервисам, проверьте интернет подключение.");
                            builder.setPositiveButton("OK", ((dialogInterface, i) -> dialogInterface.dismiss()));
                            AlertDialog alert = builder.create();
                            alert.show();

                            currentCity = "";
                            temperatureTextView.setText("--" + CELCIUS);
                            cityTextView.setText(currentCity);

                        });
                    } catch (FileNotFoundException e) {
                        Log.d("file not found", "run: Такого города нет");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Ошибка");
                                builder.setMessage("Не удалось получить данные для \"" + city + "\"");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (urlConnection != null) {
                            urlConnection.disconnect();
                        }
                    }
                }
            }).start();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void displayWeather(WeatherRequest weatherRequest, String city) {
        Log.d("weatherResult", weatherRequest.getName());
        currentCity = city;
        cityTextView.setText(currentCity);
        temperatureTextView.setText(String.valueOf(weatherRequest.getMain().getTemp()) + CELCIUS);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));

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

            CitiesFragment cf = CitiesFragment.create(getPublisher());
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.addToBackStack("main");
            ft.replace(R.id.cities_container, cf);
            ft.replace(R.id.frame_layout, this);
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
//                Intent intent = new Intent(getContext(), CitiesActivity.class);
//                intent.putExtra("publisher", getPublisher());
//                startActivity(intent);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.addToBackStack("main");
                ft.add(R.id.frame_layout, citiesFragment);
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
//            SettingFragment sf = new SettingFragment();
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            ft.addToBackStack("main");
//            ft.add(R.id.frame_layout, sf);
//            ft.commit();
            Intent intent = new Intent(getContext(), SettingActivity.class);
            startActivityForResult(intent, SETTING_ACTIVITY_CODE);

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case SETTING_ACTIVITY_CODE: {
                getActivity().recreate();
                break;
            }

        }
        super.onActivityResult(requestCode, resultCode, data);

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
    public void updateCity(String city) {
        updateTemperature(city);
        Log.d("test123", "change city");
        Log.d("test123", city);

    }

    @Override
    public Publisher getPublisher() {
        return publisher;
    }
}
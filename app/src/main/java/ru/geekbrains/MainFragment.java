package ru.geekbrains;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainFragment extends Fragment  implements Observer, PublisherGetter {
    private static int REQUEST_CODE_SECOND_ACTIVITY = 123;
    private Publisher publisher;

    private ImageView cityButton;
    private ImageView infoButton;
    private TextView temperatureTextView;
    private TextView cityTextView;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setClickListeners();

        publisher = new Publisher();
        publisher.subscribe(this);

    }

    private void setClickListeners() {
        cityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), CitiesActivity.class);
//                startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY);

                CitiesFragment citiesFragment = CitiesFragment.create(publisher);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.addToBackStack("main");
                ft.replace(R.id.frame_layout, citiesFragment);

                ft.commit();

            }
        });

        infoButton.setOnClickListener(v->{
            String url = "https://ru.wikipedia.org/wiki/" + cityTextView.getText().toString();
            Uri uri = Uri.parse(url);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browserIntent);
        });
    }

    private void findViews(View view) {
        infoButton = view.findViewById(R.id.infoBtn);
        cityTextView = view.findViewById(R.id.cityTextView);
        temperatureTextView = view.findViewById(R.id.temperature);
        cityButton = view.findViewById(R.id.cityButton);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
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
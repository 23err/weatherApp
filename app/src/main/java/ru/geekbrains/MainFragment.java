package ru.geekbrains;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainFragment extends Fragment  implements Observer {
    private static final int REQUEST_CODE_SECOND_ACTIVITY = 123;

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
    }

    private void setClickListeners() {
        cityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CitiesActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY);

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
        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SECOND_ACTIVITY && resultCode == getActivity().RESULT_OK) {
            String city = data.getStringExtra(CitiesFragment.CITY);
            cityTextView.setText(city);

        }
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
}
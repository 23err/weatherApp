package ru.geekbrains;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SECOND_ACTIVITY = 123;
    private enum StateParams {
            TEMPERATURE;
    }

    ImageView cityButton;
    ImageView infoButton;
    TextView temperatureTextView;
    TextView cityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Activity create", Toast.LENGTH_LONG).show();
        Log.d("activity status", "activity create");

        findViews();
        setClickListeners();



    }

    private void setClickListeners() {
        cityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CitiesActivity.class);
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

    private void findViews() {
        infoButton = findViewById(R.id.infoBtn);
        cityTextView = findViewById(R.id.cityTextView);
        temperatureTextView = findViewById(R.id.temperature);
        cityButton = findViewById(R.id.cityButton);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SECOND_ACTIVITY && resultCode == RESULT_OK) {
            String city = data.getStringExtra(CitiesActivity.CITY);
            cityTextView.setText(city);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "activity start", Toast.LENGTH_LONG).show();
        Log.d("activity status", "activity start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "activity pause", Toast.LENGTH_LONG).show();
        Log.d("activity status", "activity pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "activity resume", Toast.LENGTH_LONG).show();
        Log.d("activity status", "activity resume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "activity stop", Toast.LENGTH_LONG).show();
        Log.d("activity status", "activity stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "activity restart", Toast.LENGTH_LONG).show();
        Log.d("activity status", "activity restart");
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "activity destroy", Toast.LENGTH_LONG).show();
        Log.d("activity status", "activity destroy");
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(StateParams.TEMPERATURE.toString(), temperatureTextView.getText().toString());
        super.onSaveInstanceState(outState);

        DataContainer dataContainer = DataContainer.getInstance();
        dataContainer.setTemperature(temperatureTextView.getText().toString());


    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        temperatureTextView.setText(savedInstanceState.getString(StateParams.TEMPERATURE.toString()));

        DataContainer dataContainer = DataContainer.getInstance();
        Log.d("data container", dataContainer.getTemperature());

    }
}
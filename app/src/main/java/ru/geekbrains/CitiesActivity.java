package ru.geekbrains;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CitiesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.d("TAG", "onCreate: finish()");
            finish();
        }
        Publisher publisher = (Publisher) savedInstanceState.getSerializable("publisher");
        CitiesFragment cf = CitiesFragment.create(publisher);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.citiesRecyclerView, cf);
        setContentView(R.layout.activity_cities);
    }



}


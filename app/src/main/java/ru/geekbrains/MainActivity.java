package ru.geekbrains;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isDarkTheme()) {
            setTheme(R.style.AppDarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }


        try {

            setContentView(R.layout.activity_main);
        } catch (Exception e){
            Log.d("Exception", e.getMessage());
        }



    }

    protected boolean isDarkTheme() {
        SharedPreferences sp = getSharedPreferences(SettingFragment.NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        return sp.getBoolean(SettingFragment.IS_DARK_THEME, false);
    }




}
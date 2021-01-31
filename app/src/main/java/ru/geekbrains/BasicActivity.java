package ru.geekbrains;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BasicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkDarkTheme();
    }
    private void checkDarkTheme() {
        if (isDarkTheme()) {
            setTheme(R.style.AppDarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
    }


    protected boolean isDarkTheme() {
        SharedPreferences sp = getSharedPreferences(SettingFragment.NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        return sp.getBoolean(SettingFragment.IS_DARK_THEME, false);
    }
}

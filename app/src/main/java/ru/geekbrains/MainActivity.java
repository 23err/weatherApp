package ru.geekbrains;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

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

public class MainActivity extends BasicActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            if (savedInstanceState == null) {
                MainFragment mf = new MainFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.frame_layout, mf);
                ft.commit();
                Log.d("test123", "recreate main fragment");
            }


    }


}
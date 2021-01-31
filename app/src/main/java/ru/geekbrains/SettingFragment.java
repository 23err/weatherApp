package ru.geekbrains;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingFragment extends Fragment {
    public final static String NAME_SHARED_PREFERENCE = "LOGIN";
    public final static String IS_DARK_THEME = "IS_DARK_THEME";

    private SwitchMaterial darkThemeSM;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.setting_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setDefaultState();

        setClickListenter();
    }

    private void setClickListenter() {
        darkThemeSM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setDarkTheme(b);

                getActivity().recreate();
            }
        });
    }

    private void setDefaultState() {

        darkThemeSM.setChecked(isDarkTheme());
    }

    private void findViews(@NonNull View view) {

        darkThemeSM = view.findViewById(R.id.dark_theme_sm);

    }

    private void setDarkTheme (boolean isDarkTheme){


        SharedPreferences sp = getActivity().getSharedPreferences(NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(IS_DARK_THEME, isDarkTheme);
        editor.apply();



    }

    private boolean isDarkTheme() {
        SharedPreferences sp = getActivity().getSharedPreferences(SettingFragment.NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        return sp.getBoolean(SettingFragment.IS_DARK_THEME, false);
    }

}

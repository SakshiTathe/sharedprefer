package com.example.sharedprefer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Switch themeSwitch;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load the theme first before calling super.onCreate()
        sharedPreferences = getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean("isDarkMode", false);

        if (isDarkMode) {
            setTheme(R.style.Theme_App_Dark);  // Apply dark theme
        } else {
            setTheme(R.style.Theme_App_Light); // Apply light theme
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        themeSwitch = findViewById(R.id.swt);
        btn=findViewById(R.id.button);

        // Initialize the switch with the correct theme
        themeSwitch.setChecked(isDarkMode);

        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Save the selected theme in SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isDarkMode", isChecked);
            editor.apply();

            // Restart activity to apply the new theme
            recreate();
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, Loginpage.class);
                startActivity(i);
            }
        });
    }
}

/*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this, Loginpage.class);
                startActivity(i);

                SharedPreferences pref=getSharedPreferences("auth",MODE_PRIVATE);
                Boolean check=pref.getBoolean("flag",false);
                Intent i;
                if(check){
                    i=new Intent(MainActivity.this,Homescreen.class);
                }else{
                    i=new Intent(MainActivity.this,Loginpage.class);
                }

                finish();
            }
        },4000);*/
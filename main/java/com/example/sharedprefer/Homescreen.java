package com.example.sharedprefer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Homescreen extends AppCompatActivity {
    TextView name2,prn2,email2;
    Button logoutbtn;
    SharedPreferences share2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homescreen);
        name2=findViewById(R.id.name);

        prn2=findViewById(R.id.prnn);
        email2=findViewById(R.id.emaill);
        logoutbtn=findViewById(R.id.logout);

        share2=getSharedPreferences("keys",MODE_PRIVATE);

        String names=share2.getString("keyname",null);
        String pass=share2.getString("keypass",null);

        String emstr=share2.getString("keyemail",null);
        String prnstr=share2.getString("keyprn",null);

        if(names!=null || pass!=null){
            name2.setText(names);
            prn2.setText(prnstr);
            email2.setText(emstr);
        }
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor= share2.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();
                Intent intent = new Intent(Homescreen.this, Loginpage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
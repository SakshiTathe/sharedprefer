package com.example.sharedprefer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Loginpage extends AppCompatActivity {
    EditText name,pass,prn,email;
    Button logbtn;
    SharedPreferences share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginpage);

        name=findViewById(R.id.name);
        pass=findViewById(R.id.editpassword);
        logbtn=findViewById(R.id.loginn);

        email=findViewById(R.id.email);
        prn=findViewById(R.id.prn);

        share=getSharedPreferences("keys",MODE_PRIVATE);
        String savedName = share.getString("keyname", null);
        String savedPass = share.getString("keypass", null);
        String savedemstr=share.getString("keyemail",null);
        String savedprnstr=share.getString("keyprn",null);

        boolean isLoggedIn = share.getBoolean("isLoggedIn", false);

        if(isLoggedIn){
            Intent inext=new Intent(Loginpage.this,Homescreen.class);
            startActivity(inext);
            finish();
        }

        if (savedName != null && savedPass != null) {
            name.setText(savedName);
            prn.setText(savedprnstr);
            email.setText(savedemstr);
        }


        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=share.edit();
                editor.putString("keyname", name.getText().toString()); // Save username
                editor.putString("keypass", pass.getText().toString()); // Save password
                editor.putString("keyprn", prn.getText().toString()); // Save username
                editor.putString("keyemail", email.getText().toString());

                editor.putBoolean("isLoggedIn", true); // Set login state
                editor.apply();

                Intent inext=new Intent(Loginpage.this,Homescreen.class);
                startActivity(inext);
                finish();
            }
        });
    }
}
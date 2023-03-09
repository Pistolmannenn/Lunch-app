package com.example.lunch_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lunch_app.databinding.LoginBinding;

public class Login extends AppCompatActivity {
    private LoginBinding binding;

    // references to buttons and other controls on the layout;
    Button loggain, newlogin, forgot;
    EditText log_user, log_password;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = LoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(view);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        loggain = binding.loggain;
        forgot = binding.forgot;
        newlogin = binding.newlogin;
        log_user = binding.logUser;
        log_password = binding.logPassword;
        String name = "Rasmus";
        String pass = "12345";

        // button listener for the login button
        loggain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(log_user.getText().length() != 0 && log_password.getText().length() != 0){
                    if(log_user.getText().toString().equals(name) && log_password.getText().toString().equals(pass)){
                        Intent in = new Intent(Login.this, MainActivity.class);
                        Login.this.startActivity(in);
                    }
                    else{
                        Toast.makeText(Login.this, log_user.getText()+":"+log_password.getText(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(Login.this,"Tyvärr, fel användarnamn/lösenord", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Login.this,"Du kan inte lämna någon textruta tom!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        newlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, AddPerson.class);
                intent.putExtra("FROM_ACTIVITY", "B");
                Login.this.startActivity(intent);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, NewPassword.class);
                Login.this.startActivity(intent);
            }
        });
    }
}
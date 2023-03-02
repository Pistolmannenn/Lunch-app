package com.example.lunch_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lunch_app.databinding.AddpersonBinding;

public class AddPerson extends Activity {
    private AddpersonBinding binding;

    // references to buttons and other controls on the layout;
    Button btn_add;
    ImageButton btn_arrow;
    EditText et_name, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddpersonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        btn_add = binding.Addp;
        btn_arrow = binding.arrow;
        et_name = binding.etName;
        et_password = binding.etPassword;


        // button listener for the add button
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddPerson.this, "Nytt inlogg skapad", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddPerson.this, Login.class);
                AddPerson.this.startActivity(intent);
            }
        });

        btn_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = getIntent();
                String prevActivity = myIntent.getStringExtra("FROM_ACTIVITY");
                String prevName = myIntent.getStringExtra("loginuser");
                String prevPassword = myIntent.getStringExtra("loginpassword");

                if(prevActivity.equals("A"))
                {
                    Intent intent = new Intent(AddPerson.this, MainActivity.class);
                    AddPerson.this.startActivity(intent);
                }

                if(prevActivity.equals("B"))
                {
                    Intent intent = new Intent(AddPerson.this, Login.class);
                    AddPerson.this.startActivity(intent);
                }
            }
        });
    }
}


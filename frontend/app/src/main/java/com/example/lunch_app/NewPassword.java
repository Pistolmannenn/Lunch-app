package com.example.lunch_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lunch_app.databinding.NewpasswordBinding;

public class NewPassword extends Activity {
    private NewpasswordBinding binding;

    Button btn_update;
    ImageButton btn_back;

    EditText confirm_name, new_password, confirm_password;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = NewpasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        btn_update = binding.Update;
        btn_back = binding.back;
        confirm_name = binding.confirmName;
        new_password = binding.newPassword;
        confirm_password = binding.confirmPassword;

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirm_name.getText().length() != 0 && new_password.length() != 0 && confirm_password.length() != 0){
                    Toast.makeText(NewPassword.this, "Lösenordet är uppdaterad", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NewPassword.this, Login.class);
                    NewPassword.this.startActivity(intent);
                }
                else{
                    Toast.makeText(NewPassword.this, "Du kan inte lämna någon textruta tom!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewPassword.this, Login.class);
                NewPassword.this.startActivity(intent);
            }
        });
    }
}
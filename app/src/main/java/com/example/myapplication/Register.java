package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    private User user;
    EditText emailET;
    EditText passET;
    DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        emailET = findViewById(R.id.emailRET);
        passET = findViewById(R.id.passRET);
        Button submitBTN = findViewById(R.id.submitRBTN);




        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitUser();
            }
        });
    }

    private void submitUser() {
        user = new User(emailET.getText().toString().trim(),passET.getText().toString().trim(),null);
        if(!user.getEmail().isEmpty() && !user.getPass().isEmpty())
        {
            db.insertUser(user);
        }
    }


}

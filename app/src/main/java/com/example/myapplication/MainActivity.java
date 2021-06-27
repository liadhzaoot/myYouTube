package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText emailET;
    EditText passET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        emailET = findViewById(R.id.emailET);
        passET = findViewById(R.id.passET);
        Button registerBTN = findViewById(R.id.registerBTN);
        Button submitBTN = findViewById(R.id.submitBTN);

        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser(new User(emailET.getText().toString().trim(),passET.getText().toString().trim(),null));
            }
        });
        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });
    }
    public void openRegister()
    {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    private void checkUser(User loginUser) {
        DatabaseHandler db = new DatabaseHandler(this);
        db.isRowExist(loginUser.getEmail());
        User user = db.getUser(loginUser.getEmail());
        if((user.getPass() != null && user.getEmail() != null)) {
            if (user.checkUser(loginUser.getEmail(), loginUser.getPass())) {
                Intent intent = new Intent(this, FirstScreen.class);
                startActivity(intent);
                finish();
            }
        }

    }

}

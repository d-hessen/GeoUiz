package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {



    SQLiteDatabase db;

    EditText username, password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = openOrCreateDatabase("GeoUiz", Context.MODE_PRIVATE, null);

        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        submit = findViewById(R.id.submitBtnSignUp);

        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String name = username.getText().toString();
        String pswrd = password.getText().toString();
        try {
            db.execSQL("INSERT INTO User VALUES ('"+name+"','"+pswrd+"')");
            finish();
        }
        catch (SQLException e)
        {
            Toast.makeText(this, "User is already exist", Toast.LENGTH_SHORT).show();
        }
    }
}
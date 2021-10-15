package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

/**
 * Class for the login activity.
 */
public class Login extends AppCompatActivity {

    /**
     * onCreate method for the login activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.Login_button).setOnClickListener(this::onClick);

    }

    /**
     * onClick method for the log in button.
     */
    private void onClick(View view) {
        AccountHandler.logIn(this);
    }
}
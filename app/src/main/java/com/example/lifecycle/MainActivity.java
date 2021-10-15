package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;

/**
 * Main Activity for running the application
 */
public class MainActivity extends AppCompatActivity {

    // Declare private variables.
    private CheckBox description;
    private CheckBox windSpeed;
    private CheckBox temperature;
    private Switch cookie;
    private Switch personalize;


    /**
     * Main onCreate method start of the application.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set view.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if the user is logged in.
        AccountHandler.checkLogin(this);

        // Get switches from view.
        cookie = findViewById(R.id.cookieSwitch);
        personalize = findViewById(R.id.personalizeSwitch);

        // Get checkboxes from view.
        description = findViewById(R.id.weatherData1);
        windSpeed = findViewById(R.id.weatherData2);
        temperature = findViewById(R.id.weatherData3);

        // Get button from view.
        Button sendSettings = findViewById(R.id.sendSettingsButton);
        sendSettings.setOnClickListener(this::onClick);
    }

    /**
     * onClick method for the send settings button.
     *
     * saves the settings in a bundle and sends it to the save activity.
     */
    private void onClick(View view) {
        // Set intent.
        Intent intent = new Intent(this, SaveActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Get values.
        String cookiesValue = (cookie.isChecked() ? "On" : "Off");
        String personalizeValue = (personalize.isChecked() ? "On" : "Off");
        String descriptionValue = (description.isChecked() ? description.getText().toString() : "");
        String windSpeedValue = (windSpeed.isChecked() ? windSpeed.getText().toString() : "");
        String temperatureValue = (temperature.isChecked() ? temperature.getText().toString() : "");

        // Generate bundle.
        Bundle bundle = new Bundle();
        bundle.putString("cookies", cookiesValue);
        bundle.putString("personalize", personalizeValue);
        bundle.putString("description", descriptionValue);
        bundle.putString("windSpeed", windSpeedValue);
        bundle.putString("temperature", temperatureValue);

        // Add bundle to intent.
        intent.putExtra("settingBundle", bundle);

        // Switch activity.
        startActivity(intent);
    }

    /**
     * inflate the Menu into view.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    /**
     * Check which menu item was selected and redirect accordingly.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_menu_1:
                return true;
            case R.id.nav_menu_2:
                Intent intent = new Intent(this, SaveActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.nav_menu_3:
                AccountHandler.logOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
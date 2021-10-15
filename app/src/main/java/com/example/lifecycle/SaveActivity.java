package com.example.lifecycle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Save Activity for saving the settings data.
 */
public class SaveActivity extends AppCompatActivity {

    // Declare private variables.
    private Bundle settings;
    private TextView showSettings;
    private SharedPreferences sharedPreferences;

    /**
     * onCreate method for saveActivity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set view.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        // Get bundle from main activity.
        Intent intent = getIntent();
        settings = intent.getBundleExtra("settingBundle");

        sharedPreferences = this.getSharedPreferences("appPreferences", Context.MODE_PRIVATE);
        showSettings = findViewById(R.id.savedSettingsText);
        Button saveSettings = findViewById(R.id.saveButton);
        saveSettings.setOnClickListener(this::Click);

        printSettings();
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
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.nav_menu_2:
                return true;
            case R.id.nav_menu_3:
                AccountHandler.logOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * onClick method for the save changes button.
     *
     * saves the changes in sharedPreferences.
     */
    private void Click(View view) {
        if (settings!=null){
            // Generate String values based on bundle from main activity.
            String weatherData;
            String description = settings.getString("description", "");
            String windSpeed = settings.getString("windSpeed", "");
            String temperature = settings.getString("temperature", "");

            weatherData = (description.isEmpty() ? "" : "\n" +description) +
                    (windSpeed.isEmpty() ? "" : "\n" + windSpeed) +
                    (temperature.isEmpty() ? "" : "\n" + temperature);

            // Store new settings in sharedPreferences.
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("cookieSwitch", settings.getString("cookies", "Off"));
            editor.putString("personalizeSwitch", settings.getString("personalize", "Off"));
            editor.putString("weatherData", (weatherData.isEmpty() ? "none" : weatherData));
            editor.apply();

            // Print the new settings.
            printSettings();
        }
    }

    /**
     * Method for printing the current settings.
     */
    private void printSettings(){
        String settingsText = "Cookies: " +
                sharedPreferences.getString("cookieSwitch", "Off") +
                "\nPersonalized: " +
                sharedPreferences.getString("personalizeSwitch", "Off") +
                "\n\nSelected weather data: " +
                sharedPreferences.getString("weatherData", "none");

        showSettings.setText(settingsText);
    }
}
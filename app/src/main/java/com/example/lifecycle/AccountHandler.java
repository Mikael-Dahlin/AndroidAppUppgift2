package com.example.lifecycle;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Class that handles user login and logout methods.
 */
public class AccountHandler {

    /**
     * Method that checks if the user is logged in.
     */
    public static void checkLogin(Context activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("appPreferences", Context.MODE_PRIVATE);

        if(!sharedPreferences.getBoolean("isLoggedInLifeCycle", false)){
            startNewActivity(activity, Login.class);
        }
    }

    /**
     * Method that handles the log in of a user.
     */
    public static void logIn(Context activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("appPreferences", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedInLifeCycle", true);
        editor.apply();

        startNewActivity(activity, MainActivity.class);
    }

    /**
     * Method that handles log out of the user.
     */
    public static void logOut(Context activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("appPreferences", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedInLifeCycle", false);
        editor.apply();

        startNewActivity(activity, Login.class);
    }

    /**
     * Method that starts a new activity based on provided current activity and target class.
     */
    private static void startNewActivity(Context activity, Class<?> target ){
        Intent intent = new Intent(activity, target);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(activity, intent,null);
    }
}

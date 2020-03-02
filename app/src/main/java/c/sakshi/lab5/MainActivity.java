package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // A (key, value) SharedPreference exists for only one key. Saving a (newKey, newValue) will overwrite (key, value)

    static String usernameKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        usernameKey = "username";
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5",Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernameKey,"").equals("")) {
            goToWelcomeActivity(sharedPreferences.getString(usernameKey,""));
        } else {
            setContentView(R.layout.activity_main);
        }
    }


    public void goToWelcomeActivity(String s) {

        Intent intent = new Intent(this,WelcomeActivity.class);
        intent.putExtra("message",s);
        startActivity(intent);
    }

    public void loginButtonClicked(View view) {
        EditText usernameField = (EditText) findViewById(R.id.usernameField);
        EditText passwordField = (EditText) findViewById(R.id.passwordField);
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();


        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5",Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username",username).apply();
        goToWelcomeActivity(username);

    }
}

package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void goToWelcomeActivity(String s) {
        Intent intent = new Intent(this,WelcomeActivity.class);
        intent.putExtra("message",s);
        startActivity(intent);
    }

    public void loginButtonClicked(View view) {
        EditText aTextField = (EditText) findViewById(R.id.usernameField);
        String s = aTextField.getText().toString();
        Toast.makeText(MainActivity.this,aTextField.getText().toString(),Toast.LENGTH_LONG).show();
        goToWelcomeActivity(s);
    }
}

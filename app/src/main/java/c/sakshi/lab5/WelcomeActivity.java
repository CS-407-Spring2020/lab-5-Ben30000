package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity{

    //TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        /*textView2 = (TextView) findViewById(R.id.WelcomeActivityWelcomeMessage);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message");
        textView2.setText("Welcome "+str+"!");*/
    }
}



package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity{

    TextView welcomeText;
    String username;
    public static ArrayList<Note> notes = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        // Message stating "Welcome username!" where username is a SharedPreference passed in from main activity

        welcomeText = (TextView) findViewById(R.id.WelcomeActivityWelcomeMessage);

        String usernameKey = "username";
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5",Context.MODE_PRIVATE);
        String str = sharedPreferences.getString(usernameKey,"");

        username = str;
        welcomeText.setText("Welcome "+str+"!");


        // Create SQLDatabase instance

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes",Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        // Use readNotes() from dbHelper
        notes = dbHelper.readNotes(username);

        // Display list of users' notes
        ArrayList<String> displayNotes = new ArrayList();
        for (Note note: notes) {
            displayNotes.add(String.format("Title:%s\nDate:%s\n",note.getTitle(),note.getDate()));
        }

        // Display Notes List via ListView, by modifying this list part of the XML dynamically
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,displayNotes);
        ListView listView = (ListView) findViewById(R.id.WelcomeActivityNotesListView);
        listView.setAdapter(adapter);

        // On click listener for each note list item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),NotesActivity.class);
                intent.putExtra("noteid",position);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.welcome_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNoteMenuItem:
                Intent intent1 = new Intent(this,NotesActivity.class);
                startActivity(intent1);
                return true;
            case R.id.logoutMenuItem:
                SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove(MainActivity.usernameKey).apply();
                Intent intent2 = new Intent(this,MainActivity.class);
                startActivity(intent2);
                return true;
            default: return super.onOptionsItemSelected(item);

        }
    }

}



package c.sakshi.lab5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesActivity extends AppCompatActivity {

    int noteid = -1;
    EditText notesField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_activity);

        notesField = (EditText) findViewById(R.id.NotesActivityField);

        /*
        Two possible ways of getting to this activity: From pressing 'Add note' from WelcomeActivity,
        or by pressing a note from from list of notes from WelcomeActivity
         */

        Intent intent = getIntent();
        Integer integer = intent.getIntExtra("message",-1);
        noteid = integer;

        if (noteid != -1) {
            Note note = WelcomeActivity.notes.get(noteid);
            String noteContent = note.getContent();

            notesField.setText(noteContent);
        }

    }

    public void notesActivityButtonClicked(View view) {

        notesField = (EditText) findViewById(R.id.NotesActivityField);
        String aNote = notesField.getText().toString();
        Log.i("message","notesActivityButtonClicked: "+ aNote);

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes",Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        String usernameKey = "username";
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(usernameKey,"");


        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());


        if (noteid == -1) {

            title = "NOTE_" + (WelcomeActivity.notes.size() + 1);
            //Log.i("message","TITLE IS "+title);
            dbHelper.saveNotes(username,title,aNote,date);
        } else {
            title = "NOTE_" + noteid;
            dbHelper.updateNote(username,title,aNote,date);
        }

        // Return to WelcomeActivity
        Intent intent = new Intent(getApplicationContext(),WelcomeActivity.class);
        startActivity(intent);
    }

}

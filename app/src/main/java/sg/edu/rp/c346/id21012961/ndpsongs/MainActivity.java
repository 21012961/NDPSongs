package sg.edu.rp.c346.id21012961.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTitle;
    EditText editSingers;
    EditText editYear;
    Button btnInsert;
    Button btnShowList;
    RadioGroup Stars;
    ArrayList<Song> alSong;
    ArrayAdapter<Song> aaSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTitle = findViewById(R.id.editTitle);
        editSingers = findViewById(R.id.editSinger);
        editYear = findViewById(R.id.editYear);
        Stars = findViewById(R.id.Ratings);
        btnInsert = findViewById(R.id.btnINSERT);
        btnShowList = findViewById(R.id.btnSHOW);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String singers = editSingers.getText().toString();
                int year = Integer.parseInt(String.valueOf(editYear.getText()));
                int stars = 0; // Temp
                DBHelper dbh = new DBHelper(MainActivity.this);


                Toast.makeText(MainActivity.this, "Insert successful",
                        Toast.LENGTH_SHORT).show();
            }
        });


        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                alSong.clear();
                alSong.addAll(dbHelper.getAllSongs());
                aaSong.notifyDataSetChanged();

                Song target = alSong.get(0);

                Intent i = new Intent(MainActivity.this,
                        SongListActivity.class);
                i.putExtra("data", target);
                startActivity(i);
            }
        });

    }
}
package sg.edu.rp.c346.id21012961.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class ModifyActivity extends AppCompatActivity {

    EditText editID,
            editTitle,
            editSingers,
            editYear;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;
    RadioGroup Stars;
    ArrayList<Song> alSong;
    ArrayAdapter<Song> aaSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        editID = findViewById(R.id.editID);
        editTitle = findViewById(R.id.editTitle);
        editSingers = findViewById(R.id.editSinger);
        editYear = findViewById(R.id.editYear);
        btnUpdate = findViewById(R.id.btnINSERT);
        btnDelete = findViewById(R.id.btnSHOW);
        btnCancel = findViewById(R.id.btnCancel);
        Stars = findViewById(R.id.Ratings);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("songData");

        editID.setText(data.get_id());
        editTitle.setText(data.getTitle());
        editSingers.setText(data.getSingers());
        editYear.setText(data.getYear());


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                data.setSong(editTitle.getText().toString(), editSingers.getText().toString(), Integer.parseInt(editYear.getText().toString()), Stars.getCheckedRadioButtonId());
                dbh.updateSong(data);
                dbh.close();

                Intent i = new Intent(ModifyActivity.this,
                        SongListActivity.class);
                startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                dbh.deleteSong(data.get_id());

                Intent i = new Intent(ModifyActivity.this,
                        SongListActivity.class);
                startActivity(i);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ModifyActivity.this,
                        SongListActivity.class);
                startActivity(i);
            }
        });
    }
}

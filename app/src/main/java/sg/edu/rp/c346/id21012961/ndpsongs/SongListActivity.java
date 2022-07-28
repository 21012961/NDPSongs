package sg.edu.rp.c346.id21012961.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SongListActivity extends AppCompatActivity {

    Button btnFilter;
    ListView lvSongs;
    ArrayList<Song> alSong;
    ArrayAdapter<Song> aaSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        btnFilter = findViewById(R.id.btnFilter);
        lvSongs = findViewById(R.id.lvSongs);


        alSong = new ArrayList<Song>();
        aaSong = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, alSong);
        lvSongs.setAdapter(aaSong);

        lvSongs = findViewById(R.id.lvSongs);
        aaSong = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, alSong);
        lvSongs.setAdapter(aaSong);

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song songData = alSong.get(position);
                Intent i = new Intent(SongListActivity.this,
                        ModifyActivity.class);
                i.putExtra("songData", songData);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbHelper = new DBHelper(SongListActivity.this);
        alSong.clear();
        alSong.addAll(dbHelper.getAllSongs());
        aaSong.notifyDataSetChanged();
    }

}
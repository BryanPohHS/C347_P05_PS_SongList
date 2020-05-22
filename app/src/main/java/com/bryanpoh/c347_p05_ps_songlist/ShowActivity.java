package com.bryanpoh.c347_p05_ps_songlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class ShowActivity extends AppCompatActivity{

    Button btnFilter;
    TextView tvYear, tvTitle, tvSinger;
    ListView lv;
    ArrayList<Song> al;
    SongArrayAdapter aa;
    Spinner spinner;

    ArrayList<String> yearsArr ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        btnFilter = findViewById(R.id.btnFilter);
        spinner = findViewById(R.id.spinner);
        lv = findViewById(R.id.listView);
        al = new ArrayList<>();

        DBHelper dbh = new DBHelper(ShowActivity.this);
        al.clear();
        al.addAll(dbh.getAllSongs(""));

        // Retrieve years and add into an array for spinner
        yearsArr = new ArrayList<String>(Arrays.asList(""));
        for(int i = 0; i < al.size(); i++){
            yearsArr.add(al.get(i).getYear());
        }
        dbh.close();
        aa = new SongArrayAdapter(this, R.layout.row, al);
        ArrayAdapter aa = new SongArrayAdapter(getApplicationContext(),R.layout.row,al);
        lv.setAdapter(aa);

        // Set spinner adapter
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, yearsArr);
        spinner.setAdapter(spinnerAdapter);

        // On selected list view item
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Song currSong = al.get(position);

                Intent intent = new Intent(ShowActivity.this, EditActivity.class);
                intent.putExtra("data", currSong);
                startActivity(intent);
            }
        });

        // Filter button for songs = 5 stars
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ShowActivity.this);
                al.clear();
                al.addAll(dbh.getAllSongs("5"));
                dbh.close();
                ArrayAdapter aa = new SongArrayAdapter(getApplicationContext(),R.layout.row,al);
                lv.setAdapter(aa);
            }
        });

        // On select spinner item
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String currYear = spinner.getSelectedItem().toString();
                DBHelper dbh = new DBHelper(ShowActivity.this);
                al.clear();
                al.addAll(dbh.getAllSongsByYear(currYear));
                Log.d("tag", currYear);
                dbh.close();

                ArrayAdapter aa = new SongArrayAdapter(ShowActivity.this,R.layout.row,al);
                lv.setAdapter(aa);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            DBHelper dbh = new DBHelper(ShowActivity.this);
            al.clear();
            al.addAll(dbh.getAllSongs(""));
            dbh.close();
            aa = new SongArrayAdapter(this, R.layout.row, al);
            ArrayAdapter aa = new SongArrayAdapter(getApplicationContext(),R.layout.row,al);
            lv.setAdapter(aa);
        }
    }
}

package com.bryanpoh.c347_p05_ps_songlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity{

    Button btnFilter;
    TextView tvYear, tvTitle, tvSinger;
    ListView lv;
    ArrayList<Song> al;
    SongArrayAdapter aa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        btnFilter = findViewById(R.id.btnFilter);
//        tvTitle = findViewById(R.id.tvTitle);
//        tvSinger = findViewById(R.id.tvSinger);
//        tvYear = findViewById(R.id.tvYear);
        lv = findViewById(R.id.listView);

        al = new ArrayList<>();


        DBHelper dbh = new DBHelper(ShowActivity.this);
        al.clear();
        al.addAll(dbh.getAllSongs(""));
        dbh.close();
        aa = new SongArrayAdapter(this, R.layout.row, al);
        ArrayAdapter aa = new SongArrayAdapter(getApplicationContext(),R.layout.row,al);
        lv.setAdapter(aa);

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

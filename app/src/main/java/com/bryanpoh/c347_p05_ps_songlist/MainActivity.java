package com.bryanpoh.c347_p05_ps_songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShow;
    EditText etTitle, etSinger, etYear;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the variables with UI here
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);
        etTitle = findViewById(R.id.etSongTitle);
        etSinger = findViewById(R.id.etSingerName);
        etYear = findViewById(R.id.etYear);
        rg = findViewById(R.id.radioGroup);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                String year = etYear.getText().toString();

                // Get ID of selected radio button in RG
                int selectedButtonId = rg.getCheckedRadioButtonId();
                // Get the radio button object from id
                RadioButton rb = findViewById(selectedButtonId);

                int stars = Integer.parseInt(rb.getText().toString());

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(title, singer, year, stars);
                dbh.close();

                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });
    }
}
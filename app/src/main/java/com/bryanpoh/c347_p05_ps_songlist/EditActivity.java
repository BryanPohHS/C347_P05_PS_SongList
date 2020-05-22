package com.bryanpoh.c347_p05_ps_songlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    EditText etTitle, etSinger, etYear;
    TextView tvSongID;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //initialize the variables with UI here
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        etTitle = findViewById(R.id.etSongTitle);
        etSinger = findViewById(R.id.etSingerName);
        etYear = findViewById(R.id.etYear);
        tvSongID = findViewById(R.id.tvSongID);
        rg = findViewById(R.id.radioGroup);

        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rb5 = findViewById(R.id.radioButton6);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        tvSongID.setText("ID: " + data.get_id());
        etYear.setText(data.getYear());
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());

        switch(data.getStars()){
            case 1:
                rb1.setChecked(true);
                break;
            case 2:
                rb2.setChecked(true);
                break;
            case 3:
                rb3.setChecked(true);
                break;
            case 4:
                rb4.setChecked(true);
                break;
            case 5:
                rb5.setChecked(true);
                break;
        }



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get radio button value
                int chosenValue = rg.getCheckedRadioButtonId();
                RadioButton chosenRB = findViewById(chosenValue);

                Song newSong = new Song(
                        etTitle.getText().toString(), etSinger.getText().toString(),
                        etYear.getText().toString(), Integer.parseInt(chosenRB.getText().toString())
                );

                // Remember to set id for new song
                newSong.set_id(data.get_id());

                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.updateSong(newSong);
                dbh.close();

                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(data.get_id());
                dbh.close();

                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }
}
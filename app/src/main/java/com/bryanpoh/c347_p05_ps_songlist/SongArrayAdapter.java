package com.bryanpoh.c347_p05_ps_songlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongArrayAdapter extends ArrayAdapter {
    private ArrayList<Song> song;
    private Context context;
    private TextView tvDisplayYear, tvDisplaytitle, tvDisplaySinger;
    private ImageView displayedSongImage;

    ImageView iv1, iv2, iv3, iv4, iv5;


    public SongArrayAdapter(Context context, int resource, ArrayList<Song> objects){
        super(context, resource, objects);
        song = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);

        tvDisplayYear = (TextView)rowView.findViewById(R.id.tvYear);
        tvDisplaytitle = (TextView)rowView.findViewById(R.id.tvTitle);
        tvDisplaySinger = (TextView) rowView.findViewById(R.id.tvSinger);
        displayedSongImage = (ImageView)rowView.findViewById(R.id.imageView) ;
        displayedSongImage.setImageResource(R.drawable.ic_library_music);

        Song currSong = song.get(position);

        // Dont know why settext is swapped for title and author
        tvDisplayYear.setText(currSong.getSingers());
        tvDisplaytitle.setText(currSong.getTitle());
        tvDisplaySinger.setText(currSong.getYear());

        iv1 = rowView.findViewById(R.id.imageView1star);
        iv2 = rowView.findViewById(R.id.imageView2star);
        iv3 = rowView.findViewById(R.id.imageView3star);
        iv4 = rowView.findViewById(R.id.imageView4star);
        iv5 = rowView.findViewById(R.id.imageView5star);
/*        int rating =  songObj.getStars();
        if(rating >= 1){
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
            if(rating >= 2){
                iv2.setImageResource(android.R.drawable.btn_star_big_on);
                if(rating >= 3){
                    iv3.setImageResource(android.R.drawable.btn_star_big_on);
                    if(rating >= 4){
                        iv4.setImageResource(android.R.drawable.btn_star_big_on);
                        if(rating == 5){
                            iv5.setImageResource(android.R.drawable.btn_star_big_on);
                        }

                    }
                }
            }
        }*/

        //Check if the property for starts == 5, if so, "light" up the stars
        if (currSong.getStars() == 5) {
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (currSong.getStars() == 4) {
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (currSong.getStars() == 3) {
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (currSong.getStars() == 2) {
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }

        return rowView;

    }
}
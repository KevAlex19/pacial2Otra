package com.example.pacial2otra;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.SimpleViewHolder> {

    private List<Track> song;

    public MainAdapter(List<Track> songs) {
        this.song = songs;
    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = new TextView(parent.getContext());
        SimpleViewHolder viewHolder = new SimpleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.textView1.setTextSize(1,20);
        holder.textView1.setTypeface(Typeface.DEFAULT_BOLD);
        String dDuration = ""+(Double.parseDouble("" + (Double.parseDouble(song.get(position).getDuration()) / 60)));
        String duration = dDuration.substring(0,1)+":"+dDuration.substring(2);
        holder.textView1.setText("► "+song.get(position).getTitle()+" - "+ song.get(position).getArtist().getName()+" - "+duration.substring(0,3));
        holder.textView1.setWidth(2000);
        holder.textView1.setHeight(180);
    }

    @Override
    public int getItemCount() {
        return song.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView;
        }
    }
}
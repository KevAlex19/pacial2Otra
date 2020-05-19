package com.example.pacial2otra;

import com.google.gson.annotations.SerializedName;

public class Playlist {
    @SerializedName("title")
    private String title;

    @SerializedName("tracks")
    public SongRequest tracks;

    public SongRequest getTracks() {
        return tracks;
    }

    public void setTracks(SongRequest tracks) {
        this.tracks = tracks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

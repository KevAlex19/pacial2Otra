package com.example.pacial2otra;

import com.google.gson.annotations.SerializedName;

public class Track {
    @SerializedName("title")
    String title;

    Artist artist;

    Album album;

    @SerializedName("duration")
    String duration;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}

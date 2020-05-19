package com.example.pacial2otra;

import com.google.gson.annotations.SerializedName;

public class Artist {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }
}

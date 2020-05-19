package com.example.pacial2otra;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface DeezerApi {

    @GET("93489551")
    Call<Playlist> getPlayList();

    @GET("search")
    Call<SongRequest> searchSongs( @Query("q") String q );
}

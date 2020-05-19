package com.example.pacial2otra;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
     public RecyclerView listSongs;

     private int REQUEST=1;

    List<Track> trackList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        trackList = new ArrayList<>();

        FloatingActionButton btn = findViewById(R.id.flBtnAdd);
        listSongs = findViewById(R.id.recyclerView);
        listSongs.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.deezer.com/playlist/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivityForResult(intent,REQUEST);
            }
        });




        listSongs.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        getSongs();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST){
            if (resultCode==RESULT_OK){
                String resultSong = data.getStringExtra("song");
                String resultSongs[] = resultSong.split(";");
                Toast.makeText(getApplicationContext(),"New song add: "+resultSongs[0],Toast.LENGTH_LONG).show();
                Artist artist = new Artist();
                Track songnew = new Track();
                songnew.setTitle(resultSongs[0]);
                artist.setName(resultSongs[1]);
                songnew.setArtist(artist);
                songnew.setDuration(resultSongs[2]);

                trackList.add(songnew);
                MainAdapter adapter = new MainAdapter(trackList);
                listSongs.setAdapter(adapter);

            }
        }
    }

    public void getSongs(){
        DeezerApi api = retrofit.create(DeezerApi.class);

        Call<Playlist> requestCall = api.getPlayList();

        requestCall.enqueue(new Callback<Playlist>() {
            @Override
            public void onResponse(Call<Playlist> call, Response<Playlist> response) {
                if (response.isSuccessful()){
                    Playlist playlist = response.body();

                    for ( Track item: playlist.getTracks().getData()
                    ) {
                        trackList.add(item);
                    }

                    MainAdapter adapter = new MainAdapter(trackList);
                    listSongs.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Playlist> call, Throwable t) {

            }
        });
    }
}

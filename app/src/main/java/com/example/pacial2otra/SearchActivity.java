package com.example.pacial2otra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    Retrofit retrofit;
    Button btnSearch;
    TextView tvSong, tvArtist, tvAlbum, tvDuration;
    EditText txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.setTitle("Search Song");

        tvSong = findViewById(R.id.tvSong);
        tvArtist = findViewById(R.id.tvArtist);
        tvAlbum = findViewById(R.id.tvAlbum);
        tvDuration = findViewById(R.id.tvDuration);
        txtSearch = findViewById(R.id.txtSeach);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.deezer.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void addSong(View view){
        String song = tvSong.getText()+";"+tvArtist.getText()+";"+tvDuration.getText();
        Intent intent = new Intent();
        intent.putExtra("song",song);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void searchSong(View view){
        DeezerApi api = retrofit.create(DeezerApi.class);
        String title = txtSearch.getText().toString();
        Call<SongRequest> songRequestCall = api.searchSongs(title);

        songRequestCall.enqueue(new Callback<SongRequest>() {
            @Override
            public void onResponse(Call<SongRequest> call, Response<SongRequest> response) {
                if (response.isSuccessful()){
                    SongRequest request = response.body();
                    List<Track> listTracks = request.getData();

                    Track track = listTracks.get(0);

                    tvSong.setText(track.getTitle());
                    tvArtist.setText(track.artist.getName());
                    tvAlbum.setText(track.album.getTitle());
                    tvDuration.setText(track.getDuration());
                }
            }

            @Override
            public void onFailure(Call<SongRequest> call, Throwable t) {
                tvSong.setText(t.getMessage());
            }
        });
    }
}

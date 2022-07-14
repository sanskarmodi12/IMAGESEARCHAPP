package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.gridview.Adapters.ImageAdapter;
import com.example.gridview.Interfaces.Myapi;
import com.example.gridview.Models.ImageResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    String url="https://serpapi.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        gridView=(GridView) findViewById(R.id.grid_view_image);
//
//        ImageAdapter imageAdapter=new ImageAdapter(MainActivity.this,new ImageResults());
//
//        gridView.setAdapter(imageAdapter);




        Retrofit retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();

        Myapi myapi=retrofit.create(Myapi.class);

        Call<ImageResults> call=myapi.getImageResults();
        call.enqueue(new Callback<ImageResults>() {
            @Override
            public void onResponse(Call<ImageResults> call, Response<ImageResults> response) {
                ImageResults data=response.body();
                ImageAdapter imageAdapter=new ImageAdapter(MainActivity.this,data);

                gridView.setAdapter(imageAdapter);

            }

            @Override
            public void onFailure(Call<ImageResults> call, Throwable t) {

            }
        });




    }
}
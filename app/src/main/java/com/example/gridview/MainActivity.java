package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.gridview.Adapters.ImageAdapter;
import com.example.gridview.Adapters.ImageAdapterRecyclerView;
import com.example.gridview.Interfaces.Myapi;
import com.example.gridview.Models.ImageResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    GridView gridView;
    String url="https://serpapi.com/";

    RecyclerView recyclerView;
    EditText et_searched_name;
   ImageView searchbt;
    GridLayoutManager gridLayoutManager;
    ImageAdapterRecyclerView imageAdapterRecyclerView;
    ImageResults data;

    public  void onFilter(View v)
    {
        PopupMenu popupMenu=new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.select_col_grid);
        popupMenu.show();
    }


    public void onSearch(View v)
    {
//         gridLayoutManager.setSpanCount(3);


        recyclerView.setLayoutManager(gridLayoutManager);





        Retrofit retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();

        Myapi myapi=retrofit.create(Myapi.class);




        Call<ImageResults> call=myapi.getImageResults(et_searched_name.getText().toString());
        call.enqueue(new Callback<ImageResults>() {
            @Override
            public void onResponse(Call<ImageResults> call, Response<ImageResults> response) {
                 data=response.body();
//                ImageAdapter imageAdapter=new ImageAdapter(MainActivity.this,data);


//                gridView.setAdapter(imageAdapter);
                GridLayoutManager gridLayoutManager1=gridLayoutManager;


                 imageAdapterRecyclerView=new ImageAdapterRecyclerView(MainActivity.this,data,gridLayoutManager1.getSpanCount());
                recyclerView.setAdapter(imageAdapterRecyclerView);


            }

            @Override
            public void onFailure(Call<ImageResults> call, Throwable t) {

            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        gridView=(GridView) findViewById(R.id.grid_view_image);
//
//        ImageAdapter imageAdapter=new ImageAdapter(MainActivity.this,new ImageResults());
//
//        gridView.setAdapter(imageAdapter);
         gridLayoutManager=new GridLayoutManager(this,2);



        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(gridLayoutManager);


        et_searched_name=(EditText) findViewById(R.id.et_searched_name);
        searchbt= (ImageView) findViewById(R.id.searchbt);


//
//
//
//
//        Retrofit retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
//
//        Myapi myapi=retrofit.create(Myapi.class);
//
//
//
//
//        Call<ImageResults> call=myapi.getImageResults();
//        call.enqueue(new Callback<ImageResults>() {
//            @Override
//            public void onResponse(Call<ImageResults> call, Response<ImageResults> response) {
//                ImageResults data=response.body();
////                ImageAdapter imageAdapter=new ImageAdapter(MainActivity.this,data);
//
//
////                gridView.setAdapter(imageAdapter);
//
//                ImageAdapterRecyclerView imageAdapterRecyclerView=new ImageAdapterRecyclerView(MainActivity.this,data);
//                recyclerView.setAdapter(imageAdapterRecyclerView);
//
//
//            }
//
//            @Override
//            public void onFailure(Call<ImageResults> call, Throwable t) {
//
//            }
//        });




    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int spanCount=2;
        switch(item.getItemId())
        {
            case R.id.size_1:
                spanCount=1;
//                imageAdapterRecyclerView.onCreateViewHolder(recyclerView,1);
                break;
            case R.id.size_2:
                spanCount=2;
//                imageAdapterRecyclerView.onCreateViewHolder(recyclerView,1);
                break;
            case R.id.size_3:
                spanCount=3;
//                imageAdapterRecyclerView.onCreateViewHolder(recyclerView,1);
                break;
            case R.id.size_4:
                spanCount=4;
//                imageAdapterRecyclerView.onCreateViewHolder(recyclerView,1);
                break;





        }


        gridLayoutManager.setSpanCount(spanCount);
        imageAdapterRecyclerView=new ImageAdapterRecyclerView(MainActivity.this,data,spanCount);
        recyclerView.setAdapter(imageAdapterRecyclerView);

        return true;
    }
}
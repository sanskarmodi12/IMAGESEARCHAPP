package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gridview.Adapters.ImageAdapter;
import com.example.gridview.Adapters.ImageAdapterRecyclerView;
import com.example.gridview.Interfaces.Myapi;
import com.example.gridview.LocalDb.CacheImageManager;
import com.example.gridview.Models.ImageModel;
import com.example.gridview.Models.ImageResults;

import java.util.ArrayList;
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

    ProgressBar progressBar;
  List<ImageModel>imageModels;
    static int page;
    String searchedName;
    private boolean isLoading = false;

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
        page=0;

        searchedName=et_searched_name.getText().toString();
        if(searchedName.equals(""))
        {
            Toast.makeText(MainActivity.this,"PLs enter the item to  be searched",Toast.LENGTH_SHORT).show();
            return ;
        }
        recyclerView.setLayoutManager(gridLayoutManager);
        imageModels=new ArrayList<>();
        getData(0);

    }



    private void getData(int page)  {
//        progressBar.setVisibility(View.INVISIBLE);

        //check for cache first
        //get the bitmap values as list of imagemodels from localDb



        List<ImageModel>imageModels2= CacheImageManager.getImage(MainActivity.this,searchedName,page);

        if((imageModels2!=null)&&imageModels2.size()!=0)
        {

            imageModels.addAll(imageModels2);

//            GridLayoutManager gridLayoutManager1=gridLayoutManager;


            imageAdapterRecyclerView=new ImageAdapterRecyclerView(MainActivity.this,imageModels,gridLayoutManager.getSpanCount());
            recyclerView.setAdapter(imageAdapterRecyclerView);
            Toast.makeText(MainActivity.this,"data from local",Toast.LENGTH_SHORT).show();
            isLoading=false;



        }
        // data from api
        else {

            Toast.makeText(MainActivity.this,"Loading "+searchedName+"images from Internet",Toast.LENGTH_SHORT).show();

            Call<ImageResults> call = RetrofitClient.getInstance()
                    .getApi().getImageResults(searchedName, page);
            call.enqueue(new Callback<ImageResults>() {
                @Override
                public void onResponse(Call<ImageResults> call, Response<ImageResults> response) {

                    data = response.body();
                    ImageResults imageResults = data;
                    List<ImageModel> imageModels2 = imageResults.getImageResults();
                    if (imageModels == null || imageModels2 == null) {
                        Log.i("List ended", "ends");
                        return;
                    }
                    imageModels.addAll(imageModels2);
                    Log.i("sizeusa", String.valueOf(imageModels.size()));
                    CacheImageManager.putImageData(MainActivity.this, imageModels2, searchedName, page);


//                    GridLayoutManager gridLayoutManager1 = gridLayoutManager;


                    imageAdapterRecyclerView = new ImageAdapterRecyclerView(MainActivity.this, imageModels, gridLayoutManager.getSpanCount());
                    recyclerView.setAdapter(imageAdapterRecyclerView);
                    isLoading=false;



                }

                @Override
                public void onFailure(Call<ImageResults> call, Throwable t) {

                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageModels = new ArrayList<ImageModel>();


        gridLayoutManager = new GridLayoutManager(this, 2);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(gridLayoutManager);


        et_searched_name = (EditText) findViewById(R.id.et_searched_name);
        searchbt = (ImageView) findViewById(R.id.searchbt);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
//        progressBar.setVisibility(View.INVISIBLE);



        recyclerView.addOnScrollListener(new PaginationScrollListener(gridLayoutManager,searchedName) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
              page += 1;
                getData(page);
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }


        });



    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int spanCount=2;
        if(data==null)
            data=new ImageResults();

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

        imageAdapterRecyclerView=new ImageAdapterRecyclerView(MainActivity.this,imageModels,spanCount);
        Log.i("imgmodle", String.valueOf(imageModels));
        recyclerView.setAdapter(imageAdapterRecyclerView);

        return true;
    }
}
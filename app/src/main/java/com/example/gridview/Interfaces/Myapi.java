package com.example.gridview.Interfaces;

import com.example.gridview.Models.ImageResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Myapi {

    @GET("search.json?tbm=isch&api_key=517896c797e4203aaf337f5ec4b1355b622ffb881aa88cdd029023a0556ae65b")
    Call<ImageResults> getImageResults(@Query("q") String name,@Query("ijn") int page);


}

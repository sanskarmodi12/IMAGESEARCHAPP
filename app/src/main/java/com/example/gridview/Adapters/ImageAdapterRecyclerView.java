package com.example.gridview.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gridview.DisplayImage;
import com.example.gridview.LocalDb.CacheImageManager;
import com.example.gridview.Models.BitMapTransfer;
import com.example.gridview.Models.ImageModel;
import com.example.gridview.Models.ImageResults;
import com.example.gridview.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.Random;

import retrofit2.http.GET;

public class ImageAdapterRecyclerView extends RecyclerView.Adapter<ImageAdapterRecyclerView.viewHolder> {
    private Context context;
    List<ImageModel> imageModels;
    int spanCount;

//    int pos;
//    String searchedName;
//    int page;


    public ImageAdapterRecyclerView(Context context, List<ImageModel> imageModels, int spanCount) {
        this.context = context;
        this.imageModels = imageModels;
        this.spanCount=spanCount;
//        this.page=page;
//        this.searchedName=searchedName;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        context=parent.getContext();
        View view=  LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_image,parent,false);
        int w=parent.getMeasuredWidth();



        view.getLayoutParams().height = parent.getMeasuredWidth() / spanCount;
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String imageUrl=imageModels.get(position).getImageUrl();
        Bitmap imageBitMap=imageModels.get(position).getBitMap();
//        pos=imageModels.get(position).getPosition();
        Log.i("pos1233", "onbin");




        ImageView imageView= holder.imageView;
        if(imageUrl!=null){
        Glide.with(context).load(imageModels.get(position).getImageUrl()).into(imageView);
//            MyImageTask task=new MyImageTask();
//            task.execute(imageUrl);

        }
        else
        {
            if(imageBitMap!=null)
            imageView.setImageBitmap(imageBitMap);
            else
            {
                Glide.with(context).load(R.drawable.ic_launcher_background).into(imageView);
            }

        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DisplayImage.class);
                intent.putExtra("imageUrl",imageUrl);
                BitMapTransfer.bitmap=imageBitMap;
//                intent.putExtra("imageBit",imageBitMap)
//                Intent intent;
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                if(imageBitMap!=null){
//                imageBitMap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                byte[] byteArray = stream.toByteArray();
//                     intent = new Intent(context, DisplayImage.class);
//
//                intent.putExtra("keyobj", byteArray);
//                intent.putExtra("keyobj1","true");
//                    intent.putExtra("imageUrl",imageUrl);
//                }
//                else
//                {
//                     intent = new Intent(context, DisplayImage.class);
//                    intent.putExtra("keyobj1","false");
//                    intent.putExtra("imageUrl",imageUrl);
//                }


                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) context, imageView, ViewCompat.getTransitionName(imageView));
               context.startActivity(intent, options.toBundle());

            }
        });


    }

    @Override
    public int getItemCount() {
        if(imageModels==null)
            return 0;
        return imageModels.size();
    }


  public  class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.card_imag);

        }
    }


//    class MyImageTask extends AsyncTask<String,Void,Bitmap> {
//
//
//
//
//        @Override
//        protected Bitmap doInBackground(String... strings) {
//            Bitmap bitmap = null;
//
//
//            String imageurl=strings[0];
//
//            InputStream inputStream=null;
//
//            try {
//                URL imageUrl=new URL(imageurl);
//                inputStream = (InputStream) imageUrl.getContent();
//                bitmap= BitmapFactory.decodeStream(inputStream);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            finally {
//                if (inputStream != null) {
//                    try {
//                        inputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            int sleepTime=random.nextInt(400);
//            try {
//                Thread.sleep(sleepTime);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Log.d("downloaded", "doInBackground: Image downloaded: "+imageurl);
//
//            return bitmap;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            super.onPostExecute(bitmap);
//            Log.i("bitval", String.valueOf(bitmap));
//
//            CacheImageManager.putImageData(context,pos,searchedName,page,bitmap);
//        }
//    }

}



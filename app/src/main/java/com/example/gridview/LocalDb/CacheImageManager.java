package com.example.gridview.LocalDb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.gridview.Models.ImageModel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


public class CacheImageManager {
    public static List<ImageModel> getImage(Context context, String searched, int page) {
        //cache->Mango->Page1->m1.png,m2.png
        // use for loop for getting all images in a given page
        List<ImageModel> imageModels;
        imageModels = new ArrayList<>();
        File dir= context.getFilesDir();
//
        File dir2=new File(dir,searched);
        if(!dir2.exists())
            return imageModels;

        File dir3=new File(dir2,searched+"page"+String.valueOf(page));





        File[] files = dir3.listFiles();
        if (files != null) {

            for (int i = 0; i < files.length; ++i) {
                File file = files[i];

                    Bitmap bitmap = null;

                    try {
                        bitmap = BitmapFactory.decodeStream(new FileInputStream(file));



                        imageModels.add(new ImageModel(null,bitmap,0));


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


            }


        }
        return imageModels;


    }


    public static void putImageData(Context context,List<ImageModel>imageModels, String searchedName, int page) {
        String dir4 = context.getCacheDir()+ "/" + searchedName ;

        File dir= context.getFilesDir();
//
        File dir2=new File(dir,searchedName);
        if(!dir2.exists())
        dir2.mkdir();
        File dir3=new File(dir2,searchedName+"page"+String.valueOf(page));
        if(!dir3.exists())
        dir3.mkdir();




        for(int i=0;i<imageModels.size()&&i<10;i++)
        {
            String fileName=searchedName+imageModels.get(i).getPosition()+".png";
      //      Picasso.with(context).load(imageModels.get(i).getImageUrl()).into(picassoImageTarget(context,searchedName+"page"+String.valueOf(page) , fileName));
//            File myImageFile = new File(context.getFilesDir(), fileName);
           new ImageDownloader(context,imageModels.get(i).getImageUrl(),fileName,dir3).execute();

            Log.i("bitmap", String.valueOf(new StoreBitMap().execute(imageModels.get(i).getImageUrl())));



        }






    }









}

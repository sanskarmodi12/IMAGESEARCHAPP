package com.example.gridview.LocalDb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;


class StoreBitMap extends AsyncTask<String, Void,Bitmap> {

    protected Bitmap doInBackground(String... iurl) {
        Bitmap image = null;
        try {
            URL url = new URL(iurl[0]);
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            return image;
        } catch(IOException e) {
            System.out.println(e);
        }
        return image;
    }

    protected void onPostExecute(Bitmap result) {
        // TODO: do something with the feed


    }


}
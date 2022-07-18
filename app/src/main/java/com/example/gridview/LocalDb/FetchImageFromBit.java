package com.example.gridview.LocalDb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class FetchImageFromBit extends AsyncTask<File, Void, Bitmap>

    {

        protected Bitmap doInBackground(File... f) {
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(new FileInputStream(f[0]));
                return  bitmap;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;


        }

        protected void onPostExecute(Void result) {
        // TODO: do something with the feed
    }
    }

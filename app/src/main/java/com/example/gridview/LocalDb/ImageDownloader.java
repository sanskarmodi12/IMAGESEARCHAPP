package com.example.gridview.LocalDb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

class ImageDownloader extends AsyncTask<String, Void, File> {

    String imageurl;
    String name;
    Context ctx;
    File cacheDir;

    public ImageDownloader(Context context, String url, String fileName,File cacheDir) {

        this.imageurl = url;
        this.name = fileName;
        this.ctx = context;
        this.cacheDir=cacheDir;


    }
    @Override
    protected File doInBackground(String... urls) {

        Bitmap mIcon;

//        File cacheDir = ctx.getCacheDir();
        File f = new File(cacheDir, name);

        try {
            InputStream in = new java.net.URL(imageurl).openStream();
            mIcon = BitmapFactory.decodeStream(in);

            try {
                FileOutputStream out = new FileOutputStream(
                        f);
                mIcon.compress(
                        Bitmap.CompressFormat.JPEG,
                        100, out);
                out.flush();
                out.close();
                return f;

            } catch (FileNotFoundException e) {

                return null;
            } catch (IOException e) {

                return null;
            }

        } catch (Exception e) {

            return null;
        }


    }
    @Override
    protected void onPostExecute(File result) {

        super.onPostExecute(result);
        Toast.makeText(ctx," connected " + name, Toast.LENGTH_LONG).show();

    }


}


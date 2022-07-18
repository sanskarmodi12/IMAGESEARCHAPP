package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.gridview.Models.BitMapTransfer;
import com.example.gridview.Models.ImageModel;
// DISPLAY IMAGE IN SHARED TRANSITION
public class DisplayImage extends AppCompatActivity {
    ImageView imageView;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);
        imageView=(ImageView)findViewById(R.id.display_image);



        Intent intent=getIntent();
        String url=intent.getStringExtra("imageUrl");
//        String keyobj1=intent.getStringExtra("keyobj1");

        if(url!=null)
        Glide.with(DisplayImage.this).load(url).into(imageView);
        else
        {
            if(BitMapTransfer.bitmap!=null)
            {
//                byte[] byteArray = getIntent().getByteArrayExtra("keyobj");
//                Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                imageView.setImageBitmap(BitMapTransfer.bitmap);

            }
            else
            {
                Glide.with(DisplayImage.this).load(R.drawable.ic_launcher_background).into(imageView);
            }

        }


    }
}
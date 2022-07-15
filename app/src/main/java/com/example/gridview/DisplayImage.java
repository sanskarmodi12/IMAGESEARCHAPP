package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

public class DisplayImage extends AppCompatActivity {
    ImageView imageView;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);
        imageView=(ImageView)findViewById(R.id.display_image);


//        relativeLayout=(RelativeLayout)findViewById(R.id.rl_display);
//
//
//        int w=relativeLayout.getMeasuredWidth();

//        int w=imageView.getDrawable().getIntrinsicWidth();

//        Log.i("w", String.valueOf(w));
//        imageView.getLayoutParams().height=imageView.getMeasuredWidth();
//        imageView.setLayoutParams(params);


        Intent intent=getIntent();
        String url=intent.getStringExtra("imageUrl");
        Glide.with(DisplayImage.this).load(url).into(imageView);


    }
}
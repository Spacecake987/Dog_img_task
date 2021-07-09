package com.junglist963.task_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageActivity extends AppCompatActivity {

    private ImageView imgLarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imgLarge = findViewById(R.id.imgLarge);

        Intent i = getIntent();
        String imgUrl = i.getStringExtra("image");
        Glide.with(this).asBitmap().load(imgUrl).into(imgLarge);

    }
}
package com.hello.learning.malluapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;


public class MainActivity extends Activity {

    Button loadPictureButton;
    Button takePictureButton;
    ImageView image;

    Bitmap imageBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadPictureButton = (Button) findViewById(R.id.choosePictureButton);
        takePictureButton = (Button) findViewById(R.id.takePictureButton);
        image = (ImageView) findViewById(R.id.imageView);

        loadPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            Uri imagePath = data.getData();
            Log.d("path of image: ", imagePath.toString());

            try {
                imageBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imagePath));
                image.setImageBitmap(imageBitmap); // display the pic
            }

            catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }


    }




}

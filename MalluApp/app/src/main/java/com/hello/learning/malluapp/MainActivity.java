package com.hello.learning.malluapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.*;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.DragEvent;
import android.view.View;
import android.view.View.*;
import android.view.View.OnDragListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;


public class MainActivity extends Activity {

    DrawingClass d;

    Button loadPictureButton;
    Button takePictureButton;
    ImageView image;

    Bitmap imageBitmap; // NOTE: THIS IS IMMUTABLE
    Bitmap drawableBitmap; // THIS IS NOT, USABLE TO DRAW ON

    Canvas imageCanvas; // used for drawing on
    RectF faceRectangle; // holds coordinate of rect with face in it


    float upperX = -1; // these variables are init to -1 so that they can be SENTINELS
    float upperY = -1;  // ACTUALLY THEY WILL BE SENTINELS NO LONGER

    float lowerX = -1;
    float lowerY = -1;

    boolean tappedTwice = false;


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
    public boolean onTouchEvent(MotionEvent e) {


        //tappedTwice = !tappedTwice;

        float touchX = e.getX();
        float touchY = e.getY();



        Log.d("Touch: ", touchX + "," + touchY);


        // get the current view so u can draw
        View view = getWindow().getDecorView().findViewById(android.R.id.content);


        d = new DrawingClass(this);



        if (imageCanvas != null) {

            // view.draw(imageCanvas);

            Paint yellowPaint = new Paint();
            yellowPaint.setColor(Color.YELLOW);

            // try drawing directly?
            imageCanvas.drawColor(Color.BLUE);

            Log.d("imageCanvas: ", "not null");
         //  d.draw(imageCanvas);
//            d.invalidate();
        }

        return true;


    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            Uri imagePath = data.getData();
            Log.d("path of image: ", imagePath.toString());

            try {

                //initialize the immutable bitmap
                imageBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imagePath));
                image.setImageBitmap(imageBitmap); // display the pic

                // initialize the mutable bitmap
                drawableBitmap = imageBitmap.copy(Bitmap.Config.ARGB_8888, true);


                // initialize the canvas that we will draw on
                imageCanvas = new Canvas(drawableBitmap);
                Log.d("imageCanvas:", " successfuly created");
            }

            catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }


    }




}

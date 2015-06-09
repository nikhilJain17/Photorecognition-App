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


        tappedTwice = !tappedTwice;

        float touchX = e.getX();
        float touchY = e.getY();

        //Log.d("Touch: ", touchX + "," + touchY);

        // get the current view so u can draw
        View view = getWindow().getDecorView().findViewById(android.R.id.content);
        DrawingClass d = new DrawingClass(this);
        d.invalidate();


        return true;


    }




    public class DrawingClass extends View {

        public DrawingClass (Context context) {
            super(context);
        }


        @Override
         public void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Log.d("Drawing: ", "Drawing");

            canvas.drawARGB(100, 100, 100, 100);

        }


    }






















    // just use onDraw
    public void drawPoint(Canvas canvas, float x, float y) {

        Paint black = new Paint();
        black.setARGB(255, 100, 10, 70);

        canvas.drawARGB(100, 100, 100, 100);

//        canvas.drawPoint(x, y, black);

    }


    // just use onDraw
    public void drawRectangle(Canvas canvas) {


        float left = 0, right = 0, top = 0, bottom = 0;

        // range-checking to correctly assign left, right, up and down coordinates for rectf
        if (upperX < lowerX) {
            left = upperX;
            right = lowerX;
        }

        else if (lowerX < upperX) {
            left = lowerX;
            right = lowerX;
        }

        if (upperY < lowerY) {
            bottom = upperY;
            top = lowerY;
        }

        else if (lowerY < upperY) {
            bottom = lowerY;
            top = upperY;
        }

        faceRectangle = new RectF(left, top, right, bottom);

        Paint black = new Paint();
        black.setARGB(24, 0, 0, 0);

        canvas.drawRect(faceRectangle, black);

        Log.d("Draw: ", "Drew rectangle");

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
            }

            catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }


    }




}

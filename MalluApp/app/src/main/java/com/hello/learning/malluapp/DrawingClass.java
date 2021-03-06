package com.hello.learning.malluapp;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class DrawingClass extends View {


    Paint bluePaint;


    public DrawingClass (Context context) {
        super(context);

        setWillNotDraw(false);

        bluePaint = new Paint();
        bluePaint.setColor(Color.BLUE);


        }


        @Override
         public void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Toast.makeText(getContext(), "Drawing", Toast.LENGTH_SHORT).show();

            Log.d("Drawing: ", "Drawing");


            canvas.drawCircle(100, 100, 100, bluePaint);
            canvas.drawPoint(canvas.getWidth() / 2, canvas.getHeight() / 2, bluePaint);

            Log.d("Canvas Height/Width: ", canvas.getHeight() + " " + canvas.getWidth());



        }


    }

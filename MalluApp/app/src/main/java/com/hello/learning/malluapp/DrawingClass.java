package com.hello.learning.malluapp;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DrawingClass extends View {


    Paint paint;


    public DrawingClass (Context context) {
        super(context);

        paint = new Paint();
        paint.setARGB(0, 0, 0, 0);

        }


        @Override
         public void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Toast.makeText(getContext(), "Drawing", Toast.LENGTH_SHORT).show();

            Log.d("Drawing: ", "Drawing");


            canvas.drawCircle(100, 100, 100, paint);



        }


    }

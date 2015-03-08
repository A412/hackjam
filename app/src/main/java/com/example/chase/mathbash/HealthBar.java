package com.example.chase.mathbash;

/**
 * Created by Chase on 3/7/15.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class HealthBar extends View {

        Paint paint = new Paint();
        private int height;
        private int width;
        private int health=100;


        public HealthBar(Context context) {
            super(context);

        }

        public void setHeight(int h){
            height=h;
        }

        public void setWidth(int w){
            width=w;
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(3);
            canvas.drawRect(height/2, 0,width, 80, paint);
            paint.setStrokeWidth(1);
            paint.setColor(Color.RED);
            canvas.drawRect(height/2, 0, width, 80, paint );
            paint.setColor(Color.GREEN);
            canvas.drawRect(height/2 ,0, (width*health)/100, 80, paint );
        }

    public void setHealth(int h){
        health=h;
        postInvalidate();
    }

    }


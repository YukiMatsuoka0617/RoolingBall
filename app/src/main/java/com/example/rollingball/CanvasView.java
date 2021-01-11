package com.example.rollingball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CanvasView extends View {

    private final Paint paint;
    private float x = 500;
    private float y = 1000;
    private float preX;
    private float preY;
    private int radius = 100;

    public CanvasView(Context context) {
        super(context);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.argb(255, 255, 255, 255));

        setCircle(canvas);
    }

    void setCircle(Canvas canvas){
        paint.setColor(Color.argb(255, 0, 0, 255));
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(x, y, radius, paint);
    }

    public void setPosition(float sensorX, float sensorY) {

        float ax = -sensorX/10;
        float ay = sensorY/10;

        x += preX + ax;
        preX += ax;

        y += preY + ay;
        preY += ay;

        if(radius > x)
            x = radius;

        if(x > getWidth() - radius)
            x = getWidth() - radius;

        if( radius > y)
            y = radius;

        if(y > getHeight() - radius)
            y = getHeight() - radius;

        invalidate();
    }
}

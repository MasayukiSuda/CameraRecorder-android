package com.daasuu.sample.widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.daasuu.camerarecorder.egl.filter.GlOverlayFilter;

public class GlBitmapOverlaySample extends GlOverlayFilter {

    private Bitmap bitmap;

    public GlBitmapOverlaySample(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    protected void drawCanvas(Canvas canvas) {
        if (bitmap != null && !bitmap.isRecycled()) {
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
    }
}

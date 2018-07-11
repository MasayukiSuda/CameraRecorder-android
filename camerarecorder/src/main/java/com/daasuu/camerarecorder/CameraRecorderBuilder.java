package com.daasuu.camerarecorder;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.camera2.CameraManager;
import android.opengl.GLSurfaceView;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Surface;

import com.daasuu.camerarecorder.egl.filter.GlFilter;

/**
 * Created by sudamasayuki on 2018/03/14.
 */

public class CameraRecorderBuilder {


    private GLSurfaceView glSurfaceView;

    private LensFacing lensFacing = LensFacing.FRONT;
    private Resources resources;
    private Activity activity;
    private final CameraRecordListener cameraRecordListener;
    private int fileWidth = 720;
    private int fileHeight = 1280;
    private boolean flipVertical = false;
    private boolean flipHorizontal = false;
    private boolean mute = false;
    private boolean recordNoFilter = false;
    private int cameraWidth = 1280;
    private int cameraHeight = 720;
    private GlFilter glFilter;

    public CameraRecorderBuilder(@NonNull Activity activity, @NonNull CameraRecordListener cameraRecordListener) {
        this.activity = activity;
        this.cameraRecordListener = cameraRecordListener;
        this.resources = activity.getResources();
    }

    public CameraRecorderBuilder filter(@NonNull GlFilter glFilter) {
        this.glFilter = glFilter;
        return this;
    }

    public CameraRecorderBuilder preview(@NonNull GLSurfaceView glSurfaceView) {
        this.glSurfaceView = glSurfaceView;
        return this;
    }

    public CameraRecorderBuilder videoSize(int fileWidth, int fileHeight) {
        this.fileWidth = fileWidth;
        this.fileHeight = fileHeight;
        return this;
    }

    public CameraRecorderBuilder cameraSize(int cameraWidth, int cameraHeight) {
        this.cameraWidth = cameraWidth;
        this.cameraHeight = cameraHeight;
        return this;
    }

    public CameraRecorderBuilder lensFacing(LensFacing lensFacing) {
        this.lensFacing = lensFacing;
        return this;
    }

    public CameraRecorderBuilder flipHorizontal(boolean flip) {
        this.flipHorizontal = flip;
        return this;
    }

    public CameraRecorderBuilder flipVertical(boolean flip) {
        this.flipVertical = flip;
        return this;
    }

    public CameraRecorderBuilder mute(boolean mute) {
        this.mute = mute;
        return this;
    }

    public CameraRecorderBuilder recordNoFilter(boolean recordNoFilter) {
        this.recordNoFilter = recordNoFilter;
        return this;
    }

    public CameraRecorder build() {
        if (this.glSurfaceView == null) {
            throw new IllegalArgumentException("glSurfaceView and windowManager, multiVideoEffects is NonNull !!");
        }

        CameraManager cameraManager = (CameraManager) activity.getSystemService(Context.CAMERA_SERVICE);
        boolean isLandscapeDevice = resources.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        int degrees = 0;
        if (isLandscapeDevice) {
            int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
            Log.d("CameraRecorder", "Surface.ROTATION_90 = " + Surface.ROTATION_90 + " rotation = " + rotation);
            degrees = 90 * (rotation - 2);
        }

        CameraRecorder cameraRecorder = new CameraRecorder(
                cameraRecordListener,
                glSurfaceView,
                fileWidth,
                fileHeight,
                cameraWidth,
                cameraHeight,
                lensFacing,
                flipHorizontal,
                flipVertical,
                mute,
                cameraManager,
                isLandscapeDevice,
                degrees,
                recordNoFilter
        );

        cameraRecorder.setFilter(glFilter);
        activity = null;
        resources = null;
        return cameraRecorder;
    }

}

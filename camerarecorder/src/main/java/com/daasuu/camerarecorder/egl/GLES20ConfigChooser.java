package com.daasuu.camerarecorder.egl;

/**
 * Created by sudamasayuki on 2018/03/14.
 */

public class GLES20ConfigChooser extends DefaultConfigChooser {

    private static final int EGL_CONTEXT_CLIENT_VERSION = 2;

    public GLES20ConfigChooser(final boolean withDepthBuffer) {
        super(withDepthBuffer, EGL_CONTEXT_CLIENT_VERSION);
    }

}

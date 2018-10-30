
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
<img src="https://img.shields.io/badge/license-MIT-green.svg?style=flat">
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

<img src="https://github.com/MasayukiSuda/CameraRecorder-android/blob/master/logo/camerarecorder-logotype3.png" width="40%"><br>
Video Recording with Camera2 and apply video filter.<br>


## Gradle
Step 1. Add the JitPack repository to your build file
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Step 2. Add the dependency
```groovy
dependencies {
	 implementation 'com.github.MasayukiSuda:CameraRecorder-android:v0.1.5'
}
```

## Usage
SetUp on onResume method.
```JAVA  
  sampleGLView = new GLSurfaceView(getApplicationContext());
  FrameLayout frameLayout = findViewById(R.id.wrap_view);
  frameLayout.addView(sampleGLView);
  
  cameraRecorder = new CameraRecorderBuilder(activity, sampleGLView)
    .lensFacing(LensFacing.BACK)
    .build();
```
Release on onPause method.
```JAVA
  sampleGLView.onPause();      

  cameraRecorder.stop();
  cameraRecorder.release();
  cameraRecorder = null;

  ((FrameLayout) findViewById(R.id.wrap_view)).removeView(sampleGLView);
  sampleGLView = null;
```
Start and Stop Video record.
```JAVA
  // record start.
  cameraRecorder.start(filepath);
  // record stop.
  cameraRecorder.stop();
```
This filter is OpenGL Shaders to apply effects on camera preview. Custom filters can be created by inheriting <a href="https://github.com/MasayukiSuda/CameraRecorder-android/blob/master/camerarecorder/src/main/java/com/daasuu/camerarecorder/egl/filter/GlFilter.java">GlFilter.java</a>. , default GlFilter(No filter). Filters is <a href="https://github.com/MasayukiSuda/CameraRecorder-android/tree/master/camerarecorder/src/main/java/com/daasuu/camerarecorder/egl/filter">here</a>. 
```JAVA
  cameraRecorder.setFilter(GlFilter);
```
Other methods.
```JAVA
  // if flash enable, turn on or off camera flash. 
  cameraRecorder.switchFlashMode();
  // autofocus change.
  cameraRecorder.changeAutoFocus();
  // set focus point at manual.
  cameraRecorder.changeManualFocusPoint(float eventX, float eventY, int viewWidth, int viewHeight); 
  // scale camera preview
  cameraRecorder.setGestureScale(float scale);
```

## Builder Method
| method | description |
|:---|:---|
| cameraRecordListener | onGetFlashSupport, onRecordComplete, onError, and onCameraThreadFinish. Detail is <a href="https://github.com/MasayukiSuda/CameraRecorder-android/blob/f89aa300178ca61f7e4a30ad9bcdc48ab14412ba/camerarecorder/src/main/java/com/daasuu/camerarecorder/CameraRecordListener.java">here</a>. |
| filter | This filter is OpenGL Shaders to apply effects on camera preview. Custom filters can be created by inheriting <a href="https://github.com/MasayukiSuda/CameraRecorder-android/blob/master/camerarecorder/src/main/java/com/daasuu/camerarecorder/egl/filter/GlFilter.java">GlFilter.java</a>. , default GlFilter(No filter). Filters is <a href="https://github.com/MasayukiSuda/CameraRecorder-android/tree/master/camerarecorder/src/main/java/com/daasuu/camerarecorder/egl/filter">here</a>. |
| videoSize | Resolution of the movie, default `width=720, height=1280`. |
| cameraSize | Preview size. |
| lensFacing | Select back or front camera. Default `LensFacing.FRONT`.  |
| flipHorizontal | Flip Horizontal on recorded video. Default `flipHorizontal = false`. |
| flipVertical | Flip Vertical on recorded video. Default `flipVertical = false`. |
| mute | Mute audio track on recorded video. Default `mute = false`. |
| recordNoFilter | No Filter on recorded video although preview apply a filter. Default `recordNoFilter = false`. |


## References And Special Thanks to
* [AudioVideoRecordingSample](https://github.com/saki4510t/AudioVideoRecordingSample)
* [android-gpuimage](https://github.com/CyberAgent/android-gpuimage)
* [grafika](https://github.com/google/grafika)




## License

[MIT License](https://github.com/MasayukiSuda/CameraRecorder-android/blob/master/LICENSE)

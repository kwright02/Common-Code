package org.pattonvillerobotics.commoncode.robotclasses.opencv;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.pattonvillerobotics.commoncode.robotclasses.opencv.util.PhoneOrientation;

public final class ImageProcessor {

    private static final String TAG = ImageProcessor.class.getSimpleName();

    private static boolean initialized;

    private ImageProcessor() {
    }

    public static boolean initOpenCV(HardwareMap hardwareMap, LinearOpMode opMode) {
        return initOpenCV(hardwareMap.appContext, opMode);
    }

    public static boolean initOpenCV(Context context, LinearOpMode opMode) {
        final BaseLoaderCallback baseLoaderCallback;

        try {
            baseLoaderCallback = new BaseLoaderCallback(context) {
                @Override
                public void onManagerConnected(int status) {
                    switch (status) {
                        case LoaderCallbackInterface.SUCCESS:
                            Log.i(TAG, "OpenCV Loaded Successfully!");
                            initialized = true;
                            super.onManagerConnected(status);
                            break;
                        default:
                            super.onManagerConnected(status);
                            break;
                    }
                }
            };
        } catch (NullPointerException e) {
            Log.e(TAG, "Failed to load OpenCV app, it can be found on the Google Play store.", e);
            return false;
        }

        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            boolean success = OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, context, baseLoaderCallback);
            if (!success) {
                Log.e(TAG, "Asynchronous initialization failed!");
                Log.e(TAG, "Could not initialize OpenCV! Did you install the OpenCV Manager from the Google Play Store?");
            } else {
                Log.d(TAG, "Asynchronous initialization succeeded!");
            }
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            if (baseLoaderCallback != null)
                baseLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
            else {
                Log.e(TAG, "Failed to load OpenCV from package!");
                return false;
            }
        }

        while (!initialized && opMode.opModeIsActive()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return initialized;
    }

    public static boolean isInitialized() {
        return initialized;
    }

    /**
     * Converts the Bitmap to a Mat and then rotates the image based off of the phone's orientation.
     *
     * @param bitmap      A bitmap from taken from vuforia
     * @param orientation The current orientation of the phone on the robot
     * @return a Mat of the bitmap in the correct orientation
     */
    public static Mat processBitmap(Bitmap bitmap, PhoneOrientation orientation) {
        Mat tmp = new Mat();
        Utils.bitmapToMat(bitmap, tmp);

        Mat rotMat = Imgproc.getRotationMatrix2D(new Point(tmp.cols() / 2, tmp.rows() / 2), orientation.getRotation(), 1.0);
        Mat rotated = new Mat();
        Imgproc.warpAffine(tmp, rotated, rotMat, tmp.size());

        return rotated;
    }
}

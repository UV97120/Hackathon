package vishal.hackathon.gps_tracker;

import android.content.Context;
import android.graphics.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Vishal on 3/23/2017.
 */

public class CameraView extends SurfaceView implements SurfaceHolder.Callback{

    public CameraView(Context context, Camera camera){
        super(context);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
package es.udc.psi.Q23.encajados.TetrisImp;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TetrisView extends SurfaceView implements SurfaceHolder.Callback {
    private TetrisGame tetrisGame; // La lógica del juego
    private Paint paint;

    public TetrisView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        paint = new Paint();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Cuando el SurfaceView se crea

    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Cuando el SurfaceView cambia

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Cuando el SurfaceView es destruido

    }
}

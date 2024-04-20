package es.udc.psi.Q23.encajados;

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
        tetrisGame = new TetrisGame(); // Inicializa la lógica del juego
        paint = new Paint();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Inicia el hilo de dibujo cuando el SurfaceView es creado
        TetrisDrawingThread thread = new TetrisDrawingThread(getHolder(), tetrisGame);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // No se utiliza en este ejemplo
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Detiene el hilo de dibujo cuando el SurfaceView es destruido
        boolean retry = true;
        TetrisDrawingThread thread = null;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




}


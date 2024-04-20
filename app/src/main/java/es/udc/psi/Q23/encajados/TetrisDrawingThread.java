package es.udc.psi.Q23.encajados;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;

public class TetrisDrawingThread extends Thread{
    private final SurfaceHolder surfaceHolder;
    private TetrisGame tetrisGame;
    private boolean running;
    private Canvas canvas;
    private long targetFPS = 10; // Objetivo de fotogramas por segundo
    private long frameTimeMillis = 1000 / targetFPS;

    public TetrisDrawingThread(SurfaceHolder surfaceHolder, TetrisGame tetrisGame) {
        this.surfaceHolder = surfaceHolder;
        this.tetrisGame = tetrisGame;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        long startTime;
        long sleepTime;

        while (running) {
            startTime = System.currentTimeMillis();
            canvas = null;

            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    if (canvas != null) {
                        tetrisGame.dropDown(); // Actualiza el estado del juego

                        // Dibuja el juego en el lienzo
                        draw(canvas);
                    }
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            // Calcula el tiempo de espera para mantener el FPS objetivo
            sleepTime = frameTimeMillis - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void draw(Canvas canvas) {
        // Dibuja el fondo del juego
        canvas.drawColor(Color.BLACK);

        // Obtiene el tamaño del bloque
        int blockSize = Math.min(canvas.getWidth() / 12, canvas.getHeight() / 23);

        // Dibuja el campo de juego
        int[][] well = tetrisGame.getWell();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 23; j++) {
                int color = well[i][j];
                if (color != Color.BLACK) {
                    // Dibuja un rectángulo con el color del tetramino en la posición (i, j)
                    Paint paint = new Paint();
                    paint.setColor(color);
                    canvas.drawRect(i * blockSize, j * blockSize,
                            (i + 1) * blockSize, (j + 1) * blockSize, paint);
                }
            }
        }

        // Dibuja la pieza actual
        Point pieceOrigin = tetrisGame.getPieceOrigin();
        int currentPiece = tetrisGame.getCurrentPiece();
        int rotation = tetrisGame.getRotation();
        Point[] currentTetramino = TetrisGame.Tetraminos[currentPiece][rotation];
        for (Point p : currentTetramino) {
            int color = TetrisGame.tetraminoColors[currentPiece];
            Paint paint = new Paint();
            paint.setColor(color);
            // Ajusta las coordenadas de dibujo para la posición de la pieza actual
            int x = (p.x + pieceOrigin.x) * blockSize;
            int y = (p.y + pieceOrigin.y) * blockSize;
            // Dibuja un rectángulo para cada cuadrado del tetramino
            canvas.drawRect(x, y, x + blockSize, y + blockSize, paint);
        }
    }
}

package es.udc.psi.Q23.encajados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private TetrisGame tetrisGame;
    private TetrisDrawingThread tetrisThread;

    private TetrisView tetrisView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tetrisGame = new TetrisGame();
        tetrisView = findViewById(R.id.tetrisView);

        // Inicializar el hilo de dibujo del juego
        tetrisThread = new TetrisDrawingThread(tetrisView.getHolder(), tetrisGame);
        tetrisThread.setRunning(true);
        tetrisThread.start();

        // Botón para rotar la pieza hacia la izquierda
        Button buttonRotateLeft = findViewById(R.id.buttonRotateLeft);
        buttonRotateLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tetrisGame.rotate(-1);
            }
        });

        // Botón para rotar la pieza hacia la derecha
        Button buttonRotateRight = findViewById(R.id.buttonRotateRight);
        buttonRotateRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tetrisGame.rotate(1);
            }
        });

        // Botón para mover la pieza hacia la izquierda
        Button buttonMoveLeft = findViewById(R.id.buttonMoveLeft);
        buttonMoveLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tetrisGame.move(-1);
            }
        });

        // Botón para mover la pieza hacia la derecha
        Button buttonMoveRight = findViewById(R.id.buttonMoveRight);
        buttonMoveRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tetrisGame.move(1);
            }
        });

        // Botón para hacer caer la pieza
        Button buttonDropDown = findViewById(R.id.buttonDropDown);
        buttonDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tetrisGame.dropDown();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Detener el hilo de dibujo del juego al cerrar la aplicación
        tetrisThread.setRunning(false);
        boolean retry = true;
        while (retry) {
            try {
                tetrisThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
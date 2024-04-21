package es.udc.psi.Q23.encajados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TetrisGame tetrisGame;
    private TetrisDrawingThread tetrisThread;

    private TetrisView tetrisView;
    Button butRL, butML, butRR, butMR, butDD, butExit ;

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
        butRL = findViewById(R.id.buttonRotateLeft);
        butRL.setOnClickListener(this);

        // Botón para rotar la pieza hacia la derecha
        butRR = findViewById(R.id.buttonRotateRight);
        butRR.setOnClickListener(this);

        // Botón para mover la pieza hacia la izquierda
        butML = findViewById(R.id.buttonMoveLeft);
        butML.setOnClickListener(this);

        // Botón para mover la pieza hacia la derecha
        butMR = findViewById(R.id.buttonMoveRight);
        butMR.setOnClickListener(this);

        // Botón para hacer caer la pieza
        butDD = findViewById(R.id.buttonDropDown);
        butDD.setOnClickListener(this);

        // Botón para cerrar la partida
        butExit = findViewById(R.id.button_exit);
        butExit.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        if(v==butRL){
            tetrisGame.rotate(-1);
        }else if(v==butRR) {
            tetrisGame.rotate(1);
        }else if(v==butML) {
            tetrisGame.move(-1);
        }else if(v==butMR) {
            tetrisGame.move(1);
        }else if(v==butDD) {
            tetrisGame.dropDown();
        }else if(v==butExit) {
            Log.d("_TAG", "Boton exit");
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
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
package es.udc.psi.Q23.encajados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GameOverDialogFragment.GameOverDialogListener {

    private TetrisGame tetrisGame;
    private TetrisDrawingThread tetrisThread;
    private TetrisView tetrisView;
    Button butRL, butML, butRR, butMR, butDD, butExit, butPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tetrisGame = new TetrisGame(this);
        tetrisView = findViewById(R.id.tetrisView);

        // Inicializar el hilo de dibujo del juego
        tetrisThread = new TetrisDrawingThread(tetrisView.getHolder(), tetrisGame);
        tetrisThread.setRunning(true);
        tetrisThread.start();

        // Inicializar los botones y configurar los listeners
        butRL = findViewById(R.id.buttonRotateLeft);
        butRL.setOnClickListener(this);

        butRR = findViewById(R.id.buttonRotateRight);
        butRR.setOnClickListener(this);

        butML = findViewById(R.id.buttonMoveLeft);
        butML.setOnClickListener(this);

        butMR = findViewById(R.id.buttonMoveRight);
        butMR.setOnClickListener(this);

        butDD = findViewById(R.id.buttonDropDown);
        butDD.setOnClickListener(this);

        butExit = findViewById(R.id.button_exit);
        butExit.setOnClickListener(this);

        butPause = findViewById(R.id.button_pause);
        butPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == butRL) {
            tetrisGame.rotate(-1);
        } else if (v == butRR) {
            tetrisGame.rotate(1);
        } else if (v == butML) {
            tetrisGame.move(-1);
        } else if (v == butMR) {
            tetrisGame.move(1);
        } else if (v == butDD) {
            tetrisGame.extraPoint();
            tetrisGame.dropDown();
        } else if (v == butExit) {
            Log.d("_TAG", "Boton exit");
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else if (v == butPause) {
            boolean isPaused = tetrisGame.isPaused();
            tetrisGame.setPaused(!isPaused);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

    @Override
    public void onRetry() {
        tetrisGame.retry();
    }

    @Override
    public void onExit() {
        finish();
    }

    public void showGameOverDialog(long score) {
        GameOverDialogFragment dialog = new GameOverDialogFragment(score);
        dialog.show(getSupportFragmentManager(), "GameOverDialogFragment");
    }
}
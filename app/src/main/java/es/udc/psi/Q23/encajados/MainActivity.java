package es.udc.psi.Q23.encajados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GameOverDialogFragment.GameOverDialogListener, PauseDialogFragment.PauseDialogListener {

    private TetrisGame tetrisGame;
    private TetrisDrawingThread tetrisThread;
    private TetrisView tetrisView;
    Button butRL, butML, butRR, butMR, butDD, butExit, butPause;

    final private String gameOverTag = "GameOverDialogFragment";
    final private String pauseTag = "PauseDialogFragment";

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
            if (!tetrisGame.isPaused()) {
                tetrisGame.setPaused(true);
            }
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
    public void onContinue(){
        if (tetrisGame.isPaused()) {
            tetrisGame.setPaused(false);
        }
    }

    @Override
    public void onExit() {
        finish();
    }

    public void showGameOverDialog(long score) {
        GameOverDialogFragment dialog = new GameOverDialogFragment(score);
        dialog.show(getSupportFragmentManager(), gameOverTag);
    }

    public void showPauseDialog(long score) {
        PauseDialogFragment dialog = new PauseDialogFragment(score);
        dialog.show(getSupportFragmentManager(), pauseTag);
    }
}
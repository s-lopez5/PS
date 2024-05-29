package es.udc.psi.Q23.encajados;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;


public class GameOverDialogFragment extends DialogFragment {

    private long finalScore;
    private GameOverDialogListener listener;

    public interface GameOverDialogListener {
        void onRetry();
        void onExit();
    }

    public GameOverDialogFragment(long score) {
        this.finalScore = score;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (GameOverDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement GameOverDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.game_over_title)
                .setMessage(getString(R.string.score_game_over_message) + " " + finalScore)
                .setPositiveButton(R.string.retry_message, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onRetry();
                    }
                })
                .setNegativeButton(R.string.exit_message, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onExit();
                    }
                });
        return builder.create();
    }
}
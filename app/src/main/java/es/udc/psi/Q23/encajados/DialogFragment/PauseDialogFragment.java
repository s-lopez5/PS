package es.udc.psi.Q23.encajados.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import es.udc.psi.Q23.encajados.R;

public class PauseDialogFragment extends DialogFragment {

    private long score;
    private PauseDialogListener listener;

    public interface PauseDialogListener {
        void onContinue();
        void onExit();
    }

    public PauseDialogFragment(long score) {
        this.score = score;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (PauseDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement GameOverDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.pause_title)
                .setMessage(getString(R.string.score_game_over_message) + " " + score)
                .setPositiveButton(R.string.continue_message, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onContinue();
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

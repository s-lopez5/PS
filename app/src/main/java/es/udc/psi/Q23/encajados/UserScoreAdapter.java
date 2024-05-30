package es.udc.psi.Q23.encajados;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.udc.psi.Q23.encajados.database.UserScore;

public class UserScoreAdapter extends RecyclerView.Adapter<UserScoreAdapter.MyViewHolder> {

    private final ArrayList<UserScore> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userName;
        public TextView score;

        public MyViewHolder(View view) {
            super(view);
            userName = view.findViewById(R.id.username_tv);
            score = view.findViewById(R.id.score_tv);
        }

        public void bind(UserScore userScore) {
            userName.setText(userScore.getUsername());
            score.setText((int) userScore.getScore());
        }
    }

    public UserScoreAdapter(ArrayList<UserScore> mDataset) {
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public UserScoreAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_score, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

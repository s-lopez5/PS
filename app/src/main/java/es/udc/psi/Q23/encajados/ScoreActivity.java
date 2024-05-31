package es.udc.psi.Q23.encajados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.udc.psi.Q23.encajados.database.FirebaseHelper;
import es.udc.psi.Q23.encajados.database.UserScore;

public class ScoreActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private UserScoreAdapter mAdapter;
    FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        firebaseHelper = new FirebaseHelper();
        firebaseHelper.getTop10Scores(new FirebaseHelper.UserScoresCallback() {
            @Override
            public void onCallback(List<UserScore> userScores) {
                mAdapter.setItems(userScores);
            }
        });

        recyclerView = findViewById(R.id.categories_rv);
        initRecycler();

    }

    private void initRecycler() {
        mAdapter = new UserScoreAdapter();

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

}
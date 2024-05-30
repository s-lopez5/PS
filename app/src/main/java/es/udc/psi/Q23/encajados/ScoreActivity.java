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

import es.udc.psi.Q23.encajados.database.UserScore;

public class ScoreActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private UserScoreAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        recyclerView = findViewById(R.id.categories_rv);

    }

    private void initRecycler(ArrayList<UserScore> userScores) {
        mAdapter = new UserScoreAdapter(userScores);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

}
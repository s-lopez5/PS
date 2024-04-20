package es.udc.psi.Q23.encajados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = "_TAG";
    Button but_inicio, but_puntuacion, but_salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        but_inicio = findViewById(R.id.but_inicio);
        but_inicio.setOnClickListener(this);

        but_puntuacion = findViewById(R.id.but_puntuacion);
        but_puntuacion.setOnClickListener(this);

        but_salir = findViewById(R.id.but_salir);
        but_salir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == but_inicio){
            Log.d(TAG, "Boton Iniciar");

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(intent, 2);

        } else if (v == but_puntuacion) {
            Log.d(TAG, "Boton Puntuacion");
            String log = "No estan implementadas todavia las puntuaciones.";
            Toast.makeText(getApplicationContext(), log, Toast.LENGTH_SHORT).show();

        } else if (v == but_salir) {
            Log.d(TAG, "Boton Salir");
            finish();

        }

    }
}
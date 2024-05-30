package es.udc.psi.Q23.encajados;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = "_TAG";
    Button but_inicio, but_puntuacion, but_salir, but_user;
    EditText user_et;

    SharedPreferences sharedPreferences;
    String KEY_USER = "user";
    String KEY_SCORE = "score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        but_inicio = findViewById(R.id.but_inicio);
        but_inicio.setOnClickListener(this);

        but_puntuacion = findViewById(R.id.but_puntuacion);
        but_puntuacion.setOnClickListener(this);

        user_et = findViewById(R.id.user_edit_text);

        but_user = findViewById(R.id.but_user);
        but_user.setOnClickListener(this);

        but_salir = findViewById(R.id.but_salir);
        but_salir.setOnClickListener(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
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

        } else if (v == but_user) {
            Log.d(TAG, "Boton Usuario");

            String user_data = user_et.getText().toString();

            SharedPreferences.Editor editor =  sharedPreferences.edit();
            editor.putString(KEY_USER, user_data);
            editor.apply();


        } else if (v == but_salir) {
            Log.d(TAG, "Boton Salir");
            finish();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_settings) {
            Log.d(TAG, "menu settings");

            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}
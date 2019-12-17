package com.example.aplicaciontodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button boton;
    private EditText usuario;
    private EditText contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = findViewById(R.id.btnLogin);
        boton.setOnClickListener(this);
        usuario = findViewById(R.id.txtUser);
        contra = findViewById(R.id.txtPassword);
    }

    @Override
    public void onClick(View v) {
        String sUsuario = usuario.getText().toString();
        String sContra = contra.getText().toString();
        boolean bError = false;

        if (TextUtils.isEmpty(sUsuario)) {
            usuario.setError(getString(R.string.this_field_is_required));
            bError = true;
        }

        if (TextUtils.isEmpty(sContra)) {
            contra.setError(getString(R.string.this_field_is_required));
            bError = true;
        }

        if (!bError)
            login(sUsuario, sContra);
    }

    private void login(String usuario, String contra) {
        AsyncTask<String, Integer, Boolean> asyncTask = new AsyncTask<String, Integer, Boolean>() {
            @Override
            protected Boolean doInBackground(String... strings) {
                String username = strings[0];
                String password = strings[1];

                try {
                    Thread.sleep(2000);
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                }

                return username.equals("test") && password.equals("test");
            }

            @Override
            protected void onPostExecute(Boolean logged) {
                super.onPostExecute(logged);

                if (logged) {
                    Toast.makeText(getApplicationContext(), "Login OK", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ListaToDo.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        asyncTask.execute(usuario, contra);
    }
}

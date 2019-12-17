package com.example.aplicaciontodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class AddNewToDo extends AppCompatActivity {

    private EditText contenido;
    private CheckBox done;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_to_do);
        contenido = findViewById(R.id.txtContent);
        done = findViewById(R.id.cboxDone);
        save = findViewById(R.id.btnSave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = contenido.getText().toString();
                boolean isDone = done.isChecked();

                Intent intent = new Intent();
                intent.putExtra("content", content);
                intent.putExtra("done", isDone);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

package com.example.escinf.laboratorio03.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.escinf.laboratorio03.R;
import com.example.escinf.laboratorio03.modelo.Usuario;
import com.example.escinf.laboratorio03.utils.Data;

/**
 * Created by slon on 25/3/2018.
 */

public class UsuarioView extends AppCompatActivity {

    private Button btn_ok;
    private Button btn_cancelar;

    private String userName;
    private String clave;
    private String nuevaClave;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editar();
        cancelar();
    }

    private void editar() {
        btn_ok = (Button) findViewById(R.id.button_ok_usuario);

        btn_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                userName = ((EditText) findViewById(R.id.nombre_usuario)).getText().toString();
                clave = ((EditText) findViewById(R.id.clave_usuario)).getText().toString();
                nuevaClave = ((EditText) findViewById(R.id.nueva_clave)).getText().toString();
                position = getIntent().getIntExtra("position",-1);

                if (validate()) {
                    if (buscar() != null) {
                        buscar().setClave(nuevaClave);
                        Intent intent = new Intent(UsuarioView.this, Navigation.class);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                        //startActivityIfNeeded(intent, 0);
                        startActivity(intent);
                        finish();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "No se encontro el usuario";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Campos vacios!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });
    }

    private void cancelar() {
        btn_cancelar = (Button) findViewById(R.id.button_cancelar_usuario);
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsuarioView.this, Navigation.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean validate() {
        boolean go = true;

        if (TextUtils.isEmpty(userName)) {
            go = false;
        }
        if (TextUtils.isEmpty(clave)) {
            go = false;
        }
        if (TextUtils.isEmpty(nuevaClave)) {
            go = false;
        }

        return go;
    }

    private Usuario buscar() {
        for (int i = 0; i < Data.listaUsuarios.size(); i++) {
            if ((Data.listaUsuarios.get(i).getUserName().equals(userName) &&
                    Data.listaUsuarios.get(i).getClave().equals(clave))) {
                return Data.listaUsuarios.get(i);
            }
        }
        return null;
    }

}

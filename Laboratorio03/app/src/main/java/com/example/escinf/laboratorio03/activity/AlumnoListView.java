package com.example.escinf.laboratorio03.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.escinf.laboratorio03.R;
import com.example.escinf.laboratorio03.modelo.Alumno;

import com.example.escinf.laboratorio03.utils.Data;



/**
 * Created by slon on 25/3/2018.
 */

public class AlumnoListView extends AppCompatActivity {

    ArrayAdapter<Alumno> adapter;
    SwipeMenuListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listview = (SwipeMenuListView) findViewById(R.id.lista_alumnos);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_alumno);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlumnoListView.this, AgregarAlumno.class);
                startActivity(intent);
                finish();
            }
        });

        final SearchView searchAlumno = (SearchView)findViewById(R.id.buscar_alumno);
        searchAlumno.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });


        addData();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Data.listaAlumno);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "edit" item
                SwipeMenuItem editItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                editItem.setBackground(new ColorDrawable(Color.rgb(0x00, 0x00, 0x00)));
                // set item width
                editItem.setWidth(120);
                // set a icon
                editItem.setIcon(R.drawable.ic_action_edit);
                // add to menu
                menu.addMenuItem(editItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0x00, 0x00, 0x00)));
                // set item width
                deleteItem.setWidth(120);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_action_delete);
                // add to menu
                menu.addMenuItem(deleteItem);

            }
        };

        listview.setMenuCreator(creator);

        listview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String nombre;
                String cedula;
                String telefono;
                String email;
                String fechaNac;
                String carrera;
                Boolean edit;

                switch (index) {
                    case 0:
                        nombre = Data.listaAlumno.get(position).getNombre();
                        cedula = Data.listaAlumno.get(position).getCedula();
                        telefono = Data.listaAlumno.get(position).getTelefono();
                        email = Data.listaAlumno.get(position).getEmail();
                        fechaNac = Data.listaAlumno.get(position).getFechaNacimiento();
                        edit = true;

                        Intent intent = new Intent(AlumnoListView.this, AgregarAlumno.class);

                        Alumno alumno = (Alumno) listview.getItemAtPosition(position);
                        intent.putExtra("alumno", alumno);

                        intent.putExtra("nombre", nombre);
                        intent.putExtra("cedula", cedula);
                        intent.putExtra("telefono", telefono);
                        intent.putExtra("email", email);
                        intent.putExtra("fechaNac", fechaNac);
                        intent.putExtra("edit", edit);
                        intent.putExtra("position", position);

                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        Data.listaAlumno.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }


    public void addData() {
        Alumno alumno = new Alumno();
        alumno.setCedula(getIntent().getStringExtra("cedula"));
        alumno.setNombre(getIntent().getStringExtra("nombre"));
        alumno.setTelefono(getIntent().getStringExtra("telefono"));
        alumno.setEmail(getIntent().getStringExtra("email"));
        alumno.setFechaNacimiento(getIntent().getStringExtra("fechaNac"));
        int position = getIntent().getIntExtra("position", -1);
        if(position != -1)
            Data.listaAlumno.remove(position);
        if (alumno.getCedula() != null)
            Data.listaAlumno.add(alumno);
    }


}

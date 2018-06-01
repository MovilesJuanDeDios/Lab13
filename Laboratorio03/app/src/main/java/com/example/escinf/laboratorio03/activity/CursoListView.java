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
import com.example.escinf.laboratorio03.modelo.Curso;
import com.example.escinf.laboratorio03.utils.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slon on 25/3/2018.
 */

public class CursoListView extends AppCompatActivity {

    ArrayAdapter<Curso> adapter;
    SwipeMenuListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listview = (SwipeMenuListView) findViewById(R.id.lista_cursos);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_curso);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CursoListView.this, AgregarCurso.class);
                startActivity(intent);
                finish();
            }
        });

        final SearchView searchCurso = (SearchView)findViewById(R.id.buscar_curso);
        searchCurso.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Data.listaCurso);
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
                String codigo;
                String nombre;
                int creditos;
                int horas;
                Boolean edit;

                switch (index) {
                    case 0:
                        codigo = Data.listaCurso.get(position).getCodigo();
                        nombre = Data.listaCurso.get(position).getNombre();
                        creditos = Data.listaCurso.get(position).getCreditos();
                        horas = Data.listaCurso.get(position).getHorasSemanales();
                        edit = true;

                        Intent intent = new Intent(CursoListView.this, AgregarCurso.class);
                        intent.putExtra("nombre", nombre);
                        intent.putExtra("codigo", codigo);
                        intent.putExtra("creditos", creditos);
                        intent.putExtra("horas", horas);
                        intent.putExtra("edit", edit);
                        intent.putExtra("position", position);
                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        Data.listaCurso.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

    }

    public void addData() {
        Curso curso = new Curso();
        curso.setNombre(getIntent().getStringExtra("nombre"));
        curso.setCodigo(getIntent().getStringExtra("codigo"));
        curso.setCreditos(getIntent().getIntExtra("creditos", 0));
        curso.setHorasSemanales(getIntent().getIntExtra("horasSemanales", 0));
        int position = getIntent().getIntExtra("position", -1);
        if(position != -1)
            Data.listaCurso.remove(position);
        if (curso.getNombre() != null)
            Data.listaCurso.add(curso);
    }

}

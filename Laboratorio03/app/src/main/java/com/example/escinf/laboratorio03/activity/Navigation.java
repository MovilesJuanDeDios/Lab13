package com.example.escinf.laboratorio03.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.escinf.laboratorio03.R;
import com.example.escinf.laboratorio03.modelo.Alumno;
import com.example.escinf.laboratorio03.modelo.Curso;
import com.example.escinf.laboratorio03.utils.Data;


public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static boolean added = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (!added) {
            addCursos();
            addAlumnos();
            added = true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.curso) {
            Intent intentf = new Intent(Navigation.this,CursoListView.class);
            startActivity(intentf);
        } else if (id == R.id.alumno) {
            Intent intentf = new Intent(Navigation.this,AlumnoListView.class);
            startActivity(intentf);
        } else if (id == R.id.usuario) {
            Intent intentf = new Intent(Navigation.this,UsuarioView.class);
            startActivity(intentf);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addCursos(){
        Curso curso = new Curso("EIF604","Dispositivos_Moviles",4,12);
        Curso curso2 = new Curso("EI605","Metodos_Investigacion",4,12);
        Curso curso3 = new Curso("EIF606","Ingenieria3",4,12);
        Curso curso4 = new Curso("EIF607","Paradigmas",4,12);
        Data.listaCurso.add(curso);
        Data.listaCurso.add(curso2);
        Data.listaCurso.add(curso3);
        Data.listaCurso.add(curso4);
    }
    public void addAlumnos(){
        Alumno alumno = new Alumno("403330999","Andres","89064312", "andres_calculta@gmail.com", "27/06/1995");
        Alumno alumno1 = new Alumno("103600944","Ana","88003122", "anamari27@gmail.com", "05/06/1993");
        Alumno alumno2 = new Alumno("407890545","Claudio","77064390", "claudio94@gmail.com", "12/01/1994");
        Alumno alumno3 = new Alumno("701290125","Mary","89431207", "mary_quiroz@gmail.com", "24/09/1990");
        Data.listaAlumno.add(alumno);
        Data.listaAlumno.add(alumno1);
        Data.listaAlumno.add(alumno2);
        Data.listaAlumno.add(alumno3);
    }

}

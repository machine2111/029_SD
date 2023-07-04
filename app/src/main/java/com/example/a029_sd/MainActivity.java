package com.example.a029_sd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et_nombre, et_contenido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_contenido = (EditText) findViewById(R.id.et_contenido);
    }

    //METODO PARA EL BOTON GUARDAR

    public void guardar (View view){
        String nombre = et_nombre.getText().toString();
        String contenido = et_contenido.getText().toString();

        try {
            File tarjetaSD = Environment.getExternalStorageDirectory();
            Toast.makeText(this, tarjetaSD.getPath(),Toast.LENGTH_SHORT).show();
            File rutaarchivo = new File(tarjetaSD.getPath(), nombre);
            OutputStreamWriter creararchivo =  new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));

            creararchivo.write(contenido);
            creararchivo.flush();
            creararchivo.close();

            Toast.makeText(this,"Guardado correctamente", Toast.LENGTH_SHORT).show();
            et_nombre.setText("");
            et_contenido.setText("");



        } catch (IOException e){
            Toast.makeText(this,"No se pudo guardar", Toast.LENGTH_SHORT).show();
        }

    }

    //METODO PARA CONSUTAR

    public void consultar(View view){
        String nombre = et_nombre.getText().toString();

        try {
            File tarjetaSD = Environment.getExternalStorageDirectory();
            File rutaarchivo = new File(tarjetaSD.getPath(), nombre);
            InputStreamReader abrirarchivo = new InputStreamReader(openFileInput(nombre));

            BufferedReader leerarchivo = new BufferedReader(abrirarchivo);
            String linea = leerarchivo.readLine();
            String contenidocompleto = "";

            while (linea != null){
                contenidocompleto = contenidocompleto + linea + "\n";
                linea = leerarchivo.readLine();
            }

            leerarchivo.close();
            abrirarchivo.close();
            et_contenido.setText(contenidocompleto);

        }catch (IOException e){
            Toast.makeText(this, "Error al leer el archivo", Toast.LENGTH_SHORT).show();
        }
    }
}
package com.ejemplo.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
public static final String nombres="names";
TextView viewBienvenido;
Button Salir;
Button _btnEntrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _btnEntrar=findViewById(R.id.btnEntrar);
        _btnEntrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(),CalcularActivity.class));
                finish();
            }
        });
        viewBienvenido = (TextView)findViewById(R.id.textViewUsuario);
        String email= getIntent().getStringExtra("names");
        viewBienvenido.setText("Bienvenido "+email+ " !");
        Salir=(Button)findViewById(R.id.btnSalir);
        Salir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences preferences=getSharedPreferences("preferenciasLogin",MODE_PRIVATE);
                preferences.edit().clear().commit();
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
package com.ejemplo.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CalcularActivity extends AppCompatActivity {
    EditText _numero1,_numero2;
    RadioButton _suma,_resta,_multiplicacion,_division;
    Button _btnCalcular;
    String operacion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular);
        _numero1=findViewById(R.id.numero1);
        _numero2=findViewById(R.id.numero2);
        _suma=findViewById(R.id.suma);
        _resta=findViewById(R.id.resta);
        _multiplicacion=findViewById(R.id.multiplicacion);
        _division=findViewById(R.id.division);
        _btnCalcular=findViewById(R.id.btnCalcular);
        _btnCalcular.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(_numero1.getText().toString().length()<1){
                    Toast.makeText(getApplicationContext(),"Ingrese un numero 1",Toast.LENGTH_SHORT).show();
                }else if(_numero2.getText().toString().length()<1){
                    Toast.makeText(getApplicationContext(),"Ingrese un numero 2",Toast.LENGTH_SHORT).show();
                }else{
                    if(_suma.isChecked())
                        operacion="Suma";
                    if(_resta.isChecked())
                        operacion="Resta";
                    if(_multiplicacion.isChecked())
                        operacion="Multiplicación";
                    if(_division.isChecked())
                        operacion="División";
                    Intent i=new Intent(getApplicationContext(),ResultadoActivity.class);
                    i.putExtra("numero1",_numero1.getText().toString());
                    i.putExtra("numero2",_numero2.getText().toString());
                    i.putExtra("operacion",operacion);
                    startActivity(i);
                }
            }
        });
    }
}
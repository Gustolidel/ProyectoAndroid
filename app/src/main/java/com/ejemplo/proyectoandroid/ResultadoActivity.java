package com.ejemplo.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {
TextView _recibenumero1,_recibenumero2,_texto,_resultado;
Button _btnVolver;
double n1,n2,r;
String operacion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        _recibenumero1=findViewById(R.id.recibirNumero1);
        _recibenumero2=findViewById(R.id.recibirNumero2);
        _texto=findViewById(R.id.texto);
        _resultado=findViewById(R.id.resultado);
        _btnVolver=findViewById(R.id.btnVolver);
        n1=Double.valueOf(getIntent().getExtras().getString("numero1"));
        n2=Double.valueOf(getIntent().getExtras().getString("numero2"));
        _recibenumero1.setText("Numero 1 : "+n1);
        _recibenumero2.setText("Numero 2 : "+n2);
        operacion=getIntent().getExtras().getString("operacion");
        switch (operacion){
            case "Suma":
                r=n1+n2;
                break;
            case "Resta":
                r=n1-n2;break;
            case "División":
                r=n1/n2;break;
            case "Multiplicación":
                r=n1*n2;break;
        }
        _texto.setText("La " +operacion+ " es:");
        _resultado.setText(String.valueOf(r));
        _btnVolver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}
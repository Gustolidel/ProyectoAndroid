package com.ejemplo.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText Email, Pass;
    Button Logear;
    String correo,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email=(EditText)findViewById(R.id.edtUsuario);
        Pass=(EditText)findViewById(R.id.edtPassword);
        Logear=(Button)findViewById(R.id.btnLogin);
        recuperarSesion();
        Logear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                correo=Email.getText().toString();
                password=Pass.getText().toString();
                if(!correo.isEmpty() && !password.isEmpty()){
                    ValidarEmail("http://10.0.2.2:8000/LoginAndroid/Validarusuario.php");
                }else{
                    Toast.makeText(LoginActivity.this,"No se permiten campos vacios",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void ValidarEmail(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              if(!response.isEmpty()){
                  guardarSesion();
              Intent intent=new Intent(getApplicationContext(),MainActivity.class);
              intent.putExtra(MainActivity.nombres,Email.getText().toString());
              startActivity(intent);
              finish();
              }else{
                  Toast.makeText(LoginActivity.this,"Usuario o Contraseña Incorrectos",Toast.LENGTH_SHORT).show();
              }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
    }){
            @Override
            protected Map<String, String> getParams()throws AuthFailureError{
                Map<String, String> Parametros=new HashMap<String, String>();
                Parametros.put("email", correo);
                Parametros.put("contra",password);
                return Parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void guardarSesion(){
        SharedPreferences preferences= getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("email",correo);
        editor.putString("contra",password);
        editor.putBoolean("sesion",true);
        editor.commit();
    }
    private void recuperarSesion(){
        SharedPreferences preferences= getSharedPreferences("preferenciasLogin",Context.MODE_PRIVATE);
        Email.setText(preferences.getString("email",""));
        Pass.setText(preferences.getString("contra",""));
    }
}
package com.example.te_entrega1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2;
    private Cursor fila;
    Button btn_ingresar, btn_registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.et_usuario);
        et2 = (EditText) findViewById(R.id.et_password);
        btn_ingresar = findViewById(R.id.btn_enter);
        btn_registrar = findViewById(R.id.btn_signup);
     final ManejadorDB admin=new ManejadorDB(this,"cvdatabase",null,1);
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SQLiteDatabase db=admin.getWritableDatabase();
                String usuario=et1.getText().toString();
                String contrasena=et2.getText().toString();
                fila=db.rawQuery("select usuario,contrasena from usuarios where usuario='"+usuario+"' and  contrasena='"+contrasena+"'",null);
                //preguntamos si el cursor tiene algun valor almacenado
                if(fila.moveToFirst()==true){
                    //capturamos los valores del cursos y lo almacenamos en variable
                    String usua=fila.getString(0);
                    String pass=fila.getString(1);
                    //preguntamos si los datos ingresados son iguales
                    if (usuario.equals(usua)&&contrasena.equals(pass)){
                        //si son iguales entonces vamos a otra ventana
                        //Menu es una nueva actividad empty
                        Intent ven = new Intent(MainActivity.this,Menu.class);
                        startActivity(ven);
                        //limpiamos las las cajas de texto
                        et1.setText("");
                        et2.setText("");
                    }
                    else {
                        //Toast.makeText(this, "Error de Usuario", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ven=new Intent(MainActivity.this, Registrar.class);
                startActivity(ven);
            }
        });

    }

}

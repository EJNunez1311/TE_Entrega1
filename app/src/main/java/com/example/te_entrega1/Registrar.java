package com.example.te_entrega1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registrar extends AppCompatActivity {
    Button Enviar, Cancelar;
    EditText et1,et2,et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        et1= (EditText) findViewById(R.id.et_username);
        et2= (EditText) findViewById(R.id.et_password);
        et3= (EditText) findViewById(R.id.et_codigo);
        Enviar = findViewById(R.id.btn_enviar);
        Cancelar = findViewById(R.id.btn_cancelar);
        final ManejadorDB admin=new ManejadorDB(this,"cvdatabase",null,1);

        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db= admin.getWritableDatabase();
                Integer codigo=Integer.parseInt(et3.getText().toString()) ;
                String usuario=et1.getText().toString();
                String contrasena=et2.getText().toString();

                db.execSQL("insert into usuarios values("+codigo.toString()+",'"+usuario+"','"+contrasena+"')");

                db.close();
                finish();
            }
        });

        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

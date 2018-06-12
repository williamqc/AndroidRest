package com.ex.william.androidmaprest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ex.william.servis.EventoServis;
import com.ex.william.servis.UsuarioServis;
import com.ex.william.to.UsuarioTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    TextView idUser, idClve;
    Button Ingresar;

    public final String TAG=this.getClass().getSimpleName();
    Retrofit retrofit;
    UsuarioServis usuarioServis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

     idUser =(TextView) findViewById( R.id.idUsuario );
        idClve = (TextView) findViewById( R.id.idClave );
        Ingresar =(Button) findViewById(R.id.IdIngresar);

        Ingresar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = idUser.getText().toString();
                String pas = idClve.getText().toString();
                logeo(v,user,pas);
            }

        } );

    }
    public void logeo(View view, String usuario, String passwprd){

        retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        usuarioServis=retrofit.create(UsuarioServis.class);
        UsuarioTO user=new UsuarioTO();
        user.setUsuario(usuario);
        user.setClave(passwprd);
        Call<UsuarioTO> call=usuarioServis.login(user);
        call.enqueue(new Callback<UsuarioTO>() {
            @Override
            public void onResponse(Call<UsuarioTO> call, Response<UsuarioTO> response) {

                startActivity(new Intent().setClass(LoginActivity.this,HomeActivity.class));
            }

            @Override
            public void onFailure(Call<UsuarioTO> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"El usuario o la contraseña con incorrectos", Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.toString());
            }
        });


    }
}

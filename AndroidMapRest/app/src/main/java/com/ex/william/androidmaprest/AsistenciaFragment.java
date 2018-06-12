package com.ex.william.androidmaprest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ex.william.servis.AsistenciaServis;
import com.ex.william.to.AsistenciaTO;
import com.ex.william.util.AsistenciaAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class AsistenciaFragment extends Fragment {
    List<AsistenciaTO> asistencia;
    RecyclerView recyclerView;
    RecyclerView.Adapter<AsistenciaAdapter.AsistenciaViewHolder> adapter;
    RecyclerView.LayoutManager layoutManager;
    public final String TAG=this.getClass().getSimpleName();
    Retrofit retrofit;
    AsistenciaServis asistenciaServis;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View myFragmentView= inflater.inflate(R.layout.fragment_asistencia, container, false);

        recyclerView=(RecyclerView)myFragmentView.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.getContext());

        // Rest
      retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:8080")
                .addConverterFactory( GsonConverterFactory.create())
                .build();
        asistenciaServis=retrofit.create(AsistenciaServis.class);
        Call<List<AsistenciaTO>> listarAsistenciaTodos=asistenciaServis.listarAsistencia();
        listarAsistenciaTodos.enqueue(new Callback<List<AsistenciaTO>>() {
            @Override
            public void onResponse(Call<List<AsistenciaTO>> call, Response<List<AsistenciaTO>> response) {

                asistencia = response.body();
                adapter=new AsistenciaAdapter(asistencia);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                Log.e(TAG,"Llego.......!");
            }

            @Override
            public void onFailure(Call<List<AsistenciaTO>> call, Throwable t) {
                Toast.makeText(getContext(), "Error al recuperar rest asistencia", Toast.LENGTH_SHORT).show();
            }
        });


        return myFragmentView;
    }

}

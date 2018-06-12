package com.ex.william.androidmaprest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import com.ex.william.dao.EventoDao;
import com.ex.william.servis.EventoServis;
import com.ex.william.to.EventoTO;
import com.ex.william.util.EventoAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ReporteFragment extends Fragment {
    /*private EventoDao dao;*/
    List<EventoTO> evento;
     RecyclerView recyclerView;
     RecyclerView.Adapter<EventoAdapter.EventoViewHolder> adapter;
    RecyclerView.LayoutManager layoutManager;
    Retrofit retrofit;
    EventoServis eventoServis;
    public final String TAG=this.getClass().getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myFragmentView= inflater.inflate(R.layout.fragment_reporte, container, false);

        recyclerView=(RecyclerView) myFragmentView.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.getContext());



        // Rest
        retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        eventoServis=retrofit.create(EventoServis.class);
        Call<List<EventoTO>> listarEventoTodos=eventoServis.listarEvento();
        listarEventoTodos.enqueue(new Callback<List<EventoTO>>() {
            @Override
            public void onResponse(Call<List<EventoTO>> call, Response<List<EventoTO>> response) {
                evento = response.body();
                adapter=new EventoAdapter(evento);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

             Log.e(TAG,"Llego.......!");
            }

            @Override
            public void onFailure(Call<List<EventoTO>> call, Throwable t) {
                Toast.makeText(getContext(), "Error al recuperar rest", Toast.LENGTH_SHORT).show();
//                Log.e(TAG,"Error al recuperar el Servicio Rest de Usuario!");
            }
        });

//        dao=new EventoDao(this.getContext());
//        evento=dao.ListarEvento();
//        Log.v("DMP", "Cantidad: "+evento.size());


        return myFragmentView;
   }
}

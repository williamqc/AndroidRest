package com.ex.william.servis;

import java.util.List;

import com.ex.william.to.EventoTO;
import com.ex.william.to.UsuarioTO;

import retrofit2.Call;
import retrofit2.http.GET;



public interface EventoServis {
    @GET("/EventoUPeU/event/all")
    Call<List<EventoTO>> listarEvento();

}

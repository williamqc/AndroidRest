package com.ex.william.servis;

import com.ex.william.to.AsistenciaTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AsistenciaServis {
    @GET("/EventoUPeU/asistencia/all")
    Call<List<AsistenciaTO>> listarAsistencia();
}

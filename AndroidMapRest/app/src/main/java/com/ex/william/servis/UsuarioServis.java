package com.ex.william.servis;

import com.ex.william.to.UsuarioTO;

import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioServis {
    @POST("/EventoUPeU/user/login")
    Call<UsuarioTO>  login(@Body UsuarioTO usuario);
}


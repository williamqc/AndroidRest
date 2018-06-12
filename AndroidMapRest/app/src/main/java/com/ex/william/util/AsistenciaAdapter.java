package com.ex.william.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ex.william.androidmaprest.R;
import com.ex.william.to.AsistenciaTO;

import java.util.List;


public class AsistenciaAdapter extends RecyclerView.Adapter<AsistenciaAdapter.AsistenciaViewHolder> {
    private List<AsistenciaTO> asistencia;

    public static class AsistenciaViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNombre;
        private TextView txtCompañia;

        private AsistenciaTO asistencia;


        public AsistenciaViewHolder(View itemView) {
            super(itemView);
            this.txtNombre=(TextView) itemView.findViewById( R.id.txtNombre);
            this.txtCompañia=(TextView) itemView.findViewById(R.id.txtCompañia);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Context context=v.getContext();
                    Toast.makeText(context,asistencia.getCompanhia(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setAsistencia(AsistenciaTO asistencia){
            this.asistencia=asistencia;
            this.txtNombre.setText(asistencia.getNombres());
            this.txtCompañia.setText(asistencia.getCompanhia());
        }

    }






    public AsistenciaAdapter(List<AsistenciaTO> asistencias){
        this.asistencia=asistencias;
    }

    @Override
    public AsistenciaAdapter.AsistenciaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.lista_asistencia, parent, false);
        AsistenciaViewHolder viewHolder=new AsistenciaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AsistenciaViewHolder holder, int position) {
        AsistenciaTO asistenciax=asistencia.get(position);

        holder.setAsistencia(asistenciax);
    }

    @Override
    public int getItemCount() {
        return asistencia.size();
    }



}

package com.example.autodoc.firebase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterReciclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Usuario> list;

    public AdapterReciclerView(Context context, List<Usuario> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_usuario, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Usuario u = (list.get(position));
        MyViewHolder viewHolder = (MyViewHolder) holder;

        viewHolder.txtNome.setText(u.getmNome());
        viewHolder.txtEmail.setText(u.toString());
        viewHolder.txtEmail.setText(u.getmSenha().toString());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome;
        TextView txtEmail;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtNome = (TextView) itemView.findViewById(R.id.textViewItemNome);
            txtEmail = (TextView) itemView.findViewById(R.id.textViewItemEmail);

        }
    }
}

package com.example.autodoc.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapterBaseAdapter extends BaseAdapter {

    Context context;
    List<Usuario> list;

    public MyAdapterBaseAdapter(Context context, List<Usuario> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Usuario u = list.get(position);
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_usuario,parent,false);
        }

        TextView txtNome = (TextView)convertView.findViewById(R.id.textViewItemNome);
        TextView txtEmail = (TextView)convertView.findViewById(R.id.textViewItemEmail);

        txtNome.setText(u.getmNome().toString());
        txtEmail.setText(u.getmEmail().toString());
        return convertView;
    }
}

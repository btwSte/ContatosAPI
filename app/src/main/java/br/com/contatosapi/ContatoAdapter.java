package br.com.contatosapi;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ContatoAdapter extends ArrayAdapter<Contato>{
    public ContatoAdapter (Context ctx, ArrayList<Contato> lstContatos){
        super(ctx, 0, lstContatos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, null);
        }
        Contato item = getItem(position);

        TextView txt_nome = v.findViewById(R.id.txt_nome_contato);
        TextView txt_telefone = v.findViewById(R.id.txt_telefone_contato);
        ImageView img_foto = v.findViewById(R.id.img_contato);

        txt_nome.setText(item.getNome());
        txt_telefone.setText(item.getTelefone());

        Picasso.with(getContext()).load("http://10.0.2.2/inf3m20172/API/" + item.getFoto()).into(img_foto);
        return v;
    }
}

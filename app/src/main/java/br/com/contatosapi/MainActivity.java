package br.com.contatosapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list_view;
    ContatoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //usar para tela de cadastro
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /* ******************************************** */

        list_view = (ListView) findViewById(R.id.list_view);
        //cria adapter
        adapter = new ContatoAdapter(this, new ArrayList<Contato>());

        //definir adapter na lista
        list_view.setAdapter(adapter);

        /*
           preencher os dados fake
            adapter.add(Contato.create("Linguine", "(11) 97099-9999"));
            adapter.add(Contato.create("Celsão", "(11) 99999-9999"));
            adapter.add(Contato.create("Irineu", "(11) 97070-7070"));
        */

        //preencher lista
        new AsyncTask<Void, Void, Void>(){
            ArrayList<Contato> lstContatos = new ArrayList<Contato>();

            @Override
            protected Void doInBackground(Void... voids) {
                String retornoJson = Http.get("http://10.107.134.16/inf3m20172/API/selecionar.php");
                Log.d("TAG", retornoJson);

                try {
                    JSONArray jsonArray = new JSONArray(retornoJson);

                    for (int i=0; i < jsonArray.length(); i++){
                        JSONObject item = jsonArray.getJSONObject(i);

                        Contato c = Contato.create(item.getString("nome"), item.getString("telefone"), item.getString("foto"));
                        lstContatos.add(c);
                    }

                    Log.d("TAG", lstContatos.size()+"");

                } catch (Exception ex){
                    Log.e("ERRO", ex.getMessage());
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                adapter.addAll(lstContatos);
            }
        }.execute();

    }


}

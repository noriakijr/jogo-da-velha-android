package com.senai.jogodavelha;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.senai.jogodavelha.model.Jogador;

import java.util.ArrayList;
import java.util.HashMap;

public class JogadorActivity extends Activity {

    private Intent appIntent;
    private EditText etJogador1;
    private EditText etJogador2;
    private ArrayList<Jogador> jogadoresAtuais;

    private void inicializarComponentes() {
        appIntent = getIntent();
        etJogador1 = (EditText) findViewById(R.id.et_jogador1);
        etJogador2 = (EditText) findViewById(R.id.et_jogador2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogador);
        inicializarComponentes();

        jogadoresAtuais = JogadorControlBase.getListaJogadoresAtuais(getApplicationContext());
        if (!jogadoresAtuais.isEmpty()) {
            etJogador1.setText(jogadoresAtuais.get(0).getNome());
            etJogador2.setText(jogadoresAtuais.get(1).getNome());
        }
    }

    public void iniciarJogo(View view) {
        String jogador1 = etJogador1.getText().toString();
        String jogador2 = etJogador2.getText().toString();
        Jogador j1 = new Jogador();
        Jogador j2 = new Jogador();

        // retirando espaços dos nomes
        if(jogador1.endsWith(" "))
            jogador1 = jogador1.substring(0, jogador1.length() - 1);
        if(jogador2.endsWith(" "))
            jogador2 = jogador2.substring(0, jogador2.length() - 1);

        if (jogador1 != "" && jogador2 != "" && jogador1.length() > 2 && jogador2.length() > 2) {
            HashMap<String, Jogador> jogadores = JogadorControlBase.getListaJogadores(getApplicationContext());

            if (jogadores.containsKey(jogador1)) {
                j1 = jogadores.get(jogador1);
            } else {
                j1 = new Jogador(jogador1, 0, 0, 0);
                jogadores.put(j1.getNome(), j1);
            }
            if (jogadores.containsKey(jogador2)) {
                j2 = jogadores.get(jogador2);
            } else {
                j2 = new Jogador(jogador2, 0, 0, 0);
                jogadores.put(j2.getNome(), j2);
            }

            jogadoresAtuais = new ArrayList<Jogador>();
            jogadoresAtuais.add(j1);
            jogadoresAtuais.add(j2);
            JogadorControlBase.salvarJogadoresAtuais(getApplicationContext(), jogadoresAtuais);
            JogadorControlBase.salvarJogadores(getApplicationContext(), jogadores);

            Intent intent = new Intent(this, JogoActivity.class);
            intent.putExtra("jogador1", j1);
            intent.putExtra("jogador2", j2);

            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Digite um nome válido!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.jogador, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_sobre:
                AlertDialog.Builder builder = new AlertDialog.Builder(JogadorActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = this.getLayoutInflater();

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                View view = inflater.inflate(R.layout.dialog_sobre, null);
                Button btContato = (Button) view.findViewById(R.id.btContato);
                btContato.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "jun.odan@gmail.com", null));
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Jogo da Velha app");
                        startActivity(intent.createChooser(intent, "Enviar email..."));
                    }
                });
                builder.setTitle("Sobre o Jogo da Velha");
                builder.setView(view);
                builder.create().show();
                break;
        }
        return true;
    }
}

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
import android.widget.ListView;
import android.widget.TextView;

import com.senai.jogodavelha.model.Jogador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class EstatisticaActivity extends Activity {

	private Intent appIntent;
    private String ganhador;
    private Jogador jogador1;
    private Jogador jogador2;
    private int vitoriax;
    private int vitoriao;
    private int empate;
    private ListView lvRanking;
    private JogadorAdapter adp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estatistica);
		appIntent = getIntent();
		obterEstatisticas();
        mostrarAtribuirPontosGanhador();
		mostrarJogadores();
	}
	
	private void obterEstatisticas() {
		ganhador = appIntent.getStringExtra("ganhador");
		
		jogador1 = (Jogador) appIntent.getSerializableExtra("jogador1");
		jogador2 = (Jogador) appIntent.getSerializableExtra("jogador2");
		
		vitoriax = appIntent.getIntExtra("vitoriax", 0);
		vitoriao = appIntent.getIntExtra("vitoriao", 0);
		empate = appIntent.getIntExtra("empate", 0);
	}
	
	private void mostrarAtribuirPontosGanhador() {
		TextView tvGanhador = (TextView) findViewById(R.id.tv_ganhador);
		
		if (ganhador.equals("X")) {
			tvGanhador.setText(jogador1.getNome() + " é o vencedor!");
            jogador1.setGanho(jogador1.getGanho() + vitoriax);
            jogador2.setPerda(jogador2.getPerda() + 1);
		} else if (ganhador.equals("O")) {
			tvGanhador.setText(jogador2.getNome() + " é o vencedor!");
            jogador2.setGanho(jogador2.getGanho() + vitoriao);
            jogador1.setPerda(jogador1.getPerda() + 1);
		} else {
			tvGanhador.setText("Jogo empatado");
            jogador1.setEmpate(jogador1.getEmpate() + empate);
            jogador2.setEmpate(jogador2.getEmpate() + empate);
		}
        HashMap<String, Jogador> jogadores = JogadorControlBase.getListaJogadores(getApplicationContext());
        if(jogadores.containsKey(jogador1.getNome())) {jogadores.remove(jogador1);}
        if(jogadores.containsKey(jogador2.getNome())) {jogadores.remove(jogador2);}
        jogadores.put(jogador1.getNome(), jogador1);
        jogadores.put(jogador2.getNome(), jogador2);
        JogadorControlBase.salvarJogadores(getApplicationContext(), jogadores);
	}
	
	private void mostrarJogadores() {
        lvRanking = (ListView) findViewById(R.id.lvRanking);
        HashMap<String, Jogador> jogadores = JogadorControlBase.getListaJogadores(getApplicationContext());
        ArrayList<Jogador> list = new ArrayList<Jogador>();
        list.addAll(jogadores.values());
        Collections.sort(list);
        adp = new JogadorAdapter(list, getApplicationContext());
        lvRanking.setAdapter(adp);
	}
	
	public void jogarNovamente(View view) {
		appIntent.setClass(this, JogadorActivity.class);
		appIntent.putExtra("focus", false);
        startActivity(appIntent);
        finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.estatistica, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

        switch (id) {
            case R.id.action_sobre:
                AlertDialog.Builder builder = new AlertDialog.Builder(EstatisticaActivity.this);
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

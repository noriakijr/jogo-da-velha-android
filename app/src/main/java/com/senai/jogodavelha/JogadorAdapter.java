package com.senai.jogodavelha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.senai.jogodavelha.model.Jogador;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noriaki on 03/03/2015.
 */
public class JogadorAdapter extends BaseAdapter {
    private List<Jogador> list;
    private Context context;

    public JogadorAdapter(ArrayList<Jogador> list, Context context) {
        this.list = list;
        this.context = context;
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
        return 0;
    }

    public void deleteItem(int position) {list.remove(position); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.jogador_adapter, null);

        TextView tvClassificacao = (TextView) view.findViewById(R.id.tvClassificacao);
        TextView tvNome = (TextView) view.findViewById(R.id.tvNome);
        TextView tvGanhou = (TextView) view.findViewById(R.id.tvGanhou);
        TextView tvPerdeu = (TextView) view.findViewById(R.id.tvPerdeu);
        TextView tvEmpatou = (TextView) view.findViewById(R.id.tvEmpatou);

        tvClassificacao.setText((position + 1) + ". ");
        tvNome.setText(list.get(position).getNome());
        tvGanhou.setText(String.valueOf(list.get(position).getGanho()));
        tvPerdeu.setText(String.valueOf(list.get(position).getPerda()));
        tvEmpatou.setText(String.valueOf(list.get(position).getEmpate()));

        return view;
    }
}

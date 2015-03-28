package com.senai.jogodavelha;

import android.content.Context;

import com.senai.jogodavelha.model.Jogador;
import com.senai.jogodavelha.utils.DSSharePreferenceUtility;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Noriaki on 01/03/2015.
 */
public class JogadorControlBase {

    private static String JOGADORES_ATUAIS = "jogadores_atuais";
    private static String JOGADORES = "lista_jogadores";

    public static void salvarJogadoresAtuais(Context context, ArrayList<Jogador> jogadores) {
        DSSharePreferenceUtility.writeObjectToFile(context, jogadores, JOGADORES_ATUAIS);
    }

    public static ArrayList<Jogador> getListaJogadoresAtuais(Context context) {
        ArrayList<Jogador> jogadores = (ArrayList<Jogador>) DSSharePreferenceUtility.readObjectFromFile(context, JOGADORES_ATUAIS);
        if(jogadores == null)
            return new ArrayList<Jogador>();

        return jogadores;
    }

    public static void salvarJogadores(Context context, HashMap<String, Jogador> jogadores) {
        DSSharePreferenceUtility.writeObjectToFile(context, jogadores, JOGADORES);
    }

    public static HashMap<String, Jogador> getListaJogadores(Context context) {
        HashMap<String, Jogador> jogadores = (HashMap<String, Jogador>) DSSharePreferenceUtility.readObjectFromFile(context, JOGADORES);
        if(jogadores == null)
            return new HashMap<String, Jogador>();

        return jogadores;
    }
}

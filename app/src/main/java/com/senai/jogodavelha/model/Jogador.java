package com.senai.jogodavelha.model;

import java.io.Serializable;

/**
 * Created by Noriaki on 01/03/2015.
 */
public class Jogador implements Serializable, Comparable<Jogador> {

    private String nome;
    private long ganho;
    private long perda;
    private long empate;

    public Jogador() {}

    public Jogador(String nome, long ganho, long perda, long empate) {
        this.nome = nome;
        this.ganho = ganho;
        this.perda = perda;
        this.empate = empate;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getGanho() {
        return ganho;
    }

    public void setGanho(long ganho) {
        this.ganho = ganho;
    }

    public long getPerda() {
        return perda;
    }

    public void setPerda(long perda) {
        this.perda = perda;
    }

    public long getEmpate() {
        return empate;
    }

    public void setEmpate(long empate) {
        this.empate = empate;
    }

    @Override
    public int compareTo(Jogador jogador) {
        long thisResult = this.ganho - this.perda;
        long result = jogador.ganho - jogador.perda;

        if(thisResult > result) {
            return -1;
        } else if(thisResult < result){
            return 1;
        } else {
            return 0;
        }
    }
}

package com.company;
//Integrantes: Gabriel Sposito Conciani, Pedro Henrique Faria Amadeu.

public class Valor {
    private int dado;
    private Valor direita;
    private Valor esquerda;
    private Valor pai;
    private int balancear;

    public int getDado() {
        return dado;
    }

    public void setDado(int dado) {
        this.dado = dado;
    }

    public Valor getDireita() {
        return direita;
    }

    public void setDireita(Valor direita) {
        this.direita = direita;
    }

    public Valor getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Valor esquerda) {
        this.esquerda = esquerda;
    }

    public Valor getPai() {
        return pai;
    }

    public void setPai(Valor pai) {
        this.pai = pai;
    }

    public int getBalancear() {
        return balancear;
    }

    public void setBalancear(int balacear) {
        this.balancear = balacear;
    }

    public Valor(int numero){
        direita = null;
        esquerda = null;
        pai = null;
        dado = numero;
    }
}
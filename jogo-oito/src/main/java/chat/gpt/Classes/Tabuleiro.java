package org.example.classes;

public class Tabuleiro {

    private int[][] tabuleiro;

    public Tabuleiro() {
        tabuleiro = new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
        this.embaralhar();
    }

    public int getValue(int linha, int y){
        return this.tabuleiro[linha][y];
    }

    public void setValue(int linha, int y, int value) {
        this.tabuleiro[linha][y] = value;
    }




}
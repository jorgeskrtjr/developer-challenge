package org.example.classes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    public void ordenar() {
        tabuleiro = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    }

    public void embaralhar() {

        Random gerador = new Random();
        tabuleiro = new int[3][3];
        List<Integer> numeros = new ArrayList<>();

        for (int i = 1; i < 9; i++) {
            numeros.add(i);
        }

        Collections.shuffle(numeros, gerador);

        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(i == 2 && j == 2){
                    tabuleiro[2][2] = 0;
                }else{
                    tabuleiro[i][j] = numeros.get(count);
                    count++;
                }
            }

        }
    }

}
package chat.gpt.classes;

public class Peca {
    private int linha;
    private int coluna;
    private int numero;

    public Peca(int numero, int linha, int coluna) {
        this.numero = numero;
        this.linha = linha;
        this.coluna = coluna;
    }

    public void setLinha(int value){
        this.linha = value;
    }

    public int getLinha(){
        return this.linha;
    }

    public void setColuna(int value){
        this.coluna = value;
    }

    public int getColuna(){
        return this.coluna;
    }

    public void setNumero(int value){
        this.numero = value;
    }

    public int getnumero(){return this.numero;}
}

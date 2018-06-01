package uno.unisal.com.uno.classes;

import android.app.Application;

public class Vetor extends Application {
    private Carta[] vetorCartas = new Carta[100];
    private int totalCartas = 0;

    public void adicionaId(Carta vetorCarta){
        for(int i=0; i < 100; i++){
            this.vetorCartas[this.totalCartas] = vetorCarta;
            this.totalCartas++;
        }
    }
    public Carta pega(int posicao) {
        return this.vetorCartas[posicao];
    }

    @Override
    public String toString() {
        return toString();
        }

}

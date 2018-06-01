package uno.unisal.com.uno.classes;

import android.media.Image;

public class Carta {
         private String cor;
         private String simbolo;
         private Image imagem;
         private int id;

    public Carta(String cor, String simbolo, Image imagem, int id) {
        this.cor = cor;
        this.simbolo = simbolo;
        this.imagem = imagem;
        this.id = id;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCor() {
        return cor;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public Image getImagem() {
        return imagem;
    }

    public int getId() {
        return id;
    }
}

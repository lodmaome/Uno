package uno.unisal.com.uno.classes;

import android.app.Application;
import android.content.res.Configuration;
import android.media.Image;


public class Carta extends Application{

    private int imagem;
    private int id;
    private String caracter;
    private String cor;

    public Carta(int imagem, int id, String caracter, String cor) {
        this.imagem = imagem;
        this.id = id;
        this.caracter = caracter;
        this.cor = cor;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }


    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public String getCor(String verde) {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return toString();
    }


}

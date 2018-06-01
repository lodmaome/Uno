package uno.unisal.com.uno.classes;

import android.app.Application;
import android.content.res.Configuration;
import android.media.Image;


public class Carta extends Application{
    private String rosa;

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

    public String getRosa() {
        return rosa;
    }

    public void setRosa(String rosa) {
        this.rosa = rosa;
    }
}

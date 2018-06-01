package uno.unisal.com.uno;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

import uno.unisal.com.uno.classes.Carta;

public class JogarActivity extends AppCompatActivity{
    private TextView teste;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        Carta[] deck = new Carta[108];

        deck[0] = new Carta(R.drawable.blue_0, 0, "0", "blue");
        deck[1] = new Carta(R.drawable.blue_1, 1, "1", "blue");
        deck[2] = new Carta(R.drawable.blue_1, 2, "1", "blue");
        deck[3] = new Carta(R.drawable.blue_2, 3, "2", "blue");
        deck[4] = new Carta(R.drawable.blue_2, 4, "2", "blue");
        deck[5] = new Carta(R.drawable.blue_3, 5, "3", "blue");
        deck[6] = new Carta(R.drawable.blue_3, 6, "3", "blue");


    }
}

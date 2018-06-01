package uno.unisal.com.uno;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uno.unisal.com.uno.classes.Carta;
import uno.unisal.com.uno.classes.Vetor;

public class JogarActivity extends AppCompatActivity{
    private TextView teste;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogar);
            Carta[] deck = new Carta[108];
            


        deck[0] = new Carta(R.drawable.blue_0, 0,"0","azul");
        deck[1] = new Carta(R.drawable.blue_0, 1,"0" ,"azul");
        deck[2] = new Carta(R.drawable.blue_1,2,"1","azul");
        deck[3]= new Carta(R.drawable.blue_1,3,"1","azul");
        deck[4] = new Carta(R.drawable.blue_2,4, "2","azul");
        deck[5] = new Carta(R.drawable.blue_2, 5, "2","azul");
        deck[6] = new Carta(R.drawable.blue_3,6,"3","azul");
        deck[7] = new Carta(R.drawable.blue_3, 7,"3" ,"azul");
        deck[8] = new Carta(R.drawable.blue_4, 8,"4" ,"azul");
        deck[9] = new Carta(R.drawable.blue_4, 9,"4" ,"azul");
        deck[10] = new Carta(R.drawable.blue_5, 10,"5" ,"azul");
        deck[11= new Carta(R.drawable.blue_5, 11,"5" ,"azul");
        deck[12 = new Carta(R.drawable.blue_5, 12,"5" ,"azul");
        deck[13 = new Carta(R.drawable.blue_6, 13,"6" ,"azul");
        deck[14 = new Carta(R.drawable.blue_6, 14,"6" ,"azul");
        deck[15 = new Carta(R.drawable.blue_7, 15,"7" ,"azul");
        deck[16 = new Carta(R.drawable.blue_7, 16,"7" ,"azul");
        deck[17 = new Carta(R.drawable.blue_8, 17,"8" ,"azul");
        deck[18 = new Carta(R.drawable.blue_8, 18,"8" ,"azul");
        deck[19 = new Carta(R.drawable.blue_9, 19,"9" ,"azul");
        deck[20= new Carta(R.drawable.blue_9, 20,"9" ,"azul");
       // deck[21= new Carta(R.drawable.green_0, 21,"0" ,"verde");
        deck[22= new Carta(R.drawable.green_0, 22,"0" ,"verde");
        deck[23 = new Carta(R.drawable.green_1, 23,"1" ,"verde");
        deck[24 = new Carta(R.drawable.green_1, 24,"1" ,"verde");
        deck[25 = new Carta(R.drawable.green_2, 25,"2" ,"verde");
        deck[26 = new Carta(R.drawable.green_2, 26,"2" ,"verde");
        deck[27 = new Carta(R.drawable.green_3, 27,"3" ,"verde");
        deck[28 = new Carta(R.drawable.green_4, 28,"4" ,"verde");
        deck[29 = new Carta(R.drawable.green_4, 29,"4" ,"verde");
        deck[30 = new Carta(R.drawable.green_5, 30,"5" ,"verde");
        deck[31 = new Carta(R.drawable.green_5, 31,"5" ,"verde");
        deck[32 = new Carta(R.drawable.green_6, 32,"6" ,"verde");
        deck[33 = new Carta(R.drawable.green_6, 33,"6" ,"verde");
        deck[34 = new Carta(R.drawable.green_7, 34,"7" ,"verde");
        deck[35 = new Carta(R.drawable.green_7, 35,"7" ,"verde");
        deck[36 = new Carta(R.drawable.green_8, 36,"8" ,"verde");
        deck[37 = new Carta(R.drawable.green_8, 37,"8" ,"verde");
        deck[38 = new Carta(R.drawable.green_9, 38,"9" ,"verde");
        deck[39 = new Carta(R.drawable.green_9, 39,"9" ,"verde");
        deck[40 = new Carta(R.drawable.red_0, 40,"0" ,"vermelho");
        deck[41 = new Carta(R.drawable.red_0, 41,"0" ,"vermelho");
        deck[42 = new Carta(R.drawable.red_1, 42,"1" ,"vermelho");
        deck[43 = new Carta(R.drawable.red_1, 43,"1" ,"vermelho");
        deck[44 = new Carta(R.drawable.red_2, 44,"2" ,"vermelho");
        deck[45 = new Carta(R.drawable.red_2, 45,"2" ,"vermelho");
        deck[46 = new Carta(R.drawable.red_3, 46,"3" ,"vermelho");
        deck[47 = new Carta(R.drawable.red_3, 47,"3" ,"vermelho");
        deck[48 = new Carta(R.drawable.red_4, 48,"4" ,"vermelho");
        deck[49 = new Carta(R.drawable.red_4, 49,"4" ,"vermelho");
        deck[50 = new Carta(R.drawable.red_5, 50,"5" ,"vermelho");
        deck[51 = new Carta();
        deck[52 = new Carta();
        deck[53 = new Carta();
        deck[54 = new Carta();
        deck[55 = new Carta();
        deck[56 = new Carta();
        deck[57 = new Carta();
        deck[58 = new Carta();
        deck[59 = new Carta();
        deck[60 = new Carta();
        deck[61 = new Carta();
        deck[62 = new Carta();
        deck[63 = new Carta();
        deck[64 = new Carta();
        deck[65 = new Carta();
        deck[66 = new Carta();
        deck[67 = new Carta();
        deck[68 = new Carta();
        deck[69 = new Carta();
        deck[70 = new Carta();
        deck[71 = new Carta();
        deck[72 = new Carta();
        deck[73 = new Carta();
        deck[74 = new Carta();
        deck[75 = new Carta();
        deck[76 = new Carta();
        deck[77 = new Carta();
        deck[78 = new Carta();
        deck[79 = new Carta();
        deck[80 = new Carta();
        deck[81 = new Carta();
        deck[82 = new Carta();
        deck[83 = new Carta();
        deck[84 = new Carta();
        deck[85 = new Carta();
        deck[86 = new Carta();
        deck[87 = new Carta();
        deck[88 = new Carta();
        deck[89 = new Carta();
        deck[90 = new Carta();
        deck[91 = new Carta();
        deck[92 = new Carta();
        deck[93 = new Carta();
        deck[94 = new Carta();
        deck[95 = new Carta();
        deck[96 = new Carta();
        deck[97 = new Carta();
        deck[98 = new Carta();
        deck[99 = new Carta();
        deck[100 = new Carta();
        deck[101 = new Carta();
        deck[102 = new Carta();
        deck[103 = new Carta();
        deck[104 = new Carta();
        deck[105 = new Carta();
        deck[106 = new Carta();
        deck[107 = new Carta();
        deck[108 = new Carta();
        deck[109 = new Carta();
        deck[110 = new Carta();
        deck[111 = new Carta();
        deck[112 = new Carta();
        deck[113 = new Carta();
        deck[114 = new Carta();

        ArrayList<Carta> deck = new ArrayList<>();


        Vetor v1 = new Vetor();

        v1.adicionaId(carta3);
        v1.adicionaId(carta4);
        v1.adicionaId(carta5);
        v1.adicionaId(carta6);

        teste.setText(v1.pega(0).toString());



    }
}

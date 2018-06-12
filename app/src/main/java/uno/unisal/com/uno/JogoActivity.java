package uno.unisal.com.uno;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uno.unisal.com.uno.classes.Carta;

public class JogoActivity extends Activity {
//    private Button remove;
    private ImageView draw;
    private static final String TAG = "MainActivity";
    boolean play = true;
    private int nPlayers = 4;
    Carta[] deck = new Carta[108];
    List<Integer> controlDeck = new ArrayList<>();
    List<Integer> controlShuffle = new ArrayList<>();
    private ArrayList <Integer> cardsPictures = new ArrayList<>();
    Map<Integer, List<Carta>> playerCards =  new HashMap<Integer, List<Carta>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        draw = (ImageView) findViewById(R.id.cardPileId);

        deck[0] = new Carta(R.drawable.blue_0, 0, "0", "blue");deck[1] = new Carta(R.drawable.blue_1, 1, "1", "blue");deck[2] = new Carta(R.drawable.blue_1, 2, "1", "blue");
        deck[3] = new Carta(R.drawable.blue_2, 3, "2", "blue");deck[4] = new Carta(R.drawable.blue_2, 4, "2", "blue");deck[5] = new Carta(R.drawable.blue_3, 5, "3", "blue");
        deck[6] = new Carta(R.drawable.blue_3, 6, "3", "blue");deck[7] = new Carta(R.drawable.blue_4, 7, "4", "blue");deck[8] = new Carta(R.drawable.blue_4, 8, "4", "blue");
        deck[9] = new Carta(R.drawable.blue_5, 9, "5", "blue");deck[10] = new Carta(R.drawable.blue_5, 10, "5", "blue");deck[11] = new Carta(R.drawable.blue_6, 11, "6", "blue");
        deck[12] = new Carta(R.drawable.blue_6, 12, "6", "blue");deck[13] = new Carta(R.drawable.blue_7, 13, "7", "blue");deck[14] = new Carta(R.drawable.blue_7, 14, "7", "blue");
        deck[15] = new Carta(R.drawable.blue_8, 15, "8", "blue");deck[16] = new Carta(R.drawable.blue_8, 16, "8", "blue");deck[17] = new Carta(R.drawable.blue_9, 17, "9", "blue");
        deck[18] = new Carta(R.drawable.blue_9, 18, "9", "blue");deck[19] = new Carta(R.drawable.blue_reverse, 19, "reverse", "blue");deck[20] = new Carta(R.drawable.blue_reverse, 20, "reverse", "blue");
        deck[21] = new Carta(R.drawable.blue_picker, 21, "picker", "blue");deck[22] = new Carta(R.drawable.blue_picker, 22, "picker", "blue");deck[23] = new Carta(R.drawable.blue_skip, 23, "skip", "blue");
        deck[24] = new Carta(R.drawable.blue_skip, 24, "skip", "blue");
        deck[25] = new Carta(R.drawable.yellow_0, 25, "0", "yellow");deck[26] = new Carta(R.drawable.yellow_0, 26, "1", "yellow");deck[27] = new Carta(R.drawable.yellow_1, 27, "1", "yellow");
        deck[28] = new Carta(R.drawable.yellow_1, 28, "2", "yellow");deck[29] = new Carta(R.drawable.yellow_2, 29, "2", "yellow");deck[30] = new Carta(R.drawable.yellow_2, 30, "3", "yellow");
        deck[31] = new Carta(R.drawable.yellow_3, 31, "3", "yellow");deck[32] = new Carta(R.drawable.yellow_3, 32, "4", "yellow");deck[33] = new Carta(R.drawable.yellow_4, 33, "4", "yellow");
        deck[34] = new Carta(R.drawable.yellow_4, 34, "5", "yellow");deck[35] = new Carta(R.drawable.yellow_5, 35, "5", "yellow");deck[36] = new Carta(R.drawable.yellow_5, 36, "6", "yellow");
        deck[37] = new Carta(R.drawable.yellow_6, 37, "6", "yellow");deck[38] = new Carta(R.drawable.yellow_6, 38, "7", "yellow");deck[39] = new Carta(R.drawable.yellow_7, 39, "7", "yellow");
        deck[40] = new Carta(R.drawable.yellow_8, 40, "8", "yellow");deck[41] = new Carta(R.drawable.yellow_8, 41, "8", "yellow");deck[42] = new Carta(R.drawable.yellow_9, 42, "9", "yellow");
        deck[43] = new Carta(R.drawable.yellow_9, 43, "9", "yellow");deck[44] = new Carta(R.drawable.yellow_reverse, 44, "reverse", "yellow");deck[45] = new Carta(R.drawable.yellow_reverse, 45, "reverse", "yellow");
        deck[46] = new Carta(R.drawable.yellow_picker, 46, "picker", "yellow");deck[47] = new Carta(R.drawable.yellow_picker, 47, "picker", "yellow");deck[48] = new Carta(R.drawable.yellow_skip, 48, "skip", "yellow");
        deck[49] = new Carta(R.drawable.yellow_skip, 49, "skip", "yellow");
        deck[50] = new Carta(R.drawable.green_0, 50, "0", "green");deck[51] = new Carta(R.drawable.green_1, 51, "1", "green");deck[52] = new Carta(R.drawable.green_1, 52, "1", "green");
        deck[53] = new Carta(R.drawable.green_2, 53, "2", "green");deck[54] = new Carta(R.drawable.green_2, 54, "2", "green");deck[55] = new Carta(R.drawable.green_3, 55, "3", "green");
        deck[56] = new Carta(R.drawable.green_3,56, "3", "green");deck[57] = new Carta(R.drawable.green_4, 57, "4", "green");deck[58] = new Carta(R.drawable.green_4, 58, "4", "green");
        deck[59] = new Carta(R.drawable.green_5, 59, "5", "green");deck[60] = new Carta(R.drawable.green_5, 60, "5", "green");deck[61] = new Carta(R.drawable.green_6, 61, "6", "green");
        deck[62] = new Carta(R.drawable.green_6, 62, "6", "green");deck[63] = new Carta(R.drawable.green_7, 63, "7", "green");deck[64] = new Carta(R.drawable.green_7, 64, "7", "green");
        deck[65] = new Carta(R.drawable.green_8, 65, "8", "green");deck[66] = new Carta(R.drawable.green_8, 66, "8", "green");deck[67] = new Carta(R.drawable.green_9, 67, "9", "green");
        deck[68] = new Carta(R.drawable.green_9, 68, "9", "green");deck[69] = new Carta(R.drawable.green_reverse, 69, "reverse", "green");deck[70] = new Carta(R.drawable.green_reverse, 70, "reverse", "green");
        deck[71] = new Carta(R.drawable.green_picker, 71, "picker", "green");deck[72] = new Carta(R.drawable.green_picker, 72, "picker", "green");deck[73] = new Carta(R.drawable.green_skip, 73, "skip", "green");
        deck[74] = new Carta(R.drawable.green_skip, 74, "skip", "green");
        deck[75] = new Carta(R.drawable.red_0, 75, "0", "red");deck[76] = new Carta(R.drawable.red_1, 76, "1", "red");deck[77] = new Carta(R.drawable.red_1, 77, "1", "red");
        deck[78] = new Carta(R.drawable.red_2, 78, "2", "red");deck[79] = new Carta(R.drawable.red_2, 79, "2", "red");deck[80] = new Carta(R.drawable.red_3, 80, "3", "red");
        deck[81] = new Carta(R.drawable.red_3, 81, "3", "red");deck[82] = new Carta(R.drawable.red_4, 82, "4", "red");deck[83] = new Carta(R.drawable.red_4, 83, "4", "red");
        deck[84] = new Carta(R.drawable.red_5, 84, "5", "red");deck[85] = new Carta(R.drawable.red_5, 85, "5", "red");deck[86] = new Carta(R.drawable.red_6, 86, "6", "red");
        deck[87] = new Carta(R.drawable.red_6, 87, "6", "red");deck[88] = new Carta(R.drawable.red_7, 88, "7", "red");deck[89] = new Carta(R.drawable.red_7, 89, "7", "red");
        deck[90] = new Carta(R.drawable.red_8, 90, "8", "red");deck[91] = new Carta(R.drawable.red_8, 91, "8", "red");deck[92] = new Carta(R.drawable.red_9, 92, "9", "red");
        deck[93] = new Carta(R.drawable.red_9, 93, "9", "red");deck[94] = new Carta(R.drawable.red_reverse, 94, "reverse", "red");deck[95] = new Carta(R.drawable.red_reverse, 95, "reverse", "red");
        deck[96] = new Carta(R.drawable.red_picker, 96, "picker", "red");deck[97] = new Carta(R.drawable.red_picker, 97, "picker", "red");deck[98] = new Carta(R.drawable.red_skip, 98, "skip", "red");
        deck[99] = new Carta(R.drawable.red_skip, 99, "skip", "red");
        deck[100] = new Carta(R.drawable.wild_pick_four, 100, "pickFour", "black1");deck[101] = new Carta(R.drawable.wild_pick_four, 101, "pickFour", "black1");deck[102] = new Carta(R.drawable.wild_pick_four, 102, "pickFour", "black1");
        deck[103] = new Carta(R.drawable.wild_pick_four, 103, "pickFour", "black1");deck[104] = new Carta(R.drawable.wild_color_changer, 104, "colorChange", "black2");deck[105] = new Carta(R.drawable.wild_color_changer, 105, "colorChange", "black2");
        deck[106] = new Carta(R.drawable.wild_color_changer, 106, "colorChange", "black2");deck[107] = new Carta(R.drawable.wild_color_changer, 107, "colorChange", "black2");

        startGame();
        //drawCard(playerCards.get(0));
        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCard(playerCards.get(0));
                //Toast.makeText(mContext, cardsPictures.get(position), Toast.LENGTH_SHORT).show();
                Toast.makeText(JogoActivity.this, "PESCANDO:" + playerCards.get(0).size(), Toast.LENGTH_SHORT).show();
                showCards(playerCards.get(0));
            }
        });

        while (play){
            play = false;
        }

        //onCreate end
    }


    //iniciar jogo
    public void startGame(){
        shuffleDeck();
        int indexCarta = 0;
        for (int i = 0; i < nPlayers ; i++) {
            List<Carta> hand = new ArrayList<>();
            for (int j = 0; j < 7 ; j++) {
                indexCarta = controlDeck.get(0);
                controlDeck.remove(0);
                hand.add(deck[indexCarta]);
            }
            playerCards.put(i, hand);
        }
        initRecyclerView();
        showCards(playerCards.get(0));
    }

    //clear the list and shuffle
    public void shuffleDeck(){
        controlDeck.clear();
        for (int i = 0; i < 108 ; i++) {
            controlDeck.add(i);
        }
        Collections.shuffle(controlDeck);
    }

    public void reShuffleDeck (Map<Integer, List<Carta>> playerCards){
        controlDeck.clear();
        controlShuffle.clear();
        for (int i = 0; i < 108 ; i++) {
            controlDeck.add(i);
        }
        for (int i = 0; i < nPlayers; i++) {
            for (int j = 0; j < playerCards.get(i).size() ; j++) {
                controlShuffle.add(playerCards.get(i).get(j).getId());
            }
        }

        //EXEMPLO: Arrays.sort(array, Collections.reverseOrder());
        //controlShuffle.sort();
        //ArrayUtils.rever
        Collections.shuffle(controlDeck);
    }



    public void drawCard(List<Carta> hand){
        int indexCarta = controlDeck.get(0);
        controlDeck.remove(0);
        hand.add(deck[indexCarta]);
        //showCards(hand);
    }

    private void showCards(List<Carta> handView){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");
        cardsPictures.clear();
        for (int i = 0; i < handView.size(); i++) {
            cardsPictures.add(handView.get(i).getImagem());
        }
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(cardsPictures, this);
        recyclerView.setAdapter(adapter);
    }
}

//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        int [] vetor = {4,8,2,3};
//
//        sortHighest(vetor);
//
//        for (int i = 0; i < vetor.length; i++) {
//            System.out.println(vetor[i]);
//        }
//    }
//
//
//
//    static void sortHighest (int [] vetor) {
//        Arrays.sort(vetor);
//        int a = vetor.length;
//        int j = 0;
//        int [] vetor2 = new int[a];
//        for (int i = vetor.length; i >= 0; i--) {
//            vetor2[j] = vetor[i];
//            j++;
//        }
//
//        for (int i = 0; i < vetor.length; i++) {
//            vetor[i] = vetor2[i];
//        }
//
//    }
//}
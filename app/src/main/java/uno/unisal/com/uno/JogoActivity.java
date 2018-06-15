package uno.unisal.com.uno;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import uno.unisal.com.uno.classes.Carta;
import uno.unisal.com.uno.util.CallableStatement;

import static java.lang.Thread.*;

public class JogoActivity extends Activity {
    //caso de na telha observar logs
    private static final String TAG = "MainActivity";

    //layouts
    private ConstraintLayout table;
    private ConstraintLayout chooseColor;
    //carta do canto iferior direito
    private ImageView draw;

    //numero de cartas
    private TextView numberCards0;
    private TextView numberCards1;
    private TextView numberCards2;
    private TextView numberCards3;
    private TextView numberCards4;

    //maos dos oponentes
    private ImageView cardsTop;
    private ImageView cardsRight;
    private ImageView cardsLeft;
    private ImageView slideTop;
    private ImageView slideLeft;
    private ImageView slideRight;
    static public ImageView slideBot;


    //imagens selecao de cor
    private ImageView colorRed;
    private ImageView colorGreen;
    private ImageView colorBlue;
    private ImageView colorYellow;

    //carta jogada, centro da mesa
    static public Carta cardPlayed;
    static public ImageView cardPlayedView;
    //sim, static public é muita safadeza. não julgue tanto

    //carta para os adversarios jogarem;
    private Carta selectedCard;

    //numero de jogadores será variavel, com sorte
    private int nPlayers = 4;

    //turno
    static public int playerTurn;
    static public boolean order = true;
    //carta que sera jogada
    public Carta cardToBePlayed = null;
    public int cardToBePlayedPosition = 0;

    //armazena todas as cartas
    private Carta[] deck = new Carta[100];

    //array de controle para a distribuicao de cartas
    private List<Integer> controlDeck = new ArrayList<>();

    //caso seja necessario reembaralhar,  essa variavel armazena as cartas das maos dos jogadores. elas serao retiradas do controlDeck
    private List<Integer> controlShuffle = new ArrayList<>();

    //necessario como parametro para o recyclerViewAdapter, armazena apenas os ids das imagens;
    private ArrayList <Integer> cardsPictures = new ArrayList<>();

    //mãos dos jogadores
    Map<Integer, List<Carta>> playerCards =  new HashMap<Integer, List<Carta>>();

    //animacao
//    Animation moveTop = new TranslateAnimation(0,0,0,340);
//    Animation moveLeft = new TranslateAnimation(0,855,0,0);
//    Animation moveRight = new TranslateAnimation(0,-855,0,0);
    Animation moveTop = new TranslateAnimation(0,0,0,120);
    Animation moveLeft = new TranslateAnimation(0,600,0,0);
    Animation moveRight = new TranslateAnimation(0,-600,0,0);

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    int sizePlayerHand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        startTable();
        createDeck();
        startGame();
        gameLoop();
    }

    private void startTable() {
        table = findViewById(R.id.layoutTableId);
        chooseColor = findViewById(R.id.layoutChooseColorId);

        //ligando os botoes
        draw = (ImageView) findViewById(R.id.cardPileId);
        cardPlayedView = (ImageView) findViewById(R.id.cardPlayedId);
        cardPlayedView.setImageResource(R.drawable.card_back);

        cardsTop = findViewById(R.id.cardTopId);
        cardsRight = findViewById(R.id.cardRightId);
        cardsLeft = findViewById(R.id.cardLeftId);
        slideTop = findViewById(R.id.slideTopId);
        slideLeft = findViewById(R.id.slideLeftId);
        slideRight = findViewById(R.id.slideRightId);
        slideBot = findViewById(R.id.slideBotId);

        slideTop.setVisibility(View.GONE);
        slideLeft.setVisibility(View.GONE);
        slideRight.setVisibility(View.GONE);
        slideBot.setVisibility(View.GONE);

        numberCards0 = findViewById(R.id.textCardNumber0);
        numberCards1 = findViewById(R.id.textCardNumber1);
        numberCards2 = findViewById(R.id.textCardNumber2);
        numberCards3 = findViewById(R.id.textCardNumber3);
        numberCards4 = findViewById(R.id.textCardNumber4);

        //botoes da troca de cor
        colorRed = findViewById(R.id.chooseColorRedId);
        colorGreen = findViewById(R.id.chooseColorGreenId);
        colorBlue = findViewById(R.id.chooseColorBlueId);
        colorYellow = findViewById(R.id.chooseColorYellowId);


        //botao de pesca
        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCard(playerCards.get(0));
                //Toast.makeText(JogoActivity.this, "PESCANDO:" + playerCards.get(0).size(), Toast.LENGTH_SHORT).show();
                showCards(playerCards.get(0));
                gameLoop();
            }
        });
    }

    private void moveCard(){

        moveTop.setFillAfter(true);
        moveTop.setDuration(1200);
        moveLeft.setFillAfter(true);
        moveLeft.setDuration(1200);
        moveRight.setFillAfter(true);
        moveRight.setDuration(1200);

        switch (playerTurn) {
            case 3:
                slideRight.setVisibility(View.VISIBLE);
                slideRight.setImageResource(cardPlayed.getImage());
                slideRight.startAnimation(moveRight);

                break;
            case 2:
                slideTop.setVisibility(View.VISIBLE);
                slideTop.setImageResource(cardPlayed.getImage());
                slideTop.startAnimation(moveTop);
                break;
            case 1:
                slideLeft.setVisibility(View.VISIBLE);
                slideLeft.setImageResource(cardPlayed.getImage());
                slideLeft.startAnimation(moveLeft);
                break;
            default:
                break;
        }
    }

    private void gameLoop(){
        //loop ate que alguem fique sem cartas
        if(adapter!=null){
            adapter.notifyDataSetChanged();
        }
        //vez do jogador
        if(playerTurn == 0){
            Toast.makeText(JogoActivity.this, "Sua vez!", Toast.LENGTH_SHORT).show();
            if(sizePlayerHand != playerCards.get(0).size()){
                // prender o codigo ate o jogador pescar ou jogar
                showCards(playerCards.get(0));
                sizePlayerHand = playerCards.get(0).size();
            }
            resetPlay();

        } else {
           // Toast.makeText(JogoActivity.this, "CPU!", Toast.LENGTH_SHORT).show();
            //turno do computador
            //essa funcao verifica (boolean) se existe uma carta para jogar
            boolean canPlayCard = canPlayCard();
            if(canPlayCard){
                putOnTheTable(cardToBePlayed);
                playerCards.get(playerTurn).remove(cardToBePlayedPosition);
                moveCard();
            } else {
                drawCard(playerCards.get(playerTurn));
            }

            resetPlay();
            this.gameLoop();
        }
    }


    private void resetPlay() {
        //resetar a carta que sera jogada
        cardToBePlayed = null;
        cardToBePlayedPosition = -1;
        //atualiza a mao de todos
        numberOfCards();
        cardsInHand();

        //se o jogo acabou
        if (endGame(playerCards.get(playerTurn))) {
            Toast.makeText(JogoActivity.this, "O jogador: " + playerTurn+1 + " venceu!", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(IntroActivity.this, LoginActivity.class));
        } else {

            if(cardPlayed.getSymbol().equals("skip")){
                playerTurn++;
            }

            if(cardPlayed.getSymbol().equals("reverse")){
                order = !order;
            }
            //se estiver no sentido horario
            if(order){
                drawExtra();
                if(playerTurn == 3){
                    playerTurn = 0;
                } else if (playerTurn > 3){
                    playerTurn = 1;
                }else {
                    playerTurn++;
                }
            } else {
                //sentido antihorario
                drawExtra();
                if(playerTurn == 0){
                    playerTurn = 3;
                } else if (playerTurn < 0){
                    playerTurn = 2;
                }else {
                    playerTurn--;
                }

            }
        }
    }

    private void drawExtra(){

        if(cardPlayed.getSymbol().equals("picker")) {
            drawCard(playerCards.get(playerTurn));
            drawCard(playerCards.get(playerTurn));
        } else if(cardPlayed.getSymbol().equals("pickFour")) {
            drawCard(playerCards.get(playerTurn));
            drawCard(playerCards.get(playerTurn));
            drawCard(playerCards.get(playerTurn));
            drawCard(playerCards.get(playerTurn));
            playerTurn++;
            gameLoop();
        }
    }

    private boolean canPlayCard() {
        return playCard(playerCards.get(playerTurn));
    }

    //jogadas simples
    public boolean playCard(List<Carta> hand){
        boolean isGoingToPlay = false;
        for (int i = 0; i < hand.size(); i++) {
            if(hand.get(i).getSymbol().equals(cardPlayed.getSymbol()) || hand.get(i).getColor().equals(cardPlayed.getColor()) || hand.get(i).getColor().equals("black")){
                if(cardToBePlayed == null){
                    cardToBePlayed = hand.get(i);
                    cardToBePlayedPosition = i;
                    isGoingToPlay = true;
                } else {
                    if (hand.get(i).getValue() > cardToBePlayed.getValue()) {
                        cardToBePlayed = hand.get(i);
                        cardToBePlayedPosition = i;
                    }
                }
            }
        }

        return isGoingToPlay;
    }

    //finalizar jogo
    public boolean endGame(List<Carta> hand){
        return hand.isEmpty();
    }

    //shuffle para o começo do jogo
    public void shuffleStart(){
        controlDeck.clear();
        for (int i = 0; i < 100 ; i++) {
            controlDeck.add(i);
        }
        Collections.shuffle(controlDeck);
    }

    //pegar a próxima carta do monte
    public Carta nextCard(){
        Carta nCard;
        int indexCarta;
        indexCarta = controlDeck.get(0);
        controlDeck.remove(0);
        nCard = deck[indexCarta];
        return nCard;
    }

    //colocar carta na mesa
    public void putOnTheTable(Carta card){
        cardPlayed = card;
        cardPlayedView.setImageResource(cardPlayed.getImage());
    }

    //iniciar jogo
    public void startGame(){
        shuffleStart();
        //pega o valor do deck de controle e remove do deck principal
        int indexCarta = 0;
        //coloca a primeira carta na mesa
        cardPlayed = nextCard();
        cardPlayedView.setImageResource(cardPlayed.getImage());

        //criar uma lista no mapa para cada jogador
        for (int i = 0; i < nPlayers ; i++) {
            List<Carta> hand = new ArrayList<>();
            for (int j = 0; j < 7 ; j++) {
                // adicionar cartas numa mao
                hand.add(nextCard());
            }
            //adicionar a mao criada no mapa
            playerCards.put(i, hand);
        }

        //exibir mao
        sizePlayerHand = playerCards.get(0).size();
        showCards(playerCards.get(0));
        Random rand = new Random();
//        playerTurn = rand.nextInt(4);
        playerTurn = 0;
    }

    //shuffle para quando as cartas acabarem
    public void shuffleDeck (Map<Integer, List<Carta>> playerCards){
        controlDeck.clear();
        controlShuffle.clear();
        //recriar o deck de controle
        for (int i = 0; i < 100 ; i++) {
            controlDeck.add(i);
        }
        //adicionar as cartas das maos e da mesa em um deck.
        for (int i = 0; i < nPlayers; i++) {
            for (int j = 0; j < playerCards.get(i).size() ; j++) {
                controlShuffle.add(playerCards.get(i).get(j).getId());
            }
        }
        controlShuffle.add(cardPlayed.getId());

        //tirar as cartas usadas do deck de controle
        Collections.sort(controlShuffle, Collections.reverseOrder());
        for (int i = 0; i < controlShuffle.size() ; i++) {
            controlDeck.remove(controlShuffle.get(i));
        }

        //embaralhar o restante das cartas
        Collections.shuffle(controlDeck);
        Toast.makeText(JogoActivity.this, "Cartas adicionadas ao deck: " + controlDeck.size(), Toast.LENGTH_SHORT).show();
    }

    //pescar
    public void drawCard(List<Carta> hand){
        if(controlDeck.size() == 0){
            shuffleDeck(playerCards);
        }

        int indexCarta = controlDeck.get(0);
        controlDeck.remove(0);
        hand.add(deck[indexCarta]);
        //Toast.makeText(JogoActivity.this, "Cartas restantes: " + controlDeck.size(), Toast.LENGTH_SHORT).show();
        //showCards(hand);
    }

    private void validateGameLoopCall(){
        gameLoop();
    }

    //exibir mão do jogador
    public void showCards(List<Carta> handView){
        //Log.d(TAG, "showing cards");
        cardsPictures.clear();
        for (int i = 0; i < handView.size(); i++) {
            cardsPictures.add(handView.get(i).getImage());
        }
        initRecyclerView(handView);
    }

    //iniciar a visualizaca da mao
    private void initRecyclerView(List<Carta> handView){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(handView, cardsPictures, this, new CallableStatement() {
            @Override
            public void callGameLoop() {
                validateGameLoopCall();
            }

            @Override
            public boolean canPlayCard(int position) {
                Carta carta = playerCards.get(0).get(position);
                if(carta != null){
                    return carta.getSymbol().equals(cardPlayed.getSymbol()) || carta.getColor().equals(cardPlayed.getColor()) || carta.getColor().equals("black1") ||carta.getColor().equals("black2");
                }
                return false;
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void numberOfCards(){
        numberCards0.setText(String.valueOf(playerCards.get(0).size()));
        numberCards1.setText(String.valueOf(playerCards.get(1).size()));
        numberCards2.setText(String.valueOf(playerCards.get(2).size()));
        numberCards3.setText(String.valueOf(playerCards.get(3).size()));
        numberCards4.setText(String.valueOf(controlDeck.size()));
    }

    private void cardsInHand(){
        int a = playerCards.get(1).size();
        int b = playerCards.get(2).size();
        int c = playerCards.get(3).size();

        switch (a) {
            case 4:
                cardsLeft.setImageResource(R.drawable.cardsleft4);
                break;
            case 3:
                cardsLeft.setImageResource(R.drawable.cardsleft3);
                break;
            case 2:
                cardsLeft.setImageResource(R.drawable.cardsleft2);
                break;
            case 1:
                cardsLeft.setImageResource(R.drawable.cardsleft1);
                break;
            default:
                cardsLeft.setImageResource(R.drawable.cardsleft5);
                break;
        }

        switch (b) {
            case 4:
                cardsTop.setImageResource(R.drawable.cardstop4);
                break;
            case 3:
                cardsTop.setImageResource(R.drawable.cardstop3);
                break;
            case 2:
                cardsTop.setImageResource(R.drawable.cardstop2);
                break;
            case 1:
                cardsTop.setImageResource(R.drawable.cardstop1);
                break;
            default:
                cardsTop.setImageResource(R.drawable.cardstop5);
                break;
        }

        switch (c) {
            case 4:
                cardsRight.setImageResource(R.drawable.cardsright4);
                break;
            case 3:
                cardsRight.setImageResource(R.drawable.cardsright3);
                break;
            case 2:
                cardsRight.setImageResource(R.drawable.cardsright2);
                break;
            case 1:
                cardsRight.setImageResource(R.drawable.cardsright1);
                break;
            default:
                cardsRight.setImageResource(R.drawable.cardsright5);
                break;
        }
//        if(playerCards.get(1).size() > 4){
//            cardsLeft.setImageResource(R.drawable.cardsleft5);
//        }else if(playerCards.get(1).size() == 4){
//            cardsLeft.setImageResource(R.drawable.cardsleft4);
//        }else if(playerCards.get(1).size() == 3){
//            cardsLeft.setImageResource(R.drawable.cardsleft3);
//        }else if(playerCards.get(1).size() == 2){
//            cardsLeft.setImageResource(R.drawable.cardsleft2);
//        } else if(playerCards.get(1).size() == 1){
//            cardsLeft.setImageResource(R.drawable.cardsleft1);
//        }

//        if(playerCards.get(2).size() > 4){
//            cardsLeft.setImageResource(R.drawable.cardstop5);
//        }else if(playerCards.get(2).size() == 4){
//            cardsLeft.setImageResource(R.drawable.cardstop4);
//        }else if(playerCards.get(2).size() == 3){
//            cardsLeft.setImageResource(R.drawable.cardstop3);
//        }else if(playerCards.get(2).size() == 2){
//            cardsLeft.setImageResource(R.drawable.cardstop2);
//        } else if(playerCards.get(2).size() == 1){
//            cardsLeft.setImageResource(R.drawable.cardstop1);
//        }
//
//        if(playerCards.get(3).size() > 4){
//            cardsLeft.setImageResource(R.drawable.cardsright5);
//        }else if(playerCards.get(3).size() == 4){
//            cardsLeft.setImageResource(R.drawable.cardsright4);
//        }else if(playerCards.get(3).size() == 3){
//            cardsLeft.setImageResource(R.drawable.cardsright3);
//        }else if(playerCards.get(3).size() == 2){
//            cardsLeft.setImageResource(R.drawable.cardsright2);
//        } else if(playerCards.get(3).size() == 1){
//            cardsLeft.setImageResource(R.drawable.cardsright1);
//        }


    }

    private void createDeck() {
        //declaracao de todas as cartas
        deck[0] = new Carta(R.drawable.blue_0, 0, "0", "blue", 0);
        deck[1] = new Carta(R.drawable.blue_1, 1, "1", "blue", 1);
        deck[2] = new Carta(R.drawable.blue_1, 2, "1", "blue", 1);
        deck[3] = new Carta(R.drawable.blue_2, 3, "2", "blue", 2);
        deck[4] = new Carta(R.drawable.blue_2, 4, "2", "blue", 2);
        deck[5] = new Carta(R.drawable.blue_3, 5, "3", "blue", 3);
        deck[6] = new Carta(R.drawable.blue_3, 6, "3", "blue", 3);
        deck[7] = new Carta(R.drawable.blue_4, 7, "4", "blue", 4);
        deck[8] = new Carta(R.drawable.blue_4, 8, "4", "blue", 4);
        deck[9] = new Carta(R.drawable.blue_5, 9, "5", "blue", 5);
        deck[10] = new Carta(R.drawable.blue_5, 10, "5", "blue", 5);
        deck[11] = new Carta(R.drawable.blue_6, 11, "6", "blue", 6);
        deck[12] = new Carta(R.drawable.blue_6, 12, "6", "blue", 6);
        deck[13] = new Carta(R.drawable.blue_7, 13, "7", "blue", 7);
        deck[14] = new Carta(R.drawable.blue_7, 14, "7", "blue", 7);
        deck[15] = new Carta(R.drawable.blue_8, 15, "8", "blue", 8);
        deck[16] = new Carta(R.drawable.blue_8, 16, "8", "blue", 8);
        deck[17] = new Carta(R.drawable.blue_9, 17, "9", "blue", 9);
        deck[18] = new Carta(R.drawable.blue_9, 18, "9", "blue", 9);
        deck[19] = new Carta(R.drawable.blue_reverse, 19, "reverse", "blue", 20);
        deck[20] = new Carta(R.drawable.blue_reverse, 20, "reverse", "blue", 20);
        deck[21] = new Carta(R.drawable.blue_picker, 21, "picker", "blue", 20);
        deck[22] = new Carta(R.drawable.blue_picker, 22, "picker", "blue", 20);
        deck[23] = new Carta(R.drawable.blue_skip, 23, "skip", "blue", 20);
        deck[24] = new Carta(R.drawable.blue_skip, 24, "skip", "blue", 20);
        deck[25] = new Carta(R.drawable.yellow_0, 25, "0", "yellow", 0);
        deck[26] = new Carta(R.drawable.yellow_1, 26, "1", "yellow", 1);
        deck[27] = new Carta(R.drawable.yellow_1, 27, "1", "yellow", 1);
        deck[28] = new Carta(R.drawable.yellow_2, 28, "2", "yellow", 2);
        deck[29] = new Carta(R.drawable.yellow_2, 29, "2", "yellow", 2);
        deck[30] = new Carta(R.drawable.yellow_3, 30, "3", "yellow", 3);
        deck[31] = new Carta(R.drawable.yellow_3, 31, "3", "yellow", 3);
        deck[32] = new Carta(R.drawable.yellow_4, 32, "4", "yellow", 4);
        deck[33] = new Carta(R.drawable.yellow_4, 33, "4", "yellow", 4);
        deck[34] = new Carta(R.drawable.yellow_5, 34, "5", "yellow", 5);
        deck[35] = new Carta(R.drawable.yellow_5, 35, "5", "yellow", 5);
        deck[36] = new Carta(R.drawable.yellow_6, 36, "6", "yellow", 6);
        deck[37] = new Carta(R.drawable.yellow_6, 37, "6", "yellow", 6);
        deck[38] = new Carta(R.drawable.yellow_7, 38, "7", "yellow", 7);
        deck[39] = new Carta(R.drawable.yellow_7, 39, "7", "yellow", 7);
        deck[40] = new Carta(R.drawable.yellow_8, 40, "8", "yellow", 8);
        deck[41] = new Carta(R.drawable.yellow_8, 41, "8", "yellow", 8);
        deck[42] = new Carta(R.drawable.yellow_9, 42, "9", "yellow", 9);
        deck[43] = new Carta(R.drawable.yellow_9, 43, "9", "yellow", 9);
        deck[44] = new Carta(R.drawable.yellow_reverse, 44, "reverse", "yellow", 20);
        deck[45] = new Carta(R.drawable.yellow_reverse, 45, "reverse", "yellow", 20);
        deck[46] = new Carta(R.drawable.yellow_picker, 46, "picker", "yellow", 20);
        deck[47] = new Carta(R.drawable.yellow_picker, 47, "picker", "yellow", 20);
        deck[48] = new Carta(R.drawable.yellow_skip, 48, "skip", "yellow", 20);
        deck[49] = new Carta(R.drawable.yellow_skip, 49, "skip", "yellow", 20);
        deck[50] = new Carta(R.drawable.green_0, 50, "0", "green", 0);
        deck[51] = new Carta(R.drawable.green_1, 51, "1", "green", 1);
        deck[52] = new Carta(R.drawable.green_1, 52, "1", "green", 1);
        deck[53] = new Carta(R.drawable.green_2, 53, "2", "green", 2);
        deck[54] = new Carta(R.drawable.green_2, 54, "2", "green", 2);
        deck[55] = new Carta(R.drawable.green_3, 55, "3", "green", 3);
        deck[56] = new Carta(R.drawable.green_3, 56, "3", "green", 3);
        deck[57] = new Carta(R.drawable.green_4, 57, "4", "green", 4);
        deck[58] = new Carta(R.drawable.green_4, 58, "4", "green", 4);
        deck[59] = new Carta(R.drawable.green_5, 59, "5", "green", 5);
        deck[60] = new Carta(R.drawable.green_5, 60, "5", "green", 5);
        deck[61] = new Carta(R.drawable.green_6, 61, "6", "green", 6);
        deck[62] = new Carta(R.drawable.green_6, 62, "6", "green", 6);
        deck[63] = new Carta(R.drawable.green_7, 63, "7", "green", 7);
        deck[64] = new Carta(R.drawable.green_7, 64, "7", "green", 7);
        deck[65] = new Carta(R.drawable.green_8, 65, "8", "green", 8);
        deck[66] = new Carta(R.drawable.green_8, 66, "8", "green", 8);
        deck[67] = new Carta(R.drawable.green_9, 67, "9", "green", 9);
        deck[68] = new Carta(R.drawable.green_9, 68, "9", "green", 9);
        deck[69] = new Carta(R.drawable.green_reverse, 69, "reverse", "green", 20);
        deck[70] = new Carta(R.drawable.green_reverse, 70, "reverse", "green", 20);
        deck[71] = new Carta(R.drawable.green_picker, 71, "picker", "green", 20);
        deck[72] = new Carta(R.drawable.green_picker, 72, "picker", "green", 20);
        deck[73] = new Carta(R.drawable.green_skip, 73, "skip", "green", 20);
        deck[74] = new Carta(R.drawable.green_skip, 74, "skip", "green", 20);
        deck[75] = new Carta(R.drawable.red_0, 75, "0", "red", 0);
        deck[76] = new Carta(R.drawable.red_1, 76, "1", "red", 1);
        deck[77] = new Carta(R.drawable.red_1, 77, "1", "red", 1);
        deck[78] = new Carta(R.drawable.red_2, 78, "2", "red", 2);
        deck[79] = new Carta(R.drawable.red_2, 79, "2", "red", 2);
        deck[80] = new Carta(R.drawable.red_3, 80, "3", "red", 3);
        deck[81] = new Carta(R.drawable.red_3, 81, "3", "red", 3);
        deck[82] = new Carta(R.drawable.red_4, 82, "4", "red", 4);
        deck[83] = new Carta(R.drawable.red_4, 83, "4", "red", 4);
        deck[84] = new Carta(R.drawable.red_5, 84, "5", "red", 5);
        deck[85] = new Carta(R.drawable.red_5, 85, "5", "red", 5);
        deck[86] = new Carta(R.drawable.red_6, 86, "6", "red", 6);
        deck[87] = new Carta(R.drawable.red_6, 87, "6", "red", 6);
        deck[88] = new Carta(R.drawable.red_7, 88, "7", "red", 7);
        deck[89] = new Carta(R.drawable.red_7, 89, "7", "red", 7);
        deck[90] = new Carta(R.drawable.red_8, 90, "8", "red", 8);
        deck[91] = new Carta(R.drawable.red_8, 91, "8", "red", 8);
        deck[92] = new Carta(R.drawable.red_9, 92, "9", "red", 9);
        deck[93] = new Carta(R.drawable.red_9, 93, "9", "red", 9);
        deck[94] = new Carta(R.drawable.red_reverse, 94, "reverse", "red", 20);
        deck[95] = new Carta(R.drawable.red_reverse, 95, "reverse", "red", 20);
        deck[96] = new Carta(R.drawable.red_picker, 96, "picker", "red", 20);
        deck[97] = new Carta(R.drawable.red_picker, 97, "picker", "red", 20);
        deck[98] = new Carta(R.drawable.red_skip, 98, "skip", "red", 20);
        deck[99] = new Carta(R.drawable.red_skip, 99, "skip", "red", 20);
//        deck[100] = new Carta(R.drawable.wild_pick_four, 100, "pickFour", "black", 50);
//        deck[101] = new Carta(R.drawable.wild_pick_four, 101, "pickFour", "black", 50);
//        deck[102] = new Carta(R.drawable.wild_pick_four, 102, "pickFour", "black", 50);
//        deck[103] = new Carta(R.drawable.wild_pick_four, 103, "pickFour", "black", 50);
//        deck[104] = new Carta(R.drawable.wild_color_changer, 104, "colorChange", "black", 40);
//        deck[105] = new Carta(R.drawable.wild_color_changer, 105, "colorChange", "black", 40);
//        deck[106] = new Carta(R.drawable.wild_color_changer, 106, "colorChange", "black", 40);
//        deck[107] = new Carta(R.drawable.wild_color_changer, 107, "colorChange", "black", 40);
    }
}
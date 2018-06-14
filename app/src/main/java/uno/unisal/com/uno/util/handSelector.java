package uno.unisal.com.uno.util;

public class handSelector {

    private int selectedPlayer = 0;
    private final int playerCount = 4;

    public int getNextPlayer(){
        resetCount();
        return ++selectedPlayer;
    }

    private void resetCount(){
        if(selectedPlayer == playerCount){
            selectedPlayer = 0;
        }
    }
}

package handlers;

import java.lang.reflect.Method;

class Stats {

    private static Stats single_instance = null;

    public static int player1Wins;
    public static int player1Losses;
    public static int player1Draws;

    public static int player2Wins;
    public static int player2Losses;
    public static int player2Draws;

    public Stats()
    {
        player1Wins = 0;
        player1Losses = 0;
        player1Draws = 0;

        player2Wins = 0;
        player2Losses = 0;
        player2Draws = 0;
    }

    public static Stats getInstance()
    {
        if (single_instance == null)
            single_instance = new Stats();

        return single_instance;
    }

    public static void win(Integer player) {
        if (player == 1) {
            player1Wins += 1;
            player2Losses += 1;
        } else {
            player2Wins += 1;
            player1Losses += 1;
        }
    }

    public static void draw() {
        player1Draws += 1;
        player2Draws += 1;
    }

}
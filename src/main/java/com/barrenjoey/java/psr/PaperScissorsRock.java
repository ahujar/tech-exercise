package com.barrenjoey.java.psr;

import java.util.Scanner;

public class PaperScissorsRock {

    public static int playTheGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player1: Choose (p)aper, (s)cissors, (r)ock");
        String player_1_move = scanner.nextLine();
        System.out.println("Player2: Choose (p)aper, (s)cissors, (r)ock");
        String player_2_move = scanner.nextLine();
        switch (player_1_move.trim().toLowerCase()) {
            case "p":
                if ("p".equals(player_2_move)) {
                    System.out.println("It's a tie!");
                    return 0;
                }
                if ("s".equals(player_2_move)) {
                    System.out.println("Player 2 wins");
                    return 2;
                } else if ("r".equals(player_2_move)) {
                    System.out.println("Player 1 wins");
                    return 1;
                }
                break;

            case "s":
                if ("p".equals(player_2_move)) {
                    System.out.println("Player 1 wins");
                    return 1;
                }
                if ("s".equals(player_2_move)) {
                    System.out.println("It's a tie!");
                    return 0;
                } else if ("r".equals(player_2_move)) {
                    System.out.println("Player 2 wins");
                    return 2;
                }
                break;

            case "r":
                if ("p".equals(player_2_move)) {
                    System.out.println("Player 2 wins");
                    return 1;
                }
                if ("s".equals(player_2_move)) {
                    System.out.println("Player 1 wins");
                    return 0;
                } else if ("r".equals(player_2_move)) {
                    System.out.println("It's a tie!");
                    return 2;
                }
                break;
        }
        return 0;
    }


    public static void main(String[] args) {
        PaperScissorsRock x = new PaperScissorsRock();
        x.playTheGame();
    }
}

package com.manish;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Tic tac toe game");

        Player player1 = new Player();
        player1.setId(1);
        Scanner name = new Scanner(System.in);
        System.out.println("Please select player1 name:: ");
        String playerName1 = name.next();
        player1.setName(playerName1);
        player1.setPlayerSymbol('X');

        Player player2 = new Player();
        player2.setId(2);
        // player2.setName("Shyam");
        System.out.println("Please select player2 name:: ");
        String playerName2 = name.next();
        player2.setName(playerName2);
        player2.setPlayerSymbol('O');


        Player[] players = new Player[]{player1, player2};

        GameBoard gb = new GameBoard(3, players);
        gb.startGame();
    }
}

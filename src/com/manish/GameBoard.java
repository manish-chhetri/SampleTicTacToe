package com.manish;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GameBoard {
    char[][] board;
    int boardSize;
    Queue<Player> players;
    Scanner input;

    public GameBoard(int boardSize, Player[] players) {
        this.boardSize = boardSize;
        this.board = new char[2*boardSize-1][2*boardSize-1];
        initializeBoard(this.board);
        this.players = new LinkedList<>();
        this.players.add(players[0]);
        this.players.add(players[1]);
        input = new Scanner(System.in);
    }

    private void initializeBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (i%2 == 0 && j%2 == 1) {
                    board[i][j] = '|';
                } else if (i % 2 != 0) {
                    board[i][j] = '=';
                } else {
                    board[i][j] = ' ';
                }
            }
        }
    }

    public void startGame() {
        int move = 0;

        while (true) {
            move++;

            if (move >= ((boardSize*boardSize) + 1)) {
                printBoard();
                System.out.println("Match has been draw");
                break;
            }

            Player p = players.poll();
            int position = getUserInput(p);

            int row = 2 * (position%boardSize == 0 ?(position/boardSize)-1:position/boardSize);
            int col = 2 * (position%boardSize == 0 ?boardSize-1:position%boardSize-1);

            //System.out.println(row + " :: " + col + " :: " + boardSize);

            board[row][col] = p.playerSymbol;
            //System.out.println("Reached");

            if (move >= boardSize && checkGameEnd(p, row, col)) {
                this.printBoard();
                System.out.println(p.getName() + " has won the match");
                break;
            }

            players.add(p);
        }

    }

    private boolean checkGameEnd(Player p, int row, int col) {
        String winString = null;
        String rowString = null;
        String colString = null;
        String diagonalString = null;
        String revDiagonalString = null;

        for (int i = 0; i < boardSize; i++) {
            winString += p.getPlayerSymbol();
        }

        for (int i = 0; i < board.length; i=i+2) {
            rowString += board[row][i];
            colString += board[i][col];


            if (board[row].equals(board[col])) {
                diagonalString += board[i][i];
            }

            if ((row+col) == board.length-1) {
                revDiagonalString += board[i][board.length-i-1];
            }
        }

        if (winString.equals(rowString) || winString.equals(colString)
                || winString.equals(diagonalString) || winString.equals(revDiagonalString)) {
            return true;
        }
        return false;
    }

    private int getUserInput(Player p) {
        this.printBoard();
        System.out.println(p.getName() + " please enter a value between 1 " +
                "and " + this.boardSize*this.boardSize + ": ");

        int val = this.input.nextInt();
        while (!isValidInput(val)) {
            System.out.println(p.getName() + ", value entered by you is not valid, please enter a value between " +
                    "1 and " + this.boardSize*this.boardSize + ": ");
            val = this.input.nextInt();
        }
        return val;
    }

    private boolean isValidInput(int val) {
        if (val < 1 || val > (boardSize*boardSize)) {
            return false;
        }

        int row = 2 * (val%boardSize == 0 ?(val/boardSize)-1:val/boardSize);
        int col = 2 * (val%boardSize == 0 ?boardSize-1:val%boardSize-1);

        if (this.board[row][col] != ' ') {
            //System.out.println(row + "::"+col + " Value:: " + board[row][col]);
            return false;
        }
        return true;
    }

    public void printBoard() {
        for (char[] row:
                this.board) {
            for (char col:
                    row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
}

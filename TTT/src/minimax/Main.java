package minimax;

import java.io.Console;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        char[][] currBoard = board.initBoard();
        ArrayList<Integer> availableSpaces = board.getAvailableSpaces();
        board.printBoard(currBoard);

        Console cons = System.console();
        while (board.hasEmptySpace(currBoard)) {
            String humanInput = cons.readLine("Choose an open spot to play (1-9)\n");
            int humanInputInt = Integer.parseInt(humanInput);
            char [][] newBoard = board.playTurn(currBoard, humanInputInt, board.getHuman());
            availableSpaces.remove(availableSpaces.indexOf(humanInputInt));
           // System.out.println(availableSpaces);
            board.printBoard(newBoard);
            currBoard = newBoard;
            if (board.isWin(currBoard)) {
                System.out.println("Congratulations! You won!");
                break;
            } else if (!board.hasEmptySpace(currBoard)) {
                break;
            } else {
                //for computer playing using minimax
                int computerInput = availableSpaces.indexOf(board.getBestMove(currBoard, availableSpaces));
                newBoard = board.playTurn(currBoard, availableSpaces.get(computerInput), board.getComputer());
                availableSpaces.remove(computerInput);
            System.out.println(availableSpaces);
                board.printBoard(newBoard);
                currBoard = newBoard;
                if (board.isWin(currBoard)) {
                    System.out.println("Sorry you lost! The computer won.");
                    break;
                }
            }

        }
        if (!board.hasEmptySpace(currBoard) && !board.isWin(currBoard)){
            System.out.println("It's a draw!");
        }

    }
    
}

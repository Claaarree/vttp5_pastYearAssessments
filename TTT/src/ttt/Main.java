package ttt;

import java.io.Console;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        char[][] currBoard = board.initBoard();
        ArrayList<Integer> availableSpaces = board.getAvailableSpaces();
        board.printBoard(currBoard);

        Player human = new Player('x');
        Player computer = new Player('o');
        Random random = new Random();
        
        Console cons = System.console();
        while (board.hasEmptySpace(currBoard)) {
            String humanInput = cons.readLine("Choose an open spot to play (1-9)\n");
            int humanInputInt = Integer.parseInt(humanInput);
            char [][] newBoard = human.playTurn(currBoard, humanInputInt);
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
                int computerInput = random.nextInt(availableSpaces.size());
                newBoard = computer.playTurn(currBoard, availableSpaces.get(computerInput));
                availableSpaces.remove(computerInput);
            //System.out.println(availableSpaces);
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

package ttt;

import java.util.ArrayList;

public class Board {
    private ArrayList<Integer> availableSpaces = new ArrayList<>();

    public char[][] initBoard(){
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}};
            for (int i = 1; i <= 9; i++){
                availableSpaces.add(i);
            }
        return board;
    }

   public void printBoard(char[][] boardToPrint) {
    System.out.printf("%c|%c|%c\n", boardToPrint[0][0], boardToPrint[0][1], boardToPrint[0][2]);
    System.out.println("-----");
    System.out.printf("%c|%c|%c\n", boardToPrint[1][0], boardToPrint[1][1], boardToPrint[1][2]);
    System.out.println("-----");
    System.out.printf("%c|%c|%c\n", boardToPrint[2][0], boardToPrint[2][1], boardToPrint[2][2]);
    System.out.println();
    }

   public boolean isWin(char[][] currBoard) {
    //checking rows
    for (int i = 0; i < currBoard.length; i++){
        if (currBoard[i][0] == currBoard[i][1] && currBoard[i][1] == currBoard[i][2] 
        && currBoard[i][0] != ' ') {
            return true;
        } 
    }
    //cheking columns
    for (int j = 0 ; j < currBoard[0].length; j++) {
        if (currBoard[0][j] == currBoard [1][j] && currBoard[1][j] == currBoard[2][j]
        && currBoard[0][j] != ' '){
            return true;
        }
    }
    //checking diagonals
    if (currBoard[0][0] == currBoard[1][1] && currBoard[1][1] == currBoard[2][2]
    && currBoard[0][0] != ' '){
        return true;
    } else if (currBoard[2][0] == currBoard[1][1] && currBoard[1][1] == currBoard[0][2]
    && currBoard[2][0] != ' ') {
        return true;
    } else return false;
   }

   public boolean hasEmptySpace(char[][] currBoard){
    //checking in each space of the board if there is a space
    for (int i = 0; i < currBoard.length; i++){
        for (int j = 0; j < currBoard[0].length; j++){
            if (currBoard[i][j] == ' '){
                return true;
            }
        }
    }
    return false;
   }

public ArrayList<Integer> getAvailableSpaces() {
    return availableSpaces;
}

}

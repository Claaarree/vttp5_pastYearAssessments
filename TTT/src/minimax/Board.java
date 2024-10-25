 package minimax;

import java.util.ArrayList;

public class Board {
    private ArrayList<Integer> availableSpaces = new ArrayList<>();
    private final char human = 'x';
    private final char computer = 'o';

    public char getHuman() {
        return human;
    }

    public char getComputer() {
        return computer;
    }

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

    public char[][] playTurn(char[][] currBoard, int placement, char marker) {
        switch (placement) {
            case 1:
                currBoard[0][0] = marker;
                break;
        
            case 2:
                currBoard[0][1] = marker;
                break;
            case 3:
                currBoard[0][2] = marker;
                break;
            case 4:
                currBoard[1][0] = marker;
                break;
            case 5:
                currBoard[1][1] = marker;
                break;
            case 6:
                currBoard[1][2] = marker;
                break;
            case 7:
                currBoard[2][0] = marker;
                break;
            case 8:
                currBoard[2][1] = marker;
                break;
            case 9:
                currBoard[2][2] = marker;
                break;
            default:
                break;
        }
        char[][] newBoard = currBoard;
        return newBoard;
    }
    public char[][] undoTurn(char[][] newBoard, int placement) {
        switch (placement) {
            case 1:
                newBoard[0][0] = ' ';
                break;
        
            case 2:
                newBoard[0][1] = ' ';
                break;
            case 3:
                newBoard[0][2] = ' ';
                break;
            case 4:
                newBoard[1][0] = ' ';
                break;
            case 5:
                newBoard[1][1] = ' ';
                break;
            case 6:
                newBoard[1][2] = ' ';
                break;
            case 7:
                newBoard[2][0] = ' ';
                break;
            case 8:
                newBoard[2][1] = ' ';
                break;
            case 9:
                newBoard[2][2] = ' ';
                break;
            default:
                break;
        }
        char[][] currBoard = newBoard;
        return currBoard;
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

    public int getBestMove(char[][] currBoard, ArrayList<Integer> availableSpaces){
        int bestScore = Integer.MIN_VALUE;
        int bestMove = 0;
        for (int i = 0; i < availableSpaces.size(); i++){
            //go to space
            int move = availableSpaces.get(i);
            availableSpaces.remove(i);
            char[][] newBoard = playTurn(currBoard, move, computer);
            int moveScore = minimax(newBoard, availableSpaces, 0, false);
            //System.out.println("move score: " + moveScore);
            //undo space
            availableSpaces.add(i, move);
            currBoard = undoTurn(newBoard, move);

            if (moveScore > bestScore){
                bestScore = moveScore;
                bestMove = availableSpaces.get(i);
            }
           
        }
        //System.out.println("returned best move: " + bestMove);
        return bestMove;
    }

    private int minimax(char[][] currBoard, ArrayList<Integer> availableSpaces, int depth, boolean isMaximising) {
        int score = 0;
        if (isWin(currBoard) && !isMaximising) {
            score = 10 - depth;
            return score;
        } else if (isWin(currBoard) && isMaximising){
            score = -10 + depth;
            return score;
        } else if (availableSpaces.size() == 0){
            //all spaces played but nobody won
            return 0;
        }else if (isMaximising){
            score = Integer.MIN_VALUE;
            for (int i = 0; i < availableSpaces.size(); i++){
                int move = availableSpaces.get(i);
                char[][] newBoard = playTurn(currBoard, move, computer);
               
                availableSpaces.remove(i);
                
                int newScore = minimax(newBoard, availableSpaces, depth + 1, false);
                if (newScore > score){
                    score = newScore;
                }
                
                //undo move
                availableSpaces.add(i, move);
                currBoard = undoTurn(newBoard, move);
                //System.out.println("minimax max: " + score);
                
            }
        } else if (!isMaximising){           
            score = Integer.MAX_VALUE;
            for (int i = 0; i < availableSpaces.size(); i++){
                int move = availableSpaces.get(i);

                char[][] newBoard = playTurn(currBoard, move, human);
                
                availableSpaces.remove(i);
                int newScore = minimax(newBoard, availableSpaces, depth + 1, true);
                if (newScore < score){
                    score = newScore;
                }
                
                //undo move
                availableSpaces.add(i, move);
                currBoard = undoTurn(newBoard, move);
               //System.out.println("minimax min: " + score);
            }
        }
        //System.out.println("returned score: " + score);
        return score;
    }


}

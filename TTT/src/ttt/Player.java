package ttt;

public class Player {
    private char marker;

    public Player(char marker) {
        this.marker = marker;
    }

    public char[][] playTurn(char[][] currBoard, int placement) {
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
}

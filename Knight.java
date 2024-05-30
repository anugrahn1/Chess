public class Knight extends Piece {
    public Knight(int row, int col, String color) {
        super(row, col, color, "Knight");
    }

    public boolean isValidMove(int[] end, Piece[][] board) {
        String color = super.getColor();
        int[] start = super.getCoords();

        int rowDiff = Math.abs(start[0] - end[0]);
        int colDiff = Math.abs(start[1] - end[1]);

        boolean isValidMove;

        //if it makes the L shape it is valid
        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))
            isValidMove = true;
        else
            isValidMove = false;

        // if it is making some other shape it is not valid
        if (!isValidMove)
            return false;
        

        // can move to an empty spot
        if (board[end[0]][end[1]] == null){
            return true;
        }

        //cant move to a spot with the same color piece
        if (board[end[0]][end[1]].getColor().equals(color)){
            return false;
        }

        //can move to a spot if it has an opposite color piece
        return true; 
    }
}
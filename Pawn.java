public class Pawn extends Piece {
    private boolean canPromote;
    
    public Pawn(int row, int col, String color) {
        super(row, col, color, "Pawn");
        canPromote = false;
    }

    public boolean getPromotionStatus(){
        return canPromote;
    }
    public boolean isValidMove(int[] end, Piece[][] board){
        String color = super.getColor();
        int[] start = super.getCoords();
        int forward;

        if (color.equals("white")){
            forward = -1; // white piece moves up the board but in terms of row indices that is decreasing
        } else {
            forward = 1; // black piece moves down the board and in terms of row indices that is increasing. 
        }
        int rowDiff = (end[0] - start[0]) * forward; // this allows white to move forward which would normally return negative and disallows black to move backwards
        int colDiff = end[1] - start[1];

        if (colDiff == 0 && rowDiff == 1 && board[end[0]][end[1]] == null){
            return true; // if moving in a row and there is nothing blocking you, it is valid
        } 

        boolean atStartRow;

        if (color.equals("white") && start[0] == 6 || color.equals("black") && start[0] == 1){
            atStartRow = true;
        } else {
            atStartRow = false;
        }

        //2 squares jump at the start
        if (colDiff == 0 && rowDiff == 2 && atStartRow && board[end[0]][end[1]] == null){
            int inBetweenRow = start[0] + forward; //uses forward because it can be diff for white and black
            if (board[inBetweenRow][end[1]] == null) // returns true if there is nothing in between the first and second row
                return true;
            return false;
        }

        //taking piece diagonally
        // makes sure we are only moving 1 col and 1 row and that there is an enemy piece in that diagonal spot from the pawn
        if (Math.abs(colDiff) == 1 && rowDiff == 1 && board[end[0]][end[1]] != null && !board[end[0]][end[1]].getColor().equals(color)){
            return true;
        }

        return false;


    }
}
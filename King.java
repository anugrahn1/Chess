public class King extends Piece {
    public King(int row, int col, String color) {
        super(row, col, color, "King");
	}


    public boolean isValidMove(int[] end, Piece[][] board){
        String color = super.getColor();
        int[] start = super.getCoords();
        
        int rowDiff = Math.abs(start[0] - end[0]);
        int colDiff = Math.abs(start[1] - end[1]);
        
        boolean isOneSquareMove;
        
        //checks if the king is moving only one square
        //rowDiff and colDiff can be 1 or less but if they are both 0 then the king isn't moving any squares which isn't allowed
        if (rowDiff <= 1 && colDiff <=1 && (rowDiff != 0 || colDiff != 0)){
            isOneSquareMove = true;
        } else {
            isOneSquareMove = false;
        }
        
        if (!isOneSquareMove){ // kings can only move one square
            return false; 
        }
        
        
        
        // if the destination is empty or the opposite color it is valid
        return (board[end[0]][end[1]] == null || board[end[0]][end[1]].getColor() != getColor());
        
        
    }
}
public class Queen extends Piece {
    public Queen(int row, int col, String color) {
        super(row, col, color, "Queen"); // sets the position and color of the queen
	}

    public boolean isValidMove(int[] end, Piece[][] board) {
      int x = end[0];
      int y = end[1];
      int delta_row = Math.abs(x - super.getRow()); // gets the difference between the destination position and the current
      int delta_col = Math.abs(y - super.getColumn());

      if (super.getRow() == x && super.getColumn() == y) {
          return false;
      }// checks for the best case scenario where the destination is the same as the current

      boolean line = (super.getRow() == x) || (super.getColumn() == y); // sees if the destination is in the same row or column as the current in order to count it as a valid line

      boolean diagonal = false; // now checks to see if there is a possible diagonal move 
                                // if the change in y and change in x are the same, that means that the diagonal is possible since 
      if (delta_row == delta_col) {
        diagonal = true;
      }

      if (!line && !diagonal) { // if the final destination is not a line or a diagonal, then the move is not valid
          return false;
      }

      int rowMovement = 1;
      if (x == super.getRow()) { // basically we are going to simulate moving on the board 
        rowMovement = 0;
      } else if (x < super.getRow()) {
        rowMovement = -1;
      }
      int colMovement = 1;
      if (y == super.getColumn()) {
        colMovement = 0;
      } else if (y < super.getColumn()) {
        colMovement = -1;
      }

      int currentRow = super.getRow() + rowMovement;
      int currentCol = super.getColumn() + colMovement;
      while (currentRow != x || currentCol != y) {
          if (board[currentRow][currentCol] != null) {
              return false;
          }
          currentRow += rowMovement;
          currentCol += colMovement;
      }

      return (board[x][y] == null || board[x][y].getColor() != getColor());
  }
}
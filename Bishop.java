public class Bishop extends Piece {

	public Bishop(int row, int col, String color) {
        super(row, col, color, "Bishop");
	}

    public boolean isValidMove(int[] end, Piece[][] board) {
        int x = end[0];
        int y = end[1];
        if (super.getRow() == x && super.getColumn() == y) {
          return false;
        }

          int delta_row = Math.abs(x - super.getRow());
          int delta_col = Math.abs(y - super.getColumn());
    
          boolean diagonal = false;
          if (delta_row == delta_col) {
            diagonal = true;
          }
    
          if (!diagonal) {
              return false;
          }
    
          int rowMovement = 1;
          if (x == super.getRow()) {
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
public class Board {
    private static Piece[][] board = new Piece[8][8];
    
    public Board(){
        this.resetBoard(); 
    }

    public void resetBoard() {
        board[0][0] = new Rook(0, 0, "black");
        board[0][1] = new Knight(0, 1, "black");
        board[0][2] = new Bishop(0, 2, "black");
        board[0][3] = new Queen(0, 3, "black");
        board[0][4] = new King(0, 4, "black");
        board[0][5] = new Bishop(0, 5, "black");
        board[0][6] = new Knight(0, 6, "black");
        board[0][7] = new Rook(0, 7, "black");
        board[1][0] = new Pawn(1, 0, "black");
        board[1][1] = new Pawn(1, 1, "black");
        board[1][2] = new Pawn(1, 2, "black");
        board[1][3] = new Pawn(1, 3, "black");
        board[1][4] = new Pawn(1, 4, "black");
        board[1][5] = new Pawn(1, 5, "black");
        board[1][6] = new Pawn(1, 6, "black");
        board[1][7] = new Pawn(1, 7, "black");

        board[6][0] = new Rook(6, 0, "white");
        board[6][1] = new Knight(6, 1, "white");
        board[6][2] = new Bishop(6, 2, "white");
        board[6][3] = new Queen(6, 3, "white");
        board[6][4] = new King(6, 4, "white");
        board[6][5] = new Bishop(6, 5, "white");
        board[6][6] = new Knight(6, 6, "white");
        board[6][7] = new Rook(6, 7, "white");
        board[7][0] = new Pawn(7, 0, "white");
        board[7][1] = new Pawn(7, 1, "white");
        board[7][2] = new Pawn(7, 2, "white");
        board[7][3] = new Pawn(7, 3, "white");
        board[7][4] = new Pawn(7, 4, "white");
        board[7][5] = new Pawn(7, 5, "white");
        board[7][6] = new Pawn(7, 6, "white");
        board[7][7] = new Pawn(7, 7, "white");

        for (int i=2;i<6;i++) {
            for (int j=0;j<8;j++) {
                board[i][j] = new Piece(i, j);
            }
        }
    }
    
    public void switchBoardView() {
        for (int i=0;i<board.length;i++) {
            for (int j=0;j<board[i].length;j++) {
                Piece temp = board[i][j];
                board[i][j] = board[board.length-i-1][board[i].length-j-1];
                board[board.length-i-1][board[i].length-j-1] = temp;
            }
        }
    }

    public static boolean spotIsFree(int x, int y){
        if (x > 7 || x < 0 || y > 7 || y < 0) 
            return false;
        if (board[x][y].getColor().equals("blank")) 
            return true;
        return false;
    }
    
    public static String getColor(int x, int y){
        return board[x][y].getColor();
    }
    
    public static boolean colorIsEqual(int x, int y, String currentColor){
        return getColor(x, y).equals(currentColor);
    }
}
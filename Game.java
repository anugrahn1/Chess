public class Game {
    private Board board;
    private String turn = "white";
    private int[] selectedSquare;
    private boolean isCheckmate;
    
    public Game() {
        this.board = new Board();
        this.isCheckmate = false;
    }
    
    public boolean isPieceSelected(){
        return selectedSquare != null;
    }
    
    public boolean getIsCheckmate(){
        return this.isCheckmate;
    }
    
    public Board getBoard(){
        return this.board;
    }
    public String getTurn() {
        return turn;
    }
    
    public boolean move(int[] start, int[] end){
        Piece moving = this.board.getPiece(start[0], start[1]);
        
        if (moving == null || !moving.getColor().equals(turn)){
            return false;
        }
        
        if (isInCheck(this.turn)){
            if (isInCheckmate(this.turn)){
                return false;
            }
            
            if (isStillInCheck(this.turn, start, end)){
                return false;
            }
            
        }
        
        if (moving.isValidMove(end, this.board.getBoard())){
            this.board.movePiece(start, end);
            if (turn.equals("white")){
                turn = "black";
            } else {
                turn = "white";
            }
            
            return true;
        }
        
        return false;
    }
    
    public void promote(int[] coords, Piece p){
        this.board.setPiece(coords, p);
    }
    
    public boolean isInCheck(String c) {
        int x = 0;
        int y = 0;
        boolean isDone = false;
        
        for (int i=0;i<8;i++) {
            for (int j=0;j<8;j++) {
                if (this.board.getPiece(i, j) != null && this.board.getPiece(i, j).getColor().equals(c) && this.board.getPiece(i,j).getType().equals("King")) {
                    x = i;
                    y = j;
                    isDone = true;
                    break;
                }
            }
            if (isDone) break;
        }
        
        for (int i=0;i<8;i++) {
            for (int j=0;j<8;j++) {
                Piece p = this.board.getPiece(i, j);
                if (p != null && !p.getColor().equals(c)) {
                    int[] coords = {x, y};
                    if (p.isValidMove(coords, this.board.getBoard())){
                        System.out.println("check");
                        return true;
                    } 
                }
            }
        }
        return false;
        
    }
    
    public boolean isInCheckmate(String kingColor){
        //king needs to be in check to be in checkmate
        if (!isInCheck(kingColor)){
            return false;
        }
        //finds king location
        int[] kingCoords = new int[2];
        boolean isDone = false;
        Piece[][] b = board.getBoard();
        for (int i=0;i<b.length;i++) {
            for (int j=0;j<b[i].length;j++) {
                if (b[i][j] != null && b[i][j].getColor().equals(kingColor) && b[i][j].getType().equals("King")) {
                    kingCoords[0] = i;
                    kingCoords[1] = j;
                    isDone = true;
                    break;
                }
            }
            if (isDone) break;
        }
        //coords of king with color of kingColor
        System.out.println("Kings row: "+kingCoords[0]);
        System.out.println("Kings col: "+kingCoords[1]);
        King king = (King) this.board.getPiece(kingCoords[0], kingCoords[1]);
        
        for (int i=0;i<8;i++) {
            for (int j=0;j<8;j++) {
                Piece p = this.board.getPiece(i, j);
                if (p != null && p.getColor().equals(kingColor)) {
                    for (int r=0;r<8;r++) {
                        for (int c=0;c<8;c++) {
                            int[] coords = {i, j};
                            int[] end = {r, c};
                            if (p.isValidMove(end, this.board.getBoard()) && !isStillInCheck(kingColor, coords, end)){
                                return false;
                            } 
                        }
                    }
                }
            }
        }
        
        this.isCheckmate = true;
        return true;
        
    }
    
    public boolean onBoard(int[] end){
        if (end[0] > -1 && end[0] < 8 && end[1] > -1 && end[1] < 8)
            return true;
        return false;
    }
    
    public boolean isStillInCheck(String kingColor, int[] start, int[] end){
        Piece original = this.board.getPiece(end[0], end[1]);
        
        this.board.setPiece(end[0], end[1], this.board.getPiece(start[0], start[1]));
        this.board.setPiece(start[0], start[1], null);
        
        boolean isInCheck = isInCheck(kingColor);
        
        this.board.setPiece(start[0], start[1], this.board.getPiece(end[0], end[1]));
        this.board.setPiece(end[0], end[1], original);
        
        return isInCheck;
    }
    
    
    /*
    When you click on the piece the first time selectedSquare is equal to null
    it checks if the piece you clicked is your color and that there is a piece there
    if there is, it will set your initial click to that coordinate
    then once you click the second place you want the piece to go to, the initial click will be the start coords and the end coords will be second click you just made.
    then it resets the original click.
    */
    public boolean handleSquareSelection(int x, int y) {
        if (selectedSquare == null){
            Piece selectedPiece = board.getPiece(x, y);
            boolean selectedIsTheSameColor;
            if (selectedPiece != null && selectedPiece.getColor().equals(this.turn)) {
                selectedIsTheSameColor = true;
            } else {
                selectedIsTheSameColor = false;
            }
            if (selectedPiece != null && selectedIsTheSameColor){
                selectedSquare = new int[2];
                selectedSquare[0] = x;
                selectedSquare[1] = y;
                return false;
            }
        } else {
            int[] coords = {x, y};
            boolean moveMade = move(selectedSquare, coords);
            selectedSquare = null;
            return moveMade;
        }
        return true;
    }
}
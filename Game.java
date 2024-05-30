public class Game {
    private Board board;
    private String turn = "white"; // the turn of the person playing
    private int[] selectedSquare; // is used later for selecting squares to move your pieces
    private boolean isCheckmate; // keeps track of checkmate status
    
    public Game() { // constructor, starts the backend engine of the game
        this.board = new Board();
        this.isCheckmate = false;
    }
    // the methods below are just to return certain important values and such when necessary
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
    
    public boolean move(int[] start, int[] end){ // this method is to move the piece in the backend of the game and to check if check or checkmate happens
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
        if (isStillInCheck(this.turn, start, end)){
                return false;
        }
        
        if (moving.isValidMove(end, this.board.getBoard())){
            this.board.movePiece(start, end);
            if (turn.equals("white")){ // this switches the player status after each turn
                turn = "black";
            } else {
                turn = "white";
            }
            
            return true;
        }
        
        return false;
    }
    
    // couldnt finish castling in time
    // public boolean canCastle(int[] start, int[] end, String kingColor) {
    //     // MAKE SURE TO UPDATE MOVED VARIABLE
    //     if (isInCheck(kingColor)) return false;
        
    //     Piece[][] b = this.board.getBoard();
    //     if (kingColor.equals("white") && end[0] == 7 && end[1] == 2 && start[0] == 7 && start[1] == 4) {
    //         System.out.println("yes1");
    //         System.out.println(b[7][0].getType().equals("Rook"));
    //         System.out.println(b[7][0] != null);
    //         System.out.println(!b[7][0].getMoved());
    //         System.out.println(!b[7][4].getMoved());
    //         if (b[7][0] != null && b[7][0].getType().equals("Rook") && !b[7][0].getMoved() && !b[7][4].getMoved()) {
    //             System.out.println("yes2");
    //             if (b[7][1] == null && b[7][2] == null && b[7][3] == null) {
    //                 System.out.println("yes3");
    //                 if (!isStillInCheck(kingColor, start, end)) {
    //                     System.out.println("yes4");
    //                     return true;
    //                 }
    //             }
    //         }
    //     }
    //     return false;
    // }
    
    public void promote(int[] coords, Piece p){ // promotes pawns
        this.board.setPiece(coords, p);
    }
    
    // public void castle(int[] coords, Piece p) {
    //     this.board
    // }
    
    public boolean isInCheck(String c) { // checks if a king is in check based on the color
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
                        // System.out.println("check");
                        return true;
                    } 
                }
            }
        }
        return false;
        
    }
    
    public boolean isInCheckmate(String kingColor){ // checks if a king is in checkmate based on the color
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
        // System.out.println("Kings row: "+kingCoords[0]);
        // System.out.println("Kings col: "+kingCoords[1]);
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
    
    public boolean onBoard(int[] end){ // makes sure that any movements happen on the board
        if (end[0] > -1 && end[0] < 8 && end[1] > -1 && end[1] < 8)
            return true;
        return false;
    }
    
    public boolean isStillInCheck(String kingColor, int[] start, int[] end){ // checks in the future to see if a king will be in check after another move is performed
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
    public boolean handleSquareSelection(int x, int y) { // this was taken as "inspiration" from the source website on how to get the selected pieces to work together
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
public class Pawn extends Piece{
    
    public Pawn(int x, int y, String color){
        super(x, y, color);
    }
    
    public int[][] possibleMoves(){
        int x = super.getX();
        int y = super.getY();
        String color = super.getColor();
        int options[][];
        if (y == 1 || y == 6)
            options = new int[4][2];
        else
            options = new int[3][2];
        if (color.equals("black")){
            for (int r = 0; r < options.length; r++){
                if (Board.spotIsFree(x, y+1) && r == 0){
                    options[r][0] = x;
                    options[r][1] = y+1;
                }
                if (!(Board.spotIsFree(x+1, y+1)) && r == 1 && !Board.colorIsEqual(x+1, y+1, color)){ // taking piece diagonally 
                    options[r][0] = x+1;
                    options[r][1] = y+1;
                }
                if (!(Board.spotIsFree(x-1, y+1)) && r == 2 && !Board.colorIsEqual(x-1, y+1, color)){ // taking piece diagonally 
                    options[r][0] = x-1;
                    options[r][1] = y+1;
                }
                if (!(Board.spotIsFree(x, y+2)) && r == 3){
                    options[r][0] = x;
                    options[r][1] = y+2;
                }
            }
            return options;
        } else { // white pawns subtract y to go up
            for (int r = 0; r < options.length; r++){
                if (Board.spotIsFree(x, y-1) && r == 0){
                    options[r][0] = x;
                    options[r][1] = y-1;
                }
                if (!(Board.spotIsFree(x+1, y-1)) && r == 1 && !Board.colorIsEqual(x+1, y+1, color)){ // taking piece diagonally 
                    options[r][0] = x+1;
                    options[r][1] = y-1;
                }
                if (!(Board.spotIsFree(x-1, y-1)) && r == 2 && !Board.colorIsEqual(x-1, y+1, color)){ // taking piece diagonally 
                    options[r][0] = x-1;
                    options[r][1] = y-1;
                }
                if (!(Board.spotIsFree(x, y-2)) && r == 3){
                    options[r][0] = x;
                    options[r][1] = y-2;
                }
            }
            return options;      
        }  
    }
    
}
public class Knight extends Piece {
    
    public Knight(int x, int y, String color){
        super(x, y, color);
    }
    
    public int[][] possibleOptions(){
        
        int[][] options = new int[8][2];
        for (int r = 0; r < options.length; r++){
            for (int c = 0; c < options[0].length; c++){
                options[r][c] = -1;
            }
        }
        
        int x = super.getX();
        int y = super.getY();
        for (int r = 0; r < options.length; r++){

            if (Board.spotIsFree(x-1, y+2) && r == 0){
                options[r][0] = x-1;
                options[r][1] = y+2;
            }
            if (Board.spotIsFree(x+1, y+2) && r == 1){
                options[r][0] = x+1;
                options[r][1] = y+2;
            }
            if (Board.spotIsFree(x+1, y-2) && r == 2){
                options[r][0] = x+1;
                options[r][1] = y-2;
            }
            if (Board.spotIsFree(x-1, y-2) && r == 3){
                options[r][0] = x-1;
                options[r][1] = y-2;
            }
            if (Board.spotIsFree(x-1, y-1) && r == 4){
                options[r][0] = x-1;
                options[r][1] = y-1;
            }
            if (Board.spotIsFree(x-1, y) && r == 5){
                options[r][0] = x-1;
                options[r][1] = y;
            }
            if (Board.spotIsFree(x+1, y-1) && r == 6){
                options[r][0] = x+1;
                options[r][1] = y-1;
            }
            if (Board.spotIsFree(x, y-1) && r == 7){
                options[r][0] = x;
                options[r][1] = y-1;
            }

        }
        
        
        return options;
        
    }
    
}
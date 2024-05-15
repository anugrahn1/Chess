public class Rook extends Piece {
    
    public Rook(int x, int y, String color){
        super(x, y, color);
    }
    
    public int[][] possibleMoves(){
        int[][] options = new int[100][2];         //default values of options is [-1, -1] to represent no option there
        for (int i = 0; i < options.length; i++){
            for (int j = 0; j < options[0].length; i++){
                options[i][j] = -1;
            }
        }
        int x = super.getX();
        int y = super.getY();
        String color = super.getColor();
        int count = 0;
        for (int c = 0; c < y; c++){
            if (Board.spotIsFree(x, c)) {
                options[count][0] = x;
                options[count][1] = c;
                count++;
            } else if (!(Board.spotIsFree(x, c)) && (!Board.colorIsEqual(x, c, color))) {
                options[count][0] = x;
                options[count][1] = c;
                count++;
                break;
            } else if (!(Board.spotIsFree(x, c) && (Board.colorIsEqual(x, c, color)))) {
                break;
            }
        }
        for (int c = y; c < 8; c++){
            if (c==y) continue;
            if (Board.spotIsFree(x, c)) {
                options[count][0] = x;
                options[count][1] = c;
                count++;
            } else if (!(Board.spotIsFree(x, c)) && (!Board.colorIsEqual(x, c, color))) {
                options[count][0] = x;
                options[count][1] = c;
                count++;
                break;
            } else if (!(Board.spotIsFree(x, c) && (Board.colorIsEqual(x, c, color)))) {
                break;
            }
        }
        
        for (int r=0;r<x;r++) {
            if (Board.spotIsFree(r, y)) {
                options[count][0] = r;
                options[count][1] = y;
                count++;
            } else if (!(Board.spotIsFree(r, y)) && (!Board.colorIsEqual(r, y, color))) {
                options[count][0] = r;
                options[count][1] = y;
                count++;
                break;
            } else if (!(Board.spotIsFree(r, y) && (Board.colorIsEqual(r, y, color)))) {
                break;
            }
        }
        
        for (int r=x;r<8;r++) {
            if (r==x) continue;
            if (Board.spotIsFree(r, y)) {
                options[count][0] = r;
                options[count][1] = y;
                count++;
            } else if (!(Board.spotIsFree(r, y)) && (!Board.colorIsEqual(r, y, color))) {
                options[count][0] = r;
                options[count][1] = y;
                count++;
                break;
            } else if (!(Board.spotIsFree(r, y) && (Board.colorIsEqual(r, y, color)))) {
                break;
            }
        }
        
        

        return options;
     }
}
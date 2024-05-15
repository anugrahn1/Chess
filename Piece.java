public class Piece {
    private int[] pos; //[y, x]   [0,0] -> a8  [0,1] -> b8
    private String color;

    public Piece(int x, int y, String c) {
        this.pos[0] = x; // 
        this.pos[1] = y; 
        this.color = c;
    }

    public Piece(int x, int y) {
        this.pos[0] = x;
        this.pos[1] = y; 
        this.color = "blank";
    }

    public int getX() {
        return pos[0];
    }

    public int getY() {
        return pos[1];
    }

    public String getColor(){
        return this.color;
    }
    
    public String getOppositeColor() {
        if (this.color.equals("white")) {
            return "black";
        } else if (this.color.equals("black")) {
            return "white";
        }
        return "blank";
    }

}
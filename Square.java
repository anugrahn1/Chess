import javax.swing.*;
import java.awt.*;

public class Square extends JButton {
    private int row;
    private int col;
    
    public Square(int row, int col){
        this.row = row;
        this.col = col;
        makeButton();
    }
    
    private void makeButton(){
        setPreferredSize(new Dimension(60, 60)); //size of button
        
        if ((row + col) % 2 == 0) { //checkered boards
            setBackground(Color.RED);
        } else {
            setBackground(Color.WHITE);
        }
        
        // System.out.println("hi");
    }
    
    public void setPieceSymbol(String picturePath) {
        this.setIcon(new ImageIcon(picturePath));
    }
    
    public void clearPiece(){
        this.setIcon(null);
    }
}
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.net.URL;
import java.io.*;
import javax.imageio.ImageIO;

// chess pieces - https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent

public class ChessGUI {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private Image[][] chessPieceImages = new Image[2][6];
    private JPanel chessBoard;
    private final JLabel message = new JLabel("");
    private static final String COLS = "ABCDEFGH";

    ChessGUI() {
        initializeGui();
    }

    public void initializeGui() {
        // create the images for the chess pieces
        
        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        Action newGameAction = new AbstractAction("Resign") {

            public void actionPerformed(ActionEvent e) {
                setupNewGame();
            }
        };
        tools.add(newGameAction);
        tools.add(new JButton("Resign")); // TODO - add functionality!
        tools.add(new JButton("Draw")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(message);

        chessBoard = new JPanel(new GridLayout(0, 9)) {

            /**
             * Override the preferred size to return the largest it can, in
             * a square shape.  Must (must, must) be added to a GridBagLayout
             * as the only component (it uses the parent as a guide to size)
             * with no GridBagConstaint (so it is centered).
             */
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int)d.getWidth(),(int)d.getHeight());
                } else if (c!=null &&
                        c.getWidth()>d.getWidth() &&
                        c.getHeight()>d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w>h ? h : w);
                return new Dimension(s,s);
            }
        };
        chessBoard.setBorder(new CompoundBorder(
                new EmptyBorder(8,8,8,8),
                new LineBorder(Color.BLACK)
                ));
        chessBoard.setBackground(Color.WHITE);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(Color.WHITE);
        boardConstrain.add(chessBoard);
        gui.add(boardConstrain);

        // create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(new Color(139, 69, 19));
                }
                chessBoardSquares[jj][ii] = b;
            }
        }

        /*
         * fill the chess board
         */
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                    SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (9-(ii + 1)),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[ii][jj]);
                }
            }
        }
        setupNewGame();

    }

    public JComponent getGui() {
        return gui;
    }

    
    
    /**
     * Initializes the icons of the initial chess board piece places
     */
    private void setupNewGame() {
        
        message.setText("Make your move!");
        // set up the black pieces
        chessBoardSquares[0][0].setIcon(new ImageIcon("./images/black_rook.png"));
        chessBoardSquares[0][1].setIcon(new ImageIcon("images/black_knight.png"));
        chessBoardSquares[0][2].setIcon(new ImageIcon("images/black_bishop.png"));
        chessBoardSquares[0][3].setIcon(new ImageIcon("images/black_queen.png"));
        chessBoardSquares[0][4].setIcon(new ImageIcon("images/black_king.png"));
        chessBoardSquares[0][5].setIcon(new ImageIcon("images/black_bishop.png"));
        chessBoardSquares[0][6].setIcon(new ImageIcon("images/black_knight.png"));
        chessBoardSquares[0][7].setIcon(new ImageIcon("images/black_rook.png"));
        chessBoardSquares[1][0].setIcon(new ImageIcon("images/black_pawn.png"));
        chessBoardSquares[1][1].setIcon(new ImageIcon("images/black_pawn.png"));
        chessBoardSquares[1][2].setIcon(new ImageIcon("images/black_pawn.png"));
        chessBoardSquares[1][3].setIcon(new ImageIcon("images/black_pawn.png"));
        chessBoardSquares[1][4].setIcon(new ImageIcon("images/black_pawn.png"));
        chessBoardSquares[1][5].setIcon(new ImageIcon("images/black_pawn.png"));
        chessBoardSquares[1][6].setIcon(new ImageIcon("images/black_pawn.png"));
        chessBoardSquares[1][7].setIcon(new ImageIcon("images/black_pawn.png"));
        

        
        
        chessBoardSquares[7][0].setIcon(new ImageIcon("images/white_rook.png"));
        chessBoardSquares[7][1].setIcon(new ImageIcon("images/white_knight.png"));
        chessBoardSquares[7][2].setIcon(new ImageIcon("images/white_bishop.png"));
        chessBoardSquares[7][3].setIcon(new ImageIcon("images/white_queen.png"));
        chessBoardSquares[7][4].setIcon(new ImageIcon("images/white_king.png"));
        chessBoardSquares[7][5].setIcon(new ImageIcon("images/white_bishop.png"));
        chessBoardSquares[7][6].setIcon(new ImageIcon("images/white_knight.png"));
        chessBoardSquares[7][7].setIcon(new ImageIcon("images/white_rook.png"));
        chessBoardSquares[6][0].setIcon(new ImageIcon("images/white_pawn.png"));
        chessBoardSquares[6][1].setIcon(new ImageIcon("images/white_pawn.png"));
        chessBoardSquares[6][2].setIcon(new ImageIcon("images/white_pawn.png"));
        chessBoardSquares[6][3].setIcon(new ImageIcon("images/white_pawn.png"));
        chessBoardSquares[6][4].setIcon(new ImageIcon("images/white_pawn.png"));
        chessBoardSquares[6][5].setIcon(new ImageIcon("images/white_pawn.png"));
        chessBoardSquares[6][6].setIcon(new ImageIcon("images/white_pawn.png"));
        chessBoardSquares[6][7].setIcon(new ImageIcon("images/white_pawn.png"));
    }

    public static void main(String[] args) {
        
        ChessGUI cg = new ChessGUI();

        JFrame f = new JFrame("Chess");
        f.add(cg.getGui());
        // Ensures JVM closes after frame(s) closed and
        // all non-daemon threads are finished
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // See https://stackoverflow.com/a/7143398/418556 for demo.
        f.setLocationByPlatform(true);
        
        
        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setVisible(true);

        // Swing GUIs should be created and updated on the EDT
        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency
    }
}
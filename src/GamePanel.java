import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    public static final int WIDTH = 672;
    public static final int HEIGHT = 672;
    public static int gridWidth;
    public static int gridHeight;
    public static ArrayList<Piece> pieces = new ArrayList<Piece>();

    public GamePanel() {
        setBackground(new Color(209,190,130));
        setSize(WIDTH, HEIGHT);
        gridWidth = getWidth() / 21;
        gridHeight = getHeight() / 21;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        for (int i = 1; i < 21; i++) {
            g.drawLine(i * gridWidth, gridHeight, i * gridWidth, gridHeight * 20);
            g.drawLine(gridWidth, i * gridHeight, gridWidth * 20, i * gridHeight);
        }

        for (Piece piece: pieces) {
            piece.paint(g2d);
        }
    }

    public static void win(String player) {
        System.out.println(player);
    }
}

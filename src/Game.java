import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game extends JFrame implements MouseListener {
    private static Insets insets;
    private static GamePanel panel;
    public static final int operationWidth = 80;

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public Game() {
        addMouseListener(this);
        setSize(GamePanel.WIDTH + 14 + operationWidth, GamePanel.HEIGHT + 37);
        setTitle("五子棋");
        setBackground(new Color(209,190,130));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        insets = getInsets();
        addButton();
    }

    public void start() {
        panel = new GamePanel();
        add(panel);
        Thread th = new Thread((Runnable) panel);
        th.start();
    }

    public void mouseClicked(MouseEvent e) {
        int[] XY = getXY(e);
        // TODO limit the range
        if (XY[0] < 0 || XY[0] > 19 ||
            XY[1] < 0 || XY[1] > 19) {
            System.out.println("over");
            return;
        }
        Piece newPiece = new Piece(getPlayer(), XY[0], XY[1]);
        GamePanel.pieces.add(newPiece);
        panel.repaint();
        newPiece.judge();
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public int[] getXY(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY() - insets.top;
        int X = (int) Math.floor(mouseX / GamePanel.gridWidth);
        int Y = (int) Math.floor(mouseY / GamePanel.gridHeight);
        int restWidth = mouseX - X * GamePanel.gridWidth;
        int restHeight = mouseY - Y * GamePanel.gridHeight;
        if (restWidth < GamePanel.gridWidth / 2) {
            X = X - 1;
        }
        if (restHeight < GamePanel.gridHeight / 2) {
            Y = Y - 1;
        }
        return new int[]{X, Y};
    }

    public String getPlayer() {
        return GamePanel.pieces.size() % 2 != 0 ? "white" : "black";
    }

    public void addButton() {
        JButton back = new JButton("悔棋");
        back.setBounds(GamePanel.WIDTH + 7, GamePanel.gridHeight + 30, 60, 30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GamePanel.pieces.remove(GamePanel.pieces.size() - 1);
                panel.repaint();
            }
        });
        JButton giveUp = new JButton("认输");
        giveUp.setBounds(GamePanel.WIDTH + 7, GamePanel.gridHeight + 90, 60, 30);
        giveUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GamePanel.win(getPlayer());
            }
        });

        add(back);
        add(giveUp);
    }
}

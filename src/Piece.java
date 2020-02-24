import java.awt.*;

public class Piece {
    private static final int r = 14;
    public Color color;
    public int x;
    public int y;

    public Piece(String color, int x, int y) {
        if (color == "black") {
            this.color = Color.BLACK;
        } else if (color == "white") {
            this.color = Color.WHITE;
        }
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics2D g) {
        g.setColor(color);
        g.fillArc((x + 1) * GamePanel.gridWidth - r, (y + 1) * GamePanel.gridHeight - r, 2 * r, 2 * r, 0, 360);
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(1));
        g.drawArc((x + 1) * GamePanel.gridWidth - r, (y + 1) * GamePanel.gridHeight - r, 2 * r, 2 * r, 0, 360);
    }

    public void judge() {
        String player = "";
        if (color == Color.BLACK) {
            player = "black";
        } else if (color == Color.WHITE) {
            player = "white";
        }
        if (judgeHorizontal() || judgeVertical() || judgeLeanToLeft() || judgeLeanToRight()) {
            GamePanel.win(player);
        }
    }

    public boolean judgeVertical() {
        int number = 1;
        for (int i = 1; i < 6; i++) {
            if (getTop(i)) {
                number++;
            } else {
                break;
            }
        }
        for (int i = 1; i < 6; i++) {
            if (getBottom(i)) {
                number++;
            } else {
                break;
            }
        }
        return number >= 5;
    }

    public boolean judgeHorizontal() {
        int number = 1;
        for (int i = 1; i < 6; i++) {
            if (getLeft(i)) {
                number++;
            } else {
                break;
            }
        }
        for (int i = 1; i < 6; i++) {
            if (getRight(i)) {
                number++;
            } else {
                break;
            }
        }
        return number >= 5;
    }

    public boolean judgeLeanToLeft() {
        int number = 1;
        for (int i = 1; i < 6; i++) {
            if (getTopLeft(i)) {
                number++;
            } else {
                break;
            }
        }
        for (int i = 1; i < 6; i++) {
            if (getBottomRight(i)) {
                number++;
            } else {
                break;
            }
        }
        return number >= 5;
    }

    public boolean judgeLeanToRight() {
        int number = 1;
        for (int i = 1; i < 6; i++) {
            if (getTopRight(i)) {
                number++;
            } else {
                break;
            }
        }
        for (int i = 1; i < 6; i++) {
            if (getBottomLeft(i)) {
                number++;
            } else {
                break;
            }
        }
        return number >= 5;
    }

    public boolean getTop(int distance) {
        for (Piece p: GamePanel.pieces) {
            if (x == p.x && y == p.y + distance) {
                return p.color == color;
            }
        }
        return false;
    }

    public boolean getBottom(int distance) {
        for (Piece p: GamePanel.pieces) {
            if (x == p.x && y == p.y - distance) {
                return p.color == color;
            }
        }
        return false;
    }

    public boolean getRight(int distance) {
        for (Piece p: GamePanel.pieces) {
            if (x == p.x - distance && y == p.y) {
                return p.color == color;
            }
        }
        return false;
    }

    public boolean getLeft(int distance) {
        for (Piece p: GamePanel.pieces) {
            if (x == p.x + distance && y == p.y) {
                return p.color == color;
            }
        }
        return false;
    }

    public boolean getTopRight(int distance) {
        for (Piece p: GamePanel.pieces) {
            if (x == p.x - distance && y == p.y + distance) {
                return p.color == color;
            }
        }
        return false;
    }

    public boolean getTopLeft(int distance) {
        for (Piece p: GamePanel.pieces) {
            if (x == p.x + distance && y == p.y + distance) {
                return p.color == color;
            }
        }
        return false;
    }

    public boolean getBottomRight(int distance) {
        for (Piece p: GamePanel.pieces) {
            if (x == p.x - distance && y == p.y - distance) {
                return p.color == color;
            }
        }
        return false;
    }

    public boolean getBottomLeft(int distance) {
        for (Piece p: GamePanel.pieces) {
            if (x == p.x + distance && y == p.y - distance) {
                return p.color == color;
            }
        }
        return false;
    }
}

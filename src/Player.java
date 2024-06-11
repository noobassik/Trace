import java.awt.*;

public class Player {
    private int x, y;
    private Color color;

    public Player(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public void move(int dx, int dy, GameBoard gameBoard) {
        int newX = x + dx;
        int newY = y + dy;

        Hexagon newHex = gameBoard.getHexagon(newX, newY);
        if (newHex != null && newHex.isTraversable() && (newHex.getTrailColor() == null || !newHex.getTrailColor().equals(color))) {
            Hexagon currentHex = gameBoard.getHexagon(x, y);
            if (currentHex != null) {
                currentHex.setTrailColor(color);
            }
            x = newX;
            y = newY;

            // Collect key if present
            gameBoard.collectKey(newX, newY);
        }
    }
}

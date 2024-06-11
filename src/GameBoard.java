import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameBoard extends JPanel {
    private int width, height;
    private double hexSize;
    private Hexagon[][] board;
    private List<Key> keys;
    private Player player;
    private boolean allKeysCollected = false;
    private Hexagon targetHex;

    public GameBoard(int width, int height, double hexSize) {
        this.width = width;
        this.height = height;
        this.hexSize = hexSize;
        this.board = new Hexagon[width][height];
        this.keys = new ArrayList<>();
        initializeBoard();
    }

    private void initializeBoard() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Hexagon hex = new Hexagon(x, y, hexSize);
                board[x][y] = hex;
            }
        }
    }

    public Hexagon getHexagon(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return board[x][y];
    }

    public void addPlayer(Player player) {
        this.player = player;
        Hexagon hex = getHexagon(player.getX(), player.getY());
        if (hex != null) {
            hex.setTrailColor(player.getColor());
        }
    }

    public void addKeys(List<Key> keys) {
        this.keys = keys;
    }

    public void collectKey(int x, int y) {
        Key collectedKey = null;
        for (Key key : keys) {
            if (key.getX() == x && key.getY() == y) {
                collectedKey = key;
                break;
            }
        }
        if (collectedKey != null) {
            keys.remove(collectedKey);
        }
        allKeysCollected = keys.isEmpty();
    }

    public void setTargetHex(int x, int y) {
        targetHex = getHexagon(x, y);
        if (targetHex != null) {
            targetHex.setTarget(true);
        }
    }

    public boolean isGameWon() {
        return allKeysCollected && player.getX() == targetHex.getXCoord() && player.getY() == targetHex.getYCoord();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double xOffset = x * hexSize * 3 / 2;
                double yOffset = y * hexSize * Math.sqrt(3) + (x % 2) * hexSize * Math.sqrt(3) / 2;
                board[x][y].drawHexagon(g2d, xOffset, yOffset);
            }
        }
        // Draw keys
        for (Key key : keys) {
            int keyX = (int) (key.getX() * hexSize * 3 / 2 + hexSize / 2);
            int keyY = (int) (key.getY() * hexSize * Math.sqrt(3) + (key.getX() % 2) * hexSize * Math.sqrt(3) / 2 + hexSize / 2);
            g.setColor(Color.green);
            g.fillOval(keyX, keyY, 20, 20); // Adjust the position of the key

        }

        // Draw player
        if (player != null) {
            int playerX = (int) (player.getX() * hexSize * 3 / 2 + hexSize / 2);
            int playerY = (int) (player.getY() * hexSize * Math.sqrt(3) + (player.getX() % 2) * hexSize * Math.sqrt(3) / 2 + hexSize / 2);
            g.setColor(player.getColor());
            g.fillOval(playerX, playerY, (int) hexSize / 2, (int) hexSize / 2); // Draw player as a circle
        }
    }
}


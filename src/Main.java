import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Следы");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(650, 800);

            GameBoard gameBoard = new GameBoard(20, 20, 20);
            Player player = new Player(0, 0, Color.BLUE);
            List<Key> keys = generateKeys();

            gameBoard.addPlayer(player);
            gameBoard.addKeys(keys);
            gameBoard.setTargetHex(1, 10);

            frame.add(gameBoard);
            frame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            player.move(0, -1, gameBoard);
                            break;
                        case KeyEvent.VK_DOWN:
                            player.move(0, 1, gameBoard);
                            break;
                        case KeyEvent.VK_LEFT:
                            player.move(-1, 0, gameBoard);
                            break;
                        case KeyEvent.VK_RIGHT:
                            player.move(1, 0, gameBoard);
                            break;
                    }
                    gameBoard.repaint();
                    if (gameBoard.isGameWon()) {
                        JOptionPane.showMessageDialog(frame, "Вы выиграли!");
                    }
                }
            });

            frame.setVisible(true);
        });
    }

    private static List<Key> generateKeys() {
        List<Key> keys = new ArrayList<>();
        keys.add(new Key(2, 3));
        keys.add(new Key(5, 5));
        keys.add(new Key(7, 8));
        keys.add(new Key(10, 10));
        keys.add(new Key(15, 15));
        return keys;
    }
}

import java.awt.*;

public class Hexagon {
    private int x, y;
    private boolean traversable = true;
    private Color trailColor = null;
    private double size;
    private boolean isTarget = false;

    public Hexagon(int x, int y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public int getXCoord() {
        return x;
    }

    public int getYCoord() {
        return y;
    }

    public boolean isTraversable() {
        return traversable;
    }

    public void setTraversable(boolean traversable) {
        this.traversable = traversable;
    }

    public Color getTrailColor() {
        return trailColor;
    }

    public void setTrailColor(Color trailColor) {
        this.trailColor = trailColor;
    }

    public void setTarget(boolean isTarget) {
        this.isTarget = isTarget;
    }

    public boolean isTarget() {
        return isTarget;
    }

    public void drawHexagon(Graphics2D g2d, double xOffset, double yOffset) {
        double width = Math.sqrt(3) * size;
        double height = 2 * size;

        int[] xPoints = {
                (int) (xOffset + size * Math.sqrt(3) / 2),
                (int) (xOffset + size * Math.sqrt(3)),
                (int) (xOffset + size * Math.sqrt(3)),
                (int) (xOffset + size * Math.sqrt(3) / 2),
                (int) xOffset,
                (int) xOffset
        };

        int[] yPoints = {
                (int) (yOffset + 0),
                (int) (yOffset + size / 2),
                (int) (yOffset + 1.5 * size),
                (int) (yOffset + 2 * size),
                (int) (yOffset + 1.5 * size),
                (int) (yOffset + size / 2)
        };

        Polygon hex = new Polygon(xPoints, yPoints, 6);

        if (isTarget) {
            g2d.setColor(Color.RED);
            g2d.fillPolygon(hex);
        } else {
            if (trailColor != null) {
                g2d.setColor(trailColor);
                g2d.fillPolygon(hex);
            }
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(hex);
        }
    }
}

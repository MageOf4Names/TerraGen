import java.io.Serializable;

public class Point implements Serializable {
    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(float x, float y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public Point() {
    }

    /**
     * Get the x coordinate
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Set the x coordinate
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get the y coordinate
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Set the y coordinate
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    /**
     * Combines this point with another
     * @param other Point to add
     * @return
     */
    public Point add(Point other) {
        if (other == null)
            return new Point(x, y);

        return new Point(x + other.x, y + other.y);
    }

    /**
     * Gets the distance between this point and another
     * @param other Other point to get the distance from
     * @return
     */
    public int distance(Point other) {
        if (other == null)
            return Integer.MAX_VALUE;

        double dx = x - other.x;
        double dy = y - other.y;

        return (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * Multiplies this point by a constant
     * @param m Multiplier
     * @return
     */
    public Point multiply(float m) {
        return new Point(x * m, y * m);
    }
}

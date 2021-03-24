public class Point {
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

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

    public Point add(Point other) {
        if (other == null)
            return new Point(x, y);

        return new Point(x + other.x, y + other.y);
    }

    public int distance(Point other) {
        if (other == null)
            return Integer.MAX_VALUE;

        return (int) Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    public Point multiply(float m) {
        return new Point(x * m, y * m);
    }
}

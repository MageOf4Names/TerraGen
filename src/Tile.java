public abstract class Tile implements ITile {
    protected Point location;

    public Tile(Point location) {
        this.location = location;
    }

    @Override
    public Point getLocation() {
        return location;
    }
}

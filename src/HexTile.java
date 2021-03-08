public class HexTile extends Tile {
    public HexTile(Point location) {
        super(location);
    }

    @Override
    public Point getCenter() {
        return location;
    }
}

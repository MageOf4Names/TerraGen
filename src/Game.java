import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Manages the current set of users and the game board
 */
public class Game implements Serializable {
    // Size in pixels of tiles
    private int scale = 50;

    public User[] users;

    //private ArrayBlockingQueue<Map> map;

    private Map map;

    // The currently selected token
    private Token selected = null;

    public Game() {
        // Default pool of 4 users
        this.users = new User[]{
                new User("user1"),
                new User("user2"),
                new User("user3"),
                new User("host")
        };

        // Default map of a 10x10 square grid
        //map = new ArrayBlockingQueue<Map>();
        map = new Map(new SquareTileGrid(10, 10, scale));

        // Default map of a 10x10 square grid
        //this.map = new Map(new HexTileGrid(10, 10, scale));
    }

    public Game(User[] users, Map map) {
        this.users = users;
        this.map = map;
    }

    /**
     * Gets the pixel size of tiles
     */
    public int getScale() {
        return scale;
    }

    /**
     * Sets the pixel tile size
     *
     * @param scale
     */
    public void setScale(int scale) {
        this.scale = scale;
    }

    /**
     * Get the current list of users
     */
    public User[] getUsers() {
        return users;
    }

    /**
     * Change the current list of users
     *
     * @param users
     */
    public void setUsers(User[] users) {
        this.users = users;
    }

    public void setGame(Game game) {
        //setUsers(game.getUsers());
        map = game.getMap();
        TerraGen.window.gameRenderer.repaint();
    }

    /**
     * Gets the current map
     */
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Creates and adds a new token to the board
     *
     * @param x
     * @param y
     * @return The token created
     */
    public Token addToken(int x, int y) {
        return map.addToken(x, y);
    }

    /**
     * Adds a token to the board
     *
     * @param x
     * @param y
     * @param token Token to add to the board
     * @return The token added to the board
     */
    public Token addToken(int x, int y, Token token) {
        return map.addToken(x, y, token);
    }

    /**
     * Gets the closest token to a given point
     *
     * @param pos Point to compare to
     * @return
     */
    public Token getClosestToken(Point pos) {
        return map.getClosestToken(pos);
    }

    /**
     * Moves the given token to a new position
     *
     * @param token
     * @param pos
     */
    public void MoveToken(IToken token, Point pos) {
        var tile = map.grid.getClosestTile(pos, map.getScale());
        token.setLocation(tile.getPixelCenterLocation(map.getScale()));
    }

    /**
     * Handles a mouse click event
     *
     * @param mouseEvent
     */
    public void OnMouseClick(MouseEvent mouseEvent) {
        // Get the upper left tile location
        var origin = map.grid.getTile(0, 0);
        var originPos = origin.getPixelLocation(map.getScale());

        // Calculate the mouse relative position to the origin
        var x = mouseEvent.getX() - originPos.x;
        var y = mouseEvent.getY() - originPos.y;
        var pos = new Point(x, y);

        // Check if we have a token selected already
        if (selected == null) {
            // Find the nearest token
            selected = getClosestToken(pos);

            // Check if the token is at most 2 tiles away
            if (selected.pos.distance(pos) < map.getScale() * 2)
                selected.selected = true;
            else
                // Clear selection
                selected = null;
        } else {
            // Move token to clicked location and clear selection
            MoveToken(selected, pos);
            selected.selected = false;
            selected = null;
        }
    }

    public void draw(Graphics2D g) {
        map.draw(g);
    }
}

import java.awt.*;

public class Game {
    public User[] users;

    public Map map;

    public Game(User[] users, Map map) {
        this.users = users;
        this.map = map;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public void draw(Graphics2D g)
    {
        map.draw(g);
    }
}

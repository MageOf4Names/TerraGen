public class Game {
    public User[] users;

    public Map map;

    public Game(User[] users) {
        this.users = users;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }
}

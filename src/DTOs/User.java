package DTOs;

public class User {

    private int ID;
    private String userName;

    public User(int ID, String userName) {
        this.ID = ID;
        this.userName = userName;
    }

    public int getID() {
        return ID;
    }
    public String getUserName() {
        return userName;
    }
}

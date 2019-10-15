class User {

    private int ID;
    private String userName;
    private boolean isAdmin;

    User(int ID, String userName, boolean isAdmin) {
        this.ID = ID;
        this.userName = userName;
        this.isAdmin = isAdmin;
    }

    public int getID() {
        return ID;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}

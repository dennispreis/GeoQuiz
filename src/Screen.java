public enum Screen {

    LOGIN_STUDENT, MAIN_MENU_STUDENT, LOGIN_ADMIN, MAIN_MENU_ADMIN;

    Screen(){}


    @Override
    public String toString() {
        return this.name();
    }
}

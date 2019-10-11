public enum Screen {

    LOGIN_STUDENT(0), MAIN_MENU_STUDENT(1), LOGIN_ADMIN(2), MAIN_MENU_ADMIN(3);

    private int window;

    Screen(int win){
        this.window = win;
    }

    int getWindow(){
        return window;
    }

}

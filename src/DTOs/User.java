package DTOs;

import Images.ImageName;

public class User {

    private int ID;
    private String userName;
    private ImageName imageName;
    private static ProfileHistory profileHistory;

    public User(int ID, String userName) {
        this.ID = ID;
        this.userName = userName;
    }

    public User(int ID, String userName, int avatarIndex) {
        this.ID = ID;
        this.userName = userName;
        imageName = getImageNameFromIndex(avatarIndex);
        profileHistory = new ProfileHistory();
    }

    public int getID() {
        return ID;
    }

    public ProfileHistory getProfileHistory(){
        return profileHistory;
    }

    public String getUserName() {
        return userName;
    }

    public ImageName getAvatarImageName() {
        return imageName;
    }

    private ImageName getImageNameFromIndex(int index) {
        if (index >= 0 && index <= 5) {
            switch (index) {
                case 0:
                    this.imageName = ImageName.AVATAR_DOLPHIN;
                    break;
                case 1:
                    this.imageName = ImageName.AVATAR_EAGLE;
                    break;
                case 2:
                    this.imageName = ImageName.AVATAR_LION;
                    break;
                case 3:
                    this.imageName = ImageName.AVATAR_PENGUIN;
                    break;
                case 4:
                    this.imageName = ImageName.AVATAR_ZEBRA;
                    break;
                case 5:
                    this.imageName = ImageName.AVATAR_COALA;
                    break;
            }
            return this.imageName;
        } else {
            return null;
        }
    }

    public void setImageName(ImageName name){
        this.imageName = name;
    }
}

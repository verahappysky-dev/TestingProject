package user;

import utils.PropertyReader;

public class UserFactory {
    public static User withAdminPermission() {
        return new User(PropertyReader.getProperty("saucedemo.user"), PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withLockedUserPermission() {
        return new User(PropertyReader.getProperty("saucedemo.locked_user"), PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withUsernameOnly(String username) {
        return new User(username, "");
    }

    public static User withPasswordOnly(String password) {
        return new User("", password);
    }

    public static User emptyUser() {
        return new User("", "");
    }

    public static User createUser(String username, String password) {
        return new User(username, password);
    }
}

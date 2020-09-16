package pl.kaczmar.AgileProject.User.Model;

public enum UserType {
    ADMIN("Admin"),
    USER("User");

    private String name;

    UserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package pojo;

public class User {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public User(String email,String password) {
        this.password = password;
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

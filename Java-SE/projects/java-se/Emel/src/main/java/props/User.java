package props;

public class

 // admin login props

User {
    private int uid;
    private String name;
    private String surname;
    private String email;
    private String password;

    public User() {
    }


    public User(int uid, String name, String surname, String email, String password) {
        this.uid = uid;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
    public User(  String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

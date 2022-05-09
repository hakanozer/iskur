package props;

public class Customer {

    private int cid;
    private String name;
    private String surname;
    private String email;
    private String phone;

    public Customer(int cid, String name, String surname,String email, String phone, String address) {

        this.cid = cid;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Customer(int cid, String name, String surname,String phone) {
        this.phone=phone;
        this.cid = cid;
        this.name = name;
        this.surname = surname;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;

    public Customer(){}

}

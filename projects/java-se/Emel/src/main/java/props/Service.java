package props;

public class Service {
     private int sid;
     private int cid;
     private String title;
     private String info;
     private int days;
     private String date;
     private int status;
     private double price;
    private Customer customer;
    private String orderDate;

    public Service() {
    }

    public Service(int sid, int cid, String title, String info, int days, String date, int status, double price, Customer customer,String orderDate) {
        this.sid = sid;
        this.cid = cid;
        this.title = title;
        this.info = info;
        this.days = days;
        this.date = date;
        this.status = status;
        this.price = price;
        this.customer = customer;
        this.orderDate=orderDate;
    }

    public Service(int sid, int cid, String title, String info, int days, String date, int status, double price,String orderDate) {
        this.sid = sid;
        this.cid = cid;
        this.title = title;
        this.info = info;
        this.days = days;
        this.date = date;
        this.status = status;
        this.price = price;
        this.orderDate=orderDate;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Service{" +
                "sid=" + sid +
                ", cid=" + cid +
                ", title='" + title + '\'' +
                ", info='" + info + '\'' +
                ", days=" + days +
                ", date='" + date + '\'' +
                ", status=" + status +
                '}';
    }
}

package props;

public class Service extends Customer {
    private int sid;
    private int cid;
    private String title;
    private String info;
    private String days;
    private String date;
    private int price;
    private int status;

    public Service() {

    }

    public Service(int sid, int cid, String title, String info, String days, String date, int price, int status) {
        this.sid = sid;
        this.cid = cid;
        this.title = title;
        this.info = info;
        this.days = days;
        this.date = date;
        this.price = price;
        this.status = status;
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

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
                ", days='" + days + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}

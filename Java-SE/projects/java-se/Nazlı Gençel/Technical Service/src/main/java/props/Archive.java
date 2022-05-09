package props;

public class Archive {
    private int cid;
    private int sid;
    private String title;
    private String info;
    private int status;

    public Archive() {
    }

    public Archive(int sid, String title, String info, int status,int cid) {
        this.cid=cid;
        this.sid = sid;
        this.title = title;
        this.info = info;
        this.status = status;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}

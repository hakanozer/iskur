package useStream;

public class Customer {

    private Integer cid;
    private String name;
    private Integer count;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

}

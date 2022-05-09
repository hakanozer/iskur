package models;

public class Car {

    private String title;
    private int hp;
    private String color;
    private int km;

    public Car() {
    }

    public Car(String title, int hp, String color, int km) {
        this.title = title;
        this.hp = hp;
        this.color = color;
        this.km = km;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }
}

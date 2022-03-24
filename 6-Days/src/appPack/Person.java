package appPack;

public class Person extends Account {

    int num = 0;
    public Person( int num ) {
        this.num = num;
    }

    @Override
    public int userNumber() {
        return num;
    }

}

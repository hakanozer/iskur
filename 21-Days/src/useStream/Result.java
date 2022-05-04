package useStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Result {

    public List<Customer> customerList( int count ) {
        List<Customer> ls = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Customer c = new Customer();
            c.setCid(i);
            c.setName("onur-"+i);
            Random rd = new Random();
            int rnd = rd.nextInt(count);
            c.setCount( rnd );
            ls.add(c);
        }
        return ls;
    }

}

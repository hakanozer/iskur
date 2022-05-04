package useStream;

import java.util.Comparator;
import java.util.List;

public class MainStream {
    public static void main(String[] args) {

        Result rs = new Result();
        List<Customer> ls = rs.customerList(1000);

        // Stream Api

        // filter
        ls
        .stream()
        .filter( item -> item.getCount() > 100 && item.getCount() < 500 )
        .sorted( ( p1, p2 ) -> p1.getCount().compareTo(p2.getCount()) )
        .limit(10)
        //.sorted( Comparator.comparing(Customer::getCount) )
        .forEach( item -> {
            System.out.println(item);
        });

    }
}

package useStream;

import java.util.List;

public class MainParalel {
    public static void main(String[] args) {

        Result rs = new Result();
        List<Customer> ls = rs.customerList(10000);

        long start = System.currentTimeMillis();
        ls.parallelStream().forEach( item -> {
            try {
                Thread.sleep(5);
                System.out.println( item.getName() );
            }catch (Exception ex) {}
        });
        long end = System.currentTimeMillis();
        long between = end - start;
        System.out.println( between );

        // Stream -> 58042
        // paralel -> 7580

    }
}

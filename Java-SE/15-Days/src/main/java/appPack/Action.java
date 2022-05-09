package appPack;

import java.util.Random;

public class Action implements Runnable {

    String path = "";
    public Action(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        new Random();
        for(int i = 0; i<5; i++) {
            System.out.println("For Call : " + path + " : i -" + i  );
            try {
                Thread.sleep(1000);
            }catch (Exception ex) {}
        }
        long end = System.currentTimeMillis();
        long between = end - start;
        System.out.println( path + " Bitti - : " + between + " - " + end  );
    }

}

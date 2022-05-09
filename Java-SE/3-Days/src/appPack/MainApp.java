package appPack;

public class MainApp {
    public static void main(String[] args) {

        // while
        // while( koşul ) { // yapılacak işlmeler }

        boolean status = true;
        int i = 0;
        while ( i<10 ) {
            //if ( i < 10 ) status = false;
            System.out.println("While : " + i);
            i++;
        }

        // do - while
        int j = 0;
        do {
            System.out.println("do while call " + j);
            j++;
        }while ( j < 10 );


    }
}

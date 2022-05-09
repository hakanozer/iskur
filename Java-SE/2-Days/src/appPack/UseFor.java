package appPack;

public class UseFor {
    public static void main(String[] args) {
        
        // loops
        for (int i = 0; i < 10; i++) {
            System.out.println("i : " + i);
        }

        System.out.println("====================");
       label: for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if ( j == 3 ) {
                    continue; // bu kısmı atla
                }

                if ( j == 6) {
                    break label; // döngüyü kırmak, durdurmak
                }
                // j == 6
                System.out.println(i + " " + j);
            }
        }



    }
}

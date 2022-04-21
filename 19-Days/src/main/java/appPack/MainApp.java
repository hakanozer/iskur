package appPack;

import java.util.Locale;

public class MainApp {
    public static void main(String[] args) {

        String data = "Lorem It Ipsum, dizgi ve baskı endüstrisinde dizgi kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune kitabı oluşturmak üzere bir yazı galerisini alarak karıştırdığı 1500'lerden beri endüstri standardı sahte metinler olarak kullanılmıştır. Beşyüz yıl boyunca varlığını sürdürmekle kalmamış, aynı zamanda pek değişmeden elektronik dizgiye de sıçramıştır. 1960'larda Lorem Ipsum pasajları da içeren Letraset yapraklarının yayınlanması ile ve yakın zamanda Aldus PageMaker gibi Lorem Ipsum sürümleri içeren masaüstü yayıncılık yazılımları ile popüler olmuştur.";
        String datax = new String("asda");
        String name = "Ali";
        String adi =  "Erkan";

        System.out.println( name.hashCode() );
        System.out.println( adi.hashCode() );

        datax = data.toLowerCase(Locale.ROOT);
        System.out.println( datax );

        char c =  data.charAt(5);
        System.out.println(c);

        int cIndex = "lerdizgi".compareTo("dizgi");
        System.out.println( cIndex );

        data = data.concat("ali");
        System.out.println( data );

        boolean status = "DiZii".equalsIgnoreCase("dİzİI");
        System.out.println( status );

        boolean statusEnd = data.endsWith("ali");
        System.out.println( "statusEnd " + statusEnd );

        int index = data.indexOf("dizgi");
        System.out.println( index );

        // split
        String[] dizi = data.split("\\.");
        for ( String item : dizi ) {
            System.out.println( item );
        }

        data = data.replaceAll("It", "***");
        System.out.println( data );


        String item = data.substring(data.length() - 3, data.length());
        System.out.println( item );

    }
}

package useInterface;

public interface IUser {

    // interface
    // nesne üretimi yapılamaz?
    // içerisinde gövdesiz methodlar yazmak için kullanılır? (java 8 hariç)
    // uygulamada belirli bir method isimlendirilmesi ve düzen sağlar.

    String userName( int uid );

    boolean userChangePassword( int uid, String newPassword );

    // java 8 yenilikleri
    // default
    default int sum( int a, int b ) {
        return a + b;
    }

    // static final
    String city = "İstanbul";
    String[] arr = { "İstanbul", "İzmir", "Bursa" };


}

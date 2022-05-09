package useInterface;

public class UserImpl implements IUser, IProfile {

    @Override
    public String userName(int uid) {
        int sm = IUser.super.sum(10, 30);
        System.out.println( city );
        System.out.println(sm);
        if ( uid == 10 ) {
            return "Ali Bilmem";
        }
        return null;
    }

    @Override
    public boolean userChangePassword(int uid, String newPassword) {
        return true;
    }

    public int sum( int a, int b ) {
        return a + b;
    }


    @Override
    public String userProfile(int uid) {
        return "Ali";
    }
}

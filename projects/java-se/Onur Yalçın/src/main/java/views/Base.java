package views;

import javax.swing.*;
import java.awt.*;

public class Base extends JFrame {
    @Override
    public void setBackground(Color bgColor) {
        super.getContentPane().setBackground(Color.lightGray);
    }

    //tüm buttonları arka planını şu renkte yap vb bir standart yapıp tüm framelere uygulayabilirsin
}

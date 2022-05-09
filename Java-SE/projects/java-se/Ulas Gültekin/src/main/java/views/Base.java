package views;

import javax.swing.*;
import java.awt.*;

public class Base extends JFrame {
    @Override
    public void setBackground(Color bgColor) {
        super.getContentPane().setBackground(Color.cyan);
    }
}

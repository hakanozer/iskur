package views;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Base extends JFrame {

    @Override
    public void setBackground(Color bgColor) {
        super.getContentPane().setBackground( Color.ORANGE );
    }
    public static String Date(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        System.out.println("Converted String: " + strDate);
        return strDate;
    }

}

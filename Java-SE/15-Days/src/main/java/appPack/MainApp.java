/*
 * Created by JFormDesigner on Wed Apr 13 17:34:08 TRT 2022
 */

package appPack;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.UUID;
import javax.swing.*;
import javax.swing.GroupLayout;


public class MainApp extends JFrame {

    public static void main(String[] args) {
        new MainApp().setVisible(true);
    }

    public MainApp() {
        initComponents();
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        call();
    }

    public void call( ) {

        HashMap<String, String> hm = new HashMap<>();
        HashMap<EUser, String> hmx = new HashMap<>();

        hmx.put(EUser.account, "Ali");
        hmx.put(EUser.customer, "Veli");
        hmx.put(EUser.person, "Hasan");
        System.out.println(hmx);

        System.out.println( hmx.get( EUser.person ) );

    }

    private void btnCallClick(ActionEvent e) {
        Action ac1 = new Action("image-1");
        Action ac2 = new Action("image-2");
        Action ac3 = new Action("image-3");
        Action ac4 = new Action("image-4");
        Action ac5 = new Action("image-5");

        Thread th1 = new Thread(ac1);
        Thread th2 = new Thread(ac2);
        Thread th3 = new Thread(ac3);
        Thread th4 = new Thread(ac4);
        Thread th5 = new Thread(ac5);

        // join - threadlerin bir birini beklemesini sağlar.
        try {
            th1.start();
            th1.join();

            th2.start();
            th2.join();

            th3.start();
            th3.join();

            th4.start();
            th4.join();

            th5.start();
            th5.join();
        }catch (Exception ex) {}

        // Thread start
        // ac.run(); // normal bir fonksiyon çağırmak ile aynıdır.
        // th1.start();  burada thread başlar.





    }

    private void threadBtnClick(ActionEvent e) {
        Settings settings = new Settings();
        settings.start();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- button1 ----
        button1.setText("Implement Runnable");
        button1.addActionListener(e -> btnCallClick(e));

        //---- button2 ----
        button2.setText("Extends Thread");
        button2.addActionListener(e -> threadBtnClick(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(113, 113, 113)
                            .addComponent(button1))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(127, 127, 127)
                            .addComponent(button2)))
                    .addContainerGap(124, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(53, 53, 53)
                    .addComponent(button1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(button2)
                    .addContainerGap(151, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

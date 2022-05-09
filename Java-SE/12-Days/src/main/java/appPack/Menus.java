/*
 * Created by JFormDesigner on Thu Mar 31 18:44:25 TRT 2022
 */

package appPack;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.event.*;

/**
 * @author unknown
 */
public class Menus extends JFrame {
    public Menus() {
        initComponents();
    }


    private void menu3MenuSelected(MenuEvent e) {
        MainApp mainApp = new MainApp();
        mainApp.setVisible(true);
        dispose();
    }

    private void fullBtnExit(MenuEvent e) {
        System.exit(0); // ne varsa öldür
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menu2 = new JMenu();
        menuItem4 = new JMenuItem();
        menuItem5 = new JMenuItem();
        menu3 = new JMenu();
        menu4 = new JMenu();

        //======== this ========
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("Users");
                menu1.setIcon(new ImageIcon(getClass().getResource("/home.png")));

                //---- menuItem1 ----
                menuItem1.setText("List");
                menu1.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("Add");
                menu1.add(menuItem2);
                menu1.addSeparator();

                //---- menuItem3 ----
                menuItem3.setText("Remove");
                menu1.add(menuItem3);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("Products");

                //---- menuItem4 ----
                menuItem4.setText("Add");
                menu2.add(menuItem4);

                //---- menuItem5 ----
                menuItem5.setText("List");
                menu2.add(menuItem5);
            }
            menuBar1.add(menu2);

            //======== menu3 ========
            {
                menu3.setText("Back");
                menu3.addMenuListener(new MenuListener() {
                    @Override
                    public void menuCanceled(MenuEvent e) {}
                    @Override
                    public void menuDeselected(MenuEvent e) {}
                    @Override
                    public void menuSelected(MenuEvent e) {
                        menu3MenuSelected(e);
                    }
                });
            }
            menuBar1.add(menu3);

            //======== menu4 ========
            {
                menu4.setText("Full Exit");
                menu4.addMenuListener(new MenuListener() {
                    @Override
                    public void menuCanceled(MenuEvent e) {}
                    @Override
                    public void menuDeselected(MenuEvent e) {}
                    @Override
                    public void menuSelected(MenuEvent e) {
                        fullBtnExit(e);
                    }
                });
            }
            menuBar1.add(menu4);
        }
        setJMenuBar(menuBar1);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 533, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 339, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenu menu2;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    private JMenu menu3;
    private JMenu menu4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

/*
 * Created by JFormDesigner on Mon Apr 11 15:04:28 TRT 2022
 */

package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Archive extends JFrame {
    public Archive() {
        initComponents();
    }

    private void btnTurnBck(ActionEvent e) {
        Dashboard dashboard = new Dashboard();
        dashboard.setVisible(true);
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        btnTurnBck = new JButton();
        label2 = new JLabel();
        txtSearch = new JTextField();

        //======== this ========
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Archive");

        //---- btnTurnBck ----
        btnTurnBck.setIcon(new ImageIcon(getClass().getResource("/dashboardIcon.png")));
        btnTurnBck.addActionListener(e -> btnTurnBck(e));

        //---- label2 ----
        label2.setText("Search");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 569, Short.MAX_VALUE)
                    .addComponent(btnTurnBck)
                    .addContainerGap())
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(81, 81, 81)
                    .addComponent(label2)
                    .addGap(18, 18, 18)
                    .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(382, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(btnTurnBck))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(396, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JButton btnTurnBck;
    private JLabel label2;
    private JTextField txtSearch;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

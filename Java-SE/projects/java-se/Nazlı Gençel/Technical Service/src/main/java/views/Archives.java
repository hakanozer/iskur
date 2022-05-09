/*
 * Created by JFormDesigner on Sun Apr 10 17:18:38 TRT 2022
 */

package views;

import models.UserImpl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Archives extends Base {
    public Archives() {
        initComponents();
        lblName.setText("Sn."+ UserImpl.name);
    }

    private void txtCustomerSearchKeyReleased(KeyEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblName = new JLabel();
        label1 = new JLabel();
        txtServiceSearch = new JTextField();
        label3 = new JLabel();
        tblService = new JTable();

        //======== this ========
        Container contentPane = getContentPane();

        //---- lblName ----
        lblName.setText(" ");
        lblName.setBackground(new Color(0, 102, 102));
        lblName.setForeground(Color.black);

        //---- label1 ----
        label1.setText("Technical Service");
        label1.setBackground(new Color(0, 102, 102));
        label1.setForeground(Color.black);

        //---- txtServiceSearch ----
        txtServiceSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtCustomerSearchKeyReleased(e);
            }
        });

        //---- label3 ----
        label3.setText("Service Search");

        //---- tblService ----
        tblService.setBackground(new Color(255, 255, 153));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                    .addGap(388, 388, 388)
                    .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label3)
                            .addGap(18, 18, 18)
                            .addComponent(txtServiceSearch, GroupLayout.PREFERRED_SIZE, 572, GroupLayout.PREFERRED_SIZE))
                        .addComponent(tblService, GroupLayout.PREFERRED_SIZE, 678, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label1)
                        .addComponent(lblName))
                    .addGap(37, 37, 37)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(txtServiceSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(49, 49, 49)
                    .addComponent(tblService, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(37, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblName;
    private JLabel label1;
    private JTextField txtServiceSearch;
    private JLabel label3;
    private JTable tblService;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

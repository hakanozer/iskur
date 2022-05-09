/*
 * Created by JFormDesigner on Wed Apr 06 18:22:49 TRT 2022
 */

package views;

import java.awt.event.*;
import models.UserImpl;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Dashboard extends Base {
    public Dashboard() {
        initComponents();
        lblName.setText("Sn. " + UserImpl.name);
    }

    private void btnAddCustomerClick(ActionEvent e) {
        CustomerAdd customerAdd=new CustomerAdd();
        customerAdd.setVisible(true);
        dispose();
    }

    private void btnCustomerAddClick(ActionEvent e) {
        CustomerAdd customerAdd=new CustomerAdd();
        customerAdd.setVisible(true);
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblName = new JLabel();
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        scrollPane1 = new JScrollPane();
        tblNotCompleted = new JTable();
        scrollPane2 = new JScrollPane();
        tblCompleted = new JTable();
        textField1 = new JTextField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Technical Service");

        //---- button1 ----
        button1.setIcon(new ImageIcon(getClass().getResource("/UserAddIcon.png")));
        button1.setToolTipText("Add Customer");
        button1.setFocusable(false);
        button1.addActionListener(e -> btnCustomerAddClick(e));

        //---- button2 ----
        button2.setIcon(new ImageIcon(getClass().getResource("/technical icon.png")));
        button2.setToolTipText("Service Add");
        button2.setFocusable(false);

        //---- button3 ----
        button3.setIcon(new ImageIcon(getClass().getResource("/archive icon.png")));
        button3.setToolTipText("Archive");
        button3.setFocusable(false);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(tblNotCompleted);
        }

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(tblCompleted);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button3, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblName)
                        .addComponent(label1))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(button1)
                        .addComponent(button2)
                        .addComponent(button3)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                    .addGap(38, 38, 38)
                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(43, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblName;
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JScrollPane scrollPane1;
    private JTable tblNotCompleted;
    private JScrollPane scrollPane2;
    private JTable tblCompleted;
    private JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

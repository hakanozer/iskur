/*
 * Created by JFormDesigner on Wed Apr 06 17:15:25 TRT 2022
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
        lblName.setText("Sayın "+UserImpl.name);
    }

    private void btnCustomerAddClick(ActionEvent e) {
        CustomerAdd customerAdd= new CustomerAdd();
        customerAdd.setVisible(true);
        dispose();

    }

    private void btnServiceAddClick(ActionEvent e) {
        Services services=new Services();
        services.setVisible(true);
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblName = new JLabel();
        label1 = new JLabel();
        btnCustomerAdd = new JButton();
        btnServiceAdd = new JButton();
        btnArchive = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        txtSearch = new JTextField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/dashboardIcon.png")).getImage());
        setTitle("Tecnical Service");
        Container contentPane = getContentPane();

        //---- lblName ----
        lblName.setText(" ");
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);

        //---- label1 ----
        label1.setText("Tecnical Service");
        label1.setFont(new Font("Arial", Font.PLAIN, 12));

        //---- btnCustomerAdd ----
        btnCustomerAdd.setIcon(new ImageIcon(getClass().getResource("/userAddIcon.png")));
        btnCustomerAdd.setToolTipText("Customer Add");
        btnCustomerAdd.setFocusable(false);
        btnCustomerAdd.addActionListener(e -> btnCustomerAddClick(e));

        //---- btnServiceAdd ----
        btnServiceAdd.setIcon(new ImageIcon(getClass().getResource("/Service.png")));
        btnServiceAdd.setToolTipText("Service Add");
        btnServiceAdd.setFocusable(false);
        btnServiceAdd.addActionListener(e -> btnServiceAddClick(e));

        //---- btnArchive ----
        btnArchive.setIcon(new ImageIcon(getClass().getResource("/archiveIcon.png")));
        btnArchive.setToolTipText("Archive");
        btnArchive.setFocusable(false);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(table2);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                            .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE))
                        .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                            .addComponent(btnCustomerAdd, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnServiceAdd, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnArchive, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 299, Short.MAX_VALUE)
                            .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(btnServiceAdd, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCustomerAdd, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnArchive, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSearch, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(33, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblName;
    private JLabel label1;
    private JButton btnCustomerAdd;
    private JButton btnServiceAdd;
    private JButton btnArchive;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JTextField txtSearch;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

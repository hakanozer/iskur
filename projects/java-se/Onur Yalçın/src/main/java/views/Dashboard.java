/*
 * Created by JFormDesigner on Wed Apr 06 18:23:36 TRT 2022
 */

package views;

import java.awt.event.*;

import models.DashboardImpl;
import models.UserImpl;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Dashboard extends Base {
    DashboardImpl dash = new DashboardImpl();
    public Dashboard() {
        initComponents();
        lblName.setText("Merhaba, " + UserImpl.name);
        tblServiceDeliver.setModel(dash.serviceDeliverTable());
        tblServiceNew.setModel(dash.serviceNewTable());
    }

    private void btnAddCustomerClick(ActionEvent e) {
        CustomerAdd customerAdd = new CustomerAdd();
        customerAdd.setVisible(true);
        dispose();
    }

    private void btnAddServiceClick(ActionEvent e) {
        Services services = new Services();
        services.setVisible(true);
        dispose();
    }

    private void btnArchiveClick(ActionEvent e) {
        Archive archive = new Archive();
        archive.setVisible(true);
        dispose();
    }

    private void btnLogoutClick(ActionEvent e) {
        Login login = new Login();
        login.setVisible(true);
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblName = new JLabel();
        label1 = new JLabel();
        btnAddCustomer = new JButton();
        btnAddService = new JButton();
        btnArchive = new JButton();
        scrollPane1 = new JScrollPane();
        tblServiceDeliver = new JTable();
        scrollPane2 = new JScrollPane();
        tblServiceNew = new JTable();
        btnLogout = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- lblName ----
        lblName.setText("text");
        lblName.setForeground(Color.black);
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);

        //---- label1 ----
        label1.setText("Technical Service");
        label1.setForeground(Color.black);
        label1.setFont(new Font("Arial", Font.BOLD, 12));
        label1.setBackground(Color.white);

        //---- btnAddCustomer ----
        btnAddCustomer.setIcon(new ImageIcon(getClass().getResource("/add-user.png")));
        btnAddCustomer.setBackground(Color.white);
        btnAddCustomer.setToolTipText("Add Customer");
        btnAddCustomer.setFocusable(false);
        btnAddCustomer.addActionListener(e -> btnAddCustomerClick(e));

        //---- btnAddService ----
        btnAddService.setIcon(new ImageIcon(getClass().getResource("/addservice.png")));
        btnAddService.setBackground(Color.white);
        btnAddService.setToolTipText("Add Service");
        btnAddService.setFocusable(false);
        btnAddService.addActionListener(e -> btnAddServiceClick(e));

        //---- btnArchive ----
        btnArchive.setIcon(new ImageIcon(getClass().getResource("/archive.png")));
        btnArchive.setBackground(Color.white);
        btnArchive.setToolTipText("Archive");
        btnArchive.setFocusable(false);
        btnArchive.addActionListener(e -> btnArchiveClick(e));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(tblServiceDeliver);
        }

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(tblServiceNew);
        }

        //---- btnLogout ----
        btnLogout.setIcon(new ImageIcon(getClass().getResource("/log-out.png")));
        btnLogout.setToolTipText("Log out");
        btnLogout.addActionListener(e -> btnLogoutClick(e));

        //---- label2 ----
        label2.setText("Completed");
        label2.setForeground(Color.black);
        label2.setFont(new Font("Arial", Font.BOLD, label2.getFont().getSize()));

        //---- label3 ----
        label3.setText("Waiting");
        label3.setForeground(Color.black);
        label3.setFont(new Font("Arial", Font.BOLD, label3.getFont().getSize()));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 359, Short.MAX_VALUE)
                    .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label2))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                            .addComponent(btnAddCustomer, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(btnAddService, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)
                            .addComponent(btnArchive, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                            .addGap(160, 160, 160))
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label3)
                            .addGap(0, 644, Short.MAX_VALUE))
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblName)
                        .addComponent(label1))
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(btnAddCustomer, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAddService, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnArchive, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnLogout)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                            .addComponent(label2)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(label3)
                    .addGap(3, 3, 3)
                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(26, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblName;
    private JLabel label1;
    private JButton btnAddCustomer;
    private JButton btnAddService;
    private JButton btnArchive;
    private JScrollPane scrollPane1;
    private JTable tblServiceDeliver;
    private JScrollPane scrollPane2;
    private JTable tblServiceNew;
    private JButton btnLogout;
    private JLabel label2;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

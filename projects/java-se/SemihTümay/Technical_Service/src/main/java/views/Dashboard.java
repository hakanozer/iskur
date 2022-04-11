/*
 * Created by JFormDesigner on Thu Apr 07 12:31:26 TRT 2022
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
    DashboardImpl dashboardImpl = new DashboardImpl();
    public static void main(String[] args) {
        new Dashboard().setVisible(true);
    }
    public Dashboard() {
        initComponents();
        lblName.setText("Dear. " + UserImpl.name);
        tblNotComplieted.setModel(dashboardImpl.customerModel());
    }

    private void btnAddCustomerClick(ActionEvent e) {
        CustomerAdd customerAdd = new CustomerAdd();
        customerAdd.setVisible(true);
        dispose();
    }

    private void btnServicesClick(ActionEvent e) {
        Services services = new Services();
        services.setVisible(true);
        dispose();
    }

    private void btnArchiveClick(ActionEvent e) {
        Archive archive = new Archive();
        archive.setVisible(true);
        dispose();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblService = new JLabel();
        lblName = new JLabel();
        btnAddUser = new JButton();
        btnArchive = new JButton();
        btnService = new JButton();
        scrollPane1 = new JScrollPane();
        tblNotComplieted = new JTable();
        scrollPane2 = new JScrollPane();
        tblComplieted = new JTable();

        //======== this ========
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- lblService ----
        lblService.setText("Technical Service");
        lblService.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblService.setForeground(new Color(33, 17, 17));
        lblService.setHorizontalAlignment(SwingConstants.CENTER);

        //---- lblName ----
        lblName.setText(" ");
        lblName.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblName.setForeground(new Color(33, 17, 17));

        //---- btnAddUser ----
        btnAddUser.setToolTipText("Add User");
        btnAddUser.setIcon(new ImageIcon(getClass().getResource("/add_user.png")));
        btnAddUser.addActionListener(e -> btnAddCustomerClick(e));

        //---- btnArchive ----
        btnArchive.setToolTipText("Archive");
        btnArchive.setIcon(new ImageIcon(getClass().getResource("/archive.png")));
        btnArchive.addActionListener(e -> btnArchiveClick(e));

        //---- btnService ----
        btnService.setToolTipText("Technical Service");
        btnService.setIcon(new ImageIcon(getClass().getResource("/service.png")));
        btnService.addActionListener(e -> btnServicesClick(e));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(tblNotComplieted);
        }

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(tblComplieted);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(btnAddUser, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnService, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnArchive, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(lblService, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
                                .addComponent(scrollPane2))
                            .addGap(0, 3, Short.MAX_VALUE))
                        .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lblService, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(btnService, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnArchive, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddUser, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                    .addGap(24, 24, 24)
                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(90, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblService;
    private JLabel lblName;
    private JButton btnAddUser;
    private JButton btnArchive;
    private JButton btnService;
    private JScrollPane scrollPane1;
    private JTable tblNotComplieted;
    private JScrollPane scrollPane2;
    private JTable tblComplieted;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

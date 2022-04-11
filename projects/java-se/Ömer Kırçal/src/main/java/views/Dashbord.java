/*
 * Created by JFormDesigner on Wed Apr 06 18:22:34 TRT 2022
 */

package views;

import java.awt.event.*;

import models.DasbordImpl;
import models.UserImpl;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Dashbord extends Base {
    DasbordImpl dashbordImpl=new DasbordImpl();
    public Dashbord() {
        initComponents();
        lblName.setText("Sayın "+UserImpl.name);
        //tblCompleted.setModel(dashbordImpl.customerModel());
        tblNotCompleted.setModel(dashbordImpl.customerModel());
    }

    private void btnCustomerAddClicked(ActionEvent e) {
        CustomerAdd customerAdd=new CustomerAdd();
        customerAdd.setVisible(true);
        dispose();
    }

    private void btnAddServiceClicked(ActionEvent e) {
        ServicesAdd services=new ServicesAdd();
        services.setVisible(true);
        dispose();
    }

    private void btnArchiveClicked(ActionEvent e) {
        Archive archive=new Archive();
        archive.setVisible(true);
        dispose();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblName = new JLabel();
        panel1 = new JPanel();
        btnCustomerAdd = new JButton();
        btnAddService = new JButton();
        btnArchive = new JButton();
        txtSearch = new JTextField();
        scrollPane1 = new JScrollPane();
        tblNotCompleted = new JTable();
        scrollPane2 = new JScrollPane();
        tblCompleted = new JTable();
        label3 = new JLabel();
        label4 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/programIcon.png")).getImage());
        setResizable(false);
        Container contentPane = getContentPane();

        //---- lblName ----
        lblName.setText(" ");
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblName.setForeground(SystemColor.windowText);

        //======== panel1 ========
        {
            panel1.setBackground(SystemColor.activeCaption);

            //---- btnCustomerAdd ----
            btnCustomerAdd.setIcon(new ImageIcon(getClass().getResource("/userAddIcon.png")));
            btnCustomerAdd.setBackground(SystemColor.activeCaption);
            btnCustomerAdd.setToolTipText("Add Customer");
            btnCustomerAdd.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnCustomerAdd.setFocusable(false);
            btnCustomerAdd.setBorder(null);
            btnCustomerAdd.addActionListener(e -> btnCustomerAddClicked(e));

            //---- btnAddService ----
            btnAddService.setIcon(new ImageIcon(getClass().getResource("/technicalServiceIcon.png")));
            btnAddService.setBackground(SystemColor.activeCaption);
            btnAddService.setToolTipText("Add Service");
            btnAddService.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnAddService.setFocusable(false);
            btnAddService.setBorder(null);
            btnAddService.addActionListener(e -> btnAddServiceClicked(e));

            //---- btnArchive ----
            btnArchive.setIcon(new ImageIcon(getClass().getResource("/archiveIcon.png")));
            btnArchive.setBackground(SystemColor.activeCaption);
            btnArchive.setToolTipText("Archive");
            btnArchive.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnArchive.setFocusable(false);
            btnArchive.setBorder(null);
            btnArchive.addActionListener(e -> btnArchiveClicked(e));

            //---- txtSearch ----
            txtSearch.setFont(new Font("Segoe UI", Font.BOLD, 14));

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(tblNotCompleted);
            }

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(tblCompleted);
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                .addGap(288, 288, 288)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSearch)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(btnCustomerAdd, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnAddService, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnArchive, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 285, Short.MAX_VALUE)))
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(btnArchive, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddService, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCustomerAdd, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
            );
        }

        //---- label3 ----
        label3.setText("KIRCALO");
        label3.setHorizontalAlignment(SwingConstants.LEFT);
        label3.setFont(new Font("Kristen ITC", Font.BOLD, 26));
        label3.setForeground(SystemColor.textHighlight);

        //---- label4 ----
        label4.setText("TECHNICAL SERVICE");
        label4.setHorizontalAlignment(SwingConstants.LEFT);
        label4.setFont(new Font("Segoe UI Black", Font.BOLD, 22));
        label4.setForeground(SystemColor.textHighlight);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                    .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(lblName)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblName;
    private JPanel panel1;
    private JButton btnCustomerAdd;
    private JButton btnAddService;
    private JButton btnArchive;
    private JTextField txtSearch;
    private JScrollPane scrollPane1;
    public JTable tblNotCompleted;
    private JScrollPane scrollPane2;
    public JTable tblCompleted;
    private JLabel label3;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

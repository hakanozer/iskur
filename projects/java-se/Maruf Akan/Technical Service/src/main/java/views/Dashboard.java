/*
 * Created by JFormDesigner on Wed Apr 06 17:42:05 TRT 2022
 */

package views;

import java.awt.event.*;


import model.CustomerServiceImpl;
import model.UserImpl;
import props.Customer;

import java.awt.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Dashboard extends Base {
    CustomerServiceImpl customerServiceImplFirst=new CustomerServiceImpl(2);
    CustomerServiceImpl customerServiceImplReady=new CustomerServiceImpl(3);
    public Dashboard() {
        initComponents();
        lblName.setText(UserImpl.name);
        tbl1.setModel(customerServiceImplFirst.serviceCustomerTable(null));
        tbl2.setModel(customerServiceImplReady.serviceCustomerTable(null));
    }

    private void btnAddCustomerClick(ActionEvent e) {
        // TODO add your code here
        CustomerAdd customerAdd=new CustomerAdd();
        customerAdd.setVisible(true);
        dispose();

    }

    private void addServiceClick(ActionEvent e) {
        // TODO add your code here
        Services services=new Services();
        services.setVisible(true);
        dispose();

    }

    private void btnArchiveClick(ActionEvent e) {
        // TODO add your code here
        new Archive().setVisible(true);
        dispose();
    }

    private void txtSearch1KeyReleased(KeyEvent e) {
        // TODO add your code here
        String search=txtSearch1.getText().trim().toLowerCase(Locale.ROOT);
        tbl1.setModel(customerServiceImplFirst.serviceCustomerTable(search));
    }

    private void txtSearch2KeyReleased(KeyEvent e) {
        // TODO add your code here
        String search=txtSearch2.getText().trim().toLowerCase(Locale.ROOT);
        tbl2.setModel(customerServiceImplReady.serviceCustomerTable(search));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblName = new JLabel();
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        scrollPane1 = new JScrollPane();
        tbl1 = new JTable();
        txtSearch1 = new JTextField();
        label2 = new JLabel();
        txtSearch2 = new JTextField();
        label3 = new JLabel();
        scrollPane2 = new JScrollPane();
        tbl2 = new JTable();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        Container contentPane = getContentPane();

        //---- lblName ----
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);

        //---- label1 ----
        label1.setText("Technical Service");

        //---- button1 ----
        button1.setSelectedIcon(null);
        button1.setIcon(new ImageIcon(getClass().getResource("/adduser.png")));
        button1.setToolTipText("Customer add");
        button1.setHideActionText(true);
        button1.setFocusable(false);
        button1.addActionListener(e -> btnAddCustomerClick(e));

        //---- button2 ----
        button2.setIcon(new ImageIcon(getClass().getResource("/technics.png")));
        button2.setToolTipText("Technical service");
        button2.setHideActionText(true);
        button2.setFocusable(false);
        button2.addActionListener(e -> addServiceClick(e));

        //---- button3 ----
        button3.setToolTipText("archive");
        button3.setIcon(new ImageIcon(getClass().getResource("/archive.png")));
        button3.setHideActionText(true);
        button3.setFocusable(false);
        button3.addActionListener(e -> btnArchiveClick(e));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(tbl1);
        }

        //---- txtSearch1 ----
        txtSearch1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtSearch1KeyReleased(e);
            }
        });

        //---- label2 ----
        label2.setText("Search :");

        //---- txtSearch2 ----
        txtSearch2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtSearch2KeyReleased(e);
            }
        });

        //---- label3 ----
        label3.setText("Search :");

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(tbl2);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                            .addGap(12, 12, 12)
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
                            .addGap(14, 14, 14))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button3)
                            .addGap(29, 29, 29)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtSearch1, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(0, 352, Short.MAX_VALUE)
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtSearch2, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap())))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblName)
                        .addComponent(label1))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(button1, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                        .addComponent(button2, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                        .addComponent(button3, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2)))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(txtSearch2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addContainerGap())
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
    private JTable tbl1;
    private JTextField txtSearch1;
    private JLabel label2;
    private JTextField txtSearch2;
    private JLabel label3;
    private JScrollPane scrollPane2;
    private JTable tbl2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

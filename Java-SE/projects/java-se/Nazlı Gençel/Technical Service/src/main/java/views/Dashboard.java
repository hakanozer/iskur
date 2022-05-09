/*
 * Created by JFormDesigner on Wed Apr 06 18:22:20 TRT 2022
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
        lblName.setText( "Sn." + UserImpl.name );
    }

    private void btnAddCustomerClick(ActionEvent e) {
        CustomerAdd customerAdd=new CustomerAdd();
        customerAdd.setVisible(true);
        dispose();
    }

    private void addServiceClick(ActionEvent e) {
        Services services=new Services();
        services.setVisible(true);

    }

    private void thisWindowClosing(WindowEvent e) {
        new Dashboard().dispose();
    }

    private void btnArchive(ActionEvent e) {
    Archives archives=new Archives();
    archives.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblName = new JLabel();
        label1 = new JLabel();
        button1 = new JButton();
        btnTechnical = new JButton();
        btnArchive = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        textField1 = new JTextField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("res");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();

        //---- lblName ----
        lblName.setText(" ");
        lblName.setBackground(new Color(0, 102, 102));
        lblName.setForeground(Color.black);

        //---- label1 ----
        label1.setText("Technical Service");
        label1.setBackground(new Color(0, 102, 102));
        label1.setForeground(Color.black);

        //---- button1 ----
        button1.setText(" ");
        button1.setIcon(new ImageIcon(getClass().getResource("/user_add_icon.png")));
        button1.setToolTipText("Customer Add");
        button1.setFocusable(false);
        button1.setBackground(new Color(0, 102, 102));
        button1.addActionListener(e -> btnAddCustomerClick(e));

        //---- btnTechnical ----
        btnTechnical.setText(" ");
        btnTechnical.setIcon(new ImageIcon(getClass().getResource("/technical_service_icon.png")));
        btnTechnical.setToolTipText("Service Add");
        btnTechnical.setFocusable(false);
        btnTechnical.setBackground(new Color(0, 102, 102));
        btnTechnical.addActionListener(e -> addServiceClick(e));

        //---- btnArchive ----
        btnArchive.setText(" ");
        btnArchive.setIcon(new ImageIcon(getClass().getResource("/archive_icon.png")));
        btnArchive.setToolTipText("Archive");
        btnArchive.setFocusable(false);
        btnArchive.setBackground(new Color(0, 102, 102));
        btnArchive.addActionListener(e -> btnArchive(e));

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
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnTechnical, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnArchive, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(lblName, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)))
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label1)
                        .addComponent(lblName))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTechnical, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnArchive, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                    .addGap(32, 32, 32)
                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(36, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblName;
    private JLabel label1;
    private JButton button1;
    private JButton btnTechnical;
    private JButton btnArchive;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

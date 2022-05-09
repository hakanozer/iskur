/*
 * Created by JFormDesigner on Sun Apr 10 21:27:00 TRT 2022
 */

package views;

import java.awt.event.*;

import model.CustomerServiceImpl;
import model.ServiceImpl;
import model.UserImpl;

import java.awt.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Archive extends Base {
    CustomerServiceImpl customerServiceImpl=new CustomerServiceImpl(4);
    public Archive() {
        initComponents();
        lblName.setText(UserImpl.name);
        tblCustomerService.setModel(customerServiceImpl.serviceCustomerTable(null));
    }

    private void txtSearchKeyReleased(KeyEvent e) {
        String search=txtSearch.getText().trim().toLowerCase(Locale.ROOT);
        tblCustomerService.setModel(customerServiceImpl.serviceCustomerTable(search));
    }

    private void thisWindowClosing(WindowEvent e) {
        new Dashboard().setVisible(true);
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        lblName = new JLabel();
        label3 = new JLabel();
        txtSearch = new JTextField();
        scrollPane1 = new JScrollPane();
        tblCustomerService = new JTable();

        //======== this ========
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Technical Service");

        //---- label2 ----
        label2.setText("text");

        //---- lblName ----
        lblName.setText("text");

        //---- label3 ----
        label3.setText(" Search :");

        //---- txtSearch ----
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtSearchKeyReleased(e);
            }
        });

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(tblCustomerService);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtSearch, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblName, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(lblName))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(8, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel lblName;
    private JLabel label3;
    private JTextField txtSearch;
    private JScrollPane scrollPane1;
    private JTable tblCustomerService;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

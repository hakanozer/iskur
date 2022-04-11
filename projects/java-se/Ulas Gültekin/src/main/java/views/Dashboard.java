/*
 * Created by JFormDesigner on Sun Apr 10 01:15:50 TRT 2022
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
public class Dashboard extends Base {
    public static void main(String[] args) {

    }
    public Dashboard() {
        initComponents();
        lblName.setText("Sayýn "+ UserImpl.name+" "+UserImpl.surname);
    }

    private void btnCustomerAdd(ActionEvent e) {
        new CustomerAdd().setVisible(true);
        dispose();
    }

    private void thisWindowClosing(WindowEvent e) {
        System.exit(0);
    }

    private void btnServiceAddClick(ActionEvent e) {
        new Services().setVisible(true);
        dispose();
    }

    private void btnArchiveAdd(ActionEvent e) {
        new Archive().setVisible(true);
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        btnCustomerAdd = new JButton();
        btnServiceAdd = new JButton();
        btnArchiveAdd = new JButton();
        lblError = new JLabel();
        lblName = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();

        //======== this ========
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();

        //---- btnCustomerAdd ----
        btnCustomerAdd.setIcon(new ImageIcon(getClass().getResource("/useradd.png")));
        btnCustomerAdd.addActionListener(e -> btnCustomerAdd(e));

        //---- btnServiceAdd ----
        btnServiceAdd.setIcon(new ImageIcon(getClass().getResource("/serviceadd.png")));
        btnServiceAdd.addActionListener(e -> btnServiceAddClick(e));

        //---- btnArchiveAdd ----
        btnArchiveAdd.setIcon(new ImageIcon(getClass().getResource("/arcihiveeadd.png")));
        btnArchiveAdd.addActionListener(e -> btnArchiveAdd(e));

        //---- label1 ----
        label1.setText("TECHNIC SERVICE");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Segoe UI Black", Font.BOLD, 26));

        //---- label2 ----
        label2.setText("Customer Add");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 18));

        //---- label3 ----
        label3.setText("Service Add");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 18));

        //---- label4 ----
        label4.setText("Archive");
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 18));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 201, Short.MAX_VALUE)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(btnArchiveAdd, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
                            .addGap(16, 16, 16))))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(btnCustomerAdd, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                            .addGap(69, 69, 69)))
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnServiceAdd, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                    .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addGap(290, 290, 290))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGap(136, 136, 136)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnCustomerAdd, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                        .addComponent(btnServiceAdd, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                        .addComponent(btnArchiveAdd, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(159, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton btnCustomerAdd;
    private JButton btnServiceAdd;
    private JButton btnArchiveAdd;
    private JLabel lblError;
    private JLabel lblName;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

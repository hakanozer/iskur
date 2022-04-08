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
        CustomerAdd customerAdd = new CustomerAdd();
        customerAdd.setVisible(true);
        dispose();
    }

    private void addServiceClick(ActionEvent e) {
        Services services = new Services();
        services.setVisible(true);
        dispose();
    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        lblName = new JLabel();
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        textField1 = new JTextField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- lblName ----
        lblName.setText(" ");
        lblName.setHorizontalAlignment(SwingConstants.TRAILING);

        //---- label1 ----
        label1.setText("Technical Service");
        label1.setFont(new Font("Arial", Font.PLAIN, 16));

        //---- button1 ----
        button1.setIcon(new ImageIcon(getClass().getResource("/user_add_icon.png")));
        button1.setToolTipText("Customer Add");
        button1.setFocusable(false);
        button1.addActionListener(e -> btnAddCustomerClick(e));

        //---- button2 ----
        button2.setIcon(new ImageIcon(getClass().getResource("/service_add_icon.png")));
        button2.setToolTipText("Services Add");
        button2.setFocusable(false);
        button2.addActionListener(e -> addServiceClick(e));

        //---- button3 ----
        button3.setIcon(new ImageIcon(getClass().getResource("/archive_add_icon.png")));
        button3.setToolTipText("Archive");
        button3.setFocusable(false);

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
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(button1)
                            .addGap(18, 18, 18)
                            .addComponent(button2)
                            .addGap(18, 18, 18)
                            .addComponent(button3)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                        .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button3, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel lblName;
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

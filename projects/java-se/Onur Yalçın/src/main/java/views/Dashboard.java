/*
 * Created by JFormDesigner on Wed Apr 06 17:42:05 TRT 2022
 */

package views;

import java.awt.event.*;
import model.UserImpl;
import props.Customer;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Dashboard extends Base {
    public Dashboard() {
        initComponents();
        lblName.setText(UserImpl.name);
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
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
                            .addGap(143, 143, 143)
                            .addComponent(textField1, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                                .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE))
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
                        .addComponent(textField1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
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
    private JTable table1;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

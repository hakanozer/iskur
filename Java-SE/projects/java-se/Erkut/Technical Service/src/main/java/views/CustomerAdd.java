/*
 * Created by JFormDesigner on Thu Apr 07 15:42:13 TRT 2022
 */

package views;

import models.CustomerImpl;
import models.UserImpl;
import props.Customer;
import utils.Util;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class CustomerAdd extends Base {
    CustomerImpl customerImpl=new CustomerImpl();


    public CustomerAdd() {
        initComponents();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblName = new JLabel();
        panel1 = new JPanel();
        scrollPane4 = new JScrollPane();
        label2 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        txtName = new JTextField();
        txtSurname = new JTextField();
        txtEmail = new JTextField();
        txtPhone = new JTextField();
        label6 = new JLabel();
        scrollPane1 = new JScrollPane();
        txtAddress = new JTextArea();
        lblError = new JLabel();
        btnCustomerAdd = new JButton();
        btnCustomerUpdate = new JButton();
        btnCustomerDelete = new JButton();
        scrollPane2 = new JScrollPane();
        tblCustomer = new JTable();
        label7 = new JLabel();
        label8 = new JLabel();

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

            //---- label2 ----
            label2.setText("NAME");
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            label2.setFont(new Font("Arial", Font.BOLD, 14));
            label2.setForeground(Color.black);

            //---- label9 ----
            label9.setText("SURNAME");
            label9.setHorizontalAlignment(SwingConstants.RIGHT);
            label9.setFont(new Font("Arial", Font.BOLD, 14));
            label9.setForeground(Color.black);

            //---- label10 ----
            label10.setText("PHONE");
            label10.setHorizontalAlignment(SwingConstants.RIGHT);
            label10.setFont(new Font("Arial", Font.BOLD, 14));
            label10.setForeground(Color.black);

            //---- label11 ----
            label11.setText("E-MAIL");
            label11.setHorizontalAlignment(SwingConstants.RIGHT);
            label11.setFont(new Font("Arial", Font.BOLD, 14));
            label11.setForeground(Color.black);

            //---- txtName ----
            txtName.setFont(new Font("Segoe UI", Font.BOLD, 12));

            //---- txtSurname ----
            txtSurname.setFont(new Font("Segoe UI", Font.BOLD, 12));

            //---- txtEmail ----
            txtEmail.setFont(new Font("Segoe UI", Font.BOLD, 12));

            //---- txtPhone ----
            txtPhone.setFont(new Font("Segoe UI", Font.BOLD, 12));

            //---- label6 ----
            label6.setText("ADDRESS");
            label6.setHorizontalAlignment(SwingConstants.RIGHT);
            label6.setFont(new Font("Arial", Font.BOLD, 14));
            label6.setForeground(Color.black);

            //======== scrollPane1 ========
            {

                //---- txtAddress ----
                txtAddress.setFont(new Font("Segoe UI", Font.BOLD, 12));
                scrollPane1.setViewportView(txtAddress);
            }

            //---- lblError ----
            lblError.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblError.setForeground(new Color(245, 6, 6));
            lblError.setText(" ");
            lblError.setHorizontalAlignment(SwingConstants.CENTER);

            //---- btnCustomerAdd ----
            btnCustomerAdd.setBackground(SystemColor.activeCaption);
            btnCustomerAdd.setForeground(Color.white);
            btnCustomerAdd.setIcon(new ImageIcon(getClass().getResource("/btnAddIcon.png")));
            btnCustomerAdd.setBorder(null);
            btnCustomerAdd.setToolTipText("ADD");

            //---- btnCustomerUpdate ----
            btnCustomerUpdate.setBackground(SystemColor.activeCaption);
            btnCustomerUpdate.setForeground(Color.white);
            btnCustomerUpdate.setIcon(new ImageIcon(getClass().getResource("/btnUpdateIcon.png")));
            btnCustomerUpdate.setBorder(null);
            btnCustomerUpdate.setToolTipText("UPDATE");

            //---- btnCustomerDelete ----
            btnCustomerDelete.setBackground(SystemColor.activeCaption);
            btnCustomerDelete.setForeground(Color.white);
            btnCustomerDelete.setIcon(new ImageIcon(getClass().getResource("/btnDeleteIcon.png")));
            btnCustomerDelete.setBorder(null);
            btnCustomerDelete.setToolTipText("DELETE");

            //======== scrollPane2 ========
            {

                //---- tblCustomer ----
                tblCustomer.setFont(new Font("Segoe UI", Font.BOLD, 14));
                scrollPane2.setViewportView(tblCustomer);
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addGroup(panel1Layout.createParallelGroup()
                                                            .addGroup(panel1Layout.createSequentialGroup()
                                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                            .addGroup(panel1Layout.createSequentialGroup()
                                                                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                                                            .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                                                                    .addComponent(label11, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
                                                                    .addGap(18, 18, 18)
                                                                    .addGroup(panel1Layout.createParallelGroup()
                                                                            .addGroup(panel1Layout.createSequentialGroup()
                                                                                    .addComponent(label9, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                                                            .addGroup(panel1Layout.createSequentialGroup()
                                                                                    .addComponent(label10, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
                                                            .addGroup(panel1Layout.createSequentialGroup()
                                                                    .addComponent(label6, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addGroup(panel1Layout.createParallelGroup()
                                                                            .addComponent(lblError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(scrollPane1))))
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnCustomerAdd, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnCustomerUpdate, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnCustomerDelete, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                    .addComponent(scrollPane2)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
                                                    .addContainerGap())))
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup()
                                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                                    .addContainerGap(323, Short.MAX_VALUE)
                                                    .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addGroup(panel1Layout.createParallelGroup()
                                                            .addGroup(panel1Layout.createSequentialGroup()
                                                                    .addGroup(panel1Layout.createParallelGroup()
                                                                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                                    .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                                                            .addGroup(panel1Layout.createSequentialGroup()
                                                                                    .addGap(6, 6, 6)
                                                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                            .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                                            .addComponent(label9, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))
                                                                    .addGap(18, 18, 18)
                                                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(label11, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(label10, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                                                    .addGap(18, 18, 18)
                                                                    .addGroup(panel1Layout.createParallelGroup()
                                                                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(panel1Layout.createSequentialGroup()
                                                                    .addGap(6, 6, 6)
                                                                    .addGroup(panel1Layout.createParallelGroup()
                                                                            .addComponent(btnCustomerUpdate, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(btnCustomerAdd, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(btnCustomerDelete, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))))
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap())
            );
        }

        //---- label7 ----
        label7.setText("KIRCALO");
        label7.setHorizontalAlignment(SwingConstants.LEFT);
        label7.setFont(new Font("Kristen ITC", Font.BOLD, 26));
        label7.setForeground(SystemColor.textHighlight);

        //---- label8 ----
        label8.setText("TECHNICAL SERVICE");
        label8.setHorizontalAlignment(SwingConstants.LEFT);
        label8.setFont(new Font("Segoe UI Black", Font.BOLD, 22));
        label8.setForeground(SystemColor.textHighlight);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label7, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(label8, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                                                .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE))
                                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(lblName)
                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label7, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label8, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblName;
    private JPanel panel1;
    private JScrollPane scrollPane4;
    private JLabel label2;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JLabel label6;
    private JScrollPane scrollPane1;
    private JTextArea txtAddress;
    private JLabel lblError;
    private JButton btnCustomerAdd;
    private JButton btnCustomerUpdate;
    private JButton btnCustomerDelete;
    private JScrollPane scrollPane2;
    private JTable tblCustomer;
    private JLabel label7;
    private JLabel label8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

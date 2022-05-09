/*
 * Created by JFormDesigner on Thu Apr 07 15:41:54 TRT 2022
 */

package views;

import models.CustomerImpl;
import models.UserImpl;
import props.Customer;
import utils.DB;
import utils.Util;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

/**
 * @author unknown
 */
public class CustomerAdd extends Base {
    DB db=new DB();
    CustomerImpl cusImpl=new CustomerImpl();
    int row=-1;
    int selectedId=0;
    int status=0;

    public CustomerAdd() {

        initComponents();
        lblName.setText("Sn."+ UserImpl.name);
        tblCustomerAdd.setModel(cusImpl.customerTable());

    }

    private void thisWindowClosing(WindowEvent e) {
        new Dashboard().setVisible(true);

    }

    private Customer fncDataValid(){

        String name=txtName.getText().trim();
        String surname=txtSurname.getText().trim();
        String email=txtEmail.getText().trim().toLowerCase();
        String phone=txtPhone.getText().trim();
        String address=txtAddress.getText().trim();

        if (name.equals("")){
            lblError.setText("Name is Empty!!!");
            txtName.requestFocus();
        }else if (surname.equals("")){
            lblError.setText("Surname is Empty!!!");
            txtSurname.requestFocus();
        }else if (email.equals("")){
            lblError.setText("Email is Empty!!!");
            txtEmail.requestFocus();
        }else if(!Util.isValidEmailAddress(email)){
            lblError.setText("Email Validation Error!!!");
            txtEmail.requestFocus();
        }else if (phone.equals("")){
            lblError.setText("Phone is Empty!!!");
            txtPhone.requestFocus();
        }
        else if (address.equals("")){
            lblError.setText("Adress is Empty!!!");
            txtAddress.requestFocus();
        }else {
            lblError.setText("");
            Customer c=new Customer(0,name,surname,email,phone,address);
            return c;

        }
        return null;
    }

    private void btnCustomerAdd(ActionEvent e) {
        Customer c=fncDataValid();//bellekte bir defa c oluştu
        //bellekte ayrılmış nesnenin farklı iki referansı var
        if (c!=null){
            int status= cusImpl.customerInsert(c);
            if (status>0){
                System.out.println("Insert Success");
            }
            else{
                if (status==-1){
                    lblError.setText("E Mail or Phone have already used");
                }
                else {
                    lblError.setText("Insert Error");

                }
                tblCustomerAdd.clearSelection();
                textboxClear();
            }
            String name=txtName.getText();
            String surname=txtSurname.getText();
            String email=txtEmail.getText();
            String phone=txtPhone.getText();
            String address=txtAddress.getText();
            Customer cs=new Customer(0,name,surname,email,phone,address);
            tblCustomerAdd.setModel(cusImpl.customerTable());

        }
}



    private void txtNameKeyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            fncDataValid();
        }
    }

    private void txtSurnameKeyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            fncDataValid();
        }
    }

    private void txtEmailKeyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            fncDataValid();
        }
    }

    private void txtPhoneKeyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            btnCustomerAdd(null);//bu şekilde de olur
        }
    }

    private void txtAddressKeyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            btnCustomerAdd(null);
        }
    }

    private void btnUpdateClick(ActionEvent e) {
        Customer cUp=fncDataValid();
        row = tblCustomerAdd.getSelectedRow();
        if (row == -1) {
            lblError.setText("Please select the customer you want to update from the table.");
        } else {
            lblError.setText("");
            int question=JOptionPane.showConfirmDialog(this,"Please select the field you want to change!","Update",JOptionPane.YES_NO_OPTION);
             if (question==0){

                 if (cUp != null){
                     status= cusImpl.customerUpdate(cUp);
                 }
                 if(status>0){
                     textboxClear();
                     tblCustomerAdd.setModel(cusImpl.customerTable());

                 }
             }else {
                 tblCustomerAdd.clearSelection();
                 textboxClear();
             }

        }
    }

    private void btnDeleteClick(ActionEvent e) {
        row = tblCustomerAdd.getSelectedRow();
        if (row == -1) {
            lblError.setText("Please select the customer you want to delete from the table.");
        } else {
            lblError.setText("");
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure that you want to delete the customer","Delete Process",JOptionPane.YES_NO_OPTION);
            if(answer==0){
                cusImpl.customerDelete(selectedId);
                tblCustomerAdd.setModel(cusImpl.customerTable());

            }else{ tblCustomerAdd.clearSelection();
                textboxClear();}
        }
    }
    public void textboxClear(){
        txtName.setText("");
        txtSurname.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
    }


    private void tblCustomerAddMouseReleased(MouseEvent e) {
        row=tblCustomerAdd.getSelectedRow();

        selectedId=(Integer) tblCustomerAdd.getValueAt(row,0);
        String name=(String) tblCustomerAdd.getValueAt(row,1);
        String surname=(String) tblCustomerAdd.getValueAt(row,2);
        String email=(String) tblCustomerAdd.getValueAt(row,3);
        String phone=(String) tblCustomerAdd.getValueAt(row,4);
        String address=(String) tblCustomerAdd.getValueAt(row,5);

        txtName.setText(name);
        txtSurname.setText(surname);
        txtEmail.setText(email);
        txtPhone.setText(phone);
        txtAddress.setText(address);
    }






    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblName = new JLabel();
        label1 = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        txtName = new JTextField();
        label3 = new JLabel();
        txtSurname = new JTextField();
        txtEmail = new JTextField();
        label4 = new JLabel();
        label5 = new JLabel();
        txtPhone = new JTextField();
        label6 = new JLabel();
        scrollPane1 = new JScrollPane();
        txtAddress = new JTextArea();
        btnAdd = new JButton();
        scrollPane2 = new JScrollPane();
        tblCustomerAdd = new JTable();
        btnDelete = new JButton();
        btnUpdate = new JButton();
        lblError = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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

        //======== panel1 ========
        {
            panel1.setForeground(new Color(102, 102, 102));
            panel1.setBackground(new Color(255, 255, 153));

            //---- label2 ----
            label2.setText("Name");

            //---- txtName ----
            txtName.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txtNameKeyReleased(e);
                }
            });

            //---- label3 ----
            label3.setText("Surname");

            //---- txtSurname ----
            txtSurname.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txtSurnameKeyReleased(e);
                }
            });

            //---- txtEmail ----
            txtEmail.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txtEmailKeyReleased(e);
                }
            });

            //---- label4 ----
            label4.setText("E Mail");

            //---- label5 ----
            label5.setText("Phone");

            //---- txtPhone ----
            txtPhone.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txtPhoneKeyReleased(e);
                }
            });

            //---- label6 ----
            label6.setText("Address");

            //======== scrollPane1 ========
            {

                //---- txtAddress ----
                txtAddress.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        txtAddressKeyReleased(e);
                    }
                });
                scrollPane1.setViewportView(txtAddress);
            }

            //---- btnAdd ----
            btnAdd.setIcon(new ImageIcon(getClass().getResource("/add_icon.png")));
            btnAdd.setText("Add");
            btnAdd.setBackground(new Color(153, 255, 255));
            btnAdd.addActionListener(e -> btnCustomerAdd(e));

            //======== scrollPane2 ========
            {

                //---- tblCustomerAdd ----
                tblCustomerAdd.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        tblCustomerAddMouseReleased(e);
                    }
                });
                scrollPane2.setViewportView(tblCustomerAdd);
            }

            //---- btnDelete ----
            btnDelete.setIcon(new ImageIcon(getClass().getResource("/delete_icon.png")));
            btnDelete.setText("Delete");
            btnDelete.setBackground(new Color(153, 255, 255));
            btnDelete.addActionListener(e -> btnDeleteClick(e));

            //---- btnUpdate ----
            btnUpdate.setIcon(new ImageIcon(getClass().getResource("/update_icon.png")));
            btnUpdate.setText("Update");
            btnUpdate.setBackground(new Color(153, 255, 255));
            btnUpdate.addActionListener(e -> btnUpdateClick(e));

            //---- lblError ----
            lblError.setText(" ");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label2)
                                    .addComponent(label4))
                                .addGap(26, 26, 26)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(txtName)
                                    .addComponent(txtEmail))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                        .addComponent(label5)
                                        .addGap(18, 18, 18))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(label3)
                                        .addGap(18, 18, 18)))
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSurname, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                    .addComponent(txtPhone, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)))
                            .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label6)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(panel1Layout.createParallelGroup()
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                .addComponent(btnAdd)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnUpdate)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)))))
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2)
                            .addComponent(label3)
                            .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4)
                            .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5)
                            .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(label6)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblError)
                                .addGap(6, 6, 6)))
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdate)
                            .addComponent(btnDelete)
                            .addComponent(btnAdd))
                        .addGap(26, 26, 26)
                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                            .addGap(384, 384, 384)
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label1)
                        .addComponent(lblName))
                    .addGap(18, 18, 18)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(34, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblName;
    private JLabel label1;
    private JPanel panel1;
    private JLabel label2;
    private JTextField txtName;
    private JLabel label3;
    private JTextField txtSurname;
    private JTextField txtEmail;
    private JLabel label4;
    private JLabel label5;
    private JTextField txtPhone;
    private JLabel label6;
    private JScrollPane scrollPane1;
    private JTextArea txtAddress;
    private JButton btnAdd;
    private JScrollPane scrollPane2;
    private JTable tblCustomerAdd;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JLabel lblError;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

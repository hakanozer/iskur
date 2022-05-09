/*
 * Created by JFormDesigner on Sun Apr 10 02:00:55 TRT 2022
 */

package views;

import models.CustomerImpl;
import models.ServiceImpl;
import models.UserImpl;
import props.Customer;
import utils.DB;
import utils.Util;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class CustomerAdd extends Base {
    DB db = new DB();
    CustomerImpl cus = new CustomerImpl();
    ServiceImpl ser = new ServiceImpl();
    int row = -1;
    int value;

    public CustomerAdd() {
        initComponents();
        lblName.setText("Sayýn " + UserImpl.name + " " + UserImpl.surname);
        tblCustormer.setModel(cus.customerModel());
    }

    private void thisWindowClosing(WindowEvent e) {
        new Dashboard().setVisible(true);
        dispose();
    }

    public void rowValue() {
        int column = 0;
        row = tblCustormer.getSelectedRow();
        value = (int) tblCustormer.getModel().getValueAt(row, column);
        String cid = String.valueOf(tblCustormer.getValueAt(row, 0));
        String name = String.valueOf(tblCustormer.getValueAt(row, 1));
        String surname = String.valueOf(tblCustormer.getValueAt(row, 2));
        String email = String.valueOf(tblCustormer.getValueAt(row, 3));
        String phone = String.valueOf(tblCustormer.getValueAt(row, 4));
        String address = String.valueOf(tblCustormer.getValueAt(row, 5));
        System.out.println("val" + value);
        txtName.setText(name);
        txtSurname.setText(surname);
        txtEmail.setText(email);
        txtPhone.setText(phone);
        txtAddress.setText(address);


    }

    private Customer fncDataValid() {

        String name = txtName.getText().trim();
        String surname = txtSurname.getText().trim();
        String email = txtEmail.getText().trim().toLowerCase();  //boþluk varsa al trimle sil
        String phone = txtPhone.getText().trim();
        String address = txtAddress.getText().trim();

        if (name.equals("")) {
            lblError.setText("Name is Empty!!!");
            txtName.requestFocus();
        } else if (surname.equals("")) {
            lblError.setText("Surname is Empty!!!");
            txtSurname.requestFocus();
        } else if (email.equals("")) {
            lblError.setText("Email is Empty!!!");
            txtEmail.requestFocus();
        } else if (!Util.isValidEmailAddress(email)) { //fprmatý baþkaysa
            lblError.setText("Email Validation Error!!!");
            txtEmail.requestFocus();
        } else if (phone.equals("")) { //boþþa sýfýrsa
            lblError.setText("Phone is Empty!!!");
            txtPhone.requestFocus();//imleç otomatik olarak passwworde gelicek
        } else if (address.equals("")) { //boþþa sýfýrsa
            lblError.setText("Adress is Empty!!!");
            txtAddress.requestFocus();//imleç otomatik olarak passwworde gelicek
        } else {
            lblError.setText("");
            Customer c = new Customer(0, name, surname, email, phone, address);
            return c;
        }
        return null; //olumsuz halinde
    }

    private void btnAddclick(ActionEvent e) {
        Customer c = fncDataValid();

        int status=cus.customerInsert(c);
        if (status>0){
            System.out.println("Ekleme Baþarýlý");
            tblCustormer.setModel(cus.customerModel());
            txtName.setText("");
            txtSurname.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtAddress.setText("");

        }else{
            if (status==-1){
                lblError.setText("Email or Phone number already exists");
            }else{
                lblError.setText("Insert Error");

            }


        }




    }

    private void btnUpdateClick(ActionEvent e) {
        String name= txtName.getText();
        String surname= txtSurname.getText();
        String email= txtEmail.getText();
        String phone= txtPhone.getText();
        String address= txtAddress.getText();

        Customer customer= new Customer(value,name,surname,email,phone,address);
        if(row!=-1){
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to update the customer");
            if (answer==0){
                cus.customerUpdate(customer);
                tblCustormer.setModel(cus.customerModel());
                txtName.setText("");
                txtSurname.setText("");
                txtEmail.setText("");
                txtPhone.setText("");
                txtAddress.setText("");
                row=-1;
            }

        }
        JOptionPane.showMessageDialog(this,"Please Choose");

    }

    private void btnDeleteClick(ActionEvent e) {

        if (row !=-1){
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to delete the customer?","Delete Window",JOptionPane.YES_OPTION);//parent component nerede görüneceði this button
            System.out.println(answer); //butonlarýn sýrasý soldan baþlayarak 0 1 buton sýrasý öyle belirlenir.

            if (answer==0){
                cus.customerDelete(value);
//                System.out.println("delete row "+value);
                tblCustormer.setModel(cus.customerModel()); //tabloyu refresh et
                txtName.setText("");
                txtSurname.setText("");
                txtEmail.setText("");
                txtPhone.setText("");
                txtAddress.setText("");
                row=-1;
            }
        }

        else{
            JOptionPane.showMessageDialog(this,"Please choose."); //this kendini burada ortala
            //show confirm anlaþmayý kabul etmek istiyor musun.
        }
    }

    private void tblCustormerMouseClicked(MouseEvent e) {
        rowValue();
    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label1 = new JLabel();
        txtName = new JTextField();
        label2 = new JLabel();
        txtSurname = new JTextField();
        label3 = new JLabel();
        txtEmail = new JTextField();
        label4 = new JLabel();
        txtPhone = new JTextField();
        label5 = new JLabel();
        txtAddress = new JTextField();
        btnAdd = new JButton();
        btnUpdate = new JButton();
        btnDelete = new JButton();
        lblError = new JLabel();
        label6 = new JLabel();
        lblName = new JLabel();
        scrollPane1 = new JScrollPane();
        tblCustormer = new JTable();

        //======== this ========
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();

        //======== panel1 ========
        {

            //---- label1 ----
            label1.setText("Name");

            //---- label2 ----
            label2.setText("Surname");

            //---- label3 ----
            label3.setText("E-Mail");

            //---- label4 ----
            label4.setText("Phone");

            //---- label5 ----
            label5.setText("Address");

            //---- btnAdd ----
            btnAdd.setIcon(new ImageIcon(getClass().getResource("/useradd.png")));
            btnAdd.addActionListener(e -> btnAddclick(e));

            //---- btnUpdate ----
            btnUpdate.setIcon(new ImageIcon(getClass().getResource("/updateicon.png")));
            btnUpdate.addActionListener(e -> btnUpdateClick(e));

            //---- btnDelete ----
            btnDelete.setIcon(new ImageIcon(getClass().getResource("/2303123_bin_delete_garbage_remove_trash_icon.png")));
            btnDelete.addActionListener(e -> btnDeleteClick(e));

            //---- label6 ----
            label6.setText("Technical Service");
            label6.setHorizontalAlignment(SwingConstants.CENTER);
            label6.setFont(new Font("Source Sans Pro", Font.BOLD, 16));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(40, Short.MAX_VALUE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(156, 156, 156)
                                .addComponent(label6, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                    .addGap(36, 36, 36)
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                            .addGap(6, 6, 6)
                                            .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addGap(32, 32, 32)
                                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                            .addGap(6, 6, 6)
                                            .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addGap(40, 40, 40)
                                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                            .addGap(6, 6, 6)
                                            .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))))
                                .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(label5, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtAddress))))
                        .addContainerGap(25, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(label6, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(lblName, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(7, 7, 7)
                                    .addComponent(label2))
                                .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label1)
                                .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGap(45, 45, 45)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label3)
                                    .addComponent(label4))))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addComponent(label5)
                                .addGap(7, 7, 7))
                            .addComponent(txtAddress, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(23, Short.MAX_VALUE))
            );
        }

        //======== scrollPane1 ========
        {

            //---- tblCustormer ----
            tblCustormer.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tblCustormerMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(tblCustormer);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addContainerGap())
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label1;
    private JTextField txtName;
    private JLabel label2;
    private JTextField txtSurname;
    private JLabel label3;
    private JTextField txtEmail;
    private JLabel label4;
    private JTextField txtPhone;
    private JLabel label5;
    private JTextField txtAddress;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JLabel lblError;
    private JLabel label6;
    private JLabel lblName;
    private JScrollPane scrollPane1;
    private JTable tblCustormer;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

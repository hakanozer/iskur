/*
 * Created by JFormDesigner on Thu Apr 07 15:41:51 TRT 2022
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

    CustomerImpl cus = new CustomerImpl();

    int row=0;
    int status=0;
    int val;

    public CustomerAdd() {
        initComponents();
        lblName.setText( "Sn." + UserImpl.name );
    }

    private Customer fncDataValid() {

        String name=txtName.getText().trim();
        String surname=txtSurname.getText().trim();
        String email=txtEmail.getText().trim().toLowerCase();  //boşluk varsa al trimle sil
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
        }else if(!Util.isValidEmailAddress(email)){ //fprmatı başkaysa
            lblError.setText("Email Validation Error!!!");
            txtEmail.requestFocus();
        }else if (phone.equals("")){ //boşşa sıfırsa
            lblError.setText("Phone is Empty!!!");
            txtPhone.requestFocus();//imleç otomatik olarak passwworde gelicek
        }
        else if (address.equals("")){ //boşşa sıfırsa
            lblError.setText("Adress is Empty!!!");
            txtAddress.requestFocus();//imleç otomatik olarak passwworde gelicek
        }else {
            lblError.setText("");
            Customer c = new Customer(0,name, surname, email, phone, address);
            return c;
        }
        return null; //olumsuz halinde
    }


    private void thisWindowClosing(WindowEvent e) {
        new Dashboard().setVisible(true);
    }

    private void btnCustomerAdd(ActionEvent e) {
        Customer c = fncDataValid();
        if ( c != null ) {
            int status = cus.customerInsert(c);
            if ( status > 0) {
                System.out.println("Ekleme Başarılı");
            }else {
                if ( status == -1 ) {
                    lblError.setText("E-Mail or Phone have already used");
                }else {
                    lblError.setText("Insert Error");
                }
            }
        }

    }

    private void btnUpdateCliskAction(ActionEvent e) {
        String name= txtName.getText();
        String surname=txtSurname.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();

        Customer customer = new Customer(0,name, surname,email,phone,address);
        if (row!=-1){
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure for update ?","Update Process",JOptionPane.YES_OPTION);
            if (answer==0){
                cus.customerUpdate(customer);
                table1.setModel(cus.customerTable()); //tabloyu refresh et
                txtName.setText("");
                txtSurname.setText("");
                txtEmail.setText("");
                txtPhone.setText("");
                txtAddress.setText("");
                row=-1;

            }
            }else {JOptionPane.showMessageDialog(this,"Please Make Your Choice."); //this kendini burada ortala
            //show confirm anlaşmayı kabul etmek istiyor musun.

        }
        }

        private void btnDeleteClick(ActionEvent e) {
            if(row !=-1){
                int answer =JOptionPane.showConfirmDialog(this,"Are you sure you want to delete the customer?","delete Window",JOptionPane.YES_NO_OPTION);
                System.out.println(answer);

                if (answer==0){
                    cus.customerDelete(val);
                    table1.setModel(cus.customerTable());//refresh
                    txtName.setText("");
                    txtSurname.setText("");
                    txtEmail.setText("");
                    txtPhone.setText("");
                    txtAddress.setText("");
                    row=-1;
                }

            }else {JOptionPane.showMessageDialog(this,"Please Choose");}
        }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        lblName = new JLabel();
        label2 = new JLabel();
        txtName = new JTextField();
        label3 = new JLabel();
        txtSurname = new JTextField();
        label4 = new JLabel();
        txtEmail = new JTextField();
        label5 = new JLabel();
        txtPhone = new JTextField();
        label6 = new JLabel();
        scrollPane1 = new JScrollPane();
        txtAddress = new JTextArea();
        btnAdd = new JButton();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
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

        //---- label1 ----
        label1.setText("Technical Service");
        label1.setFont(new Font("Arial", Font.PLAIN, 16));

        //---- lblName ----
        lblName.setText(" ");
        lblName.setHorizontalAlignment(SwingConstants.TRAILING);

        //---- label2 ----
        label2.setText("Name");

        //---- label3 ----
        label3.setText("Surname");

        //---- label4 ----
        label4.setText("E-mail");

        //---- label5 ----
        label5.setText("Phone");

        //---- label6 ----
        label6.setText("Address");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(txtAddress);
        }

        //---- btnAdd ----
        btnAdd.setIcon(new ImageIcon(getClass().getResource("/addIconCus.png")));
        btnAdd.setToolTipText("Customer Add");
        btnAdd.addActionListener(e -> btnCustomerAdd(e));

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(table1);
        }

        //---- btnDelete ----
        btnDelete.setIcon(new ImageIcon(getClass().getResource("/deleteIconCus.png")));
        btnDelete.setToolTipText("Customer Delete");
        btnDelete.addActionListener(e -> btnDeleteClick(e));

        //---- btnUpdate ----
        btnUpdate.setIcon(new ImageIcon(getClass().getResource("/updateIconCus.png")));
        btnUpdate.setToolTipText("Customer Update");
        btnUpdate.addActionListener(e -> btnUpdateCliskAction(e));

        //---- lblError ----
        lblError.setText(" ");
        lblError.setForeground(new Color(244, 92, 92));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(label2)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label3)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(label6)
                                .addComponent(label4))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
                                .addComponent(scrollPane1)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
                                    .addGap(38, 38, 38)
                                    .addComponent(label5)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(lblError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(scrollPane2))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(label1))
                        .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2)
                            .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3)))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(7, 7, 7)
                            .addComponent(label5))
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4)
                            .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label6)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblError)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(btnUpdate)
                            .addGap(241, 241, 241))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(btnAdd)
                                .addComponent(btnDelete))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel lblName;
    private JLabel label2;
    private JTextField txtName;
    private JLabel label3;
    private JTextField txtSurname;
    private JLabel label4;
    private JTextField txtEmail;
    private JLabel label5;
    private JTextField txtPhone;
    private JLabel label6;
    private JScrollPane scrollPane1;
    private JTextArea txtAddress;
    private JButton btnAdd;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JLabel lblError;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

/*
 * Created by JFormDesigner on Thu Apr 07 15:41:56 TRT 2022
 */

package views;

import models.CustomerImpl;
import models.UserImpl;
import props.Customer;
import utils.Util;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * @author unknown
 */
public class CustomerAdd extends Base {
    CustomerImpl customerImpl = new CustomerImpl();
    int row=-1;
    int selectId = 0;
    public CustomerAdd() {
        initComponents();
        lblName.setText("Dear. " + UserImpl.name);
        tblCustomer.setModel(customerImpl.customerTablemodel());
    }

    private void thisWindowClosing(WindowEvent e) {
        new Dashboard().setVisible(true);
    }


    public void rowValue(){
        int column = 0; //1. kolondakini al.
        row = tblCustomer.getSelectedRow(); //seçili olan row u getir.  //dizi elemanı gibi 0 dan başlar row
        selectId = (int) tblCustomer.getValueAt(row, column);
        String cid= String.valueOf(tblCustomer.getValueAt(row,0));  //cast ettik obje olduğu için.
        String name= String.valueOf(tblCustomer.getValueAt(row,1));
        String surname= String.valueOf(tblCustomer.getValueAt(row,2));
        String email= String.valueOf(tblCustomer.getValueAt(row,3));
        String phone= String.valueOf(tblCustomer.getValueAt(row,4));
        String address= String.valueOf(tblCustomer.getValueAt(row,5));
        System.out.println("val "+ selectId);

        txtName.setText(name);
        txtSurname.setText(surname);
        txtEmail.setText(email);
        txtPhone.setText(phone);
        txtAddress.setText(address);
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
            Customer c = new Customer(0,name,surname,email,phone,address);
            return c;
        }
        return null;
    }

    private void btnCustomerAddClick(ActionEvent e) {
        Customer c = fncDataValid();
        if (c != null ) {
            int status = customerImpl.customerInsert(c);
            if (status >0) {
                System.out.println("Ekleme Başarlı");
                tblCustomer.setModel(customerImpl.customerTablemodel());
                txtName.setText("");
                txtSurname.setText("");
                txtEmail.setText("");
                txtPhone.setText("");
                txtAddress.setText("");

            }else  {
                if (status == -1) {
                    lblError.setText("E-Mail or Phone have already used");
                }else {
                    lblError.setText("Insert Error");
                }
            }
        }

    }

    private void btnDelete(ActionEvent e) {
        if (row !=-1){
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to delete the customer?","Delete Window",JOptionPane.YES_OPTION);//parent component nerede görüneceği this button
            System.out.println(answer); //butonların sırası soldan başlayarak 0 1 buton sırası öyle belirlenir.

            if (answer==0){
                customerImpl.CustomerDelete(selectId);
                tblCustomer.setModel(customerImpl.customerTablemodel()); //tabloyu refresh et
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
            //show confirm anlaşmayı kabul etmek istiyor musun.
        }

    }

    private void btnUpdate(ActionEvent e) {
        String name= txtName.getText();
        String surname= txtSurname.getText();
        String email= txtEmail.getText();
        String phone= txtPhone.getText();
        String address= txtAddress.getText();

        Customer customer= new Customer(selectId,name,surname,email,phone,address);
        //carsModel.add(c);
        if (row!=-1){
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to update the customer?","Update Window",JOptionPane.YES_OPTION);//parent component nerede görüneceği this button

            if (answer==0){
                customerImpl.customerUpdate(customer);
                tblCustomer.setModel(customerImpl.customerTablemodel()); //tabloyu refresh et
//                System.out.println(row+" update");
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
            //show confirm anlaşmayı kabul etmek istiyor musun.
        }
    }

    private void tblCustomerKeyReleased(KeyEvent e) {
        rowValue();
    }

    private void tblCustomerMouseClicked(MouseEvent e) {
        rowValue();
    }







    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblService = new JLabel();
        lblName = new JLabel();
        panel1 = new JPanel();
        label1 = new JLabel();
        txtName = new JTextField();
        txtSurname = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        txtEmail = new JTextField();
        label4 = new JLabel();
        txtPhone = new JTextField();
        label5 = new JLabel();
        lblError = new JLabel();
        txtAddress = new JTextField();
        btnAdd = new JButton();
        btnUpdate = new JButton();
        btnDelete = new JButton();
        scrollPane1 = new JScrollPane();
        tblCustomer = new JTable();

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

        //---- lblService ----
        lblService.setText("Technical Service");
        lblService.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblService.setForeground(new Color(33, 17, 17));
        lblService.setHorizontalAlignment(SwingConstants.CENTER);

        //---- lblName ----
        lblName.setText(" ");
        lblName.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblName.setForeground(new Color(33, 17, 17));

        //======== panel1 ========
        {

            //---- label1 ----
            label1.setText("Name");
            label1.setFont(new Font("Times New Roman", Font.BOLD, 16));

            //---- txtName ----
            txtName.setFont(new Font("Times New Roman", Font.PLAIN, 14));

            //---- txtSurname ----
            txtSurname.setFont(new Font("Times New Roman", Font.PLAIN, 14));

            //---- label2 ----
            label2.setText("Surname");
            label2.setFont(new Font("Times New Roman", Font.BOLD, 16));

            //---- label3 ----
            label3.setText("E-Mail");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 16));

            //---- txtEmail ----
            txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 14));

            //---- label4 ----
            label4.setText("Phone");
            label4.setFont(new Font("Times New Roman", Font.BOLD, 16));

            //---- txtPhone ----
            txtPhone.setFont(new Font("Times New Roman", Font.PLAIN, 14));

            //---- label5 ----
            label5.setText("Address");
            label5.setFont(new Font("Times New Roman", Font.BOLD, 16));

            //---- lblError ----
            lblError.setText(" ");
            lblError.setForeground(Color.red);
            lblError.setFont(new Font("Times New Roman", Font.BOLD, 16));
            lblError.setHorizontalAlignment(SwingConstants.CENTER);

            //---- txtAddress ----
            txtAddress.setFont(new Font("Times New Roman", Font.PLAIN, 14));

            //---- btnAdd ----
            btnAdd.setText("Add");
            btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 16));
            btnAdd.addActionListener(e -> btnCustomerAddClick(e));

            //---- btnUpdate ----
            btnUpdate.setText("Update");
            btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 16));
            btnUpdate.addActionListener(e -> btnUpdate(e));

            //---- btnDelete ----
            btnDelete.setText("Delete");
            btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 16));
            btnDelete.addActionListener(e -> btnDelete(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                                .addGap(96, 96, 96)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                                .addGap(89, 89, 89))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtAddress, GroupLayout.Alignment.LEADING)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(165, 165, 165)
                                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                .addComponent(btnAdd)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnUpdate)))
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDelete)))
                                .addGap(91, 91, 91))))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(label3))
                        .addGap(3, 3, 3)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2)
                            .addComponent(label4))
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label5)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnDelete)
                            .addComponent(btnUpdate))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblError)
                        .addContainerGap())
            );
        }

        //======== scrollPane1 ========
        {

            //---- tblCustomer ----
            tblCustomer.setFont(new Font("Times New Roman", Font.PLAIN, 14));
            tblCustomer.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    tblCustomerKeyReleased(e);
                }
            });
            tblCustomer.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tblCustomerMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(tblCustomer);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
                        .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                            .addComponent(lblService, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane1, GroupLayout.Alignment.LEADING))
                    .addContainerGap(7, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(lblService, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                    .addGap(169, 169, 169))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblService;
    private JLabel lblName;
    private JPanel panel1;
    private JLabel label1;
    private JTextField txtName;
    private JTextField txtSurname;
    private JLabel label2;
    private JLabel label3;
    private JTextField txtEmail;
    private JLabel label4;
    private JTextField txtPhone;
    private JLabel label5;
    private JLabel lblError;
    private JTextField txtAddress;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JScrollPane scrollPane1;
    private JTable tblCustomer;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

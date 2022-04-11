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
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class CustomerAdd extends Base {
    CustomerImpl customerImpl=new CustomerImpl();

    int row=-1;
    int selectedId=0;
    public CustomerAdd() {
        initComponents();
        tblCustomer.setModel(customerImpl.customerModel());
        lblName.setText("Sayın "+UserImpl.name);
    }



    private void thisWindowClosing(WindowEvent e) {
        new Dashbord().setVisible(true);
    }

    private void btnCustomerAddCllicked(ActionEvent e) {
        Customer customer=fncDataValid();
        //bellekte ayrılmış nesnenin farklı 2 referansı oluşur.1 nesnenin 2pointerı var.

        if(customer!=null){
            int status=customerImpl.customerInsert(customer);
            if(status>0){
                System.out.println("Ekleme Başarılı");
                tblCustomer.setModel(customerImpl.customerModel()); //tabloyu refresh eder
                textClear();
            }else{
                if(status==-1){
                    lblError.setText("Email or Phone Number Already Exists.");
                }else {
                    lblError.setText("Insert Error");
                }
            }
        }
    }
    private void btnCustomerUpdateClicked(ActionEvent e) {
        Customer customer=fncDataValid();
        if(row!=-1){
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to update?","Update Window",JOptionPane.YES_OPTION);
            if(answer==0){
                customerImpl.customerUpdate(customer);
                tblCustomer.setModel(customerImpl.customerModel());
                textClear();
                row=-1;
            }
        }else{
            JOptionPane.showMessageDialog(this,"Please Choose!"); //this kendini burada ortala
        }
    }

    private void btnCustomerDeleteClicked(ActionEvent e) {
        if(row!=-1){
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to delete?","Delete Window",JOptionPane.YES_OPTION);
            if(answer==0){
                customerImpl.customerDelete(selectedId);
                tblCustomer.setModel(customerImpl.customerModel());
                textClear();
                row=-1;
            }
        }else{
            JOptionPane.showMessageDialog(this,"Please Choose!"); //this kendini burada ortala
        }
    }

    private void tblCustomerMouseClicked(MouseEvent e) {
       rowValue();
    }

    private void tblCustomerKeyReleased(KeyEvent e) {
        rowValue();
    }

    public void rowValue(){
        int column=0;
        row=tblCustomer.getSelectedRow();
        selectedId= (int) tblCustomer.getValueAt(row,column);//seçili id'yi yakalama

        String cid= String.valueOf(tblCustomer.getValueAt(row,0));  //cast ettik obje olduğu için.
        String name= String.valueOf(tblCustomer.getValueAt(row,1));
        String surname= String.valueOf(tblCustomer.getValueAt(row,2));
        String email= String.valueOf(tblCustomer.getValueAt(row,3));
        String phone= String.valueOf(tblCustomer.getValueAt(row,4));
        String address= String.valueOf(tblCustomer.getValueAt(row,5));
        System.out.println("Selected index: "+selectedId);

        txtName.setText(name);
        txtSurname.setText(surname);
        txtEmail.setText(email);
        txtPhone.setText(phone);
        txtAddress.setText(address);
    }

    public Customer fncDataValid(){
        String name=txtName.getText().trim();
        String surname=txtSurname.getText().trim();
        String email=txtEmail.getText().trim().toLowerCase(Locale.ROOT);
        String phone=txtPhone.getText().trim();
        String address=txtAddress.getText().trim();

        if(name.equals("")){
            lblError.setText("Please Enter Name");
            txtName.requestFocus();;
        }else if(surname.equals("")){
            lblError.setText("Please Enter Surname");
            txtSurname.requestFocus();
        }else if(email.equals("")){
            lblError.setText("Please Enter Email");
            txtEmail.requestFocus();
        }else if(phone.equals("")){
            lblError.setText("Please Enter Phone");
            txtPhone.requestFocus();
        }else if(address.equals("")){
            lblError.setText("Please Enter Address");
            txtAddress.requestFocus();
        }else {
            lblError.setText("");
            Customer customer=new Customer(0,name,surname,email,phone,address);

            return customer;
        }
        return null;
    }

    public void textClear(){
        txtName.setText("");
        txtSurname.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
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
            btnCustomerAdd.addActionListener(e -> btnCustomerAddCllicked(e));

            //---- btnCustomerUpdate ----
            btnCustomerUpdate.setBackground(SystemColor.activeCaption);
            btnCustomerUpdate.setForeground(Color.white);
            btnCustomerUpdate.setIcon(new ImageIcon(getClass().getResource("/btnUpdateIcon.png")));
            btnCustomerUpdate.setBorder(null);
            btnCustomerUpdate.setToolTipText("UPDATE");
            btnCustomerUpdate.addActionListener(e -> btnCustomerUpdateClicked(e));

            //---- btnCustomerDelete ----
            btnCustomerDelete.setBackground(SystemColor.activeCaption);
            btnCustomerDelete.setForeground(Color.white);
            btnCustomerDelete.setIcon(new ImageIcon(getClass().getResource("/btnDeleteIcon.png")));
            btnCustomerDelete.setBorder(null);
            btnCustomerDelete.setToolTipText("DELETE");
            btnCustomerDelete.addActionListener(e -> btnCustomerDeleteClicked(e));

            //======== scrollPane2 ========
            {

                //---- tblCustomer ----
                tblCustomer.setFont(new Font("Segoe UI", Font.BOLD, 14));
                tblCustomer.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblCustomerMouseClicked(e);
                    }
                });
                tblCustomer.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        tblCustomerKeyReleased(e);
                    }
                });
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
                                .addGap(30, 30, 30)
                                .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap(323, Short.MAX_VALUE)
                                .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
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
                                .addGap(12, 12, 12)
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

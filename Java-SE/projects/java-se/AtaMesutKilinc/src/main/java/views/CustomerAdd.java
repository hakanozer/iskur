/*
 * Created by JFormDesigner on Thu Apr 07 15:42:13 TRT 2022
 */

package views;

import models.CustomerImpl;
import models.UserImpl;
import props.Customer;

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
    int value;
    public CustomerAdd() {
        initComponents();
        tblCustomer.setModel(customerImpl.customerModel()); //modeli tablonun içine gönderdik
        lblName.setText("Sn. "+ UserImpl.name);
    }

    private void thisWindowClosing(WindowEvent e) { //geri dönme eventi
        new Dashboard().setVisible(true);
    }

    public void rowValue(){
        int column = 0; //1. kolondakini al.
        row = tblCustomer.getSelectedRow(); //seçili olan row u getir.  //dizi elemanı gibi 0 dan başlar row
        value = (int) tblCustomer.getModel().getValueAt(row, column);
        String cid= String.valueOf(tblCustomer.getValueAt(row,0));  //cast ettik obje olduğu için.
        String name= String.valueOf(tblCustomer.getValueAt(row,1));
        String surname= String.valueOf(tblCustomer.getValueAt(row,2));
        String email= String.valueOf(tblCustomer.getValueAt(row,3));
        String phone= String.valueOf(tblCustomer.getValueAt(row,4));
        String address= String.valueOf(tblCustomer.getValueAt(row,5));
        System.out.println("val "+ value);

        txtName.setText(name);
        txtSurname.setText(surname);
        txtEmail.setText(email);
        txtPhone.setText(phone);
        txtAddress.setText(address);


    }
    private Customer fncDataValid(){ //veriler.
        String name= txtName.getText().toLowerCase(Locale.ROOT).trim();
        String surname= txtSurname.getText().toLowerCase(Locale.ROOT).trim();
        String email= txtEmail.getText().toLowerCase(Locale.ROOT).trim();
        String phone= txtPhone.getText().toLowerCase(Locale.ROOT).trim();
        String address= txtAddress.getText().toLowerCase(Locale.ROOT).trim();

        if (name.equals("")){
            lblCustomerError.setText("Name is Empty!!!");
            txtName.requestFocus();
        }else if (surname.equals("")){
            lblCustomerError.setText("Surname is Empty!!!");
            txtSurname.requestFocus();
        }else if (email.equals("")){
            lblCustomerError.setText("Email is Empty!!!");
            txtEmail.requestFocus();
        }else if(!Utils.Util.isValidEmailAddress(email)){ //fprmatı başkaysa
            lblCustomerError.setText("Email Validation Error!!!");
            txtEmail.requestFocus();
        }else if (phone.equals("")){ //boşşa sıfırsa
            lblCustomerError.setText("Phone is Empty!!!");
            txtPhone.requestFocus();//imleç otomatik olarak passwworde gelicek
        }
        else if (address.equals("")){ //boşşa sıfırsa
            lblCustomerError.setText("Adress is Empty!!!");
            txtAddress.requestFocus();//imleç otomatik olarak passwworde gelicek
        }else {
            lblCustomerError.setText("");
            Customer customer= new Customer(0,name,surname,email,phone,address);

            return customer; //iş yolunda ise customeri dön
        }
        return null; //eğer customer boşssa null dönecek

    }

    private void btnAddClick(ActionEvent e) {
        Customer customer =fncDataValid(); //fncdatavaliddeki dolu cus ı aldık.. fncdata yeni nesne üretir. üretilmiş nesneyi referansın içine atıyor. burda yeni referans almadık atama işlemi.
        //bellekte ayrılmış nesnenin farklı 2 referansı oluşur.1 nesnenin 2pointerı var.

        if(customer !=null){
            int status=customerImpl.customerInsert(customer);
            if (status>0){
                System.out.println("Ekleme Başarılı");
                tblCustomer.setModel(customerImpl.customerModel()); //tabloyu refresh et
                txtName.setText("");
                txtSurname.setText("");
                txtEmail.setText("");
                txtPhone.setText("");
                txtAddress.setText("");
            }else {
                //buraya düştüyse hata var. -1 ise email unique değil
                if (status==-1){
                    lblCustomerError.setText("Email or Phone number already exists.");
                }else{
                    lblCustomerError.setText("Insert Error.");
                }

            }

        }


    }

    private void btnUpdateClick(ActionEvent e) {

        String name= txtName.getText().toLowerCase(Locale.ROOT).trim();
        String surname= txtSurname.getText().toLowerCase(Locale.ROOT).trim();
        String email= txtEmail.getText().toLowerCase(Locale.ROOT).trim();
        String phone= txtPhone.getText().toLowerCase(Locale.ROOT).trim();
        String address= txtAddress.getText().toLowerCase(Locale.ROOT).trim();

        Customer customer= new Customer(value,name,surname,email,phone,address);
        //carsModel.add(c);
        if (row!=-1){
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to update the customer?","Update Window",JOptionPane.YES_OPTION);//parent component nerede görüneceği this button

            if (answer==0){
               customerImpl.customerUpdate(customer);
                tblCustomer.setModel(customerImpl.customerModel()); //tabloyu refresh et
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

    private void btnDeleteClick(ActionEvent e) {
        if (row !=-1){
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to delete the customer?","Delete Window",JOptionPane.YES_OPTION);//parent component nerede görüneceği this button
            System.out.println(answer); //butonların sırası soldan başlayarak 0 1 buton sırası öyle belirlenir.

            if (answer==0){
                customerImpl.customerDelete(value);
//                System.out.println("delete row "+value);
                tblCustomer.setModel(customerImpl.customerModel()); //tabloyu refresh et
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

    private void tblCustomerMouseClicked(MouseEvent e) {
        rowValue();
    }

    private void tblCustomerKeyReleased(KeyEvent e) {
        rowValue();
    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        lblName = new JLabel();
        panel1 = new JPanel();
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
        btnUpdate = new JButton();
        btnDelete = new JButton();
        lblCustomerError = new JLabel();
        scrollPane2 = new JScrollPane();
        tblCustomer = new JTable();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Customer Add");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Tecnical Service");
        label1.setFont(new Font("Arial", Font.PLAIN, 12));

        //---- lblName ----
        lblName.setText(" ");
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);

        //======== panel1 ========
        {

            //---- label2 ----
            label2.setText("Name");

            //---- label3 ----
            label3.setText("Surname");

            //---- label4 ----
            label4.setText("Email");

            //---- label5 ----
            label5.setText("Phone");

            //---- label6 ----
            label6.setText("Address");

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(txtAddress);
            }

            //---- btnAdd ----
            btnAdd.setIcon(new ImageIcon(getClass().getResource("/addBtnn.png")));
            btnAdd.setToolTipText("Add");
            btnAdd.setFocusable(false);
            btnAdd.addActionListener(e -> btnAddClick(e));

            //---- btnUpdate ----
            btnUpdate.setIcon(new ImageIcon(getClass().getResource("/updateBtn.png")));
            btnUpdate.setToolTipText("Update");
            btnUpdate.setFocusable(false);
            btnUpdate.addActionListener(e -> btnUpdateClick(e));

            //---- btnDelete ----
            btnDelete.setIcon(new ImageIcon(getClass().getResource("/deleteBtn.png")));
            btnDelete.setToolTipText("Delete");
            btnDelete.setFocusable(false);
            btnDelete.addActionListener(e -> btnDeleteClick(e));

            //---- lblCustomerError ----
            lblCustomerError.setFont(new Font("Arial", Font.PLAIN, 14));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSurname))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label6, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblCustomerError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(scrollPane1)))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName))
                        .addGap(19, 19, 19)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(label6, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete)
                            .addComponent(btnAdd)
                            .addComponent(btnUpdate)
                            .addComponent(lblCustomerError, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
            );
        }

        //======== scrollPane2 ========
        {

            //---- tblCustomer ----
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

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 512, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE))
                        .addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(scrollPane2))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel lblName;
    private JPanel panel1;
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
    private JButton btnUpdate;
    private JButton btnDelete;
    private JLabel lblCustomerError;
    private JScrollPane scrollPane2;
    private JTable tblCustomer;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

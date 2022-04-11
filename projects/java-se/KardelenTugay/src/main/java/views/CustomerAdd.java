/*
 * Created by JFormDesigner on Thu Apr 07 15:46:52 TRT 2022
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
    public CustomerAdd() {

        initComponents();
        tblCustomer.setModel(cus.model());
        lblName.setText("Merhaba, " + UserImpl.name);
    }
    private Customer fncDataValid(){
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
        return null; //olumsuz halinde

    }

    private void thisWindowClosing(WindowEvent e) {
        new Dashboard().setVisible(true);
    }

    private void saveCustomerButtonClick(ActionEvent e) {
        Customer c = fncDataValid();
        if(c!=null){
            int status = cus.customerInsert(c);
            if (status>0){
                System.out.println("Ekleme basarili");
                txtName.setText("");
                txtSurname.setText("");
                txtEmail.setText("");
                txtPhone.setText("");
                txtAddress.setText("");
                tblCustomer.setModel(cus.model() );
            }
            else {
                if (status == -1){
                    lblError.setText("E-mail or Phone have already used");
                }
                else {
                    lblError.setText("Insert Error");
                }
            }
        }
    }
    private void tblCustomerMouseClicked(MouseEvent e) {
        rowVal();
    }

    private void tblCustomerKeyReleased(KeyEvent e) {
        rowVal();
    }

    int row = -1; //tblCars.getSelectedRow();
    int cid = 0;
    int column = 0;
    void rowVal(){
        row = tblCustomer.getSelectedRow();
        String name = (String) tblCustomer.getValueAt(row, 1);
        String surname = (String) tblCustomer.getValueAt(row, 2);
        String email = (String) tblCustomer.getValueAt(row, 3);
        String phone = (String) tblCustomer.getValueAt(row, 4);
        String address = (String) tblCustomer.getValueAt(row, 5);

        txtName.setText(name);
        txtSurname.setText(surname);
        txtEmail.setText(email);
        txtPhone.setText(phone);
        txtAddress.setText(address);




    }

    private void btnDeleteClick(ActionEvent e) {
        if(row != -1 ) {
            row = tblCustomer.getSelectedRow();
            cid = Integer.valueOf(tblCustomer.getModel().getValueAt(row,column).toString());
            int answer = JOptionPane.showConfirmDialog(this, "Silmek istediginizden emin miisniz?", "Silme islemi", JOptionPane.YES_NO_OPTION);
            if(answer==0){
                cus.customerDelete(cid);
                tblCustomer.setModel(cus.model());
                row = -1;
            }
        } else{
            JOptionPane.showMessageDialog(this, "Lutfen secim yapiniz.");
        }
    }

    private void btnCustomerUpdateClick(ActionEvent e) {
        Customer c = fncDataValid();
        if(row != -1 ) {
            row = tblCustomer.getSelectedRow();
            cid = Integer.valueOf(tblCustomer.getModel().getValueAt(row,column).toString());
            int answer = JOptionPane.showConfirmDialog(this, "Guncellemek istediginizden emin misniz?", "Guncelleme islemi", JOptionPane.YES_NO_OPTION);
            if (answer == 0) {
                cus.customerUpdate(c,cid);
                tblCustomer.setModel(cus.model());
                row = -1;
            }
        }else{
                JOptionPane.showMessageDialog(this, "Lutfen secim yapiniz.");
            }
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
        btnCustomerSave = new JButton();
        btnCustomerUpdate = new JButton();
        btnDelete = new JButton();
        lblError = new JLabel();
        scrollPane2 = new JScrollPane();
        tblCustomer = new JTable();

        //======== this ========
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Technical Service");
        label1.setForeground(Color.black);
        label1.setFont(new Font("Arial", Font.BOLD, 12));

        //---- lblName ----
        lblName.setText("text");
        lblName.setForeground(Color.black);
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);

        //======== panel1 ========
        {
            panel1.setPreferredSize(new Dimension(683, 230));

            //---- label2 ----
            label2.setText("Name:");

            //---- label3 ----
            label3.setText("Surname:");

            //---- label4 ----
            label4.setText("E-mail:");

            //---- label5 ----
            label5.setText("Phone:");

            //---- label6 ----
            label6.setText("Address");

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(txtAddress);
            }

            //---- btnCustomerSave ----
            btnCustomerSave.setIcon(new ImageIcon(getClass().getResource("/addButtonIcon.png")));
            btnCustomerSave.setToolTipText("Add");
            btnCustomerSave.addActionListener(e -> saveCustomerButtonClick(e));

            //---- btnCustomerUpdate ----
            btnCustomerUpdate.setIcon(new ImageIcon(getClass().getResource("/updateButtonIcon.png")));
            btnCustomerUpdate.setToolTipText("Update");
            btnCustomerUpdate.addActionListener(e -> btnCustomerUpdateClick(e));

            //---- btnDelete ----
            btnDelete.setIcon(new ImageIcon(getClass().getResource("/deleteIconButton.png")));
            btnDelete.setToolTipText("Delete");
            btnDelete.addActionListener(e -> btnDeleteClick(e));

            //---- lblError ----
            lblError.setForeground(Color.red);
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
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE))))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(scrollPane1))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(btnCustomerSave, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnCustomerUpdate, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                        .addGap(22, 22, 22)))
                                .addGap(12, 12, 12))))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label3)
                                    .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label2)
                                    .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label4))
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label5)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addComponent(label6, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(lblError, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCustomerSave)
                                .addComponent(btnCustomerUpdate)
                                .addComponent(btnDelete)))
                        .addGap(9, 9, 9))
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
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                    .addGap(359, 359, 359)
                    .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                        .addComponent(scrollPane2))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(label1))
                        .addComponent(lblName))
                    .addGap(18, 18, 18)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
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
    private JButton btnCustomerSave;
    private JButton btnCustomerUpdate;
    private JButton btnDelete;
    private JLabel lblError;
    private JScrollPane scrollPane2;
    private JTable tblCustomer;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

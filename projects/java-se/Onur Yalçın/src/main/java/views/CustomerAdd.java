/*
 * Created by JFormDesigner on Thu Apr 07 15:41:31 TRT 2022
 */

package views;

import model.CustomerImpl;
import model.CustomerModel;
import model.UserImpl;
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
    CustomerModel customerModel=new CustomerModel();
    CustomerImpl customerImpl=new CustomerImpl();
    public CustomerAdd() {
        initComponents();
        tblCustomer.setModel(customerModel.model());
        lblName.setText(UserImpl.name);
    }


    private Customer fncDatavalidate(){
        String name=txtName.getText().toLowerCase(Locale.ROOT).trim();
        String surname=txtSurname.getText().toLowerCase(Locale.ROOT).trim();
        String email=txtEmail.getText().toLowerCase(Locale.ROOT).trim();
        String phone=txtPhone.getText().toLowerCase(Locale.ROOT).trim();
        String address=txtAddress.getText().toLowerCase(Locale.ROOT).trim();

        if(name.equals("")){
            txtName.requestFocus();
            lblError.setText("Name Empty");
        }
        else if(surname.equals("")){
            txtSurname.requestFocus();
            lblError.setText("Surname Empty");
        }
        else if(email.equals("")){
            txtEmail.requestFocus();
            lblError.setText("Email Empty");
        }
        else if(!Util.isValidEmailAddress(email)){
            lblError.setText("E-mail invalid");
            txtEmail.requestFocus();
        }
        else if(phone.equals("")){
            txtPhone.requestFocus();
            lblError.setText("Phone Empty");
        }
        else if(address.equals("")){
            txtAddress.requestFocus();
            lblError.setText("Address Empty");
        }
        else{
            lblError.setText("");
            Customer customer=new Customer(0,name,surname,email,phone,address);
            return customer;
        }
     return null;
    }

    private void fncClear(){
        txtName.setText("");
        txtSurname.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        //lblError.setText("");
    }
    public String rowValue(int row,int column){

        String selectedCellValue = String.valueOf(tblCustomer.getValueAt(row , column)) ;
        txtName.setText((String) tblCustomer.getValueAt(row , 1));
        txtSurname.setText((String) tblCustomer.getValueAt(row , 2));
        txtEmail.setText((String) tblCustomer.getValueAt(row , 3));
        txtPhone.setText( String.valueOf(tblCustomer.getValueAt(row , 4)));
        txtAddress.setText( String.valueOf(tblCustomer.getValueAt(row , 5)));

        return selectedCellValue;
    }

    private void thisWindowClosing(WindowEvent e) {
        // TODO add your code here
        new Dashboard().setVisible(true);
    }

    private void btnAddClick(ActionEvent e) {
        // TODO add your code here
        //fncDatavalidate cagırılmazsa bile bir nesne zaten oluşturulmazsa
        //Customer customer=new Customer(0,name,surname,email,phone,address); bellekte yer kaplar
        Customer c=fncDatavalidate();//bellekte 2 referans(nesne) ve 1 addres var
        if(c !=null){
            int status = customerImpl.customerInsert(c);
            if(status > 0){
                String name=txtName.getText();
                String surname=txtSurname.getText();
                String email=txtEmail.getText();
                String phone=txtPhone.getText();
                String address=txtAddress.getText();
                customerModel.addItem(new Customer(0,name,surname,email,phone,address));
                tblCustomer.setModel(customerModel.model());
                lblError.setText("Added customer succes !");
                fncClear();
            }
            else {
                if(status == -1)
                    lblError.setText("Another customer with the same email information!");
                else if(status == -2)
                    lblError.setText("Another customer with the same phone information!");
                else {
                    lblError.setText("Insert Error !");
                }
            }
            pack();
        }
    }

    private void btnCustomerUpdateClick(ActionEvent e) {
        // TODO add your code here
        lblError.setText("Updated proceses succesful");
        fncClear();
    }

    private void btnCustomerDeleteClick(ActionEvent e) {
        if (tblCustomer.getSelectedRow() != -1) {
            System.out.println(tblCustomer.getValueAt(tblCustomer.getSelectedRow() , 0));
            int input = JOptionPane.showConfirmDialog(this, "Silmek isteğinizden emin misiniz?","silme işlemi",JOptionPane.YES_NO_OPTION);
            // 0=yes, 1=no, 2=cancel
            if(input==0){
                //customerModel.deleteItem(tblCustomer.getSelectedRow());
                customerModel.removeRow(tblCustomer.getSelectedRow());
                customerModel.deleteItem(Integer.parseInt(String.valueOf(tblCustomer.getValueAt(tblCustomer.getSelectedRow() , 0))));
                customerImpl.customerDelete(Integer.parseInt(String.valueOf(tblCustomer.getValueAt(tblCustomer.getSelectedRow() , 0))));
            }
            System.out.println("input :"+input);
        }
        else
            JOptionPane.showMessageDialog(this,"Lütfen seçim yapınız");
    }

    private void tblCustomerMouseClicked(MouseEvent e) {
        // TODO add your code here
        System.out.println(rowValue(tblCustomer.getSelectedRow(),tblCustomer.getSelectedColumn()));
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
        label4 = new JLabel();
        txtEmail = new JTextField();
        label5 = new JLabel();
        txtPhone = new JTextField();
        label6 = new JLabel();
        scrollPane1 = new JScrollPane();
        txtAddress = new JTextArea();
        button1 = new JButton();
        scrollPane2 = new JScrollPane();
        tblCustomer = new JTable();
        button2 = new JButton();
        button3 = new JButton();
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
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);

        //---- label1 ----
        label1.setText("Technical Service");

        //======== panel1 ========
        {

            //---- label2 ----
            label2.setText("Name :");

            //---- label3 ----
            label3.setText("Surname :");

            //---- label4 ----
            label4.setText("Email :");

            //---- label5 ----
            label5.setText("Phone :");

            //---- label6 ----
            label6.setText("Address :");

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(txtAddress);
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(label6, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                            .addComponent(label4, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                            .addComponent(label2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(txtName, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(label5, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                                    .addComponent(label3, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSurname, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                    .addComponent(txtPhone, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)))
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName)
                            .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }

        //---- button1 ----
        button1.setText("Add");
        button1.setIcon(new ImageIcon(getClass().getResource("/addCustomer.png")));
        button1.setFocusable(false);
        button1.addActionListener(e -> btnAddClick(e));

        //======== scrollPane2 ========
        {

            //---- tblCustomer ----
            tblCustomer.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tblCustomerMouseClicked(e);
                }
            });
            scrollPane2.setViewportView(tblCustomer);
        }

        //---- button2 ----
        button2.setText("Delete");
        button2.setIcon(new ImageIcon(getClass().getResource("/deleteCustomer.png")));
        button2.setFocusable(false);
        button2.addActionListener(e -> btnCustomerDeleteClick(e));

        //---- button3 ----
        button3.setText("Update");
        button3.setIcon(new ImageIcon(getClass().getResource("/updateCustomer.png")));
        button3.setFocusable(false);
        button3.addActionListener(e -> btnCustomerUpdateClick(e));

        //---- lblError ----
        lblError.setForeground(new Color(255, 51, 51));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(77, Short.MAX_VALUE)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(button3, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(button2, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                                .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12)
                                    .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 8, Short.MAX_VALUE)))
                            .addContainerGap())))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label1)
                        .addComponent(lblName))
                    .addGap(18, 18, 18)
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button1)
                        .addComponent(button3)
                        .addComponent(button2)
                        .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
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
    private JLabel label4;
    private JTextField txtEmail;
    private JLabel label5;
    private JTextField txtPhone;
    private JLabel label6;
    private JScrollPane scrollPane1;
    private JTextArea txtAddress;
    private JButton button1;
    private JScrollPane scrollPane2;
    private JTable tblCustomer;
    private JButton button2;
    private JButton button3;
    private JLabel lblError;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

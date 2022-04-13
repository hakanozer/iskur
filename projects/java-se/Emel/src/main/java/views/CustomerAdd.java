/*
 * Created by JFormDesigner on Thu Apr 07 15:42:11 TRT 2022
 */

package views;

import javax.swing.border.*;
import models.CustomerImpl;
import models.ServiceImpl;
import models.UserImpl;
import props.Customer;
import props.Service;
import utils.Util;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.util.List;


/**
 * @author Emel Cesur
 */
public class CustomerAdd extends Base {
    CustomerImpl cus=new CustomerImpl();
    int row =-1;
    int selectedId=0;
    int status=0;
    boolean textboxIsChanged=false;
    ServiceImpl serviceImpl=new ServiceImpl();
    List<Service> lstService=serviceImpl.serviceList(-1);

    public CustomerAdd() {
        initComponents();
        lblname.setText("Sn. "+ UserImpl.name);
        table1.setModel(cus.tablemodelOlustur());
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void thisWindowClosing(WindowEvent e) {

        new Dashboard().setVisible(true);
    }

    private void btnAddClick(ActionEvent e) {

       if(selectedId==0) {
           Customer c = fncDtaValidate();


           if (c != null) {
                   int status = cus.customerInsert(c);
                   if (status > 0) {
                   textboxClear();
                   table1.setModel(cus.tablemodelOlustur());
               } else {
                   if (status == -1) {
                       lblError.setText("Email or Phone have already used");
                   } else {
                       lblError.setText("Insert Error");
                   }
               }
           }
           //b=false;
       }else {table1.clearSelection();
           selectedId=0;
           textboxClear();}
    }


    private void btnUpdateClick(ActionEvent e) {
        row = table1.getSelectedRow();
        if (row == -1) {
            lblError.setText("Please select the customer you want to update from the table.");
        } else {
            lblError.setText("");
            if(textboxIsChanged==false){
                int answer=JOptionPane.showConfirmDialog(this,"You does not change anything !!!!. Are you sure that you continue?","Update Process",JOptionPane.YES_NO_OPTION);
                if(answer==0)
                {
                    Customer customer1 = fncDtaValidate();
                    if(customer1!=null) {
                        status = cus.customerUpdate(customer1);
                    }
                    if (status > 0) {

                        textboxClear();
                        table1.setModel(cus.tablemodelOlustur());

                    }
                }
            }else{
                Customer customer1 = fncDtaValidate();
                if(customer1!=null) {
                    status = cus.customerUpdate(customer1);
                }
                if (status > 0) {
                    textboxClear();
                    table1.setModel(cus.tablemodelOlustur());

                }

            }

        }
    }




    private void btnDeleteClick(ActionEvent e) {
        Boolean isContain=false;
        row = table1.getSelectedRow();
        for(Service item:lstService) {
            if (item.getCid() == selectedId)
            { isContain = true;}
        }

        if (row == -1) {
            lblError.setText("Please select the customer you want to delete from the table.");
        } else if(isContain) {
            JOptionPane.showMessageDialog(this,
                    "You cannot delete the customer. The customer is assigned a service.");

        }else{
            lblError.setText("");
        int answer=JOptionPane.showConfirmDialog(this,
                "Are you sure that you want to customer."
                ,"Delete Process",JOptionPane.YES_NO_OPTION);
              if(answer==0){
                  cus.customerDelete(selectedId);
                table1.setModel(cus.tablemodelOlustur());

            }else{ table1.clearSelection();
                textboxClear();}
        }

    }


    private void txtNameKeyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
           // btnAddClick(null);
            txtSurname.requestFocus();
        }
        textboxIsChanged=true;
    }


    private void txtSurnameKeyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            txtEmail.requestFocus();
        }
        textboxIsChanged=true;
    }


    private void txtEmailKeyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            //btnAddClick(null);
        }
        textboxIsChanged=true;
    }


    private void txtPhoneKeyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            //btnAddClick(null);
            txtAddress.requestFocus();
        }
        textboxIsChanged=true;
    }


    private void txtAddressKeyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            btnAddClick(null);
        }
        textboxIsChanged=true;
    }


    private void table1MouseReleased(MouseEvent e) {
        int row1=table1.getSelectedRow();
        if(row1==row){
            table1.clearSelection();
            textboxClear();
        }
        selectedId = findidSelectedrow();
        Customer customer = cus.customerSingle(selectedId);
        txtName.setText(customer.getName());
        txtSurname.setText(customer.getSurname());
        txtEmail.setText(customer.getEmail());
        txtPhone.setText(customer.getPhone());
        txtAddress.setText(customer.getAddress());
        lblError.setText("");
    }


    public Customer fncDtaValidate(){
        String name=txtName.getText().trim();
        String surname=txtSurname.getText().trim();
        String email=txtEmail.getText().trim().toLowerCase();
        String phone=txtPhone.getText().trim();
        String address=txtAddress.getText().trim();

        if(name.equals("")){
            lblError.setText("Name is empty!");
            txtName.requestFocus();
        }
        else if(surname.equals("")){
            lblError.setText("Surname is empty!");
            txtSurname.requestFocus();
        }else if(email.equals("")){
            lblError.setText("E-mail is empty!");
            txtEmail.requestFocus();
        }else if(phone.equals("")){
            lblError.setText("Phone is empty!");
            txtPhone.requestFocus();
        }
        else if(!Util.isValidEmailAddress(email)){

            lblError.setText("E-Mail format Error");
            txtEmail.requestFocus();
        }
        else if(address.equals("")){
            lblError.setText("Surname is empty!");
            txtAddress.requestFocus();
        }
        else{
            lblError.setText("");
            Customer c=new Customer(selectedId,name,surname,email,phone,address);

            return c;
        }

        return null;
    }


    /**
     * Finds the cid of the customer object in the selected row in the table
     * @return selectedId
     */
    int findidSelectedrow(){
        row=table1.getSelectedRow();
        selectedId=(Integer)table1.getValueAt(row,0);
        return selectedId;
    }


    public void textboxClear(){
        txtName.setText("");
        txtSurname.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        lblname = new JLabel();
        panel1 = new JPanel();
        txtName = new JTextField();
        txtSurname = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        txtEmail = new JTextField();
        label5 = new JLabel();
        txtPhone = new JTextField();
        label6 = new JLabel();
        scrollPane1 = new JScrollPane();
        txtAddress = new JTextArea();
        lblError = new JLabel();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label7 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMaximizedBounds(new Rectangle(0, 0, 100, 135));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Customer Managment");
        label1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label1.setForeground(Color.darkGray);

        //---- lblname ----
        lblname.setText("text");
        lblname.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblname.setHorizontalAlignment(SwingConstants.RIGHT);
        lblname.setBorder(null);
        lblname.setForeground(Color.darkGray);

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.gray, Color.gray, Color.lightGray), "Customer Information", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 16), Color.darkGray));
            panel1.setForeground(new Color(204, 204, 204));

            //---- txtName ----
            txtName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            txtName.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txtNameKeyReleased(e);
                }
            });

            //---- txtSurname ----
            txtSurname.setFont(txtSurname.getFont().deriveFont(txtSurname.getFont().getSize() + 2f));
            txtSurname.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txtSurnameKeyReleased(e);
                }
            });

            //---- label2 ----
            label2.setText("Name");
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 2f));

            //---- label3 ----
            label3.setText("Surname");
            label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 2f));

            //---- label4 ----
            label4.setText("E-mail");
            label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 2f));

            //---- txtEmail ----
            txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            txtEmail.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txtEmailKeyReleased(e);
                }
            });

            //---- label5 ----
            label5.setText("Phone");
            label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 2f));

            //---- txtPhone ----
            txtPhone.setFont(txtPhone.getFont().deriveFont(txtPhone.getFont().getSize() + 2f));
            txtPhone.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txtPhoneKeyReleased(e);
                }
            });

            //---- label6 ----
            label6.setText("Addres");
            label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 2f));

            //======== scrollPane1 ========
            {

                //---- txtAddress ----
                txtAddress.setFont(txtAddress.getFont().deriveFont(txtAddress.getFont().getSize() + 2f));
                txtAddress.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        txtAddressKeyReleased(e);
                    }
                });
                scrollPane1.setViewportView(txtAddress);
            }

            //---- lblError ----
            lblError.setHorizontalAlignment(SwingConstants.CENTER);
            lblError.setForeground(Color.red);
            lblError.setFont(new Font("Segoe UI", Font.BOLD, 16));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                                    .addComponent(txtName))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label5, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSurname, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                                    .addComponent(txtPhone)))
                            .addComponent(scrollPane1))
                        .addGap(12, 12, 12))
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap(191, Short.MAX_VALUE)
                        .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 487, GroupLayout.PREFERRED_SIZE)
                        .addGap(147, 147, 147))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                            .addComponent(label3, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label5, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
                        .addGap(23, 23, 23)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
            );
        }

        //======== scrollPane2 ========
        {

            //---- table1 ----
            table1.setSelectionBackground(SystemColor.inactiveCaption);
            table1.setSelectionForeground(Color.black);
            table1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    table1MouseReleased(e);
                }
            });
            scrollPane2.setViewportView(table1);
        }

        //---- button1 ----
        button1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button1.setIcon(new ImageIcon(getClass().getResource("/3855641_add_icon.png")));
        button1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.gray, Color.gray, Color.lightGray));
        button1.setBackground(Color.lightGray);
        button1.setText("Add");
        button1.setForeground(Color.white);
        button1.addActionListener(e -> btnAddClick(e));

        //---- button2 ----
        button2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button2.setIcon(new ImageIcon(getClass().getResource("/uptadeiconcustomer.png")));
        button2.setBackground(Color.lightGray);
        button2.setText("Update");
        button2.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.gray, Color.gray, Color.lightGray));
        button2.setForeground(Color.white);
        button2.addActionListener(e -> btnUpdateClick(e));

        //---- button3 ----
        button3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button3.setIcon(new ImageIcon(getClass().getResource("/3855611_bin_garbage_trash_icon.png")));
        button3.setBackground(Color.lightGray);
        button3.setText("Delete");
        button3.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.gray, Color.gray, Color.lightGray));
        button3.setForeground(Color.white);
        button3.addActionListener(e -> btnDeleteClick(e));

        //---- label7 ----
        label7.setText("text");
        label7.setIcon(new ImageIcon(getClass().getResource("/Customeradd.png")));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
                            .addGap(143, 143, 143)
                            .addComponent(lblname, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
                        .addComponent(panel1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
                            .addGap(135, 135, 135)
                            .addComponent(button3, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE))
                    .addGap(25, 25, 25))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblname))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button3, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel lblname;
    private JPanel panel1;
    private JTextField txtName;
    private JTextField txtSurname;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField txtEmail;
    private JLabel label5;
    private JTextField txtPhone;
    private JLabel label6;
    private JScrollPane scrollPane1;
    private JTextArea txtAddress;
    private JLabel lblError;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

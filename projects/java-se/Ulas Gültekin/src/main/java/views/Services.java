/*
 * Created by JFormDesigner on Sun Apr 10 15:26:31 TRT 2022
 */

package views;

import java.awt.event.*;
import java.beans.*;

import models.CustomerImpl;
import models.ServiceImpl;
import models.ServiceTableImpl;
import models.UserImpl;
import props.Customer;
import props.Service;
import props.User;
import utils.DB;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Services extends Base {

    ServiceTableImpl serviceTable=new ServiceTableImpl();
    ServiceImpl serviceImpl=new ServiceImpl();
    Customer customer=new Customer();
    int row=-1;
    int value;

    public Services() {
        initComponents();


        lblName.setText("Sayýn "+ UserImpl.name+" "+UserImpl.surname);
        tblCustomer.setModel(serviceImpl.servicesCustomerTable(null));
        tblService.setModel(serviceTable.servicesTable(null));

    }
    public void rowValue(){
        int column = 0; //1. kolondakini al.
        row = tblCustomer.getSelectedRow(); //seçili olan row u getir.  //dizi elemaný gibi 0 dan baþlar row
        value = (int) tblCustomer.getModel().getValueAt(row, column);
        String sid= String.valueOf(tblCustomer.getValueAt(row,0));  //cast ettik obje olduðu için.
        String cid= String.valueOf(tblCustomer.getValueAt(row,0));  //cast ettik obje olduðu için.
        String title= String.valueOf(tblCustomer.getValueAt(row,1));
        String info= String.valueOf(tblCustomer.getValueAt(row,2));
        String days= String.valueOf(tblCustomer.getValueAt(row,3));
        String date= String.valueOf(tblCustomer.getValueAt(row,4));
        String price= String.valueOf(tblCustomer.getValueAt(row,5));
        String status= String.valueOf(tblCustomer.getValueAt(row,6));
        System.out.println("val "+ value);

        txtTitle.setText(title);
        txtDetail.setText(info);
        txtDays.setText(days);
        txtDate.setText(date);
        txtPrice.setText(price);


    }
    private Service fncDataValid(){


            Service ser=new Service();
        int sid=ser.getSid();
        String title=txtTitle.getText().trim();
        String info=txtDetail.getText().trim();
        String days=txtDays.getText().trim();
        String date=txtDate.getText().trim();
        int price=Integer.parseInt(txtPrice.getText().trim());
        int status=0;
        int rowIndex = tblCustomer.getSelectedRow();
        int colIndex = 0;
        int cid= (int) tblCustomer.getValueAt(rowIndex,colIndex);
        //int cid=tblCustomer.;
       // int a=Integer.parseInt(cid);
       // System.out.println(cid);

        Customer cs=new Customer();

        Service service=new Service(sid,cid,title,info,days,date,price,status);
        return service;

    }

    private void txtSearchKeyReleased(KeyEvent e) {
        String txtSearch=txtSearchCustomer.getText().trim();
        tblCustomer.setModel(serviceImpl.servicesCustomerTable(txtSearch));
    }

    private void thisWindowClosing(WindowEvent e) {
        new Dashboard().setVisible(true);
        dispose();
    }

    private void btnServiceAdd(ActionEvent e) {
        Service service=fncDataValid();
        int status=serviceImpl.serviceInsert(service);
        tblService.setModel(serviceTable.servicesTable(null));

    }

    private void btnServiceUpdate(ActionEvent e) {
        row=tblCustomer.getSelectedRow();
        String title=txtTitle.getText();
        String info=txtDetail.getText();
        String days=txtDays.getText();
        String date=txtDate.getText();


        int price=Integer.parseInt(txtPrice.getText());
        int rowIndex =row;
        int colIndex = 0;
        int cid= (int) tblCustomer.getValueAt(rowIndex,colIndex);
        Service service=new Service(value,cid,title,info,days,date,price,value);

        if (row!=-1){
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to update the customer?","Update Window",JOptionPane.YES_OPTION);//parent component nerede görüneceði this button

            if (answer==0){
                serviceImpl.serviceUpdate(service);
                tblCustomer.setModel(serviceImpl.servicesCustomerTable(null)); //tabloyu refresh et
//                System.out.println(row+" update");
                txtTitle.setText("");
                txtDetail.setText("");
                txtDays.setText("");
                txtDate.setText("");
                txtPrice.setText("");

                row=-1;

            }
        }
        else{
            JOptionPane.showMessageDialog(this,"Please choose."); //this kendini burada ortala
            //show confirm anlaþmayý kabul etmek istiyor musun.
        }
    }

    private void btnServiceTable(ActionEvent e) {

    }

    private void btnServiceList(ActionEvent e) {

    }

    private void button1(ActionEvent e) {

    }

    private void btnDelete(ActionEvent e) {
        row=tblService.getSelectedRow();
        value = (int) tblService.getModel().getValueAt(row, 0);
        if (row !=-1){
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to delete the customer?","Delete Window",JOptionPane.YES_OPTION);//parent component nerede görüneceði this button
            System.out.println(answer); //butonlarýn sýrasý soldan baþlayarak 0 1 buton sýrasý öyle belirlenir.

            if (answer==0){
                serviceTable.serviceDelete(value);
//                System.out.println("delete row "+value);
                tblService.setModel(serviceTable.servicesTable(null)); //tabloyu refresh et
                txtDetail.setText("");
                txtTitle.setText("");
                txtDays.setText("");
                txtDate.setText("");
                txtPrice.setText("");
                txtStatus.setText("");

                row=-1;
            }

        }

        else{
            JOptionPane.showMessageDialog(this,"Please choose."); //this kendini burada ortala
            //show confirm anlaþmayý kabul etmek istiyor musun.
        }
        tblService.setModel(serviceTable.servicesTable(null));
    }

    private void scrollPane2MouseClicked(MouseEvent e) {
    }

    private void tblServicePropertyChange(PropertyChangeEvent e) {


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label1 = new JLabel();
        txtSearchCustomer = new JTextField();
        scrollPane1 = new JScrollPane();
        tblCustomer = new JTable();
        lblName = new JLabel();
        label8 = new JLabel();
        panel2 = new JPanel();
        label2 = new JLabel();
        txtTitle = new JTextField();
        txtDays = new JTextField();
        label3 = new JLabel();
        txtPrice = new JTextField();
        label4 = new JLabel();
        txtDetail = new JTextField();
        label5 = new JLabel();
        btnServiceAdd = new JButton();
        btnServiceUpdate = new JButton();
        label6 = new JLabel();
        txtDate = new JTextField();
        btnDelete = new JButton();
        scrollPane2 = new JScrollPane();
        tblService = new JTable();
        txtStatus = new JTextField();
        label7 = new JLabel();

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
            label1.setText("Customer Search");

            //---- txtSearchCustomer ----
            txtSearchCustomer.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txtSearchKeyReleased(e);
                }
            });

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(tblCustomer);
            }

            //---- label8 ----
            label8.setText("TECHNIC SERVICE");
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            label8.setFont(new Font("Segoe UI Black", Font.BOLD, 26));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addComponent(label8, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                                .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchCustomer, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 160, Short.MAX_VALUE)))
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label8, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearchCustomer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                        .addGap(28, 28, 28))
            );
        }

        //======== panel2 ========
        {

            //---- label2 ----
            label2.setText("Title");

            //---- label3 ----
            label3.setText("Days");

            //---- label4 ----
            label4.setText("Price");

            //---- label5 ----
            label5.setText("Detail");

            //---- btnServiceAdd ----
            btnServiceAdd.setIcon(new ImageIcon(getClass().getResource("/useradd.png")));
            btnServiceAdd.addActionListener(e -> btnServiceAdd(e));

            //---- btnServiceUpdate ----
            btnServiceUpdate.setIcon(new ImageIcon(getClass().getResource("/updateicon.png")));
            btnServiceUpdate.addActionListener(e -> btnServiceUpdate(e));

            //---- label6 ----
            label6.setText("Date");

            //---- btnDelete ----
            btnDelete.setIcon(new ImageIcon(getClass().getResource("/2303123_bin_delete_garbage_remove_trash_icon.png")));
            btnDelete.addActionListener(e -> btnDelete(e));

            //======== scrollPane2 ========
            {
                scrollPane2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        scrollPane2MouseClicked(e);
                    }
                });

                //---- tblService ----
                tblService.addPropertyChangeListener(e -> tblServicePropertyChange(e));
                scrollPane2.setViewportView(tblService);
            }

            //---- label7 ----
            label7.setText("Status");

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(label5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label4, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addComponent(txtDetail, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(panel2Layout.createParallelGroup()
                                            .addComponent(label3)
                                            .addComponent(label6))))
                                .addGap(25, 25, 25)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtDate, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                    .addComponent(txtDays, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                    .addComponent(txtStatus, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                                .addGap(0, 108, Short.MAX_VALUE))
                            .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addComponent(btnServiceAdd, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnServiceUpdate, GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDelete, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(btnServiceAdd, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnServiceUpdate, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(txtDays, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label2)
                                            .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label3))
                                        .addGap(7, 7, 7)
                                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label4)
                                            .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label6))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label5)
                                            .addComponent(txtDetail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label7))))
                                .addGap(7, 7, 7)))
                        .addContainerGap())
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label1;
    private JTextField txtSearchCustomer;
    private JScrollPane scrollPane1;
    private JTable tblCustomer;
    private JLabel lblName;
    private JLabel label8;
    private JPanel panel2;
    private JLabel label2;
    private JTextField txtTitle;
    private JTextField txtDays;
    private JLabel label3;
    private JTextField txtPrice;
    private JLabel label4;
    private JTextField txtDetail;
    private JLabel label5;
    private JButton btnServiceAdd;
    private JButton btnServiceUpdate;
    private JLabel label6;
    private JTextField txtDate;
    private JButton btnDelete;
    private JScrollPane scrollPane2;
    private JTable tblService;
    private JTextField txtStatus;
    private JLabel label7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

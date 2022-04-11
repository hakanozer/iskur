/*
 * Created by JFormDesigner on Thu Apr 07 18:52:11 TRT 2022
 */

package views;

import java.awt.event.*;

import models.ServiceImpl;
import models.UserImpl;
import props.Customer;
import props.Service;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Services extends Base {

    ServiceImpl service = new ServiceImpl();
    Customer customer= new Customer();
    int row=-1;//slect yaptıgın aman buna gelsin diye
    int value;// rowvalue için


    public void rowvalue(){

        int column=0;
        row= tblCustomer.getSelectedRow();//
        value=(int) tblCustomer.getModel().getValueAt(row,column);
        String sid= String.valueOf(tblCustomer.getValueAt(row,0));
        String cid= String.valueOf(tblCustomer.getValueAt(row,0));//SERVİCE DE SADECE CİD VAR O YÜZDEN
        String title= String.valueOf(tblCustomer.getValueAt(row,1));
        String info= String.valueOf(tblCustomer.getValueAt(row,2));
        String days= String.valueOf(tblCustomer.getValueAt(row,3));
        String date= String.valueOf(tblCustomer.getValueAt(row,4));
        String status= String.valueOf(tblCustomer.getValueAt(row,5));
        String price= String.valueOf(tblCustomer.getValueAt(row,6));

        System.out.println("cal"+value);

        txttitle.setText("title");
        txtdetail.setText("info");
        txtdetail.setText("info");
        txtdays.setText("days");
        txtdate.setText("date");
        txtprice.setText("price");







    }

    private Service fncDataValid(){

        String title =txttitle.getText().trim();
        String detail =txtdetail.getText().trim();
        int days = Integer.parseInt(txtdays.getText().trim());
        String date =txtdate.getText().trim();
        int price = Integer.parseInt(txtprice.getText().trim());

        int status=0;//service status 0 old. eklendi. ilk ekleniş 0

        int rowIndex= tblCustomer.getSelectedRow();
        int colIndex=0;
        int cid=(int) tblCustomer.getValueAt(rowIndex,colIndex);//service ile customer arasındaki ortak yerden datayı çektik.

        Service service = new Service(0,cid,title,detail,days,date,status,price);
        return service;
    }


    public Services() {
        initComponents();
        lblName.setText( "Sn." + UserImpl.name );
        tblCustomer.setModel( service.serviceCustomerTable(null) );
    }

    private void thisWindowClosing(WindowEvent e) {
        new Dashboard().setVisible(true);
    }

    private void btnCustomerAdd(ActionEvent e) {
        Service servicex= fncDataValid();
        int status= service.serviceInsert(servicex);
    }

    private void txtCustomerSearchKeyReleased(KeyEvent e) {
        String txtSearch = txtCustomerSearch.getText().trim();
        tblCustomer.setModel(  service.serviceCustomerTable( txtSearch ) );
    }

    private void btnUpdateButtonClick(ActionEvent e) {
        String title=txttitle.getText();
        String info=txtdetail.getText();
        int days= Integer.parseInt(txtdays.getText());
        String date= txtdate.getText();
        int price=Integer.parseInt(txtprice.getText());



        int rowIndex = tblCustomer.getSelectedRow();
        int colIndex = 0;
        int cid= (int) tblCustomer.getValueAt(rowIndex,colIndex);//kesişimini al

        Service servicex=new Service(value,cid,title,info,days,date,price,value);
        row=tblCustomer.getSelectedRow();
        if (row!=-1){
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to update the customer?","Update Window",JOptionPane.YES_OPTION);//parent component nerede görüneceği this button

            if (answer==0){
                service.serviceUpdate(servicex);
                tblCustomer.setModel(service.serviceCustomerTable(null)); //tabloyu refresh et
//                System.out.println(row+" update");
                txttitle.setText("");
                txtdetail.setText("");
                txtdays.setText("");
                txtdate.setText("");
                txtprice.setText("");

                row=-1;

            }
        }
        else{
            JOptionPane.showMessageDialog(this,"Please choose."); //this kendini burada ortala
            //show confirm anlaşmayı kabul etmek istiyor musun.
        }
    }

    private void btnDeleteClick(ActionEvent e) {



        if(row !=-1){
            int answer =JOptionPane.showConfirmDialog(this,"Are you sure you want to delete the customer?","delete Window",JOptionPane.YES_NO_OPTION);
            System.out.println(answer);

            if (answer==0){
                service.serviceDelete(value);
                tblCustomer.setModel(service.serviceCustomerTable(null));//refresh
                txttitle.setText("");
                txtdetail.setText("");
                txtdays.setText("");
                txtdate.setText("");
                txtprice.setText("");
                row=-1;
            }

        }else {JOptionPane.showMessageDialog(this,"Please Choose");}


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        lblName = new JLabel();
        label3 = new JLabel();
        txtCustomerSearch = new JTextField();
        scrollPane1 = new JScrollPane();
        tblCustomer = new JTable();
        label4 = new JLabel();
        txttitle = new JTextField();
        txtdays = new JTextField();
        label5 = new JLabel();
        label6 = new JLabel();
        scrollPane2 = new JScrollPane();
        txtdetail = new JTextArea();
        btnAdd = new JButton();
        lblError = new JLabel();
        btnUpdate = new JButton();
        btnDelete = new JButton();
        label2 = new JLabel();
        label7 = new JLabel();
        txtprice = new JTextField();
        txtdate = new JTextField();

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

        //---- label3 ----
        label3.setText("Customer Search");

        //---- txtCustomerSearch ----
        txtCustomerSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtCustomerSearchKeyReleased(e);
            }
        });

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(tblCustomer);
        }

        //---- label4 ----
        label4.setText("Title");

        //---- label5 ----
        label5.setText("Days");

        //---- label6 ----
        label6.setText("Details");

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(txtdetail);
        }

        //---- btnAdd ----
        btnAdd.setIcon(new ImageIcon(getClass().getResource("/addIconsn.png")));
        btnAdd.addActionListener(e -> btnCustomerAdd(e));

        //---- lblError ----
        lblError.setText(" ");
        lblError.setForeground(new Color(244, 92, 92));

        //---- btnUpdate ----
        btnUpdate.setIcon(new ImageIcon(getClass().getResource("/updateIconsn.png")));
        btnUpdate.addActionListener(e -> btnUpdateButtonClick(e));

        //---- btnDelete ----
        btnDelete.setIcon(new ImageIcon(getClass().getResource("/deleteIconsn.png")));
        btnDelete.addActionListener(e -> btnDeleteClick(e));

        //---- label2 ----
        label2.setText("Price");

        //---- label7 ----
        label7.setText("Date");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label3)
                            .addGap(18, 18, 18)
                            .addComponent(txtCustomerSearch))
                        .addComponent(scrollPane1)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label6)
                                .addComponent(label4))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addComponent(txttitle, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label5)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtdays, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE))
                                .addComponent(scrollPane2)
                                .addComponent(lblError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(btnUpdate)
                                            .addGap(126, 126, 126)
                                            .addComponent(btnDelete)))
                                    .addGap(25, 25, 25)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label2)
                                        .addComponent(label7))
                                    .addGap(80, 80, 80)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtprice, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                        .addComponent(txtdate, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                                    .addGap(0, 0, Short.MAX_VALUE)))))
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
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(txtCustomerSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4)
                            .addComponent(txttitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(label5))
                            .addComponent(txtdays, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label6)
                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lblError)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(btnAdd)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2)
                            .addComponent(txtprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnUpdate)
                                .addComponent(btnDelete)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label7)
                                .addComponent(txtdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(55, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel lblName;
    private JLabel label3;
    private JTextField txtCustomerSearch;
    private JScrollPane scrollPane1;
    private JTable tblCustomer;
    private JLabel label4;
    private JTextField txttitle;
    private JTextField txtdays;
    private JLabel label5;
    private JLabel label6;
    private JScrollPane scrollPane2;
    private JTextArea txtdetail;
    private JButton btnAdd;
    private JLabel lblError;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JLabel label2;
    private JLabel label7;
    private JTextField txtprice;
    private JTextField txtdate;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

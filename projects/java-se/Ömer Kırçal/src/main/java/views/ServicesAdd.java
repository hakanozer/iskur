/*
 * Created by JFormDesigner on Thu Apr 07 18:52:12 TRT 2022
 */

package views;

import java.awt.event.*;

import models.DasbordImpl;
import models.DasbordImpl;
import models.ServiceImpl;
import models.UserImpl;
import props.Customer;
import props.Service;
import utils.Util;

import java.awt.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class ServicesAdd extends Base {
    int row = -1;
    int selectedId =0;
    ServiceImpl serviceImpl =  new ServiceImpl();
    Service service = new Service();
    Dashbord db = new Dashbord();
    DasbordImpl dashboardImpl = new DasbordImpl();
    public ServicesAdd() {
        initComponents();
        lblName.setText("Dear. " + UserImpl.name);
        tblServiceCustomer.setModel(serviceImpl.serviceCustomerTable(null));
        db.tblNotCompleted.setModel(dashboardImpl.customerModel());
        tblCustomer.setModel(serviceImpl.serviceUpdateDeleteTable());
    }

    private void thisWindowClosing(WindowEvent e) {
        new Dashbord().setVisible(true);
    }

    public Service fncDataValid(){
        int sid=service.getSid();
        int cid=service.getCid();
        String date=service.getDate();
        int status=service.getStatus();
        String title=txtTitle.getText().toLowerCase(Locale.ROOT).trim();
        String info=txtInfo.getText().toLowerCase(Locale.ROOT).trim();
        int days= Integer.parseInt(txtDays.getText());
        int price = Integer.parseInt(txtPrice.getText().toLowerCase(Locale.ROOT).trim());


        if(title.equals("")){
            lblError.setText("Please Enter Title");
            txtTitle.requestFocus();;
        }else if(info.equals("")){
            lblError.setText("Please Enter Details");
            txtInfo.requestFocus();
        }else if(days==0){
            lblError.setText("Please Enter Days");
            txtDays.requestFocus();
        }else if(price==0){
            lblError.setText("Please Enter Price");
            txtPrice.requestFocus();
        }else if(status==0){
            lblError.setText("Please Enter Status");
            txtStatus.requestFocus();
        }else {
            lblError.setText("");
            Service service = new Service(0,cid,title,info,days,date,status,price);

            return service;
        }
        return null;
    }

    public void textClear(){
        txtTitle.setText("");
        txtInfo.setText("");
        txtDays.setText("");
        txtPrice.setText("");
    }
    public void rowValue(){
        int column=0;
        row=tblServiceCustomer.getSelectedRow();
        selectedId= (int) tblServiceCustomer.getValueAt(row,column);//seçili id'yi yakalama

        int cid= Integer.parseInt(String.valueOf(tblServiceCustomer.getValueAt(row,0)));  //cast ettik obje olduğu için.
        String name= String.valueOf(tblServiceCustomer.getValueAt(row,1));
        String surname= String.valueOf(tblServiceCustomer.getValueAt(row,2));
        String email= String.valueOf(tblServiceCustomer.getValueAt(row,3));
        String phone= String.valueOf(tblServiceCustomer.getValueAt(row,4));
        String address= String.valueOf(tblServiceCustomer.getValueAt(row,5));
        System.out.println("Selected Sindex: "+selectedId);



    }
    public void rowSelect() {
        int column = 0;
        row = tblCustomer.getSelectedRow();
        selectedId = (int) tblCustomer.getValueAt(row,column);

        int sid = Integer.parseInt(String.valueOf(tblCustomer.getValueAt(row,0)));
        int cid = Integer.parseInt(String.valueOf(tblCustomer.getValueAt(row,1)));
        String name = String.valueOf(tblCustomer.getValueAt(row,2));
        String surname = String.valueOf(tblCustomer.getValueAt(row,3));
        String title = String.valueOf(tblCustomer.getValueAt(row,4));
        String info = String.valueOf(tblCustomer.getValueAt(row,5));
        int days = Integer.parseInt(String.valueOf(tblCustomer.getValueAt(row,6)));
        String date = String.valueOf(tblCustomer.getValueAt(row,7));
        String status = String.valueOf(tblCustomer.getValueAt(row,8));
        int price = Integer.parseInt(String.valueOf(tblCustomer.getValueAt(row,9)));
        System.out.println("selectedId "+ selectedId);

        txtTitle.setText(title);
        txtInfo.setText(info);
        txtDays.setText(String.valueOf(days));
        txtPrice.setText(String.valueOf(price));
        txtStatus.setText(String.valueOf(status));

    }



    private void btnServicesAddClicked(ActionEvent e) {
        Service s = fncDataValid();
        if (s != null ) {
            int status = serviceImpl.serviceInsert(s);
            if (status >0) {
                System.out.println("Ekleme Başarlı");
                db.tblNotCompleted.setModel(dashboardImpl.customerModel());
                textClear();
            }else {
                lblError.setText("Insert Error");
            }
        }
    }
    private void btnServiceUpdateClicked(ActionEvent e) {
        String title = txtTitle.getText();
        String info = txtInfo.getText();
        int days = Integer.parseInt(txtDays.getText());
        int price = Integer.parseInt(txtPrice.getText());
        int status = Integer.parseInt(txtStatus.getText());

        Service service = new Service(selectedId,title,info,days,price,status);
        if (row!=-1){
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to update the customer?","Update Window",JOptionPane.YES_OPTION);
            if (answer==0){
                serviceImpl.serviceUpdate(service);
                tblCustomer.setModel(serviceImpl.serviceUpdateDeleteTable());
                textClear();
                row=-1;

            }
        }else{
            JOptionPane.showMessageDialog(this,"Please choose.");

        }
    }

    private void btnServiceDeleteClicked(ActionEvent e) {
        if (row != -1) {
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to delete the customer?","Delete Window",JOptionPane.YES_OPTION);

            if (answer==0) {
                serviceImpl.serviceDelete(selectedId);
                tblCustomer.setModel(serviceImpl.serviceUpdateDeleteTable());
                textClear();
                row = -1;
            }
        }else {
            JOptionPane.showMessageDialog(this,"Please choose.");
        }
    }

    private void txtSearchKeyReleased(KeyEvent e) {
        // TODO add your code here
    }

    private void tblServiceCustomerMouseClicked(MouseEvent e) {
        rowValue();
    }

    private void tblCustomerMouseClicked(MouseEvent e) {
        rowSelect();
    }

    private void tblCustomerKeyReleased(KeyEvent e) {
        rowSelect();
    }



    private void tblServiceCustomerKeyReleased(KeyEvent e) {
        rowValue();
    }





    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblName = new JLabel();
        panel2 = new JPanel();
        label8 = new JLabel();
        txtCustomerSearch = new JTextField();
        scrollPane1 = new JScrollPane();
        tblServiceCustomer = new JTable();
        panel1 = new JPanel();
        txtTitle = new JTextField();
        txtDays = new JTextField();
        scrollPane2 = new JScrollPane();
        txtInfo = new JTextArea();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        txtPrice = new JTextField();
        label9 = new JLabel();
        btnServicesAdd = new JButton();
        btnServiceUpdate = new JButton();
        label13 = new JLabel();
        lblError = new JLabel();
        btnServiceDelete = new JButton();
        txtStatus = new JTextField();
        scrollPane3 = new JScrollPane();
        tblCustomer = new JTable();
        label6 = new JLabel();
        label7 = new JLabel();

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

        //======== panel2 ========
        {
            panel2.setBackground(SystemColor.activeCaption);

            //---- label8 ----
            label8.setText("CUSTOMER SEARCH");
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            label8.setFont(new Font("Arial", Font.BOLD, 14));
            label8.setForeground(Color.black);

            //---- txtCustomerSearch ----
            txtCustomerSearch.setHorizontalAlignment(SwingConstants.CENTER);
            txtCustomerSearch.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txtSearchKeyReleased(e);
                }
            });

            //======== scrollPane1 ========
            {

                //---- tblServiceCustomer ----
                tblServiceCustomer.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        tblServiceCustomerKeyReleased(e);
                    }
                });
                tblServiceCustomer.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblServiceCustomerMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(tblServiceCustomer);
            }

            //======== panel1 ========
            {
                panel1.setBackground(Color.lightGray);

                //---- txtTitle ----
                txtTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));

                //---- txtDays ----
                txtDays.setFont(new Font("Segoe UI", Font.BOLD, 12));

                //======== scrollPane2 ========
                {

                    //---- txtInfo ----
                    txtInfo.setFont(new Font("Segoe UI", Font.BOLD, 12));
                    scrollPane2.setViewportView(txtInfo);
                }

                //---- label10 ----
                label10.setText("DETAILS");
                label10.setHorizontalAlignment(SwingConstants.CENTER);
                label10.setFont(new Font("Arial", Font.BOLD, 14));
                label10.setForeground(Color.black);

                //---- label11 ----
                label11.setText("DAYS");
                label11.setHorizontalAlignment(SwingConstants.CENTER);
                label11.setFont(new Font("Arial", Font.BOLD, 14));
                label11.setForeground(Color.black);

                //---- label12 ----
                label12.setText("PRICE");
                label12.setHorizontalAlignment(SwingConstants.CENTER);
                label12.setFont(new Font("Arial", Font.BOLD, 14));
                label12.setForeground(Color.black);

                //---- txtPrice ----
                txtPrice.setFont(new Font("Segoe UI", Font.BOLD, 12));

                //---- label9 ----
                label9.setText("TITLE");
                label9.setHorizontalAlignment(SwingConstants.CENTER);
                label9.setFont(new Font("Arial", Font.BOLD, 14));
                label9.setForeground(Color.black);

                //---- btnServicesAdd ----
                btnServicesAdd.setBackground(Color.lightGray);
                btnServicesAdd.setForeground(Color.white);
                btnServicesAdd.setIcon(new ImageIcon(getClass().getResource("/btnAddIcon.png")));
                btnServicesAdd.setBorder(null);
                btnServicesAdd.setToolTipText("ADD");
                btnServicesAdd.addActionListener(e -> btnServicesAddClicked(e));

                //---- btnServiceUpdate ----
                btnServiceUpdate.setBackground(Color.lightGray);
                btnServiceUpdate.setForeground(Color.white);
                btnServiceUpdate.setIcon(new ImageIcon(getClass().getResource("/btnUpdateIcon.png")));
                btnServiceUpdate.setBorder(null);
                btnServiceUpdate.setToolTipText("UPDATE");
                btnServiceUpdate.addActionListener(e -> btnServiceUpdateClicked(e));

                //---- label13 ----
                label13.setText("STATUS");
                label13.setHorizontalAlignment(SwingConstants.CENTER);
                label13.setFont(new Font("Arial", Font.BOLD, 14));
                label13.setForeground(Color.black);

                //---- lblError ----
                lblError.setFont(new Font("Segoe UI", Font.BOLD, 12));
                lblError.setForeground(new Color(245, 6, 6));
                lblError.setText(" ");

                //---- btnServiceDelete ----
                btnServiceDelete.setBackground(Color.lightGray);
                btnServiceDelete.setForeground(Color.white);
                btnServiceDelete.setIcon(new ImageIcon(getClass().getResource("/btnDeleteIcon.png")));
                btnServiceDelete.setBorder(null);
                btnServiceDelete.setToolTipText("DELETE");
                btnServiceDelete.addActionListener(e -> btnServiceDeleteClicked(e));

                //---- txtStatus ----
                txtStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGroup(panel1Layout.createParallelGroup()
                                .addComponent(label9, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label10, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addComponent(label11, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label12, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label13, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                                .addComponent(txtDays, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addComponent(btnServicesAdd, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnServiceUpdate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnServiceDelete, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap())
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTitle, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(txtDays, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(144, 144, 144))
                                .addGroup(panel1Layout.createSequentialGroup()
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addComponent(label10, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label12, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label13, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
                                    .addGap(157, 157, 157))))
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGroup(panel1Layout.createParallelGroup()
                                .addComponent(btnServicesAdd, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnServiceUpdate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnServiceDelete, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                );
            }

            //======== scrollPane3 ========
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
                scrollPane3.setViewportView(tblCustomer);
            }

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup()
                            .addComponent(label8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCustomerSearch)
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                            .addComponent(scrollPane3, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE))
                        .addContainerGap())
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCustomerSearch, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
            );
        }

        //---- label6 ----
        label6.setText("KIRCALO");
        label6.setHorizontalAlignment(SwingConstants.LEFT);
        label6.setFont(new Font("Kristen ITC", Font.BOLD, 26));
        label6.setForeground(SystemColor.textHighlight);

        //---- label7 ----
        label7.setText("TECHNICAL SERVICE");
        label7.setHorizontalAlignment(SwingConstants.LEFT);
        label7.setFont(new Font("Segoe UI Black", Font.BOLD, 22));
        label7.setForeground(SystemColor.textHighlight);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label6, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label7, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                    .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
                .addComponent(panel2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(lblName)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(11, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblName;
    private JPanel panel2;
    private JLabel label8;
    private JTextField txtCustomerSearch;
    private JScrollPane scrollPane1;
    private JTable tblServiceCustomer;
    private JPanel panel1;
    private JTextField txtTitle;
    private JTextField txtDays;
    private JScrollPane scrollPane2;
    private JTextArea txtInfo;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JTextField txtPrice;
    private JLabel label9;
    private JButton btnServicesAdd;
    private JButton btnServiceUpdate;
    private JLabel label13;
    private JLabel lblError;
    private JButton btnServiceDelete;
    private JTextField txtStatus;
    private JScrollPane scrollPane3;
    private JTable tblCustomer;
    private JLabel label6;
    private JLabel label7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

/*
 * Created by JFormDesigner on Thu Apr 07 18:52:12 TRT 2022
 */

package views;

import java.awt.event.*;

import models.DashboardImpl;
import models.ServiceImpl;
import models.UserImpl;
import props.Customer;
import props.Service;
import utils.Util;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Services extends Base {
    int row = -1;
    int rowS = -1;
    int selectedId =0;
    ServiceImpl serviceImpl =  new ServiceImpl();
    Service service = new Service();
    Dashboard db = new Dashboard();
    DashboardImpl dashboardImpl = new DashboardImpl();
    public Services() {
        initComponents();
        lblName.setText("Dear. " + UserImpl.name);
        tblCustomerService.setModel(serviceImpl.serviceCustomerTable(null));
        //db.tblNotComplieted.setModel(dashboardImpl.customerModel());
        tblUpdateDelete.setModel(serviceImpl.serviceUpdateDeleteTable());
    }

    private void thisWindowClosing(WindowEvent e) {
        new Dashboard().setVisible(true);
    }

    public Service fncDataValid(){
        String title=txtTitle.getText().trim();
        String info=txtInfo.getText().trim();
        String days = txtDays.getText();
        String date = txtDate.getText();
        String price = txtPrice.getText();


        if(title.equals("")){
            lblError.setText("Please Enter Title");
            txtTitle.requestFocus();;
        }else if(info.equals("")){
            lblError.setText("Please Enter Details");
            txtInfo.requestFocus();
        }else if(date.equals("")) {
            lblError.setText("Please Enter Date");
            txtInfo.requestFocus();
        }else if(price.equals("")){
            lblError.setText("Please Enter Price");
            txtPrice.requestFocus();
        }else {
            lblError.setText("");
            int daysD = Integer.parseInt(days);
            int priceD = Integer.parseInt(price);
            Service service = new Service(0,selectedId,title,info,daysD,date,0,priceD);

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
        row=tblCustomerService.getSelectedRow();
        selectedId= (int) tblCustomerService.getValueAt(row,column);//seçili id'yi yakalama

        int cid= Integer.parseInt(String.valueOf(tblCustomerService.getValueAt(row,0)));  //cast ettik obje olduğu için.
        String name= String.valueOf(tblCustomerService.getValueAt(row,1));
        String surname= String.valueOf(tblCustomerService.getValueAt(row,2));
        String email= String.valueOf(tblCustomerService.getValueAt(row,3));
        String phone= String.valueOf(tblCustomerService.getValueAt(row,4));
        String address= String.valueOf(tblCustomerService.getValueAt(row,5));
        System.out.println("Selected Sindex: "+selectedId);



    }
    public void rowSelect() {
        int column = 0;
        row = tblUpdateDelete.getSelectedRow();
        selectedId = (int) tblUpdateDelete.getValueAt(row,column);

        int sid = Integer.parseInt(String.valueOf(tblUpdateDelete.getValueAt(row,0)));
        int cid = Integer.parseInt(String.valueOf(tblUpdateDelete.getValueAt(row,1)));
        String name = String.valueOf(tblUpdateDelete.getValueAt(row,2));
        String surname = String.valueOf(tblUpdateDelete.getValueAt(row,3));
        String title = String.valueOf(tblUpdateDelete.getValueAt(row,4));
        String info = String.valueOf(tblUpdateDelete.getValueAt(row,5));
        int days = Integer.parseInt(String.valueOf(tblUpdateDelete.getValueAt(row,6)));
        String date = String.valueOf(tblUpdateDelete.getValueAt(row,7));
        String status = String.valueOf(tblUpdateDelete.getValueAt(row,8));
        int price = Integer.parseInt(String.valueOf(tblUpdateDelete.getValueAt(row,9)));
        System.out.println("selectedId "+ selectedId);

        txtTitle.setText(title);
        txtInfo.setText(info);
        txtDays.setText(String.valueOf(days));
        txtPrice.setText(String.valueOf(price));
        txtStatus.setText(String.valueOf(status));
        txtDate.setText(date);

    }

    private void txtCustomerSearchKeyReleased(KeyEvent e) {
        String txtSearch = txtCustomerSearch.getText().trim();
            tblCustomerService.setModel(serviceImpl.serviceCustomerTable(txtSearch));
    }

    private void btnAddClickService(ActionEvent e) {
        Service s = fncDataValid();
        if (s != null ) {
            int status = serviceImpl.serviceInsert(s);
            if (status >0) {
                System.out.println("Ekleme Başarlı");
                tblUpdateDelete.setModel(serviceImpl.serviceUpdateDeleteTable());
                textClear();
            }else {
                    lblError.setText("Insert Error");
                }
            }
        }
    private void btnUpdateClickService(ActionEvent e) {
        String title = txtTitle.getText();
        String info = txtInfo.getText();
        int days = Integer.parseInt(txtDays.getText());
        int price = Integer.parseInt(txtPrice.getText());

        Service service = new Service(selectedId,title,info,days,price);
        if (row!=-1){
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to update the customer?","Update Window",JOptionPane.YES_OPTION);
            if (answer==0){
                serviceImpl.serviceUpdate(service);
                tblCustomerService.setModel(serviceImpl.serviceCustomerTable(null));
                textClear();
                row=-1;

            }
        }else{
            JOptionPane.showMessageDialog(this,"Please choose.");

        }
    }

    private void btnDeleteClickService(ActionEvent e) {
        if (row != -1) {
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to delete the customer?","Delete Window",JOptionPane.YES_OPTION);

            if (answer==0) {
                serviceImpl.serviceDelete(selectedId);
                tblCustomerService.setModel(serviceImpl.serviceCustomerTable(null));
                textClear();
                row = -1;
            }
        }else {
            JOptionPane.showMessageDialog(this,"Please choose.");
        }
    }



    private void tblCustomerServiceKeyReleased(KeyEvent e) {
            rowValue();
        }

        private void tblCustomerServiceMouseClicked(MouseEvent e) {
            rowValue();

        }

        private void tblUpdateDeleteKeyReleased(KeyEvent e) {
            rowSelect();
        }

        private void tblUpdateDeleteMouseClicked(MouseEvent e) {
            rowSelect();
        }






    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblService = new JLabel();
        lblName = new JLabel();
        label2 = new JLabel();
        txtCustomerSearch = new JTextField();
        scrollPane1 = new JScrollPane();
        tblCustomerService = new JTable();
        panel1 = new JPanel();
        label3 = new JLabel();
        txtTitle = new JTextField();
        label4 = new JLabel();
        txtDays = new JTextField();
        label5 = new JLabel();
        scrollPane2 = new JScrollPane();
        txtInfo = new JTextArea();
        btnAddClickService = new JButton();
        lblError = new JLabel();
        txtPrice = new JTextField();
        label1 = new JLabel();
        btnDeleteClickService = new JButton();
        btnUpdateClickService = new JButton();
        label6 = new JLabel();
        txtDate = new JTextField();
        txtStatus = new JTextField();
        label7 = new JLabel();
        scrollPane3 = new JScrollPane();
        tblUpdateDelete = new JTable();

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
        lblService.setHorizontalAlignment(SwingConstants.CENTER);
        lblService.setForeground(new Color(33, 17, 17));

        //---- lblName ----
        lblName.setText(" ");
        lblName.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblName.setForeground(new Color(33, 17, 17));

        //---- label2 ----
        label2.setText("Customer Search");
        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));

        //---- txtCustomerSearch ----
        txtCustomerSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtCustomerSearchKeyReleased(e);
            }
        });

        //======== scrollPane1 ========
        {

            //---- tblCustomerService ----
            tblCustomerService.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    tblCustomerServiceKeyReleased(e);
                }
            });
            tblCustomerService.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tblCustomerServiceMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(tblCustomerService);
        }

        //======== panel1 ========
        {

            //---- label3 ----
            label3.setText("Title");
            label3.setFont(new Font("Times New Roman", Font.BOLD, 14));

            //---- label4 ----
            label4.setText("Days");
            label4.setFont(new Font("Times New Roman", Font.BOLD, 14));

            //---- label5 ----
            label5.setText("Info");
            label5.setFont(new Font("Times New Roman", Font.BOLD, 14));

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(txtInfo);
            }

            //---- btnAddClickService ----
            btnAddClickService.setText("Add");
            btnAddClickService.setFont(new Font("Times New Roman", Font.BOLD, 16));
            btnAddClickService.addActionListener(e -> {
			btnAddClickService(e);
			btnAddClickService(e);
		});

            //---- lblError ----
            lblError.setText(" ");
            lblError.setForeground(Color.red);

            //---- label1 ----
            label1.setText("Price");
            label1.setFont(new Font("Times New Roman", Font.BOLD, 14));

            //---- btnDeleteClickService ----
            btnDeleteClickService.setText("Delete");
            btnDeleteClickService.setFont(new Font("Times New Roman", Font.BOLD, 14));
            btnDeleteClickService.addActionListener(e -> btnDeleteClickService(e));

            //---- btnUpdateClickService ----
            btnUpdateClickService.setText("Update");
            btnUpdateClickService.setFont(new Font("Times New Roman", Font.BOLD, 14));
            btnUpdateClickService.addActionListener(e -> btnUpdateClickService(e));

            //---- label6 ----
            label6.setText("Date");

            //---- label7 ----
            label7.setText("Status");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(label5, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(label3, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(label6, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                        .addGap(23, 23, 23)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(txtDate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label7, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))
                            .addComponent(txtTitle, GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPane2, GroupLayout.Alignment.LEADING))
                        .addGap(30, 30, 30)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label4, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtDays, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(btnAddClickService)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdateClickService)
                                .addGap(9, 9, 9)
                                .addComponent(btnDeleteClickService)))
                        .addGap(21, 21, 21))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(232, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDays, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4)
                            .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createParallelGroup()
                                .addComponent(label5)
                                .addComponent(label1)
                                .addComponent(txtPrice, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrollPane2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDeleteClickService)
                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdateClickService)
                            .addComponent(btnAddClickService)
                            .addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label7)
                            .addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(lblError)
                        .addContainerGap())
            );
        }

        //======== scrollPane3 ========
        {

            //---- tblUpdateDelete ----
            tblUpdateDelete.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    tblUpdateDeleteKeyReleased(e);
                }
            });
            tblUpdateDelete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tblUpdateDeleteMouseClicked(e);
                }
            });
            scrollPane3.setViewportView(tblUpdateDelete);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblService, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCustomerSearch, GroupLayout.PREFERRED_SIZE, 598, GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE))
                    .addContainerGap(2, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lblService, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtCustomerSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblService;
    private JLabel lblName;
    private JLabel label2;
    private JTextField txtCustomerSearch;
    private JScrollPane scrollPane1;
    private JTable tblCustomerService;
    private JPanel panel1;
    private JLabel label3;
    private JTextField txtTitle;
    private JLabel label4;
    private JTextField txtDays;
    private JLabel label5;
    private JScrollPane scrollPane2;
    private JTextArea txtInfo;
    private JButton btnAddClickService;
    private JLabel lblError;
    private JTextField txtPrice;
    private JLabel label1;
    private JButton btnDeleteClickService;
    private JButton btnUpdateClickService;
    private JLabel label6;
    private JTextField txtDate;
    private JTextField txtStatus;
    private JLabel label7;
    private JScrollPane scrollPane3;
    private JTable tblUpdateDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

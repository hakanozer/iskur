/*
 * Created by JFormDesigner on Thu Apr 07 18:52:13 TRT 2022
 */

package views;

import models.ServiceImpl;
import models.UserImpl;
import props.Service;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Services extends Base {
    ServiceImpl serviceImpl=new ServiceImpl();
    int row=-1;
    int rowS=-1;
    int sId=0;
    public Services() {
        initComponents();
        lblName.setText("Sn."+ UserImpl.name);
        tblCustomer.setModel(serviceImpl.serviceCustomerTable(null));
        tblService.setModel(serviceImpl.serviceTable());
    }

    private void thisWindowClosing(WindowEvent e) {
        new Dashboard().setVisible(true);
    }

    public Service fncDataValidate(){
        row=tblCustomer.getSelectedRow();
        String title=txtTitle.getText().trim();
        String details=txtDetails.getText().trim();
        String days=txtDays.getText().trim();
        String price=txtPrice.getText().trim();
        String date=txtDate.getText().trim();

        if(title.equals("")){
            lblError.setText("Title is Empty!");
        }else if (details.equals("")){
            lblError.setText("Details is Empty!");
        }else if (days.equals("")){
            lblError.setText("Days is Empty!");
        }else if(price.equals("")){
            lblError.setText("Price is Empty!");
        }else if(date.equals("")){
            lblError.setText("Date is Empty!");
        }
        else if(row==-1){
            lblError.setText("Please select customer from the table");
        }else {

            Double priceD=Double.parseDouble(txtPrice.getText().trim());
             int daysI=Integer.parseInt(txtDays.getText().trim());
             int cid=(Integer) tblCustomer.getValueAt(row,0);

             lblError.setText("");

            Service service=new Service(0,cid,title,details,daysI,null,0,priceD);
            return service;
        }
        return null;
    }

    private void txtCustomerSearchKeyReleased(KeyEvent e) {
        String txtSearch=txtCustomerSearch.getText().trim();
        tblCustomer.setModel(serviceImpl.serviceCustomerTable(txtSearch));
    }

    private void btnServiceAdd(ActionEvent e) {
        Service service=fncDataValidate();
        if (service != null) {
            int status = serviceImpl.serviceInsert(service);
            if (status > 0) {
                JOptionPane.showMessageDialog(this, "Add services process is successful");
                tblService.setModel(serviceImpl.serviceTable());
                txtClear();

            } else {
                if (status == -1) {
                    lblError.setText("Email or Phone have already used");
                } else {
                    lblError.setText("Insert Error");
                }
            }
        }

    }
    public void txtClear(){
        txtTitle.setText("");
        txtDays.setText("");
        txtPrice.setText("");
        txtDetails.setText("");
    }

    private void btnServiceUpdate(ActionEvent e) {
        Service service=fncDataValidateService();
        if (service != null) {
            int status = serviceImpl.serviceUpdate(service);
            if (status > 0) {
                JOptionPane.showMessageDialog(this, "Update services process is successful");
                tblService.setModel(serviceImpl.serviceTable());
                txtClear();

            }
        }else{
            System.out.println("Update");
        }
    }

    private void btnServiceDelete(ActionEvent e) {
        row =tblService.getSelectedRow();
        if (row == -1) {
            lblError.setText("Please select the services you want to delete from the table.");
        } else {
            lblError.setText("");
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure that you want to delete the service","Delete Process",JOptionPane.YES_NO_OPTION);
            if(answer==0){
                serviceImpl.serviceDelete(sId);
                tblService.setModel(serviceImpl.serviceTable());

            }else{ tblService.clearSelection();
                txtClear();}
        }
    }
    public Service fncDataValidateService(){
        rowS=tblService.getSelectedRow();
        String title=txtTitle.getText().trim();
        String details=txtDetails.getText().trim();
        String days=txtDays.getText().trim();
        String date=txtDate.getText().trim();
        String price=txtPrice.getText().trim();

        if (rowS==-1){
            lblError.setText("Please select a service from the table above");
        }else if (title.equals("")){
            lblError.setText("Date is Empty");
        }else if(price.equals("")){
            lblError.setText("Price is empty");
        }else if(days.equals("")){
            lblError.setText("Days is empty");
        }
        else {
            lblError.setText("");

            Double priceD = Double.parseDouble(price);
            int daysD = Integer.parseInt(days);
            int cid = (Integer) tblService.getValueAt(rowS, 1);

            Service service = new Service(sId, cid, title, details, daysD, date,0, priceD);
            return service;
        }
    return null;
    }

    private void tblServiceMouseReleased(MouseEvent e) {
        rowS = tblService.getSelectedRow();
        sId = (Integer) tblService.getValueAt(rowS, 0);
        String title=(String) tblService.getValueAt(rowS,4);
        txtTitle.setText(title);
        String details=(String) tblService.getValueAt(rowS,5);
        txtDetails.setText(details);
        int days=(Integer)tblService.getValueAt(rowS,6);
        String daysString= String.valueOf(days);
        txtDays.setText(daysString);
        String date = (String) tblService.getValueAt(rowS, 7);
        txtDate.setText(date);


        Double price=(Double)tblService.getValueAt(rowS,9);
        String priceSting=String.valueOf(price);
        txtPrice.setText(priceSting);
        pack();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        lblName = new JLabel();
        label3 = new JLabel();
        txtCustomerSearch = new JTextField();
        scrollPane1 = new JScrollPane();
        tblCustomer = new JTable();
        panel1 = new JPanel();
        label4 = new JLabel();
        txtTitle = new JTextField();
        label5 = new JLabel();
        txtDays = new JTextField();
        label6 = new JLabel();
        scrollPane2 = new JScrollPane();
        txtDetails = new JTextArea();
        btnAdd = new JButton();
        lblError = new JLabel();
        txtPrice = new JTextField();
        label7 = new JLabel();
        btnUpdate = new JButton();
        btnDelete = new JButton();
        txtDate = new JTextField();
        label8 = new JLabel();
        tblService = new JTable();

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
        label1.setBackground(new Color(0, 102, 102));
        label1.setForeground(Color.black);

        //---- lblName ----
        lblName.setText(" ");
        lblName.setBackground(new Color(0, 102, 102));
        lblName.setForeground(Color.black);

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

            //---- tblCustomer ----
            tblCustomer.setBackground(new Color(255, 255, 153));
            scrollPane1.setViewportView(tblCustomer);
        }

        //======== panel1 ========
        {
            panel1.setBackground(new Color(255, 255, 153));

            //---- label4 ----
            label4.setText("Title");

            //---- label5 ----
            label5.setText("Days");

            //---- label6 ----
            label6.setText("Details");

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(txtDetails);
            }

            //---- btnAdd ----
            btnAdd.setIcon(new ImageIcon(getClass().getResource("/add_icon.png")));
            btnAdd.setBackground(new Color(153, 255, 255));
            btnAdd.addActionListener(e -> btnServiceAdd(e));

            //---- lblError ----
            lblError.setText(" ");

            //---- label7 ----
            label7.setText("Price");

            //---- btnUpdate ----
            btnUpdate.setIcon(new ImageIcon(getClass().getResource("/update_icon.png")));
            btnUpdate.setBackground(new Color(153, 255, 255));
            btnUpdate.addActionListener(e -> btnServiceUpdate(e));

            //---- btnDelete ----
            btnDelete.setIcon(new ImageIcon(getClass().getResource("/delete_icon.png")));
            btnDelete.setBackground(new Color(153, 255, 255));
            btnDelete.addActionListener(e -> btnServiceDelete(e));

            //---- label8 ----
            label8.setText("Date");

            //---- tblService ----
            tblService.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    tblServiceMouseReleased(e);
                }
            });

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label4)
                                    .addComponent(label8)
                                    .addComponent(label7)
                                    .addComponent(label5))
                                .addGap(27, 27, 27))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addComponent(label6)
                                .addGap(18, 18, 18)))
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtDate)
                                        .addComponent(txtPrice, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                                    .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDays, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(168, 168, 168)
                                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                .addComponent(btnAdd)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnUpdate)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnDelete))
                                            .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(95, Short.MAX_VALUE))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(tblService, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                                        .addContainerGap())))))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(tblService, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblError))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label4)
                                    .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDays, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label5))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label7))
                                .addGap(20, 20, 20)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label8))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete)
                            .addComponent(btnUpdate)
                            .addComponent(btnAdd)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label6)
                                .addGap(17, 17, 17)))
                        .addContainerGap())
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                            .addGap(388, 388, 388)
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING)
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(label3)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtCustomerSearch, GroupLayout.PREFERRED_SIZE, 572, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addGap(0, 29, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label1)
                        .addComponent(lblName))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(txtCustomerSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
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
    private JPanel panel1;
    private JLabel label4;
    private JTextField txtTitle;
    private JLabel label5;
    private JTextField txtDays;
    private JLabel label6;
    private JScrollPane scrollPane2;
    private JTextArea txtDetails;
    private JButton btnAdd;
    private JLabel lblError;
    private JTextField txtPrice;
    private JLabel label7;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JTextField txtDate;
    private JLabel label8;
    private JTable tblService;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

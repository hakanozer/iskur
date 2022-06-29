/*
 * Created by JFormDesigner on Thu Apr 07 18:52:12 TRT 2022
 */

package views;

import model.CustomerImpl;
import model.ServiceImpl;
import model.UserImpl;
import props.ComboItem;
import props.Customer;
import props.Service;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Services extends Base {
    ServiceImpl service=new ServiceImpl();
    public Services() {
        initComponents();
        lblName.setText(UserImpl.name);
        fncCmbDaysAdd();
        fncCmbStatusAdd();
        tblCustomer.setModel(service.serviceCustomerTable(null));
        tblService.setModel(service.serviceTable(null));

    }
    private void fncCmbDaysAdd(){
        for ( int i = 0; i < 20; i++ ) {
            cmbDays.addItem(i);
        }
    }
    private void fncCmbStatusAdd(){
        cmbStatus.addItem(new ComboItem("status 0","0"));
        cmbStatus.addItem(new ComboItem("status 1","1"));
        cmbStatus.addItem(new ComboItem("status 2","2"));
        cmbStatus.addItem(new ComboItem("status 3","3"));
        cmbStatus.addItem(new ComboItem("status 4","4"));

    }
    public void rowValue(int row,int column){
        txtCid.setText(String.valueOf(tblCustomer.getValueAt(row , 0)));
    }
    public void rowValueService(int row,int column){
        txtCid.setText(String.valueOf(tblService.getValueAt(row , 1)));
        txtTitle.setText(String.valueOf(tblService.getValueAt(row , 2)));
        txtInfo.setText(String.valueOf(tblService.getValueAt(row , 3)));
        int days= Integer.parseInt(String.valueOf(tblService.getValueAt(row , 4)));
        System.out.println(days);
        cmbDays.setSelectedItem(days);
        int status = Integer.parseInt(((ComboItem)cmbStatus.getSelectedItem()).getValue());
        //cmbStatus.setSelectedItem(((ComboItem)cmbStatus.getSelectedItem()).setValue(String.valueOf(tblService.getValueAt(row , 6))));
                ;
        System.out.println(String.valueOf(tblService.getValueAt(row , 6)));
        txtPrice.setText(String.valueOf(tblService.getValueAt(row , 7)));
        pack();
    }
    private Service fncDatavalidate(){
        try {
            if (txtCid.getText().equals("") || txtCid.getText().equals(null)) {
                txtCid.requestFocus();
                lblError.setText("cid cannot be empty");
            } else if ( txtTitle.getText().equals("") || txtTitle.getText().equals(null)) {
                txtTitle.requestFocus();
                lblError.setText("Title cannot be empty");
            } else if (txtInfo.getText().equals("") || txtInfo.getText().equals(null)) {
                txtInfo.requestFocus();
                lblError.setText("Info cannot be empty");
            } else if (cmbDays.getSelectedItem().equals("")) {
                cmbDays.requestFocus();
                lblError.setText("Days cannot be empty");
            } else if (cmbStatus.getSelectedItem().equals("")) {
                cmbStatus.requestFocus();
                lblError.setText("status cannot be empty");
            } else if (txtPrice.getText().equals("")) {
                txtPrice.requestFocus();
                lblError.setText("price cannot be empty");
            } else {
                int cid = Integer.parseInt(txtCid.getText().toLowerCase(Locale.ROOT).trim());
                String title = txtTitle.getText().toLowerCase(Locale.ROOT).trim();
                String info = txtInfo.getText().toLowerCase(Locale.ROOT).trim();
                int days = Integer.parseInt(String.valueOf(cmbDays.getSelectedItem()));
                //date
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
                LocalDate localDate = LocalDate.now();
                String date = dtf.format(localDate);
                //status
                int status = Integer.parseInt(((ComboItem)cmbStatus.getSelectedItem()).getValue());
                //date
                int price = Integer.parseInt(txtPrice.getText().toLowerCase(Locale.ROOT).trim());
                lblError.setText("");
                Service service = new Service(0, cid, title, info, days, date, status, price);
                return service;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void thisWindowClosing(WindowEvent e) {
        new Dashboard().setVisible(true);
    }

    private void searchKeyReleased(KeyEvent e) {
        String searchCustomer=txtSearch.getText().trim().toLowerCase(Locale.ROOT);
        tblCustomer.setModel(service.serviceCustomerTable(searchCustomer));

    }

    private void btnAddClick(ActionEvent e) {
        Service s=fncDatavalidate();
        if(s !=null){
            int status = service.serviceInsert(s);
            if(status > 0){
                ServiceImpl serviceImpl2=new ServiceImpl();
                tblService.setModel(serviceImpl2.serviceTable(null));
                lblError.setText("Add service successful");
            }
            else {
                lblError.setText("Service Insert Error !");
            }
            fncClear();
            pack();
        }
    }

    private void fncClear() {
        txtCid.setText("");
        txtInfo.setText("");
        txtTitle.setText("");
        txtSearch.setText("");
        txtPrice.setText("");
        txtSearchService.setText("");
    }


    private void tblCustomerMouseClicked(MouseEvent e) {
        rowValue(tblCustomer.getSelectedRow(),tblCustomer.getSelectedColumn());
    }

    private void btnUpdateServiceClick(ActionEvent e) {
        if (tblService.getSelectedRow() != -1) {
            System.out.println(tblService.getValueAt(tblService.getSelectedRow() , 0));
            int input = JOptionPane.showConfirmDialog(this, "Are you sure?","update",JOptionPane.YES_NO_OPTION);
            if( input==0 ){
                int cid = Integer.parseInt(txtCid.getText().toLowerCase(Locale.ROOT).trim());
                String title = txtTitle.getText().toLowerCase(Locale.ROOT).trim();
                String info = txtInfo.getText().toLowerCase(Locale.ROOT).trim();
                int days = Integer.parseInt(String.valueOf(cmbDays.getSelectedItem()));
                //date
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
                LocalDate localDate = LocalDate.now();
                String date = dtf.format(localDate);
                //status
                int status = Integer.parseInt(((ComboItem)cmbStatus.getSelectedItem()).getValue());
                //date
                int price = Integer.parseInt(txtPrice.getText().toLowerCase(Locale.ROOT).trim());
                Service service0=new Service(Integer.parseInt(String.valueOf(tblService.getValueAt(tblService.getSelectedRow() , 0))),cid, title, info, days, String.valueOf(tblService.getValueAt(tblService.getSelectedRow(), 5)), status, price);
                service.serviceUpdate(service0);
                ServiceImpl service2=new ServiceImpl();
                tblService.setModel(service2.serviceTable(null));
            }
            System.out.println("input : " + input);
        }
        else
            JOptionPane.showMessageDialog(this,"Please choose");

        pack();
        lblError.setText("Update succesful");
        fncClear();
    }


    private void btnDeleteServiceClick(ActionEvent e) {
        if (tblService.getSelectedRow() != -1) {
            System.out.println(tblService.getValueAt(tblService.getSelectedRow() , 0));
            int input = JOptionPane.showConfirmDialog(this, "Are you sure?","Delete",JOptionPane.YES_NO_OPTION);
            if(input==0){
                service.serviceDelete(Integer.parseInt(String.valueOf(tblService.getValueAt(tblService.getSelectedRow() , 0))));
                ServiceImpl service2=new ServiceImpl();
                tblService.setModel(service2.serviceTable(null));
            }
            System.out.println( "input :" + input );
            fncClear();
        }
        else
            JOptionPane.showMessageDialog(this,"Please choose");
    }

    private void txtSearchServiceKeyReleased(KeyEvent e) {
        String SearchService = txtSearchService.getText().trim().toLowerCase(Locale.ROOT);
        tblService.setModel(service.serviceTable(SearchService));
    }

    private void tblServiceMouseClicked(MouseEvent e) {
        rowValueService(tblService.getSelectedRow(),tblService.getSelectedColumn());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        lblName = new JLabel();
        label4 = new JLabel();
        txtSearch = new JTextField();
        scrollPane1 = new JScrollPane();
        tblCustomer = new JTable();
        label5 = new JLabel();
        txtCid = new JTextField();
        label6 = new JLabel();
        txtTitle = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        cmbDays = new JComboBox();
        cmbStatus = new JComboBox();
        txtPrice = new JTextField();
        btnAdd = new JButton();
        btnUpdate = new JButton();
        btnDelete = new JButton();
        lblError = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        scrollPane4 = new JScrollPane();
        txtInfo = new JTextArea();
        label9 = new JLabel();
        txtSearchService = new JTextField();
        scrollPane5 = new JScrollPane();
        tblService = new JTable();

        //======== this ========
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

        //---- lblName ----
        lblName.setText("text");

        //---- label4 ----
        label4.setText("Customer Search :");

        //---- txtSearch ----
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchKeyReleased(e);
            }
        });

        //======== scrollPane1 ========
        {

            //---- tblCustomer ----
            tblCustomer.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tblCustomerMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(tblCustomer);
        }

        //---- label5 ----
        label5.setText("Cid :");

        //---- label6 ----
        label6.setText("Title :");

        //---- label2 ----
        label2.setText("Days :");

        //---- label3 ----
        label3.setText(" Status :");

        //---- btnAdd ----
        btnAdd.setIcon(new ImageIcon(getClass().getResource("/addBtnIcon.png")));
        btnAdd.setFocusable(false);
        btnAdd.addActionListener(e -> btnAddClick(e));

        //---- btnUpdate ----
        btnUpdate.setIcon(new ImageIcon(getClass().getResource("/updateBtnIcon.png")));
        btnUpdate.setFocusable(false);
        btnUpdate.addActionListener(e -> btnUpdateServiceClick(e));

        //---- btnDelete ----
        btnDelete.setIcon(new ImageIcon(getClass().getResource("/deleteBtnIcon.png")));
        btnDelete.setFocusable(false);
        btnDelete.addActionListener(e -> btnDeleteServiceClick(e));

        //---- lblError ----
        lblError.setForeground(new Color(255, 51, 51));
        lblError.setText("text");

        //---- label7 ----
        label7.setText("price");

        //---- label8 ----
        label8.setText("Detail :");

        //======== scrollPane4 ========
        {
            scrollPane4.setViewportView(txtInfo);
        }

        //---- label9 ----
        label9.setText("Service Search :");

        //---- txtSearchService ----
        txtSearchService.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchKeyReleased(e);
                txtSearchServiceKeyReleased(e);
            }
        });

        //======== scrollPane5 ========
        {

            //---- tblService ----
            tblService.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tblServiceMouseClicked(e);
                }
            });
            scrollPane5.setViewportView(tblService);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36)
                                    .addComponent(lblName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGroup(contentPaneLayout.createParallelGroup()
                                                .addComponent(label2)
                                                .addComponent(label5))
                                            .addGap(18, 18, 18)
                                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtCid, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                                                .addComponent(cmbDays, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(label8)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(scrollPane4)))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGroup(contentPaneLayout.createParallelGroup()
                                                .addComponent(label6)
                                                .addComponent(label3))
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(label7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lblError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGroup(contentPaneLayout.createParallelGroup()
                                                .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cmbStatus, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE))
                                            .addGap(0, 0, Short.MAX_VALUE))))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(label4)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 576, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 685, GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(scrollPane5, GroupLayout.Alignment.LEADING)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label9)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSearchService, GroupLayout.PREFERRED_SIZE, 576, GroupLayout.PREFERRED_SIZE)))
                            .addGap(15, 15, 15))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(lblName))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label4)
                        .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label5)
                        .addComponent(txtCid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label6)
                        .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label2)
                                .addComponent(cmbDays, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label3))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label8)
                                .addComponent(scrollPane4)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label7))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(btnUpdate)
                                        .addComponent(btnDelete)
                                        .addComponent(btnAdd))))))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearchService, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label9))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane5, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(13, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel lblName;
    private JLabel label4;
    private JTextField txtSearch;
    private JScrollPane scrollPane1;
    private JTable tblCustomer;
    private JLabel label5;
    private JTextField txtCid;
    private JLabel label6;
    private JTextField txtTitle;
    private JLabel label2;
    private JLabel label3;
    private JComboBox cmbDays;
    private JComboBox cmbStatus;
    private JTextField txtPrice;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JLabel lblError;
    private JLabel label7;
    private JLabel label8;
    private JScrollPane scrollPane4;
    private JTextArea txtInfo;
    private JLabel label9;
    private JTextField txtSearchService;
    private JScrollPane scrollPane5;
    private JTable tblService;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

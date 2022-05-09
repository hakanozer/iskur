/*
 * Created by JFormDesigner on Thu Apr 07 18:53:20 TRT 2022
 */

package views;

import java.awt.event.*;

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
public class Services extends Base {
    ServiceImpl service = new ServiceImpl();
    public Services() {
        initComponents();
        lblName.setText("Merhaba, " + UserImpl.name);
        tblServiceCustomer.setModel(service.serviceCustomerTable(null));
        tblServiceService.setModel(service.serviceServiceTable());
    }
    int row = -1; //tblCars.getSelectedRow();
    int cid = 0;
    int sid = 0;
    int column = 0;
    int scolumn = 0;

    private Service fncDataValid(){
        row = tblServiceCustomer.getSelectedRow();
        String title=txtTitle.getText().trim();
        String info=txtDetail.getText().trim();
        int days= Integer.parseInt(txtDays.getText().trim());  //boşluk varsa al trimle sil
        String date = txtDate.getText().trim();
        int status = Integer.parseInt(txtStatus.getText().trim());
        cid = Integer.valueOf(tblServiceCustomer.getModel().getValueAt(row,column).toString());

        if (title.equals("")){
            lblError.setText("Title is Empty!!!");
            txtTitle.requestFocus();
        }else if (info.equals("")){
            lblError.setText("Surname is Empty!!!");
            txtDetail.requestFocus();
        }else if (days == 0){
            lblError.setText("Days is Empty!!!");
            txtDays.requestFocus();
        }
        else if (date.equals("")){
            lblError.setText("Date is Empty!!!");
            txtDate.requestFocus();
        }
        else if (status > 3){
            lblError.setText("Gecerli bir status degeri giriniz.");
            txtStatus.requestFocus();
        }
        else {
            lblError.setText("");
            Service s = new Service(0,cid,title,info,days,date,status);
            return s;
        }
        return null; //olumsuz halinde

    }

    private void thisWindowClosing(WindowEvent e) {
        new Dashboard().setVisible(true);
    }

    private void saveCustomerButtonClick(ActionEvent e) {
        // TODO add your code here
    }

    private void txtCustomerSearchKeyReleased(KeyEvent e) {
        String txtSearch = txtCustomerSearch.getText().trim();
        tblServiceCustomer.setModel(service.serviceCustomerTable(txtSearch));
    }

    private void btnDeleteClick(ActionEvent e) {
        // TODO add your code here
    }

    private void btnCustomerUpdateClick(ActionEvent e) {
        // TODO add your code here
    }

    private void btnAddServiceClick(ActionEvent e) {
        Service s = fncDataValid();
        if(s!=null){
            int status = service.serviceInsert(s);
            if (status>0){
                System.out.println("Ekleme basarili");
                txtTitle.setText("");
                txtDetail.setText("");
                txtDate.setText("");
                txtDays.setText("");
                txtStatus.setText("");
                tblServiceService.setModel(service.serviceServiceTable());
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

    private void tblServiceServiceKeyReleased(KeyEvent e) {
        rowVal();
    }

    private void tblServiceServiceMouseClicked(MouseEvent e) {
        rowVal();
    }
    void rowVal(){/////////////
        row = tblServiceService.getSelectedRow();
        String title = (String) tblServiceService.getValueAt(row, 2);
        String info = (String) tblServiceService.getValueAt(row, 3);
        int days = (int) tblServiceService.getValueAt(row, 4);
        String date = (String) tblServiceService.getValueAt(row, 5);
        int status = (int) tblServiceService.getValueAt(row, 6);

        txtTitle.setText(title);
        txtDetail.setText(info);
        txtDays.setText(String.valueOf(days));
        txtDate.setText(date);
        txtStatus.setText(String.valueOf(status));

    }

    private void btnServiceUpdateClick(ActionEvent e) {
       Service s = fncDataValid();
        if(row != -1 ) {
            row = tblServiceService.getSelectedRow();
            sid = Integer.valueOf(tblServiceService.getModel().getValueAt(row,scolumn).toString());
            int answer = JOptionPane.showConfirmDialog(this, "Guncellemek istediginizden emin misniz?", "Guncelleme islemi", JOptionPane.YES_NO_OPTION);
            if (answer == 0) {
                service.serviceUpdate(s,sid);
                tblServiceService.setModel(service.serviceServiceTable());
                row = -1;
            }
        }else{
            JOptionPane.showMessageDialog(this, "Lutfen secim yapiniz.");
        }
    }

    private void btnDeleteServiceClick(ActionEvent e) {
        if(row != -1 ) {
            row = tblServiceService.getSelectedRow();
            sid = Integer.valueOf(tblServiceService.getModel().getValueAt(row,scolumn).toString());
            int answer = JOptionPane.showConfirmDialog(this, "Silmek istediginizden emin miisniz?", "Silme islemi", JOptionPane.YES_NO_OPTION);
            if(answer==0){
                service.serviceDelete(sid);
                tblServiceService.setModel(service.serviceServiceTable());
                row = -1;
            }
        } else{
            JOptionPane.showMessageDialog(this, "Lutfen secim yapiniz.");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        lblName = new JLabel();
        label3 = new JLabel();
        txtCustomerSearch = new JTextField();
        scrollPane1 = new JScrollPane();
        tblServiceCustomer = new JTable();
        panel1 = new JPanel();
        label4 = new JLabel();
        txtTitle = new JTextField();
        label5 = new JLabel();
        txtDays = new JTextField();
        label6 = new JLabel();
        scrollPane2 = new JScrollPane();
        txtDetail = new JTextArea();
        lblError = new JLabel();
        scrollPane3 = new JScrollPane();
        tblServiceService = new JTable();
        btnServiceUpdate = new JButton();
        btnDeleteService = new JButton();
        btnAddService = new JButton();
        label2 = new JLabel();
        txtDate = new JTextField();
        label7 = new JLabel();
        txtStatus = new JTextField();

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
        label1.setForeground(Color.black);
        label1.setFont(new Font("Arial", Font.BOLD, 12));

        //---- lblName ----
        lblName.setText("text");
        lblName.setForeground(Color.black);
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);

        //---- label3 ----
        label3.setText("Customer Search:");

        //---- txtCustomerSearch ----
        txtCustomerSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtCustomerSearchKeyReleased(e);
            }
        });

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(tblServiceCustomer);
        }

        //======== panel1 ========
        {

            //---- label4 ----
            label4.setText("Title:");

            //---- label5 ----
            label5.setText("Days:");

            //---- label6 ----
            label6.setText("Detay");

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(txtDetail);
            }

            //---- lblError ----
            lblError.setForeground(Color.red);
            lblError.setText(" ");

            //======== scrollPane3 ========
            {

                //---- tblServiceService ----
                tblServiceService.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        tblServiceServiceKeyReleased(e);
                    }
                });
                tblServiceService.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblServiceServiceMouseClicked(e);
                    }
                });
                scrollPane3.setViewportView(tblServiceService);
            }

            //---- btnServiceUpdate ----
            btnServiceUpdate.setIcon(new ImageIcon(getClass().getResource("/updateButtonIcon.png")));
            btnServiceUpdate.addActionListener(e -> btnServiceUpdateClick(e));

            //---- btnDeleteService ----
            btnDeleteService.setIcon(new ImageIcon(getClass().getResource("/deleteIconButton.png")));
            btnDeleteService.addActionListener(e -> btnDeleteServiceClick(e));

            //---- btnAddService ----
            btnAddService.setIcon(new ImageIcon(getClass().getResource("/addButtonIcon.png")));
            btnAddService.addActionListener(e -> btnAddServiceClick(e));

            //---- label2 ----
            label2.setText("Date");

            //---- label7 ----
            label7.setText("Status");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(btnAddService, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(btnServiceUpdate, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(btnDeleteService, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(lblError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(104, 104, 104))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(scrollPane2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(txtTitle, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                                .addGap(40, 40, 40)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(label5, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                    .addComponent(label2, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDays, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txtDate)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label7)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane3))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDays, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label2)
                                .addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label7)
                                .addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(btnAddService)
                            .addComponent(btnServiceUpdate)
                            .addComponent(btnDeleteService))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                        .addGap(319, 319, 319)
                        .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
            );
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
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCustomerSearch, GroupLayout.PREFERRED_SIZE, 579, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane1)
                        .addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCustomerSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE))
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
    private JTable tblServiceCustomer;
    private JPanel panel1;
    private JLabel label4;
    private JTextField txtTitle;
    private JLabel label5;
    private JTextField txtDays;
    private JLabel label6;
    private JScrollPane scrollPane2;
    private JTextArea txtDetail;
    private JLabel lblError;
    private JScrollPane scrollPane3;
    private JTable tblServiceService;
    private JButton btnServiceUpdate;
    private JButton btnDeleteService;
    private JButton btnAddService;
    private JLabel label2;
    private JTextField txtDate;
    private JLabel label7;
    private JTextField txtStatus;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

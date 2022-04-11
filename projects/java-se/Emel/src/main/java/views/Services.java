/*
 * Created by JFormDesigner on Thu Apr 07 18:52:11 TRT 2022
 */

package views;

import java.beans.*;
import javax.swing.border.*;
import models.ServiceImpl;
import models.UserImpl;
import props.Service;

import java.awt.*;
import java.awt.event.*;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.text.JTextComponent;


public class Services extends Base {
    ServiceImpl serviceImpl = new ServiceImpl();
    int row = -1;
    int rowService = -1;
    int selectedId = 0;




    public Services() {
        initComponents();
        lblname.setText("Sn. " + UserImpl.name);
        tblCustomer.setModel(serviceImpl.serviceCustomerTable(null));
        tblServices.setModel(serviceImpl.tablemodelOlustur());
        cmbStatus.setModel(cmbmodel());


    }

    private void thisWindowClosing(WindowEvent e) {
        Dashboard d = new Dashboard();
        d.setVisible(true);
    }

    private void txtCustomerSearchKeyReleased(KeyEvent e) {
        String txtSearch = txtCustomerSearch.getText().trim();
        tblCustomer.setModel(serviceImpl.serviceCustomerTable(txtSearch));

    }

    private void btnAddClick(ActionEvent e) {
        Service service = fncDataValidate();
        if (service != null) {
            int status = serviceImpl.serviceInsert(service);
            if (status > 0) {
                JOptionPane.showMessageDialog(this, "Add services process is successful");
                tblServices.setModel(serviceImpl.tablemodelOlustur());
                tabbedPane1.setSelectedIndex(1);
            } else {
                if (status == -1) {
                    lblError.setText("Email or Phone have already used");
                } else {
                    lblError.setText("Insert Error");
                }
            }
        }

    }

    public Service fncDataValidate() {
        row = tblCustomer.getSelectedRow();
        String title = txtTitle.getText().trim();
        String details = txtDetails.getText().trim();
        String daysString = txtDays.getText().trim();
        String priceString = txtPrice.getText().trim();




        if (title.equals("")) {
            lblError.setText("Title is empty");
        } else if (daysString.equals("")) {
            lblError.setText("Days is empty");
        } else if (!isInt(txtDays.getText().trim())) {
            lblError.setText("Days can be only number");
        } else if ((priceString.contains(",")) ){///Hocaya sor
            lblError.setText(" Pelase use . instead of ,");
        }else if (priceString.equals("")) {
            lblError.setText("Price is empty");
        } else if (details.equals("")) {
            lblError.setText("Details is empty");
        } else if (row == -1) {
            lblError.setText("Please select a customer from the table above ");
        } else {
            Double price = Double.parseDouble(txtPrice.getText().trim());
            int days = Integer.parseInt(txtDays.getText().trim());
            int cid = (Integer) tblCustomer.getValueAt(row, 0);
            lblError.setText("");
            Service service = new Service(0, cid, title, details, days, null, 0, price);
            return service;
        }

        return null;

    }

    public Service fncValidateService() {
        rowService = tblServices.getSelectedRow();
        String title = txtTitleUpdate.getText().trim();
        String details = txtDetailsUpdate.getText().trim();
        String daysString = txtDaysUpdate.getText().trim();
        String priceString = txtPriceUpdate2.getText().trim();
        String date = txtDeliveryDate.getText().trim();
        int statusCmb = cmbStatus.getSelectedIndex();

       if(statusCmb==1 || statusCmb==0 || statusCmb==2) {
        if (rowService == -1) {
               lblError2.setText("Please select a service from the table above");
           } else if (title.equals("")) {
               lblError2.setText("Title is empty");
           } else if (details.equals("")) {
               lblError2.setText("Details is empty");
           } else if (priceString.equals("")) {
               lblError2.setText("Price is empty");
           } else if (priceString.contains(",")) {
               lblError2.setText("Please use . instead of ,");
           } else if (cmbStatus.getSelectedIndex() == -1) {
               lblError2.setText("Status is empty");
           } else if (daysString.equals("")) {
               lblError2.setText("Days is empty");
           } else if (!isInt(daysString)) {
               lblError2.setText("Days can be only number");
           } else if (!date.equals("")) {
            lblError2.setText("Date must be empty");
        }else{
            lblError2.setText("");

            Double price = Double.parseDouble(priceString);
            int days = Integer.parseInt(daysString);
            int cid = (Integer) tblServices.getValueAt(rowService, 1);
            Service service = new Service(selectedId, cid, title, details, days, date, statusCmb, price);
            return service;
        }
       } else if(statusCmb==3){

           if (date.equals("")) {
               lblError2.setText("Delivery Date is empty");
           }else if(rowService==-1){
               lblError2.setText("Please select a service from the table above");
           }else if (title.equals("")) {
               lblError2.setText("Title is empty");
           } else if (details.equals("")) {
               lblError2.setText("Details is empty");
           } else if (priceString.equals("")) {
               lblError2.setText("Price is empty");
           } else if (priceString.contains(",")) {
               lblError2.setText("Please use . instead of ,");
           } else if (cmbStatus.getSelectedIndex() == -1) {
               lblError2.setText("Status is empty");
           } else if (daysString.equals("")) {
               lblError2.setText("Days is empty");
           } else if (!isInt(daysString)) {
               lblError2.setText("Days can be only number");}
           else{
               lblError2.setText("");

               Double price = Double.parseDouble(priceString);
               int days = Integer.parseInt(daysString);
               int cid = (Integer) tblServices.getValueAt(rowService, 1);
               Service service = new Service(selectedId, cid, title, details, days, date, statusCmb, price);
               return service;}
       } /*else {
           lblError2.setText("");

           Double price = Double.parseDouble(priceString);
           int days = Integer.parseInt(daysString);
           int cid = (Integer) tblServices.getValueAt(rowService, 1);
           Service service = new Service(selectedId, cid, title, details, days, date, statusCmb, price);
           return service;
       }*/

        return null;
    }



        public static boolean isInt (String s){
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        private void tblServicesMouseReleased (MouseEvent e){


            rowService = tblServices.getSelectedRow();
            selectedId = (Integer) tblServices.getValueAt(rowService, 0);
            String title=(String) tblServices.getValueAt(rowService,4);
            txtTitleUpdate.setText(title);
            String details=(String) tblServices.getValueAt(rowService,5);
            txtDetailsUpdate.setText(details);
            int days=(Integer)tblServices.getValueAt(rowService,6);
            String daysString= String.valueOf(days);
            txtDaysUpdate.setText(daysString);
           String date = (String) tblServices.getValueAt(rowService, 7);
            txtDeliveryDate.setText(date);

            String cmb=(String) tblServices.getValueAt(rowService,8);


                cmbStatus.setSelectedIndex(0);

                cmbStatus.setSelectedItem(cmb);

            Double price=(Double)tblServices.getValueAt(rowService,9);
            String priceSting=String.valueOf(price);
            txtPriceUpdate2.setText(priceSting);
    pack();
    }
        DefaultComboBoxModel cmbmodel () {
            String[] statuslist = new String[4];
            statuslist[0] = "0-Just received";
            statuslist[1] = "1-In Repair";
            statuslist[2] = "2-Completed";
            statuslist[3] = "3-Delivered";
            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel<>(statuslist);
            return comboBoxModel;
        }

        private void btnUpdateClik(ActionEvent e) {
            Service service = fncValidateService();
            if (service != null) {
                int status = serviceImpl.serviceUpdate(service);
                if (status > 0) {
                    JOptionPane.showMessageDialog(this, "Update services process is successful");

                    tblServices.setModel(serviceImpl.tablemodelOlustur());
                    textClear();

                }
            }else{
                System.out.println("hfhfhhfhfhf");
            }
        }
        public void textClear(){
        txtPriceUpdate2.setText("");
        txtDeliveryDate.setText("");
        txtDaysUpdate.setText("");
        txtTitleUpdate.setText("");
        txtDetailsUpdate.setText("");
            cmbStatus.setSelectedIndex(0);

         }

         private void btnDelete(ActionEvent e) {
             row =tblServices.getSelectedRow();
             if (row == -1) {
                 lblError2.setText("Please select the services you want to delete from the table.");
             } else {
                 lblError2.setText("");
                 int answer=JOptionPane.showConfirmDialog(this,"Are you sure that you want to delete the service","Delete Process",JOptionPane.YES_NO_OPTION);
                 if(answer==0){
                    serviceImpl.serviceDelete(selectedId);
                     tblServices.setModel(serviceImpl.tablemodelOlustur());

                 }else{ tblServices.clearSelection();
                     textClear();}
             }
         }



                private void txtPriceKeyPressed(KeyEvent e) {
                    char c=e.getKeyChar();
                    if(Character.isLetter(c)){
                        txtPrice.setEditable(false);
                        lblError2.setText("Please enter only number");
                    }else{
                        lblError2.setText("");
                        txtPrice.setEditable(true);
                    }
                }

                private void txtPriceUpdate2KeyPressed(KeyEvent e) {
                    char c=e.getKeyChar();
                    if(Character.isLetter(c)){
                        txtPriceUpdate2.setEditable(false);
                        lblError2.setText("Please enter only number");
                    }else{
                        lblError2.setText("");
                        txtPriceUpdate2.setEditable(true);
                    }
                }

        private void initComponents () {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            tabbedPane1 = new JTabbedPane();
            panel1 = new JPanel();
            txtCustomerSearch = new JTextField();
            panel3 = new JPanel();
            scrollPane1 = new JScrollPane();
            tblCustomer = new JTable();
            panel4 = new JPanel();
            label3 = new JLabel();
            txtTitle = new JTextField();
            label5 = new JLabel();
            txtDetails = new JFormattedTextField();
            label6 = new JLabel();
            txtDays = new JTextField();
            label7 = new JLabel();
            txtPrice = new JTextField();
            lblError = new JLabel();
            btnAdd = new JButton();
            label2 = new JLabel();
            panel2 = new JPanel();
            panel5 = new JPanel();
            label4 = new JLabel();
            txtTitleUpdate = new JTextField();
            label8 = new JLabel();
            scrollPane2 = new JScrollPane();
            txtDetailsUpdate = new JTextArea();
            txtDeliveryDate = new JTextField();
            label11 = new JLabel();
            label12 = new JLabel();
            cmbStatus = new JComboBox();
            txtDaysUpdate = new JTextField();
            label9 = new JLabel();
            label10 = new JLabel();
            txtPriceUpdate2 = new JTextField();
            scrollPane3 = new JScrollPane();
            tblServices = new JTable();
            btnDelete = new JButton();
            btnUpdate = new JButton();
            lblError2 = new JLabel();
            label1 = new JLabel();
            lblname = new JLabel();
            label13 = new JLabel();

            //======== this ========
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setMaximizedBounds(new Rectangle(0, 0, 100, 135));
            setResizable(false);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    thisWindowClosing(e);
                }
            });
            Container contentPane = getContentPane();

            //======== tabbedPane1 ========
            {
                tabbedPane1.setFont(tabbedPane1.getFont().deriveFont(tabbedPane1.getFont().getSize() + 5f));
                tabbedPane1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.gray, Color.lightGray, Color.lightGray));
                tabbedPane1.setForeground(Color.darkGray);
                tabbedPane1.setBackground(Color.white);

                //======== panel1 ========
                {
                    panel1.setMaximumSize(new Dimension(40, 50));
                    panel1.setMinimumSize(new Dimension(800, 635));
                    panel1.setPreferredSize(null);
                    panel1.setBackground(Color.lightGray);

                    //---- txtCustomerSearch ----
                    txtCustomerSearch.setFont(txtCustomerSearch.getFont().deriveFont(txtCustomerSearch.getFont().getSize() + 2f));
                    txtCustomerSearch.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            txtCustomerSearchKeyReleased(e);
                        }
                    });

                    //======== panel3 ========
                    {
                        panel3.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.lightGray, Color.gray, Color.lightGray), "Customer Table", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                            new Font("Segoe UI", Font.BOLD, 15), Color.darkGray));

                        //======== scrollPane1 ========
                        {

                            //---- tblCustomer ----
                            tblCustomer.setFont(tblCustomer.getFont().deriveFont(tblCustomer.getFont().getSize() + 2f));
                            scrollPane1.setViewportView(tblCustomer);
                        }

                        GroupLayout panel3Layout = new GroupLayout(panel3);
                        panel3.setLayout(panel3Layout);
                        panel3Layout.setHorizontalGroup(
                            panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 753, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(14, Short.MAX_VALUE))
                        );
                        panel3Layout.setVerticalGroup(
                            panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                    }

                    //======== panel4 ========
                    {
                        panel4.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.lightGray, Color.gray, Color.lightGray), "Service General Information", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                            new Font("Segoe UI", Font.BOLD, 15), Color.darkGray));
                        panel4.setBackground(Color.lightGray);

                        //---- label3 ----
                        label3.setText("Title :");
                        label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 2f));
                        label3.setForeground(Color.darkGray);

                        //---- txtTitle ----
                        txtTitle.setFont(txtTitle.getFont().deriveFont(txtTitle.getFont().getSize() + 2f));

                        //---- label5 ----
                        label5.setText("Details :");
                        label5.setFont(label5.getFont().deriveFont(label5.getFont().getStyle() | Font.BOLD, label5.getFont().getSize() + 2f));
                        label5.setForeground(Color.darkGray);

                        //---- txtDetails ----
                        txtDetails.setFont(txtDetails.getFont().deriveFont(txtDetails.getFont().getSize() + 2f));

                        //---- label6 ----
                        label6.setText("Days :");
                        label6.setFont(label6.getFont().deriveFont(label6.getFont().getStyle() | Font.BOLD, label6.getFont().getSize() + 2f));
                        label6.setForeground(Color.darkGray);

                        //---- txtDays ----
                        txtDays.setFont(txtDays.getFont().deriveFont(txtDays.getFont().getSize() + 2f));

                        //---- label7 ----
                        label7.setText("Price :");
                        label7.setFont(label7.getFont().deriveFont(label7.getFont().getStyle() | Font.BOLD, label7.getFont().getSize() + 2f));
                        label7.setForeground(Color.darkGray);

                        //---- txtPrice ----
                        txtPrice.setFont(txtPrice.getFont().deriveFont(txtPrice.getFont().getSize() + 2f));
                        txtPrice.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyPressed(KeyEvent e) {
                                txtPriceKeyPressed(e);
                            }
                        });

                        //---- lblError ----
                        lblError.setHorizontalAlignment(SwingConstants.CENTER);
                        lblError.setForeground(Color.red);
                        lblError.setFont(new Font("Segoe UI", Font.BOLD, 16));

                        //---- btnAdd ----
                        btnAdd.setText("Service Add");
                        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 18));
                        btnAdd.setIcon(new ImageIcon(getClass().getResource("/3855641_add_icon.png")));
                        btnAdd.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.gray, Color.gray, null));
                        btnAdd.setBackground(Color.lightGray);
                        btnAdd.addActionListener(e -> btnAddClick(e));

                        GroupLayout panel4Layout = new GroupLayout(panel4);
                        panel4.setLayout(panel4Layout);
                        panel4Layout.setHorizontalGroup(
                            panel4Layout.createParallelGroup()
                                .addGroup(panel4Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel4Layout.createParallelGroup()
                                        .addComponent(label5)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                                    .addGap(22, 22, 22)
                                    .addGroup(panel4Layout.createParallelGroup()
                                        .addGroup(panel4Layout.createSequentialGroup()
                                            .addGap(35, 35, 35)
                                            .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 555, GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap(136, Short.MAX_VALUE))
                                        .addGroup(panel4Layout.createSequentialGroup()
                                            .addGroup(panel4Layout.createParallelGroup()
                                                .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                                                    .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                                    .addComponent(label6, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txtDays, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(49, 49, 49)
                                                    .addComponent(label7, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtDetails, GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE))
                                            .addContainerGap())))
                                .addGroup(panel4Layout.createSequentialGroup()
                                    .addGap(306, 306, 306)
                                    .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 312, Short.MAX_VALUE))
                        );
                        panel4Layout.setVerticalGroup(
                            panel4Layout.createParallelGroup()
                                .addGroup(panel4Layout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtDays, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDetails, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(13, Short.MAX_VALUE))
                        );
                    }

                    //---- label2 ----
                    label2.setText("Search For :  Customer name");
                    label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 3f));
                    label2.setForeground(Color.darkGray);

                    GroupLayout panel1Layout = new GroupLayout(panel1);
                    panel1.setLayout(panel1Layout);
                    panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(panel4, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtCustomerSearch, GroupLayout.PREFERRED_SIZE, 577, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 27, Short.MAX_VALUE)))
                                .addContainerGap())
                    );
                    panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCustomerSearch, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(24, Short.MAX_VALUE))
                    );
                }
                tabbedPane1.addTab("New", panel1);

                //======== panel2 ========
                {

                    //======== panel5 ========
                    {
                        panel5.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.gray, Color.gray, Color.lightGray), "Service Information", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                            new Font("Segoe UI", Font.BOLD, 15), Color.darkGray));
                        panel5.setBackground(Color.lightGray);

                        //---- label4 ----
                        label4.setText("Title:");
                        label4.setForeground(Color.darkGray);
                        label4.setFont(new Font("Segoe UI", Font.BOLD, 14));

                        //---- txtTitleUpdate ----
                        txtTitleUpdate.setFont(txtTitleUpdate.getFont().deriveFont(txtTitleUpdate.getFont().getSize() + 2f));

                        //---- label8 ----
                        label8.setText("Details");
                        label8.setFont(new Font("Segoe UI", Font.BOLD, 14));
                        label8.setForeground(Color.darkGray);

                        //======== scrollPane2 ========
                        {

                            //---- txtDetailsUpdate ----
                            txtDetailsUpdate.setFont(txtDetailsUpdate.getFont().deriveFont(txtDetailsUpdate.getFont().getSize() + 2f));
                            scrollPane2.setViewportView(txtDetailsUpdate);
                        }

                        //---- txtDeliveryDate ----
                        txtDeliveryDate.setFont(txtDeliveryDate.getFont().deriveFont(txtDeliveryDate.getFont().getSize() + 2f));

                        //---- label11 ----
                        label11.setText("Delivery Date");
                        label11.setFont(new Font("Segoe UI", Font.BOLD, 14));
                        label11.setForeground(Color.darkGray);

                        //---- label12 ----
                        label12.setText("Status :");
                        label12.setFont(new Font("Segoe UI", Font.BOLD, 14));
                        label12.setForeground(Color.darkGray);

                        //---- cmbStatus ----
                        cmbStatus.setFont(cmbStatus.getFont().deriveFont(cmbStatus.getFont().getSize() + 2f));

                        //---- txtDaysUpdate ----
                        txtDaysUpdate.setFont(txtDaysUpdate.getFont().deriveFont(txtDaysUpdate.getFont().getSize() + 2f));

                        //---- label9 ----
                        label9.setText("Days :");
                        label9.setFont(new Font("Segoe UI", Font.BOLD, 14));
                        label9.setForeground(Color.darkGray);

                        //---- label10 ----
                        label10.setText("Price :");
                        label10.setFont(new Font("Segoe UI", Font.BOLD, 14));
                        label10.setForeground(Color.darkGray);

                        //---- txtPriceUpdate2 ----
                        txtPriceUpdate2.setFont(txtPriceUpdate2.getFont().deriveFont(txtPriceUpdate2.getFont().getSize() + 2f));
                        txtPriceUpdate2.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyPressed(KeyEvent e) {
                                txtPriceUpdate2KeyPressed(e);
                            }
                        });

                        GroupLayout panel5Layout = new GroupLayout(panel5);
                        panel5.setLayout(panel5Layout);
                        panel5Layout.setHorizontalGroup(
                            panel5Layout.createParallelGroup()
                                .addGroup(panel5Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel5Layout.createParallelGroup()
                                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label11, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(panel5Layout.createParallelGroup()
                                        .addGroup(panel5Layout.createSequentialGroup()
                                            .addComponent(txtDeliveryDate, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(label12, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(cmbStatus, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
                                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                                        .addGroup(panel5Layout.createSequentialGroup()
                                            .addComponent(txtTitleUpdate, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(label9, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                            .addGap(28, 28, 28)
                                            .addComponent(txtDaysUpdate, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                            .addComponent(label10, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtPriceUpdate2, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
                                    .addGap(21, 21, 21))
                        );
                        panel5Layout.setVerticalGroup(
                            panel5Layout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addGroup(panel5Layout.createParallelGroup()
                                        .addGroup(panel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtTitleUpdate, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                            .addComponent(label9, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtPriceUpdate2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label10, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDaysUpdate, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(panel5Layout.createParallelGroup()
                                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scrollPane2))
                                    .addGap(18, 18, Short.MAX_VALUE)
                                    .addGroup(panel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label11, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDeliveryDate, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label12, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbStatus, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                                    .addGap(17, 17, 17))
                        );
                    }

                    //======== scrollPane3 ========
                    {

                        //---- tblServices ----
                        tblServices.setFont(tblServices.getFont().deriveFont(tblServices.getFont().getSize() + 2f));
                        tblServices.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseReleased(MouseEvent e) {
                                tblServicesMouseReleased(e);
                            }
                        });
                        scrollPane3.setViewportView(tblServices);
                    }

                    //---- btnDelete ----
                    btnDelete.setText("Delete");
                    btnDelete.setFont(btnDelete.getFont().deriveFont(btnDelete.getFont().getStyle() | Font.BOLD, btnDelete.getFont().getSize() + 6f));
                    btnDelete.setForeground(Color.darkGray);
                    btnDelete.setIcon(new ImageIcon(getClass().getResource("/3855611_bin_garbage_trash_icon.png")));
                    btnDelete.setBackground(new Color(204, 204, 204));
                    btnDelete.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.gray, Color.gray, Color.lightGray));
                    btnDelete.addActionListener(e -> btnDelete(e));

                    //---- btnUpdate ----
                    btnUpdate.setText("Update");
                    btnUpdate.setFont(btnUpdate.getFont().deriveFont(btnUpdate.getFont().getStyle() | Font.BOLD, btnUpdate.getFont().getSize() + 6f));
                    btnUpdate.setForeground(Color.darkGray);
                    btnUpdate.setIcon(new ImageIcon(getClass().getResource("/uptadeiconcustomer.png")));
                    btnUpdate.setBackground(new Color(204, 204, 204));
                    btnUpdate.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.gray, Color.gray, Color.lightGray));
                    btnUpdate.addActionListener(e -> btnUpdateClik(e));

                    //---- lblError2 ----
                    lblError2.setHorizontalAlignment(SwingConstants.CENTER);
                    lblError2.setForeground(Color.red);
                    lblError2.setFont(new Font("Segoe UI", Font.BOLD, 16));

                    GroupLayout panel2Layout = new GroupLayout(panel2);
                    panel2.setLayout(panel2Layout);
                    panel2Layout.setHorizontalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                .addContainerGap(143, Short.MAX_VALUE)
                                .addComponent(lblError2, GroupLayout.PREFERRED_SIZE, 555, GroupLayout.PREFERRED_SIZE)
                                .addGap(126, 126, 126))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(panel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 8, Short.MAX_VALUE))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(227, 227, 227)
                                        .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                                        .addGap(77, 77, 77)
                                        .addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(16, Short.MAX_VALUE))
                    );
                    panel2Layout.setVerticalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(panel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblError2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                    .addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                                .addContainerGap(21, Short.MAX_VALUE))
                    );
                }
                tabbedPane1.addTab("Update-Delete", panel2);
            }

            //---- label1 ----
            label1.setText("Tecnical Services Managmet");
            label1.setFont(new Font("Segoe UI", Font.BOLD, 18));
            label1.setForeground(Color.darkGray);

            //---- lblname ----
            lblname.setText("text");
            lblname.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblname.setHorizontalAlignment(SwingConstants.RIGHT);
            lblname.setBorder(null);
            lblname.setForeground(Color.darkGray);

            //---- label13 ----
            label13.setText("text");
            label13.setIcon(new ImageIcon(getClass().getResource("/settings.png")));

            GroupLayout contentPaneLayout = new GroupLayout(contentPane);
            contentPane.setLayout(contentPaneLayout);
            contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(label13, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97)
                                .addComponent(lblname, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
                            .addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 828, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(20, Short.MAX_VALUE))
            );
            contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label13)
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblname, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 543, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
            );
            pack();
            setLocationRelativeTo(getOwner());
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        private JTabbedPane tabbedPane1;
        private JPanel panel1;
        private JTextField txtCustomerSearch;
        private JPanel panel3;
        private JScrollPane scrollPane1;
        private JTable tblCustomer;
        private JPanel panel4;
        private JLabel label3;
        private JTextField txtTitle;
        private JLabel label5;
        private JFormattedTextField txtDetails;
        private JLabel label6;
        private JTextField txtDays;
        private JLabel label7;
        private JTextField txtPrice;
        private JLabel lblError;
        private JButton btnAdd;
        private JLabel label2;
        private JPanel panel2;
        private JPanel panel5;
        private JLabel label4;
        private JTextField txtTitleUpdate;
        private JLabel label8;
        private JScrollPane scrollPane2;
        private JTextArea txtDetailsUpdate;
        private JTextField txtDeliveryDate;
        private JLabel label11;
        private JLabel label12;
        private JComboBox cmbStatus;
        private JTextField txtDaysUpdate;
        private JLabel label9;
        private JLabel label10;
        private JTextField txtPriceUpdate2;
        private JScrollPane scrollPane3;
        private JTable tblServices;
        private JButton btnDelete;
        private JButton btnUpdate;
        private JLabel lblError2;
        private JLabel label1;
        private JLabel lblname;
        private JLabel label13;
        // JFormDesigner - End of variables declaration  //GEN-END:variables

}
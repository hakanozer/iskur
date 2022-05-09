/*
 * Created by JFormDesigner on Thu Apr 07 18:52:15 TRT 2022
 */

package views;

import javax.swing.border.*;

import models.DasbordImpl;
import models.ServiceImpl;
import models.UserImpl;
import props.Customer;
import props.Service;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

/**
 * @author unknown
 */
public class ServicesAdd extends Base {
    int row=-1;
    int selectedId=0;

    Dashboard dashbord=new Dashboard()
    DasbordImpl dasbordImpl=new DasbordImpl();
    ServiceImpl serviceImpl=new ServiceImpl();
    Service service=new Service();
    public ServicesAdd() {
        initComponents();
        lblName.setText("Sayın "+ UserImpl.name);
        tblServiceCustomer.setModel(serviceImpl.serviceCustomerTablo(null));
        dashbord.tblNotCompleted.setModel(dasbordImpl.customerModel());
    }



    private void thisWindowClosing(WindowEvent e) {
        Dashbord dashbord=new Dashbord();
        dashbord.setVisible(true);
    }

    private void btnCustomerUpdateClicked(ActionEvent e) {
        // TODO add your code here
    }

    private void btnCustomerDeleteClicked(ActionEvent e) {
        // TODO add your code here
    }

    private void txtSearchKeyReleased(KeyEvent e) {
        String txtSearch= txtCustomerSearch.getText().trim();

        tblServiceCustomer.setModel(serviceImpl.serviceCustomerTablo(txtSearch));
    }


    public void rowValue(){
        int column=0;
        row=tblServiceCustomer.getSelectedRow();
        selectedId= (int) tblServiceCustomer.getValueAt(row,column);//seçili id'yi yakalama

        String cid= String.valueOf(tblServiceCustomer.getValueAt(row,0));  //cast ettik obje olduğu için.
        String name= String.valueOf(tblServiceCustomer.getValueAt(row,1));
        String surname= String.valueOf(tblServiceCustomer.getValueAt(row,2));
        String email= String.valueOf(tblServiceCustomer.getValueAt(row,3));
        String phone= String.valueOf(tblServiceCustomer.getValueAt(row,4));
        String address= String.valueOf(tblServiceCustomer.getValueAt(row,5));
        System.out.println("Selected index: "+selectedId);

    }

    public Service fncDataValid(){
        int sid=service.getSid();
        int cid=service.getCid();
        String date=service.getDate();
        int status=service.getStatus();
        String title=txtTitle.getText().trim();
        String info=txtInfo.getText().trim();
        int days= Integer.parseInt(txtDays.getText().trim());
        int price= Integer.parseInt(txtPrice.getText().trim());


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
        }else {
            lblError.setText("");
            Service service=new Service(0,selectedId,title,info,days,date,price,status);

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


    private void btnServicesAddClicked(ActionEvent e) {
        Service service=fncDataValid();
        if(service!=null){
            int status=serviceImpl.serviceInsert(service);
            if(status>0){
                System.out.println("Ekleme Başarılı");
                dashbord.tblNotCompleted.setModel(dasbordImpl.customerModel());
            }
        }
    }



    private void tblServiceCustomerMouseClicked(MouseEvent e) {
        rowValue();
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
        btnDeleteUpdate = new JButton();
        lblError = new JLabel();
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
            txtCustomerSearch.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txtSearchKeyReleased(e);
                }
            });

            //======== scrollPane1 ========
            {

                //---- tblServiceCustomer ----
                tblServiceCustomer.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblServiceCustomerMouseClicked(e);
                    }
                });
                tblServiceCustomer.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        tblServiceCustomerKeyReleased(e);
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
                btnServiceUpdate.addActionListener(e -> btnCustomerUpdateClicked(e));

                //---- btnDeleteUpdate ----
                btnDeleteUpdate.setBackground(Color.lightGray);
                btnDeleteUpdate.setForeground(Color.white);
                btnDeleteUpdate.setIcon(new ImageIcon(getClass().getResource("/btnDeleteIcon.png")));
                btnDeleteUpdate.setBorder(null);
                btnDeleteUpdate.setToolTipText("DELETE");
                btnDeleteUpdate.addActionListener(e -> btnCustomerDeleteClicked(e));

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                        .addGroup(panel1Layout.createParallelGroup()
                                                .addGroup(panel1Layout.createSequentialGroup()
                                                        .addGap(6, 6, 6)
                                                        .addComponent(btnServicesAdd, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(btnServiceUpdate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(btnDeleteUpdate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(panel1Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(label12, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(panel1Layout.createSequentialGroup()
                                        .addGroup(panel1Layout.createParallelGroup()
                                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(txtDays, GroupLayout.Alignment.LEADING)
                                                        .addComponent(label11, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtTitle, GroupLayout.Alignment.LEADING)
                                                        .addGroup(panel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(label9, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(label10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                                .addGroup(panel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(label9, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(label10, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(label11, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDays, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label12, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panel1Layout.createParallelGroup()
                                                .addComponent(btnServicesAdd, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnServiceUpdate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnDeleteUpdate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                                        .addGap(25, 25, 25))
                );
            }

            //---- lblError ----
            lblError.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblError.setForeground(new Color(245, 6, 6));
            lblError.setText(" ");

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                    .addGroup(panel2Layout.createParallelGroup()
                                            .addComponent(txtCustomerSearch, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                                            .addComponent(label8, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                                            .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                                            .addComponent(lblError, GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
            );
            panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                    .addComponent(label8, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(txtCustomerSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE)))
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
                                .addGap(12, 12, 12)
                                .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private JButton btnDeleteUpdate;
    private JLabel lblError;
    private JLabel label6;
    private JLabel label7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

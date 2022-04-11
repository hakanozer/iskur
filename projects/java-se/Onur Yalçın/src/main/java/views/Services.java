/*
 * Created by JFormDesigner on Thu Apr 07 18:52:12 TRT 2022
 */

package views;

import model.ServiceImpl;
import model.UserImpl;
import props.Service;

import java.awt.*;
import java.awt.event.*;
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
        tblCustomer.setModel(service.serviceCustomerTable(null));
    }

    private Service fncDatavalidate() {
        Service s = null;
        int row = tblCustomer.getSelectedRow(); //hangi satırın seçildiğini burda yakalıyoruz.
        int cid = (Integer) tblCustomer.getValueAt(row, 1); //getValueAt hep obje döndürür, Parantez içinde Integer yazarak Objeden int'e çevirdik.
        String title = txtTitle.getText().toLowerCase(Locale.ROOT).trim();
        String days = txtDays.getText().toLowerCase(Locale.ROOT).trim();
        String detail = txtDetail.getText().toLowerCase(Locale.ROOT).trim();
        String price = txtPrice.getText().toLowerCase(Locale.ROOT).trim();
        if (row == -1) {
            lblError.setText("You must select a customer from Customer Table");
        } else if (title.equals("")) {
            txtTitle.requestFocus();
            lblError.setText("Title is empty");
        } else if (days.equals("")) {
            txtDays.requestFocus();
            lblError.setText("Days is empty");
        } else if (detail.equals("")) {
            txtDetail.requestFocus();
            lblError.setText("Detail is empty");
        } else if (price.equals("")) {
            txtDetail.requestFocus();
            lblError.setText("Price is empty");
        } else {
            s = new Service(0, 0, null, null, 0, null, 0, 0);

        }
        return s;
    }

    private void thisWindowClosing(WindowEvent e) {
        // TODO add your code here
        new Dashboard().setVisible(true);
    }

    private void searchKeyReleased(KeyEvent e) {
        // TODO add your code here
        String searchCustomer=txtSearch.getText().trim().toLowerCase(Locale.ROOT);
        tblCustomer.setModel(service.serviceCustomerTable(searchCustomer));

    }

    private void btnAddClickService(MouseEvent e) {
        ServiceImpl si = new ServiceImpl();
        Service s = fncDatavalidate();
        if(s != null){
            int status = si.serviceInsert(s);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        lblName = new JLabel();
        label4 = new JLabel();
        txtSearch = new JTextField();
        scrollPane1 = new JScrollPane();
        tblCustomer = new JTable();
        panel1 = new JPanel();
        label5 = new JLabel();
        txtTitle = new JTextField();
        txtDays = new JTextField();
        label6 = new JLabel();
        label7 = new JLabel();
        scrollPane2 = new JScrollPane();
        txtDetail = new JTextArea();
        lblError = new JLabel();
        btnAdd = new JButton();
        txtPrice = new JTextField();
        lblPricce = new JLabel();

        //======== this ========
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
            scrollPane1.setViewportView(tblCustomer);
        }

        //======== panel1 ========
        {

            //---- label5 ----
            label5.setText("Title :");

            //---- label6 ----
            label6.setText("Days :");

            //---- label7 ----
            label7.setText("Detail :");

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(txtDetail);
            }

            //---- lblError ----
            lblError.setForeground(new Color(255, 51, 51));

            //---- btnAdd ----
            btnAdd.setText("Add");
            btnAdd.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnAddClickService(e);
                }
            });

            //---- lblPricce ----
            lblPricce.setText("Price");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addComponent(btnAdd, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label7)
                                    .addComponent(label5))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(lblError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                        .addComponent(txtTitle, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(label6)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDays, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(scrollPane2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(lblPricce, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 395, Short.MAX_VALUE)))
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap(38, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addComponent(label6)
                                .addGap(14, 14, 14))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label5)
                                    .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(txtDays, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label7)
                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPricce)
                            .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36)
                            .addComponent(lblName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label4)
                            .addGap(18, 18, 18)
                            .addComponent(txtSearch))
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane1))
                    .addContainerGap())
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
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
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
    private JPanel panel1;
    private JLabel label5;
    private JTextField txtTitle;
    private JTextField txtDays;
    private JLabel label6;
    private JLabel label7;
    private JScrollPane scrollPane2;
    private JTextArea txtDetail;
    private JLabel lblError;
    private JButton btnAdd;
    private JTextField txtPrice;
    private JLabel lblPricce;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

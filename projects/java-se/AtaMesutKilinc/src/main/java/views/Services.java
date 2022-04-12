/*
 * Created by JFormDesigner on Thu Apr 07 18:52:08 TRT 2022
 */

package views;

import models.ServiceImpl;
import models.UserImpl;
import props.Customer;
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
    ServiceImpl serviceImpl=new ServiceImpl();
    DefaultComboBoxModel modelCombo= new DefaultComboBoxModel<>();
    String[] cboxitems ={"Product Just Arrived","Product In Repair","Product Has Been Repaired","Product Delivered"};
    int row=-1;
    int rowService=-1;
    int value; //cid
    int serviceValueSid; //sid
    int serviceValueCid; //sid

    public Services() {
        initComponents();
        lblName.setText("Sn "+ UserImpl.name);
        fncComboDataResult();
        cmbStatus.setSelectedItem(0);
        tblCustomer.setModel(serviceImpl.serviceCustomerTable(null));
        tblService.setModel(serviceImpl.serviceTable(0));

    }



    public int rowValue(){
        int column = 0; //1. kolondakini al.
        row = tblCustomer.getSelectedRow(); //seçili olan row u getir.  //dizi elemanı gibi 0 dan başlar row
        value = (int) tblCustomer.getModel().getValueAt(row, column);
        String cid= String.valueOf(tblCustomer.getValueAt(row,0));  //cast ettik obje olduğu için.
        String name= String.valueOf(tblCustomer.getValueAt(row,1));
        String surname= String.valueOf(tblCustomer.getValueAt(row,2));
        String email= String.valueOf(tblCustomer.getValueAt(row,3));
        String phone= String.valueOf(tblCustomer.getValueAt(row,4));
        String address= String.valueOf(tblCustomer.getValueAt(row,5));
        System.out.println("val "+ value);
        cmbStatus.setSelectedIndex(0);
        return value;


    }

    public int rowValueService(int colomn){

        int column = colomn; //1. kolondakini al.
        rowService = tblService.getSelectedRow(); //seçili olan row u getir.  //dizi elemanı gibi 0 dan başlar row
        serviceValueSid =  (int) tblService.getModel().getValueAt(rowService, column);
        String sid= String.valueOf(tblService.getValueAt(rowService,0));
        String name= String.valueOf(tblService.getValueAt(rowService,1));  //cast ettik obje olduğu için.
        String surname= String.valueOf(tblService.getValueAt(rowService,2));
        String email= String.valueOf(tblService.getValueAt(rowService,3));
        String phone= String.valueOf(tblService.getValueAt(rowService,4));
        String cid= String.valueOf(tblService.getValueAt(rowService,5));
        String title= String.valueOf(tblService.getValueAt(rowService,6));
        String info= String.valueOf(tblService.getValueAt(rowService,7));
        String days= String.valueOf(tblService.getValueAt(rowService,8));
        String date= String.valueOf(tblService.getValueAt(rowService,9));
        String status= String.valueOf(tblService.getValueAt(rowService,10));
        String price= String.valueOf(tblService.getValueAt(rowService,11));
        System.out.println("sid"+ serviceValueSid);


        txtTitle.setText(title);
        txtDetails.setText(info);
        txtPrice.setText(price);
        txtDays.setText(days);
        cmbStatus.setSelectedItem(status);
        return serviceValueSid;


    }

    public int rowValueServiceCid(int colomn){
        int column = colomn; //1. kolondakini al.
        rowService = tblService.getSelectedRow(); //seçili olan row u getir.  //dizi elemanı gibi 0 dan başlar row
        serviceValueCid =  (int) tblService.getModel().getValueAt(rowService, column);
        String sid= String.valueOf(tblService.getValueAt(rowService,0));
        String name= String.valueOf(tblService.getValueAt(rowService,1));  //cast ettik obje olduğu için.
        String surname= String.valueOf(tblService.getValueAt(rowService,2));
        String email= String.valueOf(tblService.getValueAt(rowService,3));
        String phone= String.valueOf(tblService.getValueAt(rowService,4));
        String cid= String.valueOf(tblService.getValueAt(rowService,5));
        String title= String.valueOf(tblService.getValueAt(rowService,6));
        String info= String.valueOf(tblService.getValueAt(rowService,7));
        String days= String.valueOf(tblService.getValueAt(rowService,8));
        String date= String.valueOf(tblService.getValueAt(rowService,9));
        String status= String.valueOf(tblService.getValueAt(rowService,10));
        String price= String.valueOf(tblService.getValueAt(rowService,11));
        System.out.println("cid"+ serviceValueCid);



//        int s= (int) cmbStatus.getModel().getSelectedItem(cboxitems);
        int statusCmb = cmbStatus.getSelectedIndex();
        System.out.println(statusCmb);
        txtTitle.setText(title);
        txtDetails.setText(info);
        txtPrice.setText(price);
        txtDays.setText(days);
        cmbStatus.setSelectedIndex(statusCmb);
//        cbxStatus.setSelectedItem();
        return serviceValueCid;


    }

    private Service fncDataValid(){
        //veriler.

//            int cid= rowValue();
//         int sid=rowValueService(0);
//         int sCid=  rowValueService(4);
        String title= txtTitle.getText().toLowerCase(Locale.ROOT).trim();
        String info= txtDetails.getText().toLowerCase(Locale.ROOT).trim();
        int price= Integer.parseInt(txtPrice.getText().toLowerCase(Locale.ROOT).trim());
        int days= Integer.parseInt(txtDays.getText().toLowerCase(Locale.ROOT).trim());
        String date=Base.Date();
        int statusCmb = cmbStatus.getSelectedIndex();



        if (title.equals("")){
            lblCustomerError.setText("Title is Empty!!!");
            txtTitle.requestFocus();
        }else if (info.equals("")){
            lblCustomerError.setText("Details is Empty!!!");
            txtDetails.requestFocus();
        }
//        else if (price !=null){
//            lblCustomerError.setText("Price is Empty!!!");
//            txtPrice.requestFocus();
//        }

//        else if (days==null)){ //boşşa sıfırsa
//            lblCustomerError.setText("Days is Empty!!!");
//            txtDays.requestFocus();//imleç otomatik olarak passwworde gelicek
//        }
          else {
            lblCustomerError.setText("");

            Service service=new Service(0,value,title,info,days,date,0,price);
            System.out.println("fnc : "+value);

            return service;//iş yolunda ise customeri dön
        }
        return null; //eğer customer boşssa null dönecek

    }

    private void fncComboDataResult(){
        //bu fonk tetiklendiğinde ilgili modelin içi datayla dolsun.
       // modelCombo.addElement("Select Status");  //default değeri
        for (int i = 0; i < cboxitems.length; i++) {
            String item= cboxitems[i];
            modelCombo.addElement(item);


        }

        cmbStatus.setModel(modelCombo); //combobox içine modeli attık


    }

    private void btnAddClick(ActionEvent e) {
        cmbStatus.setSelectedItem(0);
        Service service=fncDataValid();

           if(service !=null && value !=0){
           int status=serviceImpl.serviceInsert(service);
               System.out.println("Ekleme "+status);
            if (status>0){

                tblService.setModel(serviceImpl.serviceTable(0));
                System.out.println("Servis ekleme başarılı");
                txtTitle.setText("");
                txtDays.setText("");
                txtDetails.setText("");
                txtPrice.setText("");
////
//            }else {
//                //buraya düştüyse hata var. -1 ise email unique değil
//                if (status==-1){
//                    lblCustomerError.setText("Email or Phone number already exists.");
//                }else{
//                    lblCustomerError.setText("Insert Error.");
                }
//
            }
           else{

               System.out.println("service boş");
               lblCustomerError.setText("");
        }

    }

    private void btnUpdateClick(ActionEvent e) {
        String title= txtTitle.getText().toLowerCase(Locale.ROOT).trim();
        String info= txtDetails.getText().toLowerCase(Locale.ROOT).trim();
        int price= Integer.parseInt(txtPrice.getText().toLowerCase(Locale.ROOT).trim());
        int days= Integer.parseInt(txtDays.getText().toLowerCase(Locale.ROOT).trim());
        int status= cmbStatus.getSelectedIndex();


        Service service=fncDataValid();

        service.setSid(serviceValueSid);
        service.setCid(serviceValueCid);
        service.setTitle(title);
        service.setInfo(info);
        service.setPrice(price);
        service.setDays(days);
        service.setStatus(status);
        //TODO: combobox
        if (serviceValueSid!=-1){
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to update the customer?","Update Window",JOptionPane.YES_OPTION);//parent component nerede görüneceği this button

            if (answer==0){

                serviceImpl.serviceUpdate(service);
                tblService.setModel(serviceImpl.serviceTable(0)); //tabloyu refresh et
//                System.out.println(row+" update");
                txtTitle.setText("");
                txtDetails.setText("");
                txtPrice.setText("");
                txtDays.setText("");
//            <    txtAddress.setText("");>
                serviceValueSid=-1;

            }
        }
        else{
            JOptionPane.showMessageDialog(this,"Please choose."); //this kendini burada ortala
            //show confirm anlaşmayı kabul etmek istiyor musun.
        }



    }

    private void btnDeleteClick(ActionEvent e) {
        if (serviceValueSid !=-1){
            int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to delete the service?","Delete Window",JOptionPane.YES_OPTION);//parent component nerede görüneceği this button
            System.out.println(answer); //butonların sırası soldan başlayarak 0 1 buton sırası öyle belirlenir.

            if (answer==0){
                serviceImpl.serviceDelete(serviceValueSid);
                tblService.setModel(serviceImpl.serviceTable(0)); //tabloyu refresh et
                txtTitle.setText("");
                txtDetails.setText("");
                txtPrice.setText("");
                txtDays.setText("");
                serviceValueSid=-1;
            }
        }

        else{
            JOptionPane.showMessageDialog(this,"Please choose."); //this kendini burada ortala
            //show confirm anlaşmayı kabul etmek istiyor musun.
        }
    }

    private void thisWindowClosing(WindowEvent e) {
        new Dashboard().setVisible(true);
    }

    private void txtSearchKeyReleased(KeyEvent e) {

        String txtSearch=txtSearchCustomer.getText().toLowerCase(Locale.ROOT).trim();
        tblCustomer.setModel(serviceImpl.serviceCustomerTable(txtSearch));


    }

    private void tblCustomerKeyReleased(KeyEvent e) {
        rowValue();
        txtTitle.setText("");
        txtDetails.setText("");
        txtPrice.setText("");
        txtDays.setText("");


    }

    private void tblCustomerMouseClicked(MouseEvent e) {
        rowValue();
        txtTitle.setText("");
        txtDetails.setText("");
        txtPrice.setText("");
        txtDays.setText("");

    }

    private void tblServiceKeyReleased(KeyEvent e) {

        rowValueService(0);
        rowValueServiceCid(5);


    }

    private void tblServiceMouseClicked(MouseEvent e) {
        rowValueService(0);
        rowValueServiceCid(5);


    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblName = new JLabel();
        label3 = new JLabel();
        txtSearchCustomer = new JTextField();
        scrollPane1 = new JScrollPane();
        tblCustomer = new JTable();
        panel1 = new JPanel();
        label4 = new JLabel();
        txtTitle = new JTextField();
        txtDays = new JTextField();
        label5 = new JLabel();
        label6 = new JLabel();
        scrollPane2 = new JScrollPane();
        txtDetails = new JTextArea();
        btnAdd = new JButton();
        lblCustomerError = new JLabel();
        btnUpdate = new JButton();
        btnDelete = new JButton();
        label2 = new JLabel();
        txtPrice = new JTextField();
        scrollPane3 = new JScrollPane();
        tblService = new JTable();
        cmbStatus = new JComboBox();
        label7 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Services ");
        setResizable(false);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();

        //---- lblName ----
        lblName.setText(" ");
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);

        //---- label3 ----
        label3.setText("Customer Search");

        //---- txtSearchCustomer ----
        txtSearchCustomer.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtSearchKeyReleased(e);
            }
        });

        //======== scrollPane1 ========
        {

            //---- tblCustomer ----
            tblCustomer.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    tblCustomerKeyReleased(e);
                }
            });
            tblCustomer.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tblCustomerMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(tblCustomer);
        }

        //======== panel1 ========
        {

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
            btnAdd.setIcon(new ImageIcon(getClass().getResource("/addBtnn.png")));
            btnAdd.setToolTipText("Add");
            btnAdd.setFocusable(false);
            btnAdd.addActionListener(e -> btnAddClick(e));

            //---- lblCustomerError ----
            lblCustomerError.setFont(new Font("Arial", Font.PLAIN, 14));

            //---- btnUpdate ----
            btnUpdate.setIcon(new ImageIcon(getClass().getResource("/updateBtn.png")));
            btnUpdate.setToolTipText("Update");
            btnUpdate.setFocusable(false);
            btnUpdate.addActionListener(e -> btnUpdateClick(e));

            //---- btnDelete ----
            btnDelete.setIcon(new ImageIcon(getClass().getResource("/deleteBtn.png")));
            btnDelete.setToolTipText("Delete");
            btnDelete.setFocusable(false);
            btnDelete.addActionListener(e -> btnDeleteClick(e));

            //---- label2 ----
            label2.setText("Price");

            //======== scrollPane3 ========
            {

                //---- tblService ----
                tblService.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        tblServiceKeyReleased(e);
                    }
                });
                tblService.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblServiceMouseClicked(e);
                    }
                });
                scrollPane3.setViewportView(tblService);
            }

            //---- label7 ----
            label7.setText("Status");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label6, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(label5, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                        .addComponent(label2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(label7, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblCustomerError, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(cmbStatus, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(txtDays, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(txtPrice, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 971, Short.MAX_VALUE)
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDays, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblCustomerError, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAdd)
                            .addComponent(btnUpdate)
                            .addComponent(btnDelete))
                        .addGap(8, 8, 8))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                        .addContainerGap())
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
                            .addComponent(label3)
                            .addGap(18, 18, 18)
                            .addComponent(txtSearchCustomer, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 544, Short.MAX_VALUE)
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE))
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane1))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearchCustomer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                    .addGap(34, 34, 34)
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblName;
    private JLabel label3;
    private JTextField txtSearchCustomer;
    private JScrollPane scrollPane1;
    private JTable tblCustomer;
    private JPanel panel1;
    private JLabel label4;
    private JTextField txtTitle;
    private JTextField txtDays;
    private JLabel label5;
    private JLabel label6;
    private JScrollPane scrollPane2;
    private JTextArea txtDetails;
    private JButton btnAdd;
    private JLabel lblCustomerError;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JLabel label2;
    private JTextField txtPrice;
    private JScrollPane scrollPane3;
    private JTable tblService;
    private JComboBox cmbStatus;
    private JLabel label7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

/*
 * Created by JFormDesigner on Wed Apr 06 18:22:08 TRT 2022
 */

package views;

import java.awt.event.*;
import javax.swing.border.*;

import models.ServiceImpl;
import models.UserImpl;

import java.awt.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Dashboard extends Base {
    ServiceImpl sI=new ServiceImpl();
    public Dashboard() {
        initComponents();
        lblname.setText("Sn "+ UserImpl.name);
        tblRepair.setModel(sI.serviceModelInrepair(null));
        tblCompleted.setModel(sI.serviceModelCompleted(null));

    }

    private void button1AddCustomerClick(ActionEvent e) {
        CustomerAdd customerAdd=new CustomerAdd();
        customerAdd.setVisible(true);
        dispose();

    }

    private void btnServiceClick(ActionEvent e) {
      Services s=new Services();
      s.setVisible(true);
      dispose();
    }



    private void bntArchieveClick(ActionEvent e) {
       Archieve a=new Archieve();
       a.setVisible(true);
       dispose();;
    }

    private void txtSearchKeyReleased(KeyEvent e) {
        String searcData=txtSearch.getText().toLowerCase().trim();
       tblRepair.setModel(sI.serviceModelInrepair(searcData));
        tblCompleted.setModel(sI.serviceModelCompleted(searcData));
    }

    private void thisWindowClosed(WindowEvent e) {


    }

    private void thisWindowClosing(WindowEvent e) {

       JOptionPane.getRootFrame().setSize(100,100);
       int answer=JOptionPane.showConfirmDialog(this,"Are you sure you want to exit the program?",
               "Exit Confirm",JOptionPane.YES_NO_OPTION);

        if(answer==0){
           dispose();
        }
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblname = new JLabel();
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblRepair = new JTable();
        panel2 = new JPanel();
        scrollPane2 = new JScrollPane();
        tblCompleted = new JTable();
        txtSearch = new JTextField();
        label2 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setMaximizedBounds(new Rectangle(0, 0, 100, 135));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();

        //---- lblname ----
        lblname.setText("text");
        lblname.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblname.setHorizontalAlignment(SwingConstants.RIGHT);
        lblname.setBorder(null);
        lblname.setForeground(Color.darkGray);

        //---- label1 ----
        label1.setText("Technical Service ");
        label1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        label1.setForeground(Color.darkGray);

        //---- button1 ----
        button1.setIcon(new ImageIcon(getClass().getResource("/CustomerAdd.png")));
        button1.setBackground(Color.lightGray);
        button1.setToolTipText("M\u00fc\u015fteri Ekle");
        button1.setFocusable(false);
        button1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.lightGray, Color.gray, Color.lightGray));
        button1.addActionListener(e -> button1AddCustomerClick(e));

        //---- button2 ----
        button2.setIcon(new ImageIcon(getClass().getResource("/settings.png")));
        button2.setBackground(Color.lightGray);
        button2.setToolTipText("Servis Ekle");
        button2.setFocusable(false);
        button2.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.lightGray, Color.gray, Color.lightGray));
        button2.addActionListener(e -> btnServiceClick(e));

        //---- button3 ----
        button3.setIcon(new ImageIcon(getClass().getResource("/folder.png")));
        button3.setBackground(Color.lightGray);
        button3.setToolTipText("Ar\u015fiv");
        button3.setFocusable(false);
        button3.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.lightGray, Color.gray, Color.lightGray));
        button3.addActionListener(e -> bntArchieveClick(e));

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.lightGray, Color.gray, Color.lightGray), "Open Work Orders", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 14), Color.darkGray));
            panel1.setBackground(Color.lightGray);

            //======== scrollPane1 ========
            {

                //---- tblRepair ----
                tblRepair.setFont(tblRepair.getFont().deriveFont(tblRepair.getFont().getSize() + 2f));
                scrollPane1.setViewportView(tblRepair);
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(scrollPane1)
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
            );
        }

        //======== panel2 ========
        {
            panel2.setBackground(Color.lightGray);
            panel2.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.lightGray, Color.gray, Color.lightGray), "Completed Work Orders", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 14), Color.darkGray));

            //======== scrollPane2 ========
            {

                //---- tblCompleted ----
                tblCompleted.setFont(tblCompleted.getFont().deriveFont(tblCompleted.getFont().getSize() + 2f));
                scrollPane2.setViewportView(tblCompleted);
            }

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                        .addContainerGap())
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))
            );
        }

        //---- txtSearch ----
        txtSearch.setFont(txtSearch.getFont().deriveFont(txtSearch.getFont().getSize() + 2f));
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtSearchKeyReleased(e);
            }
        });

        //---- label2 ----
        label2.setText("Search for :");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 4f));
        label2.setForeground(Color.darkGray);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblname, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button3, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
                            .addGap(95, 95, 95)
                            .addComponent(label2)
                            .addGap(18, 18, 18)
                            .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 21, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblname))
                    .addGap(24, 24, 24)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label2))
                            .addComponent(button1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                            .addComponent(button2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
                        .addComponent(button3))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblname;
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable tblRepair;
    private JPanel panel2;
    private JScrollPane scrollPane2;
    private JTable tblCompleted;
    private JTextField txtSearch;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

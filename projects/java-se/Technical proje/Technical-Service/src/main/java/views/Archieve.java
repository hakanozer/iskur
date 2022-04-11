/*
 * Created by JFormDesigner on Sat Apr 09 21:17:40 TRT 2022
 */

package views;

import models.IUser;
import models.ServiceImpl;
import models.UserImpl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class Archieve extends Base {
    ServiceImpl sI=new ServiceImpl();
    public Archieve() {

        initComponents();
        lblname.setText("Sn. "+UserImpl.name);
        tblarcieve.setModel(sI.serviceModelDelivered(null));
    }



    private void thisWindowClosed(WindowEvent e) {
        Dashboard dh=new Dashboard();
        dh.setVisible(true);
        dispose();

    }

    private void txtSearchKeyReleased(KeyEvent e) {
        String searchData=txtSearch.getText().toLowerCase().trim();
        tblarcieve.setModel(sI.serviceModelDelivered(searchData));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        txtSearch = new JTextField();
        label2 = new JLabel();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblarcieve = new JTable();
        label1 = new JLabel();
        lblname = new JLabel();
        label3 = new JLabel();

        //======== this ========
        setMaximizedBounds(new Rectangle(0, 0, 100, 137));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                thisWindowClosed(e);
            }
        });
        Container contentPane = getContentPane();

        //======== panel1 ========
        {

            //---- txtSearch ----
            txtSearch.setFont(txtSearch.getFont().deriveFont(txtSearch.getFont().getSize() + 2f));
            txtSearch.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txtSearchKeyReleased(e);
                }
            });

            //---- label2 ----
            label2.setText("Search For :");
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 4f));
            label2.setForeground(Color.darkGray);

            //======== panel2 ========
            {
                panel2.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.lightGray, Color.darkGray, Color.lightGray), "Historical Data Table", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                    new Font("Segoe UI", Font.BOLD, 16), Color.darkGray));
                panel2.setForeground(new Color(73, 69, 69));
                panel2.setMaximumSize(new Dimension(60008, 60224));

                //======== scrollPane1 ========
                {
                    scrollPane1.setMaximumSize(new Dimension(60767, 60767));

                    //---- tblarcieve ----
                    tblarcieve.setSelectionBackground(new Color(240, 229, 99));
                    tblarcieve.setFont(tblarcieve.getFont().deriveFont(tblarcieve.getFont().getSize() + 2f));
                    scrollPane1.setViewportView(tblarcieve);
                }

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                            .addContainerGap(20, Short.MAX_VALUE)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 739, GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24))
                );
                panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE)
                            .addGap(21, 21, 21))
                );
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtSearch, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)))
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
            );
        }

        //---- label1 ----
        label1.setText("Past Records");
        label1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        label1.setForeground(Color.darkGray);

        //---- lblname ----
        lblname.setText("text");
        lblname.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblname.setHorizontalAlignment(SwingConstants.RIGHT);
        lblname.setBorder(null);
        lblname.setForeground(Color.darkGray);

        //---- label3 ----
        label3.setText("text");
        label3.setIcon(new ImageIcon(getClass().getResource("/folder.png")));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
                            .addGap(63, 63, 63)
                            .addComponent(lblname, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(24, Short.MAX_VALUE))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(label3, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(lblname)
                                    .addGap(0, 27, Short.MAX_VALUE)))
                            .addGap(18, 18, 18))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(label1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)))
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(23, 23, 23))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JTextField txtSearch;
    private JLabel label2;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JTable tblarcieve;
    private JLabel label1;
    private JLabel lblname;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

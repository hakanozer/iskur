/*
 * Created by JFormDesigner on Mon Apr 11 01:56:39 TRT 2022
 */

package views;

import models.ArchiveImpl;
import models.UserImpl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Archive extends Base {
    ArchiveImpl archiveImpl = new ArchiveImpl();
    public Archive() {
        initComponents();
        lblName.setText("Dear. " + UserImpl.name);
        tblArchive.setModel(archiveImpl.archiveTable());
    }

    private void thisWindowClosing(WindowEvent e) {
        new Dashboard().setVisible(true);
    }

    private void txtCustomerSearchKeyReleased(KeyEvent e) {
        String txtSearch = txtCustomerSearch.getText().trim();
        tblArchive.setModel(archiveImpl.archiveTable());
    }

    private void tblCustomerServiceKeyReleased(KeyEvent e) {
        // TODO add your code here
    }

    private void tblCustomerServiceMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void btnAddClickService(ActionEvent e) {
        // TODO add your code here
    }

    private void btnDeleteClickService(ActionEvent e) {
        // TODO add your code here
    }

    private void btnUpdateClickService(ActionEvent e) {
        // TODO add your code here
    }

    private void tblUpdateDeleteKeyReleased(KeyEvent e) {
        // TODO add your code here
    }

    private void tblUpdateDeleteMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblService = new JLabel();
        lblName = new JLabel();
        label2 = new JLabel();
        txtCustomerSearch = new JTextField();
        scrollPane1 = new JScrollPane();
        tblArchive = new JTable();

        //======== this ========
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
        lblService.setForeground(new Color(33, 17, 17));
        lblService.setHorizontalAlignment(SwingConstants.CENTER);

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
            scrollPane1.setViewportView(tblArchive);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(lblService, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCustomerSearch)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 598, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(19, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(lblService, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtCustomerSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(349, Short.MAX_VALUE))
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
    private JTable tblArchive;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

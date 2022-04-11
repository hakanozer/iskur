/*
 * Created by JFormDesigner on Mon Apr 11 17:00:40 TRT 2022
 */

package views;

import models.ServiceImpl;
import models.ServiceTableImpl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Archive extends Base {
    Services services=new Services();
    ServiceImpl serviceImpl=new ServiceImpl();
    ServiceTableImpl serviceTable=new ServiceTableImpl();

    int row=-1;
    int value;

    public Archive() {
        initComponents();
        tblArchive.setModel(services.serviceTable.servicesTable(null));

    }

    private void btnDelete(ActionEvent e) {




    }

    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label8 = new JLabel();
        lblName = new JLabel();
        scrollPane1 = new JScrollPane();
        tblArchive = new JTable();
        btnDelete = new JButton();
        btnUpdate = new JButton();

        //======== this ========
        Container contentPane = getContentPane();

        //---- label8 ----
        label8.setText("TECHNIC SERVICE");
        label8.setHorizontalAlignment(SwingConstants.CENTER);
        label8.setFont(new Font("Segoe UI Black", Font.BOLD, 26));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(tblArchive);
        }

        //---- btnDelete ----
        btnDelete.setIcon(new ImageIcon(getClass().getResource("/2303123_bin_delete_garbage_remove_trash_icon.png")));
        btnDelete.addActionListener(e -> btnDelete(e));

        //---- btnUpdate ----
        btnUpdate.setIcon(new ImageIcon(getClass().getResource("/updateicon.png")));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(151, 151, 151)
                    .addComponent(label8, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                    .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                    .addContainerGap())
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(btnUpdate)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 340, Short.MAX_VALUE)
                    .addComponent(btnDelete)
                    .addGap(64, 64, 64))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                        .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label8, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(54, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label8;
    private JLabel lblName;
    private JScrollPane scrollPane1;
    private JTable tblArchive;
    private JButton btnDelete;
    private JButton btnUpdate;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

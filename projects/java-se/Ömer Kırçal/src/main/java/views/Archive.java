/*
 * Created by JFormDesigner on Sun Apr 10 23:29:30 TRT 2022
 */

package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Archive extends Base {
    public Archive() {
        initComponents();
    }

    private void btnCustomerAddClicked(ActionEvent e) {
        // TODO add your code here
    }

    private void btnAddServiceClicked(ActionEvent e) {
        // TODO add your code here
    }

    private void btnArchiveClicked(ActionEvent e) {
        // TODO add your code here
    }

    private void thisWindowClosing(WindowEvent e) {
        new Dashbord().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label3 = new JLabel();
        label4 = new JLabel();
        txtSearch = new JTextField();
        label8 = new JLabel();

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

        //======== panel1 ========
        {
            panel1.setBackground(SystemColor.activeCaption);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(table1);
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane1)
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))
            );
        }

        //---- label3 ----
        label3.setText("KIRCALO");
        label3.setHorizontalAlignment(SwingConstants.LEFT);
        label3.setFont(new Font("Kristen ITC", Font.BOLD, 26));
        label3.setForeground(SystemColor.textHighlight);

        //---- label4 ----
        label4.setText("TECHNICAL SERVICE");
        label4.setHorizontalAlignment(SwingConstants.LEFT);
        label4.setFont(new Font("Segoe UI Black", Font.BOLD, 22));
        label4.setForeground(SystemColor.textHighlight);

        //---- txtSearch ----
        txtSearch.setFont(new Font("Segoe UI", Font.BOLD, 14));

        //---- label8 ----
        label8.setText(" SEARCH");
        label8.setHorizontalAlignment(SwingConstants.CENTER);
        label8.setFont(new Font("Arial", Font.BOLD, 14));
        label8.setForeground(Color.black);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(txtSearch)
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE))
                                .addComponent(label8, GroupLayout.PREFERRED_SIZE, 876, GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                    .addComponent(label8, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label3;
    private JLabel label4;
    private JTextField txtSearch;
    private JLabel label8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

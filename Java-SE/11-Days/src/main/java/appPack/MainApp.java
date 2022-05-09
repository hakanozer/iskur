/*
 * Created by JFormDesigner on Wed Mar 30 15:11:47 TRT 2022
 */

package appPack;

import java.awt.event.*;

import models.Car;
import models.CarsModel;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class MainApp extends JFrame {

    public static void main(String[] args) {
        new MainApp().setVisible(true);
    }

    CarsModel carsModel = new CarsModel();
    public MainApp() {
        initComponents();
        tblCars.setModel( carsModel.model() );
    }

    private void addBtnClick(ActionEvent e) {
        String title = txtTitle.getText();
         int hp = Integer.parseInt(txtHp.getText());
        String color = txtColor.getText();
        int km = Integer.parseInt(txtKm.getText());
        Car c = new Car(title, hp, color, km);

        carsModel.add(c);
        tblCars.setModel( carsModel.model() );

    }

    private void tblCarsMouseClicked(MouseEvent e) {
        rowVal();
    }

    private void tblCarsKeyReleased(KeyEvent e) {
        if ( e.getKeyCode() == KeyEvent.VK_DELETE ) {
            rowVal();
            btnDeleteClick(null);
        }
    }

    int row = -1;
    void rowVal() {
        row = tblCars.getSelectedRow();
        String title = (String) tblCars.getValueAt(row, 0);
        String hp = ""+ tblCars.getValueAt(row, 1);
        String color = (String) tblCars.getValueAt(row, 2);
        String km = ""+ tblCars.getValueAt(row, 3);

        txtTitle.setText(title);
        txtHp.setText(hp);
        txtColor.setText(color);
        txtKm.setText(km);
    }

    private void btnDeleteClick(ActionEvent e) {
        if ( row != -1 ) {
            int answer = JOptionPane.showConfirmDialog(this, "Silmek istediğinizden emin misiniz?", "Silme İşlemi", JOptionPane.YES_NO_OPTION );
            if ( answer == 0 ) {
                carsModel.remove(row);
                tblCars.setModel( carsModel.model() );
                row = -1;
            }
        }else {
            // mesaj yazdırma
            JOptionPane.showMessageDialog(this, "Lütfen seçim yapınız!");
        }
    }

    private void btnDeleteAll(ActionEvent e) {
        int[] rows = tblCars.getSelectedRows();
        for (int j = rows.length - 1; j > -1 ; j--) {
            carsModel.remove( rows[j] );
            tblCars.setModel( carsModel.model() );
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        scrollPane1 = new JScrollPane();
        tblCars = new JTable();
        txtTitle = new JTextField();
        txtHp = new JTextField();
        txtColor = new JTextField();
        txtKm = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        setResizable(false);
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- tblCars ----
            tblCars.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tblCarsMouseClicked(e);
                }
            });
            tblCars.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    tblCarsKeyReleased(e);
                }
            });
            scrollPane1.setViewportView(tblCars);
        }

        //---- button1 ----
        button1.setText("Add");
        button1.addActionListener(e -> addBtnClick(e));

        //---- button2 ----
        button2.setText("Delete");
        button2.addActionListener(e -> btnDeleteClick(e));

        //---- button3 ----
        button3.setText("Delete All");
        button3.addActionListener(e -> btnDeleteAll(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, Short.MAX_VALUE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(txtColor)
                                    .addGap(18, 18, 18)))
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(txtHp, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                .addComponent(txtKm, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGap(0, 163, Short.MAX_VALUE)
                            .addComponent(button3)
                            .addGap(18, 18, 18)
                            .addComponent(button2)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtKm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button1)
                        .addComponent(button2)
                        .addComponent(button3))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JScrollPane scrollPane1;
    private JTable tblCars;
    private JTextField txtTitle;
    private JTextField txtHp;
    private JTextField txtColor;
    private JTextField txtKm;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

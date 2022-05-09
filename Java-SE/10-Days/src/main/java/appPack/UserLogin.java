/*
 * Created by JFormDesigner on Tue Mar 29 16:11:03 TRT 2022
 */

package appPack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class UserLogin extends JFrame {

    public static void main(String[] args) {
        UserLogin userLogin = new UserLogin();
        userLogin.setVisible(true);
    }

    public UserLogin() {
        initComponents();
        btnLogin.setText("Login");
    }

    boolean status = true;
    private void loginBtnClick(ActionEvent e) {
        String data = txtData.getText();
        System.out.println("login Btn Call " + data);
        lblData.setText( data );
        String title = btnLogin.getText();
        if ( status ) {
            btnLogin.setText("Giriş Yap");
        }else {
            btnLogin.setText("text");
        }
        status = !status;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        btnLogin = new JButton();
        txtData = new JTextField();
        lblData = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- btnLogin ----
        btnLogin.setText("text");
        btnLogin.addActionListener(e -> loginBtnClick(e));

        //---- lblData ----
        lblData.setText("text");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(txtData, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(lblData, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(txtData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLogin)
                        .addComponent(lblData))
                    .addContainerGap(187, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JButton btnLogin;
    private JTextField txtData;
    private JLabel lblData;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

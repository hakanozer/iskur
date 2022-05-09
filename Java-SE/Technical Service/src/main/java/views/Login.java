/*
 * Created by JFormDesigner on Wed Apr 06 15:18:22 TRT 2022
 */

package views;

import java.awt.event.*;

import models.UserImpl;
import utils.Mail;
import utils.Util;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Login extends Base {

    UserImpl user = new UserImpl();

    public static void main(String[] args) {
        new Login().setVisible(true);
    }

    public Login() {
        initComponents();
        txtPassword.setText("12345");
        Mail.send("hakanozer02@gmail.com", "Java Sub", "İleti mesajı");

    }

    private void btnLoginClick(ActionEvent e) {
        userLogin();
    }

    private void txtEmailKeyReleased(KeyEvent e) {
        if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {
            userLogin();
        }
    }

    private void txtPasswordKeyReleased(KeyEvent e) {
        if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {
            userLogin();
        }
    }

    // user login
    public void userLogin() {
        String email = txtEmail.getText().trim().toLowerCase();
        String password = String.valueOf(txtPassword.getPassword()).trim();
        if ( email.equals("") ) {
            txtEmail.requestFocus();
            lblError.setText("E-Mail Empty!");
        }else if (!Util.isValidEmailAddress((email))) {
            lblError.setText("E-Mail Format Fail!");
            txtEmail.requestFocus();
        }else if ( password.length() == 0 ) {
            lblError.setText("Password Empty!");
            txtPassword.requestFocus();
        }else {

            lblError.setText("");
            boolean status = user.userLogin(email, password);
            if ( status ) {
                // giriş başarılı
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
                dispose();
            }else {
                lblError.setText("Email or Password Fail");
            }

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        txtEmail = new JTextField();
        txtPassword = new JPasswordField();
        lblError = new JLabel();
        btnLogin = new JButton();

        //======== this ========
        setResizable(false);
        setBackground(new Color(51, 51, 255));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/login_icon.png")));

        //---- label2 ----
        label2.setText("E-Mail");

        //---- label3 ----
        label3.setText("Pasword");

        //---- txtEmail ----
        txtEmail.setText("ali@gmail.com");
        txtEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtEmailKeyReleased(e);
            }
        });

        //---- txtPassword ----
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtPasswordKeyReleased(e);
            }
        });

        //---- lblError ----
        lblError.setText(" ");
        lblError.setForeground(new Color(244, 92, 92));

        //---- btnLogin ----
        btnLogin.setIcon(new ImageIcon(getClass().getResource("/login_btn_icon.png")));
        btnLogin.addActionListener(e -> btnLoginClick(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPaneLayout.setHonorsVisibility(false);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label3)
                        .addComponent(label2))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(btnLogin)
                            .addGap(0, 238, Short.MAX_VALUE))
                        .addComponent(lblError, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                        .addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                        .addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
                    .addContainerGap())
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(162, 162, 162)
                    .addComponent(label1)
                    .addContainerGap(172, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(label1)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblError)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnLogin)
                    .addContainerGap(43, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JLabel lblError;
    private JButton btnLogin;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

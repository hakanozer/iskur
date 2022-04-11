/*
 * Created by JFormDesigner on Wed Apr 06 15:18:33 TRT 2022
 */

package views;

import java.awt.event.*;

import models.UserImpl;
import props.User;
import utils.Util;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Login extends Base {
    UserImpl user= new UserImpl();

    public static void main(String[] args) {
        new Login().setVisible(true);

    }

    //user Login
    public void userLogin() {
        String email = txtEmail.getText().trim();
        String password = String.valueOf(txtPassword.getPassword()).trim();
        if (email.equals("") ) {
            lblError.setText("Please Type Your E-Mail!");
        } else if (!Util.isValidEmailAddress(email)) {
            lblError.setText("Check Your E-Mail Format!");
            txtEmail.requestFocus();
        } else if (password.length()==0) {
            lblError.setText("Please Type Your Password!");
            txtPassword.requestFocus();
        } else {
            lblError.setText("");
            boolean status=user.userLogin(email,password);
            if (status) { //giriş başarılı
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
                dispose();
            } else {
                lblError.setText("E-Mail or Password Fail");
            }

        }

    }


    public Login() {
        initComponents();
        txtPassword.setText("5");
    }

    private void btnLoginClick(ActionEvent e) {
        userLogin();
    }

    private void txtEmailKeyReleased(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_ENTER)
        userLogin();
    }

    private void txtPasswordKeyReleased(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_ENTER)
        userLogin();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        txtEmail = new JTextField();
        txtPassword = new JPasswordField();
        lblError = new JLabel();
        btnLogin = new JButton();

        //======== this ========
        setBackground(new Color(0, 153, 153));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/login icon.png")));

        //---- label2 ----
        label2.setText("E-mail:");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 3f));

        //---- label3 ----
        label3.setText("Password:");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 2f));

        //---- txtEmail ----
        txtEmail.setText("erkut@mail.com");
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
        lblError.setText("  ");
        lblError.setBackground(Color.red);
        lblError.setForeground(new Color(255, 0, 51));

        //---- btnLogin ----
        btnLogin.setIcon(new ImageIcon(getClass().getResource("/loginBtn icon.png")));
        btnLogin.addActionListener(e -> btnLoginClick(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(208, Short.MAX_VALUE)
                    .addComponent(label1)
                    .addGap(196, 196, 196))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label3, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(lblError)
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addComponent(txtEmail, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(lblError))
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(btnLogin)
                    .addContainerGap(50, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JLabel lblError;
    private JButton btnLogin;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

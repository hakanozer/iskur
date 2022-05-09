/*
 * Created by JFormDesigner on Thu Apr 07 09:40:54 TRT 2022
 */

package views;

import models.UserImpl;
import utils.Util;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
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
        txtEmail.setText("semih@gmail.com");
    }

    private void btnLoginClick(ActionEvent e) {
        userLogin();
    }

    private void txtEmailKeyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            userLogin();
        }
    }

    private void txtPasswordKeyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            userLogin();
        }
    }
    public void userLogin() {
        String email = txtEmail.getText().trim().toLowerCase();
        String password = String.valueOf(txtPassword.getPassword());
        if (email.equals("")) {
            txtEmail.requestFocus();
            lblError.setText("Please Entry E-Mail!");
        }else if (!Util.isValidEmailAddress(email)) {
            lblError.setText("E-Mail Format Error!");
        }else if (password.length() == 0) {
            lblError.setText("Please Entry Password");
            txtPassword.requestFocus();
        }else {
            lblError.setText("");
            boolean status = user.userLogin(email, password);
            if (status) {
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
            dispose();
            }else {
                lblError.setText("E-Mail or Password False!");
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label2 = new JLabel();
        txtEmail = new JTextField();
        label3 = new JLabel();
        btnLogin = new JButton();
        lblError = new JLabel();
        txtPassword = new JPasswordField();
        label1 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new Color(255, 255, 153));
        setResizable(false);
        Container contentPane = getContentPane();

        //---- label2 ----
        label2.setText("E-Mail");
        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));

        //---- txtEmail ----
        txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtEmailKeyReleased(e);
            }
        });

        //---- label3 ----
        label3.setText("Password");
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));

        //---- btnLogin ----
        btnLogin.setText("LOGIN");
        btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnLogin.setForeground(new Color(25, 17, 17));
        btnLogin.setBackground(new Color(179, 223, 223));
        btnLogin.addActionListener(e -> btnLoginClick(e));

        //---- lblError ----
        lblError.setText(" ");
        lblError.setForeground(Color.red);
        lblError.setHorizontalAlignment(SwingConstants.CENTER);

        //---- txtPassword ----
        txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtPasswordKeyReleased(e);
            }
        });

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/user-login.png")));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblError, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                    .addContainerGap())
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap(245, Short.MAX_VALUE)
                            .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                                .addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 233, Short.MAX_VALUE)))))
                    .addGap(14, 14, 14))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(142, 142, 142)
                    .addComponent(label1)
                    .addContainerGap(142, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(label2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label3)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                    .addComponent(lblError)
                    .addGap(19, 19, 19))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label2;
    private JTextField txtEmail;
    private JLabel label3;
    private JButton btnLogin;
    private JLabel lblError;
    private JPasswordField txtPassword;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

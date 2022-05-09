/*
 * Created by JFormDesigner on Wed Apr 06 15:19:32 TRT 2022
 */

package views;

import java.awt.event.*;

import models.UserImpl;
import props.User;
import utils.Util;

import java.awt.*;
import javax.jws.soap.SOAPBinding;
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

    //user login
    public void userLogin(){
        String email = txtEmail.getText().trim().toLowerCase();//trim bosluklari temizler, tolowercase karakterleri kucultur
        String password = String.valueOf(txtPassword.getPassword()).trim();//password ozelligi geregi string dondurmez,char dizisi dondurur
        if (email.equals("")){
            txtEmail.requestFocus();
            lblError.setText("Please enter an e-mail!");
        }else if(!Util.isValidEmailAddress(email)){//validation dogru degilse
            lblError.setText("Your e-mail format is fail!");
        }
        else if (password.length() == 0){
            lblError.setText("Please enter a password!");
            txtPassword.requestFocus();//hataya odaklan ve imleci oraya kaydir
        }
        else {
            lblError.setText("");
            boolean status = user.userLogin(email, password);
            if (status){
                //giris basarili
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
                dispose();
            }
            else
            {
                lblError.setText("Email or password fail");
            }



        }
    }
    
    public Login() {
        initComponents();
        txtPassword.setText("12345");
    }

    private void btnLoginClick(ActionEvent e) {
        userLogin();
    }

    private void txtEmailKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            userLogin();
        }

    }

    private void txtPasswordKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            userLogin();
        }
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
        setBackground(Color.lightGray);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/user.png")));

        //---- label2 ----
        label2.setText("E-mail:");

        //---- label3 ----
        label3.setText("Password:");

        //---- txtEmail ----
        txtEmail.setText("kardelen@gmail.com");
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
        lblError.setForeground(Color.red);
        lblError.setText(" ");

        //---- btnLogin ----
        btnLogin.setIcon(new ImageIcon(getClass().getResource("/login.png")));
        btnLogin.addActionListener(e -> btnLoginClick(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(91, 91, 91)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)))
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(209, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
                            .addGap(194, 194, 194))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                            .addGap(236, 236, 236))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(179, 179, 179)
                            .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(59, Short.MAX_VALUE))
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

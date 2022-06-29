/*
 * Created by JFormDesigner on Wed Apr 06 15:18:24 TRT 2022
 */

package views;

import java.awt.event.*;

import model.UserImpl;
import utils.Util;

import java.awt.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Login extends Base  {
    UserImpl user = new UserImpl();
    public static void main(String[] args) {
        new Login().setVisible( true );
    }

    //User Login
    public void userLogin(){
        String email = txtEmail.getText().toLowerCase(Locale.ROOT);
        String password = String.valueOf(txtPassword.getPassword());

        if(email.equals("")){
            txtEmail.requestFocus();
            lblError.setText("E-mail cannot be empty");
        }
        else if(!Util.isValidEmailAddress( email )){
            lblError.setText("E-mail invalid");
            txtEmail.requestFocus();
        }
        else if( password.length()==0 ){
            lblError.setText( "Password cannot be empty" );
            txtPassword.requestFocus();
        }
        else{
            lblError.setText("");
            if (user.userLogin( email,password )) {
                lblError.setText("Login successful");
                new Dashboard().setVisible( true );
                dispose();
            } else {
                lblError.setText( "Incorrect Data" );
            }
        }
    }
    public Login() {
        initComponents();
        txtEmail.setText("admin@gmail.com");
        txtPassword.setText("12345");

    }



    private void txtEmailKeyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            userLogin();
        }
    }

    private void txtPasswordKeyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            userLogin();
        }

    }

    private void btnLoginClick(ActionEvent e) {
       userLogin();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - ali
        label1 = new JLabel();
        label2 = new JLabel();
        txtEmail = new JTextField();
        label3 = new JLabel();
        txtPassword = new JPasswordField();
        lblError = new JLabel();
        btnLogin = new JButton();

        //======== this ========
        setResizable(false);
        setBackground(new Color(102, 102, 255));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/newLoginIcon.png")));

        //---- label2 ----
        label2.setText("E-mail");

        //---- txtEmail ----
        txtEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtEmailKeyReleased(e);
            }
        });

        //---- label3 ----
        label3.setText("Password:");

        //---- txtPassword ----
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtPasswordKeyReleased(e);
            }
        });

        //---- lblError ----
        lblError.setForeground(new Color(255, 51, 51));
        lblError.setText("text");

        //---- btnLogin ----
        btnLogin.setText("Login");
        btnLogin.setIcon(new ImageIcon(getClass().getResource("/loginButtonIcon.png")));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblError, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(label3, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                .addComponent(label2, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addComponent(label1))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE))))
                        .addComponent(btnLogin, GroupLayout.Alignment.TRAILING))
                    .addContainerGap(23, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnLogin)
                    .addContainerGap(17, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - ali
    private JLabel label1;
    private JLabel label2;
    private JTextField txtEmail;
    private JLabel label3;
    private JPasswordField txtPassword;
    private JLabel lblError;
    private JButton btnLogin;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

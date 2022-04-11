/*
 * Created by JFormDesigner on Fri Apr 08 23:02:21 TRT 2022
 */

package views;

import model.UserImpl;
import utils.Util;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

/**
 * @author ali
 */
public class Login extends Base {
    UserImpl user = new UserImpl();
    public static void main(String[] args) {
        new Login().setVisible( true );
    }
public void userLogin(){
    String email=txtEMail.getText().toLowerCase(Locale.ROOT);
    String password= String.valueOf(txtPassword.getPassword());//txtPassword.getPassword() char dizisi getirir

    if(email.equals("")){
        txtEMail.requestFocus();
        lblError.setText("E-mail Empty");
    }
    else if(!Util.isValidEmailAddress(email)){
        lblError.setText("E-mail invalid");
        txtEMail.requestFocus();
    }
    else if(password.length()==0){
        lblError.setText("Password Empty");
        txtPassword.requestFocus();
    }
    else{
        lblError.setText("");
        if (user.userLogin(email,password)) {
            lblError.setText("Login successful");
            new Dashboard().setVisible(true);
            dispose();
        } else {
            lblError.setText("Incorrect Data Entry");
        }
    }
}


    public Login() {
        txtEMail.setText("user@gmail.com");
        txtPassword.setText("12345");
        initComponents();

    }

    private void btnLoginClicked(ActionEvent e) {
        // TODO add your code here
        userLogin();
    }

    private void txtEMailKeyReleased(KeyEvent e) {
        // TODO add your code here
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            userLogin();
        }
    }

    private void txtPasswordKeyReleased(KeyEvent e) {
        // TODO add your code here
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            userLogin();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - ali
        label1 = new JLabel();
        lblEmail = new JLabel();
        lblPassword = new JLabel();
        txtEMail = new JTextField();
        btnLogin = new JButton();
        txtPassword = new JPasswordField();
        lblError = new JLabel();

        //======== this ========
        setForeground(SystemColor.textHighlight);
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/loginIcon.png")));

        //---- lblEmail ----
        lblEmail.setText("E-Mail");

        //---- lblPassword ----
        lblPassword.setText("Password");

        //---- txtEMail ----
        txtEMail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtEMailKeyReleased(e);
            }
        });

        //---- btnLogin ----
        btnLogin.setText("Login");
        btnLogin.addActionListener(e -> btnLoginClicked(e));

        //---- txtPassword ----
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtPasswordKeyReleased(e);
            }
        });

        //---- lblError ----
        lblError.setText("text");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLogin))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(txtEMail, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(txtPassword))
                    .addGap(31, 31, 31))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(78, 78, 78)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEMail, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                .addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                .addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblError))))
                    .addContainerGap(8, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - ali
    private JLabel label1;
    private JLabel lblEmail;
    private JLabel lblPassword;
    private JTextField txtEMail;
    private JButton btnLogin;
    private JPasswordField txtPassword;
    private JLabel lblError;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

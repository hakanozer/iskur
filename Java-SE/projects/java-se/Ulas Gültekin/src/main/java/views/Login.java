/*
 * Created by JFormDesigner on Sat Apr 09 22:49:46 TRT 2022
 */

package views;

import java.awt.event.*;
import models.UserImpl;
import utils.Util;

import java.awt.*;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Login extends Base {
    UserImpl user=new UserImpl();
    public static void main(String[] args) {
        new Login().setVisible(true);
    }
    public Login() {
        initComponents();
        txtEmail.setText("ulas1@gmail.com");
        txtPassword.setText("12345");
    }
    public void userLogin(){
        String email =txtEmail.getText().trim().toLowerCase(Locale.ROOT);
        String password=String.valueOf(txtPassword.getPassword()).trim();
        if(email.equals("")){
            lblError.setText("Email Faid");
            txtEmail.requestFocus();
        }else if(!utils.Util.isValidEmailAddress(email)){
            lblError.setText("Email Validation Error");
            txtEmail.requestFocus();
        }else if (password.length()==0){
            lblError.setText("Password is Empty");
            txtPassword.requestFocus();
        }else {
            lblError.setText("");
            boolean status=user.userLogin(email,password);
            if (status){
            new Dashboard().setVisible(true);
            dispose();}
            else{

                lblError.setText("Email or Password Fail");

            }

        }

    }

    private void btnSignInclick(ActionEvent e) {
        userLogin();

    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        txtEmail = new JTextField();
        label3 = new JLabel();
        txtPassword = new JPasswordField();
        btnSignIn = new JButton();
        label4 = new JLabel();
        lblError = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/loginin.png")));

        //---- label2 ----
        label2.setText("E-Mail");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Segoe UI Black", Font.BOLD, 16));

        //---- label3 ----
        label3.setText("Password");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Segoe UI Black", Font.BOLD, 16));

        //---- btnSignIn ----
        btnSignIn.setText("Sign In");
        btnSignIn.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
        btnSignIn.setForeground(Color.blue);
        btnSignIn.setBackground(Color.red);
        btnSignIn.setIcon(new ImageIcon(getClass().getResource("/signin.png")));
        btnSignIn.addActionListener(e -> btnSignInclick(e));

        //---- label4 ----
        label4.setText("Technic Service");
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setFont(new Font("Bodoni MT Black", Font.BOLD, 26));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(51, 51, 51)
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(39, 39, 39)
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                            .addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                                        .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGap(16, 16, 16)
                                            .addComponent(btnSignIn, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(122, 122, 122)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(16, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)
                            .addComponent(btnSignIn)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                            .addComponent(label1)
                            .addGap(32, 32, 32))))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField txtEmail;
    private JLabel label3;
    private JPasswordField txtPassword;
    private JButton btnSignIn;
    private JLabel label4;
    private JLabel lblError;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

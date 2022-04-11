/*
 * Created by JFormDesigner on Wed Apr 06 15:18:11 TRT 2022
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
        txtPassword.setText("12345");
        txtEmail.setText("omer@mail.com");
    }

    private void btnLoginClicked(ActionEvent e) {
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

    //userLogin
    public void userLogin(){
        String email=txtEmail.getText().trim().toLowerCase();
        String password=String.valueOf(txtPassword.getPassword()).trim();
        if(email.equals("")){
            txtEmail.requestFocus();
            lblError.setText("Please Enter E-Mail");
        }else if(!Util.isValidEmailAddress(email)){
            lblError.setText("E-Mail Format Error");
        }else if(password.length()==0){
            lblError.setText("Please Enter Password");
            txtPassword.requestFocus();
        }else{
            lblError.setText("");
            boolean status=user.userLogin(email,password);
            if(status){
               Dashbord dashbord =new Dashbord();
               dashbord.setVisible(true);
               dispose();
            }else{
                lblError.setText("E-mail or Password Fail");
            }

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblIcon = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        txtEmail = new JTextField();
        txtPassword = new JPasswordField();
        lblError = new JLabel();
        btnLogin = new JButton();
        label3 = new JLabel();
        label4 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/programIcon.png")).getImage());
        Container contentPane = getContentPane();

        //---- lblIcon ----
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/programIcon.png")));
        lblIcon.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label1 ----
        label1.setText("E-MAIL");
        label1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        //---- label2 ----
        label2.setText("PASSWORD");
        label2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label2.setHorizontalAlignment(SwingConstants.CENTER);

        //---- txtEmail ----
        txtEmail.setFont(new Font("Segoe UI", Font.BOLD, 12));
        txtEmail.setForeground(SystemColor.controlText);
        txtEmail.setBackground(Color.lightGray);
        txtEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtEmailKeyReleased(e);
            }
        });

        //---- txtPassword ----
        txtPassword.setFont(new Font("Segoe UI", Font.BOLD, 12));
        txtPassword.setForeground(SystemColor.controlText);
        txtPassword.setBackground(Color.lightGray);
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtPasswordKeyReleased(e);
            }
        });

        //---- lblError ----
        lblError.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblError.setForeground(new Color(245, 6, 6));
        lblError.setText(" ");
        lblError.setHorizontalAlignment(SwingConstants.CENTER);

        //---- btnLogin ----
        btnLogin.setBackground(Color.lightGray);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setForeground(Color.white);
        btnLogin.setIcon(new ImageIcon(getClass().getResource("/loginicon.png")));
        btnLogin.setBorder(null);
        btnLogin.addActionListener(e -> btnLoginClicked(e));

        //---- label3 ----
        label3.setText("KIRCALO");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Kristen ITC", Font.BOLD, 26));
        label3.setForeground(SystemColor.textHighlight);

        //---- label4 ----
        label4.setText("TECHNICAL SERVICE");
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setFont(new Font("Segoe UI Black", Font.BOLD, 16));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(127, 127, 127)
                                    .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(txtEmail)
                                        .addComponent(txtPassword)))
                                .addComponent(label3, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addContainerGap())
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(70, 70, 70)
                    .addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label4, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                    .addGap(25, 25, 25)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1))
                    .addGap(17, 17, 17)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblIcon;
    private JLabel label1;
    private JLabel label2;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JLabel lblError;
    private JButton btnLogin;
    private JLabel label3;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

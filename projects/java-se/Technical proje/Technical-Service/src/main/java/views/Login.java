/*
 * Created by JFormDesigner on Wed Apr 06 15:18:05 TRT 2022
 */

package views;

import java.awt.event.*;
import javax.swing.border.*;

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
    UserImpl user=new UserImpl();
    boolean status=false;
   static String email="";
    public static void main(String[] args) {
        new Login().setVisible(true);
    }
    public void userLogin(){
        String email=txtEmail.getText().trim().toLowerCase();
        String password=String.valueOf(txtPassword.getPassword()).trim();
        if(email.equals("")){
            lblError.setText("Email Empty");
            txtEmail.requestFocus();

        }else if(!Util.isValidEmailAddress(email)){

            lblError.setText("E-mail Format Error");
            txtEmail.requestFocus();
        }
        else if(password.length()==0){
            lblError.setText("Password Warning");
            txtPassword.requestFocus();

        }else {
        lblError.setText("");

         status =user.userLogin(email,password);
        if(status){
          Dashboard dashboard=new Dashboard();
          dashboard.setVisible(true);
          dispose();
        }
        else {
            lblError.setText("Email or Pasword Fail");
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
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
        userLogin();}
    }

    private void txtPasswordKeyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            userLogin();}

    }

    private void checkBox1MouseClicked(MouseEvent e) {

    }

    private void label6MouseClicked(MouseEvent e) {
        email=txtEmail.getText().trim();
     PasswordClear p=new PasswordClear();

     p.setVisible(true);
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
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();

        //======== this ========
        setBackground(Color.cyan);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/userlogin.png")));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label1.setBackground(Color.lightGray);

        //---- label2 ----
        label2.setText("E-Mail");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Segoe UI", Font.BOLD, 17));
        label2.setForeground(new Color(79, 77, 77));

        //---- label3 ----
        label3.setText("Password");
        label3.setFont(new Font("Segoe UI", Font.BOLD, 17));
        label3.setForeground(new Color(79, 77, 77));

        //---- txtEmail ----
        txtEmail.setText("mustafa@gmail.com");
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtEmailKeyReleased(e);
            }
        });

        //---- txtPassword ----
        txtPassword.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 17));
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtPasswordKeyReleased(e);
            }
        });

        //---- lblError ----
        lblError.setText("  ");
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        lblError.setForeground(Color.red);
        lblError.setFont(new Font("Segoe UI", Font.BOLD, 16));

        //---- btnLogin ----
        btnLogin.setBackground(new Color(255, 199, 11));
        btnLogin.setIcon(new ImageIcon(getClass().getResource("/login.png")));
        btnLogin.setText("SUBMIT");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btnLogin.setForeground(Color.white);
        btnLogin.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.lightGray, Color.gray, Color.lightGray));
        btnLogin.addActionListener(e -> btnLoginClick(e));

        //---- label4 ----
        label4.setText("USER");
        label4.setFont(new Font("Segoe UI", Font.BOLD, 25));
        label4.setForeground(new Color(255, 191, 43));

        //---- label5 ----
        label5.setText("LOGIN");
        label5.setFont(new Font("Segoe UI", Font.BOLD, 25));
        label5.setForeground(new Color(79, 77, 77));

        //---- label6 ----
        label6.setText("Forgot password?");
        label6.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        label6.setForeground(new Color(79, 77, 77));
        label6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label6MouseClicked(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(192, 192, 192)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(221, 221, 221)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(264, 264, 264)
                    .addComponent(label6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(103, 103, 103))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addGap(199, 199, 199))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)
                            .addGap(22, 22, 22))))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                                .addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
                            .addGap(81, 81, 81))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                            .addGap(146, 146, 146))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
                            .addGap(82, 82, 82))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label5)
                        .addComponent(label4))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(label2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblError)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(label6)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE))
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
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

/*
 * Created by JFormDesigner on Wed Apr 06 15:19:32 TRT 2022
 */

package views;

import java.awt.event.*;

import models.UserImpl;
import props.User;
import utils.Mail;
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
        Mail.send("onuryalcin@outlook.com", "Mail Hk.","Selamlar");
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
        panel1 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        txtEmail = new JTextField();
        txtPassword = new JPasswordField();
        label3 = new JLabel();
        label2 = new JLabel();
        lblError = new JLabel();
        btnLogin = new JButton();
        panel3 = new JPanel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();

        //======== this ========
        setBackground(Color.lightGray);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        Container contentPane = getContentPane();

        //======== panel1 ========
        {

            //======== panel2 ========
            {

                //---- label1 ----
                label1.setIcon(new ImageIcon(getClass().getResource("/user.png")));

                //---- txtEmail ----
                txtEmail.setText("onur@mail.com");
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

                //---- label3 ----
                label3.setText("Password:");

                //---- label2 ----
                label2.setText("E-mail:");

                //---- lblError ----
                lblError.setForeground(Color.red);
                lblError.setText(" ");

                //---- btnLogin ----
                btnLogin.setIcon(new ImageIcon(getClass().getResource("/login.png")));
                btnLogin.addActionListener(e -> btnLoginClick(e));

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addGroup(panel2Layout.createParallelGroup()
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addGap(104, 104, 104)
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel2Layout.createParallelGroup()
                                        .addGroup(panel2Layout.createSequentialGroup()
                                            .addGap(9, 9, 9)
                                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel2Layout.createSequentialGroup()
                                            .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))))
                            .addContainerGap(21, Short.MAX_VALUE))
                );
                panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addContainerGap(58, Short.MAX_VALUE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                            .addGap(51, 51, 51)
                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(panel2Layout.createParallelGroup()
                                .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                            .addGap(103, 103, 103))
                );
            }

            //======== panel3 ========
            {
                panel3.setBackground(new Color(94, 185, 155));

                //---- label4 ----
                label4.setIcon(new ImageIcon(getClass().getResource("/globe.png")));

                //---- label5 ----
                label5.setText("Global ");
                label5.setFont(new Font("Sitka Small", Font.BOLD | Font.ITALIC, 48));
                label5.setForeground(new Color(135, 201, 242));

                //---- label6 ----
                label6.setText("Technology");
                label6.setFont(new Font("Sitka Small", Font.BOLD | Font.ITALIC, 36));
                label6.setForeground(new Color(0, 112, 255));

                GroupLayout panel3Layout = new GroupLayout(panel3);
                panel3.setLayout(panel3Layout);
                panel3Layout.setHorizontalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addGap(54, 54, 54)
                            .addGroup(panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                        .addComponent(label5)
                                        .addGap(29, 29, 29))
                                    .addComponent(label6, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addGap(51, 51, 51)
                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                                    .addGap(47, 47, 47)))
                            .addContainerGap(58, Short.MAX_VALUE))
                );
                panel3Layout.setVerticalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label6)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel3, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JPanel panel2;
    private JLabel label1;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JLabel label3;
    private JLabel label2;
    private JLabel lblError;
    private JButton btnLogin;
    private JPanel panel3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

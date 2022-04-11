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
    UserImpl user=new UserImpl();
    public static void main(String[] args) {
        new Login().setVisible(true);
    }


    //User Login
    public void userLogin(){
        String email=txtEmail.getText().toLowerCase(Locale.ROOT);
        String password= String.valueOf(txtPassword.getPassword());//txtPassword.getPassword() char dizisi getirir

        if(email.equals("")){
            txtEmail.requestFocus();
            lblError.setText("E-mail Empty");
        }
        else if(!Util.isValidEmailAddress(email)){
            lblError.setText("E-mail invalid");
            txtEmail.requestFocus();
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
        initComponents();
        //herzaman doldurmayalım diye
        txtEmail.setText("admin@gmail.com");
        txtPassword.setText("12345");
    }



    private void txtEmailKeyReleased(KeyEvent e) {
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

    private void btnLoginClick(ActionEvent e) {
        // TODO add your code here
       userLogin();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label1 = new JLabel();
        txtEmail = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        txtPassword = new JPasswordField();
        btnLogin = new JButton();
        lblError = new JLabel();
        panel2 = new JPanel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();

        //======== this ========
        setResizable(false);
        setBackground(new Color(102, 102, 255));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(73, 241, 157));

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/loginicon.png")));

            //---- txtEmail ----
            txtEmail.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txtEmailKeyReleased(e);
                }
            });

            //---- label2 ----
            label2.setText("E-mail");

            //---- label3 ----
            label3.setText("Password:");

            //---- txtPassword ----
            txtPassword.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    txtPasswordKeyReleased(e);
                }
            });

            //---- btnLogin ----
            btnLogin.setIcon(new ImageIcon(getClass().getResource("/login_btn.png")));
            btnLogin.setBackground(new Color(73, 241, 157));
            btnLogin.setHorizontalAlignment(SwingConstants.LEADING);

            //---- lblError ----
            lblError.setForeground(new Color(255, 51, 51));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap(148, Short.MAX_VALUE)
                        .addComponent(label1)
                        .addGap(98, 98, 98))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap(17, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblError, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                            .addGroup(GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                    .addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)))
                            .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(28, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label3)
                            .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLogin)
                        .addContainerGap())
            );
        }

        //======== panel2 ========
        {
            panel2.setBackground(new Color(94, 185, 155));

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

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addContainerGap(73, Short.MAX_VALUE)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                .addComponent(label5)
                                .addGap(94, 94, 94))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                .addComponent(label6, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65))))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label6)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label1;
    private JTextField txtEmail;
    private JLabel label2;
    private JLabel label3;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblError;
    private JPanel panel2;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

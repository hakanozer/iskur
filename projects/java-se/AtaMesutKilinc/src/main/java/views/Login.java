/*
 * Created by JFormDesigner on Wed Apr 06 15:17:57 TRT 2022
 */

package views;

import models.UserImpl;

import java.awt.*;
import java.awt.event.*;
import java.security.Key;
import java.util.Locale;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class Login extends Base {

    UserImpl user= new UserImpl();
    public static void main(String[] args) {
        new Login().setVisible(true);

       // Customer u = new User(0,"sss","ss","usss@gmassil.com","12345");
//        int status=userimpl.userInsert(u);
//        if (status>0){
//            System.out.println("ekleme başarılı");
//        }
//        else{
//            System.out.println("Ekleme hatası..");
//        }
    }




    public Login() {
        initComponents();
        txtEmail.setText("ata@mail.com"); //dolu getir.
        txtPassword.setText("12345");
    }

    private void btnLoginClick(ActionEvent e) {
        userLogin();
    }

    private void txtEmailKeyReleased(KeyEvent e) { //enter a basılınca
        if (e.getKeyCode()== KeyEvent.VK_ENTER){
            userLogin();
        }

    }

    private void txtPasswordKeyReleased(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_ENTER){
            userLogin();
        }
    }


    //user Login
    public void userLogin(){
        String email=txtEmail.getText().trim().toLowerCase();  //boşluk varsa al trimle sil
        String password=String.valueOf(txtPassword.getPassword()).trim(); //char dizisi getirir get password-- stringe dönüştürdük
        if (email.equals("")){ //email boşssa
            lblError.setText("Email is Empty!!!");
            txtEmail.requestFocus();
        }else if(!Utils.Util.isValidEmailAddress(email)){ //fprmatı başkaysa
            lblError.setText("Email Validation Error!!!");
            txtEmail.requestFocus();
        }else if (password.length()==0){ //boşşa sıfırsa
            lblError.setText("Password is Empty!!!");
            txtPassword.requestFocus();//imleç otomatik olarak passwworde gelicek
        }else {
           lblError.setText("");
           boolean status=user.userLogin(email,password); //veritabanına gittik burada
           if (status){
               //giriş başarılı
               Dashboard dashboard= new Dashboard();
               dashboard.setVisible(true);
               dispose();//öldür zombiyi

           }else{
               lblError.setText("Email or Password Fail!!!");
           }

        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        lblEmail = new JLabel();
        lblPassword = new JLabel();
        txtPassword = new JPasswordField();
        txtEmail = new JTextField();
        lblError = new JLabel();
        btnLogin = new JButton();
        pnlIcon = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();

        //======== this ========
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/loginwindow1.png")).getImage());
        setTitle("Login Window");
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/LoginIcon.png")));

        //---- lblEmail ----
        lblEmail.setText("Email :");
        lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);

        //---- lblPassword ----
        lblPassword.setText("Password :");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);

        //---- txtPassword ----
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtPasswordKeyReleased(e);
            }
        });

        //---- txtEmail ----
        txtEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtEmailKeyReleased(e);
            }
        });

        //---- lblError ----
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        lblError.setForeground(new Color(245, 29, 29));

        //---- btnLogin ----
        btnLogin.setText("Login");
        btnLogin.setIcon(new ImageIcon(getClass().getResource("/LoginbtnIcon.png")));
        btnLogin.setBackground(Color.lightGray);
        btnLogin.setForeground(new Color(0, 172, 238));
        btnLogin.addActionListener(e -> btnLoginClick(e));

        //======== pnlIcon ========
        {
            pnlIcon.setEnabled(false);
            pnlIcon.setBackground(new Color(0, 172, 238));

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/loginRepairService.png")));

            //---- label3 ----
            label3.setText("Tecnical Service Login");
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            label3.setFont(new Font("Arial", Font.BOLD, 20));
            label3.setBackground(new Color(60, 167, 233));

            //---- label4 ----
            label4.setText("More Than Repair");
            label4.setHorizontalAlignment(SwingConstants.CENTER);
            label4.setFont(new Font("Arial", Font.PLAIN, 16));

            GroupLayout pnlIconLayout = new GroupLayout(pnlIcon);
            pnlIcon.setLayout(pnlIconLayout);
            pnlIconLayout.setHorizontalGroup(
                pnlIconLayout.createParallelGroup()
                    .addGroup(pnlIconLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(pnlIconLayout.createParallelGroup()
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))
                    .addGroup(GroupLayout.Alignment.TRAILING, pnlIconLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label2)
                        .addGap(85, 85, 85))
            );
            pnlIconLayout.setVerticalGroup(
                pnlIconLayout.createParallelGroup()
                    .addGroup(pnlIconLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(40, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(pnlIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(lblError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(106, 106, 106)
                                    .addComponent(label1)
                                    .addGap(0, 105, Short.MAX_VALUE)))
                            .addContainerGap())
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblPassword))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 37, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(89, 89, 89)
                            .addComponent(btnLogin)
                            .addContainerGap(98, Short.MAX_VALUE))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap(29, Short.MAX_VALUE)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnLogin)
                    .addGap(55, 55, 55))
                .addComponent(pnlIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel lblEmail;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
    private JTextField txtEmail;
    private JLabel lblError;
    private JButton btnLogin;
    private JPanel pnlIcon;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

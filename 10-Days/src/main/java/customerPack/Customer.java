/*
 * Created by JFormDesigner on Tue Mar 29 18:19:31 TRT 2022
 */

package customerPack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

public class Customer extends JFrame {

    static String name = "";

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setVisible(true);
    }

    public Customer() {
        initComponents();
        txtPassword.setVisible(false);
    }

    private void btnLoginClick(ActionEvent e) {
        String email = txtMail.getText();
        String password = txtPassword.getText();

        if ( email.equals("ali@mail.com") && password.equals("12345") ) {
            name = "Erkan Bilirim";
            Dashboard dashboard = new Dashboard(name);
            dashboard.setVisible(true);
            dispose(); // sınıfı öldür
        }else {
            System.out.println("Giriş Hatalı");
        }
    }

    private void txtMailKeyReleased(KeyEvent e) {
        String email = txtMail.getText();
        if ( email.length() > 2 && email.contains("@") ) {
            txtPassword.setVisible(true);
            pack(); // sayfayı refresh yapmak için kullanıcak bir fnc
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        txtMail = new JTextField();
        button1 = new JButton();
        txtPassword = new JPasswordField();

        //======== this ========
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("User Login");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Arial", Font.PLAIN, 16));

        //---- label2 ----
        label2.setText("E-Mail");

        //---- label3 ----
        label3.setText("Password");

        //---- txtMail ----
        txtMail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtMailKeyReleased(e);
            }
        });

        //---- button1 ----
        button1.setText("Login");
        button1.addActionListener(e -> btnLoginClick(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label1, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label2)
                                .addComponent(label3))
                            .addGap(18, 21, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMail, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                                .addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGap(0, 308, Short.MAX_VALUE)
                            .addComponent(button1)))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(txtMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(button1)
                    .addContainerGap(125, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField txtMail;
    private JButton button1;
    private JPasswordField txtPassword;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

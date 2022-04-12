/*
 * Created by JFormDesigner on Mon Apr 11 13:54:28 TRT 2022
 */

package views;

import javax.swing.border.*;
import models.ServiceImpl;
import models.UserImpl;
import props.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class PasswordClear extends Base {
    UserImpl user=new UserImpl();


    public PasswordClear() {

        initComponents();
        String userName=user.userSingle(Login.email);
        lblpassword.setText(userName);
    }

    private void btnSaveClick(ActionEvent e) {


   String password= txtnewpassword.getText();
        User us=new User(Login.email,password);
        int i=user.userUpdate(us);
        if(i>0){
            if(password.equals("")){
            lblError.setText("Password can not be empty !!!!!");
        }else{lblError.setText("");
            JOptionPane.showMessageDialog(this,"Update password process is successuful");
            dispose();
        }
        }
    }

    private void thisWindowClosing(WindowEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        txtnewpassword = new JTextField();
        label1 = new JLabel();
        button1 = new JButton();
        lblpassword = new JLabel();
        label5 = new JLabel();
        label4 = new JLabel();
        label2 = new JLabel();
        lblError = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();

        //---- txtnewpassword ----
        txtnewpassword.setFont(txtnewpassword.getFont().deriveFont(txtnewpassword.getFont().getSize() + 2f));

        //---- label1 ----
        label1.setText("New Password ");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 4f));
        label1.setBackground(Color.darkGray);
        label1.setForeground(Color.darkGray);

        //---- button1 ----
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 8f));
        button1.setIcon(new ImageIcon(getClass().getResource("/save.png")));
        button1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.lightGray, Color.lightGray, Color.gray, Color.lightGray));
        button1.setBackground(new Color(255, 199, 11));
        button1.setText("SAVE");
        button1.setForeground(Color.white);
        button1.addActionListener(e -> btnSaveClick(e));

        //---- lblpassword ----
        lblpassword.setForeground(Color.darkGray);
        lblpassword.setFont(lblpassword.getFont().deriveFont(lblpassword.getFont().getSize() + 1f));
        lblpassword.setHorizontalAlignment(SwingConstants.RIGHT);

        //---- label5 ----
        label5.setText("LOGIN");
        label5.setFont(new Font("Segoe UI", Font.BOLD, 25));
        label5.setForeground(new Color(79, 77, 77));

        //---- label4 ----
        label4.setText("USER");
        label4.setFont(new Font("Segoe UI", Font.BOLD, 25));
        label4.setForeground(new Color(255, 191, 43));

        //---- label2 ----
        label2.setIcon(new ImageIcon(getClass().getResource("/userlogin.png")));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label2.setBackground(Color.lightGray);

        //---- lblError ----
        lblError.setText("  ");
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        lblError.setForeground(Color.red);
        lblError.setFont(new Font("Segoe UI", Font.BOLD, 16));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(176, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addComponent(lblpassword, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                                .addGap(156, 156, 156)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(0, 113, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(txtnewpassword, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(105, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(lblError, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 27, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblpassword, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label5)
                        .addComponent(label4))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                    .addGap(33, 33, 33)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addComponent(txtnewpassword, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                    .addGap(3, 3, 3)
                    .addComponent(lblError)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(79, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField txtnewpassword;
    private JLabel label1;
    private JButton button1;
    private JLabel lblpassword;
    private JLabel label5;
    private JLabel label4;
    private JLabel label2;
    private JLabel lblError;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

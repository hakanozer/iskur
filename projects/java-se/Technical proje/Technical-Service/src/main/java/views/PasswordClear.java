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
        lblpassword.setText("Mustafa Gültekin");
    }

    private void btnSaveClick(ActionEvent e) {


   String password= txtnewpassword.getText();
        User us=new User(Login.email,password);
        int i=user.userUpdate(us);
        if(i>0){
            JOptionPane.showMessageDialog(this,"Update password process is successuful");
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
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 3f));
        label1.setBackground(Color.darkGray);

        //---- button1 ----
        button1.setText("Save");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 8f));
        button1.setIcon(new ImageIcon(getClass().getResource("/save.png")));
        button1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.gray, Color.gray, Color.gray, Color.gray));
        button1.setBackground(Color.lightGray);
        button1.addActionListener(e -> btnSaveClick(e));

        //---- lblpassword ----
        lblpassword.setForeground(Color.darkGray);
        lblpassword.setText("Sn Mustafa G\u00fcltekin");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(129, 129, 129)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 140, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(102, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                            .addGap(134, 134, 134))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(txtnewpassword, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
                            .addGap(95, 95, 95))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(lblpassword, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblpassword, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    .addGap(39, 39, 39)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(txtnewpassword, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGap(51, 51, 51))
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

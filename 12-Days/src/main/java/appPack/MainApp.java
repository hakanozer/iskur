package appPack;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

public class MainApp extends JFrame {

    String[] arr = { "İstanbul", "Ankara", "İzmir", "Bursa", "Adana" };
    DefaultListModel<String> modelList = new DefaultListModel<>();
    DefaultComboBoxModel<String> modelCombo = new DefaultComboBoxModel<>();

    public static void main(String[] args) {
        new MainApp().setVisible(true);
    }

    public MainApp() {
        initComponents();
        fncDataResult();
        fncListResult();
        cmbCity.setSelectedIndex(3);
        listCity.setSelectedIndex(3);
    }

    private void fncDataResult() {
        modelCombo.addElement("Select City");
        for (int i = 0; i < arr.length; i++) {
            String item = arr[i];
            modelCombo.addElement(item);
        }
        cmbCity.setModel(modelCombo);
    }

    void fncListResult() {

        for( String item : arr ) {
            modelList.addElement(item);
        }
        listCity.setModel(modelList);
    }

    private void sendCtnClick(ActionEvent e) {

        // select item
        Object item = cmbCity.getSelectedItem();
        System.out.println( item );

        int[] selecteds = listCity.getSelectedIndices();
        for ( int index : selecteds ) {
            System.out.println( "index :" + index + " " + modelCombo.getElementAt(index + 1) );
        }
        // array to ArrayList
        List<int[]> ls = Arrays.asList(selecteds);
        System.out.println( ls.get(0)[0] );
    }

    private void listCityMouseClicked(MouseEvent e) {
        int select  = listCity.getSelectedIndex();
        cmbCity.setSelectedIndex(select + 1);
    }

    private void cmbCityItemStateChanged(ItemEvent e) {
        int select = cmbCity.getSelectedIndex();
        listCity.setSelectedIndex(select - 1);
    }

    private void btnAddClick(ActionEvent e) {

        String item = txtData.getText();
        modelList.addElement(item);
        modelCombo.addElement(item);

    }

    private void gonderClick(ActionEvent e) {
        boolean kosu = chkKosu.isSelected();
        boolean gures = chkGures.isSelected();
        boolean yuzme = chkYuzme.isSelected();

        System.out.printf("%s %s %s", kosu, gures, yuzme);
    }

    private void btnRadioSendClik(ActionEvent e) {
        boolean orta = rdoOrta.isSelected();
        boolean lise = rdoLise.isSelected();
        boolean unv = rdoUniversite.isSelected();
        System.out.printf("%s %s %s\n", orta, lise, unv);
        fncIsRadio();
    }

    private void fncIsRadio() {
        Component[] components = pnlOkul.getComponents();
        for ( Component c : components ) {
            if ( c instanceof JRadioButton ) {
                JRadioButton jr = (JRadioButton)  c;
                if ( jr.isSelected() ) {
                    String title = jr.getText();
                    System.out.println( "Select Auto :" + title );
                }
            }
        }
    }

    private void gotoMenuBtnClick(ActionEvent e) {
        Menus menus = new Menus();
        menus.setVisible(true);
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        cmbCity = new JComboBox();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        listCity = new JList();
        txtData = new JTextField();
        btnAdd = new JButton();
        panel1 = new JPanel();
        chkKosu = new JCheckBox();
        chkYuzme = new JCheckBox();
        chkGures = new JCheckBox();
        button2 = new JButton();
        pnlOkul = new JPanel();
        rdoOrta = new JRadioButton();
        rdoLise = new JRadioButton();
        rdoUniversite = new JRadioButton();
        button3 = new JButton();
        tabbedPane1 = new JTabbedPane();
        panel2 = new JPanel();
        comboBox1 = new JComboBox();
        panel3 = new JPanel();
        textField1 = new JTextField();
        panel4 = new JPanel();
        checkBox3 = new JCheckBox();
        panel5 = new JPanel();
        scrollPane2 = new JScrollPane();
        textArea1 = new JTextArea();
        button4 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();

        //---- cmbCity ----
        cmbCity.addItemListener(e -> cmbCityItemStateChanged(e));

        //---- button1 ----
        button1.setText("Send");
        button1.addActionListener(e -> sendCtnClick(e));

        //======== scrollPane1 ========
        {

            //---- listCity ----
            listCity.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    listCityMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(listCity);
        }

        //---- btnAdd ----
        btnAdd.setText("Add");
        btnAdd.addActionListener(e -> btnAddClick(e));

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder("Spor Dallar\u0131n\u0131z"));
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
            .swing.border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing
            .border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.
            Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt.Color.red
            ),panel1. getBorder()));panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override
            public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName(
            )))throw new RuntimeException();}});

            //---- chkKosu ----
            chkKosu.setText("Ko\u015fu");

            //---- chkYuzme ----
            chkYuzme.setText("Y\u00fczme");

            //---- chkGures ----
            chkGures.setText("G\u00fcre\u015f");

            //---- button2 ----
            button2.setText("G\u00f6nder");
            button2.addActionListener(e -> gonderClick(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chkKosu)
                        .addGap(18, 18, 18)
                        .addComponent(chkYuzme)
                        .addGap(18, 18, 18)
                        .addComponent(chkGures)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button2)
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(chkKosu)
                            .addComponent(chkYuzme)
                            .addComponent(chkGures)
                            .addComponent(button2))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }

        //======== pnlOkul ========
        {
            pnlOkul.setBorder(new TitledBorder("Son Bitirdi\u011finiz Okul"));

            //---- rdoOrta ----
            rdoOrta.setText("Orta Okul");

            //---- rdoLise ----
            rdoLise.setText("Lise");

            //---- rdoUniversite ----
            rdoUniversite.setText("\u00dcniversite");

            //---- button3 ----
            button3.setText("G\u00f6nder");
            button3.addActionListener(e -> btnRadioSendClik(e));

            GroupLayout pnlOkulLayout = new GroupLayout(pnlOkul);
            pnlOkul.setLayout(pnlOkulLayout);
            pnlOkulLayout.setHorizontalGroup(
                pnlOkulLayout.createParallelGroup()
                    .addGroup(pnlOkulLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(rdoOrta)
                        .addGap(18, 18, 18)
                        .addComponent(rdoLise)
                        .addGap(18, 18, 18)
                        .addComponent(rdoUniversite)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button3)
                        .addContainerGap())
            );
            pnlOkulLayout.setVerticalGroup(
                pnlOkulLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, pnlOkulLayout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlOkulLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoOrta)
                            .addComponent(rdoLise)
                            .addComponent(rdoUniversite)
                            .addComponent(button3))
                        .addGap(16, 16, 16))
            );
        }

        //======== tabbedPane1 ========
        {

            //======== panel2 ========
            {

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(371, Short.MAX_VALUE))
                );
                panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(156, Short.MAX_VALUE))
                );
            }
            tabbedPane1.addTab("Tab-1", panel2);

            //======== panel3 ========
            {

                GroupLayout panel3Layout = new GroupLayout(panel3);
                panel3.setLayout(panel3Layout);
                panel3Layout.setHorizontalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(406, Short.MAX_VALUE))
                );
                panel3Layout.setVerticalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(156, Short.MAX_VALUE))
                );
            }
            tabbedPane1.addTab("Tab-2", panel3);

            //======== panel4 ========
            {

                //---- checkBox3 ----
                checkBox3.setText("text");

                GroupLayout panel4Layout = new GroupLayout(panel4);
                panel4.setLayout(panel4Layout);
                panel4Layout.setHorizontalGroup(
                    panel4Layout.createParallelGroup()
                        .addGroup(panel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(checkBox3)
                            .addContainerGap(408, Short.MAX_VALUE))
                );
                panel4Layout.setVerticalGroup(
                    panel4Layout.createParallelGroup()
                        .addGroup(panel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(checkBox3)
                            .addContainerGap(165, Short.MAX_VALUE))
                );
            }
            tabbedPane1.addTab("Tab-3", panel4);

            //======== panel5 ========
            {

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(textArea1);
                }

                GroupLayout panel5Layout = new GroupLayout(panel5);
                panel5.setLayout(panel5Layout);
                panel5Layout.setHorizontalGroup(
                    panel5Layout.createParallelGroup()
                        .addGroup(panel5Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                            .addContainerGap())
                );
                panel5Layout.setVerticalGroup(
                    panel5Layout.createParallelGroup()
                        .addGroup(panel5Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(93, Short.MAX_VALUE))
                );
            }
            tabbedPane1.addTab("Tab-4", panel5);
        }

        //---- button4 ----
        button4.setText("Menu");
        button4.addActionListener(e -> gotoMenuBtnClick(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(cmbCity)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(button1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlOkul, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(txtData, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button4, GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE))
                        .addComponent(tabbedPane1))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd)
                        .addComponent(txtData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button4))
                    .addGap(4, 4, 4)
                    .addComponent(cmbCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                        .addComponent(button1, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(pnlOkul, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(tabbedPane1)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());

        //---- buttonGroup1 ----
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(rdoOrta);
        buttonGroup1.add(rdoLise);
        buttonGroup1.add(rdoUniversite);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JComboBox cmbCity;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JList listCity;
    private JTextField txtData;
    private JButton btnAdd;
    private JPanel panel1;
    private JCheckBox chkKosu;
    private JCheckBox chkYuzme;
    private JCheckBox chkGures;
    private JButton button2;
    private JPanel pnlOkul;
    private JRadioButton rdoOrta;
    private JRadioButton rdoLise;
    private JRadioButton rdoUniversite;
    private JButton button3;
    private JTabbedPane tabbedPane1;
    private JPanel panel2;
    private JComboBox comboBox1;
    private JPanel panel3;
    private JTextField textField1;
    private JPanel panel4;
    private JCheckBox checkBox3;
    private JPanel panel5;
    private JScrollPane scrollPane2;
    private JTextArea textArea1;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

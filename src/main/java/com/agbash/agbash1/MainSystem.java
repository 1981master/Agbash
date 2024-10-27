package com.agbash.agbash1;

import com.agbash.component.Message;
import com.agbash.connection.DatabaseConnection;
import com.agbash.model.ModelUser;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class MainSystem extends javax.swing.JFrame {

    private ModelUser user;
    private Connection con;

    public MainSystem(ModelUser user) {
        con = DatabaseConnection.getInstance().getConnection();
        //testConnection();

        this.user = user;
        initComponents();
            try {
                populateStoreComboBox(); 
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading stores: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }

        try {
            Image icon = ImageIO.read(getClass().getResource("/com/agbash/icon/gw.png"));
            setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getContentPane().setBackground(new Color(255, 255, 255));
        lbUser.setText(user.getUserName());
    }

    public MainSystem() throws SQLException {
        initComponents();

        try {
            Image icon = ImageIO.read(getClass().getResource("/com/agbash/icon/gw.png"));
            setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void chooseFileButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
    // Create a JFileChooser instance
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Select a Receipt File");

    // Show the open dialog
    int result = fileChooser.showOpenDialog(this);

    // If a file was selected, display the file name or path
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        selectedFileLabel.setText(selectedFile.getName()); 
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btTab3 = new com.agbash.swing.ButtonOutLine();
        btTab1 = new com.agbash.swing.ButtonOutLine();
        btTab2 = new com.agbash.swing.ButtonOutLine();
        logo = new javax.swing.JLabel();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        myTextField2 = new com.agbash.swing.MyTextField();
        jPanel4 = new javax.swing.JPanel();
        myTextField3 = new com.agbash.swing.MyTextField();
        jPanel2 = new javax.swing.JPanel();
        myTextField1 = new com.agbash.swing.MyTextField();
        lbUser = new javax.swing.JLabel();
        myTextField4 = new com.agbash.swing.MyTextField();
        myTextField5 = new com.agbash.swing.MyTextField();
        myTextField7 = new com.agbash.swing.MyTextField();
        myTextField8 = new com.agbash.swing.MyTextField();
        myTextField9 = new com.agbash.swing.MyTextField();
        myTextField10 = new com.agbash.swing.MyTextField();
        myTextField11 = new com.agbash.swing.MyTextField();
        myTextField12 = new com.agbash.swing.MyTextField();
        myTextField6 = new com.agbash.swing.MyTextField();
        itmName = new javax.swing.JTextField();
        itmBarcode = new javax.swing.JTextField();
        itBoughtPrice = new javax.swing.JTextField();
        itmBoughtPrice = new javax.swing.JTextField();
        itmProfit = new javax.swing.JTextField();
        itmSellPrice = new javax.swing.JTextField();
        itmShelf = new javax.swing.JTextField();
        itmWeight = new javax.swing.JTextField();
        itmSize = new javax.swing.JTextField();
        buttonOutLine1 = new com.agbash.swing.ButtonOutLine();
        selectedFileLabel = new javax.swing.JLabel();
        myTextField13 = new com.agbash.swing.MyTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        buttonOutLine2 = new com.agbash.swing.ButtonOutLine();
        jTextField10 = new javax.swing.JTextField();
        myTextField14 = new com.agbash.swing.MyTextField();
        newStoreMessage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        myTextField15 = new com.agbash.swing.MyTextField();
        itmReceiptBarcode = new javax.swing.JTextField();
        itmSave = new com.agbash.swing.ButtonOutLine();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MaStEr");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(155, 222, 208));

        btTab3.setText("Insert New Item");
        btTab3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTab3ActionPerformed(evt);
            }
        });

        btTab1.setText("Tab1");
        btTab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTab1ActionPerformed(evt);
            }
        });

        btTab2.setText("Tab2");
        btTab2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTab2ActionPerformed(evt);
            }
        });

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/GW100.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(logo)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btTab3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btTab1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btTab2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btTab1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btTab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btTab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(354, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        myTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField2.setText("TAB2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(myTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(603, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(myTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(418, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("tab2", jPanel3);

        myTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField3.setText("TAB3");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(myTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(626, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(myTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(411, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Tab3", jPanel4);

        jPanel2.setBackground(new java.awt.Color(10, 163, 243));

        myTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField1.setText("Item Name:   ");
        myTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myTextField1ActionPerformed(evt);
            }
        });

        lbUser.setText("Besm ALALH");

        myTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField4.setText("Desired Profit %: ");

        myTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField5.setText("Shelf Location:   ");

        myTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField7.setText("Bought Price: ");

        myTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField8.setText("Bought Price: ");

        myTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField9.setText("Item Barcode:");

        myTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField10.setText("Sell Price:   ");

        myTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField11.setText("Weight:");
        myTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myTextField11ActionPerformed(evt);
            }
        });

        myTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField12.setText("Size:");

        myTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField6.setText("Upload Receipt");

        itmName.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        itmSellPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmSellPriceActionPerformed(evt);
            }
        });

        itmWeight.setText("                                             : pound");
        itmWeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmWeightActionPerformed(evt);
            }
        });

        itmSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmSizeActionPerformed(evt);
            }
        });

        buttonOutLine1.setBackground(new java.awt.Color(12, 94, 29));
        buttonOutLine1.setForeground(new java.awt.Color(255, 255, 255));
        buttonOutLine1.setText("Choose File");
        buttonOutLine1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonOutLine1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOutLine1ActionPerformed(evt);
            }
        });

        selectedFileLabel.setBackground(new java.awt.Color(255, 255, 255));
        selectedFileLabel.setForeground(new java.awt.Color(255, 255, 255));
        selectedFileLabel.setText("No file chosen");

        myTextField13.setText("Bought from(Store):");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chose Store", "Walmart", "GoodWill", "Thrift Store", "Grage Sale", "Over Seas" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        buttonOutLine2.setBackground(new java.awt.Color(12, 94, 29));
        buttonOutLine2.setForeground(new java.awt.Color(255, 255, 255));
        buttonOutLine2.setText("Add Store");
        buttonOutLine2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonOutLine2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOutLine2ActionPerformed(evt);
            }
        });

        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        myTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField14.setText("Missing Store");

        newStoreMessage.setBackground(new java.awt.Color(255, 255, 255));
        newStoreMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        newStoreMessage.setForeground(new java.awt.Color(255, 0, 0));

        jLabel1.setText("Welcome Back:");

        myTextField15.setText("Receipt Number/Barcode:");

        itmSave.setForeground(new java.awt.Color(255, 255, 255));
        itmSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/save.png"))); // NOI18N
        itmSave.setText("Save Item");
        itmSave.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(myTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myTextField9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myTextField12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myTextField14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myTextField13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonOutLine2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(itmProfit, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itmBoughtPrice, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itBoughtPrice, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itmShelf, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itmName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itmBarcode, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itmSellPrice)
                            .addComponent(itmWeight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(itmSize, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(buttonOutLine1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectedFileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(myTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(itmReceiptBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(newStoreMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(173, 173, 173)
                                        .addComponent(itmSave, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(178, Short.MAX_VALUE))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(itmName)
                            .addComponent(myTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(itmBarcode)
                            .addComponent(myTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(itmReceiptBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(myTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(itBoughtPrice)
                    .addComponent(itmSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(itmBoughtPrice)
                    .addComponent(myTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(itmProfit)
                    .addComponent(myTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(myTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itmSellPrice))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(myTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itmShelf, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(myTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itmWeight))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(myTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itmSize))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(myTextField13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1))
                    .addComponent(newStoreMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonOutLine2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(myTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonOutLine1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectedFileLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        jTabbedPane.addTab("Save Items", jPanel2);

        getContentPane().add(jTabbedPane);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btTab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTab1ActionPerformed
        jTabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_btTab1ActionPerformed

    private void btTab2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTab2ActionPerformed
        jTabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_btTab2ActionPerformed

    private void btTab3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTab3ActionPerformed
        jTabbedPane.setSelectedIndex(2);
    }//GEN-LAST:event_btTab3ActionPerformed

    private void myTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myTextField1ActionPerformed

    private void myTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myTextField11ActionPerformed

    private void itmSellPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmSellPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itmSellPriceActionPerformed

    private void itmWeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmWeightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itmWeightActionPerformed

    private void itmSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itmSizeActionPerformed

    private void buttonOutLine1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOutLine1ActionPerformed
        this.chooseFileButtonActionPerformed(evt);
    }//GEN-LAST:event_buttonOutLine1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void buttonOutLine2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOutLine2ActionPerformed
        
        Message ms = new Message();
        try {
            this.addStore();
            this.populateStoreComboBox();
            newStoreMessage.setText("Select new added Store form Stores list.");
            //ms.showMessage(Message.MessageType.SUCCESS, "Select new added Store form Stores list.");
        } catch (SQLException ex) {
            Logger.getLogger(MainSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonOutLine2ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    public static void main(ModelUser user) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainSystem(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.agbash.swing.ButtonOutLine btTab1;
    private com.agbash.swing.ButtonOutLine btTab2;
    private com.agbash.swing.ButtonOutLine btTab3;
    private com.agbash.swing.ButtonOutLine buttonOutLine1;
    private com.agbash.swing.ButtonOutLine buttonOutLine2;
    private javax.swing.JTextField itBoughtPrice;
    private javax.swing.JTextField itmBarcode;
    private javax.swing.JTextField itmBoughtPrice;
    private javax.swing.JTextField itmName;
    private javax.swing.JTextField itmProfit;
    private javax.swing.JTextField itmReceiptBarcode;
    private com.agbash.swing.ButtonOutLine itmSave;
    private javax.swing.JTextField itmSellPrice;
    private javax.swing.JTextField itmShelf;
    private javax.swing.JTextField itmSize;
    private javax.swing.JTextField itmWeight;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JLabel lbUser;
    private javax.swing.JLabel logo;
    private com.agbash.swing.MyTextField myTextField1;
    private com.agbash.swing.MyTextField myTextField10;
    private com.agbash.swing.MyTextField myTextField11;
    private com.agbash.swing.MyTextField myTextField12;
    private com.agbash.swing.MyTextField myTextField13;
    private com.agbash.swing.MyTextField myTextField14;
    private com.agbash.swing.MyTextField myTextField15;
    private com.agbash.swing.MyTextField myTextField2;
    private com.agbash.swing.MyTextField myTextField3;
    private com.agbash.swing.MyTextField myTextField4;
    private com.agbash.swing.MyTextField myTextField5;
    private com.agbash.swing.MyTextField myTextField6;
    private com.agbash.swing.MyTextField myTextField7;
    private com.agbash.swing.MyTextField myTextField8;
    private com.agbash.swing.MyTextField myTextField9;
    private javax.swing.JLabel newStoreMessage;
    private javax.swing.JLabel selectedFileLabel;
    // End of variables declaration//GEN-END:variables

    private void addStore() throws SQLException{

        PreparedStatement p = null;
        String storeName = jTextField10.getText().trim();

        try {
            p = con.prepareStatement(
                    "INSERT INTO `store` (STORENAME, LOCATION) VALUES (?, ?)"
            );

            // Set parameters for the prepared statement
            p.setString(1, storeName);
            p.setString(2, "");//inserting empty stirng for store location.
           

            // Execute the insert
            int affectedRows = p.executeUpdate();

            if (affectedRows > 0) {

                System.out.println("Successfully inserted new Store: "+storeName);
                jTextField10.setText("");
                populateStoreComboBox(); 
                //jComboBox1.setSelectedItem(storeName);
                this.setSpecificStore(storeName);

            } else {
                System.out.println("FATAL insertion new store: "+ storeName);
            }
        } catch (SQLException e) {

            System.out.println("Issue with DB Connection. while inserting new store.");

        } finally {

            if (p != null) {
                p.close();
            }
        }
    }
    
    private void populateStoreComboBox() throws SQLException {
        ArrayList<String> storeNames = getStoreNames();
        
        jComboBox1.setModel(new DefaultComboBoxModel<>(storeNames.toArray(new String[0])));
    }

    private ArrayList<String> getStoreNames() throws SQLException {
        ArrayList<String> storeNames = new ArrayList<>();

        String query = "SELECT STORENAME FROM STORE";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            storeNames.add("Select/Add Store...");//set first option for prompt
            System.out.println("Executing query: " + query);
            while (rs.next()) {
                String storeName = rs.getString("STORENAME");
                storeNames.add(storeName);
            }
        }

        System.out.println("Total stores retrieved: " + storeNames.size()); // Output the number of stores retrieved
        return storeNames;
    }
    
    private void setSpecificStore(String storeName) {
    DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) jComboBox1.getModel();
    
    // Check if the store exists in the model
    if (model.getIndexOf(storeName) != -1) {
        jComboBox1.setSelectedItem(storeName);
        jComboBox1.revalidate();
        jComboBox1.repaint();
    } else {
        System.out.println("Store not found: " + storeName); // Debug output
    }
}


//    private void testConnection() {
//    try {
//        // Assuming 'con' is your Connection object
//        if (con != null && !con.isClosed()) {
//            System.out.println("Database connection is active.");
//        } else {
//            System.out.println("Failed to connect to the database.");
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}




}

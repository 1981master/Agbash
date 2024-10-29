package com.agbash.agbash1;

import com.agbash.component.Message;
import com.agbash.connection.DatabaseConnection;
import com.agbash.model.ModelUser;
import com.agbash.service.AlternatingRowColorRenderer;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class MainSystem extends javax.swing.JFrame {

    private ModelUser user;
    private Connection con;

    public MainSystem(ModelUser user) {
        con = DatabaseConnection.getInstance().getConnection();

        this.user = user;
        initComponents();  

        jTable1.setDefaultRenderer(Object.class, new AlternatingRowColorRenderer());
        initListeners();
        populateItemTable();
        jTable1.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));

        jTable1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

                    int itemId = (Integer) model.getValueAt(row, 0); // Get ITEMID
                    String newValue = model.getValueAt(row, column).toString(); // Get new value

                    if(column > 1 || column <= 9){
                        updateItemInDatabase(itemId, column, newValue);
                    }
                    
                }
            }
        });

        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Check for double click
                    int row = jTable1.rowAtPoint(e.getPoint());
                    int column = jTable1.columnAtPoint(e.getPoint());
                    if (row >= 0 && column >= 0) {
                        Object currentValue = jTable1.getValueAt(row, column);
                        String newValue = JOptionPane.showInputDialog(
                                MainSystem.this,
                                "Enter new value for " + currentValue + ":",
                                currentValue
                        );
                        if (newValue != null) { // Check if user didn't cancel
                            // Update the table and database
                            jTable1.setValueAt(newValue, row, column);
                        if(column > 1 || column <= 9){
                            updateItemInDatabase((Integer) jTable1.getValueAt(row, 0), column, newValue);
                        }
                            
                        }
                    }
                }
            }
        });

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
        initListeners();
        jTable1.setDefaultRenderer(Object.class, new AlternatingRowColorRenderer());

        jTable1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

                    int itemId = (Integer) model.getValueAt(row, 0); // Get ITEMID
                    String newValue = model.getValueAt(row, column).toString(); // Get new value
                    if(column > 1 || column <= 9){
                        updateItemInDatabase(itemId, column, newValue);
                    }
                    
                }
            }
        });

        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Check for double click
                    int row = jTable1.rowAtPoint(e.getPoint());
                    int column = jTable1.columnAtPoint(e.getPoint());
                    if (row >= 0 && column >= 0) {
                        Object currentValue = jTable1.getValueAt(row, column);
                        String newValue = JOptionPane.showInputDialog(
                                MainSystem.this,
                                "Enter new value for " + currentValue + ":",
                                currentValue
                        );
                        if (newValue != null) { // Check if user didn't cancel
                            // Update the table and database
                            jTable1.setValueAt(newValue, row, column);
                        if(column > 1 || column <= 9){
                            updateItemInDatabase((Integer) jTable1.getValueAt(row, 0), column, newValue);
                        }
                            
                        }
                    }
                }
            }
        });

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
        jPanel2 = new javax.swing.JPanel();
        myTextField1 = new com.agbash.swing.MyTextField();
        lbUser = new javax.swing.JLabel();
        myTextField4 = new com.agbash.swing.MyTextField();
        myTextField5 = new com.agbash.swing.MyTextField();
        myTextField7 = new com.agbash.swing.MyTextField();
        myTextField9 = new com.agbash.swing.MyTextField();
        myTextField10 = new com.agbash.swing.MyTextField();
        myTextField11 = new com.agbash.swing.MyTextField();
        myTextField12 = new com.agbash.swing.MyTextField();
        myTextField6 = new com.agbash.swing.MyTextField();
        itmName = new javax.swing.JTextField();
        itmBarcode = new javax.swing.JTextField();
        itmBoughtPrice = new javax.swing.JTextField();
        itmProfit = new javax.swing.JTextField();
        itmShipingCost = new javax.swing.JTextField();
        itmShelf = new javax.swing.JTextField();
        itmWeight = new javax.swing.JTextField();
        itmSize = new javax.swing.JTextField();
        buttonOutLine1 = new com.agbash.swing.ButtonOutLine();
        selectedFileLabel = new javax.swing.JLabel();
        myTextField13 = new com.agbash.swing.MyTextField();
        itmStore = new javax.swing.JComboBox<>();
        buttonOutLine2 = new com.agbash.swing.ButtonOutLine();
        jTextField10 = new javax.swing.JTextField();
        myTextField14 = new com.agbash.swing.MyTextField();
        newStoreMessage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        myTextField15 = new com.agbash.swing.MyTextField();
        itmReceiptBarcode = new javax.swing.JTextField();
        itmSave = new com.agbash.swing.ButtonOutLine();
        itmTotalAmountForSale = new javax.swing.JLabel();
        buttonOutLine3 = new com.agbash.swing.ButtonOutLine();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        itmSearch = new com.agbash.swing.MyTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnUpdate = new com.agbash.swing.ButtonOutLine();
        btnDelete = new com.agbash.swing.ButtonOutLine();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MaStEr");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(155, 222, 208));
        jPanel1.setPreferredSize(new java.awt.Dimension(150, 620));

        btTab3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/warehouse.png"))); // NOI18N
        btTab3.setText("Items");
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

        btTab2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/save.png"))); // NOI18N
        btTab2.setText("Save Item");
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
                .addContainerGap()
                .addComponent(btTab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btTab2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btTab3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(btTab3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btTab1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btTab2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(189, Short.MAX_VALUE))
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
                .addContainerGap(708, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(myTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(458, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("tab2", jPanel3);

        jPanel2.setBackground(new java.awt.Color(10, 163, 243));

        myTextField1.setEditable(false);
        myTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField1.setText("Item Name:   ");
        myTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myTextField1ActionPerformed(evt);
            }
        });

        lbUser.setText("Besm ALALH");

        myTextField4.setEditable(false);
        myTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField4.setText("Selling for $: ");

        myTextField5.setEditable(false);
        myTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField5.setText("Shelf Location:   ");

        myTextField7.setEditable(false);
        myTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField7.setText("Bought Price: ");
        myTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myTextField7ActionPerformed(evt);
            }
        });

        myTextField9.setEditable(false);
        myTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField9.setText("Item Barcode:");

        myTextField10.setEditable(false);
        myTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField10.setText("Shiping Cost $:");

        myTextField11.setEditable(false);
        myTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField11.setText("Weight:");
        myTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myTextField11ActionPerformed(evt);
            }
        });

        myTextField12.setEditable(false);
        myTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField12.setText("Size:");

        myTextField6.setEditable(false);
        myTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField6.setText("Upload Receipt");

        itmName.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        itmBoughtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmBoughtPriceActionPerformed(evt);
            }
        });

        itmShipingCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmShipingCostActionPerformed(evt);
            }
        });

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

        myTextField13.setEditable(false);
        myTextField13.setText("Bought (Store):");

        itmStore.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chose Store", "Walmart", "GoodWill", "Thrift Store", "Grage Sale", "Over Seas" }));
        itmStore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmStoreActionPerformed(evt);
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

        myTextField14.setEditable(false);
        myTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        myTextField14.setText("Missing Store");

        newStoreMessage.setBackground(new java.awt.Color(255, 255, 255));
        newStoreMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        newStoreMessage.setForeground(new java.awt.Color(255, 0, 0));

        jLabel1.setText("Welcome Back:");

        myTextField15.setEditable(false);
        myTextField15.setText("Receipt Number/Barcode:");

        itmSave.setForeground(new java.awt.Color(255, 255, 255));
        itmSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/save.png"))); // NOI18N
        itmSave.setText("Save Item");
        itmSave.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        itmSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmSaveActionPerformed(evt);
            }
        });

        itmTotalAmountForSale.setBackground(new java.awt.Color(255, 255, 255));
        itmTotalAmountForSale.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        itmTotalAmountForSale.setForeground(new java.awt.Color(255, 0, 0));

        buttonOutLine3.setBackground(new java.awt.Color(12, 94, 29));
        buttonOutLine3.setForeground(new java.awt.Color(255, 255, 255));
        buttonOutLine3.setText("Display Amount for Sale");
        buttonOutLine3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonOutLine3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOutLine3ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(177, 235, 198));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/item.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/barcode.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/mony.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/mony.png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/mony.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/weight.png"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/shelf.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/store.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/size.png"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/store.png"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/receipt.png"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/barcode-reader.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/mony.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(myTextField10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                            .addComponent(myTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(myTextField7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(myTextField9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(myTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(myTextField14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(myTextField13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(myTextField12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(myTextField11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(myTextField6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(myTextField5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))))
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
                            .addComponent(itmName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itmBarcode, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itmShipingCost)
                            .addComponent(itmWeight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(itmSize, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(buttonOutLine1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectedFileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(itmStore, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(itmShelf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(itmSave, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(newStoreMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel14)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(buttonOutLine3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(myTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(itmReceiptBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(itmTotalAmountForSale, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(112, Short.MAX_VALUE))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(myTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(itmName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(itmBarcode)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(myTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(itmBoughtPrice)
                                    .addComponent(myTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(itmProfit)
                                    .addComponent(myTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(myTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(itmShipingCost)
                                    .addComponent(jLabel4)))
                            .addComponent(itmTotalAmountForSale, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(itmReceiptBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(myTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonOutLine3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(108, 108, 108)))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itmShelf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(myTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itmSave, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myTextField11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itmWeight))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(myTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itmSize))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(myTextField13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(itmStore)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(newStoreMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonOutLine2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(myTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(myTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonOutLine1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(selectedFileLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );

        jTabbedPane.addTab("Save Items", jPanel2);

        itmSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        itmSearch.setText("Insert Item Barcode");
        itmSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(224, 255, 242));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/search.png"))); // NOI18N
        jButton1.setBorder(new javax.swing.border.MatteBorder(null));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setBackground(new java.awt.Color(238, 184, 80));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEMID", "ITEM_BARCODE", "ITEM_NAME", "BOUGHT_PRICE", "SELL_PRICE", "SHELF_LOCATION", "WEIGHT", "SIZE", "BOUGHT_FROM_STORE", "SOLD_FOR"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateField(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(50);
        }

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/update.png"))); // NOI18N
        btnUpdate.setText("Update Item");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/agbash/icon/delete.png"))); // NOI18N
        btnDelete.setText("Delete Item");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
                        .addComponent(itmSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(0, 0, 0)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(itmSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane.addTab("All Items", jPanel4);

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

    private void itmSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmSaveActionPerformed
        try {
            this.saveItem();
        } catch (SQLException ex) {
            Logger.getLogger(MainSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_itmSaveActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

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

    private void itmStoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmStoreActionPerformed

    }//GEN-LAST:event_itmStoreActionPerformed

    private void buttonOutLine1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOutLine1ActionPerformed
        this.chooseFileButtonActionPerformed(evt);
    }//GEN-LAST:event_buttonOutLine1ActionPerformed

    private void itmSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itmSizeActionPerformed

    private void itmWeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmWeightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itmWeightActionPerformed

    private void itmShipingCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmShipingCostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itmShipingCostActionPerformed

    private void itmBoughtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmBoughtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itmBoughtPriceActionPerformed

    private void myTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myTextField11ActionPerformed

    private void myTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myTextField1ActionPerformed

    private void buttonOutLine3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOutLine3ActionPerformed
        String boughtPrice = "0.0", sellForPrice = "0.0", shippingCost = "0.0";
        double total = 0.0;
        try {
            boughtPrice = itmBoughtPrice.getText().trim();
        } catch (Exception e) {
            System.out.println("FATAL with boughtPrice \n" + e);
        }
        try {
            sellForPrice = itmProfit.getText().trim();
            System.out.println("sellForPrice " + sellForPrice);
        } catch (Exception e) {
            System.out.println("FATAL with sellForPrice ");
        }
        try {
            shippingCost = itmShipingCost.getText().trim();
        } catch (Exception e) {
            System.out.println("FATAL with shippingCost ");
        }

        total = Double.parseDouble(boughtPrice)
                + Double.parseDouble(sellForPrice)
                + Double.parseDouble(shippingCost);
        String totalValue = String.format("%.2f", total);
        itmTotalAmountForSale.setText(totalValue);
    }//GEN-LAST:event_buttonOutLine3ActionPerformed

    private void myTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myTextField7ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        this.updateItem(evt);
        this.populateTableAfterNewItemInsertion(evt);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        this.deleteItem(evt);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        this.searchItem();
    }//GEN-LAST:event_searchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.searchItem();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void updateField(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateField
        // TODO add your handling code here:
    }//GEN-LAST:event_updateField

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
    private com.agbash.swing.ButtonOutLine btnDelete;
    private com.agbash.swing.ButtonOutLine btnUpdate;
    private com.agbash.swing.ButtonOutLine buttonOutLine1;
    private com.agbash.swing.ButtonOutLine buttonOutLine2;
    private com.agbash.swing.ButtonOutLine buttonOutLine3;
    private javax.swing.JTextField itmBarcode;
    private javax.swing.JTextField itmBoughtPrice;
    private javax.swing.JTextField itmName;
    private javax.swing.JTextField itmProfit;
    private javax.swing.JTextField itmReceiptBarcode;
    private com.agbash.swing.ButtonOutLine itmSave;
    private com.agbash.swing.MyTextField itmSearch;
    private javax.swing.JTextField itmShelf;
    private javax.swing.JTextField itmShipingCost;
    private javax.swing.JTextField itmSize;
    private javax.swing.JComboBox<String> itmStore;
    private javax.swing.JLabel itmTotalAmountForSale;
    private javax.swing.JTextField itmWeight;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTable1;
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
    private com.agbash.swing.MyTextField myTextField4;
    private com.agbash.swing.MyTextField myTextField5;
    private com.agbash.swing.MyTextField myTextField6;
    private com.agbash.swing.MyTextField myTextField7;
    private com.agbash.swing.MyTextField myTextField9;
    private javax.swing.JLabel newStoreMessage;
    private javax.swing.JLabel selectedFileLabel;
    // End of variables declaration//GEN-END:variables

    private void saveItem() throws SQLException {

        PreparedStatement p = null;
        String receiteBarcode, itemName, boughtPrice, sellPrice, shelf, weight, size, boughtFromStore, soldFor;

        receiteBarcode = itmReceiptBarcode.getText().trim();
        itemName = itmName.getText().trim();
        boughtPrice = itmBoughtPrice.getText().trim();
        sellPrice = itmShipingCost.getText().trim();
        shelf = itmShelf.getText().trim();
        weight = itmWeight.getText().trim();
        size = itmSize.getText().trim();
        boughtFromStore = (String) itmStore.getSelectedItem();
        soldFor = (String) itmTotalAmountForSale.getText().trim();

        try {
            p = con.prepareStatement(
                    "INSERT INTO `item` (ITEM_BARCODE, ITEM_NAME, BOUGHT_PRICE, SELL_PRICE, SHELF_LOCATION, "
                    + "WEIGHT, SIZE, BOUGHT_FROM_STORE, SOLD_FOR) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            p.setString(1, receiteBarcode);
            p.setString(2, itemName);
            p.setString(3, boughtPrice);
            p.setString(4, sellPrice);
            p.setString(5, shelf);
            p.setString(6, weight);
            p.setString(7, size);
            p.setString(8, size);
            p.setString(9, soldFor);

            // Execute the insert
            int affectedRows = p.executeUpdate();

            if (affectedRows > 0) {

                System.out.println("Successfully inserted new Item: " + itemName);
                jTextField10.setText("");
                populateStoreComboBox();

            } else {
                System.out.println("FATAL insertion new Item: " + itemName);
            }
        } catch (SQLException e) {

            System.out.println("Issue with DB Connection. while inserting new Item.\n" + e);

        } finally {

            if (p != null) {
                p.close();
            }
        }
    }

    private void addStore() throws SQLException {

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

                System.out.println("Successfully inserted new Store: " + storeName);
                jTextField10.setText("");
                populateStoreComboBox();
                //jComboBox1.setSelectedItem(storeName);
                this.setSpecificStore(storeName);

            } else {
                System.out.println("FATAL insertion new store: " + storeName);
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

        itmStore.setModel(new DefaultComboBoxModel<>(storeNames.toArray(new String[0])));
    }

    private ArrayList<String> getStoreNames() throws SQLException {
        ArrayList<String> storeNames = new ArrayList<>();

        String query = "SELECT STORENAME FROM STORE";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
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
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) itmStore.getModel();

        // Check if the store exists in the model
        if (model.getIndexOf(storeName) != -1) {
            itmStore.setSelectedItem(storeName);
            itmStore.revalidate();
            itmStore.repaint();
        } else {
            System.out.println("Store not found: " + storeName); // Debug output
        }
    }

    public ResultSet fetchItems() throws SQLException {
        PreparedStatement p = null;
        ResultSet r = null;

        try {
            String query = "SELECT ITEMID, RECEIPTID, ITEM_BARCODE, ITEM_NAME, BOUGHT_PRICE, "
                    + "SELL_PRICE, SHELF_LOCATION, WEIGHT, SIZE, BOUGHT_FROM_STORE, SOLD_FOR "
                    + "FROM ITEM";
            p = con.prepareStatement(query);
            r = p.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error fetching items: " + e.getMessage());
        }
        return r;
    }

    private void populateItemTable() {
        try {
            ResultSet rs = this.fetchItems();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Clear any existing rows
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ITEMID"),
                    rs.getString("ITEM_BARCODE"),
                    rs.getString("ITEM_NAME"),
                    rs.getString("BOUGHT_PRICE"),
                    rs.getString("SELL_PRICE"),
                    rs.getString("SHELF_LOCATION"),
                    rs.getString("WEIGHT"),
                    rs.getString("SIZE"),
                    rs.getString("BOUGHT_FROM_STORE"),
                    rs.getString("SOLD_FOR")
                });
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error populating item table: " + e.getMessage());
        }
    }

    private void updateItem(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Retrieve the ITEMID to identify the row in the database
            int itemId = (Integer) jTable1.getValueAt(selectedRow, 0);  // Assuming ITEMID is the first column

            // Prompt for new values for each field
            String itemBarcode = JOptionPane.showInputDialog(this, "Enter new barcode:", jTable1.getValueAt(selectedRow, 1));
            String itemName = JOptionPane.showInputDialog(this, "Enter new item name:", jTable1.getValueAt(selectedRow, 2));
            String boughtPrice = JOptionPane.showInputDialog(this, "Enter new bought price:", jTable1.getValueAt(selectedRow, 3));
            String sellPrice = JOptionPane.showInputDialog(this, "Enter new sell price:", jTable1.getValueAt(selectedRow, 4));
            String shelfLocation = JOptionPane.showInputDialog(this, "Enter new shelf location:", jTable1.getValueAt(selectedRow, 5));
            String weight = JOptionPane.showInputDialog(this, "Enter new weight:", jTable1.getValueAt(selectedRow, 6));
            String size = JOptionPane.showInputDialog(this, "Enter new size:", jTable1.getValueAt(selectedRow, 7));
            String boughtFromStore = JOptionPane.showInputDialog(this, "Enter new store:", jTable1.getValueAt(selectedRow, 8));
            String soldFor = JOptionPane.showInputDialog(this, "Enter new sold for price:", jTable1.getValueAt(selectedRow, 9));

            // Prepare the SQL update statement without RECEIPTID
            String query = "UPDATE ITEM SET ITEM_BARCODE = ?, ITEM_NAME = ?, BOUGHT_PRICE = ?, "
                    + "SELL_PRICE = ?, SHELF_LOCATION = ?, WEIGHT = ?, SIZE = ?, BOUGHT_FROM_STORE = ?, SOLD_FOR = ? "
                    + "WHERE ITEMID = ?";

            try (PreparedStatement p = con.prepareStatement(query)) {
                // Set new values for each placeholder in the SQL statement
                p.setString(1, itemBarcode);
                p.setString(2, itemName);
                p.setString(3, boughtPrice);
                p.setString(4, sellPrice);
                p.setString(5, shelfLocation);
                p.setString(6, weight);
                p.setString(7, size);
                p.setString(8, boughtFromStore);
                p.setString(9, soldFor);
                p.setInt(10, itemId);  // Set ITEMID as the identifier for the WHERE clause

                int rowsAffected = p.executeUpdate();

                if (rowsAffected > 0) {
                    // Update the JTable to reflect changes
                    jTable1.setValueAt(itemBarcode, selectedRow, 1);
                    jTable1.setValueAt(itemName, selectedRow, 2);
                    jTable1.setValueAt(boughtPrice, selectedRow, 3);
                    jTable1.setValueAt(sellPrice, selectedRow, 4);
                    jTable1.setValueAt(shelfLocation, selectedRow, 5);
                    jTable1.setValueAt(weight, selectedRow, 6);
                    jTable1.setValueAt(size, selectedRow, 7);
                    jTable1.setValueAt(boughtFromStore, selectedRow, 8);
                    jTable1.setValueAt(soldFor, selectedRow, 9);

                    JOptionPane.showMessageDialog(this, "Row updated successfully.");
                }
            }
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error updating row: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateTableAfterNewItemInsertion(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Retrieve current values from the selected row for the prompts
        int itemId = (Integer) jTable1.getValueAt(selectedRow, 0);  // Assuming ITEMID is the first column
        String itemBarcode = (String) jTable1.getValueAt(selectedRow, 1);
        String itemName = (String) jTable1.getValueAt(selectedRow, 2);
        String boughtPrice = (String) jTable1.getValueAt(selectedRow, 3);
        String sellPrice = (String) jTable1.getValueAt(selectedRow, 4);
        String shelfLocation = (String) jTable1.getValueAt(selectedRow, 5);
        String weight = (String) jTable1.getValueAt(selectedRow, 6);
        String size = (String) jTable1.getValueAt(selectedRow, 7);
        String boughtFromStore = (String) jTable1.getValueAt(selectedRow, 8);
        String soldFor = (String) jTable1.getValueAt(selectedRow, 9);

        // Prompt user for new values
        itemBarcode = JOptionPane.showInputDialog(this, "Enter new barcode:", itemBarcode);
        itemName = JOptionPane.showInputDialog(this, "Enter new item name:", itemName);
        boughtPrice = JOptionPane.showInputDialog(this, "Enter new bought price:", boughtPrice);
        sellPrice = JOptionPane.showInputDialog(this, "Enter new sell price:", sellPrice);
        shelfLocation = JOptionPane.showInputDialog(this, "Enter new shelf location:", shelfLocation);
        weight = JOptionPane.showInputDialog(this, "Enter new weight:", weight);
        size = JOptionPane.showInputDialog(this, "Enter new size:", size);
        boughtFromStore = JOptionPane.showInputDialog(this, "Enter new store:", boughtFromStore);
        soldFor = JOptionPane.showInputDialog(this, "Enter new sold for price:", soldFor);

        // Update the database with the new values
        //if (updateItemInDatabase(itemId, itemBarcode, itemName, boughtPrice, sellPrice, shelfLocation, weight, size, boughtFromStore, soldFor)) {
        // Reload the table to display the updated values
        populateItemTable();
        JOptionPane.showMessageDialog(this, "Row updated successfully.");
        //}
    }

    private void deleteItem(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int itemId = (Integer) jTable1.getValueAt(selectedRow, 0);  // Assuming ITEMID is the first column

        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this item?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                // Delete from the database
                String query = "DELETE FROM ITEM WHERE ITEMID = ?";
                try (PreparedStatement p = con.prepareStatement(query)) {
                    p.setInt(1, itemId);
                    int rowsAffected = p.executeUpdate();

                    if (rowsAffected > 0) {
                        // Remove from table model
                        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(this, "Row deleted successfully.");
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error deleting row: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void initListeners() {
        jTabbedPane.addChangeListener(evt -> {
            if (jTabbedPane.getSelectedIndex() == 2) {
                populateItemTable(); // Reload table data when switching to the display tab
            }
        });
    }

    private void searchItem() {
        String searchText = itmSearch.getText().trim();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);

        if (searchText.isEmpty()) {
            sorter.setRowFilter(null); // Show all rows if the search field is empty
        } else {
            // Create a list of filters
            List<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<>();

            // Add regex filters for ITEM_BARCODE and ITEM_NAME
            filters.add(RowFilter.regexFilter("(?i)" + searchText, 2)); // Filter for ITEM_BARCODE (column index 2)
            filters.add(RowFilter.regexFilter("(?i)" + searchText, 3)); // Filter for ITEM_NAME (column index 3)

            // Combine filters using orFilter
            RowFilter<DefaultTableModel, Object> combinedFilter = RowFilter.orFilter(filters);
            sorter.setRowFilter(combinedFilter);
        }
    }

    private void updateItemInDatabase(int itemId, int column, String newValue) {
        // Prepare your SQL update statement here based on the column index
        // This is a simplified example; adjust as needed based on your table structure
        String columnName = ""; // Determine column name based on the column index
        switch (column) {
            case 1:
                columnName = "ITEM_BARCODE";
                break;
            case 2:
                columnName = "ITEM_NAME";
                break;
            case 3:
                columnName = "BOUGHT_PRICE";
                break;
            case 4:
                columnName = "SELL_PRICE";
                break;
            case 5:
                columnName = "SHELF_LOCATION";
                break;
            case 6:
                columnName = "WEIGHT";
                break;
            case 7:
                columnName = "SIZE";
                break;
            case 8:
                columnName = "BOUGHT_FROM_STORE";
                break;
            case 9:
                columnName = "SOLD_FOR";
                break;

        }

        // Update the database using a PreparedStatement
        String query = "UPDATE ITEM SET " + columnName + " = ? WHERE ITEMID = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, newValue); // Set the new value
            pstmt.setInt(2, itemId); // Set the ITEMID
            pstmt.executeUpdate(); // Execute the update
        } catch (SQLException e) {
            // Handle SQL exceptions here
            e.printStackTrace();
        }
    }

}

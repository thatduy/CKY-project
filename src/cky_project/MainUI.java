/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cky_project;

import Model.CellInfo;
import Model.NodeCNF;
import Model.TableModel;
import Util.RowNumberTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ASUS
 */
public class MainUI extends javax.swing.JFrame {

    /**
     * Creates new form MainUI
     */
    private TableModel tableModel;
    private ArrayList<NodeCNF> arrayNodesCFN;
    private ArrayList<String> arrSentence;
    private ArrayList<ArrayList<CellInfo>> table;
    private static final int DELAY = 1000;

    public MainUI() {
        initComponents();

    }

    private void readFileCNF(String path) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        arrayNodesCFN = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String[] secondPart = line.split("->")[1].trim().split(" ");
            if (secondPart.length > 1) {
                arrayNodesCFN.add(new NodeCNF(line.split("->")[0].trim(), secondPart[0],
                         secondPart[1], false));
            } else {
                arrayNodesCFN.add(new NodeCNF(line.split("->")[0].trim(), secondPart[0],
                         "", true));
            }

        }
        reader.close();

    }

    private void parseSentence(String sentence) {
        //String sentence = edtSentence.getText();
        //INIT DATA FOR TABLE
        arrSentence = new ArrayList();
        arrSentence.addAll(Arrays.asList(sentence.toLowerCase().split(" ")));
        //set up table grid
        table = new ArrayList<>();
        for (int i = 0; i < arrSentence.size(); i++) {
            ArrayList<CellInfo> temp = new ArrayList<>();
            for (int j = 0; j < arrSentence.size(); j++) {
                temp.add(new CellInfo(".", ".", "", "."));
            }
            table.add(temp);
        }
        tableModel = new TableModel(arrSentence, table);
        tbCKYResult.setModel(tableModel);
        JTable rowTable = new RowNumberTable(tbCKYResult);
        jScrollPane1.setRowHeaderView(rowTable);
        jScrollPane1.setCorner(JScrollPane.UPPER_LEFT_CORNER,
                rowTable.getTableHeader());
        tbCKYResult.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int col = 0; col < tableModel.getColumnCount(); col++) {
            tbCKYResult.getColumnModel().getColumn(col).setCellRenderer(rightRenderer);
        }
        tableModel.fireTableDataChanged();
        //END INIT DATA
        

    }

    private void realTimeUpdate() throws IOException{
        boolean paused = true;
        for (int k = 1; k <= arrSentence.size(); k++) {
            for (NodeCNF node : arrayNodesCFN) {
                if (arrSentence.get(k - 1).equalsIgnoreCase(node.getFirstValue()) && node.isIsNonTerminal()) {
                    
                    long startTime = System.currentTimeMillis();
                    while (paused) {
                        if (System.currentTimeMillis() - startTime > DELAY) {
                            paused = false;
                            table.get(k - 1).set(k - 1, new CellInfo(node.getOwnerName(), ".", "", "."));
                            tableModel.fireTableDataChanged();
                        }
                        // An infinite loop that keeps on going until the pause flag is set to false
                    }
                    paused = true;
                    if (k == 1) {
                        continue;
                    }
                    
                    for (int i = k - 2; i >= 0; i--) {
                        for (int j = i + 1; j <= k - 1; j++) {
                            for (NodeCNF n : arrayNodesCFN) {
                                if(!table.get(i).get(k-1).getLabelCell().equalsIgnoreCase(".")){
                                    break;
                                }
                                if (table.get(i).get(j - 1).getLabelCell().equalsIgnoreCase(n.getFirstValue())
                                        && table.get(j).get(k - 1).getLabelCell().equalsIgnoreCase(n.getSecondValue())) {
                                    System.out.println(n.getOwnerName());
                                    
                                    startTime = System.currentTimeMillis();
                                    while (paused) {
                                        if(System.currentTimeMillis() - startTime > DELAY){
                                            paused = false;
                                            table.get(i).set(k - 1, new CellInfo(n.getOwnerName(), "(" + i + ", " + j + ")", "+",
                                            "(" + j + ", " + k + ")"));
                                            tableModel.fireTableDataChanged();
                                        }
                                    // An infinite loop that keeps on going until the pause flag is set to false
                                    }
                                    paused = true;
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
        tableModel.fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Completed CKY Algorithm");
        //tbCKYResult.setR
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        edtSentence = new javax.swing.JTextField();
        btnStart = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnImportCNF = new javax.swing.JButton();
        edtCNFPath = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCKYResult = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INPUT DATA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 14), new java.awt.Color(0, 0, 102))); // NOI18N

        edtSentence.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        edtSentence.setText("Sentence");
        edtSentence.setMargin(new java.awt.Insets(2, 10, 2, 10));

        btnStart.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnStart.setForeground(new java.awt.Color(0, 0, 153));
        btnStart.setText("START CKY");
        btnStart.setPreferredSize(new java.awt.Dimension(73, 25));
        btnStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onStartClick(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnReset.setForeground(new java.awt.Color(0, 0, 153));
        btnReset.setText("RESET");
        btnReset.setPreferredSize(new java.awt.Dimension(73, 25));
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onResetClick(evt);
            }
        });

        btnImportCNF.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnImportCNF.setForeground(new java.awt.Color(0, 0, 153));
        btnImportCNF.setText("IMPORT CNF RULES");
        btnImportCNF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onImportCNFClick(evt);
            }
        });

        edtCNFPath.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        edtCNFPath.setText("CNF Rules");
        edtCNFPath.setMargin(new java.awt.Insets(2, 10, 2, 10));
        edtCNFPath.setPreferredSize(new java.awt.Dimension(59, 23));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtCNFPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(edtSentence)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnImportCNF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(edtSentence, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtCNFPath, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImportCNF, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RESULT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 14), new java.awt.Color(0, 0, 102))); // NOI18N

        tbCKYResult.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbCKYResult.setCellSelectionEnabled(true);
        tbCKYResult.setRowHeight(60);
        tbCKYResult.setRowSorter(null);
        jScrollPane1.setViewportView(tbCKYResult);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onStartClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onStartClick
        // TODO add your handling code here:
        arrayNodesCFN = new ArrayList<>();
        if (edtCNFPath.getText().isEmpty() || edtSentence.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty CNF rules or sentence");
        } else {

            try {
                readFileCNF("./CNF.txt");
                parseSentence("nó vừa gặp mấy người bạn trường cũ");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean paused = true;
                        long startTime = System.currentTimeMillis();
                        while (paused) {
                            if (System.currentTimeMillis() - startTime > DELAY) {
                                paused = false;
                                try {
                                    realTimeUpdate();
                                } catch (IOException ex) {
                                    Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            // An infinite loop that keeps on going until the pause flag is set to false
                        }
                    }
                }).start();
                
                
            } catch (IOException ex) {
                Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_onStartClick

    private void onResetClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onResetClick
        
        
    }//GEN-LAST:event_onResetClick

    private void onImportCNFClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onImportCNFClick
        // TODO add your handling code here:
        final JFileChooser fc = new JFileChooser();

        //In response to a button click:
        int returnVal = fc.showOpenDialog(btnStart);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fileValues = fc.getSelectedFile();
            //This is where a real application would open the file.
            edtCNFPath.setText(fileValues.getAbsolutePath());

        }
    }//GEN-LAST:event_onImportCNFClick


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImportCNF;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnStart;
    private javax.swing.JTextField edtCNFPath;
    private javax.swing.JTextField edtSentence;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbCKYResult;
    // End of variables declaration//GEN-END:variables
}

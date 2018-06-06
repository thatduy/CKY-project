/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cky_project;

import Model.CellInfo;

import Model.MyEditor;
import Model.MyRenderer;
import Model.NodeCNF;
import Model.Result;
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
    private static final int DELAY = 10;

    public MainUI() {
        setTitle("CKY Algorithm");
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
                temp.add(new CellInfo("", "", "", -1));
            }
            table.add(temp);
        }
        tableModel = new TableModel(arrSentence, table);
        tbCKYResult.setModel(tableModel);
        tbCKYResult.setDefaultRenderer(String.class, new MyRenderer());
        tbCKYResult.setDefaultEditor(String.class, new MyEditor(tbCKYResult));

        JTable rowTable = new RowNumberTable(tbCKYResult);
        jScrollPane1.setRowHeaderView(rowTable);
        jScrollPane1.setCorner(JScrollPane.UPPER_LEFT_CORNER,
                rowTable.getTableHeader());
        tbCKYResult.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tableModel.fireTableDataChanged();
        //END INIT DATA

    }

    private void realTimeUpdate() throws IOException, InterruptedException {
        boolean paused = true;
        int size = arrSentence.size();
        for (int k = 1; k <= size; k++) {
            for (int h = 0; h <arrayNodesCFN.size(); h++) {
                if (arrSentence.get(k - 1).equalsIgnoreCase(arrayNodesCFN.get(h).getFirstValue()) && arrayNodesCFN.get(h).isIsNonTerminal() 
                        && !arrayNodesCFN.get(h).isIsUsed()
                        ) {
                    table.get(k - 1).get(k - 1).setState(0);
                    arrayNodesCFN.get(h).setIsUsed(true);
                    table.get(k - 1).get(k - 1).getArrayList().add(new Result(arrayNodesCFN.get(h).getOwnerName(), "", ""));
                    tableModel.fireTableDataChanged();
                    Thread.sleep(DELAY);
                    if (k == 1) {
                        break;
                    }

                    for (int i = k - 2; i >= 0; i--) {

                        for (int j = i + 1; j <= k - 1; j++) {
                            table.get(i).get(j - 1).setState(1); // i chay
                            table.get(i).get(k - 1).setState(0); // O dang xet
                            table.get(j).get(k - 1).setState(2); // j chay
                            tableModel.fireTableDataChanged();
                            Thread.sleep(DELAY);
                            paused = true;
                            for (NodeCNF n : arrayNodesCFN) {
                                for (Result re : table.get(i).get(j - 1).getArrayList()) {
                                    for (Result re2 : table.get(j).get(k - 1).getArrayList()) {
                                        if (re.getLabelCell().equalsIgnoreCase(n.getFirstValue())
                                                && re2.getLabelCell().equalsIgnoreCase(n.getSecondValue())) {
                                            System.out.println(n.getOwnerName());
                                            table.get(i).get(k - 1).getArrayList().add(new Result(n.getOwnerName(), "(" + i + ", " + j + ")",
                                                    "(" + j + ", " + k + ")"));

                                            tableModel.fireTableDataChanged();
                                            Thread.sleep(DELAY);

                                        }
                                    }
                                }
                            }
                            table.get(i).get(j - 1).setState(-1);
                            table.get(j).get(k - 1).setState(-1);
                            table.get(i).get(k - 1).setState(-1);
                            tableModel.fireTableDataChanged();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCKYResult = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INPUT DATA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 14), new java.awt.Color(0, 0, 102))); // NOI18N

        edtSentence.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
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
        edtCNFPath.setMargin(new java.awt.Insets(2, 10, 2, 10));
        edtCNFPath.setPreferredSize(new java.awt.Dimension(59, 23));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Sentence");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("CNF Rules");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnImportCNF, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStart, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtSentence)
                            .addComponent(edtCNFPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtSentence, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtCNFPath, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImportCNF, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RESULT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 14), new java.awt.Color(0, 0, 102))); // NOI18N

        tbCKYResult.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tbCKYResult.setCellSelectionEnabled(true);
        tbCKYResult.setRowHeight(60);
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
                readFileCNF(edtCNFPath.getText());
                parseSentence(edtSentence.getText());
                new Thread(() -> {
                    boolean paused = true;
                    long startTime = System.currentTimeMillis();
                    while (paused) {
                        if (System.currentTimeMillis() - startTime > DELAY) {
                            paused = false;
                            try {
                                realTimeUpdate();
                            } catch (IOException ex) {
                                Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        // An infinite loop that keeps on going until the pause flag is set to false
                    }
                }).start();

            } catch (IOException ex) {
                Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_onStartClick

    private void onResetClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onResetClick

        arrayNodesCFN = new ArrayList<>();
        arrSentence = new ArrayList<>();
        table = new ArrayList<>();
        tableModel = new TableModel();
        tableModel.setColunmName(arrSentence);
        tbCKYResult.setModel(tableModel);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbCKYResult;
    // End of variables declaration//GEN-END:variables
}

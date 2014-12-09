/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddos.GUI;

import ddos.Simulation;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juancho
 */
public class GUIPanel extends javax.swing.JFrame {

    int numNodes;
    int numClients;
    int cycles;
    int waves;

    //Minimum 4 alerted nodes
    int alarmThreshold;
    int mode;
    int maxConnections;
    double attackF;
    double attackers;
    boolean mutation = false;
    
    public GUIPanel() {
        super("DDoS Detector");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        startSim = new javax.swing.JButton();
        cleanSim = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        syncMode = new javax.swing.JRadioButton();
        asyncMode = new javax.swing.JRadioButton();
        mutateInput = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        attackThresholdInput = new javax.swing.JSpinner();
        numNodesInput = new javax.swing.JSpinner();
        cyclesInput = new javax.swing.JSpinner();
        numClientsInput = new javax.swing.JSpinner();
        wavesInput = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        attacksDropped = new javax.swing.JLabel();
        attacksReceived = new javax.swing.JLabel();
        suspectsReceived = new javax.swing.JLabel();
        attacksDetected = new javax.swing.JLabel();
        attacksSend = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        packetsSend = new javax.swing.JLabel();
        packetsReceived = new javax.swing.JLabel();
        packetsSendPer = new javax.swing.JLabel();
        packetsReceivedPer = new javax.swing.JLabel();
        attacksSendPer = new javax.swing.JLabel();
        attacksDetectedPer = new javax.swing.JLabel();
        suspectsReceivedPer = new javax.swing.JLabel();
        attacksReceivedPer = new javax.swing.JLabel();
        attacksDroppedPer = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        attackFInput = new javax.swing.JSpinner();
        attackersInput = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel17.setText("Attack %");

        jLabel21.setText("0%");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("DDoS Detector");

        startSim.setText("Start");
        startSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startSimActionPerformed(evt);
            }
        });

        cleanSim.setText("Clean");
        cleanSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanSimActionPerformed(evt);
            }
        });

        jLabel3.setText("# Nodes");

        jLabel4.setText("#Clients");

        jLabel5.setText("# Cycles");

        jLabel6.setText("Attack Threshold");

        syncMode.setText("Sync Mode");
        syncMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                syncModeActionPerformed(evt);
            }
        });

        asyncMode.setSelected(true);
        asyncMode.setText("Async");
        asyncMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asyncModeActionPerformed(evt);
            }
        });

        mutateInput.setText("Dynamic Attack");
        mutateInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mutateInputActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Attack Mode");

        attackThresholdInput.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(4), Integer.valueOf(4), null, Integer.valueOf(1)));

        numNodesInput.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(10), Integer.valueOf(4), null, Integer.valueOf(1)));

        cyclesInput.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(10), Integer.valueOf(1), null, Integer.valueOf(1)));

        numClientsInput.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(10), Integer.valueOf(4), null, Integer.valueOf(1)));

        wavesInput.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        wavesInput.setInputVerifier(numNodesInput.getInputVerifier());

        jLabel8.setText("# Waves");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Attacks Send");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Attacks Detected");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Suspect P. Received");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Attacks Received");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Attacks Dropped");

        attacksDropped.setText("0");

        attacksReceived.setText("0");

        suspectsReceived.setText("0");

        attacksDetected.setText("0");

        attacksSend.setText("0");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Packets Received");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Packets Send");

        packetsSend.setText("0");

        packetsReceived.setText("0");

        packetsSendPer.setText("0%");

        packetsReceivedPer.setText("0%");

        attacksSendPer.setText("0%");

        attacksDetectedPer.setText("0%");

        suspectsReceivedPer.setText("0%");

        attacksReceivedPer.setText("0%");

        attacksDroppedPer.setText("0%");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(packetsReceived, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                .addGap(24, 24, 24))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(packetsSendPer, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(packetsReceivedPer, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(suspectsReceived, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(attacksReceived, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(attacksDropped, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(76, 76, 76))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(129, 129, 129)
                                    .addComponent(jLabel12)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(packetsSend, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(162, 162, 162))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(attacksSendPer, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(attacksDetectedPer, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(25, 25, 25))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(attacksSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(27, 27, 27)
                                    .addComponent(attacksDetected, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(attacksReceivedPer, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(suspectsReceivedPer, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(attacksDroppedPer, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(packetsSend)
                    .addComponent(packetsReceived))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(packetsSendPer)
                    .addComponent(packetsReceivedPer))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(attacksSend)
                    .addComponent(attacksDetected))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(attacksSendPer)
                    .addComponent(attacksDetectedPer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(suspectsReceived)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(suspectsReceivedPer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(attacksReceived)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(attacksReceivedPer)
                .addGap(15, 15, 15)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(attacksDropped)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(attacksDroppedPer)
                .addContainerGap())
        );

        jLabel16.setText("Attack %");

        jLabel18.setText("Attacker %");

        attackFInput.setModel(new javax.swing.SpinnerNumberModel(0.1d, 0.0d, 1.0d, 0.05d));

        attackersInput.setModel(new javax.swing.SpinnerNumberModel(0.4d, 0.0d, 1.0d, 0.05d));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Statistics");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUIResources/network2.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(224, 224, 224))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(attackFInput, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(42, 42, 42)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(cyclesInput, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numNodesInput, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(numClientsInput, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(attackThresholdInput, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cleanSim)))
                            .addComponent(startSim))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(asyncMode)
                            .addComponent(syncMode)
                            .addComponent(mutateInput)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(wavesInput, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(attackersInput, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(136, 136, 136))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(numClientsInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numNodesInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(syncMode))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(attackThresholdInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cyclesInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addGap(11, 11, 11)
                                .addComponent(attackFInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(wavesInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(attackersInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(asyncMode)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(mutateInput)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(startSim)
                                    .addComponent(cleanSim))))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startSimActionPerformed
        
        Simulation simulation = new Simulation();
        //Clear data
        packetsSend.setText("");
        packetsReceived.setText("");
        attacksSend.setText("");
        attacksDetected.setText("");
        attacksReceived.setText("");
        attacksDropped.setText("");
        suspectsReceived.setText("");
        
        simulation.setAlarmThreshold((int)attackThresholdInput.getValue());
        simulation.setAttackF((double) attackFInput.getValue());
        simulation.setCycles((int) cyclesInput.getValue());
        simulation.setMutation(mutation);
        simulation.setNumClients((int) numClientsInput.getValue());
        simulation.setNumNodes((int) numNodesInput.getValue());
        
        //Asynchronous Mode
        if(mode == 0){
            simulation.setMode(0);
        }
        //Synchronous Mode
        if(mode == 1){
            simulation.setMode(0);
        }
        try {
            simulation.initalize();
        } catch (Exception ex) {
            Logger.getLogger(GUIPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            simulation.simulate();
        } catch (Exception ex) {
            Logger.getLogger(GUIPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        packetsSend.setText(Integer.toString(simulation.getNumPackets()));
        packetsSendPer.setText("100%");
        packetsReceived.setText(Integer.toString(simulation.getNumPacketsReceived()));
        packetsReceivedPer.setText(Integer.toString((simulation.getNumPacketsReceived()*100/simulation.getNumPackets()))+ "%");
        attacksSend.setText(Integer.toString(simulation.getNumAttacks()));
        attacksSendPer.setText("100%");
        attacksDetected.setText(Integer.toString(simulation.getNumAttacksDetected()));
        attacksDetectedPer.setText(Integer.toString((simulation.getNumAttacksDetected()*100/simulation.getNumAttacks()))+ "%");
        attacksReceived.setText(Integer.toString(simulation.getNumAttacks()- simulation.getNumPacketsDropped()));
        attacksReceivedPer.setText(Integer.toString((simulation.getNumAttacks()- simulation.getNumPacketsDropped())*100/simulation.getNumAttacks())+ "%");
        attacksDropped.setText(Integer.toString(simulation.getNumPacketsDropped()));
        attacksDroppedPer.setText(Integer.toString(simulation.getNumPacketsDropped()*100/simulation.getNumAttacks())+ "%");
        suspectsReceived.setText(Integer.toString(simulation.getNumSuspectsReceived()));
        suspectsReceivedPer.setText(Integer.toString(simulation.getNumSuspectsReceived()*100/simulation.getNumPackets())+ "%");
        
    }//GEN-LAST:event_startSimActionPerformed

    private void asyncModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asyncModeActionPerformed
        // TODO add your handling code here:
        mode = 0;
        syncMode.setSelected(false);
        wavesInput.setEnabled(false);
    }//GEN-LAST:event_asyncModeActionPerformed

    private void syncModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_syncModeActionPerformed
        // TODO add your handling code here:
        mode = 1;
        asyncMode.setSelected(false);
    }//GEN-LAST:event_syncModeActionPerformed

    private void mutateInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mutateInputActionPerformed
        // TODO add your handling code here:
        mutation = true;
    }//GEN-LAST:event_mutateInputActionPerformed

    private void cleanSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanSimActionPerformed
        // TODO add your handling code here:
        //Reset control
        numNodesInput.setValue(10);
        numClientsInput.setValue(10);
        cyclesInput.setValue(10);
        attackThresholdInput.setValue(4);
        attackFInput.setValue(0.1);
        attackersInput.setValue(0.4);
        wavesInput.setValue(1);
        asyncMode.setSelected(true);
        mutateInput.setSelected(false);
        
        //Reset data
        packetsSend.setText("");
        packetsReceived.setText("");
        attacksSend.setText("");
        attacksDetected.setText("");
        attacksReceived.setText("");
        attacksDropped.setText("");
        suspectsReceived.setText("");
    }//GEN-LAST:event_cleanSimActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton asyncMode;
    private javax.swing.JSpinner attackFInput;
    private javax.swing.JSpinner attackThresholdInput;
    private javax.swing.JSpinner attackersInput;
    private javax.swing.JLabel attacksDetected;
    private javax.swing.JLabel attacksDetectedPer;
    private javax.swing.JLabel attacksDropped;
    private javax.swing.JLabel attacksDroppedPer;
    private javax.swing.JLabel attacksReceived;
    private javax.swing.JLabel attacksReceivedPer;
    private javax.swing.JLabel attacksSend;
    private javax.swing.JLabel attacksSendPer;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JButton cleanSim;
    private javax.swing.JSpinner cyclesInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JCheckBox mutateInput;
    private javax.swing.JSpinner numClientsInput;
    private javax.swing.JSpinner numNodesInput;
    private javax.swing.JLabel packetsReceived;
    private javax.swing.JLabel packetsReceivedPer;
    private javax.swing.JLabel packetsSend;
    private javax.swing.JLabel packetsSendPer;
    private javax.swing.JButton startSim;
    private javax.swing.JLabel suspectsReceived;
    private javax.swing.JLabel suspectsReceivedPer;
    private javax.swing.JRadioButton syncMode;
    private javax.swing.JSpinner wavesInput;
    // End of variables declaration//GEN-END:variables
}

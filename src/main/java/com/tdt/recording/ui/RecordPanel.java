/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tdt.recording.ui;

import music.AudioComponents;

import javax.swing.*;


/**
 * @author Khoi Le
 */
public class RecordPanel extends javax.swing.JPanel {

    AudioComponents components;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JButton pauseButton;
    private javax.swing.JComboBox visualizationBox;

    /**
     * Creates new form RecordPanel
     */
    public RecordPanel() {
        initComponents();
    }

    public RecordPanel(AudioComponents audioComponents) {

        this.components = audioComponents;
        initComponents();
    }

    public JComboBox getVisualizationBox() {
        return visualizationBox;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        exitButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        visualizationBox = new javax.swing.JComboBox();

        setLayout(new java.awt.GridBagLayout());

        exitButton.setText("Exit");
        exitButton.setName("exitButton"); // NOI18N
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 10);
        add(exitButton, gridBagConstraints);

        pauseButton.setText("|| / >");
        pauseButton.setName("pauseButton"); // NOI18N
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 18, 11, 0);
        add(pauseButton, gridBagConstraints);

        visualizationBox.setModel(new SetComboBoxModel());
        visualizationBox.setName("visualizationBox"); // NOI18N
        visualizationBox.setPreferredSize(new java.awt.Dimension(250, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 551;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 0);
        add(visualizationBox, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        components.stop();
        components.destroy();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        if (components.getGUIManager().paused())
            components.getGUIManager().unpause();
        else {
            components.getGUIManager().pause();
        }
    }//GEN-LAST:event_pauseButtonActionPerformed
    // End of variables declaration//GEN-END:variables

    class SetComboBoxModel extends DefaultComboBoxModel {

        @Override
        public void addElement(Object anObject) {
            if (this.getIndexOf(anObject) == -1) {
                super.addElement(anObject);
            }
        }

        @Override
        public void insertElementAt(Object anObject, int index) {
            if (this.getIndexOf(anObject) == -1) {
                super.insertElementAt(anObject, index);
            }
        }

    }
}

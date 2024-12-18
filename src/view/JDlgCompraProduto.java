/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import bean.EbsCompra;
import bean.EbsProduto;
import dao.DAOgeneric;
import java.util.List;

/**
 *
 * @author bispo
 */
public class JDlgCompraProduto extends javax.swing.JDialog {

    /**
     * Creates new form JDlgPedidosProdutos
     */
    public JDlgCompraProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Compra Produto");
        setLocationRelativeTo(null);
        
        dao.DAOgeneric dao = new DAOgeneric();

        List listProdutos = dao.listAll(EbsProduto.class);
        for (Object p : listProdutos) {
            ebs_jCboProduto.addItem((EbsProduto) p);
        }
        List listCompras = dao.listAll(EbsCompra.class);
        for (Object c : listCompras) {
            ebs_jCboCompra.addItem((EbsCompra) c);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLblProduto = new javax.swing.JLabel();
        ebs_jCboProduto = new javax.swing.JComboBox<EbsProduto>();
        jLblQuantidade = new javax.swing.JLabel();
        ebs_jTxtQuantidade = new javax.swing.JTextField();
        jLblValorUnitario = new javax.swing.JLabel();
        ebs_jTxtValorUnitario = new javax.swing.JTextField();
        jLblTotal = new javax.swing.JLabel();
        ebs_jTxtTotal = new javax.swing.JTextField();
        ebs_jBtnCancelar = new javax.swing.JButton();
        ebs_jBtnOk = new javax.swing.JButton();
        jLblCompra = new javax.swing.JLabel();
        ebs_jCboCompra = new javax.swing.JComboBox<EbsCompra>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLblProduto.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLblProduto.setText("Produto");

        ebs_jCboProduto.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        jLblQuantidade.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLblQuantidade.setText("Quantidade");

        ebs_jTxtQuantidade.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        jLblValorUnitario.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLblValorUnitario.setText("Valor Unitário");

        ebs_jTxtValorUnitario.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        jLblTotal.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLblTotal.setText("Total");

        ebs_jTxtTotal.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        ebs_jBtnCancelar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ebs_jBtnCancelar.setText("Cancelar");
        ebs_jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnCancelarActionPerformed(evt);
            }
        });

        ebs_jBtnOk.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ebs_jBtnOk.setText("OK");
        ebs_jBtnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnOkActionPerformed(evt);
            }
        });

        jLblCompra.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLblCompra.setText("Compra");

        ebs_jCboCompra.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblCompra)
                            .addComponent(ebs_jCboCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblProduto)
                            .addComponent(ebs_jCboProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(ebs_jBtnOk)
                            .addGap(18, 18, 18)
                            .addComponent(ebs_jBtnCancelar))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLblQuantidade)
                                .addComponent(ebs_jTxtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLblValorUnitario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ebs_jTxtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLblTotal)
                                .addComponent(ebs_jTxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblCompra)
                    .addComponent(jLblProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ebs_jCboCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ebs_jCboProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblQuantidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ebs_jTxtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblValorUnitario)
                            .addComponent(jLblTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ebs_jTxtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ebs_jTxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ebs_jBtnCancelar)
                    .addComponent(ebs_jBtnOk))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ebs_jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_ebs_jBtnCancelarActionPerformed

    private void ebs_jBtnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnOkActionPerformed
        this.dispose();
    }//GEN-LAST:event_ebs_jBtnOkActionPerformed

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
            java.util.logging.Logger.getLogger(JDlgCompraProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgCompraProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgCompraProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgCompraProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            JDlgCompraProduto dialog = new JDlgCompraProduto(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ebs_jBtnCancelar;
    private javax.swing.JButton ebs_jBtnOk;
    private javax.swing.JComboBox<EbsCompra> ebs_jCboCompra;
    private javax.swing.JComboBox<EbsProduto> ebs_jCboProduto;
    private javax.swing.JTextField ebs_jTxtQuantidade;
    private javax.swing.JTextField ebs_jTxtTotal;
    private javax.swing.JTextField ebs_jTxtValorUnitario;
    private javax.swing.JLabel jLblCompra;
    private javax.swing.JLabel jLblProduto;
    private javax.swing.JLabel jLblQuantidade;
    private javax.swing.JLabel jLblTotal;
    private javax.swing.JLabel jLblValorUnitario;
    // End of variables declaration//GEN-END:variables
}

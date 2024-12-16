/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import bean.EbsCategoria;
import bean.EbsProduto;
import dao.DAOgeneric;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tools.Util;

/**
 *
 * @author bispo
 */
public class JDlgProduto extends javax.swing.JDialog {

    /**
     * Creates new form jDlgUsuario
     */
    boolean incluir;
    boolean pesquisar;

    public JDlgProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Produto");
        setLocationRelativeTo(null);
        habilitar(false);

        Util.maskData(ebs_jFmtdata_chegada);
        Util.maskData(ebs_jFmtdata_validade);

        DAOgeneric dao = new DAOgeneric();
        List lstCategoria = dao.listAll(EbsCategoria.class);
        for (Object c : lstCategoria) {
            ebs_jCbofk_categoria.addItem((EbsCategoria) c);
        }

    }

    private void habilitar(boolean status) {
        if (status) {
            Util.habilitar(true, ebs_jTxtid_produto, ebs_jCbofk_categoria, ebs_jTxtnome, ebs_jFmtdata_chegada, ebs_jFmtdata_validade, ebs_jTxtvalor, ebs_jTxtestoque, ebs_jBtnCancelar, ebs_jBtnConfirmar);
            Util.habilitar(false, ebs_jBtnIncluir, ebs_jBtnAlterar, ebs_jBtnPesquisar, ebs_jBtnExcluir);
        } else {
            Util.habilitar(false, ebs_jTxtid_produto, ebs_jCbofk_categoria, ebs_jTxtnome, ebs_jFmtdata_chegada, ebs_jFmtdata_validade, ebs_jTxtvalor, ebs_jTxtestoque, ebs_jBtnCancelar, ebs_jBtnConfirmar);
            Util.habilitar(true, ebs_jBtnIncluir, ebs_jBtnAlterar, ebs_jBtnPesquisar, ebs_jBtnExcluir);
        }
    }

    private void limparCampos() {
        Util.limpar(ebs_jTxtid_produto, ebs_jCbofk_categoria, ebs_jTxtnome, ebs_jFmtdata_chegada, ebs_jFmtdata_validade, ebs_jTxtvalor, ebs_jTxtestoque);
    }

    public EbsProduto viewbean() {
        EbsProduto p = new EbsProduto();

        try {
            // Verificar se o campo ID do produto está vazio
            String idText = ebs_jTxtid_produto.getText();
            if (idText.isEmpty()) {
                Util.mostrar("O ID do produto não pode estar vazio", "ERRO");
                return null;
            }
            // Adiciona o id do produto na Classe
            p.setEbsIdProduto(Util.strToInt(idText));

            // Verificar se o nome do produto está vazio
            String nome = ebs_jTxtnome.getText();
            if (nome.isEmpty()) {
                Util.mostrar("O nome do produto não pode estar vazio", "ERRO");
                return null;
            }
            // Adiciona o nome do produto na Classe
            p.setEbsNome(nome);

            // Verificar se a categoria foi selecionada
            EbsCategoria c = (EbsCategoria) ebs_jCbofk_categoria.getSelectedItem();
            if (c == null) {
                Util.mostrar("Nenhuma categoria selecionada", "ERRO");
                return null;
            }// Adiciona a categoria do  produto na Classe
            p.setEbsCategoria(c);

            // Verificar se a data de chegada está vazia
            String dataChegadaText = ebs_jFmtdata_chegada.getText();
            if (dataChegadaText.isEmpty()) {
                Util.mostrar("A data de chegada não pode estar vazia", "ERRO");
                return null;
            }
            // Cria o formato de data e converte a data de chegada para a classe
            p.setEbsDataChegada(Util.strToDate(dataChegadaText));

            // Verificar se a data de validade está vazia
            String dataValidadeText = ebs_jFmtdata_validade.getText();
            if (dataValidadeText.isEmpty()) {
                Util.mostrar("A data de validade não pode estar vazia", "ERRO");
                return null;
            }
            // Converte a data de validade para a classe
            p.setEbsDataValidade(Util.strToDate(dataValidadeText));

            // Verificar se o valor está vazio
            String valorText = ebs_jTxtvalor.getText();
            if (valorText.isEmpty()) {
                Util.mostrar("O valor do produto não pode estar vazio", "ERRO");
                return null;
            }
            // Adiciona o valor do produto na Classe
            p.setEbsValor(Double.parseDouble(valorText));

            // Verificar se o estoque está vazio
            String estoqueText = ebs_jTxtestoque.getText();
            if (estoqueText.isEmpty()) {
                Util.mostrar("O estoque do produto não pode estar vazio", "ERRO");
                return null;
            }
            // Adiciona o estoque do produto na Classe
            p.setEbsEstoque(Integer.parseInt(estoqueText));

        } catch (NumberFormatException | ParseException ex) {
            Util.mostrar("Erro ao converter valores", "ERRO");
            Logger.getLogger(JDlgUsuario.class.getName()).log(Level.SEVERE, "Erro ao preencher usuário", ex);
            return null;
        }
        return p;
    }

    // Método para preencher a interface com os valores do Bean Ebs_Produto
    public void beanview(EbsProduto p) {
        ebs_jTxtid_produto.setText(String.valueOf(p.getEbsIdProduto()));
        ebs_jTxtnome.setText(p.getEbsNome());
        ebs_jCbofk_categoria.setSelectedItem(p.getEbsCategoria());
        ebs_jFmtdata_chegada.setText(Util.dateToStr((Date) p.getEbsDataChegada()));
        ebs_jFmtdata_validade.setText(Util.dateToStr((Date) p.getEbsDataValidade()));
        ebs_jTxtvalor.setText(Util.doubleToStr(p.getEbsValor()));
        ebs_jTxtestoque.setText(Util.intToStr(p.getEbsEstoque()));
    }

    private void telaPesquisar() {
        Class c = EbsProduto.class;
        EbsProduto o = new EbsProduto();
        Class ct = JDlgProduto.class;
        JDlgPesquisar jDlgP = new JDlgPesquisar(null, true, c, o, ct, this, "Produto");
        jDlgP.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ebs_jLblCodigo = new javax.swing.JLabel();
        ebs_JLblCategoria = new javax.swing.JLabel();
        ebs_jLblNome = new javax.swing.JLabel();
        ebs_JLblDataChegada = new javax.swing.JLabel();
        ebs_JLblDataValidade = new javax.swing.JLabel();
        ebs_jLblValor = new javax.swing.JLabel();
        ebs_jLblEstoque = new javax.swing.JLabel();
        ebs_jTxtid_produto = new javax.swing.JTextField();
        ebs_jTxtnome = new javax.swing.JTextField();
        ebs_jFmtdata_chegada = new javax.swing.JFormattedTextField();
        ebs_jFmtdata_validade = new javax.swing.JFormattedTextField();
        ebs_jTxtvalor = new javax.swing.JTextField();
        ebs_jTxtestoque = new javax.swing.JTextField();
        ebs_jCbofk_categoria = new javax.swing.JComboBox<EbsCategoria>();
        ebs_jBtnIncluir = new javax.swing.JButton();
        ebs_jBtnAlterar = new javax.swing.JButton();
        ebs_jBtnExcluir = new javax.swing.JButton();
        ebs_jBtnConfirmar = new javax.swing.JButton();
        ebs_jBtnCancelar = new javax.swing.JButton();
        ebs_jBtnPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(new java.awt.Font("Dubai", 0, 12)); // NOI18N

        ebs_jLblCodigo.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ebs_jLblCodigo.setText("ID");

        ebs_JLblCategoria.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ebs_JLblCategoria.setText("Categoria");

        ebs_jLblNome.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ebs_jLblNome.setText("Nome");

        ebs_JLblDataChegada.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ebs_JLblDataChegada.setText("Data de Chegada");

        ebs_JLblDataValidade.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ebs_JLblDataValidade.setText("Data de Validade");

        ebs_jLblValor.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ebs_jLblValor.setText("Valor");

        ebs_jLblEstoque.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ebs_jLblEstoque.setText("Estoque");

        ebs_jTxtid_produto.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        ebs_jTxtnome.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        ebs_jFmtdata_chegada.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        ebs_jFmtdata_validade.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        ebs_jTxtvalor.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        ebs_jTxtestoque.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        ebs_jCbofk_categoria.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        ebs_jBtnIncluir.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ebs_jBtnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add1.png"))); // NOI18N
        ebs_jBtnIncluir.setText("Incluir");
        ebs_jBtnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnIncluirActionPerformed(evt);
            }
        });

        ebs_jBtnAlterar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ebs_jBtnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        ebs_jBtnAlterar.setText("Alterar");
        ebs_jBtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnAlterarActionPerformed(evt);
            }
        });

        ebs_jBtnExcluir.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ebs_jBtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/deletar.png"))); // NOI18N
        ebs_jBtnExcluir.setText("Excluir");
        ebs_jBtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnExcluirActionPerformed(evt);
            }
        });

        ebs_jBtnConfirmar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ebs_jBtnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/confirmar1.png"))); // NOI18N
        ebs_jBtnConfirmar.setText("Confirmar");
        ebs_jBtnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnConfirmarActionPerformed(evt);
            }
        });

        ebs_jBtnCancelar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ebs_jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar2.png"))); // NOI18N
        ebs_jBtnCancelar.setText("Cancelar");
        ebs_jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnCancelarActionPerformed(evt);
            }
        });

        ebs_jBtnPesquisar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ebs_jBtnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pesquisa1.png"))); // NOI18N
        ebs_jBtnPesquisar.setText("Pesquisar");
        ebs_jBtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_JLblDataChegada)
                                    .addComponent(ebs_jFmtdata_chegada, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_JLblDataValidade)
                                    .addComponent(ebs_jFmtdata_validade, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(66, 66, 66))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_jLblNome)
                                    .addComponent(ebs_jTxtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_jTxtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ebs_jLblValor))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_jTxtestoque, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ebs_jLblEstoque)))
                            .addComponent(ebs_jCbofk_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ebs_JLblCategoria)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ebs_jLblCodigo)
                            .addComponent(ebs_jTxtid_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ebs_jBtnIncluir)
                .addGap(18, 18, 18)
                .addComponent(ebs_jBtnAlterar)
                .addGap(18, 18, 18)
                .addComponent(ebs_jBtnExcluir)
                .addGap(18, 18, 18)
                .addComponent(ebs_jBtnConfirmar)
                .addGap(18, 18, 18)
                .addComponent(ebs_jBtnCancelar)
                .addGap(18, 18, 18)
                .addComponent(ebs_jBtnPesquisar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(ebs_jLblCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ebs_jTxtid_produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ebs_jLblNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ebs_jTxtnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ebs_JLblCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ebs_jCbofk_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ebs_JLblDataChegada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ebs_jFmtdata_chegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ebs_JLblDataValidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ebs_jFmtdata_validade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ebs_jLblValor)
                            .addComponent(ebs_jLblEstoque))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ebs_jTxtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ebs_jTxtestoque, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ebs_jBtnIncluir)
                    .addComponent(ebs_jBtnConfirmar)
                    .addComponent(ebs_jBtnAlterar)
                    .addComponent(ebs_jBtnExcluir)
                    .addComponent(ebs_jBtnCancelar)
                    .addComponent(ebs_jBtnPesquisar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ebs_jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnIncluirActionPerformed
        habilitar(true);
        limparCampos();
        incluir = true;
    }//GEN-LAST:event_ebs_jBtnIncluirActionPerformed

    private void ebs_jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnAlterarActionPerformed
        if (pesquisar == false) {
            //INSTACIAR TELA
            telaPesquisar();
        }
        habilitar(true);
        Util.habilitar(false, ebs_jTxtid_produto);
        incluir = false;
        pesquisar = false;
    }//GEN-LAST:event_ebs_jBtnAlterarActionPerformed

    private void ebs_jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnExcluirActionPerformed
        if (pesquisar == false) {
            telaPesquisar();
        }
        if (Util.perguntar("Confirme exclusão!", "Deletar registro")) {
            DAOgeneric dao = new DAOgeneric();
            dao.delete(viewbean());
            Util.mostrar("Exclusão realizada", "Aviso");
            limparCampos();
        } else {
            Util.mostrar("Exclusão cancelada", "Aviso");
            habilitar(false);
        }
        pesquisar = false;
    }//GEN-LAST:event_ebs_jBtnExcluirActionPerformed

    private void ebs_jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnConfirmarActionPerformed
        DAOgeneric dao = new DAOgeneric();
        if (incluir == true) {
            dao.insert(viewbean());
        } else {
            dao.update(viewbean());
        }
        limparCampos();
        habilitar(false);
    }//GEN-LAST:event_ebs_jBtnConfirmarActionPerformed

    private void ebs_jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnCancelarActionPerformed
        incluir = false;
        habilitar(false);
        limparCampos();
    }//GEN-LAST:event_ebs_jBtnCancelarActionPerformed

    private void ebs_jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnPesquisarActionPerformed
        pesquisar = true;
        telaPesquisar();
    }//GEN-LAST:event_ebs_jBtnPesquisarActionPerformed

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
            java.util.logging.Logger.getLogger(JDlgProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgProduto dialog = new JDlgProduto(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ebs_JLblCategoria;
    private javax.swing.JLabel ebs_JLblDataChegada;
    private javax.swing.JLabel ebs_JLblDataValidade;
    private javax.swing.JButton ebs_jBtnAlterar;
    private javax.swing.JButton ebs_jBtnCancelar;
    private javax.swing.JButton ebs_jBtnConfirmar;
    private javax.swing.JButton ebs_jBtnExcluir;
    private javax.swing.JButton ebs_jBtnIncluir;
    private javax.swing.JButton ebs_jBtnPesquisar;
    private javax.swing.JComboBox<EbsCategoria> ebs_jCbofk_categoria;
    private javax.swing.JFormattedTextField ebs_jFmtdata_chegada;
    private javax.swing.JFormattedTextField ebs_jFmtdata_validade;
    private javax.swing.JLabel ebs_jLblCodigo;
    private javax.swing.JLabel ebs_jLblEstoque;
    private javax.swing.JLabel ebs_jLblNome;
    private javax.swing.JLabel ebs_jLblValor;
    private javax.swing.JTextField ebs_jTxtestoque;
    private javax.swing.JTextField ebs_jTxtid_produto;
    private javax.swing.JTextField ebs_jTxtnome;
    private javax.swing.JTextField ebs_jTxtvalor;
    // End of variables declaration//GEN-END:variables
}

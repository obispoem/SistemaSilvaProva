/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import bean.EbsUsuario;
import dao.DAOgeneric;
import java.sql.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tools.Util;

/**
 *
 * @author bispo
 */
public class JDlgUsuario extends javax.swing.JDialog {

    /**
     * Creates new form jDlgUsuario
     */
    private static final int NIVEL_NAO_DEFINIDO = 0;
    private static final int NIVEL_BAIXO = 1;
    private static final int NIVEL_MEDIO = 2;
    private static final int NIVEL_ALTO = 3;

    boolean incluir;
    boolean pesquisar;

    public JDlgUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Usuario");
        setLocationRelativeTo(null);
        habilitar(false);
        
        Util.maskCPF(ebs_jFmtcpf);
        Util.maskData(ebs_jFmtdata_nasc);
    }

    private void habilitar(boolean status) {
        if (status) {
            Util.habilitar(true, ebs_jTxtid_usuario, ebs_jTxtnome, ebs_jTxtapelido, ebs_jFmtcpf, ebs_jFmtdata_nasc, ebs_jPwfsenha, ebs_jCbonivel, ebs_jChbativo, ebs_jBtnCancelar, ebs_jBtnConfirmar);
            Util.habilitar(false, ebs_jBtnIncluir, ebs_jBtnAlterar, ebs_jBtnPesquisar, ebs_jBtnExcluir);
        } else {
            Util.habilitar(false, ebs_jTxtid_usuario, ebs_jTxtnome, ebs_jTxtapelido, ebs_jFmtcpf, ebs_jFmtdata_nasc, ebs_jPwfsenha, ebs_jCbonivel, ebs_jChbativo, ebs_jBtnCancelar, ebs_jBtnConfirmar);
            Util.habilitar(true, ebs_jBtnIncluir, ebs_jBtnAlterar, ebs_jBtnPesquisar, ebs_jBtnExcluir);
        }
    }

    private void limparCampos() {
        Util.limpar(ebs_jTxtid_usuario, ebs_jTxtnome, ebs_jTxtapelido, ebs_jFmtcpf, ebs_jFmtdata_nasc, ebs_jPwfsenha, ebs_jCbonivel, ebs_jChbativo);
    }

    private EbsUsuario viewbean() {
        // instaciando a classe
        EbsUsuario u = new EbsUsuario();

        try {
            // Verificar se o campo ID do usuário está vazio
            String idText = ebs_jTxtid_usuario.getText();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O ID do usuário não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o id do usuario na Classe
            u.setEbsIdUsuario(Util.strToInt(idText));

            // Verificar se o nome do usuário está vazio
            String nome = ebs_jTxtnome.getText();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome do usuário não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o nome do usuario na Classe
            u.setEbsNome(nome);

            // Verificar se o apelido do usuário está vazio
            String apelido = ebs_jTxtapelido.getText();
            if (apelido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O apelido do usuário não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o apelido do usuario na Classe
            u.setEbsApelido(apelido);

            // Verificar se o CPF está vazio
            String cpf = ebs_jFmtcpf.getText();
            if (cpf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O CPF não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o cpf do usuario na Classe
            u.setEbsCpf(cpf);

            // Verificar se a data de nascimento está vazia
            String dataNascText = ebs_jFmtdata_nasc.getText();
            if (dataNascText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "A data de nascimento não pode estar vazia", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona a data de nascimento do usuario na Classe
            u.setEbsDataNasc(Util.strToDate(dataNascText));

            // Verificar se a senha está vazia
            String senha = new String(ebs_jPwfsenha.getPassword());
            if (senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "A senha não pode estar vazia", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona a senha do usuario na Classe
            u.setEbsSenha(senha);

            // Verificar se o nível foi selecionado
            int nivelSelecionado = ebs_jCbonivel.getSelectedIndex();
            if (nivelSelecionado == NIVEL_NAO_DEFINIDO) {
                JOptionPane.showMessageDialog(this, "Não selecionado nível", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o nivel do usuario na Classe
            u.setEbsNivel(nivelSelecionado);

            // Definir se o usuário está ativo e adiciona na classe
            u.setEbsAtivo(ebs_jChbativo.isSelected() ? "s" : "n");

        } catch (NumberFormatException | ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao converter valores", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDlgUsuario.class.getName()).log(Level.SEVERE, "Erro ao preencher usuário", ex);
            return null;
        }
        return u;
    }

    private void beanview(EbsUsuario u) {
        ebs_jTxtid_usuario.setText(Util.intToStr(u.getEbsIdUsuario()));
        ebs_jTxtnome.setText(u.getEbsNome());
        ebs_jTxtapelido.setText(u.getEbsApelido());
        ebs_jFmtcpf.setText(u.getEbsCpf());
        ebs_jFmtdata_nasc.setText(Util.dateToStr((Date) u.getEbsDataNasc()));
        ebs_jPwfsenha.setText(u.getEbsSenha());
        ebs_jCbonivel.setSelectedIndex(u.getEbsNivel());
        ebs_jChbativo.setSelected("s".equals(u.getEbsAtivo()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ebs_jLblid_usuario = new javax.swing.JLabel();
        ebs_jLblnome = new javax.swing.JLabel();
        ebs_jLblapelido = new javax.swing.JLabel();
        ebs_JLblcpf = new javax.swing.JLabel();
        ebs_JLbldata_nasc = new javax.swing.JLabel();
        ebs_JLblsenha = new javax.swing.JLabel();
        ebs_JLblnivel = new javax.swing.JLabel();
        ebs_jTxtid_usuario = new javax.swing.JTextField();
        ebs_jTxtnome = new javax.swing.JTextField();
        ebs_jTxtapelido = new javax.swing.JTextField();
        ebs_jFmtcpf = new javax.swing.JFormattedTextField();
        ebs_jFmtdata_nasc = new javax.swing.JFormattedTextField();
        ebs_jPwfsenha = new javax.swing.JPasswordField();
        ebs_jCbonivel = new javax.swing.JComboBox<>();
        ebs_jChbativo = new javax.swing.JCheckBox();
        ebs_jBtnCancelar = new javax.swing.JButton();
        ebs_jBtnPesquisar = new javax.swing.JButton();
        ebs_jBtnIncluir = new javax.swing.JButton();
        ebs_jBtnAlterar = new javax.swing.JButton();
        ebs_jBtnExcluir = new javax.swing.JButton();
        ebs_jBtnConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        ebs_jLblid_usuario.setText("ID");

        ebs_jLblnome.setText("Nome");

        ebs_jLblapelido.setText("Apelido");

        ebs_JLblcpf.setText("CPF");

        ebs_JLbldata_nasc.setText("Data de Nascimento");

        ebs_JLblsenha.setText("Senha");

        ebs_JLblnivel.setText("Nível");

        ebs_jTxtid_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jTxtid_usuarioActionPerformed(evt);
            }
        });

        ebs_jFmtdata_nasc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jFmtdata_nascActionPerformed(evt);
            }
        });

        ebs_jCbonivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "não definido", "Administrador", "Funcionario", "Usuario" }));

        ebs_jChbativo.setText("Ativo");
        ebs_jChbativo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ebs_jChbativo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        ebs_jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar2.png"))); // NOI18N
        ebs_jBtnCancelar.setText("Cancelar");
        ebs_jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnCancelarActionPerformed(evt);
            }
        });

        ebs_jBtnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pesquisa1.png"))); // NOI18N
        ebs_jBtnPesquisar.setText("Pesquisar");
        ebs_jBtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnPesquisarActionPerformed(evt);
            }
        });

        ebs_jBtnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add1.png"))); // NOI18N
        ebs_jBtnIncluir.setText("Incluir");
        ebs_jBtnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnIncluirActionPerformed(evt);
            }
        });

        ebs_jBtnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        ebs_jBtnAlterar.setText("Alterar");
        ebs_jBtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnAlterarActionPerformed(evt);
            }
        });

        ebs_jBtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/deletar.png"))); // NOI18N
        ebs_jBtnExcluir.setText("Excluir");
        ebs_jBtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnExcluirActionPerformed(evt);
            }
        });

        ebs_jBtnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/confirmar1.png"))); // NOI18N
        ebs_jBtnConfirmar.setText("Confirmar");
        ebs_jBtnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ebs_jPwfsenha)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ebs_JLblsenha)
                                .addGap(246, 246, 246)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ebs_JLblnivel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ebs_jCbonivel, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ebs_jChbativo))))
                    .addComponent(ebs_jTxtnome)
                    .addComponent(ebs_jTxtapelido)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ebs_jTxtid_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ebs_jLblapelido)
                            .addComponent(ebs_jLblnome)
                            .addComponent(ebs_jLblid_usuario)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(ebs_jFmtcpf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(206, 206, 206))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ebs_JLblcpf)
                                        .addGap(335, 335, 335)))
                                .addGap(135, 135, 135)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_JLbldata_nasc)
                                    .addComponent(ebs_jFmtdata_nasc, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addGap(19, 19, 19)
                .addComponent(ebs_jLblid_usuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ebs_jTxtid_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ebs_jLblnome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ebs_jTxtnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ebs_jLblapelido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ebs_jTxtapelido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ebs_JLblcpf)
                    .addComponent(ebs_JLbldata_nasc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ebs_jFmtcpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ebs_jFmtdata_nasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ebs_JLblsenha)
                    .addComponent(ebs_JLblnivel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ebs_jPwfsenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ebs_jCbonivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ebs_jChbativo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void ebs_jTxtid_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jTxtid_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ebs_jTxtid_usuarioActionPerformed
    private void ebs_jFmtdata_nascActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jFmtdata_nascActionPerformed

    }//GEN-LAST:event_ebs_jFmtdata_nascActionPerformed

    private void ebs_jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnCancelarActionPerformed
        incluir = false;
        habilitar(false);
        limparCampos();
    }//GEN-LAST:event_ebs_jBtnCancelarActionPerformed

    private void ebs_jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnPesquisarActionPerformed
        JDlgUsuarioPesquisar jDlgUP = new JDlgUsuarioPesquisar(null, true);
        jDlgUP.setVisible(true);
    }//GEN-LAST:event_ebs_jBtnPesquisarActionPerformed

    private void ebs_jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnIncluirActionPerformed
        habilitar(true);
        limparCampos();
        incluir = true;
    }//GEN-LAST:event_ebs_jBtnIncluirActionPerformed

    private void ebs_jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnAlterarActionPerformed
        if (pesquisar == false) {
            //INSTACIAR TELA
            JDlgUsuarioPesquisar jDlgUP = new JDlgUsuarioPesquisar(null, true);
            jDlgUP.setVisible(true);
        }
        habilitar(true);
        Util.habilitar(false, ebs_jTxtid_usuario);
        incluir = false;
        pesquisar = false;
    }//GEN-LAST:event_ebs_jBtnAlterarActionPerformed

    private void ebs_jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnExcluirActionPerformed
        if (pesquisar == false) {
            JDlgUsuarioPesquisar jDlgUP = new JDlgUsuarioPesquisar(null, true);
            jDlgUP.setVisible(true);
        }
        if (Util.perguntar("Confirme exclusão!", "Deletar registro")) {
            DAOgeneric dao = new DAOgeneric();
            dao.delete(viewbean());
            JOptionPane.showMessageDialog(null, "Exclusão realizada");
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Exclusão cancelada");
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
        habilitar(false);
    }//GEN-LAST:event_ebs_jBtnConfirmarActionPerformed

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
            java.util.logging.Logger.getLogger(JDlgUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgUsuario dialog = new JDlgUsuario(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel ebs_JLblcpf;
    private javax.swing.JLabel ebs_JLbldata_nasc;
    private javax.swing.JLabel ebs_JLblnivel;
    private javax.swing.JLabel ebs_JLblsenha;
    private javax.swing.JButton ebs_jBtnAlterar;
    private javax.swing.JButton ebs_jBtnCancelar;
    private javax.swing.JButton ebs_jBtnConfirmar;
    private javax.swing.JButton ebs_jBtnExcluir;
    private javax.swing.JButton ebs_jBtnIncluir;
    private javax.swing.JButton ebs_jBtnPesquisar;
    private javax.swing.JComboBox<String> ebs_jCbonivel;
    private javax.swing.JCheckBox ebs_jChbativo;
    private javax.swing.JFormattedTextField ebs_jFmtcpf;
    private javax.swing.JFormattedTextField ebs_jFmtdata_nasc;
    private javax.swing.JLabel ebs_jLblapelido;
    private javax.swing.JLabel ebs_jLblid_usuario;
    private javax.swing.JLabel ebs_jLblnome;
    private javax.swing.JPasswordField ebs_jPwfsenha;
    private javax.swing.JTextField ebs_jTxtapelido;
    private javax.swing.JTextField ebs_jTxtid_usuario;
    private javax.swing.JTextField ebs_jTxtnome;
    // End of variables declaration//GEN-END:variables
}

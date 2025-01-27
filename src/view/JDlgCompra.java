/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import bean.EbsCompra;
import bean.EbsCompraProduto;
import bean.EbsFornecedor;
import bean.EbsUsuario;
import controller.ControllerCompraProdutos;
import dao.DAOgeneric;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import tools.Util;

/**
 *
 * @author bispo
 */
public class JDlgCompra extends javax.swing.JDialog {

    /**
     * Creates new form JDlgPedidos
     *
     * @param parent
     * @param modal
     */
    boolean incluir, pesquisar;
    ControllerCompraProdutos controllerCompraProdutos;
    DAOgeneric dao;

    public JDlgCompra(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        setTitle("Compra");
        setLocationRelativeTo(null);
        habilitar(false);
        Util.habilitar(false, ebs_jTxtTotal);
        Util.maskData(ebs_jFmtDataCompra);

        dao = new DAOgeneric();
        List listFornecedor = dao.listAll(EbsFornecedor.class);
        for (Object f : listFornecedor) {
            ebs_jCboFornecedor.addItem((EbsFornecedor) f);
            //System.out.println(f);
        }
        List listUsuario = dao.listAll(EbsUsuario.class);
        for (Object u : listUsuario) {
            ebs_jCboUsuario.addItem((EbsUsuario) u);
            //System.out.println(f);
        }

        controllerCompraProdutos = new ControllerCompraProdutos();
        controllerCompraProdutos.setList(new ArrayList());
        ebs_jTblCompraProduto.setModel(controllerCompraProdutos);

        ebs_jTxtTotal.setText("0");
        controllerCompraProdutos.addTableModelListener((TableModelEvent e) -> {
            List compraProdutos = controllerCompraProdutos.getList();
            if (compraProdutos.isEmpty()) {
                ebs_jTxtTotal.setText("0");
                return;
            }
            double total = 0;
            for (Object produto : compraProdutos) {
                EbsCompraProduto p = (EbsCompraProduto) produto;
                double valUnit = p.getEbsValorUnit();
                int quant = p.getEbsQuantidade();
                total += valUnit * quant;
                ebs_jTxtTotal.setText(Util.doubleToStr(total));
            }
        });

    }

    private void habilitar(boolean status) {
        if (status) {
            Util.habilitar(true, ebs_jTxtIdCompra, ebs_jFmtDataCompra, ebs_jCboFornecedor, ebs_jCboUsuario, ebs_jBtnCancelar, ebs_jBtnConfirmar,
                    ebs_jBtnIncluirCompraProduto, ebs_jBtnAlterarCompraProduto, ebs_jBtnExcluirCompraProduto);
            Util.habilitar(false, ebs_jBtnIncluir, ebs_jBtnAlterar, ebs_jBtnPesquisar, ebs_jBtnExcluir);
        } else {
            Util.habilitar(false, ebs_jTxtIdCompra, ebs_jFmtDataCompra, ebs_jCboFornecedor, ebs_jCboUsuario, ebs_jBtnCancelar, ebs_jBtnConfirmar,
                    ebs_jBtnIncluirCompraProduto, ebs_jBtnAlterarCompraProduto, ebs_jBtnExcluirCompraProduto);
            Util.habilitar(true, ebs_jBtnIncluir, ebs_jBtnAlterar, ebs_jBtnPesquisar, ebs_jBtnExcluir);
        }
    }

    private void limparCampos() {
        Util.limpar(ebs_jTxtIdCompra, ebs_jFmtDataCompra, ebs_jCboFornecedor, ebs_jCboUsuario);
        int rows = ebs_jTblCompraProduto.getRowCount();
        for (int i = 0; i < rows; i++) {
            controllerCompraProdutos.removeBean(controllerCompraProdutos.getBean(0));
        }
    }

    private EbsCompra viewbean() {
        // instaciando a classe
        EbsCompra c = new EbsCompra();

        try {
            // Verificar se o campo ID do usuário está vazio
            String idText = ebs_jTxtIdCompra.getText();
            if (idText.isEmpty()) {
                Util.mostrar("O ID do usuário não pode estar vazio", "ERRO");
                return null;
            } // Adiciona o id da compra na Classe
            c.setEbsIdCompra(Util.strToInt(idText));

            // Verificar se a data de nascimento está vazia
            String dataCompraText = ebs_jFmtDataCompra.getText();
            if (dataCompraText.isEmpty()) {
                Util.mostrar("A data de nascimento não pode estar vazia", "ERRO");
                return null;
            } // Adiciona a data de nascimento da compra na Classe
            c.setEbsDataCompra(Util.strToDate(dataCompraText));

            // Verificar se o fornecedor foi selecionado
            EbsFornecedor f = (EbsFornecedor) ebs_jCboFornecedor.getSelectedItem();
            if (f == null) {
                Util.mostrar("Nenhum fornecedor foi selecionado", "ERRO");
                return null;
            }// Adiciona o fornecedor da compra na Classe
            c.setEbsFornecedor(f);

            // Verificar se o fornecedor foi selecionado
            EbsUsuario u = (EbsUsuario) ebs_jCboUsuario.getSelectedItem();
            if (u == null) {
                Util.mostrar("Nenhum usuario foi selecionado", "ERRO");
                return null;
            }// Adiciona a usuario da compra na Classe
            c.setEbsUsuario(u);

            // Verificar se o campo Total do usuário está vazio
            String totalText = ebs_jTxtTotal.getText();
            // Adiciona o id da compra na Classe
            c.setEbsTotal(Util.strToDouble(totalText));

        } catch (NumberFormatException | ParseException ex) {
            Util.mostrar("Erro ao converter valores", "ERRO");
            Logger.getLogger(JDlgUsuario.class.getName()).log(Level.SEVERE, "Erro ao preencher usuário", ex);
            return null;
        }
        return c;
    }

    public void beanview(EbsCompra c) {
        ebs_jTxtIdCompra.setText(Util.intToStr(c.getEbsIdCompra()));
        ebs_jFmtDataCompra.setText(Util.dateToStr((Date) c.getEbsDataCompra()));
        ebs_jCboFornecedor.setSelectedItem(c.getEbsFornecedor());
        ebs_jCboUsuario.setSelectedItem(c.getEbsUsuario());
        ebs_jTxtTotal.setText(Util.doubleToStr(c.getEbsTotal()));

        controllerCompraProdutos.setList(compIDcompraProduto(c.getEbsIdCompra()));
        //     System.out.println("Lst " + lstCompraProduto);
        //    System.out.println("LstBV " + lstCompraProdutoBv);
    }

    private List compIDcompraProduto(int idCompra) {
        List lstCompraProduto = dao.listAll(EbsCompraProduto.class);
        List lstCompraProdutoBv = new ArrayList();
        for (Object produto : lstCompraProduto) {
            EbsCompraProduto p = (EbsCompraProduto) produto;
//            int idCompraProdutoListss = p.getId().getEbsFkCompra();
            int idCompraProdutoList = p.getEbsCompra().getEbsIdCompra();
            if (idCompraProdutoList == idCompra) {
                lstCompraProdutoBv.add(p);
            }
        }
        return lstCompraProdutoBv;
    }
    
    private void telaPesquisar() {
        limparCampos();
        pesquisar = true;
        Class c = EbsCompra.class;
        EbsCompra o = new EbsCompra();
        Class ct = JDlgCompra.class;
        JDlgPesquisar jDlgP = new JDlgPesquisar(null, true, c, o, ct, this, "Compra");
        jDlgP.setVisible(true);
    }

    private void telaPesquisarProduto() {
        int id = viewbean().getEbsIdCompra();
        JDlgCompraProduto jDlgCp = new JDlgCompraProduto(null, true, this, id);
        jDlgCp.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLblIdCompra = new javax.swing.JLabel();
        jLblData = new javax.swing.JLabel();
        jLblCliente = new javax.swing.JLabel();
        jLblUsuario = new javax.swing.JLabel();
        jLblTotal = new javax.swing.JLabel();
        ebs_jTxtIdCompra = new javax.swing.JTextField();
        ebs_jFmtDataCompra = new javax.swing.JFormattedTextField();
        ebs_jCboFornecedor = new javax.swing.JComboBox<EbsFornecedor>();
        ebs_jCboUsuario = new javax.swing.JComboBox<EbsUsuario>();
        ebs_jTxtTotal = new javax.swing.JTextField();
        ebs_jSlpCompraProduto = new javax.swing.JScrollPane();
        ebs_jTblCompraProduto = new javax.swing.JTable();
        ebs_jBtnIncluirCompraProduto = new javax.swing.JButton();
        ebs_jBtnAlterarCompraProduto = new javax.swing.JButton();
        ebs_jBtnExcluirCompraProduto = new javax.swing.JButton();
        ebs_jBtnCancelar = new javax.swing.JButton();
        ebs_jBtnPesquisar = new javax.swing.JButton();
        ebs_jBtnIncluir = new javax.swing.JButton();
        ebs_jBtnAlterar = new javax.swing.JButton();
        ebs_jBtnExcluir = new javax.swing.JButton();
        ebs_jBtnConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLblIdCompra.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLblIdCompra.setText("ID");

        jLblData.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLblData.setText("Data");

        jLblCliente.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLblCliente.setText("Fornecedor");

        jLblUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLblUsuario.setText("Usuario");

        jLblTotal.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLblTotal.setText("Total");

        ebs_jTxtIdCompra.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        ebs_jFmtDataCompra.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        ebs_jCboFornecedor.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        ebs_jCboUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        ebs_jTxtTotal.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        ebs_jSlpCompraProduto.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        ebs_jTblCompraProduto.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ebs_jTblCompraProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        ebs_jTblCompraProduto.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                ebs_jTblCompraProdutoAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        ebs_jTblCompraProduto.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ebs_jTblCompraProdutoPropertyChange(evt);
            }
        });
        ebs_jTblCompraProduto.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                ebs_jTblCompraProdutoVetoableChange(evt);
            }
        });
        ebs_jSlpCompraProduto.setViewportView(ebs_jTblCompraProduto);

        ebs_jBtnIncluirCompraProduto.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        ebs_jBtnIncluirCompraProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add1.png"))); // NOI18N
        ebs_jBtnIncluirCompraProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnIncluirCompraProdutoActionPerformed(evt);
            }
        });

        ebs_jBtnAlterarCompraProduto.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        ebs_jBtnAlterarCompraProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        ebs_jBtnAlterarCompraProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnAlterarCompraProdutoActionPerformed(evt);
            }
        });

        ebs_jBtnExcluirCompraProduto.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        ebs_jBtnExcluirCompraProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/deletar.png"))); // NOI18N
        ebs_jBtnExcluirCompraProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jBtnExcluirCompraProdutoActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLblIdCompra)
                                    .addComponent(ebs_jTxtIdCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_jFmtDataCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLblData))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_jCboFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLblCliente))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_jCboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLblUsuario))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_jTxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLblTotal)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ebs_jSlpCompraProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ebs_jBtnIncluirCompraProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ebs_jBtnAlterarCompraProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ebs_jBtnExcluirCompraProduto)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
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
                        .addComponent(ebs_jBtnPesquisar)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblIdCompra)
                    .addComponent(jLblData)
                    .addComponent(jLblCliente)
                    .addComponent(jLblUsuario)
                    .addComponent(jLblTotal))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ebs_jTxtIdCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ebs_jFmtDataCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ebs_jCboFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ebs_jCboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ebs_jTxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ebs_jSlpCompraProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ebs_jBtnIncluirCompraProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ebs_jBtnAlterarCompraProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ebs_jBtnExcluirCompraProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ebs_jBtnIncluir)
                    .addComponent(ebs_jBtnConfirmar)
                    .addComponent(ebs_jBtnAlterar)
                    .addComponent(ebs_jBtnExcluir)
                    .addComponent(ebs_jBtnCancelar)
                    .addComponent(ebs_jBtnPesquisar))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ebs_jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnCancelarActionPerformed
        incluir = false;
        habilitar(false);
        limparCampos();
    }//GEN-LAST:event_ebs_jBtnCancelarActionPerformed

    private void ebs_jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnPesquisarActionPerformed
        telaPesquisar();
    }//GEN-LAST:event_ebs_jBtnPesquisarActionPerformed

    private void ebs_jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnIncluirActionPerformed
        habilitar(true);
        limparCampos();
        incluir = true;
    }//GEN-LAST:event_ebs_jBtnIncluirActionPerformed

    private void ebs_jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnAlterarActionPerformed
        if (pesquisar == false) {
            telaPesquisar();
        }
        habilitar(true);
        Util.habilitar(false, ebs_jTxtIdCompra);
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
        dao = new DAOgeneric();
        if (incluir == true) {
            dao.insert(viewbean());
            for (int i = 0; i < ebs_jTblCompraProduto.getRowCount(); i++) {
                EbsCompraProduto compraProduto = controllerCompraProdutos.getBean(i);
                compraProduto.setEbsCompra(viewbean());
                dao.insert(compraProduto);
            }
        } else {
            dao.update(viewbean());
        }
        limparCampos();
        habilitar(false);
    }//GEN-LAST:event_ebs_jBtnConfirmarActionPerformed

    private void ebs_jBtnIncluirCompraProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnIncluirCompraProdutoActionPerformed
        telaPesquisarProduto();
    }//GEN-LAST:event_ebs_jBtnIncluirCompraProdutoActionPerformed

    private void ebs_jBtnAlterarCompraProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnAlterarCompraProdutoActionPerformed
        telaPesquisarProduto();
    }//GEN-LAST:event_ebs_jBtnAlterarCompraProdutoActionPerformed

    private void ebs_jBtnExcluirCompraProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnExcluirCompraProdutoActionPerformed
        if (Util.perguntar("Confirme a remoção!", "Remover registro")) {
            int row = ebs_jTblCompraProduto.getSelectedRow();
            controllerCompraProdutos.removeBean(controllerCompraProdutos.getBean(row));
            Util.mostrar("Remoção realizada", "Aviso");
        } else {
            Util.mostrar("Remoção cancelada", "Aviso");
            habilitar(false);
        }
    }//GEN-LAST:event_ebs_jBtnExcluirCompraProdutoActionPerformed

    private void ebs_jTblCompraProdutoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ebs_jTblCompraProdutoPropertyChange
    }//GEN-LAST:event_ebs_jTblCompraProdutoPropertyChange

    private void ebs_jTblCompraProdutoVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_ebs_jTblCompraProdutoVetoableChange

    }//GEN-LAST:event_ebs_jTblCompraProdutoVetoableChange

    private void ebs_jTblCompraProdutoAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ebs_jTblCompraProdutoAncestorMoved

    }//GEN-LAST:event_ebs_jTblCompraProdutoAncestorMoved

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
            java.util.logging.Logger.getLogger(JDlgCompra.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgCompra.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgCompra.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgCompra.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            JDlgCompra dialog = new JDlgCompra(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton ebs_jBtnAlterar;
    private javax.swing.JButton ebs_jBtnAlterarCompraProduto;
    private javax.swing.JButton ebs_jBtnCancelar;
    private javax.swing.JButton ebs_jBtnConfirmar;
    private javax.swing.JButton ebs_jBtnExcluir;
    private javax.swing.JButton ebs_jBtnExcluirCompraProduto;
    private javax.swing.JButton ebs_jBtnIncluir;
    private javax.swing.JButton ebs_jBtnIncluirCompraProduto;
    private javax.swing.JButton ebs_jBtnPesquisar;
    private javax.swing.JComboBox<EbsFornecedor> ebs_jCboFornecedor;
    private javax.swing.JComboBox<EbsUsuario> ebs_jCboUsuario;
    private javax.swing.JFormattedTextField ebs_jFmtDataCompra;
    private javax.swing.JScrollPane ebs_jSlpCompraProduto;
    private javax.swing.JTable ebs_jTblCompraProduto;
    private javax.swing.JTextField ebs_jTxtIdCompra;
    private javax.swing.JTextField ebs_jTxtTotal;
    private javax.swing.JLabel jLblCliente;
    private javax.swing.JLabel jLblData;
    private javax.swing.JLabel jLblIdCompra;
    private javax.swing.JLabel jLblTotal;
    private javax.swing.JLabel jLblUsuario;
    // End of variables declaration//GEN-END:variables
}

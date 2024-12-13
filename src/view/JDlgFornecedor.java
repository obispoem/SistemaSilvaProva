/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import bean.EbsFornecedor;
import bean.EbsTransportadora;
import dao.DAOgeneric;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tools.Util;

/**
 *
 * @author bispo
 */
public class JDlgFornecedor extends javax.swing.JDialog {

    /**
     * Creates new form jDlgUsuario
     */
    boolean incluir;
    boolean pesquisar;

    public JDlgFornecedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Fornecedor");
        setLocationRelativeTo(null);
        habilitar(false);

        Util.maskCNPJ(ebs_jFmtcnpj);
        Util.maskTelefone(ebs_jFmttelefone);
        Util.maskTelefone(ebs_jFmtcelular);

        DAOgeneric dao = new DAOgeneric();
        List lstTransportadora = dao.listAll(EbsTransportadora.class);
        for (Object c : lstTransportadora) {
            ebs_jCbofk_transportadora.addItem((EbsTransportadora) c);
        }

    }

    private void habilitar(boolean status) {
        if (status) {
            Util.habilitar(true, ebs_jTxtid_fornecedor, ebs_jCbofk_transportadora, ebs_jChbativo, ebs_jTxtnome_fornecedor,
                    ebs_jFmtcelular, ebs_jTxtnome_empresa, ebs_jFmttelefone, ebs_jTxtnome_fant, ebs_jFmtcnpj,
                    ebs_jTxtsite_empresa, ebs_jTxtemail, ebs_jTxtendereco, ebs_jTxtnumero, ebs_jTxtbairro, ebs_jTxtcidade,
                    ebs_jCboestado, ebs_jBtnCancelar, ebs_jBtnConfirmar);
            Util.habilitar(false, ebs_jBtnIncluir, ebs_jBtnAlterar, ebs_jBtnPesquisar, ebs_jBtnExcluir);
        } else {
            Util.habilitar(false, ebs_jTxtid_fornecedor, ebs_jCbofk_transportadora, ebs_jChbativo, ebs_jTxtnome_fornecedor,
                    ebs_jFmtcelular, ebs_jTxtnome_empresa, ebs_jFmttelefone, ebs_jTxtnome_fant, ebs_jFmtcnpj,
                    ebs_jTxtsite_empresa, ebs_jTxtemail, ebs_jTxtendereco, ebs_jTxtnumero, ebs_jTxtbairro, ebs_jTxtcidade,
                    ebs_jCboestado, ebs_jBtnCancelar, ebs_jBtnConfirmar);
            Util.habilitar(true, ebs_jBtnIncluir, ebs_jBtnAlterar, ebs_jBtnPesquisar, ebs_jBtnExcluir);
        }
    }

    private void limparCampos() {
        Util.limpar(ebs_jTxtid_fornecedor, ebs_jCbofk_transportadora, ebs_jChbativo, ebs_jTxtnome_fornecedor,
                ebs_jFmtcelular, ebs_jTxtnome_empresa, ebs_jFmttelefone, ebs_jTxtnome_fant, ebs_jFmtcnpj,
                ebs_jTxtsite_empresa, ebs_jTxtemail, ebs_jTxtendereco, ebs_jTxtnumero, ebs_jTxtbairro, ebs_jTxtcidade, ebs_jCboestado);
    }

    public EbsFornecedor viewbean() {
        EbsFornecedor f = new EbsFornecedor();

        try {
            // Verificar se o campo ID do fornecedor está vazio
            String idText = ebs_jTxtid_fornecedor.getText();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O ID do fornecedor não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o ID do fornecedor na Classe
            f.setEbsIdFornecedor(Util.strToInt(idText));

            // Verificar se o campo transportadora foi selecionado
            EbsTransportadora fkT = (EbsTransportadora) ebs_jCbofk_transportadora.getSelectedItem();
            if (fkT == null) {
                JOptionPane.showMessageDialog(this, "Nenhuma transportadora selecionada", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona a FK da transportadora na Classe
            f.setEbsTransportadora((EbsTransportadora) fkT);

            // Definir se o fornecedor está ativo e adicionar na classe            
            f.setEbsAtivo(ebs_jChbativo.isSelected() ? "s" : "n");

            // Verificar se o nome do fornecedor está vazio
            String nomeFornecedor = ebs_jTxtnome_fornecedor.getText();
            if (nomeFornecedor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome do fornecedor não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o nome do fornecedor na Classe
            f.setEbsNomeFornecedor(nomeFornecedor);

            // Verificar se o celular está vazio
            String celular = ebs_jFmtcelular.getText();
            if (celular.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O celular não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o celular do fornecedor na Classe
            f.setEbsCelular(celular);

            // Verificar se o nome da empresa está vazio
            String nomeEmpresa = ebs_jTxtnome_empresa.getText();
            if (nomeEmpresa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome da empresa não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o nome da empresa na Classe
            f.setEbsNomeEmpresa(nomeEmpresa);

            // Verificar se o telefone está vazio
            String telefone = ebs_jFmttelefone.getText();
            if (telefone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O telefone não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o telefone do fornecedor na Classe
            f.setEbsTelefone(telefone);

            // Verificar se o nome fantasia está vazio
            String nomeFantasia = ebs_jTxtnome_fant.getText();
            if (nomeFantasia.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome fantasia não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o nome fantasia na Classe
            f.setEbsNomeFant(nomeFantasia);

            // Verificar se o CNPJ está vazio
            String cnpj = ebs_jFmtcnpj.getText();
            if (cnpj.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O CNPJ não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o CNPJ do fornecedor na Classe
            f.setEbsCnpj(cnpj);

            // Verificar se o site da empresa está vazio
            String siteEmpresa = ebs_jTxtsite_empresa.getText();
            if (siteEmpresa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O site da empresa não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o site da empresa na Classe
            f.setEbsSiteEmpresa(siteEmpresa);

            // Verificar se o email está vazio
            String email = ebs_jTxtemail.getText();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O email não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o email do fornecedor na Classe
            f.setEbsEmail(email);

            // Verificar se o endereço está vazio
            String endereco = ebs_jTxtendereco.getText();
            if (endereco.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O endereço não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o endereço na Classe
            f.setEbsEndereco(endereco);

            // Verificar se o número está vazio
            String numero = ebs_jTxtnumero.getText();
            if (numero.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O número não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o número na Classe
            f.setEbsNumero(numero);

            // Verificar se o bairro está vazio
            String bairro = ebs_jTxtbairro.getText();
            if (bairro.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O bairro não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o bairro na Classe
            f.setEbsBairro(bairro);

            // Verificar se a cidade está vazia
            String cidade = ebs_jTxtcidade.getText();
            if (cidade.isEmpty()) {
                JOptionPane.showMessageDialog(this, "A cidade não pode estar vazia", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona a cidade na Classe
            f.setEbsCidade(cidade);

            // Verificar se o estado foi selecionado
            int estadoSelecionado = ebs_jCboestado.getSelectedIndex();
            if (estadoSelecionado == 0) {
                JOptionPane.showMessageDialog(this, "Nenhum estado selecionado", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            } // Adiciona o estado na Classe
            f.setEbsEstado(ebs_jCboestado.getSelectedItem().toString());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao converter valores", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(JDlgFornecedor.class.getName()).log(Level.SEVERE, "Erro ao preencher fornecedor", ex);
            return null;
        }
        return f;
    }

    public void beanview(EbsFornecedor f) {
        ebs_jTxtid_fornecedor.setText(String.valueOf(f.getEbsIdFornecedor()));
        ebs_jCbofk_transportadora.setSelectedItem(f.getEbsTransportadora());
        ebs_jChbativo.setSelected("s".equals(f.getEbsAtivo()));
        ebs_jTxtnome_fornecedor.setText(f.getEbsNomeFornecedor());
        ebs_jFmtcelular.setText(f.getEbsCelular());
        ebs_jTxtnome_empresa.setText(f.getEbsNomeEmpresa());
        ebs_jFmttelefone.setText(f.getEbsTelefone());
        ebs_jTxtnome_fant.setText(f.getEbsNomeFant());
        ebs_jFmtcnpj.setText(f.getEbsCnpj());
        ebs_jTxtsite_empresa.setText(f.getEbsSiteEmpresa());
        ebs_jTxtemail.setText(f.getEbsEmail());
        ebs_jTxtendereco.setText(f.getEbsEndereco());
        ebs_jTxtnumero.setText(f.getEbsNumero());
        ebs_jTxtbairro.setText(f.getEbsBairro());
        ebs_jTxtcidade.setText(f.getEbsCidade());
        ebs_jCboestado.setSelectedItem(f.getEbsEstado());
    }

    private void telaPesquisar() {
        Class c = EbsFornecedor.class;
        EbsFornecedor o = new EbsFornecedor();
        Class ct = JDlgFornecedor.class;
        JDlgPesquisar jDlgP = new JDlgPesquisar(null, true, c, o, ct, this, "Fornecedor");
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

        ebs_jLblid_fornecedor = new javax.swing.JLabel();
        ebs_jLbltransportadora = new javax.swing.JLabel();
        ebs_jLblnome_fornecedor = new javax.swing.JLabel();
        ebs_jLblcelular = new javax.swing.JLabel();
        ebs_jLblnome_empresa = new javax.swing.JLabel();
        ebs_jLbltelefone = new javax.swing.JLabel();
        ebs_jLblnome_fant = new javax.swing.JLabel();
        ebs_jLblcnpj = new javax.swing.JLabel();
        ebs_jLblsite_empresa = new javax.swing.JLabel();
        ebs_jLblemail = new javax.swing.JLabel();
        ebs_jLblendereco = new javax.swing.JLabel();
        ebs_jLblnumero = new javax.swing.JLabel();
        ebs_jLblbairro = new javax.swing.JLabel();
        ebs_jLblcidade = new javax.swing.JLabel();
        ebs_jLblestado = new javax.swing.JLabel();
        ebs_jTxtid_fornecedor = new javax.swing.JTextField();
        ebs_jTxtnome_fornecedor = new javax.swing.JTextField();
        ebs_jFmtcelular = new javax.swing.JFormattedTextField();
        ebs_jTxtnome_empresa = new javax.swing.JTextField();
        ebs_jFmttelefone = new javax.swing.JFormattedTextField();
        ebs_jTxtnome_fant = new javax.swing.JTextField();
        ebs_jFmtcnpj = new javax.swing.JFormattedTextField();
        ebs_jTxtsite_empresa = new javax.swing.JTextField();
        ebs_jTxtemail = new javax.swing.JTextField();
        ebs_jTxtendereco = new javax.swing.JTextField();
        ebs_jTxtnumero = new javax.swing.JTextField();
        ebs_jTxtbairro = new javax.swing.JTextField();
        ebs_jTxtcidade = new javax.swing.JTextField();
        ebs_jCbofk_transportadora = new javax.swing.JComboBox<EbsTransportadora>();
        ebs_jChbativo = new javax.swing.JCheckBox();
        ebs_jCboestado = new javax.swing.JComboBox<String>();
        ebs_jBtnIncluir = new javax.swing.JButton();
        ebs_jBtnAlterar = new javax.swing.JButton();
        ebs_jBtnExcluir = new javax.swing.JButton();
        ebs_jBtnConfirmar = new javax.swing.JButton();
        ebs_jBtnCancelar = new javax.swing.JButton();
        ebs_jBtnPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(new java.awt.Font("Dubai", 0, 12)); // NOI18N

        ebs_jLblid_fornecedor.setText("ID");

        ebs_jLbltransportadora.setText("Transportadora");

        ebs_jLblnome_fornecedor.setText("Nome Fornecedor");

        ebs_jLblcelular.setText("Celular");

        ebs_jLblnome_empresa.setText("Nome Empresa");

        ebs_jLbltelefone.setText("Telefone");

        ebs_jLblnome_fant.setText("Nome Fantoche");

        ebs_jLblcnpj.setText("CNPJ");

        ebs_jLblsite_empresa.setText("Site");

        ebs_jLblemail.setText("Email");

        ebs_jLblendereco.setText("Endereço");

        ebs_jLblnumero.setText("Número");

        ebs_jLblbairro.setText("Bairro");

        ebs_jLblcidade.setText("Cidade");

        ebs_jLblestado.setText("Estado");

        ebs_jTxtnome_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jTxtnome_empresaActionPerformed(evt);
            }
        });

        ebs_jChbativo.setText("Ativo");

        ebs_jCboestado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" }));
        ebs_jCboestado.setToolTipText("");
        ebs_jCboestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebs_jCboestadoActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_jLblsite_empresa)
                                    .addComponent(ebs_jTxtsite_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_jTxtemail)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ebs_jLblemail)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(ebs_jTxtendereco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ebs_jLblbairro)
                                                .addComponent(ebs_jTxtbairro, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ebs_jTxtcidade, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ebs_jLblcidade))))
                                    .addComponent(ebs_jLblendereco))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_jTxtnumero)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ebs_jLblestado)
                                            .addComponent(ebs_jLblnumero)
                                            .addComponent(ebs_jCboestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_jLblnome_empresa)
                                    .addComponent(ebs_jTxtnome_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_jLbltelefone)
                                    .addComponent(ebs_jFmttelefone)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_jLblnome_fant)
                                    .addComponent(ebs_jTxtnome_fant, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ebs_jLblcnpj)
                                    .addComponent(ebs_jFmtcnpj)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ebs_jTxtid_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ebs_jLblid_fornecedor))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ebs_jLbltransportadora)
                                            .addComponent(ebs_jCbofk_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(ebs_jLblnome_fornecedor)
                                    .addComponent(ebs_jTxtnome_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ebs_jChbativo)
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(ebs_jLblcelular)
                                            .addGap(18, 18, 18))
                                        .addComponent(ebs_jFmtcelular)))))
                        .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ebs_jLblid_fornecedor)
                    .addComponent(ebs_jLbltransportadora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ebs_jTxtid_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ebs_jCbofk_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ebs_jChbativo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ebs_jLblnome_fornecedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ebs_jTxtnome_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ebs_jLblcelular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ebs_jFmtcelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ebs_jLblnome_empresa)
                            .addComponent(ebs_jLbltelefone))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ebs_jTxtnome_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ebs_jFmttelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ebs_jLblnome_fant)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ebs_jTxtnome_fant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ebs_jLblcnpj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ebs_jFmtcnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ebs_jLblsite_empresa)
                            .addComponent(ebs_jLblemail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ebs_jTxtsite_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ebs_jTxtemail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ebs_jLblendereco)
                    .addComponent(ebs_jLblnumero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ebs_jTxtendereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ebs_jTxtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ebs_jLblbairro)
                    .addComponent(ebs_jLblcidade)
                    .addComponent(ebs_jLblestado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ebs_jTxtbairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ebs_jTxtcidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ebs_jCboestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ebs_jBtnIncluir)
                    .addComponent(ebs_jBtnConfirmar)
                    .addComponent(ebs_jBtnAlterar)
                    .addComponent(ebs_jBtnExcluir)
                    .addComponent(ebs_jBtnCancelar)
                    .addComponent(ebs_jBtnPesquisar))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ebs_jTxtnome_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jTxtnome_empresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ebs_jTxtnome_empresaActionPerformed
    private void ebs_jCboestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jCboestadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ebs_jCboestadoActionPerformed

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
        Util.habilitar(false, ebs_jTxtid_fornecedor);
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
        limparCampos();
        habilitar(false);
    }//GEN-LAST:event_ebs_jBtnConfirmarActionPerformed

    private void ebs_jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnCancelarActionPerformed
        incluir = false;
        habilitar(false);
        limparCampos();
    }//GEN-LAST:event_ebs_jBtnCancelarActionPerformed

    private void ebs_jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebs_jBtnPesquisarActionPerformed
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
            java.util.logging.Logger.getLogger(JDlgFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                JDlgFornecedor dialog = new JDlgFornecedor(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton ebs_jBtnAlterar;
    private javax.swing.JButton ebs_jBtnCancelar;
    private javax.swing.JButton ebs_jBtnConfirmar;
    private javax.swing.JButton ebs_jBtnExcluir;
    private javax.swing.JButton ebs_jBtnIncluir;
    private javax.swing.JButton ebs_jBtnPesquisar;
    private javax.swing.JComboBox<String> ebs_jCboestado;
    private javax.swing.JComboBox<EbsTransportadora> ebs_jCbofk_transportadora;
    private javax.swing.JCheckBox ebs_jChbativo;
    private javax.swing.JFormattedTextField ebs_jFmtcelular;
    private javax.swing.JFormattedTextField ebs_jFmtcnpj;
    private javax.swing.JFormattedTextField ebs_jFmttelefone;
    private javax.swing.JLabel ebs_jLblbairro;
    private javax.swing.JLabel ebs_jLblcelular;
    private javax.swing.JLabel ebs_jLblcidade;
    private javax.swing.JLabel ebs_jLblcnpj;
    private javax.swing.JLabel ebs_jLblemail;
    private javax.swing.JLabel ebs_jLblendereco;
    private javax.swing.JLabel ebs_jLblestado;
    private javax.swing.JLabel ebs_jLblid_fornecedor;
    private javax.swing.JLabel ebs_jLblnome_empresa;
    private javax.swing.JLabel ebs_jLblnome_fant;
    private javax.swing.JLabel ebs_jLblnome_fornecedor;
    private javax.swing.JLabel ebs_jLblnumero;
    private javax.swing.JLabel ebs_jLblsite_empresa;
    private javax.swing.JLabel ebs_jLbltelefone;
    private javax.swing.JLabel ebs_jLbltransportadora;
    private javax.swing.JTextField ebs_jTxtbairro;
    private javax.swing.JTextField ebs_jTxtcidade;
    private javax.swing.JTextField ebs_jTxtemail;
    private javax.swing.JTextField ebs_jTxtendereco;
    private javax.swing.JTextField ebs_jTxtid_fornecedor;
    private javax.swing.JTextField ebs_jTxtnome_empresa;
    private javax.swing.JTextField ebs_jTxtnome_fant;
    private javax.swing.JTextField ebs_jTxtnome_fornecedor;
    private javax.swing.JTextField ebs_jTxtnumero;
    private javax.swing.JTextField ebs_jTxtsite_empresa;
    // End of variables declaration//GEN-END:variables
}

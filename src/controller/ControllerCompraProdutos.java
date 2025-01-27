/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bean.EbsCompraProduto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author bispo
 */
public class ControllerCompraProdutos extends AbstractTableModel {

    private List lstEbsCompraProduto;

    public void setList(List lstEbsCompraProduto) {
        this.lstEbsCompraProduto = lstEbsCompraProduto;
        this.fireTableDataChanged();
    }
    
    public List getList() {
        return this.lstEbsCompraProduto;
    }

    public void addBean(EbsCompraProduto compraProduto){
        this.lstEbsCompraProduto.add(compraProduto);
        this.fireTableDataChanged();
    }
    public void removeBean(EbsCompraProduto compraProduto){
        this.lstEbsCompraProduto.remove(compraProduto);
        this.fireTableDataChanged();
    }
    public EbsCompraProduto getBean(int rowIndex) {
        return (EbsCompraProduto) lstEbsCompraProduto.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return lstEbsCompraProduto.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EbsCompraProduto compraProdutos = (EbsCompraProduto) lstEbsCompraProduto.get(rowIndex);
        if (columnIndex == 0) {
            return compraProdutos.getId();
        } else if (columnIndex == 1) {
            return compraProdutos.getEbsProduto();
        } else if (columnIndex == 2) {
            return compraProdutos.getEbsQuantidade();
        } else if (columnIndex == 3) {
            return compraProdutos.getEbsValorUnit();
        } else if (columnIndex == 4) {
            return (compraProdutos.getEbsValorUnit()* compraProdutos.getEbsQuantidade());
        }
        return "";
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) {
            return "ID";
        } else if (columnIndex == 1) {
            return "PRODUTO";
        } else if (columnIndex == 2) {
            return "QUANTIDADE";
        } else if (columnIndex == 3) {
            return "VALOR";
        } else if (columnIndex == 4) {
            return "TOTAL";
        }
        return "";
    }
}

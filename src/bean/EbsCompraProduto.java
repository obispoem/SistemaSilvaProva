package bean;
// Generated 08/12/2024 10:31:19 by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * EbsCompraProduto generated by hbm2java
 */
@Entity
@Table(name = "ebs_compra_produto", catalog = "db_emanuel_silva"
)
public class EbsCompraProduto implements java.io.Serializable {

    private EbsCompraProdutoId id;
    private EbsCompra ebsCompra;
    private EbsProduto ebsProduto;
    private int ebsQuantidade;
    private double ebsValorUnit;

    public EbsCompraProduto() {
    }

    public EbsCompraProduto(EbsCompraProdutoId id, EbsCompra ebsCompra, EbsProduto ebsProduto, int ebsQuantidade, double ebsValorUnit) {
        this.id = id;
        this.ebsCompra = ebsCompra;
        this.ebsProduto = ebsProduto;
        this.ebsQuantidade = ebsQuantidade;
        this.ebsValorUnit = ebsValorUnit;
    }

    @EmbeddedId

    @AttributeOverrides({
        @AttributeOverride(name = "ebsFkProduto", column = @Column(name = "ebs_fk_produto", nullable = false)),
        @AttributeOverride(name = "ebsFkCompra", column = @Column(name = "ebs_fk_compra", nullable = false))})
    public EbsCompraProdutoId getId() {
        return this.id;
    }

    public void setId(EbsCompraProdutoId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ebs_fk_compra", nullable = false, insertable = false, updatable = false)
    public EbsCompra getEbsCompra() {
        return this.ebsCompra;
    }

    public void setEbsCompra(EbsCompra ebsCompra) {
        this.ebsCompra = ebsCompra;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ebs_fk_produto", nullable = false, insertable = false, updatable = false)
    public EbsProduto getEbsProduto() {
        return this.ebsProduto;
    }

    public void setEbsProduto(EbsProduto ebsProduto) {
        this.ebsProduto = ebsProduto;
    }

    @Column(name = "ebs_quantidade", nullable = false)
    public int getEbsQuantidade() {
        return this.ebsQuantidade;
    }

    public void setEbsQuantidade(int ebsQuantidade) {
        this.ebsQuantidade = ebsQuantidade;
    }

    @Column(name = "ebs_valor_unit", nullable = false, precision = 10)
    public double getEbsValorUnit() {
        return this.ebsValorUnit;
    }

    public void setEbsValorUnit(double ebsValorUnit) {
        this.ebsValorUnit = ebsValorUnit;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EbsProduto) {
            EbsCompraProduto cp = (EbsCompraProduto) obj;
            if (cp.getId()== this.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {;
        return this.getId()+ ": "+ this.getEbsProduto().getEbsNome();
    }

}

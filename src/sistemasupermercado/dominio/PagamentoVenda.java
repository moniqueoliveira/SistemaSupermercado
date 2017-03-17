
package sistemasupermercado.dominio;

import java.math.BigDecimal;

public class PagamentoVenda {
    private Venda venda;
    private BigDecimal dinheiro;
    private BigDecimal debito;
    private BigDecimal credito;
    private BigDecimal voucher;
    private BigDecimal cheque;
    private BigDecimal outros;
    private BigDecimal troco;
    
    public BigDecimal obterTotal() {
        BigDecimal total = new BigDecimal(0);
        total.add(dinheiro);
        total.add(debito);
        total.add(credito);
        total.add(voucher);
        total.add(cheque);
        total.add(outros);
        total.subtract(troco);
        return total;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    public void setVenda(int idVenda) {
        this.venda = new Venda();
        this.venda.setIdVenda(idVenda);
    }

    public BigDecimal getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(BigDecimal dinheiro) {
        this.dinheiro = dinheiro;
    }

    public BigDecimal getDebito() {
        return debito;
    }

    public void setDebito(BigDecimal debito) {
        this.debito = debito;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }

    public BigDecimal getVoucher() {
        return voucher;
    }

    public void setVoucher(BigDecimal voucher) {
        this.voucher = voucher;
    }

    public BigDecimal getCheque() {
        return cheque;
    }

    public void setCheque(BigDecimal cheque) {
        this.cheque = cheque;
    }

    public BigDecimal getOutros() {
        return outros;
    }

    public void setOutros(BigDecimal outros) {
        this.outros = outros;
    }

    public BigDecimal getTroco() {
        return troco;
    }

    public void setTroco(BigDecimal troco) {
        this.troco = troco;
    }
    
    
}

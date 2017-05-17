
package sistemasupermercado.dominio;

import java.math.BigDecimal;

public class PagamentoVenda {
    private Venda venda;
    private BigDecimal dinheiro = BigDecimal.ZERO;
    private BigDecimal debito = BigDecimal.ZERO;
    private BigDecimal credito = BigDecimal.ZERO;
    private BigDecimal voucher = BigDecimal.ZERO;
    private BigDecimal cheque = BigDecimal.ZERO;
    private BigDecimal outros = BigDecimal.ZERO;
    private BigDecimal troco = BigDecimal.ZERO;
    
    public BigDecimal obterTotal() {
        BigDecimal total = new BigDecimal(0);
        total = total.add(dinheiro);
        total = total.add(debito);
        total = total.add(credito);
        total = total.add(voucher);
        total = total.add(cheque);
        total = total.add(outros);
        total = total.subtract(troco);
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

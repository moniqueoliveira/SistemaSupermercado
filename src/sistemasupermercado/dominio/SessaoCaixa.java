
package sistemasupermercado.dominio;

import java.math.BigDecimal;


public class SessaoCaixa {
    private Sessao sessao;
    private Caixa caixa;
    private BigDecimal valorInicialCaixa;
    private BigDecimal valorFechamento;

    
    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
    
    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }
    
    public void setCaixa(int numeroCaixa) {
        this.caixa = new Caixa();
        this.caixa.setNumeroCaixa(numeroCaixa);
    }

    public BigDecimal getValorInicialCaixa() {
        return valorInicialCaixa;
    }

    public void setValorInicialCaixa(BigDecimal valorInicialCaixa) {
        this.valorInicialCaixa = valorInicialCaixa;
    }

    public BigDecimal getValorFechamento() {
        return valorFechamento;
    }

    public void setValorFechamento(BigDecimal valorFechamento) {
        this.valorFechamento = valorFechamento;
    }
    
}

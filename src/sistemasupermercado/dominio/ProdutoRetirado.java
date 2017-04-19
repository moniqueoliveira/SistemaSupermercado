
package sistemasupermercado.dominio;

import java.math.BigDecimal;
import java.util.Calendar;

public class ProdutoRetirado {
    private int idRetirada;
    private Produto produto;
    private BigDecimal quantidade;
    private Calendar data;
    private Sessao sessao;
    private MotivoProdutoRetirado motivo;
    private String observacao;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getIdRetirada() {
        return idRetirada;
    }

    public void setIdRetirada(int idRetirada) {
        this.idRetirada = idRetirada;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public void setProduto(int codigo) {
        this.produto = new Produto();
        this.produto.setIdProduto(codigo);
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
    
    public void setSessao(int idSessao) {
        this.sessao = new Sessao();
        this.sessao.setIdSessao(idSessao);
    }

    public MotivoProdutoRetirado getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoProdutoRetirado motivo) {
        this.motivo = motivo;
    }
    
    public void setMotivo(int idMotivo) {
        this.motivo = new MotivoProdutoRetirado();
        this.motivo.setIdMotivo(idMotivo);
    }
    
}

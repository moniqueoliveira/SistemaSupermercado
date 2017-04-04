
package sistemasupermercado.dominio;

import java.math.BigDecimal;
import java.util.Calendar;


public class EntradaProduto {
    private int idEntrada;
    private Produto produto;
    private Fornecedor fornecedor;
    private double quantidade;
    private BigDecimal valorUnitario;
    private Calendar data;
    private Sessao sessao;

    public int getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
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

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    public void setFornecedor(int idFornecedor) {
        this.fornecedor = new Fornecedor();
        this.fornecedor.setIdFornecedor(idFornecedor);
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
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
    
}

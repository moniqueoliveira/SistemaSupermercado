
package sistemasupermercado.dominio;

import java.math.BigDecimal;


public class ItemVenda {
    private int numeroItem;
    private Venda venda;
    private Produto produto;
    private BigDecimal quantidade;
    private BigDecimal subtotal;
    private boolean cancelado;

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public int getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(int numeroItem) {
        this.numeroItem = numeroItem;
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
    
}

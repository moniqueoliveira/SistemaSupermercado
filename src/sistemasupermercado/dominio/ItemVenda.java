
package sistemasupermercado.dominio;


public class ItemVenda {
    private Venda venda;
    private Produto produto;
    private double quantidade;

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

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
    
}

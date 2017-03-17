
package sistemasupermercado.dominio;


public class Produto {
    private int codigo;
    private String descricao;
    private String descricaoReduzida;
    private boolean vendaFracionada;
    private CategoriaProduto categoria;
    private String codigoDeBarras;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoReduzida() {
        return descricaoReduzida;
    }

    public void setDescricaoReduzida(String descricaoReduzida) {
        this.descricaoReduzida = descricaoReduzida;
    }

    public boolean isVendaFracionada() {
        return vendaFracionada;
    }

    public void setVendaFracionada(boolean vendaFracionada) {
        this.vendaFracionada = vendaFracionada;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }
    
    public void setCategoria(int idCategoria) {
        this.categoria = new CategoriaProduto();
        this.categoria.setIdCategoria(idCategoria);
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }
    
}

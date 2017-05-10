
package sistemasupermercado.dominio;

import java.util.Objects;


public class Produto {
    private Integer idProduto;
    private String descricao;
    private String descricaoReduzida;
    private boolean vendaFracionada;
    private CategoriaProduto categoria;
    private String codigoDeBarras;
    private boolean estocavel;

    public boolean isEstocavel() {
        return estocavel;
    }

    public void setEstocavel(boolean estocavel) {
        this.estocavel = estocavel;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.idProduto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.idProduto, other.idProduto)) {
            return false;
        }
        return true;
    }
    
}

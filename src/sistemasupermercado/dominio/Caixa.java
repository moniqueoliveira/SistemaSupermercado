
package sistemasupermercado.dominio;

public class Caixa {
    private int numeroCaixa;
    private Unidade unidade;
    private boolean aberto;

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public int getNumeroCaixa() {
        return numeroCaixa;
    }

    public void setNumeroCaixa(int numeroCaixa) {
        this.numeroCaixa = numeroCaixa;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }
    
    public void setUnidade(int idUnidade) {
        this.unidade = new Unidade();
        this.unidade.setIdUnidade(idUnidade);
    }

    @Override
    public String toString() {
        return numeroCaixa + "";
    }
    
    
    
}

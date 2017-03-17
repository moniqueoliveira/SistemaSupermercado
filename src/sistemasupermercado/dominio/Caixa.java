
package sistemasupermercado.dominio;

public class Caixa {
    private int numeroCaixa;
    private Unidade unidade;

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
    
}


package sistemasupermercado.dominio;

import java.util.Calendar;

public class Venda {
    private int idVenda;
    private Sessao sessao;
    private boolean finalizada;
    private Calendar data;

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
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
    
    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalixada) {
        this.finalizada = finalixada;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }
    
}


package sistemasupermercado.dominio;

import java.util.Calendar;

public class Sessao {
    private int idSessao;
    private Usuario usuario;
    private Calendar inicioSessao;
    private Calendar fimSessao;

    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void setUsuario(int idUsuario) {
        this.usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
    }

    public Calendar getInicioSessao() {
        return inicioSessao;
    }

    public void setInicioSessao(Calendar inicioSessao) {
        this.inicioSessao = inicioSessao;
    }

    public Calendar getFimSessao() {
        return fimSessao;
    }

    public void setFimSessao(Calendar fimSessao) {
        this.fimSessao = fimSessao;
    }
    
}

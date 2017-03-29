
package sistemasupermercado.dominio;

public class Usuario {
    private Integer idUsuario;
    private String nome;
    private String login;
    private String senha;
    private Unidade unidade;
    private FuncaoUsuario funcaoUsuario;

    public Integer getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public FuncaoUsuario getFuncaoUsuario() {
        return funcaoUsuario;
    }

    public void setFuncaoUsuario(FuncaoUsuario funcaoUsuario) {
        this.funcaoUsuario = funcaoUsuario;
    }
    
    public void setFuncaoUsuario(int idFuncao) {
        this.funcaoUsuario = new FuncaoUsuario();
        this.funcaoUsuario.setIdFuncao(idFuncao);
    } 
    
}


package sistemasupermercado.servicos;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dao.FuncaoUsuarioDAOImpl;
import sistemasupermercado.dominio.FuncaoUsuario;
import sistemasupermercado.exceptions.DadosInvalidosException;
import sistemasupermercado.exceptions.PesquisaNulaException;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.FuncaoUsuarioDAO;

public class FuncaoUsuarioServico {
    FuncaoUsuarioDAO funcaoUsuarioDAO;
    
    public void inserir(FuncaoUsuario funcaoUsuario){
        validarDados(funcaoUsuario);
        funcaoUsuarioDAO = new FuncaoUsuarioDAOImpl();
        try {
            verificarResultado(funcaoUsuarioDAO.inserir(funcaoUsuario));
            funcaoUsuarioDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public void alterar(FuncaoUsuario funcaoUsuario) {
        validarDados(funcaoUsuario);
        funcaoUsuarioDAO = new FuncaoUsuarioDAOImpl();
        try {
            verificarResultado(funcaoUsuarioDAO.alterar(funcaoUsuario));
            funcaoUsuarioDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public void excluir(FuncaoUsuario funcaoUsuario) {
        funcaoUsuarioDAO = new FuncaoUsuarioDAOImpl();
        try {
            verificarResultado(funcaoUsuarioDAO.excluir(funcaoUsuario));
            funcaoUsuarioDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public FuncaoUsuario pesquisar(FuncaoUsuario funcaoUsuario) {
        funcaoUsuarioDAO = new FuncaoUsuarioDAOImpl();
        try {
            funcaoUsuario = funcaoUsuarioDAO.pesquisar(funcaoUsuario);
            validarPesquisa(funcaoUsuario);
            funcaoUsuarioDAO.fecharConexao();
            return funcaoUsuario;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public List<FuncaoUsuario> listar() {
        return listar("", "");
    }
    
    public List<FuncaoUsuario> listar(String pesquisaPor, String texto) {
        funcaoUsuarioDAO = new FuncaoUsuarioDAOImpl();
        try {
            List<FuncaoUsuario> funcoes = funcaoUsuarioDAO.listar(pesquisaPor, texto);
            funcaoUsuarioDAO.fecharConexao();
            return funcoes;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }

    private void validarDados(FuncaoUsuario funcaoUsuario) {
        if (funcaoUsuario.getDescricao().equals("")) 
            throw new DadosInvalidosException("O campo \"descrição\" não pode ser vazio");
    }

    private void verificarResultado(boolean result) {
        if (!result) throw new RetornoDeAlteracaoDeDadosInesperadoException();
    }

    private void validarPesquisa(FuncaoUsuario funcaoUsuario) {
        if (funcaoUsuario == null) 
            throw new PesquisaNulaException();
    }
}

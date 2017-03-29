
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
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public void alterar(FuncaoUsuario funcaoUsuario) {
        validarDados(funcaoUsuario);
        funcaoUsuarioDAO = new FuncaoUsuarioDAOImpl();
        try {
            verificarResultado(funcaoUsuarioDAO.alterar(funcaoUsuario));
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public void excluir(FuncaoUsuario funcaoUsuario) {
        funcaoUsuarioDAO = new FuncaoUsuarioDAOImpl();
        try {
            verificarResultado(funcaoUsuarioDAO.excluir(funcaoUsuario));
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public FuncaoUsuario pesquisar(FuncaoUsuario funcaoUsuario) {
        funcaoUsuarioDAO = new FuncaoUsuarioDAOImpl();
        try {
            funcaoUsuario = funcaoUsuarioDAO.pesquisar(funcaoUsuario);
            validarPesquisa(funcaoUsuario);
            return funcaoUsuario;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public List<FuncaoUsuario> listar(String pesquisaPor, String texto) {
        funcaoUsuarioDAO = new FuncaoUsuarioDAOImpl();
        try {
            return funcaoUsuarioDAO.listar(pesquisaPor, texto);
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

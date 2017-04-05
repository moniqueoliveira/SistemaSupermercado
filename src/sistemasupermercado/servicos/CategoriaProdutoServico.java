
package sistemasupermercado.servicos;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dao.CategoriaProdutoDAOImpl;
import sistemasupermercado.dominio.CategoriaProduto;
import sistemasupermercado.exceptions.DadosInvalidosException;
import sistemasupermercado.exceptions.PesquisaNulaException;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.CategoriaProdutoDAO;

public class CategoriaProdutoServico {
    CategoriaProdutoDAO categoriaProdutoDAO;
    
    public void inserir(CategoriaProduto categoriaProduto) {
        validarDados(categoriaProduto);
        categoriaProdutoDAO = new CategoriaProdutoDAOImpl();
        try {
            verificarResultado(categoriaProdutoDAO.inserir(categoriaProduto));
            categoriaProdutoDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public void alterar(CategoriaProduto categoriaProduto) {
        validarDados(categoriaProduto);
        categoriaProdutoDAO = new CategoriaProdutoDAOImpl();
        try {
            verificarResultado(categoriaProdutoDAO.alterar(categoriaProduto));
            categoriaProdutoDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public void excluir(CategoriaProduto categoriaProduto) {
        categoriaProdutoDAO = new CategoriaProdutoDAOImpl();
        try {
            verificarResultado(categoriaProdutoDAO.excluir(categoriaProduto));
            categoriaProdutoDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public CategoriaProduto pesquisar(CategoriaProduto categoriaProduto) {
        categoriaProdutoDAO = new CategoriaProdutoDAOImpl();
        try {
            categoriaProduto = categoriaProdutoDAO.pesquisar(categoriaProduto);
            verificarPesquisa(categoriaProduto); 
            categoriaProdutoDAO.fecharConexao();
            return categoriaProduto;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public List<CategoriaProduto> listar() {
        return listar("", "");
    }
    
    public List<CategoriaProduto> listar(String pesquisarPor, String filtro) {
        categoriaProdutoDAO = new CategoriaProdutoDAOImpl();
        try {
            List<CategoriaProduto> categorias = categoriaProdutoDAO.listar(pesquisarPor, filtro);
            categoriaProdutoDAO.fecharConexao();
            return categorias;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }

    private void validarDados(CategoriaProduto categoriaProduto) {
        if (categoriaProduto.getDescricao().equals(""))
            throw new DadosInvalidosException("O campo \"descrição\" não pode ser vazio.");
    }

    private void verificarResultado(boolean result) {
        if (!result)
            throw new RetornoDeAlteracaoDeDadosInesperadoException();
    }

    private void verificarPesquisa(CategoriaProduto categoriaProduto) {
        if (categoriaProduto == null) throw new PesquisaNulaException();
    }
}

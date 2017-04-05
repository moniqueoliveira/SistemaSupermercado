
package sistemasupermercado.servicos;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dao.TelefoneFornecedorDAOImpl;
import sistemasupermercado.dominio.TelefoneFornecedor;
import sistemasupermercado.exceptions.DadosInvalidosException;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.TelefoneFornecedorDAO;

public class TelefoneFornecedorServico {
    TelefoneFornecedorDAO telefoneFornecedorDAO;
    
    public void inserir(TelefoneFornecedor telefoneFornecedor) {
        validarDados(telefoneFornecedor);
        telefoneFornecedorDAO = new TelefoneFornecedorDAOImpl();
        try {
            verificarResultado(telefoneFornecedorDAO.inserir(telefoneFornecedor));
            telefoneFornecedorDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }
    
    public void alterar(TelefoneFornecedor telefoneFornecedor) {
        validarDados(telefoneFornecedor);
        telefoneFornecedorDAO = new TelefoneFornecedorDAOImpl();
        try {
            verificarResultado(telefoneFornecedorDAO.alterar(telefoneFornecedor));
            telefoneFornecedorDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }
    
    public void excluir(TelefoneFornecedor telefoneFornecedor) {
        telefoneFornecedorDAO = new TelefoneFornecedorDAOImpl();
        try {
            verificarResultado(telefoneFornecedorDAO.excluir(telefoneFornecedor));
            telefoneFornecedorDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }
    
    public List<TelefoneFornecedor> listar(int idFornecedor) {
        telefoneFornecedorDAO = new TelefoneFornecedorDAOImpl();
        try {
            List<TelefoneFornecedor> telefones = telefoneFornecedorDAO.listar(idFornecedor);
            telefoneFornecedorDAO.fecharConexao();
            return telefones;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }

    private void validarDados(TelefoneFornecedor telefoneFornecedor) {
        if(!telefoneFornecedor.validarTelefone()) {
            throw new DadosInvalidosException("O campo telefone é inválido ou não foi preenchido.");
        }
    }

    private void verificarResultado(boolean resultado) {
        if (!resultado) {
            throw new RetornoDeAlteracaoDeDadosInesperadoException();
        }
    }
}


package sistemasupermercado.servicos;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dao.TelefoneFornecedorDAOImpl;
import sistemasupermercado.dominio.TelefoneFornecedor;
import sistemasupermercado.exceptions.DadosInvalidosException;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.TelefoneFornecedorDAO;

public class TelefoneFornecedorServico {
    TelefoneFornecedorDAO TelefoneFornecedorDAO;
    
    public void inserir(TelefoneFornecedor telefoneFornecedor) {
        validarDados(telefoneFornecedor);
        TelefoneFornecedorDAO = new TelefoneFornecedorDAOImpl();
        try {
            verificarResultado(TelefoneFornecedorDAO.inserir(telefoneFornecedor));
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }
    
    public void alterar(TelefoneFornecedor telefoneFornecedor) {
        validarDados(telefoneFornecedor);
        TelefoneFornecedorDAO = new TelefoneFornecedorDAOImpl();
        try {
            verificarResultado(TelefoneFornecedorDAO.alterar(telefoneFornecedor));
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }
    
    public void excluir(TelefoneFornecedor telefoneFornecedor) {
        TelefoneFornecedorDAO = new TelefoneFornecedorDAOImpl();
        try {
            verificarResultado(TelefoneFornecedorDAO.excluir(telefoneFornecedor));
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }
    
    public List<TelefoneFornecedor> listar(int idFornecedor) {
        TelefoneFornecedorDAO = new TelefoneFornecedorDAOImpl();
        try {
            return TelefoneFornecedorDAO.listar(idFornecedor);
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

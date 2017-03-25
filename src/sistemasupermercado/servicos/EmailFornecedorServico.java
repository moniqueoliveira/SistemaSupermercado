
package sistemasupermercado.servicos;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dao.EmailFornecedorDAOImpl;
import sistemasupermercado.dominio.EmailFornecedor;
import sistemasupermercado.exceptions.DadosInvalidosException;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.EmailFornecedorDAO;

public class EmailFornecedorServico {
    EmailFornecedorDAO emailFornecedorDAO;
    
    public void inserir(EmailFornecedor emailFornecedor) {
        validarDados(emailFornecedor);
        emailFornecedorDAO = new EmailFornecedorDAOImpl();
        try {
            verificarResultado(emailFornecedorDAO.inserir(emailFornecedor));
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }
    
    public void alterar(EmailFornecedor emailFornecedor) {
        validarDados(emailFornecedor);
        emailFornecedorDAO = new EmailFornecedorDAOImpl();
        try {
            verificarResultado(emailFornecedorDAO.alterar(emailFornecedor));
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }
    
    public void excluir(EmailFornecedor emailFornecedor) {
        emailFornecedorDAO = new EmailFornecedorDAOImpl();
        try {
            verificarResultado(emailFornecedorDAO.excluir(emailFornecedor));
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }
    
    public List<EmailFornecedor> listar(int idFornecedor) {
        emailFornecedorDAO = new EmailFornecedorDAOImpl();
        try {
            return emailFornecedorDAO.listar(idFornecedor);
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }

    private void validarDados(EmailFornecedor emailFornecedor) {
        if(!emailFornecedor.validarEmail()) {
            throw new DadosInvalidosException("O campo e-mail é inválido ou não foi preenchido.");
        }
    }

    private void verificarResultado(boolean resultado) {
        if (!resultado) {
            throw new RetornoDeAlteracaoDeDadosInesperadoException();
        }
    }
}

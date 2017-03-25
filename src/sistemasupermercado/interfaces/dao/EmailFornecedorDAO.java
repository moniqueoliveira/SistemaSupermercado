
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.EmailFornecedor;

public interface EmailFornecedorDAO {
    
    public boolean inserir(EmailFornecedor obj) throws SQLException;
    
    public boolean alterar(EmailFornecedor obj) throws SQLException;
    
    public boolean excluir(EmailFornecedor obj) throws SQLException;
    
    public List<EmailFornecedor> listar(int idFornecedor) throws SQLException;
    
}

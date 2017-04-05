
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.TelefoneFornecedor;

public interface TelefoneFornecedorDAO {
    
    public void fecharConexao() throws SQLException;
    
    public boolean inserir(TelefoneFornecedor obj) throws SQLException;
    
    public boolean alterar(TelefoneFornecedor obj) throws SQLException;
    
    public boolean excluir(TelefoneFornecedor obj) throws SQLException;
    
    public List<TelefoneFornecedor> listar(int idFornecedor) throws SQLException;
    
}

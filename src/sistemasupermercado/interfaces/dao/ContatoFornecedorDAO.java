
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import sistemasupermercado.dominio.ContatoFornecedor;

public interface ContatoFornecedorDAO {

    public boolean alterar(ContatoFornecedor obj) throws SQLException;

    public boolean excluir(ContatoFornecedor obj) throws SQLException;

    public boolean inserir(ContatoFornecedor obj) throws SQLException;

    public ContatoFornecedor pesquisar(ContatoFornecedor obj) throws SQLException;
    
}

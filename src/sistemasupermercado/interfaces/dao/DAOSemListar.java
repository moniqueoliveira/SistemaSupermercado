
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;

public interface DAOSemListar<T> {
    
    public boolean inserir(T obj) throws SQLException;
    
    public boolean alterar(T obj) throws SQLException;
    
    public boolean excluir(T obj) throws SQLException;
    
    public T pesquisar(T obj) throws SQLException;
    
}

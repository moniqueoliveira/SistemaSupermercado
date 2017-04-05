
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;

public interface DAO<T> {
    
    public void fecharConexao() throws SQLException;
    
    public boolean inserir(T obj) throws SQLException;
    
    public boolean alterar(T obj) throws SQLException;
    
    public boolean excluir(T obj) throws SQLException;
    
    public T pesquisar(T obj) throws SQLException;
    
}

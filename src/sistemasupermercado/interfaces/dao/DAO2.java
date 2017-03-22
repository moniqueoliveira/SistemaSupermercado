
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;

interface DAO2<T> {
    
    public boolean inserir(T obj) throws SQLException;
    
    public boolean alterar(T obj) throws SQLException;
    
    public boolean excluir(T obj) throws SQLException;
    
    public T pesquisar(T obj) throws SQLException;
    
    public List<T> listar(String pesquisaPor, String texto) throws SQLException;
    
}
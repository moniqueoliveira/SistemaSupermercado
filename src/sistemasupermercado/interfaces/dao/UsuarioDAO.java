
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.Usuario;

public interface UsuarioDAO extends DAOSemListar<Usuario> {

    public List<Usuario> listar(String pesquisaPor, String texto) throws SQLException;
    
}


package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.Sessao;

public interface SessaoDAO {

    public boolean inserir(Sessao obj) throws SQLException;

    public boolean alterar(Sessao obj) throws SQLException;

    public List<Sessao> listar(String filtro) throws SQLException;

    public Sessao pesquisar(Sessao obj) throws SQLException;
    
}

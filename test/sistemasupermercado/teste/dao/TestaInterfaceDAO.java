
package sistemasupermercado.teste.dao;

import java.sql.SQLException;
import sistemasupermercado.dao.CaixaDAOImpl;
import sistemasupermercado.dao.UnidadeDAOImpl;
import sistemasupermercado.dao.UnidadeDAOImpl;
import sistemasupermercado.dominio.Caixa;
import sistemasupermercado.dominio.Unidade;
import sistemasupermercado.interfaces.dao.CaixaDAO;
import sistemasupermercado.interfaces.dao.UnidadeDAO;

public class TestaInterfaceDAO {
    public static void main(String[] args) throws SQLException {
        Unidade unidade = new Unidade();
        Caixa c = new Caixa();
        
        UnidadeDAO dao = new UnidadeDAOImpl();
        
        unidade.setDescricao("Unidade Santa Catarina");
        dao.inserir(unidade);
    }
}

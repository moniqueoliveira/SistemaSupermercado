
package sistemasupermercado.teste.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import sistemasupermercado.dao.EntradaProdutoDAOImpl;
import sistemasupermercado.dao.EntradaProdutoDAOImpl;
import sistemasupermercado.dominio.EntradaProduto;
import sistemasupermercado.interfaces.dao.EntradaProdutoDAO;

public class TestaPesquisaEntradaProduto {
    public static void main(String[] args) throws SQLException {
        EntradaProduto entradaProduto = new EntradaProduto();
        EntradaProdutoDAO dao = new EntradaProdutoDAOImpl();
        
        entradaProduto.setIdEntrada(11);
        entradaProduto = dao.pesquisar(entradaProduto);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println(sdf.format(entradaProduto.getData().getTime()));
    }
}

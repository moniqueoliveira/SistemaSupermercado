
package sistemasupermercado.teste.dao;

import java.sql.SQLException;
import sistemasupermercado.dao.ItemVendaDAOImpl;
import sistemasupermercado.dominio.ItemVenda;
import sistemasupermercado.interfaces.dao.ItemVendaDAO;

public class TestaItemVendaDAO {
    public static void main(String[] args) throws SQLException {
        ItemVenda itemVenda = new ItemVenda();
        ItemVendaDAO dao = new ItemVendaDAOImpl();
        
        itemVenda.setProduto(1);
        itemVenda.setVenda(1);
        
        itemVenda = dao.pesquisar(itemVenda);
        System.out.println(itemVenda.getQuantidade());
    }
}

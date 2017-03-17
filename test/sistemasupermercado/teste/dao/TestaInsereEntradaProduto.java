
package sistemasupermercado.teste.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import sistemasupermercado.dao.EntradaProdutoDAOImpl;
import sistemasupermercado.dao.EntradaProdutoDAOImpl;
import sistemasupermercado.dominio.EntradaProduto;
import sistemasupermercado.interfaces.dao.EntradaProdutoDAO;

public class TestaInsereEntradaProduto {
    public static void main(String[] args) throws SQLException {
        EntradaProduto entradaProduto = new EntradaProduto();
        EntradaProdutoDAO dao = new EntradaProdutoDAOImpl();
        
        Calendar data = Calendar.getInstance();
        data.setTimeInMillis(System.currentTimeMillis());
        entradaProduto.setData(data);
        entradaProduto.setFornecedor(1);
        entradaProduto.setProduto(1);
        entradaProduto.setQuantidade(1);
        entradaProduto.setValorUnitario(new BigDecimal(2.3));
        entradaProduto.setSessao(1);
        
        dao.inserir(entradaProduto);
    }
}

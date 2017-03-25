
package sistemasupermercado.teste.dao;

import java.sql.SQLException;
import sistemasupermercado.dao.FornecedorDAOImpl;
import sistemasupermercado.dominio.Fornecedor;
import sistemasupermercado.interfaces.dao.FornecedorDAO;

public class TestaFornecedorDAO {
    public static void main(String[] args) throws SQLException {
        FornecedorDAO fornecedorDAO = new FornecedorDAOImpl();
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setAtivo(true);
        fornecedor.setCnpj("abc");
        fornecedor.setNomeFantasia("abc");
        fornecedor.setRazaoSocial("abc");
        System.out.println(fornecedorDAO.inserir(fornecedor));
        System.out.println(fornecedorDAO.obterUltimoID());
    }
}


package sistemasupermercado.teste.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import sistemasupermercado.dao.SessaoDAOImpl;
import sistemasupermercado.dominio.Sessao;
import sistemasupermercado.interfaces.dao.SessaoDAO;

public class TestaSessaoDAO {
    public static void main(String[] args) throws SQLException {
        Sessao sessao = new Sessao();
        SessaoDAO dao = new SessaoDAOImpl();
        
        sessao.setIdSessao(1);
        sessao = dao.pesquisar(sessao);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println(sdf.format(sessao.getInicioSessao().getTime()));
        System.out.println(sessao.getFimSessao());
    }
}

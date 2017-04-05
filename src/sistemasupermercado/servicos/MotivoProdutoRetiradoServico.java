
package sistemasupermercado.servicos;

import sistemasupermercado.exceptions.PesquisaNulaException;
import sistemasupermercado.exceptions.DadosInvalidosException;
import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dao.MotivoProdutoRetiradoDAOImpl;
import sistemasupermercado.dominio.MotivoProdutoRetirado;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.MotivoProdutoRetiradoDAO;

public class MotivoProdutoRetiradoServico {
    MotivoProdutoRetiradoDAO motivoProdutoRetiradoDAO;
    
    public void inserir(MotivoProdutoRetirado motivoProdutoRetirado) {
        validarDescricao(motivoProdutoRetirado);
        motivoProdutoRetiradoDAO = new MotivoProdutoRetiradoDAOImpl();
        try {
            verificarResultado(motivoProdutoRetiradoDAO.inserir(motivoProdutoRetirado));
            motivoProdutoRetiradoDAO.fecharConexao();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void alterar(MotivoProdutoRetirado motivoProdutoRetirado) {
        validarDescricao(motivoProdutoRetirado);
        motivoProdutoRetiradoDAO = new MotivoProdutoRetiradoDAOImpl();
        try {
            verificarResultado(motivoProdutoRetiradoDAO.alterar(motivoProdutoRetirado));
            motivoProdutoRetiradoDAO.fecharConexao();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void excluir(MotivoProdutoRetirado motivoProdutoRetirado) {
        motivoProdutoRetiradoDAO = new MotivoProdutoRetiradoDAOImpl();
        try {
            verificarResultado(motivoProdutoRetiradoDAO.excluir(motivoProdutoRetirado));
            motivoProdutoRetiradoDAO.fecharConexao();
        } catch (SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }
    
    public MotivoProdutoRetirado pesquisar(MotivoProdutoRetirado motivoProdutoRetirado) {
        motivoProdutoRetiradoDAO = new MotivoProdutoRetiradoDAOImpl();
        try {
            motivoProdutoRetirado = motivoProdutoRetiradoDAO.pesquisar(motivoProdutoRetirado);
            validarPesquisa(motivoProdutoRetirado);
            motivoProdutoRetiradoDAO.fecharConexao();
            return motivoProdutoRetirado;
        } catch (SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }
        
    public List<MotivoProdutoRetirado> listar(String pesquisaPor, String texto) {
        motivoProdutoRetiradoDAO = new MotivoProdutoRetiradoDAOImpl();
        try {
            List<MotivoProdutoRetirado> motivos = motivoProdutoRetiradoDAO.listar(pesquisaPor, texto);
            motivoProdutoRetiradoDAO.fecharConexao();
            return motivos;
        } catch (SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
        
    }
    
    private void validarDescricao(MotivoProdutoRetirado motivoProdutoRetirado) {
        if (motivoProdutoRetirado.getDescricao().equals("")) {
            throw new DadosInvalidosException("O campo \"descrição\" não pode ser vazio.");
        }
    }

    private void validarPesquisa(MotivoProdutoRetirado motivoProdutoRetirado) {
        if (motivoProdutoRetirado == null) {
            throw new PesquisaNulaException("Não há motivos cadastrados com o ID informado.");
        }
    }

    private void verificarResultado(boolean resultado) {
        if (!resultado) throw new RetornoDeAlteracaoDeDadosInesperadoException();
    }
    
}
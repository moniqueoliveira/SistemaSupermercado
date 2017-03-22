
package sistemasupermercado.servicos;

import sistemasupermercado.exceptions.PesquisaNulaException;
import sistemasupermercado.exceptions.CampoRequeridoVazioException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemasupermercado.dao.MotivoProdutoRetiradoDAOImpl;
import sistemasupermercado.dominio.MotivoProdutoRetirado;
import sistemasupermercado.interfaces.dao.MotivoProdutoRetiradoDAO;

public class MotivoProdutoRetiradoServico {
    MotivoProdutoRetiradoDAO motivoProdutoRetiradoDAO;
    
    public boolean inserir(MotivoProdutoRetirado motivoProdutoRetirado) {
        validarDescricao(motivoProdutoRetirado);
        motivoProdutoRetiradoDAO = new MotivoProdutoRetiradoDAOImpl();
        try {
            return motivoProdutoRetiradoDAO.inserir(motivoProdutoRetirado);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public boolean alterar(MotivoProdutoRetirado motivoProdutoRetirado) {
        validarDescricao(motivoProdutoRetirado);
        motivoProdutoRetiradoDAO = new MotivoProdutoRetiradoDAOImpl();
        try {
            return motivoProdutoRetiradoDAO.alterar(motivoProdutoRetirado);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public boolean excluir(MotivoProdutoRetirado motivoProdutoRetirado) {
        motivoProdutoRetiradoDAO = new MotivoProdutoRetiradoDAOImpl();
        try {
            return motivoProdutoRetiradoDAO.excluir(motivoProdutoRetirado);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public MotivoProdutoRetirado pesquisar(MotivoProdutoRetirado motivoProdutoRetirado) {
        motivoProdutoRetiradoDAO = new MotivoProdutoRetiradoDAOImpl();
        try {
            motivoProdutoRetirado = motivoProdutoRetiradoDAO.pesquisar(motivoProdutoRetirado);
            validarPesquisa(motivoProdutoRetirado);
            return motivoProdutoRetirado;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
        
    public List<MotivoProdutoRetirado> listar(String pesquisaPor, String texto) {
        
        motivoProdutoRetiradoDAO = new MotivoProdutoRetiradoDAOImpl();
        try {
            return motivoProdutoRetiradoDAO.listar(pesquisaPor, texto);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    private void validarDescricao(MotivoProdutoRetirado motivoProdutoRetirado) {
        if (motivoProdutoRetirado.getDescricao().equals("")) {
            throw new CampoRequeridoVazioException("O campo \"descrição\" não pode ser vazio.");
        }
    }

    private void validarPesquisa(MotivoProdutoRetirado motivoProdutoRetirado) {
        if (motivoProdutoRetirado == null) {
            throw new PesquisaNulaException("Não há motivos cadastrados com o ID informado.");
        }
    }
    
}

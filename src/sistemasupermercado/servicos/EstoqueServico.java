
package sistemasupermercado.servicos;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dao.EstoqueDAOImpl;
import sistemasupermercado.dominio.Estoque;
import sistemasupermercado.exceptions.DadosInvalidosException;
import sistemasupermercado.exceptions.PesquisaNulaException;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.EstoqueDAO;

public class EstoqueServico {
    EstoqueDAO estoqueDAO;
    
    public void inserir(Estoque estoque) {
        validarDados(estoque);
        estoqueDAO = new EstoqueDAOImpl();
        try {
            verificarResultado(estoqueDAO.inserir(estoque));
            estoqueDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public void alterar(Estoque estoque) {
        validarDados(estoque);
        estoqueDAO = new EstoqueDAOImpl();
        try {
            verificarResultado(estoqueDAO.alterar(estoque));
            estoqueDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public Estoque pesquisar(Estoque estoque) {
        estoqueDAO = new EstoqueDAOImpl();
        try {
            estoque = estoqueDAO.pesquisar(estoque);
            estoque.setProduto(new ProdutoServico().pesquisar(estoque.getProduto()));
            estoqueDAO.fecharConexao();
            return estoque;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao pesquisar o item de estoque): " + ex.getMessage());
        }
    }
    
    public List<Estoque> listar(String pesquisarPor, String filtro, int idUnidade) {
        estoqueDAO = new EstoqueDAOImpl();
        try {
            List<Estoque> estoques = estoqueDAO.listar(pesquisarPor, filtro, idUnidade);
            for (Estoque estoque : estoques) {
                estoque.setProduto(new ProdutoServico().pesquisar(estoque.getProduto()));
            }
            estoqueDAO.fecharConexao();
            return estoques;
        } catch (SQLException ex){
            throw new RuntimeException("SQLException (Erro ao listar o conteúdo do estoque): " + ex.getMessage());
        }
    }

    private void validarDados(Estoque estoque) {
        StringBuilder mensagem = new StringBuilder();
        
        if (estoque.getProduto() == null) mensagem.append("Produto\n");
        if (estoque.getUnidade() == null) mensagem.append("Unidade\n");
        
        if (mensagem.length() > 1)
            throw new DadosInvalidosException("Os seguintes dados são inválidos ou não foram preenchidos:\n" + mensagem);
    }

    private void verificarResultado(boolean result) {
        if (!result)
            throw new RetornoDeAlteracaoDeDadosInesperadoException("Ocorreu um erro inesperado ao atualizar os dados"
                    + "do estoque.");
    }

    private void validarPesquisa(Estoque estoque) {
        if (estoque == null)
            throw new PesquisaNulaException("A pesquisa dos dados do estoque não retornou resultados.");
    }
}

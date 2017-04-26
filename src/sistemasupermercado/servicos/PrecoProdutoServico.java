
package sistemasupermercado.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import sistemasupermercado.dao.PrecoProdutoDAOImpl;
import sistemasupermercado.dominio.PrecoProduto;
import sistemasupermercado.dominio.Produto;
import sistemasupermercado.exceptions.PesquisaNulaException;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.PrecoProdutoDAO;

public class PrecoProdutoServico {
    PrecoProdutoDAO precoProdutoDAO;
    
    public void inserir(PrecoProduto precoProduto) {
        precoProduto.setData(definirData());
        precoProdutoDAO = new PrecoProdutoDAOImpl();
        try {
            verificarResultado(precoProdutoDAO.inserir(precoProduto));
            precoProdutoDAO.fecharConexao();
        } catch (SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public PrecoProduto pesquisarPrecoAtual(PrecoProduto precoProduto) {
        precoProdutoDAO = new PrecoProdutoDAOImpl();
        try {
            List<PrecoProduto> precos = precoProdutoDAO.listar(precoProduto.getProduto().getIdProduto(), 
                    precoProduto.getUnidade().getIdUnidade());
            
            precoProduto = obterPrecoAtual(precos);
           
            precoProdutoDAO.fecharConexao();
            return precoProduto;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    private PrecoProduto pesquisarPrecoAtual(int idProduto, int idUnidade) {
        PrecoProduto precoProduto = new PrecoProduto();
        precoProduto.setProduto(idProduto);
        precoProduto.setUnidade(idUnidade);
        return pesquisarPrecoAtual(precoProduto);
    }
    
    public List<PrecoProduto> obterPrecos(List<Produto> produtos, int idUnidade) {
        List<PrecoProduto> precos = new ArrayList<>();
        for (int i = 0; i < produtos.size(); i++) {
            precos.add(pesquisarPrecoAtual(produtos.get(i).getIdProduto(), idUnidade));
        }
        return precos;
    }

    private Calendar definirData() {
        Calendar data = Calendar.getInstance();
        data.setTimeInMillis(System.currentTimeMillis());
        return data;
    }
    private void verificarResultado(boolean result) {
        if (!result) throw new RetornoDeAlteracaoDeDadosInesperadoException();
    }

    private void validarPesquisa(PrecoProduto precoProduto) {
        if (precoProduto == null) throw new PesquisaNulaException();
    }

    private PrecoProduto obterPrecoAtual(List<PrecoProduto> precos) {
        if (precos.isEmpty()) return null;
        PrecoProduto precoProduto = precos.get(0);
        for (int i = 1; i < precos.size(); i++) {
            if (precos.get(i).getData().after(precoProduto.getData()))
                precoProduto = precos.get(i);
        }
        precoProduto.setProduto(new ProdutoServico().pesquisar(precoProduto.getProduto()));
        return precoProduto;
    }
}

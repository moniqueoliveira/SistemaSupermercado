
package sistemasupermercado.servicos;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dao.ProdutoDAOImpl;
import sistemasupermercado.dominio.Produto;
import sistemasupermercado.exceptions.DadosInvalidosException;
import sistemasupermercado.exceptions.PesquisaNulaException;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.ProdutoDAO;

public class ProdutoServico {
    ProdutoDAO produtoDAO;
    
    public void inserir(Produto produto) {
        validarDados(produto);
        validarCodigoDeBarras(produto);
        produtoDAO = new ProdutoDAOImpl();
        try {
            verificarResultado(produtoDAO.inserir(produto));
            produtoDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("ID já cadastrado no sistema!\n");
        }
    }
    
    public void alterar(Produto produto) {
        validarDados(produto);
        produtoDAO = new ProdutoDAOImpl();
        try {
            verificarResultado(produtoDAO.alterar(produto));
            produtoDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public void excluir(Produto produto) {
        produtoDAO = new ProdutoDAOImpl();
        try {
            verificarResultado(produtoDAO.excluir(produto));
            produtoDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public Produto pesquisar(Produto produto) {
        produtoDAO = new ProdutoDAOImpl();
        try {
            produto = produtoDAO.pesquisar(produto);
            validarPesquisa(produto);
            
            produto.setCategoria(new CategoriaProdutoServico().pesquisar(produto.getCategoria()));
           
            produtoDAO.fecharConexao();
            return produto;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public List<Produto> listar() {
        return listar("", "");
    }
    
    public List<Produto> listar(String pesquisarPor, String texto) {
        produtoDAO = new ProdutoDAOImpl();
        try {
            List<Produto> produtos = produtoDAO.listar(pesquisarPor, texto);
            
            CategoriaProdutoServico categoriaProdutoServico = new CategoriaProdutoServico();
            for(int i = 0; i < produtos.size(); i++) {
                // Define a descrição da função do usuário. Ao listar os usuários somente o ID da função é definido.
                produtos.get(i).setCategoria(categoriaProdutoServico.pesquisar(produtos.get(i).getCategoria()));
            }
            
            produtoDAO.fecharConexao();
            return produtos;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }

    private void validarDados(Produto produto) {
        StringBuilder mensagem = new StringBuilder();
        if (produto.getCategoria() == null) mensagem.append("Categoria\n");
        if (produto.getCodigoDeBarras().equals("")) mensagem.append("Código de barras\n");
        if (produto.getDescricao().equals("")) mensagem.append("Descricão\n");
        if (produto.getDescricaoReduzida().equals("")) mensagem.append("Descricão reduzida\n");
        
        
        if (mensagem.length() > 1)
            throw new DadosInvalidosException("O(s) seguinte(s) dado(s) estão sem preenchimento ou foram preenchidos"
                    + "incorretamente:\n" + mensagem);
    }

    private void verificarResultado(boolean result) {
        if (!result) throw new RetornoDeAlteracaoDeDadosInesperadoException();
    }

    private void validarPesquisa(Produto produto) {
        if (produto == null) throw new PesquisaNulaException();
    }

    private void validarCodigoDeBarras(Produto produto) {
        List<Produto> produtos = listar();
        for (Produto produtoLista : produtos) {
            if (produtoLista.getCodigoDeBarras().equals(produto.getCodigoDeBarras()))
                throw new DadosInvalidosException("O código de barras digitado já está cadastrado!");
        }
    }

    public Produto pesquisarProdutoEstocavel(Produto produto) {
        produto = pesquisar(produto);
        if (!produto.isEstocavel()) 
            throw new DadosInvalidosException("O produto pesquisado não é estocável.");
        return produto;
    }
}

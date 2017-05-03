
package sistemasupermercado.servicos;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import sistemasupermercado.dao.EntradaProdutoDAOImpl;
import sistemasupermercado.dominio.EntradaProduto;
import sistemasupermercado.dominio.Estoque;
import sistemasupermercado.exceptions.DadosInvalidosException;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.EntradaProdutoDAO;

public class EntradaProdutoServico {
    EntradaProdutoDAO entradaProdutoDAO;
    
    public void inserir(EntradaProduto entradaProduto) {
        validarDados(entradaProduto);
        entradaProdutoDAO = new EntradaProdutoDAOImpl();
        
        entradaProduto.setData(Calendar.getInstance());
        entradaProduto.getData().setTimeInMillis(System.currentTimeMillis());
        
        try {
            verificarResultado(entradaProdutoDAO.inserir(entradaProduto));
            entradaProdutoDAO.fecharConexao();
            atualizarEstoque(entradaProduto);
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public EntradaProduto pesquisar(EntradaProduto entradaProduto) {
        entradaProdutoDAO = new EntradaProdutoDAOImpl();
        try {
            entradaProduto = entradaProdutoDAO.pesquisar(entradaProduto);
            entradaProduto.setProduto(new ProdutoServico().pesquisar(entradaProduto.getProduto()));
            entradaProduto.setFornecedor(new FornecedorServico().pesquisar(entradaProduto.getFornecedor()));
            entradaProduto.setSessao(new SessaoServico().pesquisar(entradaProduto.getSessao()));
            entradaProdutoDAO.fecharConexao();
            return entradaProduto;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao pesquisar o item): " + ex.getMessage());
        }
    }
    
    public List<EntradaProduto> listar(String pesquisarPor, String texto, int idUnidade) {
        entradaProdutoDAO = new EntradaProdutoDAOImpl();
        try {
            List<EntradaProduto> entradas = entradaProdutoDAO.listar(pesquisarPor, texto, idUnidade);
            for (EntradaProduto entradaProduto : entradas) {
                entradaProduto.setProduto(new ProdutoServico().pesquisar(entradaProduto.getProduto()));
                entradaProduto.setFornecedor(new FornecedorServico().pesquisar(entradaProduto.getFornecedor()));
                entradaProduto.setSessao(new SessaoServico().pesquisar(entradaProduto.getSessao()));
            }
            entradaProdutoDAO.fecharConexao();
            return entradas;
        } catch (SQLException ex){
            throw new RuntimeException("SQLException (Erro ao listar os registros de entradas de produtos):\n" + ex.getMessage());
        }
    }

    private void validarQuantidade(EntradaProduto entradaProduto) {
        if (!entradaProduto.getProduto().isVendaFracionada() && 
                entradaProduto.getQuantidade().subtract(new BigDecimal(entradaProduto.getQuantidade().intValue())).doubleValue() != 0.00) {
            throw new DadosInvalidosException("O produto não pode ser estocado em quantidades fracionadas!");
        }
    }

    private void verificarResultado(boolean result) {
        if (!result)
            throw new RetornoDeAlteracaoDeDadosInesperadoException();
    }

    private void validarDados(EntradaProduto entradaProduto) {
        StringBuilder mensagem = new StringBuilder();
        
        if (entradaProduto.getFornecedor() == null) mensagem.append("Fornecedor\n");
        if (entradaProduto.getProduto() == null) mensagem.append("Produto\n");
        if (entradaProduto.getQuantidade().compareTo(BigDecimal.ZERO) <= 0) mensagem.append("Quantidade (deve ser maior do que zero)");
        if (entradaProduto.getValorUnitario().compareTo(BigDecimal.ZERO) <= 0) mensagem.append("Valor unitário (deve ser maior do que zero)");
        
        if (mensagem.length() > 1)
            throw new DadosInvalidosException("Os seguintes dados estão sem preenchimento ou foram preenchidos "
                    + "incorretamente:\n" + mensagem);
        
        validarQuantidade(entradaProduto);
    }

    private void atualizarEstoque(EntradaProduto entradaProduto) {
        Estoque estoque = new Estoque();
        EstoqueServico estoqueServico = new EstoqueServico();
        
        estoque.setProduto(entradaProduto.getProduto());
        estoque.setUnidade(entradaProduto.getSessao().getUsuario().getUnidade());
        
        Estoque estoqueAuxiliar = estoqueServico.pesquisar(estoque);
        
        if (estoqueAuxiliar == null) {
            estoque.setQuantidade(entradaProduto.getQuantidade());
            estoque.setValorTotal(entradaProduto.getValorUnitario().multiply(entradaProduto.getQuantidade()));

            estoqueServico.inserir(estoque);
        } else {
            estoque.setQuantidade(estoqueAuxiliar.getQuantidade().add(entradaProduto.getQuantidade()));
            
            BigDecimal valorTotalEntrada = entradaProduto.getValorUnitario().multiply(entradaProduto.getQuantidade());
            estoque.setValorTotal(estoqueAuxiliar.getValorTotal().add(valorTotalEntrada));
            
            estoqueServico.alterar(estoque);
        }
    }
}

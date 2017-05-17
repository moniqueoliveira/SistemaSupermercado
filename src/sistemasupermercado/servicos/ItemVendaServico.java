/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasupermercado.servicos;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dao.ItemVendaDAOImpl;
import sistemasupermercado.dominio.Estoque;
import sistemasupermercado.dominio.ItemVenda;
import sistemasupermercado.dominio.Venda;
import sistemasupermercado.exceptions.DadosInvalidosException;
import sistemasupermercado.exceptions.EstoqueInsuficienteException;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.ItemVendaDAO;

public class ItemVendaServico {
    ItemVendaDAO itemVendaDAO;
    
    public void inserir(ItemVenda itemVenda) {
        validarDados(itemVenda);
        itemVendaDAO = new ItemVendaDAOImpl();
        try {
            verificarResultado(itemVendaDAO.inserir(itemVenda));
            itemVendaDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao adicionar o item):\n" + ex.getMessage());
        }
    }
    
    public void alterar(ItemVenda itemVenda) {
        validarDados(itemVenda);
        itemVendaDAO = new ItemVendaDAOImpl();
        try {
            verificarResultado(itemVendaDAO.alterar(itemVenda));
            itemVendaDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao adicionar o item):\n" + ex.getMessage());
        }
    }
    
    public List<ItemVenda> listar(Venda venda) {
        itemVendaDAO = new ItemVendaDAOImpl();
        try {
            List<ItemVenda> itens = itemVendaDAO.listar(venda.getIdVenda());
            itemVendaDAO.fecharConexao();
            for (ItemVenda item : itens) {
                item.setProduto(new ProdutoServico().pesquisar(item.getProduto()));
                item.setVenda(venda);
            }
            return itens;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao adicionar o item):\n" + ex.getMessage());
        }
    }
    
    public void excluirItens(int idVenda) {
        try {
            verificarResultado(itemVendaDAO.excluirItens(idVenda));
            itemVendaDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao excluir os itens):\n" + ex.getMessage());
        }
    }
    
    private void validarQuantidade(ItemVenda itemVenda) {
        if (!itemVenda.getProduto().isVendaFracionada() && 
                itemVenda.getQuantidade().subtract(new BigDecimal(itemVenda.getQuantidade().intValue())).doubleValue() != 0.00) {
            throw new DadosInvalidosException("O produto não é estocado em quantidades fracionadas!");
        }
    }

    private void verificarEstoque(ItemVenda itemVenda) {
        Estoque estoque = new Estoque();
        EstoqueServico estoqueServico = new EstoqueServico();
        
        estoque.setProduto(itemVenda.getProduto());
        estoque.setUnidade(itemVenda.getVenda().getSessao().getUsuario().getUnidade());
        
        estoque = estoqueServico.pesquisar(estoque);
        
        if (estoque == null) {
            throw new EstoqueInsuficienteException("Não há unidades do produto selecionado em estoque.");
        } else if (itemVenda.getQuantidade().compareTo(estoque.getQuantidade()) > 0) {
            throw new EstoqueInsuficienteException("Há apenas " + estoque.getQuantidade() + " unidades/quilos do produto.");
        }
    }

    private void validarDados(ItemVenda itemVenda) {
        validarQuantidade(itemVenda);
        if (itemVenda.getProduto().isEstocavel()) verificarEstoque(itemVenda);
    }
    
    private void verificarResultado(boolean result) {
        if (!result)
            throw new RetornoDeAlteracaoDeDadosInesperadoException();
    }
}

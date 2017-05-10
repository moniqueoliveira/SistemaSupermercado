/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasupermercado.servicos;

import java.sql.SQLException;
import sistemasupermercado.dao.PagamentoVendaDAOImpl;
import sistemasupermercado.dominio.PagamentoVenda;
import sistemasupermercado.exceptions.PesquisaNulaException;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.PagamentoVendaDAO;

public class PagamentoServico {
    PagamentoVendaDAO pagamentoVendaDAO;
    
    public void inserir(PagamentoVenda pagamentoVenda) {
        pagamentoVendaDAO = new PagamentoVendaDAOImpl();
        try {
            verificarResultado(pagamentoVendaDAO.inserir(pagamentoVenda));
            pagamentoVendaDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao inserir o pagamento):\n" + ex.getMessage());
        }
    }
    
    public PagamentoVenda pesquisar(PagamentoVenda pagamentoVenda) {
        pagamentoVendaDAO = new PagamentoVendaDAOImpl();
        try {
            pagamentoVenda = pagamentoVendaDAO.pesquisar(pagamentoVenda);
            validarPesquisa(pagamentoVenda);
            pagamentoVendaDAO.fecharConexao();
            return pagamentoVenda;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao inserir o pagamento):\n" + ex.getMessage());
        }
    }
    
    private void verificarResultado(boolean result) {
        if (!result)
            throw new RetornoDeAlteracaoDeDadosInesperadoException("Ocorreu um erro inesperado ao atualizar os dados"
                    + "do pagamento.");
    }

    private void validarPesquisa(PagamentoVenda pagamentoVenda) {
        if (pagamentoVenda == null) throw new PesquisaNulaException();
    }
}


package sistemasupermercado.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemasupermercado.dao.CaixaDAOImpl;
import sistemasupermercado.dominio.Caixa;
import sistemasupermercado.dominio.Unidade;
import sistemasupermercado.exceptions.PesquisaNulaException;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.CaixaDAO;

public class CaixaServico {
    CaixaDAO caixaDAO;
    
    private void inserir(Caixa caixa) {
        caixaDAO = new CaixaDAOImpl();
        try {
            verificarResultado(caixaDAO.inserir(caixa));
            caixaDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao inserir caixa): " + ex.getMessage());
        }
    }
    
    public void alterar(Caixa caixa) {
        caixaDAO = new CaixaDAOImpl();
        try {
            verificarResultado(caixaDAO.alterar(caixa));
            caixaDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao alterar caixa): " + ex.getMessage());
        }
    }
    
    public void excluirUltimoCaixa(Unidade unidade) {
        Caixa caixa = new Caixa();
        caixa.setUnidade(unidade);
        caixa.setNumeroCaixa(obterUltimoCaixaInserido(unidade));
        
        caixaDAO = new CaixaDAOImpl();
        try {
            verificarResultado(caixaDAO.excluir(caixa));
            caixaDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao excluir caixa): " + ex.getMessage());
        }
    }
    
    public void adicionarCaixas(int quantidade, Unidade unidade) {
        Caixa caixa;
        int numeroProximoCaixa = obterUltimoCaixaInserido(unidade) + 1;
        for (int i = 0;  i < quantidade; i++, numeroProximoCaixa++) {
            caixa = new Caixa();
            caixa.setNumeroCaixa(numeroProximoCaixa);
            caixa.setUnidade(unidade);
            inserir(caixa);
        }
    }
    
    public boolean confirmarCaixaFechado(Caixa caixa) {
        caixaDAO = new CaixaDAOImpl();
        try {
            caixa = caixaDAO.pesquisar(caixa);
            caixaDAO.fecharConexao();
            verificarPesquisa(caixa);
            return !caixa.isAberto();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao pesquisar caixa): " + ex.getMessage());
        }
    }
    
    public List<Caixa> listarCaixasFechados(Unidade unidade) {
        List<Caixa> caixas = listar(unidade);
        List<Caixa> caixasFechados = new ArrayList<>();
        for (Caixa caixa : caixas) {
            if (!caixa.isAberto()) caixasFechados.add(caixa);
        }
        return caixasFechados;
    }
    
    public List<Caixa> listar(Unidade unidade) {
        caixaDAO = new CaixaDAOImpl();
        try {
            List<Caixa> caixas = caixaDAO.listar(unidade.getIdUnidade());
            caixaDAO.fecharConexao();
            return caixas;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao listar caixas): " + ex.getMessage());
        }
    }
    
    private int obterUltimoCaixaInserido(Unidade unidade) {
        caixaDAO = new CaixaDAOImpl();
        try {
            List<Caixa> caixas = caixaDAO.listar(unidade.getIdUnidade());
            caixaDAO.fecharConexao();
            return caixas.size();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao obter o número do caixa): " + ex.getMessage());
        }
    }

    private void verificarResultado(boolean result) {
        if (!result) throw new RetornoDeAlteracaoDeDadosInesperadoException();
    }
    
    private void verificarPesquisa(Caixa caixa) {
        if (caixa == null) throw new PesquisaNulaException();
    }
}

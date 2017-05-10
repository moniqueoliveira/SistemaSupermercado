
package sistemasupermercado.servicos;

import java.math.BigDecimal;
import java.sql.SQLException;
import sistemasupermercado.dao.SessaoCaixaDAOImpl;
import sistemasupermercado.dominio.Caixa;
import sistemasupermercado.dominio.Sessao;
import sistemasupermercado.dominio.SessaoCaixa;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.SessaoCaixaDAO;

public class SessaoCaixaServico {
    SessaoCaixaDAO sessaoCaixaDAO;
    
    public void abrirCaixa(SessaoCaixa sessaoCaixa) {
        sessaoCaixaDAO = new SessaoCaixaDAOImpl();
        CaixaServico caixaServico = new CaixaServico();
        
        sessaoCaixa.getCaixa().setAberto(true);
        caixaServico.alterar(sessaoCaixa.getCaixa());
        
        try {
            verificarResultado(sessaoCaixaDAO.inserir(sessaoCaixa));
            sessaoCaixaDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao inserir o inicio de caixa):\n" + ex.getMessage());
        }
    }
    
    public void fecharCaixa(SessaoCaixa sessaoCaixa) {
        sessaoCaixaDAO = new SessaoCaixaDAOImpl();
        SessaoServico sessaoServico = new SessaoServico();
        sessaoServico.encerrarSessao(sessaoCaixa.getSessao());
        
        CaixaServico caixaServico = new CaixaServico();
        sessaoCaixa.getCaixa().setAberto(false);
        caixaServico.alterar(sessaoCaixa.getCaixa());
        
        try {
            verificarResultado(sessaoCaixaDAO.alterarValorFechamento(sessaoCaixa));
            sessaoCaixaDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao fechar o caixa):\n" + ex.getMessage());
        }
        
    }
    
    public SessaoCaixa verificarCaixaAberto(Sessao sessao) {
        sessaoCaixaDAO = new SessaoCaixaDAOImpl();
        SessaoCaixa sessaoCaixa = new SessaoCaixa();
        sessaoCaixa.setSessao(sessao);
        try {
            sessaoCaixa = sessaoCaixaDAO.pesquisar(sessaoCaixa);
            sessaoCaixaDAO.fecharConexao();
            return sessaoCaixa;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao inserir o inicio de caixa):\n" + ex.getMessage());
        }
    }
    
    private void verificarResultado(boolean result) {
        if (!result)
            throw new RetornoDeAlteracaoDeDadosInesperadoException();
    }
}

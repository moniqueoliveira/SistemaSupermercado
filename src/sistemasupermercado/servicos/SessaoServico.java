/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasupermercado.servicos;

import java.sql.SQLException;
import java.util.Calendar;
import sistemasupermercado.dao.SessaoDAOImpl;
import sistemasupermercado.dominio.Sessao;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.SessaoDAO;

public class SessaoServico {
    SessaoDAO sessaoDAO;
    
    public Sessao iniciarSessao(Sessao sessao) {
        sessaoDAO = new SessaoDAOImpl();
        AutenticacaoServico autenticacaoServico = new AutenticacaoServico();
        
        //Retorna o usuario com todos os dados e valida o usuario e a senha
        sessao.setUsuario(autenticacaoServico.autenticar(sessao.getUsuario()));
        
        try {
            
            //Verifica se o usuário já tem uma sessão aberta
            Sessao sessaoAberta = sessaoDAO.pesquisarSessaoAberta(sessao);
            if (sessaoAberta != null) {
                sessaoDAO.fecharConexao();
                sessaoAberta.setUsuario(sessao.getUsuario());
                return sessaoAberta;
            }
            
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            sessao.setInicioSessao(c);
        
            verificarResultado(sessaoDAO.inserir(sessao));
            sessao.setIdSessao(sessaoDAO.obterUltimoID());
            sessaoDAO.fecharConexao();
            return sessao;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro durante o inicio da sessão.): " + ex.getMessage());
        }
        
    }
    
    public void encerrarSessao(Sessao sessao) {
        sessaoDAO = new SessaoDAOImpl();
        try {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            sessao.setFimSessao(c);
        
            verificarResultado(sessaoDAO.alterar(sessao));
            sessaoDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao encerrar a sessão):\n" + ex.getMessage());
        }
    }
    
    public Sessao pesquisar(Sessao sessao) {
        sessaoDAO = new SessaoDAOImpl();
        try {
            sessao = sessaoDAO.pesquisar(sessao);
            sessao.setUsuario(new UsuarioServico().pesquisar(sessao.getUsuario()));
            sessaoDAO.fecharConexao();
            return sessao;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro ao pesquisar o item de sessao): " + ex.getMessage());
        }
    }

    private void verificarResultado(boolean result) {
        if (!result) throw new RetornoDeAlteracaoDeDadosInesperadoException("Retorno inesperado de "
                + "alteração do banco de dados durante o início da sessão.");
    }
    
    
}

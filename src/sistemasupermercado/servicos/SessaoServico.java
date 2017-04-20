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
        
        //Retorna o usuario com todos os dados
        sessao.setUsuario(autenticacaoServico.autenticar(sessao.getUsuario()));
        
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        sessao.setInicioSessao(c);
        
        try {
            verificarResultado(sessaoDAO.inserir(sessao));
            sessao.setIdSessao(sessaoDAO.obterUltimoID());
            return sessao;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro durante o inicio da sessão.): " + ex.getMessage());
        }
        
    }

    private void verificarResultado(boolean result) {
        if (!result) throw new RetornoDeAlteracaoDeDadosInesperadoException("Retorno inesperado de "
                + "alteração do banco de dados durante o início da sessão.");
    }
}

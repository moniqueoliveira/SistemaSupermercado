/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasupermercado.servicos;

import java.sql.SQLException;
import sistemasupermercado.dao.UnidadeDAOImpl;
import sistemasupermercado.dominio.Unidade;
import sistemasupermercado.exceptions.PesquisaNulaException;
import sistemasupermercado.interfaces.dao.UnidadeDAO;

public class UnidadeServico {
    UnidadeDAO unidadeDAO;
    
    public Unidade pesquisar(Unidade unidade) {
        unidadeDAO = new UnidadeDAOImpl();
        try {
            unidade = unidadeDAO.pesquisar(unidade);
            validarPesquisa(unidade);
            unidadeDAO.fecharConexao();
            return unidade;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException (Erro na pesquisa de unidade): " + ex.getMessage());
        }
    }

    private void validarPesquisa(Unidade unidade) {
        if (unidade == null)
            throw new PesquisaNulaException("A pesquisa da unidade não retornou resultados.");
    }
}

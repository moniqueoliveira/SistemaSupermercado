
package sistemasupermercado.servicos;

import java.sql.SQLException;
import sistemasupermercado.dao.EnderecoFornecedorDAOImpl;
import sistemasupermercado.dominio.EnderecoFornecedor;
import sistemasupermercado.exceptions.PesquisaNulaException;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.EnderecoFornecedorDAO;

public class EnderecoFornecedorServico {
    EnderecoFornecedorDAO enderecoFornecedorDAO;
    
    public void inserir(EnderecoFornecedor enderecoFornecedor) {
        validarDados(enderecoFornecedor);
        enderecoFornecedorDAO = new EnderecoFornecedorDAOImpl();
        try {
            verificarResultado(enderecoFornecedorDAO.inserir(enderecoFornecedor));
            enderecoFornecedorDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }
    
    public void alterar(EnderecoFornecedor enderecoFornecedor) {
        validarDados(enderecoFornecedor);
        enderecoFornecedorDAO = new EnderecoFornecedorDAOImpl();
        try {
            verificarResultado(enderecoFornecedorDAO.alterar(enderecoFornecedor));
            enderecoFornecedorDAO.fecharConexao();
        } catch (SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }
    
    public void excluir(EnderecoFornecedor enderecoFornecedor) {
        enderecoFornecedorDAO = new EnderecoFornecedorDAOImpl();
        try {
            verificarResultado(enderecoFornecedorDAO.excluir(enderecoFornecedor));
            enderecoFornecedorDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }
    
    public EnderecoFornecedor pesquisar(EnderecoFornecedor enderecoFornecedor) {
        enderecoFornecedorDAO = new EnderecoFornecedorDAOImpl();
        try {
            enderecoFornecedor = enderecoFornecedorDAO.pesquisar(enderecoFornecedor);
            verificarPesquisa(enderecoFornecedor);
            enderecoFornecedorDAO.fecharConexao();
            return enderecoFornecedor;
            
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex);
        }
    }

    public String validarDados(EnderecoFornecedor enderecoFornecedor) {
        StringBuilder mensagem = new StringBuilder();
        if (enderecoFornecedor.getLogradouro().equals("")) mensagem.append("Logradouro\n");
        if (enderecoFornecedor.getNumero() == null) mensagem.append("Número\n");
        if (enderecoFornecedor.getBairro().equals("")) mensagem.append("Bairro\n");
        if (!enderecoFornecedor.validarCep()) mensagem.append("CEP\n");
        if (!enderecoFornecedor.validarUf()) mensagem.append("UF\n");
        if (enderecoFornecedor.getCidade().equals(""))mensagem.append("Cidade\n");
        
        
        return mensagem.toString();
    }

    private void verificarResultado(boolean resultado) {
        if (!resultado) {
            throw new RetornoDeAlteracaoDeDadosInesperadoException();
        }
    }

    private void verificarPesquisa(EnderecoFornecedor enderecoFornecedor) {
        if (enderecoFornecedor == null) {
            throw new PesquisaNulaException();
        }
    }
}


package sistemasupermercado.servicos;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dao.EnderecoFornecedorDAOImpl;
import sistemasupermercado.dao.FornecedorDAOImpl;
import sistemasupermercado.dominio.Fornecedor;
import sistemasupermercado.exceptions.DadosInvalidosException;
import sistemasupermercado.exceptions.PesquisaNulaException;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.EnderecoFornecedorDAO;
import sistemasupermercado.interfaces.dao.FornecedorDAO;

public class FornecedorServico {
    private FornecedorDAO fornecedorDAO;
    private EnderecoFornecedorDAO enderecoFornecedorDAO;
    private EnderecoFornecedorServico enderecoFornecedorServico;
    private int ultimoID;
    
    public void inserir(Fornecedor fornecedor) {
        validarDados(fornecedor);
        fornecedorDAO = new FornecedorDAOImpl();
        enderecoFornecedorDAO = new EnderecoFornecedorDAOImpl();
        try {
            verificarResultado(fornecedorDAO.inserir(fornecedor));
            if (fornecedor.getIdFornecedor() == null) {
                ultimoID = fornecedorDAO.obterUltimoID();
                fornecedor.setIdFornecedor(this.ultimoID);
            }
            verificarResultado(enderecoFornecedorDAO.inserir(fornecedor.getEndereco()));
        } catch(SQLException ex) {
            throw new RuntimeException("SQLExeption: " + ex);
        }
    }
    
    public void alterar(Fornecedor fornecedor) {
        validarDados(fornecedor);
        fornecedorDAO = new FornecedorDAOImpl();
        enderecoFornecedorDAO = new EnderecoFornecedorDAOImpl();
        try {
            verificarResultado(fornecedorDAO.alterar(fornecedor));
            verificarResultado(enderecoFornecedorDAO.alterar(fornecedor.getEndereco()));
        } catch(SQLException ex) {
            throw new RuntimeException("SQLExeption: " + ex);
        }
    }
    
    public void excluir(Fornecedor fornecedor) {
        fornecedorDAO = new FornecedorDAOImpl();
        try {
            verificarResultado(fornecedorDAO.excluir(fornecedor));
        } catch(SQLException ex) {
            throw new RuntimeException("SQLExeption: " + ex);
        }
    }
    
    public Fornecedor pesquisar(Fornecedor fornecedor) {
        fornecedorDAO = new FornecedorDAOImpl();
        try {
            fornecedor = fornecedorDAO.pesquisar(fornecedor);
            validarPesquisa(fornecedor);
            return fornecedor;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLExeption: " + ex);
        }
    }
    
    public List<Fornecedor> listar(String pesquisaPor, String texto) {
        fornecedorDAO = new FornecedorDAOImpl();
        try{
            return fornecedorDAO.listar(pesquisaPor, texto);
        } catch(SQLException ex) {
            throw new RuntimeException("SQLExeption: " + ex);
        }
    }

    private void validarDados(Fornecedor fornecedor) {
        StringBuilder mensagem = new StringBuilder();
        if (!fornecedor.validarCnpj()) mensagem.append("CNPJ\n");
        if (fornecedor.getNomeFantasia().equals("")) mensagem.append("Nome fantasia\n");
        if (fornecedor.getRazaoSocial().equals("")) mensagem.append("Razão social\n");
        
        enderecoFornecedorServico = new EnderecoFornecedorServico();
        mensagem.append(enderecoFornecedorServico.validarDados(fornecedor.getEndereco()));
        
        if (mensagem.length() > 1) {
            throw new DadosInvalidosException("Os seguintes dados estão sem preenchimento ou foram"
                    + "preenchidos incorretamente:\n" + mensagem.toString());
        } 
    }

    private void verificarResultado(boolean resultado) {
        if (!resultado) {
            throw new RetornoDeAlteracaoDeDadosInesperadoException();
        }
    }

    private void validarPesquisa(Fornecedor fornecedor) {
        if (fornecedor == null) {
            throw new PesquisaNulaException();
        }
    }
    
    public int obterUltimoID(){
        return this.ultimoID;
    }
}

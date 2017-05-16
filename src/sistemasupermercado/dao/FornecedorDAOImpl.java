
package sistemasupermercado.dao;

import sistemasupermercado.interfaces.dao.FornecedorDAO;
import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAOImpl implements FornecedorDAO {
    private final Connection conexao;

    public FornecedorDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public boolean inserir(Fornecedor obj) throws SQLException {
        if (obj.getIdFornecedor() == null) return inserirSemID(obj);
        else return inserirComID(obj);
    }
    
    public boolean inserirSemID(Fornecedor obj) throws SQLException {
        String sql = "insert into fornecedores (nome_fantasia, razao_social, cnpj, ativo) values (?, ?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getNomeFantasia());
        pstm.setString(2, obj.getRazaoSocial());
        pstm.setString(3, obj.getCnpj());
        pstm.setBoolean(4, obj.isAtivo());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }
    private boolean inserirComID(Fornecedor obj) throws SQLException {
        String sql = "insert into fornecedores (id_fornecedor, nome_fantasia, razao_social, cnpj, ativo) values (?, ?, ?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdFornecedor());
        pstm.setString(2, obj.getNomeFantasia());
        pstm.setString(3, obj.getRazaoSocial());
        pstm.setString(4, obj.getCnpj());
        pstm.setBoolean(5, obj.isAtivo());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }
    @Override
    public boolean alterar(Fornecedor obj) throws SQLException {
        String sql = "update fornecedores set nome_fantasia = ?, razao_social = ?, cnpj = ?, ativo = ? "
                + "where id_fornecedor = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getNomeFantasia());
        pstm.setString(2, obj.getRazaoSocial());
        pstm.setString(3, obj.getCnpj());
        pstm.setBoolean(4, obj.isAtivo());
        pstm.setInt(5, obj.getIdFornecedor());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean excluir(Fornecedor obj) throws SQLException {
        String sql = "delete from fornecedores where id_fornecedor = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdFornecedor());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public Fornecedor pesquisar(Fornecedor obj) throws SQLException {
        Fornecedor fornecedor = null;
        String sql = "select * from fornecedores where id_fornecedor = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdFornecedor());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            fornecedor = new Fornecedor();
            fornecedor.setIdFornecedor(obj.getIdFornecedor());
            fornecedor.setNomeFantasia(rs.getString("nome_fantasia"));
            fornecedor.setRazaoSocial(rs.getString("razao_social"));
            fornecedor.setCnpj(rs.getString("cnpj"));
            fornecedor.setAtivo(rs.getBoolean("ativo"));
        }
        pstm.close();
        return fornecedor;
    }

    private List<Fornecedor> listar(String filtro) throws SQLException {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "select * from fornecedores " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setIdFornecedor(rs.getInt("id_fornecedor"));
            fornecedor.setNomeFantasia(rs.getString("nome_fantasia"));
            fornecedor.setRazaoSocial(rs.getString("razao_social"));
            fornecedor.setCnpj(rs.getString("cnpj"));
            fornecedores.add(fornecedor);
        }
        pstm.close();
        return fornecedores;
    }

    @Override
    public List<Fornecedor> listar(String pesquisaPor, String texto) throws SQLException {
        String filtro;
        switch (pesquisaPor) {
            case ("ID"):
                filtro = "where id_fornecedor like '%" + texto + "%'";
                break;
            case ("CNPJ"):
                filtro = "where cnpj like '%" + texto + "%'";
                break;
            case ("Razão Social"):
                filtro = "where razao_social like '%" + texto + "%'";
                break;
            case ("Nome Fantasia"):
                filtro = "where nome_fantasia like '%" + texto + "%'";
                break;
            default:
                filtro = "";
        }
        return listar(filtro);
    }

    @Override
    public Integer obterUltimoID() throws SQLException {
        String sql = "select last_insert_id()";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        return rs.getInt("last_insert_id()");
    }

    @Override
    public void fecharConexao() throws SQLException {
        this.conexao.close();
    }

    
    
}

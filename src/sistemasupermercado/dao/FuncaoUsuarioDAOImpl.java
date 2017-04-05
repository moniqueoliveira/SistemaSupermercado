
package sistemasupermercado.dao;

import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.FuncaoUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemasupermercado.interfaces.dao.FuncaoUsuarioDAO;

public class FuncaoUsuarioDAOImpl implements FuncaoUsuarioDAO {
    private final Connection conexao;

    public FuncaoUsuarioDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public boolean inserir(FuncaoUsuario obj) throws SQLException {
        if (obj.getIdFuncao() == null) return inserirSemId(obj);
        return inserirComId(obj);
    }
    
    private boolean inserirSemId(FuncaoUsuario obj) throws SQLException {
        String sql = "insert into funcoes_usuarios (descricao) values (?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getDescricao());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }
    
    private boolean inserirComId(FuncaoUsuario obj) throws SQLException {
        String sql = "insert into funcoes_usuarios (id_funcao, descricao) values (?,?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdFuncao());
        pstm.setString(2, obj.getDescricao());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(FuncaoUsuario obj) throws SQLException {
        String sql = "update funcoes_usuarios set descricao = ? where id_funcao = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getDescricao());
        pstm.setInt(2, obj.getIdFuncao());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean excluir(FuncaoUsuario obj) throws SQLException {
        String sql = "delete from funcoes_usuarios where id_funcao = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdFuncao());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public FuncaoUsuario pesquisar(FuncaoUsuario obj) throws SQLException {
        FuncaoUsuario funcaoUsuario = null;
        String sql = "select * from funcoes_usuarios where id_funcao = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdFuncao());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            funcaoUsuario = new FuncaoUsuario();
            funcaoUsuario.setIdFuncao(obj.getIdFuncao());
            funcaoUsuario.setDescricao(rs.getString("descricao"));
        }
        pstm.close();
        return funcaoUsuario;
    }

    private List<FuncaoUsuario> listar(String filtro) throws SQLException {
        List<FuncaoUsuario> funcoes = new ArrayList<>();
        String sql = "select * from funcoes_usuarios " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            FuncaoUsuario funcaoUsuario = new FuncaoUsuario();
            funcaoUsuario.setIdFuncao(rs.getInt("id_funcao"));
            funcaoUsuario.setDescricao(rs.getString("descricao"));
            funcoes.add(funcaoUsuario);
        }
        pstm.close();
        return funcoes;
    }

    @Override
    public List<FuncaoUsuario> listar(String pesquisaPor, String texto) throws SQLException {
        String filtro;
        switch(pesquisaPor){
            case ("ID"):
                filtro = "where id_funcao like '%" + texto + "%'";
                break;
            default:
                filtro = "where descricao like '%" + texto + "%'";
        }
        return listar(filtro);
    }

    @Override
    public void fecharConexao() throws SQLException {
        this.conexao.close();
    }
    
    
}

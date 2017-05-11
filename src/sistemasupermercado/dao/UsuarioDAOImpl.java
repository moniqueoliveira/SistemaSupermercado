
package sistemasupermercado.dao;

import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemasupermercado.interfaces.dao.UsuarioDAO;

public class UsuarioDAOImpl implements UsuarioDAO {
    private final Connection conexao;

    public UsuarioDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public boolean inserir(Usuario obj) throws SQLException {
        if (obj.getIdUsuario() == null) return inserirSemId(obj);
        return inserirComId(obj);
    }
    
    private boolean inserirSemId(Usuario obj) throws SQLException {
        String sql = "insert into usuarios (nome, login, senha, id_unidade, id_funcao, ativo) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getNome());
        pstm.setString(2, obj.getLogin());
        pstm.setString(3, obj.getSenha());
        pstm.setInt(4, obj.getUnidade().getIdUnidade());
        pstm.setInt(5, obj.getFuncaoUsuario().getIdFuncao());
        pstm.setBoolean(6, obj.isAtivo());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }
    
    private boolean inserirComId(Usuario obj) throws SQLException {
        String sql = "insert into usuarios (id_usuario, nome, login, senha, id_unidade, id_funcao, ativo) "
                + "values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdUsuario());
        pstm.setString(2, obj.getNome());
        pstm.setString(3, obj.getLogin());
        pstm.setString(4, obj.getSenha());
        pstm.setInt(5, obj.getUnidade().getIdUnidade());
        pstm.setInt(6, obj.getFuncaoUsuario().getIdFuncao());
        pstm.setBoolean(7, obj.isAtivo());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(Usuario obj) throws SQLException {
        String sql = "update usuarios set nome = ?, login = ?, senha = ?, id_unidade = ?, id_funcao = ?, "
                + "ativo = ? where id_usuario = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getNome());
        pstm.setString(2, obj.getLogin());
        pstm.setString(3, obj.getSenha());
        pstm.setInt(4, obj.getUnidade().getIdUnidade());
        pstm.setInt(5, obj.getFuncaoUsuario().getIdFuncao());
        pstm.setBoolean(6, obj.isAtivo());
        pstm.setInt(7, obj.getIdUsuario());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean excluir(Usuario obj) throws SQLException {
        String sql = "delete from usuarios where id_usuario = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdUsuario());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public Usuario pesquisar(Usuario obj) throws SQLException {
        if (obj.getLogin() != null)
            return pesquisar("select * from usuarios where login = '" + obj.getLogin() + "'");
        else
            return pesquisar("select * from usuarios where id_usuario = " + obj.getIdUsuario());
        
    }
    
    private Usuario pesquisar(String sql) throws SQLException {
        Usuario usuario = null;
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setUnidade(rs.getInt("id_unidade"));
            usuario.setFuncaoUsuario(rs.getInt("id_funcao"));
            usuario.setAtivo(rs.getBoolean("ativo"));
        }
        pstm.close();
        return usuario;
    }

    private List<Usuario> listar(String filtro) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "select * from usuarios " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
         ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setUnidade(rs.getInt("id_unidade"));
            usuario.setFuncaoUsuario(rs.getInt("id_funcao"));
            usuario.setAtivo(rs.getBoolean("ativo"));
            usuarios.add(usuario);
        }
        pstm.close();
        return usuarios;
    }
    
    @Override
    public List<Usuario> listar() throws SQLException {
        return listar("");
    }

    @Override
    public List<Usuario> listar(String pesquisaPor, String texto, int idUnidade) throws SQLException {
        String filtro= "where id_unidade = " + idUnidade;
        switch(pesquisaPor) {
            case ("ID"):
                filtro = filtro + " and id_usuario like '%" + texto + "%'";
                break;
            case ("Login"):
                filtro = filtro + " and login like '%" + texto + "%'";
                break;
            case ("Nome"):
                filtro = filtro + " and nome like '%" + texto + "%'";
        }
        return listar(filtro);
    }

    @Override
    public void fecharConexao() throws SQLException {
        this.conexao.close();
    }
    
}

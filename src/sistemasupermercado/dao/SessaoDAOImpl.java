
package sistemasupermercado.dao;

import sistemasupermercado.interfaces.dao.SessaoDAO;
import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.Sessao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SessaoDAOImpl implements SessaoDAO {
    private final Connection conexao;

    public SessaoDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public boolean inserir(Sessao obj) throws SQLException {
        String sql = "insert into sessoes (id_usuario, inicio_sessao) values (?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getUsuario().getIdUsuario());
        
        Timestamp tmstmp = new Timestamp(obj.getInicioSessao().getTimeInMillis());
        pstm.setTimestamp(2, tmstmp);
        
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(Sessao obj) throws SQLException {
        String sql = "update sessoes set fim_sessao = ? where id_sesssao = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        
        Timestamp tmstmp = new Timestamp(obj.getFimSessao().getTimeInMillis());
        pstm.setTimestamp(1, tmstmp);
        
        pstm.setInt(2, obj.getIdSessao());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public Sessao pesquisar(Sessao obj) throws SQLException {
        Sessao sessao = null;
        String sql = "select * from sessoes where id_sessao = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdSessao());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            sessao = new Sessao();
            Timestamp tmstmp;
            Calendar calendar = Calendar.getInstance();
            
            sessao.setIdSessao(rs.getInt("id_sessao"));
            sessao.setUsuario(rs.getInt("id_usuario"));
            
            tmstmp = rs.getTimestamp("inicio_sessao");
            calendar.setTimeInMillis(tmstmp.getTime());
            sessao.setInicioSessao(calendar);
            
            tmstmp = rs.getTimestamp("fim_sessao");
            if (tmstmp != null) {
                calendar.setTimeInMillis(tmstmp.getTime());
                sessao.setFimSessao(calendar);
            }
        }
        pstm.close();
        return sessao;
    }

    @Override
    public List<Sessao> listar(String filtro) throws SQLException {
        List<Sessao> sessoes = new ArrayList<>();
        String sql = "select * from sessoes " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Sessao sessao = new Sessao();
            Timestamp tmstmp;
            Calendar calendar = Calendar.getInstance();
            
            sessao.setIdSessao(rs.getInt("id_sessao"));
            sessao.setUsuario(rs.getInt("id_usuario"));
            
            tmstmp = rs.getTimestamp("inicio_sessao");
            calendar.setTimeInMillis(tmstmp.getTime());
            sessao.setInicioSessao(calendar);
            
            tmstmp = rs.getTimestamp("fim_sessao");
            if (tmstmp != null) {
                calendar.setTimeInMillis(tmstmp.getTime());
                sessao.setFimSessao(calendar);
            }
            sessoes.add(sessao);
        }
        pstm.close();
        return sessoes;
    }
    
    @Override
    public int obterUltimoID() throws SQLException {
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

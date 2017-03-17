
package sistemasupermercado.dao;

import sistemasupermercado.interfaces.dao.CaixaDAO;
import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.Caixa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CaixaDAOImpl implements CaixaDAO {
    private final Connection conexao;

    public CaixaDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }
        
    @Override
    public boolean inserir(Caixa obj) throws SQLException {
        String sql = "insert into caixas (numero_caixa, id_unidade) values (?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getNumeroCaixa());
        pstm.setInt(2, obj.getUnidade().getIdUnidade());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    

    @Override
    public boolean excluir(Caixa obj) throws SQLException {
        String sql = "delete from caixas where numero_caixa = ? and id_unidade = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getNumeroCaixa());
        pstm.setInt(2, obj.getUnidade().getIdUnidade());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public List<Caixa> listar(String filtro) throws SQLException {
        List<Caixa> caixas = new ArrayList<>();
        String sql = "select * from caixas " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()){
            Caixa caixa = new Caixa();
            caixa.setNumeroCaixa(rs.getInt("numero_caixa"));
            caixa.setUnidade(rs.getInt("id_unidade"));
            caixas.add(caixa);
        }
        return caixas;
    }
    
}

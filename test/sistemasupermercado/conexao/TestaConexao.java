
package sistemasupermercado.conexao;

import sistemasupermercado.conexao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaConexao {
    public static void main(String[] args) {
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            System.out.println("Conectado");
            String sql = "select descricao from produtos where codigo = 2";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                System.out.println(rs.getString("descricao"));
            }
        } catch(RuntimeException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

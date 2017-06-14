/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasupermercado.relatorios;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import sistemasupermercado.conexao.ConnectionFactory;

public class GeradorDeRelatorioDeUsuarios extends GeradorDeRelatorios{
    public JFrame abrirRelatorio(int idUnidade, String usuario, String dia, int mesMin, int mesMax, String ano, int tipoPesquisa) throws JRException {

        InputStream inputStream = getClass().getResourceAsStream("RelatorioDeUsuarios.jasper");

        Map parametros = new HashMap();
        ConnectionFactory conexao = new ConnectionFactory();

        parametros.put( "idUnidade", idUnidade);
        parametros.put( "idUsuario", usuario);
        parametros.put( "dia", dia);
        parametros.put( "mesMin", mesMin);
        parametros.put( "mesMax", mesMax);
        parametros.put( "ano", ano);
        parametros.put( "tipoPesquisa", tipoPesquisa);

        JFrame jFrame;
        try {
            jFrame = new GeradorDeRelatorios().openReport( "Relatório de Usuarios", inputStream, parametros,
                    conexao.getConnection());
            return jFrame;

        } catch ( JRException exc ) {
            System.out.println(exc.getMessage());
        }
        return null;
    }

}

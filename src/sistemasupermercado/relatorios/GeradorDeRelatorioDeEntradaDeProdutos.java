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

public class GeradorDeRelatorioDeEntradaDeProdutos extends GeradorDeRelatorios{
    public JFrame abrirRelatorio(Integer idUnidade, int mes, String ano) throws JRException {

        InputStream inputStream = getClass().getResourceAsStream("RelatorioDeEntradaDeProdutos.jasper");

        Map parametros = new HashMap();
        ConnectionFactory conexao = new ConnectionFactory();

        parametros.put( "parameter1", idUnidade);
        parametros.put( "mes", mes);
        parametros.put( "ano", ano);

        JFrame jFrame;
        try {
            jFrame = new GeradorDeRelatorios().openReport( "Relatório de Entrada de Produtos", inputStream, parametros,
                    conexao.getConnection());
            return jFrame;

        } catch ( JRException exc ) {
            System.out.println(exc.getMessage());
        }
        return null;
    }

}

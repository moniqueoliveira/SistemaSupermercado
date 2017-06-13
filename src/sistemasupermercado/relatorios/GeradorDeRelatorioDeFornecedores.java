/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasupermercado.relatorios;

import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import sistemasupermercado.conexao.ConnectionFactory;

public class GeradorDeRelatorioDeFornecedores extends GeradorDeRelatorios{
    public JFrame abrirRelatorio() throws JRException {

        InputStream inputStream = getClass().getResourceAsStream("RelatorioDeFornecedores.jasper");

        Map parametros = new HashMap();
        ConnectionFactory conexao = new ConnectionFactory();

        JFrame jFrame;
        try {
            jFrame = new GeradorDeRelatorios().openReport( "Relatório de Fornecedores", inputStream, parametros,
                    conexao.getConnection());
            
            URL url = this.getClass().getResource("/sistemasupermercado/imagens/icone.png");
            Image image = java.awt.Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/sistemasupermercado/imagens/icone.png"));
            jFrame.setIconImage(image);
                    
            return jFrame;

        } catch ( JRException exc ) {
            System.out.println(exc.getMessage());
        }
        return null;
    }

}
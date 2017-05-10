/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasupermercado.teste.operacoesmatematicas;

import java.math.BigDecimal;

/**
 *
 * @author Monique
 */
public class TestaBigDecimal {
    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal(2.5);
        BigDecimal b2 = new BigDecimal(3.5);
        
        b1 = b2;
        b1 = b1.subtract(new BigDecimal(10));
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b1.abs());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasupermercado.exceptions;

public class EstoqueInsuficienteException extends RuntimeException {

    public EstoqueInsuficienteException() {
        super("Não há quantidade suficiente do produto em estoque!");
    }
    
    public EstoqueInsuficienteException(String string) {
        super(string);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen;

/**
 *
 * @author jdrr7
 */
public class Cantidadcompras {
    
    private Cliente cliente;
    private int cantidadCompras;

    public Cantidadcompras(Cliente cliente, int cantidadCompras) {
        this.cliente = cliente;
        this.cantidadCompras = cantidadCompras;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getCantidadCompras() {
        return cantidadCompras;
    }

    
    
    
}

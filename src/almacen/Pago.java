/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen;

/**
 *
 * @author joaquin
 */
public class Pago {
    public int id_pago;
    public String metodo;
    public double costo_adicional;

    public Pago(int id_pago, String metodo, double costo_adicional) {
        this.id_pago = id_pago;
        this.metodo = metodo;
        this.costo_adicional = costo_adicional;
    }

    public int getId_pago() {
        return id_pago;
    }

    public String getMetodo() {
        return metodo;
    }

    public double getCosto_adicional() {
        return costo_adicional;
    }
    
    
}

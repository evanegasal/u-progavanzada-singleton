/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author joaquin
 */
public class Venta {

    public int id_venta;
    public ArrayList<Articulo> canasta;
    public Cliente cliente;
    public Date fecha;
    public Pago pago;

    public Venta(int id_venta, Date fecha, ArrayList<Articulo> canasta, Cliente cliente, Pago pago) {
        this.id_venta = id_venta;
        this.canasta = canasta;
        this.cliente = cliente;
        this.fecha = fecha;
        this.pago = pago;
    }

    public int getId_venta() {
        return id_venta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public ArrayList<Articulo> getCanasta() {
        return canasta;
    }

    public void setCanasta(ArrayList<Articulo> canasta) {
        this.canasta = canasta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setcliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public void mostrar_factura() throws IOException {
        double total = 0;
        double precio_parcial = 0;
        System.out.print("************************* FACTURA #" + this.id_venta + " *********************\n\tCliente:" + cliente.getNombre() + "\n\tProductos en canasta:");
        for (Articulo producto : canasta) {
            System.out.print("\n\t\t" + producto.getId() + ". " + producto.getProducto() + " ..........$ " + producto.getPrecio());
            precio_parcial += producto.getPrecio();

        }
        System.out.println("\n\ttotal + IVA: -------------------- $" + precio_parcial);
        total = precio_parcial + (precio_parcial * pago.getCosto_adicional()/100);
        System.out.println("\t" + pago.getMetodo() + " x" + pago.getCosto_adicional() + "---------- $" + (precio_parcial * pago.getCosto_adicional()/100));
        System.out.println("\t Total a Pagar -------------------- $" + total+"\n\n presione enter para continuar");
        System.in.read();
    }

    public double total_pagar() {
        double total = 0;
        double precio_parcial = 0;
        for (Articulo producto : canasta) {
            precio_parcial += producto.getPrecio();
        }
        total = precio_parcial + (precio_parcial * pago.getCosto_adicional()/100);
        return total;
    }
}

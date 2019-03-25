/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen;

import java.util.Date;

/**
 *
 * @author joaquin
 */
public class Cliente {
    
    public String id;
    public String telefono;
    public String nombre;
    public Date ultima_compra;

    public Cliente(String id, String nombre, String telefono) {
        this.id = id;
        this.telefono = telefono;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombre() {
        return nombre;
    }  

    public void setUltima_compra(Date ultima_compra) {
        this.ultima_compra = ultima_compra;
    }

    public Date getUltima_compra() {
        return ultima_compra;
    }
    
    
    
}

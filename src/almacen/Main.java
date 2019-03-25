/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author Esteban
 */
/*public class Main {

    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Inventario> inventarios = new ArrayList<>();
    private ArrayList<Pago> pagos = new ArrayList<>();
    private ArrayList<Venta> ventas = new ArrayList<>();
    
    public static void main(String[] args) {
        Main n = new Main();
        n.create_venta();
    }

    
    private void create_venta(){
        Cliente cliente_venta = select_client();
        ArrayList<Articulo> canasta= create_canasta();
        Pago metodo = select_pago();
        
        if(cliente_venta!= null && canasta != null){
            ventas.add(new Venta(ventas.size()+1, new Date(), canasta, cliente_venta, metodo));
            ventas.get(ventas.size()-1).mostrar_factura();
            clientes.get(cliente_venta.getId()).setUltima_compra(new Date());
        }
    }
    

    
    private Pago select_pago(){
        Pago metodo = null;
        int opc;
        do{
            System.out.print("*******************Añadir Metodo de Pago*****************");
            for(Pago pago: pagos){
                System.out.print("\n\t"+pago.getId_pago()+". "+pago.getMetodo()+" .......................... %"+(pago.getCosto_adicional()*100));
            }
            System.out.print("\n\nSeleccione el metodo de pago a usar para pagar la venta\n\t> ");  
            Scanner sc = new Scanner(System.in);
            try{
                opc = sc.nextInt();
                if(opc <=pagos.size() && opc>0){
                    metodo = pagos.get(opc-1);
                }else{
                    System.out.println("****** Por favor digite un número valido *******"); 
                }
            }catch(java.util.InputMismatchException e){
                opc = -1;
                System.out.println("****** Por favor digite un número valido *******");
            }
        }while(opc <=0 || opc>pagos.size());
        
        return metodo;       
    }
    
    private Cliente select_client(){
        Cliente cliente_venta = null;
        int opc;
        do{
            System.out.print("*******************Añadir cliente a Venta*****************");
            for(Cliente cliente: clientes){
                System.out.print("\n\t"+cliente.getId()+". "+cliente.getNombre());
            }
            System.out.print("\n\nSeleccione el cliente al cual realizara la venta\n\t> ");  
            Scanner sc = new Scanner(System.in);
            try{
                opc = sc.nextInt();
                if(opc <=clientes.size() && opc>0){
                    cliente_venta = clientes.get(opc-1);
                }else{
                    System.out.println("****** Por favor digite un número valido *******"); 
                }
            }catch(java.util.InputMismatchException e){
                opc = -1;
                System.out.println("****** Por favor digite un número valido *******");
            }
        }while(opc <=0 || opc>clientes.size());
        
        return cliente_venta;
    }
    
    private ArrayList<Articulo> create_canasta(){
        ArrayList<Articulo> canasta = new ArrayList<>();
        int opc;
        do{
            System.out.print("*******************Añadir a Canasta*****************");
            for(Inventario invent: inventarios){
                if(invent.getCantidad()>0){
                    System.out.print("\n\t"+invent.getArticulo().getId()+". "+invent.getArticulo().getProducto()+".......................... $"+invent.getArticulo().getPrecio());
                }
            }
            System.out.print("\n\nSeleccione el producto que desea agregar a la canasta, presione 0 para terminar\n\t> ");  
            Scanner sc = new Scanner(System.in);
            try{
                opc = sc.nextInt();
                if(opc <=inventarios.size() && opc>0){
                    if(inventarios.get(opc-1).getCantidad()>0){
                        canasta.add(inventarios.get(opc-1).getArticulo());
                        inventarios.get(opc-1).setCantidad(inventarios.get(opc-1).getCantidad()-1);
                    }
                }else if(opc == 0){
                    System.out.println("************ Productos añadidos satisfactoriamente a la Canasta!!! ************");                   
                }else{
                    System.out.println("****** Por favor digite un número valido *******"); 
                }
            }catch(java.util.InputMismatchException e){
                opc = -1;
                System.out.println("****** Por favor digite un número valido *******");
            }
            
        }while(opc != 0);
           
        return canasta;
    }
    
}*/

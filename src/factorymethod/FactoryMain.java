/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymethod;

import almacen.Articulo;
import almacen.Cantidadcompras;
import almacen.Cliente;
import almacen.Inventario;
import almacen.Pago;
import almacen.Venta;
import java.sql.SQLException;
import java.util.List;
import factorymethod.dao.ProductDAO;
import factorymethod.entity.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wilgen
 */
public class FactoryMain {

    /**
     * @param args the command line arguments
     */
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Pago> pagos = new ArrayList<>();
    private ArrayList<Venta> ventas = new ArrayList<>();
    private ProductDAO productDAO;

    public static void factory_start() throws SQLException, IOException {
        // TODO code application logic here
        //Se crean los productos a registrar
        FactoryMain f = new FactoryMain();
        int opcion = 2;
        /*do {
            Scanner n = new Scanner(System.in);
            System.out.print("*********Seleccionar base de datos***********\n\n\t1. Postgres\n\t2. Oracle DB\n\n\t> ");
            try {
                opcion = n.nextInt();
            } catch (Exception e) {
                System.out.println("**********Por favor ingrese un numero valido! *******");
                opcion = 0;
            }
        } while (opcion < 1 || opcion > 2);*/
        //Crea una Instancia de DAO
        f.productDAO = new ProductDAO();

        Scanner read = new Scanner(System.in);
        int Opcion1;
        do {
            System.out.println("\n\n---------------- MENU DE OPCIONES ----------------");
            System.out.println("1. Crear una venta");
            System.out.println("2. Mostrar lista de Clientes");
            System.out.println("3. Mostrar lista de productos");
            System.out.println("4. Mostrar Mejor Cliente");
            System.out.println("5. Salir");

            Opcion1 = read.nextInt();
            switch (Opcion1) {
                case 1:
                    //Guardamos los productos
                    f.create_venta();
                    break;
                case 2:
                    f.Consulta_cliente();
                    break;
                case 3:
                    f.Consulta_producto();
                    break;
                case 4:
                    f.Consulta_mejor_cliente();
                    break;
                case 5:
                    break;

                default:
                    System.out.println("Seleccione una opcion valida ");
                    break;
            }

        } while (Opcion1 != 5);

    }

    private void create_venta() throws IOException {

        Cliente cliente_venta = select_client();
        ArrayList<Articulo> canasta = create_canasta();
        Pago metodo = select_pago();
        int id_compra = productDAO.findLast_id_compra();
        if (cliente_venta != null && canasta != null) {
            productDAO.saveSale(new Venta(id_compra + 1, new Date(), canasta, cliente_venta, metodo));
            ventas.add(new Venta(id_compra + 1, new Date(), canasta, cliente_venta, metodo));
            ventas.get(ventas.size() - 1).mostrar_factura();
            //clientes.get(cliente_venta.getId()).setUltima_compra(new Date());
        }
        create_venta();
    }

    private Pago select_pago() {
        Pago metodo = null;
        ArrayList<Pago> pagos = productDAO.findAllPagos();
        int opc;
        do {
            System.out.print("*******************Añadir Metodo de Pago*****************");
            for (Pago pago : pagos) {
                System.out.print("\n\t" + pago.getId_pago() + ". " + pago.getMetodo() + " .......................... %" + pago.getCosto_adicional());
            }
            System.out.print("\n\nSeleccione el metodo de pago a usar para pagar la venta\n\t> ");
            Scanner sc = new Scanner(System.in);
            try {
                opc = sc.nextInt();
                if (opc <= pagos.size() && opc > 0) {
                    metodo = pagos.get(opc - 1);
                } else {
                    System.out.println("****** Por favor digite un número valido *******");
                }
            } catch (java.util.InputMismatchException e) {
                opc = -1;
                System.out.println("****** Por favor digite un número valido *******");
            }
        } while (opc <= 0 || opc > pagos.size());

        return metodo;
    }

    private Cliente select_client() {
        Cliente cliente_venta = null;
        ArrayList<Cliente> clientes = productDAO.findAllClients();
        int opc;
        do {
            System.out.print("*******************Añadir cliente a Venta*****************");
            int contador = 0;
            for (Cliente cliente : clientes) {
                contador += 1;
                System.out.print("\n\t" + contador + ". " + cliente.getNombre());
            }
            System.out.print("\n\nSeleccione el cliente al cual realizara la venta, selecciione 0 para finalizar el programa\n\t> ");
            Scanner sc = new Scanner(System.in);
            try {
                opc = sc.nextInt();
                if (opc == 0) {
                    System.exit(0);
                }
                if (opc <= clientes.size() && opc > 0) {
                    cliente_venta = clientes.get(opc - 1);
                } else {
                    System.out.println("****** Por favor digite un número valido *******");
                }
            } catch (java.util.InputMismatchException e) {
                opc = -1;
                System.out.println("****** Por favor digite un número valido *******");
            }
        } while (opc < 0 || opc > clientes.size());

        return cliente_venta;
    }

    private ArrayList<Articulo> create_canasta() {
        ArrayList<Articulo> canasta = new ArrayList<>();
        int opc;
        do {
            System.out.print("*******************Añadir a Canasta*****************");
            ArrayList<Inventario> inventarios = productDAO.findAllInventarios();
            for (Inventario invent : inventarios) {
                if (invent.getCantidad() > 0) {
                    System.out.print("\n\t" + invent.getArticulo().getId() + ". " + invent.getArticulo().getProducto() + ".......................... $" + invent.getArticulo().getPrecio());
                }
            }
            System.out.print("\n\nSeleccione el producto que desea agregar a la canasta, presione 0 para terminar\n\t> ");
            Scanner sc = new Scanner(System.in);
            try {
                opc = sc.nextInt();
                if (opc <= inventarios.size() && opc > 0) {
                    if (inventarios.get(opc - 1).getCantidad() > 0) {
                        canasta.add(inventarios.get(opc - 1).getArticulo());
                        inventarios.get(opc - 1).setCantidad(inventarios.get(opc - 1).getCantidad() - 1);
                    }
                } else if (opc == 0) {
                    System.out.println("************ Productos añadidos satisfactoriamente a la Canasta!!! ************");
                } else {
                    System.out.println("****** Por favor digite un número valido *******");
                }
            } catch (java.util.InputMismatchException e) {
                opc = -1;
                System.out.println("****** Por favor digite un número valido *******");
            }

        } while (opc != 0);

        return canasta;
    }

    private Cliente Consulta_cliente() {
        Cliente cliente_venta = null;
        ArrayList<Cliente> clientes = productDAO.findAllClients();
        System.out.print("\n\n*******************LISTA DE CLIENTES ACTUALES*****************");
        int contador = 0;
        for (Cliente cliente : clientes) {

            contador += 1;
            System.out.print("\n\t" + contador + ". " + cliente.getNombre());
        }
        return cliente_venta;
    }

    private ArrayList<Inventario> Consulta_producto() {

        ArrayList<Inventario> inventarios = productDAO.findAllInventarios();
        System.out.print("\n\n*******************LISTA DE PRODUCTOS ACTUALES*****************");
        for (Inventario invent : inventarios) {

            System.out.print("\n\t" + invent.getArticulo().getId() + ". " + invent.getArticulo().getProducto() + ".......................... $" + invent.getArticulo().getPrecio());
        }
        return inventarios;
    }

    private void Consulta_mejor_cliente() {

        try {
            Cantidadcompras cantidad = productDAO.Mejor_Cliente();
            System.out.print("\n\n*******************MEJOR CLIENTE*****************");
            System.out.print("\n\t" + "EL MEJOR CLIENTE ES: " + cantidad.getCliente().getNombre() + " SU CANTIDAD DE COMPRAS ES: " + cantidad.getCantidadCompras());
            System.in.read();
//return clientes;   
        } catch (IOException ex) {
            Logger.getLogger(FactoryMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

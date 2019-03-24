/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymethod.dao;

import almacen.Articulo;
import almacen.Cantidadcompras;
import almacen.Cliente;
import almacen.Inventario;
import almacen.Pago;
import almacen.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import factorymethod.IDBAdapter;
import factorymethod.DBFactory;
import factorymethod.DBType;


/**
 *
 * @author Wilgen Clase para el acceso a los datos, la cual utilizará al Factory
 * para obtener la conexiones a BD
 */
public class ProductDAO {

    private IDBAdapter dbAdapter;
    
    public ProductDAO(){
        DBType tipo = null;
        System.out.println("Se usara la base de datos de Postgres");
        tipo = tipo.Postgres;
        dbAdapter = DBFactory.getDBAdapter(tipo);
    }

    public ArrayList<Articulo> findAllProducts() {
        Connection connection = dbAdapter.getConnection();
        ArrayList<Articulo> productList = new ArrayList<>();
        try {
            //dependiendo de la base de datos activar una. 

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"Producto\""); //--> postgres
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                productList.add(new Articulo(results.getInt(1), results.getString(2), results.getInt(3)));
            }

            return productList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Articulo findProduct(int id_producto) {
        Connection connection = dbAdapter.getConnection();
        Articulo articulo = null;
        try {
            //dependiendo de la base de datos activar una. 

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"Producto\" WHERE id_producto = ?"); //--> postgres
            statement.setLong(1, id_producto);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                articulo = new Articulo(results.getInt(1), results.getString(2), results.getInt(3));
            }

            return articulo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveSale(Venta venta) {
        Connection connection = dbAdapter.getConnection();
        int continuar = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO \"Compra\" (id_compra, id_cliente, id_tipoPago, total_pagar) VALUES (?,?,?,?)");

            statement.setInt(1, venta.getId_venta());
            statement.setString(2, venta.getCliente().getId());
            statement.setInt(3, venta.getPago().getId_pago());
            statement.setInt(4, (int) venta.total_pagar());
            statement.executeUpdate();
            continuar = 1;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (continuar == 1) {
            saveCanasta(venta.getId_venta(), venta.getCanasta());
        }

    }

    public int findLast_id_compra() {
        Connection connection = dbAdapter.getConnection();
        int lastid_compra = 0;
        try {
            //dependiendo de la base de datos activar una. 

            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM \"Compra\""); //--> postgres
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                lastid_compra = results.getInt(1);
            }

            return lastid_compra;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveCanasta(int id_compra, ArrayList<Articulo> articulos) {
        Connection connection = dbAdapter.getConnection();

        try {
            for (Articulo art : articulos) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO \"Canasta\" (id_compra,"
                        + "id_producto) VALUES (?,?)");

                statement.setInt(1, id_compra);
                statement.setInt(2, art.getId());
                statement.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<Cliente> findAllClients() {
        Connection connection = dbAdapter.getConnection();
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            //dependiendo de la base de datos activar una. 

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"Cliente\""); //--> postgres
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                clientes.add(new Cliente(results.getString(1), results.getString(2), results.getString(4)));
            }

            return clientes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Cliente findClient(String id_cliente) {
        Connection connection = dbAdapter.getConnection();
        Cliente cliente = null;
        try {
            //dependiendo de la base de datos activar una. 

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"Cliente\" WHERE id_cliente = ?"); //--> postgres
            statement.setString(1, id_cliente);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                cliente = new Cliente(results.getString(1), results.getString(2), results.getString(5));
            }

            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Pago> findAllPagos() {
        Connection connection = dbAdapter.getConnection();
        ArrayList<Pago> pagos = new ArrayList<>();
        try {
            //dependiendo de la base de datos activar una. 

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"Tipo_Pago\""); //--> postgres
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                pagos.add(new Pago(results.getInt(1), results.getString(2), results.getInt(3)));
            }

            return pagos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Inventario> findAllInventarios() {
        Connection connection = dbAdapter.getConnection();
        ArrayList<Inventario> inventarios = new ArrayList<>();
        try {
            //dependiendo de la base de datos activar una. 

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"Producto\""); //--> postgres
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                inventarios.add(new Inventario(new Articulo(results.getInt(1), results.getString(2), results.getInt(3)), results.getInt(4)));
            }
            return inventarios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    
    public ArrayList<Cliente> Clientes_Compra() {
        Connection connection = dbAdapter.getConnection();
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            //dependiendo de la base de datos activar una. 

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"Cliente\""); //--> postgres
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                clientes.add(new Cliente(results.getString(1), results.getString(2), results.getString(5)));
            }

            return clientes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public Cantidadcompras Mejor_Cliente() {
        Connection connection = dbAdapter.getConnection();
        Cantidadcompras cantidad = null;
        try {
            //dependiendo de la base de datos activar una. 

            //PreparedStatement statement = connection.prepareStatement("SELECT * FROM (SELECT ID_CLIENTE, COUNT(ID_COMPRA) FROM \"Compra\" GROUP BY ID_CLIENTE ORDER BY COUNT(ID_COMPRA) DESC) AS alias1 WHERE top <= 1"); //--> oracle
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM (SELECT ID_CLIENTE, COUNT(ID_COMPRA) FROM \"Compra\" GROUP BY ID_CLIENTE ORDER BY COUNT(ID_COMPRA) DESC) AS alias1 limit 1");
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                cantidad = new Cantidadcompras(findClient(results.getString(1)), results.getInt(2));
            }
            return cantidad;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
}

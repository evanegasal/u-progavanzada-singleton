/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymethod.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
//import com.mysql.jdbc.Driver; // Dependiendo de que base de datos voy a conectar uso en Driver
import factorymethod.IDBAdapter; // Se referencia la Interface que se va a implementar
import factorymethod.util.PropertiesUtil; //Clase de utileria para llegar a los archivos de propiedades


/**
 *
 * @author Wilgen
 */


public class MySQLDBAdapter implements IDBAdapter {

   //Archivo que contiene las propiedades de conexión
    private static final String DB_PROPERTIES = "DBMySQL.properties";
   
    
    // Caracteristicas de la conexión
    private static final String DB_NAME_PROP ="dbname";
    private static final String DB_HOST_PROP ="host";
    private static final String DB_PASSWORD_PROP ="password";
    private static final String DB_PORT_PROP ="port";
    private static final String DB_USER_PROP ="user";
    
    /*Creación de un bloque estatico para asegurar que del driver JDBC de MySQL
      sea registrado antes que el metodo getConnection sea ejecutado
    */
    static {
     //Bloque para registrar el Driver de MySQL
     try{
        //new com.mysql.jdbc.Driver();
     }catch(Exception e){
         e.printStackTrace();         
     }   
    }
    
        
    @Override
    public Connection getConnection() {
        try {
        String connectionString = createConnectionString();
        Connection connection = DriverManager.getConnection(connectionString);
        System.out.println("Connection class ===>"+connection.getClass().getName());
        return connection;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }         
    }
    
    
    private String createConnectionString(){
        
        Properties prop = PropertiesUtil.loadProperty(DB_PROPERTIES);
        String host = prop.getProperty(DB_HOST_PROP);
        String port = prop.getProperty(DB_PORT_PROP);
        String db = prop.getProperty(DB_NAME_PROP);
        String user = prop.getProperty(DB_USER_PROP);
        String password = prop.getProperty(DB_PASSWORD_PROP);
        
        String connectionString = "jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password"+password;
        System.out.println("ConnectionString ===>"+ connectionString);
        return connectionString;       
     }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymethod.impl;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.Statement;
import java.util.Properties;
import oracle.jdbc.OracleDriver; // Dependiendo de que base de datos voy a conectar uso en Driver Oracle en este caso
import factorymethod.IDBAdapter; // Se referencia la Interface que se va a implementar
import factorymethod.util.PropertiesUtil; //Clase de utileria para llegar a los archivos de propiedades


/**
 *
 * @author Wilgen
 */


public class OracleDBAdapter implements IDBAdapter{

    //Archivo que contiene las propiedades de conexión
    private static final String DB_PROPERTIES = "DBOracle.properties";
    
    // Caracteristicas de la conexión
    private static final String DB_SERVICE_PROP ="service";
    private static final String DB_HOST_PROP ="host";
    private static final String DB_PASSWORD_PROP ="password";
    private static final String DB_PORT_PROP ="port";
    private static final String DB_USER_PROP ="user";
    
    /*Creación de un bloque estatico para asegurar que del driver JDBC de Oracle 
      sea registrado antes que el metodo getConnection sea ejecutado
    */
    static {
        // Bloque para registrar el drive de Oracle
        try{
            new OracleDriver();            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public Connection getConnection() {
       try {
          Properties prop = PropertiesUtil.loadProperty(DB_PROPERTIES); 
           
          String connectionString = createConnectionString();
          String user = prop.getProperty(DB_USER_PROP);
          String password = prop.getProperty(DB_PASSWORD_PROP);
          Connection connection = DriverManager.getConnection(connectionString,user,password);
         //System.out.println("Connection clas ===>" + connection.getClass().getName());
          return connection;
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }    
    }
    
    private String createConnectionString(){
        // Se sacan las propiedades del archivo DBOracle.properties
        Properties prop = PropertiesUtil.loadProperty(DB_PROPERTIES);
        String host = prop.getProperty(DB_HOST_PROP);
        String port = prop.getProperty(DB_PORT_PROP);
        String service = prop.getProperty(DB_SERVICE_PROP);
        //String user = prop.getProperty(DB_USER_PROP);
        //String password = prop.getProperty(DB_PASSWORD_PROP);
        
        String connectionString = "jdbc:oracle:thin:@"+host+":"+port+":"+service;                                   
        //System.out.println("ConnectionString===>"+connectionString);
        return connectionString;        
    }    
}

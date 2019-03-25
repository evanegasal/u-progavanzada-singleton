/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymethod;

import java.util.Properties;
import factorymethod.impl.MySQLDBAdapter;
import factorymethod.impl.OracleDBAdapter;
import factorymethod.impl.PostgresDBAdapter;
import factorymethod.util.PropertiesUtil;


/**
 * Representa en ConcreteFactory se usa para fabricar los adaptadores de conexión
 * @author Wilgen
 */
public class DBFactory {
    
    private static final String DB_FACTORY_PROPERTY_URL ="DBFactory.properties";
    private static final String DEFAULT_DB_CLASS_PROP = "defaultDBClass";
    
    public static IDBAdapter getDBAdapter(DBType dbType){
        switch (dbType){
            case MySQL: 
                 return new MySQLDBAdapter();
            case Oracle:
                 return new OracleDBAdapter();
            case Postgres:
                 return new PostgresDBAdapter();     
            default:
               throw new IllegalArgumentException("No soportado");        
        }
    }

    public static IDBAdapter getDefaultDBAdapter(){
        try{
           Properties prop = PropertiesUtil.loadProperty(DB_FACTORY_PROPERTY_URL);
           String defaultDBClass = prop.getProperty(DEFAULT_DB_CLASS_PROP);
           System.out.println("DefaultDBAdapter ===> "+defaultDBClass);
           return (IDBAdapter) Class.forName(defaultDBClass).newInstance();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymethod.dao;

import factorymethod.DBFactory;
import factorymethod.DBType;
import factorymethod.IDBAdapter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author joaquin
 */
public class ConfigDAO {

    private IDBAdapter dbAdapter;
    
    
    public ConfigDAO() {
        dbAdapter = DBFactory.getDBAdapter(DBType.Postgres);
    }

    public void findPropertiesConfiguration(String CONFIGURATION_PROP, String[] DB_VARIABLES_PROP ){
        Connection connection = dbAdapter.getConnection();
        Properties prop = new Properties();
        
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM  \"configuration\"");
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                //productList.add(new Product(results.getLong(1), results.getString(2),results.getDouble(3)));
                prop.setProperty(DB_VARIABLES_PROP[0], results.getString(1));
                prop.setProperty(DB_VARIABLES_PROP[1], results.getString(2));
                prop.setProperty(DB_VARIABLES_PROP[2], results.getString(3));
                prop.setProperty(DB_VARIABLES_PROP[3], results.getString(4));
                prop.setProperty(DB_VARIABLES_PROP[5], results.getString(5));
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
        try {
            prop.store(new FileOutputStream("/Users/joaquin/Downloads/FactoryMethod-2/src/"+CONFIGURATION_PROP), null);
        } catch (Exception ex) {
            Logger.getLogger(ConfigDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
}

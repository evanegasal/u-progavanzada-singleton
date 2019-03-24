/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import factorymethod.dao.ConfigDAO;
import java.util.ArrayList;
import java.util.Properties;
import singleton.util.PropertiesUtil;
/**
 *
 * @author Wilgen
 */
public class ConfigurationSingleton {
    
    private static ConfigurationSingleton singleton;
    
    private static final String CONFIGURATION_PROP ="DBPostgres.properties";
    private static final String[] DB_VARIABLES_PROP = {"host","port","dbname","user","password"};

    
    private String host;
    private String port;
    private String db;
    private String user;
    private String password;
    
    private ConfigurationSingleton(){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.findPropertiesConfiguration(CONFIGURATION_PROP, DB_VARIABLES_PROP);
        
        Properties prop = PropertiesUtil.loadProperty(CONFIGURATION_PROP);
        this.host = prop.getProperty(DB_VARIABLES_PROP[0]);
        this.port = prop.getProperty(DB_VARIABLES_PROP[1]);
        this.db = prop.getProperty(DB_VARIABLES_PROP[1]);
        this.user = prop.getProperty(DB_VARIABLES_PROP[1]);
        this.password = prop.getProperty(DB_VARIABLES_PROP[1]);


    }
    
    private static synchronized void createInstance (){
        if (singleton==null){
            singleton = new ConfigurationSingleton();
        }
    }

    
    public static ConfigurationSingleton getInstance(){
        if (singleton==null){
            createInstance();
        }
        return singleton;
    }

    public static ConfigurationSingleton getSingleton() {
        return singleton;
    }

    public static void setSingleton(ConfigurationSingleton singleton) {
        ConfigurationSingleton.singleton = singleton;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import factorymethod.FactoryMain;
import factorymethod.dao.ConfigDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wilgen
 */
public class SingletonMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ConfigurationSingleton singletonA = ConfigurationSingleton.getInstance();
        /*ConfigurationSingleton singletonB = ConfigurationSingleton.getInstance();
            ConfigurationSingleton singletonC = ConfigurationSingleton.getInstance();
            
            System.out.println(singletonA);
            System.out.println(singletonB);
            
            System.out.println(singletonA.getAppVersion());
            System.out.println(singletonB.getAppVersion());
            
            System.out.println("Misma referencia ===> " + (singletonA == singletonB));
            
            singletonA.setAppName("Singleton Pattern");
            singletonA.setAppVersion("1.0x");
            
            
            singletonC.setAppName("Singleton Pattern");
            singletonC.setAppVersion("20000.0x");
            
            System.out.println("SingletonA ===> " + singletonA);
            System.out.println("SingletonB ===> " + singletonB);
            
            singletonA = null;
            singletonB = null;
            
            singletonA = ConfigurationSingleton.getInstance();
            System.out.println("SingletonA ===> " + singletonA.getAppVersion());
            singletonB = ConfigurationSingleton.getInstance();
            System.out.println("Singletonb ===> " + singletonB.getAppVersion());
            System.out.println("SingletonA ===> " + singletonA.getAppVersion());*/
        FactoryMain factory = new FactoryMain();
        try {
            factory.factory_start();
        } catch (SQLException ex) {
            Logger.getLogger(SingletonMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SingletonMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

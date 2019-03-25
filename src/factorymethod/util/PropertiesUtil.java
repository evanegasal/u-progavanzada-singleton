/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymethod.util;


import java.io.InputStream;
import java.util.Properties;
/**
 *
 * @author Wilgen
 */
public class PropertiesUtil {
  public static Properties loadProperty(String propertiesURL ){
      try{
          Properties properties = new Properties();
          InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesURL);
          properties.load(inputStream);
          return properties;
      }catch (Exception e){
          e.printStackTrace();
          return null;
      }
  }  
}

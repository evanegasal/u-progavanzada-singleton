/*
 *interface define la estructura de los productos que podrá crear el Factory, en este
 * caso habrá dos clases concretas, una para MySQL y otra para Oracle
 */
package factorymethod;

import java.sql.Connection;

/**
 *
 * @author Wilgen
 */

public interface IDBAdapter {
    public Connection  getConnection();    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymethod.entity;

/**
 * Representa los registros de Productos que se llevarán a la BD
 * @author Wilgen
 */
public class Product {
    private Long idProduct;
    private  String productName;
    private double price;
    
    public Product(Long idProduct, String productName, double price){
        this.idProduct = idProduct;
        this.productName = productName;
        this.price = price;        
    }
    
    // Hacer Get and Set

    public Long getIdProduct() {
        return idProduct;
    }
    
    public void setIdProduct(Long idProduct){
        this.idProduct = idProduct;
    }
    
    public String getProductName(){
        return productName;
    }
    
    public void setProductName(String productName){
        this.productName = productName;
    }
    
    public Double getPrice(){
        return price;
    }
    
    public void setPrice(Double price){
        this.price = price;
    }    
}

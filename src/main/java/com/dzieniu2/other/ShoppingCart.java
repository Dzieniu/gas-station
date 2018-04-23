package com.dzieniu2.other;

import com.dzieniu2.entity.Product;

import java.util.HashMap;

public class ShoppingCart {

    private HashMap<Product, Integer> products;

    double totalPrice;

    public ShoppingCart(){
        products = new HashMap<>();
        totalPrice = 0.00;
    }

    public void add(Product product){
        if(products.containsKey(product)){
            products.replace(product,products.get(product)+1);
        }else{
            products.put(product,1);
        }
        calculatePrice();
    }

    public void setNumberOfProduct(Product product, int number){
        if(products.containsKey(product)){
            products.replace(product,number);
        }else{
            products.put(product,number);
        }
        calculatePrice();
    }

    public void remove(Product product){
        if(products.containsKey(product)){
            if(products.get(product)==1){
                products.remove(product);
            }else{
                products.replace(product,products.get(product)-1);
            }
        }
        calculatePrice();
    }

    public void removeAll(){
        products.clear();
        calculatePrice();
    }

    private void calculatePrice(){
        totalPrice = 0.00;
        products.forEach((product,value) -> {
            totalPrice += product.getPrice()*value;
        });
    }

    public double getPrice(){
        return totalPrice;
    }

    public void printCart(){
        products.forEach((x,y) -> System.out.println(x+", "+y));
    }
}

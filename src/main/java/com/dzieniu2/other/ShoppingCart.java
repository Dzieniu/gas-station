package com.dzieniu2.other;

import com.dzieniu2.controller.employee.ProductContainerController;

import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<ProductContainerController> products;

    double totalPrice;

    public ShoppingCart(){
        products = new ArrayList<>();
        totalPrice = 0.00;
    }

    public void add(ProductContainerController product){
        if(products.contains(product)){
            products.get(products.indexOf(product)).add();
        }else{
            products.add(product);
        }
    }
}

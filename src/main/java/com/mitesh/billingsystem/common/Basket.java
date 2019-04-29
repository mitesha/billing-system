package com.mitesh.billingsystem.common;

import java.util.HashMap;
import java.util.Map;

public class Basket {
	
	/**
	 * Map of product with number of items
	 */
    Map<Product, Integer> items;    

    public Basket() {
        items = new HashMap<>();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void addProduct(Product product){
        if(items.containsKey(product)){
            items.compute(product, (p,q ) -> Integer.valueOf(q+1));
        }else{
            items.put(product, 1);
        }
    }

    public void removeProduct(Product product){
        if(items.get(product).intValue() > 1){
            items.compute(product, (p, q) -> Integer.valueOf(q-1));
        }else{
            items.remove(product);
        }
    }

}

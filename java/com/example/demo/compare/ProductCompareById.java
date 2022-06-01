package com.example.demo.compare;

import java.util.Comparator;

import com.example.demo.entity.Product;

public class ProductCompareById implements Comparator<Product> {

    public int compare(Product p1, Product p2) {
        int id1 = p1.getProductId();
        int id2 = p2.getProductId();
        
        if (id1 > id2) {
            return 1;
        } else if (id1 == id2) {
            return 0;
        } else {
            return -1;
        }
    }

}

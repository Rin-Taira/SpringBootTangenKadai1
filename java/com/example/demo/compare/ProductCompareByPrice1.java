package com.example.demo.compare;

import java.util.Comparator;

import com.example.demo.entity.Product;

public class ProductCompareByPrice1 implements Comparator<Product> {

    public int compare(Product p1, Product p2) {
        int pr1 = p1.getPrice();
        int pr2 = p2.getPrice();
        
        if (pr1 > pr2) {
            return 1;
        } else if (pr1 == pr2) {
            return 0;
        } else {
            return -1;
        }
    }

}

package com.example.demo.compare;

import java.util.Comparator;
import java.util.Date;

import com.example.demo.entity.Product;

public class ProductCompareByDate2 implements Comparator<Product> {

    public int compare(Product p1, Product p2) {
        Date date1 = p1.getCreatedDate();
        Date date2 = p2.getCreatedDate();
        
        if (date1 == null || date2 == null ||date1.compareTo(date2) == 1) {
            return -1;
        } else if (date1.compareTo(date2) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

}
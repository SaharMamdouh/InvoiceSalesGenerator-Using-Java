/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.models;

/**
 *
 * @author HP
 */
public class InvoiceLine {
    private String item;
    private int count;
    private double price;
    InvoiceHeader invHeader;

    public InvoiceLine() {
    }

    public InvoiceLine(String item, double price, int count,InvoiceHeader invHeader) {
        this.item = item;
        this.count = count;
        this.price = price;
        this.invHeader=invHeader;
    }
    
    
    public InvoiceHeader getinvHeader(){
    return invHeader;
    }
    
    public void setinvHeader(InvoiceHeader invHeader){
    this.invHeader=invHeader;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public double CalcLinetotal(){
    
        return count*price;
    }

    @Override
    public String toString() {
        return  invHeader.getInvoiceNum()+ "," + item + "," + price + "," + count  ;
    }
    
    
}

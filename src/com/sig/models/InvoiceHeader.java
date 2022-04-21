/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.models;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
/**
 *
 * @author HP
 */
public class InvoiceHeader {
    private int invoiceNum;
    private String CustomerName;
    private Date invoiceDate;
    private ArrayList<InvoiceLine> lines;
    private DateFormat date=new SimpleDateFormat("dd-MM-yyyy");

    public InvoiceHeader() {
    }

    public InvoiceHeader(int invoiceNum, String CustomerName, Date invoiceDate) {
        this.invoiceNum = invoiceNum;
        this.CustomerName = CustomerName;
        this.invoiceDate = invoiceDate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }
    
    public ArrayList<InvoiceLine> getLines() {
        if(lines==null){
           lines=new ArrayList<>();
        }
        return lines;
    }
     public double sumTotalOfLines(){
        double total=0.0;
        for(int i=0;i<getLines().size();i++){
        total += getLines().get(i).CalcLinetotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return invoiceNum + "," + date.format(invoiceDate) + "," + CustomerName  ;
    }
    
}

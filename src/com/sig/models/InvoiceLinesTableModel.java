/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.models;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */
public class InvoiceLinesTableModel extends AbstractTableModel{
    private ArrayList<InvoiceLine> invoiceLineArrays;
    private String[] columns={"Item","Price","Count","total"};
    public InvoiceLinesTableModel(ArrayList<InvoiceLine> invoiceLineArrays) {
        this.invoiceLineArrays = invoiceLineArrays;
    }
    
    @Override
    public int getRowCount() {
       return invoiceLineArrays==null ? 0 : invoiceLineArrays.size(); //return all rows in the invoiceline array
    }

    @Override
    public int getColumnCount() {
        return columns.length;        //return all columns of the invoice line array
    }

     @Override
    public Object getValueAt(int rowIndex, int columnIndex) {   //return content of the invoice array
        InvoiceLine inv=invoiceLineArrays.get(rowIndex);
        switch(columnIndex){
            case 0: return inv.getItem();
            case 1:return inv.getPrice();
            case 2:return inv.getCount();
            case 3: return inv.CalcLinetotal();
            
        }
        return "";
    }
    
    @Override
    public String getColumnName(int column) {                //return columns name
        return columns[column];
    }
    
}

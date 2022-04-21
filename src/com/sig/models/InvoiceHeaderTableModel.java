/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.models;

import com.sig.views.InvoiceForm;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */
public class InvoiceHeaderTableModel extends AbstractTableModel{
    private ArrayList<InvoiceHeader> invoicesArray;
    private String[] columns={"Invoice ID","Invoice Date","Customer Name","Total Cost"};

    public InvoiceHeaderTableModel(ArrayList<InvoiceHeader> invoicesArray) {
        this.invoicesArray = invoicesArray;
    }
    

    @Override
    public int getRowCount() {
        return invoicesArray.size();
    }

    @Override
    public int getColumnCount() {
       return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader inv=invoicesArray.get(rowIndex);
        switch(columnIndex){
            case 0: return inv.getInvoiceNum();
            case 1: return InvoiceForm.date.format(inv.getInvoiceDate());
            case 2:return inv.getCustomerName();
            case 3: return inv.sumTotalOfLines();
        }
        return "";
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public ArrayList<InvoiceHeader> getInvoicesArray() {
        return invoicesArray;
    }
    
}

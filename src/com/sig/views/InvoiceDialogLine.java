/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.views;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class InvoiceDialogLine extends JDialog{
    //declaration of all fields 
    private JTextField productName;
    private JTextField productPrice;
    private JTextField productCount;
    private JLabel productNamelbl;
    private JLabel productPricelbl;
    private JLabel productCountbl;
    private JButton OKbtn;
    private JButton cancelbtn;
    
    
    
    public InvoiceDialogLine(InvoiceForm form){
        
        productNamelbl=new JLabel("Item");
        productName=new JTextField(15);
        
        productPricelbl=new JLabel("Price");
        productPrice=new JTextField(15);
        
        productCountbl=new JLabel("Count");
        productCount=new JTextField(15);
        
        OKbtn=new JButton("OK");
        cancelbtn=new JButton("Cancel");
        
        //insert action commands for the buttons
        OKbtn.setActionCommand("insertNewLineOk");
        cancelbtn.setActionCommand("cancelNewLine");
        
        //apply action listener for both buttons
        OKbtn.addActionListener(form.getActionListener());
        cancelbtn.addActionListener(form.getActionListener());
        
        add(productNamelbl);
        add(productName);
        add(productPricelbl);
        add(productPrice);
        add(productCountbl);
        add(productCount);
        add(OKbtn);
        add(cancelbtn);
        setLayout(new GridLayout(4,3));
        setLocation(200,200);
         pack();
    }

    public JTextField getProductName() {
        return productName;
    }

    public JTextField getProductPrice() {
        return productPrice;
    }

    public JTextField getProductCount() {
        return productCount;
    }
    
    
    
}

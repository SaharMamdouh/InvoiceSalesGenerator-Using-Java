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
public class InvoiceDialogHeader extends JDialog{
    private JTextField customerNameTF;
    private JTextField invoiceDateTF;
    private JLabel customerlabel;
    private JLabel invDateLabel;
    private JButton OK_button;
    private JButton Cancel_button;
    
    public InvoiceDialogHeader(InvoiceForm form){
        customerlabel = new JLabel("Customer Name");
        customerNameTF=new JTextField(20);
        invDateLabel = new JLabel("Invoice Date");
        invoiceDateTF=new JTextField(20);
        OK_button = new JButton("OK");
        Cancel_button = new JButton("Cancel");
        
        // insert action commands for both buttons to apply methods for them in action listener
        OK_button.setActionCommand("applyNewInv");
        Cancel_button.setActionCommand("cancelNewInv");
        
        // add action listener on both buttons 
         OK_button.addActionListener(form.getActionListener());
         Cancel_button.addActionListener(form.getActionListener());
         
         //add all fields to be displayed in the frame
         add(customerlabel);
         add(customerNameTF);
         add(invDateLabel);
         add(invoiceDateTF);
         add(OK_button);
         add(Cancel_button);
         setLayout(new GridLayout(3,2));
         pack();
         
        
    }

    public JTextField getCustomerNameTF() {
        return customerNameTF;
    }

    public JTextField getInvoiceDateTF() {
        return invoiceDateTF;
    }

    public JLabel getCustomerlabel() {
        return customerlabel;
    }

    public JLabel getInvDateLabel() {
        return invDateLabel;
    }

    public JButton getOK_button() {
        return OK_button;
    }

    public JButton getCancel_button() {
        return Cancel_button;
    }
    
    
}

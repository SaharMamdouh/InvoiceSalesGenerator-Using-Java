/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.controller;

import com.sig.models.InvoiceHeader;
import com.sig.models.InvoiceHeaderTableModel;
import com.sig.models.InvoiceLine;
import com.sig.models.InvoiceLinesTableModel;
import com.sig.views.InvoiceDialogHeader;
import com.sig.views.InvoiceDialogLine;
import com.sig.views.InvoiceForm;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileWriter;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class InvoiceListener implements ActionListener{
    private InvoiceDialogHeader dialogHeader;
    private InvoiceDialogLine dialogLine;
    private InvoiceForm form;
   
    
    public InvoiceListener(InvoiceForm form){
        this.form=form;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
      switch ( e.getActionCommand()){
      
          case "Load File":
              loadFile();
              break;
              
          case "Save File":
              saveFile();
              break;
              
          case "Create New Invoice":
              createInvoice();
              break;   
          
          case "Delete Invoice":
              deleteInvoice();
              break;
              
          case "Save":
              saveNewLine();
              break;
              
          case "Cancel":
              deleteLine();
              break;   
              
          case "applyNewInv" :
              OkNewInvDialog();
              break;
              
          case "cancelNewInv" :
              cancelNewInvDialog();
              break;
              
          case "insertNewLineOk" :
              insertNewLineDialog();
              break;
          
           case "cancelNewLine" :
              cancelNewLineDialog();
              break;
      }
          
    }
    private void loadFile(){
        JFileChooser file =new JFileChooser();
       try{ 
        int result=file.showOpenDialog(form);
         ArrayList<InvoiceHeader> invoiceHeaders=new ArrayList<>();  //create an empty array of invoice headers to fill it when reading data from the file
        if(result==JFileChooser.APPROVE_OPTION){
        File headerFile= file.getSelectedFile();
//        FileReader FileRead=new FileReader(headerFile);
//        BufferedReader bufferReader=new BufferedReader(FileRead);
        Path headerPath= Paths.get(headerFile.getAbsolutePath());
        List <String> headerLines =Files.readAllLines(headerPath);
        for(String headerLine :headerLines){
           String[] segment=headerLine.split(",");
           String seg1=segment[0];
           String seg2=segment[1];
           String seg3=segment[2];
           int invCode=Integer.parseInt(seg1); //convert invoice id from string to integer
           Date invoiceDate=InvoiceForm.date.parse(seg2);  //convert the string of the date into dateformat
            
            InvoiceHeader header=new InvoiceHeader(invCode,seg3,invoiceDate); //create object header from class invoiceheader
            invoiceHeaders.add(header);
        }
        form.setInvoicesArray(invoiceHeaders);
        }
        
        result=file.showOpenDialog(form);
        if(result==JFileChooser.APPROVE_OPTION){
            File LineFile=file.getSelectedFile();
            Path LinePath=Paths.get(LineFile.getAbsolutePath());
            List<String> LineLines=Files.readAllLines(LinePath);
            ArrayList<InvoiceLine> invoiceLines=new ArrayList<>();
            for(String LineLine:LineLines){
                String[] LineSegment=LineLine.split(",");
                String lnseg1=LineSegment[0]; //invoice id int
                String lnseg2=LineSegment[1]; //item string
                String lnseg3=LineSegment[2]; //price double
                String lnseg4=LineSegment[3]; //count int
                int invCodee=Integer.parseInt(lnseg1);
                double itemPrice=Double.parseDouble(lnseg3);
                int itemCount=Integer.parseInt(lnseg4);
                InvoiceHeader inv=form.getInvCode(invCodee);
                InvoiceLine invoiceLine=new InvoiceLine(lnseg2,itemPrice,itemCount,inv);
                inv.getLines().add(invoiceLine);
                   
            } 
        InvoiceHeaderTableModel headerTableModel=new InvoiceHeaderTableModel(invoiceHeaders);
        form.setHeaderTableModel(headerTableModel);
        form.getInvoices().setModel(headerTableModel);
        System.out.println("show result");
        ArrayList<InvoiceHeader> invHeaderArray=form.getInvoicesArray();
        ArrayList<InvoiceLine> invLineArray=form.getInvoiceLinesArray();
       // System.out.println(invHeaderArray.toString());
        String invHeaders="";
        String invLines="";
       for(InvoiceHeader invHead : invHeaderArray){
            invHeaders+=invHead.toString();  
            invHeaders+="\n";
            for(InvoiceLine invLine:invHead.getLines()){
            invLines+=invLine.toString();
            invLines+="\n";  //to write the lines sequential
           
            }
           }
        System.out.println("the content of the invoices table ");
        System.out.println(invHeaders);
        System.out.println("the content of the invoicelines table ");
        System.out.println(invLines);
       }}catch(IOException e){
           
           JOptionPane.showMessageDialog(form, e, "Error", JOptionPane.ERROR_MESSAGE);
           
           }catch (ParseException ex) {
               // Logger.getLogger(InvoiceListener.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(form, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    

    private void saveFile() {
       ArrayList<InvoiceHeader> invHeaderArray=form.getInvoicesArray();
       JFileChooser newFile=new JFileChooser();  
      try{ int result=newFile.showOpenDialog(form);    //open the dialog once pressed on the save file button 
       if(result==JFileChooser.APPROVE_OPTION){
           File invoicesFile = newFile.getSelectedFile();  //select the file to store data
           FileWriter headerFileWriter=new FileWriter(invoicesFile);
           String invHeaders="";
           String invLines="";
           // nested for loop to write each row in the invoice table in the new file  
           for(InvoiceHeader invHead : invHeaderArray){
            invHeaders+=invHead.toString();  
            invHeaders+="\n";
            //each invoice has an array of lines so loop on them to write each line of invoices in the new lines file
            for(InvoiceLine invLine:invHead.getLines()){
            invLines+=invLine.toString();
            invLines+="\n";  //to write the lines sequential
            }
           }
           result=newFile.showOpenDialog(form);
           File invoiceLinesFile = newFile.getSelectedFile();
           FileWriter LineFileWriter=new FileWriter(invoiceLinesFile);
           // write all headers in the header file
           headerFileWriter.write(invHeaders);
           LineFileWriter.write(invLines);
           headerFileWriter.close();
           LineFileWriter.close();
           
       }
        }catch(IOException e){
        
          JOptionPane.showMessageDialog(form, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

     private void createInvoice() {
        dialogHeader= new InvoiceDialogHeader(form);
        dialogHeader.setVisible(true);
        
    }
     
    private void deleteInvoice() {
       int selectedRowIndex=form.getInvoices().getSelectedRow();
       if(selectedRowIndex >= 0){
        form.getInvoicesArray().remove(selectedRowIndex);    //to remove the selected line from the invoice table
        form.getHeaderTableModel().fireTableDataChanged();   //redraw the invoice table after removing the line
        form.getInvoiceDetails().setModel(new InvoiceLinesTableModel(null));
        form.setInvoiceLinesArray(null);
        form.getCustomernamelabel().setText("");
        form.getInvouceNolable().setText("");
        form.getInvoiceTotallable().setText("");
        form.getInvoicedatelbl().setText("");
       }
    } 
    
    private void saveNewLine() {
       dialogLine= new InvoiceDialogLine(form);
       dialogLine.setVisible(true);
    }
    
    private void deleteLine() {
        int selectedRowIndex =form.getInvoiceDetails().getSelectedRow();
         int invHeadIndex=form.getInvoices().getSelectedRow();
        if(selectedRowIndex >= 0){
           form.getInvoiceLinesArray().remove(selectedRowIndex);
           form.getLinesTableModel().fireTableDataChanged();      
           form.getInvoiceTotallable().setText(""+form.getInvoicesArray().get(invHeadIndex).sumTotalOfLines());  //update the total cost in the lable above the item details table
           form.getHeaderTableModel().fireTableDataChanged();        
        }
        form.getInvoices().setRowSelectionInterval(invHeadIndex, invHeadIndex);  //to can delete more lines and updating the total cost in parallel withit 
       
    }

    private void cancelNewInvDialog() {
        dialogHeader.setVisible(false);
        dialogHeader.dispose();    //to hide the dialog completely
        dialogHeader=null;       
    } 

    private void OkNewInvDialog() {
        dialogHeader.setVisible(false);
        String customerName=dialogHeader.getCustomerNameTF().getText();
        String strDate=dialogHeader.getInvoiceDateTF().getText();
        Date date=new Date();
        try{
             date = InvoiceForm.date.parse(strDate);
        }catch(Exception e){
            JOptionPane.showMessageDialog(form, strDate, "cannot parse the date", JOptionPane.ERROR_MESSAGE);
        }
        
// finding the last id number to increment it for getting the new id number
      int newID=form.getInvoicesArray().size();
      int Newid= newID+1;
      System.out.println(newID);
        InvoiceHeader inv = new InvoiceHeader(Newid,customerName,date);
        form.getInvoicesArray().add(inv);
        form.getHeaderTableModel().fireTableDataChanged(); //to redraw the table with the new data
        dialogHeader.dispose();
        dialogHeader=null;
    }

    private void insertNewLineDialog() {
        dialogLine.setVisible(false);
        String itemName=dialogLine.getProductName().getText();
        String itemPrice =dialogLine.getProductPrice().getText();
        String itemCount =dialogLine.getProductCount().getText();
        int Count=0;
        double Price=0;
        try{Count=Integer.parseInt(itemCount);
            Price=Double.parseDouble(itemPrice);
        }catch(NumberFormatException e){
        JOptionPane.showMessageDialog(form,"cannot convert number","Invalid Format" ,JOptionPane.ERROR_MESSAGE);
        }
        int selectedRowIndex=form.getInvoices().getSelectedRow();
        if(selectedRowIndex >= 0){
            InvoiceHeader invoiceHead=form.getInvoicesArray().get(selectedRowIndex);
            InvoiceLine invLine = new InvoiceLine(itemName,Price,Count,invoiceHead);
            form.getInvoiceLinesArray().add(invLine);         //adding the new line of item details to the arraylines
            form.getLinesTableModel().fireTableDataChanged(); //redraw the lines table
            form.getHeaderTableModel().fireTableDataChanged(); //to redraw the invoices table with the new data  
        }
         form.getInvoices().setRowSelectionInterval(selectedRowIndex, selectedRowIndex); // to keep the invoice header row selected even after refreshing the tables to add more lines
        
        dialogLine.dispose();    //to hide the dialog completely
        dialogLine=null;  
    }

    private void cancelNewLineDialog() {
        dialogLine.setVisible(false);
        dialogLine.dispose();    //to hide the dialog completely
        dialogLine=null;    
    }

   
    
}

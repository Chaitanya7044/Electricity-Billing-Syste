package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paytm extends JFrame implements ActionListener{

    String meter;
    JButton back;
    Paytm(String meter) {
        this.meter = meter;
        
        JEditorPane j = new JEditorPane();
        j.setEditable(false);
        
        try {
            j.setPage("https://business.paytm.com/retail");
        } catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html>Could not load<html>");
            
        }
        
        JScrollPane pane = new JScrollPane(j);
        add(pane);
        
        back = new JButton("Back");
        back.setBounds(640, 20, 80, 30);
        back.addActionListener(this);
        j.add(back);
        
        setSize(800, 600);
        setLocation(400, 150);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new PayBill(meter);
    }
    
    public static void main(String[] args) {
        new Paytm("");
    }
}


//package electricity.billing.system;
//import java.awt.event.*;
//import javax.swing.*;
//import java.awt.*;
//
//public class Paytm extends JFrame implements ActionListener {
//    
//    String meterNumber;
//    
//    Paytm(String meterNumber){
//        
//        this.meterNumber=meterNumber;
//        
//        setBounds(400,150,800,600);
//        
//        
//        JEditorPane j = new JEditorPane();
//        j.setEditable(false);
//        
//        try{
//            j.setPage("https://paytm.com/online-payments");
//        }
//        catch(Exception e){
//            j.setContentType("text/html");
//            j.setText("<html>Could not load<html>");
//        }
//        
//        JScrollPane pane = new  JScrollPane();
//        add(pane);
//        
//        
//        setVisible(true);
//        
//    }
//    
//    
//    public void actionPerformed(ActionEvent ae){
//        
//    }
//    
//    
//    public static void main(String args[]){
//        new Paytm("");
//    }
//}

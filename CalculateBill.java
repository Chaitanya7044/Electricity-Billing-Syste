package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener {
    
    JTextField name,address,state,units,email,phone;
    JButton next,cancel;
    JLabel randomMeter,labeladdress; 
    Choice meterNumber,cMonth;

    CalculateBill() {
        setSize(700, 500);
        setLocation(400, 200);

        //blue panel
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
//        add(p);

        //-----------LABEL-->New Customer
        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(140, 20, 400, 20);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);

        //name---LABEL
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(100, 80, 120, 20);
        p.add(lblmeter);
        
        
        //choice for meter number
        meterNumber = new Choice();
        //fetch meter numbers from database
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){
                meterNumber.add(rs.getString("meter_no"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        meterNumber.setBackground(Color.WHITE);
        meterNumber.setBounds(240, 80, 250, 20);
        p.add(meterNumber);
        
        
        //----------LABEL---meter number
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100, 120, 105, 20);
        p.add(lblname);
        
        //--actual name
        JLabel name = new JLabel("");
        name.setBounds(240, 120, 150, 20);
        p.add(name);
       
        
        //-------------Address---LABEL
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100, 160, 105, 20);
        p.add(lbladdress);
        
        
        //input of address
        labeladdress = new JLabel("");
        labeladdress.setBounds(240, 160, 220, 20);
        p.add(labeladdress);
        
        //fetch address and name from database
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meterNumber.getSelectedItem()+"'");
            while(rs.next()){
                name.setText(rs.getString("name"));
                labeladdress.setText(rs.getString("address"));
            }
        }catch(Exception e){
            e.printStackTrace(); 
        }
        
         
        meterNumber.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                //fetch address and name from database when meter number is changed
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meterNumber.getSelectedItem()+"'");
                    while(rs.next()){
                        name.setText(rs.getString("name"));
                        labeladdress.setText(rs.getString("address"));
                    }
                }catch(Exception e){
                    e.printStackTrace(); 
                }
            }   
        });
        
       //----------city---LABEL
        JLabel lblcity = new JLabel("Units Consumed");
        lblcity.setBounds(100, 200, 150, 20);
        p.add(lblcity);
        
        
        //input of city
        units = new JTextField();
        units.setBounds(240, 200, 220, 20);
        p.add(units);
        
        //----------state---LABEL
        JLabel lblstate = new JLabel("Month");
        lblstate.setBounds(100, 240, 105, 20);
        p.add(lblstate);
        
        
        //input of state
        cMonth = new Choice();
        cMonth.setBounds(240, 240, 220, 20);
        cMonth.setBackground(Color.WHITE);
        cMonth.add("January");
        cMonth.add("February");
        cMonth.add("March");
        cMonth.add("April");
        cMonth.add("May");
        cMonth.add("June");
        cMonth.add("July");
        cMonth.add("August");
        cMonth.add("September");
        cMonth.add("October");
        cMonth.add("November");
        cMonth.add("December");
        
        p.add(cMonth);

        
        //------Buttons--------
        
        
        //1. Next
        next = new JButton("Submit");
        next.setBounds(120,350,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);
        
        // 2.Cancel
        cancel = new JButton("Cancel");
        cancel.setBounds(250,350,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);
        
        //place panel P in center
        
        setLayout(new BorderLayout());
        
        add(p,"Center");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image,"West");//add image by borderLayout
        
        getContentPane().setBackground(Color.WHITE);
        
        
        setVisible(true);

    }
    
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()== next){
            
            String sMeter = meterNumber.getSelectedItem();
            String sUnits = units.getText();
            String sMonth = cMonth.getSelectedItem();
            
            
            ///--------calculate total bill------
            int totalBill = 0;
            int unitsInInt = Integer.parseInt(sUnits);
            
            String query = "select * from tax";
            
            
            
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()){
                    totalBill+=unitsInInt*Integer.parseInt(rs.getString("cost_per_unit"));
                    totalBill+=Integer.parseInt(rs.getString("meter_rent"));
                    totalBill+=Integer.parseInt(rs.getString("service_charge"));
                    totalBill+=Integer.parseInt(rs.getString("service_tax"));
                    totalBill+=Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    totalBill+=Integer.parseInt(rs.getString("fixed_tax"));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            
            ///-------push bill details into bill table------------
            String query2 = "insert into bill values('"+sMeter+"','"+sMonth+"','"+sUnits+"','"+totalBill+"','Not Paid')";
            
            try{
                
                Conn c = new Conn();
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null,"Cutomer Bill Updated Successfully");
                setVisible(false);
                
            }catch(Exception e){
                e.printStackTrace();
            }
        
        }
        else{
            setVisible(false);
        }
    }

    public static void main(String args[]) {
        new CalculateBill();
    }
}

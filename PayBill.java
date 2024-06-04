package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PayBill extends JFrame implements ActionListener {

    
    Choice cMonth;
    JButton pay,back;
    String meterNumber;
    
    PayBill(String meterNumber) {

        this.meterNumber = meterNumber;
        setBounds(300, 150, 900, 600);
        setLayout(null);

        JLabel heading = new JLabel("Electricity BILL");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(120, 5, 400, 30);
        add(heading);
        
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(35, 80, 200, 20);
        add(lblmeter);

        JLabel meter = new JLabel("");
        meter.setBounds(300, 80, 200, 20);
        add(meter);
        
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35, 140, 200, 20);
        add(lblname);

        JLabel name = new JLabel("");
        name.setBounds(300, 140, 200, 20);
        add(name);
        
        
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(35, 200, 200, 20);
        add(lblmonth);
        
        cMonth = new Choice();
        cMonth.setBounds(300,200,200,20);
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
        add(cMonth);
        
        
        JLabel lblunits = new JLabel("Units");
        lblunits.setBounds(35, 260, 200, 20);
        add(lblunits);
        
        JLabel units = new JLabel("");
        units.setBounds(300, 260, 200, 20);
        add(units);
        
        
        JLabel lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setBounds(35, 320, 200, 20);
        add(lbltotalbill);
        
        JLabel totalbill = new JLabel("");
        totalbill.setBounds(300, 320, 200, 20);
        add(totalbill);
        
        
        JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(35, 380, 200, 20);
        add(lblstatus);
        
        JLabel status = new JLabel("");
        status.setBounds(300, 380, 200, 20);
        status.setForeground(Color.RED);
        add(status);
        
        try{
            Conn c = new Conn();
            //two info from customer table and rest from bill table
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meterNumber+"'");
            while(rs.next()){
                name.setText(rs.getString("name"));
                meter.setText(rs.getString("meter_no"));
            }
            
            rs = c.s.executeQuery("select * from bill where meter_no = '"+meterNumber+"' and month='"+cMonth.getSelectedItem()+"'");
            while(rs.next()){
                status.setText(rs.getString("status"));
                totalbill.setText(rs.getString("totalbill"));
                units.setText(rs.getString("units"));
            }
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //run query again when month is changed
        cMonth.addItemListener(new ItemListener(){
            
            public void itemStateChanged(ItemEvent ie){
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from bill where meter_no = '"+meterNumber+"' and month='"+cMonth.getSelectedItem()+"'");
                    while(rs.next()){
                        status.setText(rs.getString("status"));
                        totalbill.setText(rs.getString("totalbill"));
                        units.setText(rs.getString("units"));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            
        });
        
        
        pay = new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100,460,100,25);
        pay.addActionListener(this);
        add(pay);
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(230,460,100,25);
        back.addActionListener(this);
        add(back);
        
        
        getContentPane().setBackground(Color.WHITE);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,120,600,300);
        add(image);
        
        
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae){
        
        String msg = ae.getActionCommand();
        
        if(msg.equals("Pay")){
            
            try{
                
                Conn c =new Conn();
                c.s.executeQuery("update bill set status='paid' where meter_no = '"+meterNumber+"'and month = '"+cMonth.getSelectedItem()+"'");
            }catch(Exception e){
                e.printStackTrace();
            }
            
            setVisible(false);
            new Paytm(meterNumber);
        }
        else{
            setVisible(false);
        }
    }

    public static void main(String args[]) {
        new PayBill("");
    }
}

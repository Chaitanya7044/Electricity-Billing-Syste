package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class GenerateBill extends JFrame implements ActionListener{
    
    String meterNumber;
    JButton bill;
    Choice cMonth;
    JTextArea area;
    
    GenerateBill(String meterNumber){
        
        this.meterNumber = meterNumber;
        
        setBounds(550,30,500,800);
     
        
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        
        
        JLabel heading = new JLabel("Generate Bill");
        JLabel meter = new JLabel(meterNumber);
        
        cMonth = new Choice();
        cMonth = new Choice();
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
        
        area = new JTextArea(50,15);
        area.setText("\n\n\t   -------Click on the------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month");
        area.setFont(new Font("Senserif",Font.BOLD,15));
        
        JScrollPane pane = new JScrollPane(area);
        
        //----button------
        bill = new JButton("Generate Bill");
        bill.addActionListener(this);
        
        
        panel.add(heading);
        panel.add(meter);
        panel.add(cMonth);
        add(panel,"North");
        
        //text area
        add(pane,"Center");
        
        add(bill,"South");
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        try{
            Conn c = new Conn();
            
            String month = cMonth.getSelectedItem();
            
            area.setText("\tReliance Power Limited\nELECTRICITY BILL GENERATED FOR THE MONTH\n\t OF "+month+",2022\n\n");
            
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meterNumber+"'");
            
            if(rs.next()){
                area.append("\n    Customer Name: "+rs.getString("name"));
                area.append("\n    Meter Number: "+rs.getString("meter_no"));
                area.append("\n    Address: "+rs.getString("address"));
                area.append("\n    City: "+rs.getString("city"));
                area.append("\n    State: "+rs.getString("state"));
                area.append("\n    E-Mail: "+rs.getString("email"));
                area.append("\n    Phone Number: "+rs.getString("phone"));
                area.append("\n----------------------------------------------\n");

            }
            
            rs = c.s.executeQuery("select * from meter_info where meter_no = '"+meterNumber+"'");
            
            if(rs.next()){
                area.append("\n    Meter Location: "+rs.getString("meter_location"));
                area.append("\n    Meter Type: "+rs.getString("meter_type"));
                area.append("\n    Phase Code: "+rs.getString("phase_code"));
                area.append("\n    Bill Type: "+rs.getString("bill_type"));
                area.append("\n    Days: "+rs.getString("days"));
                area.append("\n----------------------------------------------\n");
            }
            
            rs = c.s.executeQuery("select * from tax");
            
            if(rs.next()){
                area.append("\n    Cost Per Unit: "+rs.getString("cost_per_unit"));
                area.append("\n    Meter Rent: "+rs.getString("meter_rent"));
                area.append("\n    Service Charge: "+rs.getString("service_charge"));
                area.append("\n    Service Tax: "+rs.getString("service_tax"));
                area.append("\n    Swacch Bharat Cess: "+rs.getString("swacch_bharat_cess"));
                area.append("\n    Fixed Tax: "+rs.getString("fixed_tax"));
                area.append("\n----------------------------------------------\n");
            }
            
            rs = c.s.executeQuery("select * from bill where meter_no = '"+meterNumber+"' and month = '"+month+"'");
            
            if(rs.next()){
                area.append("\n    Current Month: "+rs.getString("month"));
                area.append("\n    Units Consumed: "+rs.getString("units"));
                area.append("\n    Total Charges: "+rs.getString("totalbill"));
                area.append("\n----------------------------------------------\n");
                area.append("\n    Total Payable: "+rs.getString("totalbill"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]){
        new GenerateBill("");
    }
    
}

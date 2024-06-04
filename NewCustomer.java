package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class NewCustomer extends JFrame implements ActionListener {
    
    JTextField name,address,state,city,email,phone;
    JButton next,cancel;
    JLabel randomMeter; 

    NewCustomer() {
        setSize(700, 500);
        setLocation(400, 200);

        //blue panel
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
//        add(p);

        //-----------LABEL-->New Customer
        JLabel heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 20);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);

        //name---LABEL
        JLabel lblname = new JLabel("Customer Name");
        lblname.setBounds(100, 80, 150, 20);
        p.add(lblname);
        
        
        //input of name 
        name = new JTextField();
        name.setBounds(240, 80, 220, 20);
        p.add(name);
        
        
        //----------LABEL---meter number
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(100, 120, 105, 20);
        p.add(lblmeter);
        
        //add label in front of meter text to keep it non
        //editable as meter no. will be auto generated
        
        randomMeter = new JLabel("");
        randomMeter.setBounds(240, 120, 105, 20);
        p.add(randomMeter);
        
        //random meter number
        Random ran = new Random();
        long number = ran.nextLong()%1000000;
        randomMeter.setText(""+Math.abs(number));
        
        //-------------Address---LABEL
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100, 160, 105, 20);
        p.add(lbladdress);
        
        
        //input of address
        address = new JTextField();
        address.setBounds(240, 160, 220, 20);
        p.add(address);
        
        
       //----------city---LABEL
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(100, 200, 105, 20);
        p.add(lblcity);
        
        
        //input of city
        city = new JTextField();
        city.setBounds(240, 200, 220, 20);
        p.add(city);
        
        //----------state---LABEL
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(100, 240, 105, 20);
        p.add(lblstate);
        
        
        //input of state
        state = new JTextField();
        state.setBounds(240, 240, 220, 20);
        p.add(state);
        
        //----------email---LABEL
        JLabel lblemail = new JLabel("E-Mail");
        lblemail.setBounds(100, 280, 105, 20);
        p.add(lblemail);
        
        
        //input of e-mail
        email = new JTextField();
        email.setBounds(240, 280, 220, 20);
        p.add(email);
        
        //----------phone---LABEL
        JLabel lblphone = new JLabel("Phone Number");
        lblphone.setBounds(100, 320, 105, 20);
        p.add(lblphone);
        
        
        //input of phone
        phone = new JTextField();
        phone.setBounds(240, 320, 220, 20);
        p.add(phone);
        
        //------Buttons--------
        
        
        //1. Next
        next = new JButton("Next");
        next.setBounds(120,390,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);
        
        // 2.Cancel
        cancel = new JButton("Cancel");
        cancel.setBounds(250,390,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);
        
        //place panel P in center
        
        setLayout(new BorderLayout());
        
        add(p,"Center");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image,"West");//add image by borderLayout
        
        getContentPane().setBackground(Color.WHITE);
        
        
        setVisible(true);

    }
    
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()== next){
            
            String sName = name.getText();
            String sMeter = randomMeter.getText();
            String sAddress = address.getText();
            String sCity = city.getText();
            String sState = state.getText();
            String sMail = email.getText();
            String sPhone = phone.getText();
            
            String query1 = "insert into customer values ('"+sName+"','"+sMeter+"','"+sAddress+"','"+sCity+"','"+sState+"','"+sMail+"','"+sPhone+"')";
            String query2 = "insert into login values ('"+sMeter+"','', '"+sName+"','','')";
            
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null,"Customer Details Added Successfully" );
                setVisible(false);
                
                //new frame
                new MeterInfo(sMeter);
                
                
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            setVisible(false);
        }
    }

    public static void main(String args[]) {
        new NewCustomer();
    }
}

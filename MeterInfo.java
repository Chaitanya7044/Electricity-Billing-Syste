package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class MeterInfo extends JFrame implements ActionListener {
    
    JTextField name,address,state,city,email,phone;
    JButton submit,cancel;
    JLabel randomMeter; 
    Choice meterLocation,meterType,phaseCode,billType;
    String meterNumber ;
    
    MeterInfo(String meterNumber) {
        
        this.meterNumber = meterNumber;
        
        setSize(750, 500);
        setLocation(400, 200);

        //blue panel
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
//        add(p);

        //-----------LABEL-->Meter Information
        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 250, 20);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);

        //meter number---LABEL
        JLabel lblname = new JLabel("Meter Number");
        lblname.setBounds(100, 80, 105, 20);
        p.add(lblname);
        
        
        //display meter number
        JLabel lblmeterNumber = new JLabel(meterNumber);
        lblmeterNumber.setBounds(240, 80, 105, 20);
        p.add(lblmeterNumber);
        
        
        //----------LABEL---meter location
        JLabel lblmeter = new JLabel("Meter Location");
        lblmeter.setBounds(100, 120, 120, 20);
        p.add(lblmeter);
        
        //choice for meter location
        meterLocation = new Choice();
        meterLocation.add("Outside");
        meterLocation.add("Inside");
        meterLocation.setBounds(240, 120, 220, 20);        
        meterLocation.setBackground(Color.WHITE);
        p.add(meterLocation);
       
        
        //-------------meter type---LABEL
        JLabel lblmeterType = new JLabel("Meter Type");
        lblmeterType.setBounds(100, 160, 105, 20);
        p.add(lblmeterType);
        
        
        //choice for meter type
        meterType = new Choice();
        meterType.add("Electric Meter");
        meterType.add("Solar Meter");
        meterType.add("Smart Meter");
        meterType.setBounds(240, 160, 220, 20);        
        meterType.setBackground(Color.WHITE);
        p.add(meterType);
        
        
       //----------phase code---LABEL
        JLabel lblphaseCode = new JLabel("Phase Code");
        lblphaseCode.setBounds(100, 200, 105, 20);
        p.add(lblphaseCode);
        
        
        //choice for phase code
        phaseCode = new Choice();
        phaseCode.add("011");
        phaseCode.add("022");
        phaseCode.add("033");
        phaseCode.add("044");
        phaseCode.add("055");
        phaseCode.add("066");
        phaseCode.add("077");
        phaseCode.add("088");
        phaseCode.add("099");
        phaseCode.setBounds(240, 200, 220, 20);        
        phaseCode.setBackground(Color.WHITE);
        p.add(phaseCode);
        
        //----------Bill type---LABEL
        JLabel lblstate = new JLabel("Bill Type");
        lblstate.setBounds(100, 240, 105, 20);
        p.add(lblstate);
        
        
        //choice for bill type
        billType = new Choice();
        billType.add("Normal");
        billType.add("Industrial");
        billType.setBounds(240, 240, 220, 20);        
        billType.setBackground(Color.WHITE);
        p.add(billType);
        
        //----------days---LABEL
        JLabel lblDays = new JLabel("Days");
        lblDays.setBounds(100, 280, 105, 20);
        p.add(lblDays);
        
        
        JLabel Days = new JLabel("30 Days");
        Days.setBounds(240, 280, 105, 20);
        p.add(Days);
        
        JLabel lblnote = new JLabel("Note");
        lblnote.setBounds(100, 320, 105, 20);
        p.add(lblnote);
        
        
        JLabel note = new JLabel("By default Bill is Calculated for 30 Days only.");
        note.setBounds(240, 320, 500, 20);
        p.add(note);
        
        //------Buttons--------
        
        
        //1. Submit
        submit = new JButton("Submit");
        submit.setBounds(220,390,100,25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        p.add(submit);
       
        
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
        
        if(ae.getSource()== submit){
            
            String meter = meterNumber;
            String location = meterLocation.getSelectedItem();
            String type = meterType.getSelectedItem();
            String code = phaseCode.getSelectedItem();
            String bType = billType.getSelectedItem();
            String days = "30";
            
            String query = "insert into meter_info values ('"+meter+"','"+location+"','"+type+"','"+code+"','"+bType+"','"+days+"')";
            
            
            
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null,"Meter Information Added Successfully" );
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
        new MeterInfo("");
    }
}

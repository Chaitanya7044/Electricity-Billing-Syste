package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateInformation extends JFrame implements ActionListener{
   
    JTextField tfAddress,tfState,tfCity,tfEmail,tfPhone;
    JButton cancel,update;
    JLabel name;
    
    
    String meter;
    UpdateInformation(String meter){
        this.meter = meter;
        setBounds(100,150,1050,450);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("UPDATE CUSTOMER INFORMATION");
        heading.setBounds(110,20,350,30);
        heading.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(heading);
        
        //name of user
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30,70,100,20);
        add(lblname);
        
        name = new JLabel("");
        name.setBounds(230,70,200,20);
        add(name);
        
        //meter number
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(30,110,110,20);
        add(lblmeter);
        
        JLabel meterNumber = new JLabel("");
        meterNumber.setBounds(230,110,200,20);
        add(meterNumber);
        
        //address
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(30,150,100,20);
        add(lbladdress);
        
        tfAddress  = new JTextField("");
        tfAddress.setBounds(230,150,200,20);
        add(tfAddress);
        
        //city
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(30,190,100,20);
        add(lblcity);
        
        tfCity = new JTextField("");
        tfCity.setBounds(230,190,200,20);
        add(tfCity);
        
        //state
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(30, 230,100,20);
        add(lblstate);
        
        tfState = new JTextField("");
        tfState.setBounds(230,230,200,20);
        add(tfState);
        
        //email
        JLabel lblemail = new JLabel("E-Mail");
        lblemail.setBounds(30,270,100,20);
        add(lblemail);
        
        tfEmail = new JTextField("");
        tfEmail.setBounds(230,270,200,20);
        add(tfEmail);
        
        //phine number
        JLabel lblphone = new JLabel("Phone No.");
        lblphone.setBounds(30,310,100,20);
        add(lblphone);
        
        tfPhone = new JTextField("");
        tfPhone.setBounds(230,310,200,20);
        add(tfPhone);
        
        
        //----Button----
        
        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(70,360,100,25);
        update.addActionListener(this);
        add(update);
        
        
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(230,360,100,25);
        cancel.addActionListener(this);
        add(cancel);
        
        
        
        //---image----
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(550,50,400,300);
        add(image);
        
        
        //fetch all information basis of meterNumber
        try{
            Conn c = new Conn();
            String query = "select * from customer where meter_no = '"+meter+"'";
            ResultSet rs = c.s.executeQuery(query);
            //set each labelfield
            while(rs.next()){
                name.setText(rs.getString("name"));
                meterNumber.setText(rs.getString("meter_no"));
                tfCity.setText(rs.getString("city"));
                tfPhone.setText(rs.getString("phone"));
                tfState.setText(rs.getString("state"));
                tfAddress.setText(rs.getString("address"));
                tfEmail.setText(rs.getString("email"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        String msg = ae.getActionCommand();
        if(msg.equals("Cancel")){
            setVisible(false);
        }
        else{
            //update information
            String city = tfCity.getText();
            String state = tfState.getText();
            String address = tfAddress.getText();
            String email = tfEmail.getText();
            String phone = tfPhone.getText();
            
            try{
                Conn c = new Conn();
                String query = "update customer set phone = '"+phone+"',email = '"+email+"',address = '"+address+"', city= '"+city+"',state = '"+state+"' where meter_no = '"+meter+"' ";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
                setVisible(false);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String args[]){
        new UpdateInformation("");
    }
}

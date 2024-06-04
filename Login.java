package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    
    JButton login,cancel,signup;
    JTextField username,password;
    Choice loginin;
    Login(){
        //super must be the first statement on constructor
        super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        //actual implementation of login page
        
        //JLabel is use to write anything on frame
        JLabel lblusername = new JLabel("Username");
        //defauld alignment of text is border layout 
        //to use setBounds we have to setLayout null and use
        //setBounds to align things manually
        lblusername.setBounds(300, 20, 100, 20);
        add(lblusername);
        
        //JLabel is use to write anything on frame
        JLabel lblpassword = new JLabel("Password");
        //defauld alignment of text is border layout 
        lblpassword.setBounds(300, 60, 100, 20);
        add(lblpassword);
        
        //JLabel is use to write anything on frame
        JLabel logininas = new JLabel("Login as");
        //default alignment of text is border layout 
        logininas.setBounds(300,100, 100, 20);
        add(logininas);
        
        //Input field------------------------
        
        //using JTextField for username and password
        //username
        username = new JTextField();
        username.setBounds(400,20,150,20);
        add(username);
        //password
        password = new JTextField();
        password.setBounds(400,60,150,20);
        add(password);
        
        //dropdown for login in as using CHOICE class of awt
        loginin = new Choice();
        loginin.add("Admin");
        loginin.add("Customer");
        loginin.setBounds(400,100,150,20);
        add(loginin);
        
        //Buttons along with images in it-----------------------
        
        //login
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2 = i1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        login = new JButton("Login",new ImageIcon(i2));
        login.setBounds(310, 160, 120, 20);
        login.addActionListener(this);
        add(login);
        //cancel
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4 = i3.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        cancel = new JButton("Cancel",new ImageIcon(i4));
        cancel.setBounds(460, 160, 120, 20);
        cancel.addActionListener(this);
        add(cancel);
        //signup
        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6 = i5.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        signup = new JButton("SignUp",new ImageIcon(i6));
        signup.setBounds(380, 200, 120, 20);
        signup.addActionListener(this); 
        add(signup);
        
        
        //Profile image 
        ImageIcon i7  = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i8 = i7.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(0,0,250,250);
        add(image);
        
        
        setSize(640,300);
        setLocation(400,200);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==login){
            String sUsername = username.getText();
            String sPassword = password.getText();
            String sUser = loginin.getSelectedItem();
            
            try{
                
                Conn c = new Conn();
                String query = "select * from login where username = '"+sUsername+"' and password = '"+sPassword+"' and user = '"+sUser+"' ";
                //to execcute a query and to update someting in database we use executeUpdate
                //execute query returns an object of Resylt Set class
                ResultSet rs = c.s.executeQuery(query);
                if(rs.next()){
                    String meter = rs.getString("meter_no");
                    setVisible(false);
                    new Project(loginin.getSelectedItem(),meter);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    username.setText("");
                    password.setText("");
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == cancel){
            setVisible(false);
        }
        else if(ae.getSource() == signup){
            //close login page  
            setVisible(false);
            //open sign up page
            new SignUp();
        }
    }
    
    public static void main(String args[]){
        new Login();
    }
}

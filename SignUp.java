package electricity.billing.system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignUp extends JFrame implements ActionListener {

    JButton create, back;
    Choice accountType;
    JTextField meter, username, name, password;

    SignUp() {

        setBounds(450, 150, 700, 400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        //create account panel
        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Create Account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(172, 216, 230)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139, 34));
        add(panel);

        //heading of create account
        JLabel heading = new JLabel("Create Account As");
        heading.setBounds(100, 50, 160, 20);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(heading);

        //account type choice bar(drop down)
        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(270, 50, 150, 20);
        panel.add(accountType);

        //meter number name
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(100, 90, 160, 20);
        lblmeter.setForeground(Color.GRAY);
        lblmeter.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblmeter.setVisible(false);
        panel.add(lblmeter);

        //input of meter number
        meter = new JTextField();
        meter.setBounds(270, 90, 150, 20);
        meter.setVisible(false);
        panel.add(meter);

        //username name
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(100, 130, 160, 20);
        lblusername.setForeground(Color.GRAY);
        lblusername.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblusername);

        //input of username
        username = new JTextField();
        username.setBounds(270, 130, 150, 20);
        panel.add(username);

        //name text
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100, 170, 160, 20);
        lblname.setForeground(Color.GRAY);
        lblname.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblname);

        //input of name
        name = new JTextField();
        name.setBounds(270, 170, 150, 20);
        panel.add(name);

        //check name mathched with meter number
        meter.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent fe) {

            }

            public void focusLost(FocusEvent fe) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select name from customer where meter_no = '" + meter.getText() + "'");
                    if (rs.next()) {
                        name.setText(rs.getString("name"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        //password name
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(100, 210, 160, 20);
        lblpassword.setForeground(Color.GRAY);
        lblpassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblpassword);

        //input of username
        password = new JTextField();
        password.setBounds(270, 210, 150, 20);
        panel.add(password);

        //show meter number and textfiled according account type
        accountType.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                String user = accountType.getSelectedItem();
                if (user.equals("Customer")) {
                    lblmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                } else {
                    lblmeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }
            }
        });

        //Buttons----------------------------------
        //1.Create
        create = new JButton("Create");
        create.setBounds(140, 250, 120, 25);
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.addActionListener(this);
        panel.add(create);

        //2.Back
        back = new JButton("Back");
        back.setBounds(300, 250, 120, 25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        panel.add(back);

        //sign up image---
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(415, 30, 250, 250);
        panel.add(image);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == back) {

            setVisible(false);

            new Login();
        } else if (ae.getSource() == create) {
            //textfield returns string
            String aType = accountType.getSelectedItem();
            String uName = username.getText();
            String sName = name.getText();
            String pass = password.getText();
            String sMeter = meter.getText();

            try {
                Conn c = new Conn();
                String query = null;
                if (accountType.equals("Admin")) {
                    query = "insert into login values('" + sMeter + "','" + uName + "','" + sName + "','" + pass + "','" + aType + "')";
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Account Created Successfully");
                } else {
                    try {
                        ResultSet rs = c.s.executeQuery("select * from login where meter_no ='" + sMeter + "'");
                        if (rs.next()) {
                            query = "update login set username='" + uName + "', password='" + pass + "', user='" + aType + "' where meter_no='" + sMeter + "'";
                            c.s.executeUpdate(query);
                            JOptionPane.showMessageDialog(null, "Account Created Successfully");
                        } else {
                            JOptionPane.showMessageDialog(null, "MeterNumber does not exist");
                            meter.setText("");
                            username.setText("");
                            password.setText("");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //for DML command --> executeUpdate
                
                
                setVisible(false);
                new Login();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String args[]) {
        new SignUp();
    }

}

package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class ViewInformation extends JFrame implements ActionListener {

    JButton cancel;
    String meter;

    ViewInformation(String meter) {
        this.meter = meter;
        setBounds(150, 150, 850, 650);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Customer Information");
        heading.setBounds(250, 0, 500, 40);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);

        //name of user
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(70, 80, 100, 20);
        add(lblname);

        JLabel name = new JLabel("");
        name.setBounds(250, 80, 100, 20);
        add(name);

        //meter number
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(70, 140, 110, 20);
        add(lblmeter);

        JLabel meterNumber = new JLabel("");
        meterNumber.setBounds(250, 140, 100, 20);
        add(meterNumber);

        //address
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(70, 200, 100, 20);
        add(lbladdress);

        JLabel address = new JLabel("");
        address.setBounds(250, 200, 100, 20);
        add(address);

        //address
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(70, 260, 100, 20);
        add(lblcity);

        JLabel city = new JLabel("");
        city.setBounds(250, 260, 100, 20);
        add(city);

        //state
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(500, 80, 100, 20);
        add(lblstate);

        JLabel state = new JLabel("");
        state.setBounds(650, 80, 100, 20);
        add(state);

        //email
        JLabel lblemail = new JLabel("E-Mail");
        lblemail.setBounds(500, 140, 100, 20);
        add(lblemail);

        JLabel email = new JLabel("");
        email.setBounds(650, 140, 100, 20);
        add(email);

        //phine number
        JLabel lblphone = new JLabel("Phone No.");
        lblphone.setBounds(500, 200, 100, 20);
        add(lblphone);

        JLabel phone = new JLabel("");
        phone.setBounds(650, 200, 100, 20);
        add(phone);

        //----Button----
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(380, 340, 100, 25);
        cancel.addActionListener(this);
        add(cancel);

        //---image----
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20, 350, 600, 300);
        add(image);

        //fetch all information basis of meterNumber
        try {
            Conn c = new Conn();
            String query = "select * from customer where meter_no = '" + meter + "'";
            ResultSet rs = c.s.executeQuery(query);
            //set each labelfield
            while (rs.next()) {
                name.setText(rs.getString("name"));
                meterNumber.setText(rs.getString("meter_no"));
                city.setText(rs.getString("city"));
                phone.setText(rs.getString("phone"));
                state.setText(rs.getString("state"));
                address.setText(rs.getString("address"));
                email.setText(rs.getString("email"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        if (msg.equals("Cancel")) {
            setVisible(false);
        }
    }

    public static void main(String args[]) {
        new ViewInformation("");
    }

}

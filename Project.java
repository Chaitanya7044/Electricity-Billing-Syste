package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener {

    String USER, meterNumber;

    Project(String USER, String meterNumber) {
        this.meterNumber = meterNumber;
        this.USER = USER;
        //maximize frame size
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        //---------------menu bar---------------------
        JMenuBar mb = new JMenuBar();    //a whole bar

        //1.Master------------------------------
        JMenu master = new JMenu("Master");   //which contains sub menu's on clicked
        master.setForeground(Color.RED);

        //Items of master----
        //1.1 new customer
        JMenuItem newCustomer = new JMenuItem("New Customer");
        newCustomer.setFont(new Font("monospace", Font.PLAIN, 12));
        newCustomer.setBackground(Color.WHITE);
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon1.png"));
        Image image1 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        newCustomer.setIcon(new ImageIcon(image1));
        newCustomer.setMnemonic('N');
        newCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newCustomer.addActionListener(this);
        master.add(newCustomer);

        //1.2 customer details
        JMenuItem customerDetails = new JMenuItem("Customer Details");
        customerDetails.setFont(new Font("monospace", Font.PLAIN, 12));
        customerDetails.setBackground(Color.WHITE);
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("icon/icon2.png"));
        Image image2 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        customerDetails.setIcon(new ImageIcon(image2));
        customerDetails.setMnemonic('D');
        customerDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        customerDetails.addActionListener(this);
        master.add(customerDetails);

        //1.3 Deposit details
        JMenuItem depositDetails = new JMenuItem("Deposit Details");
        depositDetails.setFont(new Font("monospace", Font.PLAIN, 12));
        depositDetails.setBackground(Color.WHITE);
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icon/icon3.png"));
        Image image3 = icon3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        depositDetails.setIcon(new ImageIcon(image3));
        depositDetails.setMnemonic('T');
        depositDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        depositDetails.addActionListener(this);
        master.add(depositDetails);

        //1.4 Calculate Bill
        JMenuItem calculateBill = new JMenuItem("Calculate Bill");
        calculateBill.setFont(new Font("monospace", Font.PLAIN, 12));
        calculateBill.setBackground(Color.WHITE);
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icon/icon5.png"));
        Image image4 = icon4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculateBill.setIcon(new ImageIcon(image4));
        calculateBill.setMnemonic('B');
        calculateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        calculateBill.addActionListener(this);
        master.add(calculateBill);

        //2.Information------------------------------
        JMenu info = new JMenu("Information");   //which contains sub menu's on clicked
        info.setForeground(Color.BLUE);

        //adding menu items
        //2.1 update information
        JMenuItem updateInfo = new JMenuItem("Update Information");
        updateInfo.setFont(new Font("monospace", Font.PLAIN, 12));
        updateInfo.setBackground(Color.WHITE);
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image5 = icon5.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        updateInfo.setIcon(new ImageIcon(image5));
        updateInfo.setMnemonic('U');
        updateInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        updateInfo.addActionListener(this);
        info.add(updateInfo);

        //2.2 View Information
        JMenuItem viewInfo = new JMenuItem("View Information");
        viewInfo.setFont(new Font("monospace", Font.PLAIN, 12));
        viewInfo.setBackground(Color.WHITE);
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image image6 = icon6.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        viewInfo.setIcon(new ImageIcon(image6));
        viewInfo.setMnemonic('I');
        viewInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        viewInfo.addActionListener(this);
        info.add(viewInfo);

        //3.User------------------------------
        JMenu user = new JMenu("User");   //which contains sub menu's on clicked
        user.setForeground(Color.RED);

        //user menu items
        //3.1 Pay Bill
        JMenuItem payBill = new JMenuItem("Pay Bill");
        payBill.setFont(new Font("monospace", Font.PLAIN, 12));
        payBill.setBackground(Color.WHITE);
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image7 = icon7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        payBill.setIcon(new ImageIcon(image7));
        payBill.setMnemonic('P');
        payBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        payBill.addActionListener(this);
        user.add(payBill);

        //3.2 Bill Details
        JMenuItem billDetails = new JMenuItem("Bill Details");
        billDetails.setFont(new Font("monospace", Font.PLAIN, 12));
        billDetails.setBackground(Color.WHITE);
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image image8 = icon8.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        billDetails.setIcon(new ImageIcon(image8));
        billDetails.setMnemonic('B');
        billDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        billDetails.addActionListener(this);
        user.add(billDetails);

        // 4. Report
        JMenu report = new JMenu("Report");   //which contains sub menu's on clicked
        report.setForeground(Color.BLUE);

        // 4.1 Generate Bill
        JMenuItem generateBill = new JMenuItem("Generate Bill");
        generateBill.setFont(new Font("monospace", Font.PLAIN, 12));
        generateBill.setBackground(Color.WHITE);
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));
        Image image9 = icon9.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        generateBill.setIcon(new ImageIcon(image9));
        generateBill.setMnemonic('G');
        generateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        generateBill.addActionListener(this);
        report.add(generateBill);

        // 4. Utility
        JMenu utility = new JMenu("Utility");   //which contains sub menu's on clicked
        utility.setForeground(Color.RED);

        // 4.1 Note Pad
        JMenuItem notePad = new JMenuItem("Note Pad");
        notePad.setFont(new Font("monospace", Font.PLAIN, 12));
        notePad.setBackground(Color.WHITE);
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
        Image image10 = icon10.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        notePad.setIcon(new ImageIcon(image10));
        notePad.setMnemonic('X');
        notePad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        notePad.addActionListener(this);
        utility.add(notePad);

        // 4.2 Calculator
        JMenuItem calc = new JMenuItem("Calculator");
        calc.setFont(new Font("monospace", Font.PLAIN, 12));
        calc.setBackground(Color.WHITE);
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
        Image image11 = icon11.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calc.setIcon(new ImageIcon(image11));
        calc.setMnemonic('C');
        calc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        calc.addActionListener(this);
        utility.add(calc);

        // 5. Exit
        JMenu exit = new JMenu("Exit");   //which contains sub menu's on clicked
        exit.setForeground(Color.BLUE);

        //5.1 exit
        JMenuItem ex = new JMenuItem("Exit");
        ex.setFont(new Font("monospace", Font.PLAIN, 12));
        ex.setBackground(Color.WHITE);
        ImageIcon icon12 = new ImageIcon(ClassLoader.getSystemResource("icon/icon11.png"));
        Image image12 = icon12.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ex.setIcon(new ImageIcon(image12));
        ex.setMnemonic('W');
        ex.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        ex.addActionListener(this);
        exit.add(ex);

        //add menu's according to userType
        if (USER.equals("Admin")) {
            //add master to menubar
            mb.add(master);
        } else {
            //add info to menu bar
            mb.add(info);
            //add user to menu bar
            mb.add(user);
            //add utility to menu bar
            //add report to menu bar
            mb.add(report);
        }

        //In both the cases----
        mb.add(utility);

        mb.add(exit);

        setJMenuBar(mb);
        setLayout(new FlowLayout());

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        if (msg.equals("New Customer")) {
            new NewCustomer();
        } else if (msg.equals("Customer Details")) {
            new CustomerDetails();
        } else if (msg.equals("Deposit Details")) {
            new DepositDetails();
        } else if (msg.equals("Calculate Bill")) {
            new CalculateBill();
        } else if (msg.equals("View Information")) {
            new ViewInformation(meterNumber);
        } else if (msg.equals("Update Information")) {
            new UpdateInformation(meterNumber);
        } else if (msg.equals("Bill Details")) {
            new BillDetails(meterNumber);
        } else if (msg.equals("Pay Bill")) {
            new PayBill(meterNumber);
        }
        else if(msg.equals("Generate Bill")){
            new GenerateBill(meterNumber);
        }
        else if (msg.equals("Note Pad")) {

            try {
                Runtime.getRuntime().exec("gedit");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (msg.equals("Calculator")) {

            try {
                Runtime.getRuntime().exec("gnome-calculator");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (msg.equals("Exit")) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String args[]) {
        new Project("", "");
    }
}

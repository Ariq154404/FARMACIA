/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FARMACIA;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TASNIM
 */
public class FARMACIA extends JFrame {
    
    /**
     *
     */
    public static Menu menu = new Menu();

    /**
     *
     */
    public static Login login = new Login();

    /**
     *
     */
    public static Account account = new Account();

    /**
     *
     */
    public static Shop shop = new Shop();

    /**
     *
     */
    public static MedData data = new MedData();

    /**
     *
     */
    public static ListData listdata = new ListData();

    /**
     *
     */
    public static About about = new About();

    /**
     *
     */
    public static Bank bank = new Bank();

    /**
     *
     */
    public static CheckOut chk = new CheckOut();

    /**
     *
     */
    public static Order order = new Order();
    public static Doc doc = new Doc();
    public static AddPhy addphy = new AddPhy();

    
    FARMACIA(){
        initialize();
    }
    
    /**
     *
     */
    public void initialize(){
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("F A R M A C I A");
        //login.jTextField1.setBackground(new Color(0,0,0,0));
        //login.jPasswordField1.setBackground(new Color(0,0,0,0));
       // this.add(login, BorderLayout.CENTER);
       this.add(login, BorderLayout.CENTER);
        this.pack();
        this.validate();
        this.setVisible(true);
        
        
        
        setAction();
        
        
    }
    
    /**
     *
     * @param a
     * @param b
     * @return Hash
     */
    public static long hash(String a, String b) {
        String str = a + b;
        long prime = 3797;
        long MOD = 1000000007;
        long Hash = 0;
        for (int i = 0; i < str.length(); i++) {
            Hash = (prime * Hash) % MOD + str.charAt(i);
            Hash = Hash % MOD;
        }
        return Hash;
    }
    
    /**
     *
     */
    
    
    public void setAction()
    {
        //Action Listeners
        login.jButton2.addActionListener(e -> {
        String username = login.jTextField1.getText();
        String password = login.jPasswordField1.getText();
        try {
            
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","farmacia","1234");
            Statement st = con.createStatement();
            String query = "Select * from user001";
            ResultSet rs = st.executeQuery(query);
            int flag = 0;
            while(rs.next())
            {
                String usr = rs.getString(10);
                String pass = rs.getString(11);
                if(usr.equals(username) && pass.equals(password))
                {
                    flag = 1;
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String age = rs.getString(3);
                    String sex = rs.getString(4);
                    String address = rs.getString(5);
                    String blood = rs.getString(6);
                    String med = rs.getString(7);
                    String contact = rs.getString(8);
                    String emergency = rs.getString(9);
                    String sss = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date());
                    double balance = rs.getDouble(12);
                    menu.jLabel20.setText(Integer.toString(id));
                    menu.jLabel6.setText(name);
                    menu.jLabel7.setText(age);
                    menu.jLabel5.setText(sex);
                    menu.jLabel15.setText(address);
                    menu.jLabel9.setText(blood);
                    menu.jLabel11.setText(med);
                    menu.jLabel13.setText(contact);
                    menu.jLabel17.setText(emergency);
                    menu.jLabel19.setText(sss);
                    this.remove(login);
                    this.add(menu, BorderLayout.CENTER);
                    this.revalidate();
                    this.repaint();
                    this.pack();
                    login.jTextField1.setText("");
                    login.jPasswordField1.setText("");
                    if(password.equals("admin"))
                    {
                        menu.jLabel21.setText("admin");
                        
                    }
                    else
                    {
                         menu.jLabel21.setText("");
                    }
                    break;
                }
            }
            rs.close();
            st.close();
            con.close();
            if(flag==0)
            {
                JOptionPane.showMessageDialog(null,"Username or Password doesn't match, Try Again!" ,"ERROR",JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        });
        
        login.jButton1.addActionListener(e -> {
            
            
            
        account.jTextField9.setText("");
        account.jTextField4.setText("");
        account.jTextField3.setText("");
        account.jTextField5.setText("");
        account.jTextField6.setText("");
        account.jTextField7.setText("");
        account.jTextField8.setText("");
        account.jTextField2.setText("");
        account.jTextField1.setText("");
        account.jPasswordField1.setText("");
        
            this.remove(login);
            this.add(account, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        });
        
        menu.jButton10.addActionListener(e -> {
        
        
            this.remove(menu);
            this.add(shop, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        
        addphy.jButton1.addActionListener(e -> {
        
        
            String name = addphy.jTextField3.getText();
            String hospital = addphy.jTextField4.getText();
            String qualification = addphy.jTextField5.getText();
            String subject = addphy.jTextField6.getText();;
            String contact = addphy.jTextField9.getText();;
            int id = (int)hash(contact,"");
            
            try {
            
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","farmacia","1234");
            Statement st = con.createStatement();
            String query = "Insert into doc001 values ( "+id+",'"+name+"','"+hospital+"','"+qualification+"','"+subject+"','"+contact+"')";
            ResultSet rs = st.executeQuery(query);
            query = "commit";
            rs = st.executeQuery(query);
            JOptionPane.showMessageDialog(null,"Physician successfully Added !" ,"DONE",JOptionPane.INFORMATION_MESSAGE);
            rs.close();
            st.close();
            con.close();
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
            
        
        
        });
        
        
        
        
        
        addphy.jButton9.addActionListener(e -> {
        
        
            DefaultTableModel model = (DefaultTableModel) doc.jTable1.getModel();

                int row = model.getRowCount();
                while (row > 0) {
                    model.removeRow(model.getRowCount() - 1);
                    row = model.getRowCount();
                }
                //System.out.println(row);
                // for(int i=0;i<row;i++)model.removeRow(i);

                try {
                    Class.forName("oracle.jdbc.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "farmacia", "1234");
                    Statement st = con.createStatement();
                    String query = "Select * from doc001";
                    ResultSet rs = st.executeQuery(query);
                    //System.out.print("Here\n");
                    while (rs.next()) {
                        String name,hospital,qualification,subject,contact;
                        name = rs.getString(2);
                        qualification = rs.getString(4);
                        hospital = rs.getString(3);
                        subject = rs.getString(5);
                        contact = rs.getString(6);
                        
                        model.addRow(new Object[]{name, hospital, qualification, subject, contact});
                    }
                    rs.close();
                    st.close();
                    con.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            this.remove(addphy);
            this.add(doc, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        
        
        shop.jButton7.addActionListener(e -> {
            
            try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","farmacia","1234");
            Statement st = con.createStatement();
            String query = "Select * from user001 where id = "+menu.jLabel20.getText();
            ResultSet rs = st.executeQuery(query);
            double balance = 0.0;
            while(rs.next())
            {
                balance = rs.getDouble(12);
            }
            chk.jLabel14.setText(String.valueOf(balance));
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

            String name = shop.jLabel9.getText();
            String price = shop.jLabel11.getText();
            if(name.length()==0 || price.length()==0)
            {
                JOptionPane.showMessageDialog(null,"Fields can't be null, Try Again!" ,"ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else{
            chk.jLabel6.setText(name);
            chk.jLabel4.setText("BDT "+price);
            this.remove(shop);
            this.add(chk, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
            }
        
        });
        
        
        menu.jButton8.addActionListener(e -> {
            
            
            try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","farmacia","1234");
            Statement st = con.createStatement();
            String query = "Select * from user001 where id = "+menu.jLabel20.getText();
            ResultSet rs = st.executeQuery(query);
            double balance = 0.0;
            while(rs.next())
            {
                balance = rs.getDouble(12);
            }
            bank.jLabel14.setText(String.valueOf(balance));
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
            
            bank.jTextField4.setText("");
            bank.jTextField5.setText("");
            bank.jLabel15.setText("");
            this.remove(menu);
            this.add(bank, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
            
        
        });
        
        
        bank.jButton9.addActionListener(e -> {
            
            String a = bank.jTextField5.getText();
            String b = bank.jTextField5.getText();
            if(a.length()==0 || b.length()==0)
            {
                JOptionPane.showMessageDialog(null,"Payment ID or Amount can't be null, Try Again!" ,"ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else {
            Double Balance = Double.parseDouble(bank.jTextField5.getText());
            
            try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","farmacia","1234");
            Statement st = con.createStatement();
            String query = "Select * from user001 where id = "+menu.jLabel20.getText();
            ResultSet rs = st.executeQuery(query);
            double balance = 0.0;
            while(rs.next())
            {
                balance = rs.getDouble(12);
            }
            balance = balance+Balance;
            System.out.println(balance);
            bank.jLabel14.setText(String.valueOf(balance));
            query = "update user001 set balance = "+balance+" where id = "+menu.jLabel20.getText();
            rs = st.executeQuery(query);
            query = "commit";
            rs = st.executeQuery(query);
            rs.close();
            st.close();
            con.close();
            bank.jTextField4.setText("");
            bank.jTextField5.setText("");
            bank.jLabel15.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
            }
        
        });
        
        
        bank.jButton1.addActionListener(e -> {
        
        int transacid = (int)hash(bank.jTextField4.getText(),bank.jTextField5.getText());
         bank.jLabel15.setText(String.valueOf(transacid));
        
        
        });
        
        
        bank.jButton10.addActionListener(e -> {
        
        
            this.remove(bank);
            this.add(menu, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        
        menu.jButton6.addActionListener(e -> {
        
        DefaultTableModel model = (DefaultTableModel) doc.jTable1.getModel();

                int row = model.getRowCount();
                while (row > 0) {
                    model.removeRow(model.getRowCount() - 1);
                    row = model.getRowCount();
                }
                //System.out.println(row);
                // for(int i=0;i<row;i++)model.removeRow(i);

                try {
                    Class.forName("oracle.jdbc.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "farmacia", "1234");
                    Statement st = con.createStatement();
                    String query = "Select * from doc001";
                    ResultSet rs = st.executeQuery(query);
                    //System.out.print("Here\n");
                    while (rs.next()) {
                        String name,hospital,qualification,subject,contact;
                        name = rs.getString(2);
                        qualification = rs.getString(4);
                        hospital = rs.getString(3);
                        subject = rs.getString(5);
                        contact = rs.getString(6);
                        
                        model.addRow(new Object[]{name, hospital, qualification, subject, contact});
                    }
                    rs.close();
                    st.close();
                    con.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            this.remove(menu);
            this.add(doc, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        });
        
        chk.jButton10.addActionListener(e -> {
        
        
            this.remove(chk);
            this.add(shop, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        
        doc.jButton2.addActionListener(e -> {
        
        
            this.remove(doc);
            this.add(menu, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        
        
        doc.jButton6.addActionListener(e -> {
        
        
            if(menu.jLabel21.getText().endsWith("admin")){
            addphy.jTextField3.setText("");
            addphy.jTextField4.setText("");
            addphy.jTextField5.setText("");
            addphy.jTextField6.setText("");
            addphy.jTextField9.setText("");
            
            
            this.remove(doc);
            this.add(addphy, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
            }
            
            else 
            {
                 JOptionPane.showMessageDialog(null,"You Need Admin privilege to access this page!" ,"ERROR",JOptionPane.ERROR_MESSAGE);
            }
        
        
        });
        
        chk.jButton1.addActionListener(e -> {
        
        
            int price = chk.jSlider1.getValue();
            int id = Integer.parseInt(shop.jLabel6.getText());
            int pcs = Integer.parseInt(shop.jLabel5.getText());
            
            int x = pcs-price;
            if(x<0)
            {
                JOptionPane.showMessageDialog(null,"Given quantity is larger than the medicine available" ,"ERROR",JOptionPane.ERROR_MESSAGE);
                chk.jLabel6.setText("");
            chk.jLabel4.setText("");
                this.remove(chk);
            this.add(shop, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
            }
            else {
            try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","farmacia","1234");
            Statement st = con.createStatement();
            String query = "Select * from user001 where id = "+menu.jLabel20.getText();
            ResultSet rs = st.executeQuery(query);
            double balance = 0.0;
            while(rs.next())
            {
                balance = rs.getDouble(12);
            }
            
            Double gg = Double.parseDouble(shop.jLabel11.getText())*price;
            System.out.println(balance);
            if(gg>balance)
            {
                JOptionPane.showMessageDialog(null,"Not Available Balance,Recharge!" ,"ERROR",JOptionPane.ERROR_MESSAGE);
                rs.close();
            st.close();
            con.close();
            chk.jLabel6.setText("");
            chk.jLabel4.setText("");
                this.remove(chk);
            this.add(shop, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
            }
            else{
            query = "update medicine001 set available = "+x+" where id = "+id;
            shop.jLabel5.setText(String.valueOf(x));
            rs = st.executeQuery(query);
            query = "commit";
            rs = st.executeQuery(query);
            query = "update user001 set balance = "+(balance-gg)+" where id = "+menu.jLabel20.getText();
            int userid = Integer.parseInt(menu.jLabel20.getText());
            int transacid = (int)hash(menu.jLabel19.getText(),shop.jLabel6.getText());
            rs = st.executeQuery(query);
            query = "insert into transaction001 values ( " + transacid +" , "+userid+" , "+id+" , "+price+" , "+gg+" )";
            rs = st.executeQuery(query);
            query = "commit";
            rs = st.executeQuery(query);
            rs.close();
            st.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Medicine Bought Successfully!" ,"Done!",JOptionPane.INFORMATION_MESSAGE);
            chk.jLabel6.setText("");
            chk.jLabel4.setText("");
            this.remove(chk);
            this.add(shop, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
            }  
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            this.remove(chk);
            this.add(shop, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        }
            }
        
        });
        
        data.jButton1.addActionListener(e -> {
        
        
            if(data.jTextField4.getText().length()==0 || data.jTextField1.getText().length()==0)
            {
                JOptionPane.showMessageDialog(null,"Any field can't be null, Try Again!" ,"ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else data.jLabel12.setText(String.valueOf(hash(data.jTextField4.getText(),data.jTextField1.getText())));
        
        
        });
        
        data.jButton4.addActionListener(e -> {
        
            DefaultTableModel model = (DefaultTableModel) listdata.jTable1.getModel();

                int row = model.getRowCount();
                while (row > 0) {
                    model.removeRow(model.getRowCount() - 1);
                    row = model.getRowCount();
                }
                //System.out.println(row);
                // for(int i=0;i<row;i++)model.removeRow(i);

                try {
                    Class.forName("oracle.jdbc.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "farmacia", "1234");
                    Statement st = con.createStatement();
                    String query = "Select * from medicine001";
                    ResultSet rs = st.executeQuery(query);
                    //System.out.print("Here\n");
                    while (rs.next()) {
                        int id, unit;
                        String name, pharma;
                        id = rs.getInt(1);
                        name = rs.getString(2);
                        pharma = rs.getString(3);
                        double price= rs.getDouble(4);
                        unit = rs.getInt(5);
                        model.addRow(new Object[]{id, name, pharma, price, unit});
                    }
                    rs.close();
                    st.close();
                    con.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            this.remove(data);
            this.add(listdata, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        
        
        data.jButton7.addActionListener(e->{
        
        String name = data.jTextField5.getText();
        name=name.toUpperCase();
        int qty = Integer.parseInt(data.jTextField100.getText());
        int flag = 0;
        try {
                    Class.forName("oracle.jdbc.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "farmacia", "1234");
                    Statement st = con.createStatement();
                    String query = "select * from medicine001 where name = '"+name+"'";
                    ResultSet rs = st.executeQuery(query);
                    //System.out.print("Here\n");
                    int Q = 0;
                    while (rs.next()) {
                        Q = rs.getInt(5);
                        flag=1;
                    }
                    query = "update medicine001 set available = "+ (Q+qty)+ " where name = '"+name+"'";
                    rs = st.executeQuery(query);
                    rs.close();
                    st.close();
                    con.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
        if(flag==1)
        {
            JOptionPane.showMessageDialog(null, "Successfully Added To database.","Done",JOptionPane.INFORMATION_MESSAGE);
        }
        else if(flag==0)
        {
            JOptionPane.showMessageDialog(null, "Medicine Can't be Found.","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        data.jTextField5.setText("");
        data.jTextField100.setText("");
        
        });
        
        
        
        
         menu.jButton3.addActionListener(e -> {
              if(menu.jLabel21.getText().equals("admin")){
        
            DefaultTableModel model = (DefaultTableModel) order.jTable1.getModel();

                int row = model.getRowCount();
                while (row > 0) {
                    model.removeRow(model.getRowCount() - 1);
                    row = model.getRowCount();
                }
                //System.out.println(row);
                // for(int i=0;i<row;i++)model.removeRow(i);

                try {
                    Class.forName("oracle.jdbc.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "farmacia", "1234");
                    Statement st = con.createStatement();
                    String query = "Select * from transaction001";
                    ResultSet rs = st.executeQuery(query);
                    //System.out.print("Here\n");
                    while (rs.next()) {
                        int id, user,qty;
                        double total;
                        String name, pharma;
                        id = rs.getInt(2);
                        user = rs.getInt(3);
                        qty = rs.getInt(4);
                        total= rs.getDouble(5);
                        //unit = rs.getInt(5);
                        model.addRow(new Object[]{id, user, qty, total});
                    }
                    rs.close();
                    st.close();
                    con.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            this.remove(menu);
            this.add(order, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
              }
              
         else
            {
                JOptionPane.showMessageDialog(null,"You need admin status for this operation" ,"ERROR",JOptionPane.ERROR_MESSAGE);
            }
        
        });
        
        
        
        
        data.jButton3.addActionListener(e -> {
        
            if(menu.jLabel21.getText().equals("admin")){
            String name = data.jTextField4.getText();
            name = name.toUpperCase();
            String pharma = data.jTextField1.getText();
            Double price = Double.parseDouble(data.jTextField3.getText());
            int qty = Integer.parseInt(data.jTextField2.getText());
            int id = (int)hash(name,pharma);
            if(name.length()==0 || pharma.length()==0 || data.jTextField3.getText().length()==0 || data.jTextField2.getText().length()==0)
            {
                JOptionPane.showMessageDialog(null,"Any field can't be null, Try Again!" ,"ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","farmacia","1234");
            Statement st = con.createStatement();
            String query = "insert into medicine001 values ( "+id+",'"+name+"','"+pharma+"',"+price+","+qty+")";
            ResultSet rs = st.executeQuery(query);
            query = "commit";
            rs = st.executeQuery(query);
            JOptionPane.showMessageDialog(null, "Medicine Successfully Added");
            data.jTextField1.setText("");
            data.jTextField2.setText("");
            data.jTextField3.setText("");
            data.jTextField4.setText("");
            data.jLabel12.setText("");
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
            }
        
            }
            
         else
            {
                JOptionPane.showMessageDialog(null,"You need admin status for this operation" ,"ERROR",JOptionPane.ERROR_MESSAGE);
            }
        });
        
        
        
        about.jButton2.addActionListener(e -> {
        
        
            this.remove(about);
            this.add(menu, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        
        order.jButton2.addActionListener(e -> {
        
        
            this.remove(order);
            this.add(menu, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        
        menu.jButton4.addActionListener(e -> {
        
        
            this.remove(menu);
            this.add(about, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        
        listdata.jButton2.addActionListener(e -> {
        
        
            this.remove(listdata);
            this.add(data, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        
        menu.jButton2.addActionListener(e -> {
        
            data.jLabel12.setText("");
            data.jTextField1.setText("");
            data.jTextField2.setText("");
            data.jTextField3.setText("");
            data.jTextField4.setText("");
            this.remove(menu);
            this.add(data, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        
        menu.jButton9.addActionListener(e -> {
        
            login.jTextField1.setText("");
            login.jPasswordField1.setText("");
            this.remove(menu);
            this.add(login, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        
        account.jButton9.addActionListener(e -> {
        
        
            this.remove(account);
            this.add(login, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        data.jButton2.addActionListener(e -> {
        
        
            this.remove(data);
            this.add(menu, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        
        
        shop.jButton9.addActionListener(e -> {
        
            shop.jLabel6.setText("");
            shop.jLabel7.setText("");
            shop.jLabel9.setText("");
            shop.jLabel11.setText("");
            shop.jLabel5.setText("");
            shop.jTextField1.setText("");
            this.remove(shop);
            this.add(menu, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        
        
        shop.jButton2.addActionListener(e -> {
        
            String name = shop.jTextField1.getText();
            name = name.toUpperCase();
        
            try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","farmacia","1234");
            Statement st = con.createStatement();
            String query = "Select * from medicine001";
            ResultSet rs = st.executeQuery(query);
            int flag = 0;
            while(rs.next())
            {
                String N = rs.getString(2);
                if(name.equals(N))
                {
                    flag = 1;
                    int id = rs.getInt(1);
                    String Name = rs.getString(2);
                    String pharma = rs.getString(3);
                    int avail = rs.getInt(5);
                    double price = rs.getDouble(4);
                    shop.jLabel6.setText(String.valueOf(id));
                    shop.jLabel7.setText(pharma);
                    shop.jLabel9.setText(Name);
                    shop.jLabel11.setText(String.valueOf(price));
                    shop.jLabel5.setText(String.valueOf(avail));
                    break;
                }
            }
            rs.close();
            st.close();
            con.close();
            if(flag==0)
            {
                JOptionPane.showMessageDialog(null,"Medicine Can Not Be Found !!!" ,"ERROR",JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        
        
        });
        
        
        account.jButton1.addActionListener(e -> {
        
        int id,age;
        double balance = 0.0;
        String name,sex,address,blood,med,contact,emergency,username,password;
        id = (int)hash(account.jTextField3.getText(),account.jTextField9.getText());
        age = Integer.parseInt(account.jTextField4.getText());
        name = account.jTextField3.getText();
        sex = account.jTextField5.getText();
        address = account.jTextField6.getText();
        blood = account.jTextField7.getText();
        med = account.jTextField8.getText();
        contact = account.jTextField9.getText();
        emergency = account.jTextField2.getText();
        username = account.jTextField1.getText();
        password = account.jPasswordField1.getText();
        
        if(name.length()==0 || sex.length()==0 || address.length()==0 || blood.length()==0 || med.length()==0 || contact.length()==0 || emergency.length()==0 || username.length()==0 || password.length()<6)
        {
             JOptionPane.showMessageDialog(null,"Password must be at least 6 characters and other fields can't be null, Try Again!" ,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else{
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","farmacia","1234");
            Statement st = con.createStatement();
            String query = "insert into user001 values ( "+id+",'"+name+"',"+age+",'"+sex+"','"+address+"','"+blood+"','"+med+"','"+contact+"','"+emergency+"','"+username+"','"+password+"',"+balance+")";
            ResultSet rs = st.executeQuery(query);
            query = "commit";
            rs = st.executeQuery(query);
            JOptionPane.showMessageDialog(null, "Account Successfully Created");
            this.remove(account);
            this.add(login, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        }
        
        });
        
        
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        FARMACIA Tasnim = new FARMACIA();
        
    }
    
}

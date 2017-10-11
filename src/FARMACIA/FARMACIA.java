/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FARMACIA;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author TASNIM
 */
public class FARMACIA extends JFrame {
    
    public static Menu menu = new Menu();
    public static Login login = new Login();
    public static Account account = new Account();
    public static Shop shop = new Shop();
    public static MedData data = new MedData();
    public static ListData listdata = new ListData();
    public static About about = new About();
    public static Bank bank = new Bank();
    public static CheckOut chk = new CheckOut();
    FARMACIA(){
        initialize();
    }
    
    public void initialize(){
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("F A R M A C I A");
        //login.jTextField1.setBackground(new Color(0,0,0,0));
        //login.jPasswordField1.setBackground(new Color(0,0,0,0));
       // this.add(login, BorderLayout.CENTER);
       this.add(menu, BorderLayout.CENTER);
        this.pack();
        this.validate();
        this.setVisible(true);
        
        
        
        setAction();
        
        
    }
    
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

       /* if(username.equals("test") && password.equals("1234"))
        {
            this.remove(login);
            this.add(menu, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
            login.jTextField1.setText("");
            login.jPasswordField1.setText("");
        }*/
        
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
        
        chk.jButton10.addActionListener(e -> {
        
        
            this.remove(chk);
            this.add(shop, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        
        data.jButton1.addActionListener(e -> {
        
        
            if(data.jTextField4.getText().length()==0 || data.jTextField1.getText().length()==0)
            {
                JOptionPane.showMessageDialog(null,"Any field can't be null, Try Again!" ,"ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else data.jLabel12.setText(String.valueOf(hash(data.jTextField4.getText(),data.jTextField1.getText())));
        
        
        });
        
        data.jButton4.addActionListener(e -> {
        
        
            this.remove(data);
            this.add(listdata, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            this.pack();
        
        
        });
        
        data.jButton3.addActionListener(e -> {
        
        
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
        
        
        });
        
        
        
        about.jButton2.addActionListener(e -> {
        
        
            this.remove(about);
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
        
        
        });
        
        
    }
    
    
    public static void main(String[] args) {
        FARMACIA Tasnim = new FARMACIA();
        
    }
    
}

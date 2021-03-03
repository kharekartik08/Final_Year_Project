package DB;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 */
public class Connect {

    public static Connection conn = null;
  
  
   
    static String rdate = "";
    static String rtime = "";

    public static Connection openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mybrowser?user='root'&password=");

          
            System.out.println("Connection done");
            rdate = getDate();
            rtime = getTime();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return conn;
    }

    public static boolean validateLogin(String username, String password) {
        System.out.println(username + ":" + password);
        boolean b = false;
        try {
            if (!(username.isEmpty() || password.isEmpty())) {
                Connection conn=openConnection();
                String sql = "select * from tbluser where (emailid='" + username + "') && (password='" + password + "')";
                System.out.println();
                PreparedStatement stat=conn.prepareStatement(sql);
                
                ResultSet rs = stat.executeQuery();
                if (rs.next()) {
                    b = true;
                    System.out.println("user validated");
                }
             conn.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("user validated" + b);

        return b;
    }

  

    public static String getField(String tblname, String field, String matching_field, String value, String operator) {
      Connection conn=  openConnection();
        String i = "";
        try {String sql="select " + field + " from " + tblname + " where " + matching_field + "" + operator + "'" + value + "'";
               PreparedStatement stat=conn.prepareStatement(sql);
              
          ResultSet  rs = stat.executeQuery();
            if (rs.next()) {
                i = rs.getString(1);
            }
                   conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

 

  

   

    

    public static int saveUsers(String fname, String lname, String email,
            String address, String status, String usertype, String password, String mobile, String gender, String txtdob) {
        Connection conn=openConnection();
        int i = 0;
        try {
            String sql = "insert into tbluser (fname,lname,password,mobile,emailid,address,rdate,status,usertype,gender,dob)values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, fname);
            statement.setString(2, lname);

            statement.setString(3, password);

            statement.setString(4, mobile);
            statement.setString(5, email);
            statement.setString(6, address);
            statement.setString(7, rdate);
            statement.setString(8, status);
            statement.setString(9, usertype);
            statement.setString(10, gender);
            statement.setString(11, txtdob);

            i = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
      
        return i;
    }

   
   

    public static String getDomainName(String url) {
       if (url.startsWith("http:/")) {
        if (!url.contains("http://")) {
            url = url.replaceAll("http:/", "http://");
        }
    } else {
        url = "http://" + url;
    }
   URI uri=null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
     String domain = uri.getHost();
     domain= domain.startsWith("www.") ? domain.substring(4) : domain;
     domain=domain.substring(0, domain.indexOf("."));
        System.out.println("domain name="+domain);
    return domain;
      

    }

    public static int blockUrl(String url, String emailid) {
       Connection conn= openConnection();
        int i = 0;
        try {
            
         
            System.out.println("url=" + url);
            String sql = "insert into tblBlocked (url,emailid,rdate)values(?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, url);
            statement.setString(2, emailid);

            statement.setString(3, getDate());

            i = statement.executeUpdate();
             conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
      
        return i;
    }

    public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");

        Calendar cal = Calendar.getInstance();

        return dateFormat.format(cal.getTime());// "11/03/14 12:33:43";
    }

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd");

        Calendar cal = Calendar.getInstance();

        return dateFormat.format(cal.getTime());// "11/03/14 12:33:43";
    }

    public static String getTime() {
        DateFormat dateFormat1 = new SimpleDateFormat(
                "HH:mm:ss");

        Calendar cal = Calendar.getInstance();

        return dateFormat1.format(cal.getTime());// "11/03/14 12:33:43";
    }

}

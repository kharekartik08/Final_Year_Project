
import Analayse.com.datumbox.opensource.examples.NaiveBayesExample;
import static DB.Connect.getDomainName;
import DB.NavieBayesClassifier;
import JSON.JSONArray;
import JSON.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.swing.JOptionPane;
import org.htmlparser.Jsoup;
import org.htmlparser.nodes.Document;

public class Block {

    Block(String url, String emailid) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        try {

           
            int dialogResult = JOptionPane.showConfirmDialog(null, "Want to Block site " + url, "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                int i = DB.Connect.blockUrl(url, emailid);
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "Blocked Successfully!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Already Blocked");
                }
            } else {
            }

        } catch (Exception f) {
            f.printStackTrace();

        }
    }

    Block() {
    }

    Boolean checkBlock(String temp, String username) {
        
        Boolean flag = false;
        try {

//            Connection conn= DB.Connect.openConnection();
  //          String sql = "select * from tblblocked where emailid='" + username + "' and url ='" + temp + "'";
    //        System.out.print(sql);
      //      PreparedStatement stat=conn.prepareStatement(sql);
        //    ResultSet rs = stat.executeQuery();
          //  if (rs.next()) {
            //    flag = true;
            //}
            
            System.out.println("checked in db and found"+flag+" "+temp);
            String output = DB.NavieBayesClassifier.analayseData(temp);
             if (output.equals("positive")) {
                        flag = false;
                    } else {
                        flag = true;
                    }
//             to web scrap uncomment below.
            ///if (!flag) {

//                try {
//                    
//                    Document doc = Jsoup.connect(temp).timeout(10000).get();
//
//                    
//                    String check = doc.getAllElements().outerHtml();
//                    String output = DB.NavieBayesClassifier.analayseData(check);
//                    if (output.equals("positive")) {
//                        flag = true;
//                    } else {
//                        flag = false;
//                    }
//                    
//                    if(flag){
//                    System.out.println("good wesite no need to block");
//                    flag=false;
//                    }else{
//                        
//                        System.out.println("block it");
//                   // DB.Connect.blockUrl(temp, username);
//                    flag=true;
//                    }
//                } catch (Exception e) {
//                    //flag = true;
//                }

           // }

            return flag;
        } catch (Exception g) {
            return flag;
        }

    }
}

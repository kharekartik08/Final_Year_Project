import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;


public class HomePage {
	
	PrintWriter out;

	void setHomePage()
	{
		try
		{
			String page = JOptionPane.showInputDialog("Enter Home Page address");
			out = new PrintWriter("home.txt");
			out.println(page);
			out.close();
		}
		catch(Exception e)
		{
			System.out.println("File error");
		}
	}
	
	String homePage()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("home.txt"));
			StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        if(line.length()>0)
	        	return line;
	        else
	        	return null;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}

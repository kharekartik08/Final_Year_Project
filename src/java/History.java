import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class History {
	
	public void setHistory(String url, int tabNo)
	{
		try
		{	
			
			String fileName = "dataforward"+tabNo;
		    List<String> lines = new ArrayList<String>();
		    // read the file into lines
		    File f= new File(fileName);
		    if(f.exists())
		    {
		    	System.out.println("yes exists");
		    	try
		    	{
		    		BufferedReader r = new BufferedReader(new FileReader(f));
		    		String in;
		    		while ((in = r.readLine()) != null)
		    			lines.add(in);
		    		r.close();
		    	}
		    	catch(Exception e)
		    	{
		    	
		    	}
		    	if(lines.get(lines.size()-1).equals(url))
		    	{
		    		System.out.println("Same");
		    		lines.remove(lines.size()-1);
		    		PrintWriter w = new PrintWriter(new FileWriter(fileName));
		    		for (String line : lines)
		    			w.println(line);
		    		w.close();    	
		    	}
		    	else
		    	{
		    		/*File forward= new File(fileName);
		    		forward.delete();
		    		System.out.println("dataforward "+tabNo+" deleted");*/
		    	}
		    	
		    }
			FileWriter fullHistory= new FileWriter("FullHistory", true);
			BufferedWriter fh= new BufferedWriter(fullHistory);
			fh.write(url);
			fh.newLine();
			fh.close();
			System.out.println("Inside History "+ url);
			FileWriter fileWritter = new FileWriter("data"+tabNo , true);
			BufferedWriter bwr = new BufferedWriter(fileWritter);
			bwr.write(url);
			bwr.newLine();
			bwr.close();
		}
		catch(Exception e)
		{
			System.out.println("History cant be careated file loading error" + e);
		}
	}
	
	String getHistoryBack(int tabNo)
	{
		String fileName = "data"+tabNo;

	    List<String> lines = new ArrayList<String>();

	    // read the file into lines
	    try{
	    BufferedReader r = new BufferedReader(new FileReader(fileName));
	    String in;
	    while ((in = r.readLine()) != null)
	        lines.add(in);
	    r.close();
	    
	    // check your condition
	    String back = lines.get(lines.size() - 2);
	    String present= lines.get(lines.size()-1);
	    lines.remove(lines.size()-1);
	    lines.remove(lines.size()-1);
	    // write it to another file
	    
	    	PrintWriter w = new PrintWriter(new FileWriter(fileName));
	    	for (String line : lines)
	    		w.println(line);
	    	w.close();
	    	
	    	System.out.println("inside gethistoryback");
			FileWriter forward= new FileWriter("dataforward"+tabNo, true);
			BufferedWriter fh= new BufferedWriter(forward);
			fh.write(present);
			fh.newLine();
			fh.close();
			return back;
	    }
	    catch(Exception w)
	    {
	    	return null;
	    }
	    
	}
	
	String getHistoryforward(int tabNo)
	{
		String fileName = "dataforward"+tabNo;
		List<String> lines = new ArrayList<String>();
		try{
		    BufferedReader r = new BufferedReader(new FileReader(fileName));
		    String in;
		    while ((in = r.readLine()) != null)
		        lines.add(in);
		    r.close();
		    return lines.get(lines.size()-1);
		    }
		   catch(Exception e)
		    {
		    	return null;
		    }	
	}

}

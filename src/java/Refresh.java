import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Refresh {
	
	String getUrl(int tabNo)
	{
		String fileName = "data"+tabNo;

	    List<String> lines = new ArrayList<String>();
	    try{
	    	BufferedReader r = new BufferedReader(new FileReader(fileName));
	    	String in;
	    	while ((in = r.readLine()) != null)
	    		lines.add(in);
	    	r.close();
	    	String present= lines.get(lines.size()-1);
	    	lines.remove(lines.size()-1);
	    	PrintWriter w = new PrintWriter(new FileWriter(fileName));
	    	for (String line : lines)
	    		w.println(line);
	    	w.close();
	    	return present;
	    	
	    }
	    catch(Exception e)
	    {
	    	return null;
	    }
	}
}

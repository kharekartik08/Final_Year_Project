import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Layout {

	JFrame mainFrame= new JFrame("Browser");
	JTabbedPane tab = new JTabbedPane();
	ImageIcon icon = new ImageIcon(getClass().getResource("wowser.png"));
	static Point mouseDownCompCoords;
	int tabCount=-1;
	int lastTabCount=1;
	
	Layout() {
		
		new FrameComponents(this);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setUndecorated(false);
		mainFrame.setVisible(true);
		mainFrame.setPreferredSize(new java.awt.Dimension(800, 400));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
		WindowListener exitListener = new WindowAdapter() {
			@Override
            public void windowClosing(WindowEvent e) {
            	File d0= new File("data0");
    			d0.delete();
    			File d1= new File("data1");
    			d1.delete();
    			File d2= new File("data2");
    			d2.delete();
    			File d3= new File("data3");
    			d3.delete();
    			File d4= new File("data4");
    			d4.delete();
    			File d5= new File("data5");
    			d5.delete();
    			File d6= new File("data6");
    			d6.delete();
    			File d7= new File("data7");
    			d7.delete();
    			File d8= new File("data8");
    			d8.delete();
    			File f0= new File("dataforward0");
    			f0.delete();
    			File f1= new File("dataforward1");
    			f1.delete();
    			File f2= new File("dataforward2");
    			f2.delete();
    			File f3= new File("dataforward3");
    			f3.delete();
    			File f4= new File("dataforward4");
    			f4.delete();
    			File f5= new File("dataforward5");
    			f5.delete();
    			File f6= new File("dataforward6");
    			f6.delete();
    			File f7= new File("dataforward7");
    			f7.delete();
    			File f8= new File("dataforward8");
    			f8.delete();
    			try{
    				FileWriter fullHistory= new FileWriter("FullHistory", true);
    				BufferedWriter fh= new BufferedWriter(fullHistory);
    				fh.write("Date"+ new Date().toString());
    				fh.newLine();
    				fh.close();
    			}
    			catch(Exception m){
    				
    			}
            }
		};
		
		mainFrame.addWindowListener(exitListener);
		mainFrame.add(tab,BorderLayout.CENTER);
		Dimension maximumSize=new Dimension();
		maximumSize.setSize(137, 26);
		tab.setMaximumSize(maximumSize);
		addNewTabButton();
		addTabToFrame();
		addNewTabButton();
		tab.addChangeListener(new ChangeListener() { //add the Listener

	        public void stateChanged(ChangeEvent e) 
	        {

	            if(tab.getSelectedIndex()==lastTabCount)
	            {
	            	if(tabCount<8)
	            	{
	            	lastTabCount++;
	            	addTabToFrame();
	            	addNewTabButton();
	            	}
	            	else
	            	{
	            		tab.setSelectedIndex(8);
	            		JOptionPane.showMessageDialog(null, "Could not add more tab. Start new window for further process", "Tab Limit Reached", JOptionPane.INFORMATION_MESSAGE);
	            	}
	            }
	        }
	    });

	}
	
	void addTabToFrame()
	{
		tabCount++;
        tab.setTabComponentAt(tabCount, new ButtonTabComponent(this)); //Adding close tab button  
	}
	
	void addNewTabButton()
	{
	    PanelComponents pc= new PanelComponents(this);
	    JPanel panel1=pc.getPanel1();
	   	JPanel panel2=pc.getPanel2();
    	JSplitPane splitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT,panel1,panel2);
	   	splitPane.setDividerLocation(50);
	   	tab.addTab(null, splitPane);   
	}
	
	
	public static void main(String args[])
	{
		SwingUtilities.invokeLater(new Runnable() {
			 
            @Override
            public void run() {
            	new Layout();	
            }
        });
	}
}

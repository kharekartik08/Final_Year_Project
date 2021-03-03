import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class FrameComponents implements ActionListener {

	Layout o;
	JMenuBar menu= new JMenuBar();
	JMenu m1=new JMenu("File");
        
	JMenuItem m11= new JMenuItem("New Tab");
	//JMenuItem m12= new JMenuItem("Save Page");
	JMenuItem m13= new JMenuItem("Set Homepage");
	JMenuItem m14= new JMenuItem("Close");
	
	JMenu m2= new JMenu("Edit");
	JMenuItem m21= new JMenuItem("Cut");
	JMenuItem m22= new JMenuItem("Copy");
	JMenuItem m23= new JMenuItem("Paste");
	//JMenuItem m24= new JMenuItem("View Source");
	
	JMenu m3= new JMenu("View");
	JMenuItem m31= new JMenuItem("BookMark");
	JMenuItem m32= new JMenuItem("Add Favourite");
	JMenuItem m33= new JMenuItem("View Favourite");
	
	JMenu m4= new JMenu("Tools");
	
	
	JMenuItem m43= new JMenuItem("Delete History");
	//JMenuItem m44= new JMenuItem("Popup Blocker");
	
	JMenu m5=new JMenu("Help");
	JMenuItem m51= new JMenuItem("About Browser");
	JMenuItem m52= new JMenuItem("License");
	JMenuItem m53= new JMenuItem("Updates");
	
	
	FrameComponents(Layout obj)
	{
		o=obj;
		m1.add(m11);
                m1.setMnemonic(KeyEvent.VK_F);
		m11.addActionListener(this);
		//m1.add(m12);
		//m12.addActionListener(this);
		m1.add(m13);
		m13.addActionListener(this);
		m1.add(m14);
		m14.addActionListener(this);
		m14.setMnemonic(KeyEvent.VK_C);
		m2.add(m21);
		m21.addActionListener(this);
		m2.add(m22);
		m22.addActionListener(this);
		m2.add(m23);
		m23.addActionListener(this);
		//m2.add(m24);
		//m24.addActionListener(this);
		
		m3.add(m31);
		m31.addActionListener(this);
		m3.add(m32);
		m32.addActionListener(this);
		m3.add(m33);
		m33.addActionListener(this);
		
		
		
		m4.add(m43);
		m43.addActionListener(this);
		//m4.add(m44);
		//m44.addActionListener(this);
		
		m5.add(m51);
		m51.addActionListener(this);
		m5.add(m52);
		m52.addActionListener(this);
		m5.add(m53);
		m53.addActionListener(this);
		
		menu.add(m1);
		menu.add(m2);
		menu.add(m3);
		menu.add(m4);
		menu.add(m5);
		menu.setVisible(true);
		//menu.setBorderPainted(true);
		//menu.setBackground(Color.red);
		o.mainFrame.setJMenuBar(menu);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==m11)
		{
			if(o.tabCount==-1)
			{
				o.tab.setVisible(true);
				o.lastTabCount++;
				o.addTabToFrame();
				o.addNewTabButton();
				
			}
			else
			{
				if(o.tabCount<8)
            	{
            	o.lastTabCount++;
            	o.addTabToFrame();
            	o.addNewTabButton();
            	o.tab.setSelectedIndex(o.tabCount);
             	}
            	else
            	{
            		o.tab.setSelectedIndex(8);
            		JOptionPane.showMessageDialog(null, "Could not add more tab. Start new window for further process", "Tab Limit Reached", JOptionPane.INFORMATION_MESSAGE);
            	}
			}	
		}
		
		
		
		else if(e.getSource()==m13)
		{
			HomePage h= new HomePage();
			h.setHomePage();
			
		}
		else if(e.getSource()==m43)
		{
			File f=new File("C://db.txt");
                        f.deleteOnExit();
                        		JOptionPane.showMessageDialog(null, "History Delete please restart the browser", "Delete Success", JOptionPane.INFORMATION_MESSAGE);


		}

		
		
		else if(e.getSource()==m52)
		{
			JOptionPane.showMessageDialog(null, "Copy right reserved 2014 , version one.1", "License", JOptionPane.INFORMATION_MESSAGE);
		}
		
		else if(e.getSource()==m51)
		{
			JOptionPane.showMessageDialog(null, "Developed by: A, B, C ", "About", JOptionPane.INFORMATION_MESSAGE);
		}
		
		else if(e.getSource()==m53)
		{
			JOptionPane.showMessageDialog(null, "No updates available ", "Update", JOptionPane.INFORMATION_MESSAGE);
		}
		
		else if(e.getSource()==m14)
		{
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
			finally
			{
				o.mainFrame.dispose();
			}		
		}
		
		
	}
}

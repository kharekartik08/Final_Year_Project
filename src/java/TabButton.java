import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;


public class TabButton extends JButton implements ActionListener{
	
	Layout o;
	JPanel panel;
    public TabButton(Layout obj, JPanel panelCom) 
    {
		o=obj;
		panel=panelCom;
        int size = 17;
        setPreferredSize(new Dimension(size, size));
        setToolTipText("close this tab");
        
        setUI(new BasicButtonUI());		//Make the button looks the same for all Laf's
        
        setContentAreaFilled(false);	//Make it transparent
        
        setFocusable(false);	//No need to be focusable
        setBorder(BorderFactory.createEtchedBorder());
        setBorderPainted(false);
        
        addMouseListener(buttonMouseListener);		//Making nice rollover effect
        											//we use the same listener for all buttons
        setRolloverEnabled(true);
        
        addActionListener(this);				//Close the proper tab by clicking the button
    }

    public void actionPerformed(ActionEvent e) 
    {
    	int i = o.tab.indexOfTabComponent(panel);
        System.out.println(i);
        System.out.println(o.tabCount);
        if (i != -1) 
        {
            o.tab.remove(i);
            File d= new File("data"+i);
            d.delete();
            File f= new File("dataforward"+i);
            f.delete();
            if(i==o.tabCount)
            {
            	if(i==(o.lastTabCount-1) && i==0)
            	{
            		o.tab.setVisible(false);
            	}
            	else
            	{
            		o.tab.setSelectedIndex(o.tabCount-1);
            	}
            }
            
            if(i<o.tabCount)
            {
            	int z=i;
            	for(int j=i+1;j<9;j++,i++)
            	{
            		File d1= new File("data"+ j);
            		File d2= new File("data"+ i);
            		d1.renameTo(d2);
            	}
            	
            	for(int j=i+1;j<9;j++,z++)
            	{
            		File d1= new File("dataforward"+ j);
            		File d2= new File("dataforward"+ z);
            		d1.renameTo(d2);
            	}
            }
            o.lastTabCount--;
            o.tabCount--;
           
        }
    }

    //we don't want to update UI for this button
    public void updateUI() 
    {
    }

    //paint the cross
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        //shift the image for pressed buttons
        if (getModel().isPressed()) 
        {
            g2.translate(1, 1);
        }
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);
        if (getModel().isRollover()) 
        {
            g2.setColor(Color.red);
        }
        int delta = 6;
        g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
        g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
        g2.dispose();
    }
    
    private final static MouseListener buttonMouseListener = new MouseAdapter() 
    {
        public void mouseEntered(MouseEvent e) 
        {
            Component component = e.getComponent();
            if (component instanceof AbstractButton)
            {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(true);
            }
        }

        public void mouseExited(MouseEvent e) 
        {
            Component component = e.getComponent();
            if (component instanceof AbstractButton) 
            {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(false);
            }
        }
    };

}

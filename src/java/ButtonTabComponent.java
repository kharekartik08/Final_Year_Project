import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class ButtonTabComponent extends JPanel{

	private final JTabbedPane pane;
	Icon icon= new ImageIcon(getClass().getResource("wowser.png"));
	
	public ButtonTabComponent(Layout obj) 
    {
        
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        if (obj.tab == null) 
        {
            throw new NullPointerException("TabbedPane is null");
        }
        this.pane = obj.tab;
        setOpaque(false);
        if(obj.tabCount==0)
        {
        	JLabel label = new JLabel("About        ");
        	label.setIcon(icon); 
            add(label);
            //add more space between the label and the button
            label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        }
        else
        {	
        	JLabel label=new JLabel("New Tab      ");
        	label.setIcon(icon); 
            add(label);
            //add more space between the label and the button
            label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        }
        
        //tab button
        JButton button = new TabButton(obj, this);		//calling private class TabButton which design x button
        add(button);
        //add more space to the top of the component
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }
}

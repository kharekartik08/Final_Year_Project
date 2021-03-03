
import JSON.JSONObject;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import net.sf.image4j.codec.ico.ICODecoder;
import org.w3c.dom.Document;


public class PanelComponents implements ActionListener{
     public class TimerThread extends Thread {

        protected boolean isRunning;

     
        protected JLabel timeLabel;

        protected SimpleDateFormat dateFormat = 
                new SimpleDateFormat("EEE, d MMM yyyy");
        protected SimpleDateFormat timeFormat =
                new SimpleDateFormat("h:mm a");

        public TimerThread( JLabel timeLabel) {
          
            this.timeLabel = timeLabel;
            this.isRunning = true;
        }

        @Override
        public void run() {
            while (isRunning) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                      if(count>10000){
                        System.exit(0);
                      }else{
                       timeLabel.setText("Time used: "+count);
                        
                        count++;
                      }
                        
                       
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }

        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

    }
    public void loadUsername(){
    try{
    	File f = new File("D://db.txt");

                     f.createNewFile();
                    System.out.print(f.getPath());

                     String text = new Scanner(f).useDelimiter("\\A").next();
                         System.out.print(text);
                    JSONObject Jobj = new JSONObject(text);
                   username=Jobj.getString("user");
                   System.out.print(username);
                   signUp.setText(username);

                }catch(Exception e){
                    logout.setVisible(false);
                }
    
    }
	String username="Login";
	History history = new History();
	Layout o;
	URL u ;
	public JPanel panel1, panel2;
	final BooleanProperty loginAttempted = new SimpleBooleanProperty(false);
	   Timer timer;
	JEditorPane ed= new JEditorPane();
	
	private final JFXPanel jfxPanel = new JFXPanel();
    private WebEngine engine;
 
    //private final JPanel panel = new JPanel(new BorderLayout());
    private final JLabel lblStatus = new JLabel();
 int count=0;
    //private final JButton btnGo = new JButton("Go");
    //private final JTextField txtURL = new JTextField();
    private final JProgressBar progressBar = new JProgressBar();
    
	JLabel label1;
	Font font= new Font("Manle",Font.PLAIN,12);
	
	Icon backward= new ImageIcon(getClass().getResource("backward.png"));
	JButton Backward= new JButton(backward);
	Icon foreward= new ImageIcon(getClass().getResource("forward.png"));
	JButton Foreward= new JButton(foreward);
	Icon refresh= new ImageIcon(getClass().getResource("refresh.png"));
	JButton Refresh= new JButton(refresh);
	Icon stop= new ImageIcon(getClass().getResource("stop.png"));
	JButton Stop= new JButton(stop);
	Icon favourite= new ImageIcon(getClass().getResource("favourite.png"));
	JButton Favourite = new JButton(favourite);
	Icon home= new ImageIcon(getClass().getResource("home.png"));
	JButton Home = new JButton(home);
	Icon block= new ImageIcon(getClass().getResource("block.png"));
	JButton Block = new JButton(block);
	JLabel label= new JLabel("Web Address :");
	public JTextField url= new JTextField();
	Icon go=new ImageIcon(getClass().getResource("go.png"));
	public JButton Go= new JButton(go);
	JButton logout= new JButton("Logout");
	//JLabel globalpass= new JLabel("Global Pass:");
	JButton signUp= new JButton(username);
	
	   JLabel lblTime=new JLabel();
	PanelComponents()
	{
		
     
	}
	
	PanelComponents(Layout obj)
	{
            loadUsername();
		o=obj;
		createScene();
		panel1= new JPanel();
		panel1.setLayout(null);
		panel2= new JPanel(new BorderLayout());
		panel2.setBackground(Color.WHITE);
		
        JPanel statusBar = new JPanel(new BorderLayout(5, 0));
        statusBar.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
        statusBar.add(lblStatus, BorderLayout.CENTER);
        statusBar.add(progressBar, BorderLayout.EAST);
        
        panel2.add(jfxPanel, BorderLayout.CENTER);
        panel2.add(statusBar, BorderLayout.SOUTH);
		
		Backward.setToolTipText("Back to previous");
		Backward.setBounds(10,10,40,35);
//		Backward.setLayout(null);
		panel1.add(Backward);
		Backward.addActionListener(this);
		
		Foreward.setToolTipText("Back to Next");
		Foreward.setBounds(50,10,40,35);
		panel1.add(Foreward);
		Foreward.addActionListener(this);
		
		Refresh.setToolTipText("Refresh");
		Refresh.setBounds(100,10,40,35);
		panel1.add(Refresh);
		Refresh.addActionListener(this);
		
		Stop.setToolTipText("Stop");
		Stop.setBounds(140,10,40,35);
		panel1.add(Stop);
		
		Favourite.setToolTipText("Favourite");
		Favourite.setBounds(180,10,40,35);
		panel1.add(Favourite);
		
		Home.setToolTipText("Homepage");
		Home.setBounds(220,10,40,35);
		panel1.add(Home);
		Home.addActionListener(this);
		
		Block.setToolTipText("Block Page");
		Block.setBounds(260,10,40,35);
		panel1.add(Block);
		Block.addActionListener(this);
		
		label.setBounds(360, 1, 200, 50);
		panel1.add(label);
		
		url.setBounds(450,10,400,30);
		url.setToolTipText("URL");
		panel1.add(url);
		url.addActionListener(this);
		
		Go.setBounds(850, 10, 30, 32);
		panel1.add(Go);
		Go.addActionListener(this);
		
		signUp.setBounds(900,10,180,33);
		panel1.add(signUp);
		signUp.addActionListener(this);
                
               
                
		 lblTime.setBounds(1190,10,100,33);
                 lblTime.setText("Time Left: ");
		panel1.add(lblTime);
		//btnPlayGames.addActionListener(this);
		//globalpass.setBounds(1000, 2, 200, 50);
		//panel1.add(globalpass);
		
		
		logout.setBounds(1080, 10, 80, 33);
		panel1.add(logout);
		logout.addActionListener(this);
                  timerThread = new TimerThread( lblTime);
        timerThread.start();
	}
	TimerThread timerThread;
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==url|| e.getSource()==Go)
		{
			final String uri= url.getText();
			System.out.println("JTextPad clicked "+uri);
			loadURL(uri);
		}
		
		if(e.getSource()==Refresh)
		{
			System.out.println("In refresh");
			Refresh r= new Refresh();
			int i=o.tab.getSelectedIndex();
			String refreshUrl=r.getUrl(i);
			if(refreshUrl!=null)
			{
				url.setText(refreshUrl);
				Go.doClick();
			}
		}
		
		if(e.getSource()==signUp)
		{
                    if(signUp.getText().equalsIgnoreCase("Login")){

                    signUp.setText("Kindly Restart the browser after signing in");
                    signUp.setToolTipText("Kindly Restart the browser after signing in");
                    }
			url.setText("http://localhost:8080/MyBrowser/login.jsp");
			Go.doClick();
                      
			
		}
		
		if(e.getSource()==Home)
		{
			System.out.println("Clicked home");
			HomePage h= new HomePage();
			System.out.println(h.homePage());
			if(h.homePage()==null)
			{
				JOptionPane.showMessageDialog ( 
						null, "No home page set. Go to File menu to set homepage", "Homepage", JOptionPane.PLAIN_MESSAGE);
			}
			else
			{
				url.setText(h.homePage());
				Go.doClick();
			}
		}
		
		if(e.getSource()==Block)
		{
			new Block(url.getText(),username);
		}
		
		if(e.getSource()==Backward)
		{
			int i=o.tab.getSelectedIndex();
			History h= new History();
			String back= h.getHistoryBack(i);
			if(back!=null)
			{
				url.setText(back);
				Go.doClick();
			}
		}
		
		if(e.getSource()==Foreward)
		{
		
			int i=o.tab.getSelectedIndex();
			History h= new History();
			String forward=h.getHistoryforward(i);
			if(forward!=null)
			{
				url.setText(forward);
				Go.doClick();
			}
		}
		
		if(e.getSource()==logout)
		{
			System.out.println("Inside password");
			File f = new File("D://db.txt");
                       if(f.exists()){
                    	   if(f.delete()){
                    		   JOptionPane.showMessageDialog(null, "Please close the browser to proceed!!");
                    	   }else{
                    		   JOptionPane.showMessageDialog(null, "Error occurred!!");
                    	   }
                       }else{
                    	   JOptionPane.showMessageDialog(null, "No File found!!");
                       }
                      
		}
		
	}

	
	public JPanel getPanel1()
	{
		return panel1;
	}
	
	public JPanel getPanel2()
	{
		return panel2;
	}
	
	private void createScene() {
		 
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
 
                WebView view = new WebView();
                engine = view.getEngine();
 
              engine.titleProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, final String newValue) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                            	
                            	
                            	ImageIcon icon;
                            	String favurl= "http://"+u.getHost()+ "/favicon.ico";                            	                            	
                            	int i = o.tab.getSelectedIndex();
                            	JPanel headingPanel= (JPanel)o.tab.getTabComponentAt(i);;
                            	JLabel tabHeading;                      
                            	String heading;
                            	try
                            	{                        
                            		System.out.println(i);
                            		heading= newValue.toString();                            	
                            		if(heading.length()<12)
                            		{  
                            			
                            			for(int j=heading.length();j<13;j++)
                            			{
                            				heading= heading + " ";
                            			}
                            			System.out.println(heading);  
                            			System.out.println(heading.length());
                       
                            		}
                            		else
                            		{
                            			System.out.println(heading.length());
                            			heading= heading.substring(0, 11);
                            			System.out.println(heading);
                            		}    
                            		tabHeading= new JLabel(heading);
                            		try
                                	{
                            			System.out.println(favurl);
                                		URL favicon = new URL(favurl);
                                		List<BufferedImage> image = ICODecoder.read(favicon.openStream());                    
                                		if(image== null)
                                		{
                                			
                                			System.out.println("null");
                                		}                                		
                                		BufferedImage fav= image.get(0);
                                		icon= new ImageIcon(fav);
                                		Image img = icon.getImage();
                                		img= img.getScaledInstance(24, 21,  java.awt.Image.SCALE_SMOOTH);
                                		ImageIcon newicon= new ImageIcon(img);
                                		tabHeading.setIcon(newicon);
                                	}
                                	catch(Exception e)
                                	{
                                		System.out.println("Error"+ e);
                                		ImageIcon fileimage= new ImageIcon(getClass().getResource("file.png"));
                                		tabHeading.setIcon(fileimage);
                                	}                            		
                            		JButton close= new TabButton(o,headingPanel);
                            		headingPanel.removeAll();                    
                            		headingPanel.add(tabHeading);
                            		headingPanel.add(close);
                            		headingPanel.revalidate();
                            	}
                            	catch(Exception e)
                            	{
                            		
                            	}                            		          
                            }
                        });
                    }
                });
 
                engine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
                    @Override
                    public void handle(final WebEvent<String> event) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                lblStatus.setText(event.getData());
                            }
                        });
                    }
                });
 
                engine.locationProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> ov, String oldValue, final String newValue) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                            	
                                url.setText(newValue);
                                try
                                {
                                	u= new URL(newValue);
                                	//System.out.println(u.getHost());                                                               	
                                }
                                catch(Exception e)
                                {
                                	
                                }
                            }
                        });
                    }
                });
 
                engine.getLoadWorker().workDoneProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, final Number newValue) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setValue(newValue.intValue());
                            }
                        });
                    }
                });
                
                engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldState, Worker.State state) {
                    	
                    	int i = o.tab.getSelectedIndex();
                      switch (state) {
                        case RUNNING:
                        	history.setHistory(url.getText(),i);
                         
                          break;
               
                        case SUCCEEDED:
                        	System.out.println("Complete");
                          
                          break;
                        
                        case FAILED:
                        	
                        	break;
                        	
                      }
                    }
                  });
                
        /*       engine.getLoadWorker().stateProperty().addListener(new ChangeListener()) 
               {
                    @Override 
                    public void changed(ObservableValue observableValue, Worker.State state, Worker.State newState) 
                    {
                      if (newState.equals(Worker.State.SUCCEEDED)) 
                      {
                          // run task here.
                      }
                  }
                
               });*/
               
                engine.getLoadWorker()
                        .exceptionProperty()
                        .addListener(new ChangeListener<Throwable>() {
 
                            @Override
                            public void changed(ObservableValue<? extends Throwable> o, Throwable old, final Throwable value) 
                            {
                            	/*System.out.println(engine.getLoadWorker().getState());
                                if (engine.getLoadWorker().getState() == FAILED) 
                                {
                                	
                                	System.out.println("Failed");
                                    SwingUtilities.invokeLater(new Runnable() 
                                    {
                                        @Override
                                        public void run() 
                                        {
                                            JOptionPane.showMessageDialog(
                                            panel2,
                                            (value != null)
                                            ? engine.getLocation() + "\n" + value.getMessage()
                                            : engine.getLocation() + "\nUnexpected error.",
                                            "Loading error...",
                                            JOptionPane.ERROR_MESSAGE);
                                        }
                                    });
                                }*/
                            }
                        });
                
                
                engine.documentProperty().addListener(new ChangeListener<Document>() {
                    @Override
                    public void changed(ObservableValue<? extends Document> ov, Document oldDoc, Document doc) {
                        if (doc != null && loginAttempted.get()) {
                            String user= new String("abc");
                        	loadURL("javascript:document.getElementById('username').value = '"+user+"';");
                        }
                    }
                });
 
                jfxPanel.setScene(new Scene(view));
            }
        });
    }
	
	
	 public void loadURL(final String url) {
	        Platform.runLater(new Runnable() {
	            @Override
	            public void run() {
	            	
	                String tmp = toURL(url);
	                System.out.println("Inside loadURL "+ tmp);
	                Boolean flag=false;
	 
	                if (tmp == null) 
	                {
	                    tmp = toURL("http://" + url);
	                }
	                
	                Block b= new Block();
	                flag=b.checkBlock(tmp,username);
	                System.out.println(flag);
	                	
	                if(flag==true)
	                {
	                	engine.load(getClass().getResource("block.html").toExternalForm());
	                }
	                else
	                	engine.load(tmp);                
	               
	            }
	        });
	    }
	 
	 private static String toURL(String str) {
	        try {
	            return new URL(str).toExternalForm();
	        } catch (MalformedURLException exception) {
	            return null;
	        }
	    }
}


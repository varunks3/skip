import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.util.*;

class kgc extends JFrame implements ActionListener,Runnable
{
	static JList keylist;
	static JTextArea jta;
	JButton jbexit;
	static Vector name=new Vector();
	static Vector id=new Vector();
	static Vector keys=new Vector();
	static Vector v=new Vector();
	
	kgc()
	{
		super("KGC");
		createkgc();
		Thread t=new Thread(this);
		t.start();
	}
	
	void createkgc()
	{
		Container cp=this.getContentPane();
		cp.setLayout(null);
		cp.setBackground(new Color(145,23,213));
		
		JLabel jl=new JLabel("KEY GENERATION CENTER",JLabel.CENTER);
		jl.setFont(new Font("Dialog",Font.BOLD,18));
		jl.setForeground(new Color(170,145,12));
		JLabel jlmsg=new JLabel("Key Genration Process:");
		jta=new JTextArea("Waiting for Request:\n");
		JScrollPane jsp=new JScrollPane(jta);
		jta.setBackground(new Color(145,145,145));
		jta.setForeground(new Color(234,123,144));
		jta.setEditable(false);
		
		JLabel jlmsg1=new JLabel("Keys:");
		keylist=new JList();
		JScrollPane jsp1=new JScrollPane(keylist);
		keylist.setBackground(new Color(145,145,145));
		
		jbexit=new JButton("EXIT");
		
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		
		addcomponent(cp,jl,0,0,600,30);
		addcomponent(cp,jlmsg,10,80,200,20);
		addcomponent(cp,jsp,10,120,300,200);
		addcomponent(cp,jlmsg1,350,80,200,20);
		addcomponent(cp,jsp1,350,120,200,200);
		addcomponent(cp,jbexit,250,350,100,30);
		
		this.setSize(600,450);
		this.setVisible(true);
		
		jbexit.addActionListener(this);
		
	}
	
	
	void addcomponent(Container cp,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		cp.add(c);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource()==jbexit)
		{
			System.exit(0);
		}
	}
	
	public void run()
	{
		try
		{
			ServerSocket ss=new ServerSocket(2000);
			while(true)
			{
				Socket s=ss.accept();
				new processreq(s);
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static void main(String args[])
	{
		new kgc();
	}
}
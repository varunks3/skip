import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.net.*;
import java.io.*;

class kpa1 extends JFrame implements Runnable,ActionListener
{
	JList keylist;
	JButton jbexit;
	Vector userid=new Vector();
	Vector keys=new Vector();
	Vector v=new Vector();
	
	kpa1()
	{
		super("KPA1");
		createkpa();
		Thread t=new Thread(this);
		t.start();
	}
	
	void createkpa()
	{
		Container cp=getContentPane();
		cp.setLayout(null);
		cp.setBackground(new Color(134,156,22));
		
		JLabel jl=new JLabel("KEY PRIVACY AUTHORITY 1",JLabel.CENTER);
		jl.setFont(new Font("Dialog",Font.BOLD,18));
		jl.setForeground(new Color(111,12,211));
		
				
		JLabel jlmsg=new JLabel("ID ------> Key:");
		keylist=new JList();
		JScrollPane jsp=new JScrollPane(keylist);
		keylist.setBackground(new Color(122,122,122));
		
		
		jbexit=new JButton("Exit");
		
		addcomponent(cp,jl,0,0,300,30);
		addcomponent(cp,jlmsg,10,50,200,20);
		addcomponent(cp,keylist,10,80,200,200);
		addcomponent(cp,jbexit,90,300,100,30);
		
		this.setSize(300,380);
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
			ServerSocket ss=new ServerSocket(3000);
			while(true)
			{
				Socket soc=ss.accept();
				DataInputStream din=new DataInputStream(soc.getInputStream());
				String node=din.readUTF();
				
				if (node.equals("KGC"))
				{
					String req=din.readUTF();
					if (req.equals("CONNECT"))
					{
						int id=din.readInt();
						int key=din.readInt();
						
						userid.add(id);
						keys.add(key);
						v.add(id+" ----> "+key);
						keylist.setListData(v);
						
						
					}
					else
					{
						int id=din.readInt();
						int i=0;
						for (i=0;i<userid.size();i++)
						if (Integer.parseInt(userid.get(i).toString())==id)
						break;
						
						userid.removeElementAt(i);
						keys.removeElementAt(i);
						v.removeElementAt(i);
						
						userid.trimToSize();
						keys.trimToSize();
						v.trimToSize();
						
						keylist.setListData(v);
						
					}
				}
				else
				if (node.equals("USER"))
				{
					int id=din.readInt()	;
					
					int i=0;
					for (i=0;i<userid.size();i++)
					if (Integer.parseInt(userid.get(i).toString())==id)
					break;
					
					DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
					dout.writeInt(Integer.parseInt(keys.get(i).toString()));
					dout.close();
				}
				
				din.close();
				soc.close();
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	public static void main(String args[])
	{
		new kpa1();
	}
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
import java.security.*;

class peernode extends JFrame implements ActionListener
{
	JButton jbconnect,jbdisconnect,jbrequest,jbexit,jbsend,jbclear,jbview;
	JTextArea jta;
	JTextField jc;
	JLabel jl; 
	static int id=0;
	static int finalkey=0,receiverkey=0;
	int portno=0;
	static int senderid=0;
	static String sendermsg="";
	String name="";
	JTextField jtport;
	JLabel jlmyport,jlmyip;
	
	peernode()
	{
		super("PEER NODE");
		createpeer();
		try
		{
			InetAddress inet=InetAddress.getLocalHost();
			String ip=inet.getHostAddress();
		//	if (ip.equals(""))
		//	ip="127.0.0.1";
			jlmyip.setText("My IP Address: "+ip);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		portno=Integer.parseInt(JOptionPane.showInputDialog(this,"Enter your port No:"));
		jlmyport.setText("My Port No: "+portno);
		new receiver(portno);
	}
	
	void createpeer()
	{
		Container cp=this.getContentPane();
		cp.setLayout(null);
		cp.setBackground(new Color(23,45,23));
		
		jl=new JLabel("PEER NODE",JLabel.CENTER);
		jl.setFont(new Font("Dialog",Font.BOLD,18));
		jl.setForeground(new Color(124,221,14));
		
		jlmyip=new JLabel();
		jlmyip.setForeground(new Color(214,21,111));
		jlmyport=new JLabel();
		jlmyport.setForeground(new Color(214,21,111));
		
		JPanel jp1=new JPanel();
		jp1.setLayout(null);
		jp1.setBackground(new Color(111,234,123));
		jp1.setBorder(new TitledBorder( new EtchedBorder(), "Operations"));
		JPanel jp2=new JPanel();
		jp2.setLayout(null);
		jp2.setBackground(new Color(111,234,123));
		jp2.setBorder(new TitledBorder( new EtchedBorder(), "Messaging"));
		
		jbconnect=new JButton("REGISTER WITH KGC");
		jbrequest=new JButton("GET KEY FROM KPA'S");
		jbrequest.setEnabled(false);
		jbdisconnect=new JButton("DISCONNECT FROM KGC");
		jbdisconnect.setEnabled(false);
		jbview=new JButton("VIEW RECEIVED MSG");
		jbexit=new JButton("EXIT");
		
		JLabel jldest=new JLabel("Select Destination:");
		
		Vector hosts=new Vector() ;
	//	new net();
	//	hosts=net.vnode;
		
		jc=new JTextField();
		
		JLabel jldestport=new JLabel("Port No:");
		jtport=new JTextField();
		
		JLabel jlmsg=new JLabel("Enter Message");
		jta=new JTextArea();
		JScrollPane jsp=new JScrollPane(jta);
		
		jbsend=new JButton("Send");
		jbclear=new JButton("Clear");
		
		addcomponent(jp1,jbconnect,10,50,200,30);
		addcomponent(jp1,jbrequest,10,100,200,30);
		addcomponent(jp1,jbdisconnect,10,150,200,30);
		addcomponent(jp1,jbview,10,200,200,30);
		addcomponent(jp1,jbexit,10,250,200,30);
		
		addcomponent(jp2,jldest,10,20,150,20);
		addcomponent(jp2,jc,160,20,100,20);
		addcomponent(jp2,jldestport,10,50,150,20);
		addcomponent(jp2,jtport,160,50,100,20);
		addcomponent(jp2,jlmsg,10,80,200,20);
		addcomponent(jp2,jsp,10,120,230,100);
		addcomponent(jp2,jbsend,20,240,100,30);
		addcomponent(jp2,jbclear,140,240,100,30);
		
		addcomponent(cp,jl,0,0,600,30);
		addcomponent(cp,jlmyip,20,30,200,20);
		addcomponent(cp,jlmyport,220,30,200,20);
		addcomponent(cp,jp1,10,60,250,300);
		addcomponent(cp,jp2,260,60,280,300);
		
		this.setSize(600,400);
		this.setVisible(true);
		
		
		jbconnect.addActionListener(this);
		jbdisconnect.addActionListener(this);
		jbrequest.addActionListener(this);
		jbexit.addActionListener(this);
		jbsend.addActionListener(this);
		jbclear.addActionListener(this);
		jbview.addActionListener(this);
		
		
	}
	
	
	void addcomponent(Container cp,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		cp.add(c);
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource()==jbconnect)
		{
			register();
		}
		else
		if (ae.getSource()==jbrequest)
		{
			sendrequest();
		}
		else
		if (ae.getSource()==jbview)
		{
			new getkeyparts("RECEIVER",senderid);
			
			
		}
		else
		if (ae.getSource()==jbdisconnect)
		{
			disconnect();
		}
		else
		if (ae.getSource()==jbexit)
		{
			System.exit(0);
		}
		else
		if (ae.getSource()==jbsend)
		{
			sendmsg();
		}
		else
		if (ae.getSource()==jbclear)
		{
			jta.setText("");
		}
	}
	
	
	void sendmsg()
	{
		try
		{
		//	String dest=(String)jc.getSelectedItem()	;
			String dest=jc.getText();
			String port1=jtport.getText();
			String msg=jta.getText();
			
			if (!dest.equals("") && !port1.equals(""))
			{
				if (!msg.equals(""))
				{
					String finalmsg=encrypt(msg);
										
					try
					{
							Socket soc=new Socket(dest,Integer.parseInt(port1));
							DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
							dout.writeInt(id);
							dout.writeUTF(finalmsg);
							dout.close();
							soc.close();
							JOptionPane.showMessageDialog(this,"Message successfully sent to Destination");
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(this,"Error while connecting to destination");
					}
				
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Enter the message first");;
				}
				
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Select the Destination IP & Port no. first");;
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	void register()
	{
		name=JOptionPane.showInputDialog(this,"Enter ur Name:");
		try
		{
			Socket soc=new Socket(readaddr("KGC"),2000);
			DataInputStream din=new DataInputStream(soc.getInputStream());
			DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
			dout.writeUTF(name);
			dout.writeUTF("CONNECT");
			id=din.readInt();
			
			JOptionPane.showMessageDialog(this,"Your ID is: "+id+".\nGet Your Key from the KPA's");
			jbconnect.setEnabled(false);
			jbexit.setEnabled(false);
			jbdisconnect.setEnabled(true);
			jbrequest.setEnabled(true);
			
			din.close();
			dout.close();
			soc.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		jl.setText("Peer "+name);
		this.setTitle(name);
	}
	
	
		
	void sendrequest()
	{
		new getkeyparts("SENDER",id);
		
	}
	
	void disconnect()
	{
		try
		{
			Socket soc=new Socket(readaddr("KGC"),2000);
			DataInputStream din=new DataInputStream(soc.getInputStream());
			DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
			
			dout.writeUTF(name);
			dout.writeUTF("DISCONNECT");
			dout.writeInt(id);
			String reply=din.readUTF();
			id=0;
						
			JOptionPane.showMessageDialog(this,"You are disconnected from KGC");
			jbconnect.setEnabled(true);
			jbexit.setEnabled(true);
			jbdisconnect.setEnabled(false);
			jbrequest.setEnabled(false);
			
			din.close();
			dout.close();
			soc.close();
				
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	String readaddr(String node)
	{
		String address="";
		int ch=0;
		
		try
		{
			if (node.equals("KGC"))
			{
				FileInputStream fin=new FileInputStream("kgc.txt");
				while((ch=fin.read())!=-1)
				address+=(char)ch;
				address.trim();
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return address;
	}
	
	
	
	String encrypt(String msg)
	{
		
		try
		{
			
			String key="1234"+finalkey;
			if (key.length()==8)
			{
			
				Cipher cipher= Cipher.getInstance("DES");
				SecretKeySpec spec= new SecretKeySpec(key.getBytes(), "DES");
				cipher.init(Cipher.ENCRYPT_MODE, spec);
				byte messageArray[]=msg.getBytes("UTF8");
				messageArray= cipher.doFinal(messageArray,0,messageArray.length);
				msg=new BASE64Encoder().encode(messageArray);
				
			}
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return msg;
	}
	
	
	static String decrypt(String msg)
	{
		
		
		
		
		try
		{
			String key="1234"+receiverkey;
			Cipher cipher= Cipher.getInstance("DES");
			SecretKeySpec spec= new SecretKeySpec(key.getBytes(), "DES");
			cipher.init(Cipher.DECRYPT_MODE, spec);
			byte messageArray[]=new BASE64Decoder().decodeBuffer(msg);
			messageArray= cipher.doFinal(messageArray);
			msg="";
			for (int i=0;i<messageArray.length;i++)
			msg+=(char) messageArray[i];
			msg.trim();
			
		
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		
		
		return msg;
	}
	
	
	
	public static void main(String args[])
	{
		new peernode();
	}
}

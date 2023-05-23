import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

class getkeyparts extends JFrame implements ActionListener
{
	JRadioButton jr1,jr2,jr3,jr4,jr5,jr6;
	JButton jbok,jbclear;
	boolean kpa1=false,kpa2=false,kpa3=false,kpa4=false,kpa5=false,kpa6=false;
	int count=0;
	int keyarray[]=new int[3];
	String countx[]=new String[3];
	int index=0;
	int tempid=0;
	String reqnode="";
	
	getkeyparts(String requester,int keyid)
	{
		super("Select KPA's");
		reqnode=requester;
		reqnode.trim();
		tempid=keyid;
		displayoptions();
	}
	
	
	void displayoptions()
	{
		Container cp=getContentPane();
		cp.setLayout(null);
		cp.setBackground(new Color(221,134,99));
		
		JLabel jlreq=new JLabel("Select any three KPA'S to get Key:");
		jr1=new JRadioButton("KPA1");
		jr2=new JRadioButton("KPA2");
		jr3=new JRadioButton("KPA3");
		jr4=new JRadioButton("KPA4");
		jr5=new JRadioButton("KPA5");
		jr6=new JRadioButton("KPA6");
		
		jbok=new JButton("OK");
		jbclear=new JButton("CLEAR");
		
		addcomponent(cp,jlreq,10,10,200,20);
		addcomponent(cp,jr1,10,50,200,20);
		addcomponent(cp,jr2,10,90,200,20);
		addcomponent(cp,jr3,10,130,200,20);
		addcomponent(cp,jr4,10,170,200,20);
		addcomponent(cp,jr5,10,210,200,20);
		addcomponent(cp,jr6,10,250,200,20);
		addcomponent(cp,jbok,10,290,100,30);
		addcomponent(cp,jbclear,110,290,100,30);
		this.setUndecorated(true);
		this.setBounds(100,100,250,380);
		this.setVisible(true);
		
		
		jbok.addActionListener(this);
		jbclear.addActionListener(this);
		jr1.addActionListener(this);
		jr2.addActionListener(this);
		jr3.addActionListener(this);
		jr4.addActionListener(this);
		jr5.addActionListener(this);
		jr6.addActionListener(this);
	}
	
	void addcomponent(Container cp,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		cp.add(c);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource()==jbok)
		{
			if (count==3)
			{
				this.dispose();
				generatekey();
				if (reqnode.equals("SENDER"))
				JOptionPane.showMessageDialog(this,"Your Secret Key is: "+peernode.finalkey);
				else
				JOptionPane.showMessageDialog(this,"Your Secret Key is: "+peernode.receiverkey);
			}	
			else
			{
				JOptionPane.showMessageDialog(this,"Select 3 KPA's first.");
			}
		}
		else
		if (ae.getSource()==jbclear)
		{
			jr1.setSelected(false);
			jr2.setSelected(false);
			jr3.setSelected(false);
			jr4.setSelected(false);
			jr5.setSelected(false);
			jr6.setSelected(false);
			count=0;
		}
		else
		if (ae.getSource()==jr1)
		{
			if (jr1.isSelected())
			{
				if (count<3)
				{
					count++;
					kpa1=true;
				}
				else
				{
					jr1.setSelected(false);
					JOptionPane.showMessageDialog(this,"you cannot select more than 3 KPA");
				}
				
			}
			else
			{
				count--;
				kpa1=false;
			}
		}
		else
		if (ae.getSource()==jr2)
		{
			if (jr2.isSelected())
			{
				if (count<3)
				{
					count++;
					kpa2=true;
				}
				else
				{
					jr2.setSelected(false);
					JOptionPane.showMessageDialog(this,"you cannot select more than 3 KPA");
				}
				
			}
			else
			{
				count--;
				kpa2=false;
			}
		}
		else
		if (ae.getSource()==jr3)
		{
			if (jr3.isSelected())
			{
				if (count<3)
				{
					count++;
					kpa3=true;
				}
				else
				{
					jr3.setSelected(false);
					JOptionPane.showMessageDialog(this,"you cannot select more than 3 KPA");
				}
				
			}
			else
			{
				count--;
				kpa3=false;
			}
		}
		else
		if (ae.getSource()==jr4)
		{
			if (jr4.isSelected())
			{
				if (count<3)
				{
					count++;
					kpa4=true;
				}
				else
				{
					jr4.setSelected(false);
					JOptionPane.showMessageDialog(this,"you cannot select more than 3 KPA");
				}
				
			}
			else
			{
				count--;
				kpa4=false;
			}
		}
		else
		if (ae.getSource()==jr5)
		{
			if (jr5.isSelected())
			{
				if (count<3)
				{
					count++;
					kpa5=true;
				}
				else
				{
					jr5.setSelected(false);
					JOptionPane.showMessageDialog(this,"you cannot select more than 3 KPA");
				}
				
			}
			else
			{
				count--;
				kpa5=false;
			}
		}
		else
		if (ae.getSource()==jr6)
		{
			if (jr6.isSelected())
			{
				if (count<3)
				{
					count++;
					kpa6=true;
				}
				else
				{
					jr6.setSelected(false);
					JOptionPane.showMessageDialog(this,"you cannot select more than 3 KPA");
				}
				
			}
			else
			{
				count--;
				kpa6=false;
			}
		}
	}
	
	
	void generatekey()
	{
		int id=Integer.parseInt(JOptionPane.showInputDialog(this,"Enter your ID:"));
		if (id==tempid)
		{
		
		
			try
			{
				if (kpa1==true)
				{
					Socket soc=new Socket(readaddr("KPA1"),3000);
					DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
					DataInputStream din=new DataInputStream(soc.getInputStream());
					dout.writeUTF("USER");
					dout.writeInt(id);
					
					countx[index]="1";
					keyarray[index++]=din.readInt();
					din.close();
					dout.close();
					soc.close();
									
					
				}
				
				if (kpa2==true)
				{
					Socket soc=new Socket(readaddr("KPA2"),3100);
					DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
					DataInputStream din=new DataInputStream(soc.getInputStream());
					dout.writeUTF("USER");
					dout.writeInt(id);
					
					countx[index]="2";
					keyarray[index++]=din.readInt();
					din.close();
					dout.close();
					soc.close();
									
					
				}
				
				if (kpa3==true)
				{
					Socket soc=new Socket(readaddr("KPA3"),3200);
					DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
					DataInputStream din=new DataInputStream(soc.getInputStream());
					dout.writeUTF("USER");
					dout.writeInt(id);
					
					countx[index]="3";
					keyarray[index++]=din.readInt();
					din.close();
					dout.close();
					soc.close();
									
					
				}
				
				
				
				if (kpa4==true)
				{
					Socket soc=new Socket(readaddr("KPA4"),3300);
					DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
					DataInputStream din=new DataInputStream(soc.getInputStream());
					dout.writeUTF("USER");
					dout.writeInt(id);
					
					countx[index]="4";
					keyarray[index++]=din.readInt();
					din.close();
					dout.close();
					soc.close();
									
					
				}
				
				if (kpa5==true)
				{
					Socket soc=new Socket(readaddr("KPA5"),3400);
					DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
					DataInputStream din=new DataInputStream(soc.getInputStream());
					dout.writeUTF("USER");
					dout.writeInt(id);
					
					countx[index]="5";
					keyarray[index++]=din.readInt();
					din.close();
					dout.close();
					soc.close();
									
					
				}
				
				if (kpa6==true)
				{
					Socket soc=new Socket(readaddr("KPA6"),3500);
					DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
					DataInputStream din=new DataInputStream(soc.getInputStream());
					dout.writeUTF("USER");
					dout.writeInt(id);
					
					countx[index]="6";
					keyarray[index++]=din.readInt();
					din.close();
					dout.close();
					soc.close();
									
					
				}
				
				findkey(countx,keyarray);
				
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		
		}
		else
		JOptionPane.showMessageDialog(this,"Invalid ID Entered.");
	}
	
	
	void findkey(String countx[],int keys[])
	{
		try
		{
			String x0=countx[0],y0=Integer.toString(keys[0]);
			String x1=countx[1],y1=Integer.toString(keys[1]);
			String x2=countx[2],y2=Integer.toString(keys[2]);
			String x="x",xsquare="xsquare";
			
		//  System.out.println("x0="+x0+"\ty0="+y0);
		//	System.out.println("x1="+x1+"\ty1="+y1);
			
			float rem0=((Integer.parseInt(x0)*Integer.parseInt(x0))-(Integer.parseInt(x0)*Integer.parseInt(x2))-(Integer.parseInt(x0)*Integer.parseInt(x1))+(Integer.parseInt(x1)*Integer.parseInt(x2)));
			rem0=1/rem0;
		//	System.out.println(rem0);
			
			String l0=(Integer.parseInt(y0)*rem0)+"xsquare+"+((-1*Integer.parseInt(y0)*rem0)*(Integer.parseInt(x1)+Integer.parseInt(x2)))+x+"+"+((rem0*Integer.parseInt(y0))*Integer.parseInt(x1)*Integer.parseInt(x2));
			
			System.out.println("l0="+l0);
			
			
		
			float rem1=(Integer.parseInt(x1)-Integer.parseInt(x0))*(Integer.parseInt(x1)-Integer.parseInt(x2));
			rem1=1/rem1;
		//	System.out.println(rem1);
			
			String l1=(rem1*Integer.parseInt(y1))+"xsquare+"+((-1*rem1*Integer.parseInt(y1))*(Integer.parseInt(x0)+Integer.parseInt(x2)))+x+"+"+((rem1*Integer.parseInt(y1))*Integer.parseInt(x0)*Integer.parseInt(x2));
			
			System.out.println("l1="+l1);
			
			float rem2=(Integer.parseInt(x2)-Integer.parseInt(x0))*(Integer.parseInt(x2)-Integer.parseInt(x1));
			rem2=1/rem2;
		//	System.out.println(rem2);
			
			String l2=(rem2*Integer.parseInt(y2))+"xsquare+"+((-1*rem2*Integer.parseInt(y2))*(Integer.parseInt(x0)+Integer.parseInt(x1)))+x+"+"+((rem2*Integer.parseInt(y2))*Integer.parseInt(x0)*Integer.parseInt(x1));
			
			System.out.println("l2="+l2);
			
			int i=0;
			String l0arr[]=new String[3];
			StringTokenizer st=new StringTokenizer(l0,"+");
			while (st.hasMoreTokens())
			{
				l0arr[i]=st.nextToken();
				i++;
			}
			
			i=0;
			String l1arr[]=new String[3];
			StringTokenizer st1=new StringTokenizer(l1,"+");
			while (st1.hasMoreTokens())
			{
				l1arr[i]=st1.nextToken();
				i++;
			}
			
			i=0;
			String l2arr[]=new String[3];
			StringTokenizer st2=new StringTokenizer(l2,"+");
			while (st2.hasMoreTokens())
			{
				l2arr[i]=st2.nextToken();
				i++;
			}
			
			for (i=0;i<l0arr.length;i++)
			System.out.println(l0arr[i]);
			
			for (i=0;i<l1arr.length;i++)
			System.out.println(l1arr[i]);
			
			for (i=0;i<l2arr.length;i++)
			System.out.println(l2arr[i]);
			
			int output=(int)(Double.parseDouble(l0arr[2])+Double.parseDouble(l1arr[2])+Double.parseDouble(l2arr[2]));
			System.out.println("\nOUTPUT: "+output);
			
			if (reqnode.equals("SENDER"))
			peernode.finalkey=output;
			else
			{
				peernode.receiverkey=output;
				String msg=peernode.decrypt(peernode.sendermsg);
			JOptionPane.showMessageDialog(this,"Message: "+msg,"Sender ID: "+peernode.senderid,JOptionPane.INFORMATION_MESSAGE);
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println("findkey: "+e);
		}
	}
	
	
	String readaddr(String node)
	{
		String address="";
		int ch=0;
		try
		{
			if (node.equals("KPA1"))
			{
				FileInputStream fin=new FileInputStream("kpa1.txt");
				while((ch=fin.read())!=-1)
				address+=(char)ch;
				address.trim();
				System.out.println("Address of KPA1: "+address);
			}
			else
			if (node.equals("KPA2"))
			{
				FileInputStream fin=new FileInputStream("kpa2.txt");
				while((ch=fin.read())!=-1)
				address+=(char)ch;
				address.trim();
				System.out.println("Address of KPA2: "+address);
			}
			else
			if (node.equals("KPA3"))
			{
				FileInputStream fin=new FileInputStream("kpa3.txt");
				while((ch=fin.read())!=-1)
				address+=(char)ch;
				address.trim();
				System.out.println("Address of KPA3: "+address);
			}
			else
			if (node.equals("KPA4"))
			{
				FileInputStream fin=new FileInputStream("kpa4.txt");
				while((ch=fin.read())!=-1)
				address+=(char)ch;
				address.trim();
				System.out.println("Address of KPA4: "+address);
			}
			else
			if (node.equals("KPA5"))
			{
				FileInputStream fin=new FileInputStream("kpa5.txt");
				while((ch=fin.read())!=-1)
				address+=(char)ch;
				address.trim();
				System.out.println("Address of KPA5: "+address);
			}
			else
			if (node.equals("KPA6"))
			{
				FileInputStream fin=new FileInputStream("kpa6.txt");
				while((ch=fin.read())!=-1)
				address+=(char)ch;
				address.trim();
				System.out.println("Address of KPA6: "+address);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return address;
	}
	
	public static void main(String args[])
	{
		new getkeyparts("",0);
	}
}
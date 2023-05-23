import java.io.*;
import java.net.*;
import java.util.*;

class processreq implements Runnable
{
	Socket soc;
	
	
	processreq(Socket s)
	{
		soc=s;
		Thread t=new Thread(this);
		t.start();
		
	}
	
	public void run()
	{
		readreq();
	}
	
	void readreq()
	{
		try
		{
			DataInputStream din=new DataInputStream(soc.getInputStream());
			DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
			String node=din.readUTF();
			String request=din.readUTF();
			kgc.jta.append("\nRequest from peer "+node+".\n Request is "+request);
			
			if(request.equals("CONNECT"))
			{
			
			
				boolean idexist=false;
				int i=0,temp=0;
			
				do
				{
			
			
					temp=(int)(Math.random()*99)+100;
			
					for (i=0;i<kgc.id.size();i++)
					if (Integer.parseInt(kgc.id.get(i).toString())==temp)
					{
						idexist=true;
						break;
					}
			
					if (i>=kgc.id.size())
					idexist=false;
			
				} while(idexist);
			
			
			
				kgc.id.add(temp);
				kgc.name.add(node);
				
				
				int key=generatekey();
				
				
				kgc.keys.add(key);
				kgc.v.add(node+"-->"+key+"("+temp+")");
				kgc.keylist.setListData(kgc.v);
				
				int keys[]=dividekey(key);
				
				sendkeys(temp,keys[1],readaddr("KPA1"),3000);
				sendkeys(temp,keys[2],readaddr("KPA2"),3100);
				sendkeys(temp,keys[3],readaddr("KPA3"),3200);
				sendkeys(temp,keys[4],readaddr("KPA4"),3300);
				sendkeys(temp,keys[5],readaddr("KPA5"),3400);
				sendkeys(temp,keys[6],readaddr("KPA6"),3500);
			
				dout.writeInt(temp);
			
			}
			else
			if (request.equals("DISCONNECT"))
			{
				int peerid=din.readInt();
				
				disconnect(peerid,readaddr("KPA1"),3000);
				disconnect(peerid,readaddr("KPA2"),3100);
				disconnect(peerid,readaddr("KPA3"),3200);
				disconnect(peerid,readaddr("KPA4"),3300);
				disconnect(peerid,readaddr("KPA5"),3400);
				disconnect(peerid,readaddr("KPA6"),3500);
				
				int i=0;
				for (i=0;i<kgc.id.size();i++)
				if (Integer.parseInt(kgc.id.get(i).toString())==peerid)
				break;
				
				kgc.id.removeElementAt(i);
				kgc.keys.removeElementAt(i);
				kgc.name.removeElementAt(i);
				kgc.v.removeElementAt(i);
				
				kgc.id.trimToSize();
				kgc.keys.trimToSize();
				kgc.name.trimToSize();
				kgc.v.trimToSize();
								
				kgc.keylist.setListData(kgc.v);
				
				dout.writeUTF("SUCCESS");
			}
			
			kgc.jta.append("\nReply sent to peer "+node+"\n");
			
			dout.close();
			din.close();
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
			if (node.equals("KPA1"))
			{
				FileInputStream fin=new FileInputStream("kpa1.txt");
				while((ch=fin.read())!=-1)
				address+=(char)ch;
				address.trim();
			}
			else
			if (node.equals("KPA2"))
			{
				FileInputStream fin=new FileInputStream("kpa2.txt");
				while((ch=fin.read())!=-1)
				address+=(char)ch;
				address.trim();
			}
			else
			if (node.equals("KPA3"))
			{
				FileInputStream fin=new FileInputStream("kpa3.txt");
				while((ch=fin.read())!=-1)
				address+=(char)ch;
				address.trim();
			}
			else
			if (node.equals("KPA4"))
			{
				FileInputStream fin=new FileInputStream("kpa4.txt");
				while((ch=fin.read())!=-1)
				address+=(char)ch;
				address.trim();
			}
			else
			if (node.equals("KPA5"))
			{
				FileInputStream fin=new FileInputStream("kpa5.txt");
				while((ch=fin.read())!=-1)
				address+=(char)ch;
				address.trim();
			}
			else
			if (node.equals("KPA6"))
			{
				FileInputStream fin=new FileInputStream("kpa6.txt");
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
	
	
	
	int generatekey()
	{
		int key=0;
		try
		{
			key=(int)(Math.random()*999)+1000	;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return key;
	}
	
	
	
	int[] dividekey(int key)
	{
	//	int X=0;
	//	int XSQUARE=0;
		try
		{
			
		
			int val1=(int)(Math.random()*99)+100;
			int val2=(int)(Math.random()*99)+1;
			System.out.println(val1);
			System.out.println(val2);
			
			
			int keys[]=new int[7];
			
			for (int i=1;i<=6;i++)
			keys[i]=key+(val1*i)+(val2*(i*i));
			
			kgc.jta.append("\nkey= "+key);
			for (int i=1;i<=6;i++)
			kgc.jta.append("\nPart"+i+" = "+keys[i]);
			
			return keys;
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
	
	
	void sendkeys(int id,int key,String system,int port)
	{
		try
		{
			Socket s=new Socket(system,port);
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
			dout.writeUTF("KGC");
			dout.writeUTF("CONNECT");
			dout.writeInt(id);
			dout.writeInt(key);
			dout.close();
			s.close();
			
			kgc.jta.append("\nKey sent to KPA with port "+port);
			
		}
		catch(Exception e)
		{
			System.out.println("e");
			kgc.jta.append("\n error in sending key to KPA");
		}
	}
	
	
	void disconnect(int id,String host,int port)
	{
		try
		{
			Socket s=new Socket(host,port);
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
			dout.writeUTF("KGC");
			dout.writeUTF("DISCONNECT");
			dout.writeInt(id);
			dout.close();
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
}
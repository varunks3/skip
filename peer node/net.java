import java.net.*;
import java.util.*;
import java.io.*;

class net
{
	static Vector vnode=new Vector();
	
	net()
	{
		
		getip();
	}
	


void getip()
{
	try
	{
		Process p=Runtime.getRuntime().exec("net view");
		String tmp="";
		DataInputStream bir = new DataInputStream(p.getInputStream());
			tmp=bir.readLine();
			while(tmp!=null)
			{
				System.out.println(tmp);
			if(tmp.startsWith("\\"))
			{
				String t=tmp.substring((tmp.lastIndexOf("\\")+1),tmp.length()-(tmp.indexOf("\\")+1));
				t.trim();
				InetAddress inet=InetAddress.getByName(t);
				vnode.add(inet.getHostAddress());
			}
			tmp=bir.readLine();  

			}
		
	/*	InetAddress inet=InetAddress.getLocalHost();
		byte ip[]=inet.getAddress();
		
		for (int i=1;i<255;i++)
		{
			System.out.println(i);
			ip[3]=(byte) i;
			
			InetAddress address=InetAddress.getByAddress(ip);
			
			if (address.isReachable(1000))
			vnode.add(address.getHostAddress());
		}*/
		
		if (vnode.size()==0)
		vnode.add("127.0.0.1");
		
		for(int i=0;i<vnode.size();i++)
		System.out.println(vnode.get(i));
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
}	
	
	
	public static void main(String args[])
	{
		new net();
	}
}
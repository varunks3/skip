import java.net.*;
import java.io.*;
import javax.swing.*;

class receiver implements Runnable
{
	int portno=0;
	
	receiver(int port)
	{
		portno=port;
		Thread t=new Thread(this);
		t.start();
	}
	
	public void run()
	{
		try
		{
			ServerSocket ss=new ServerSocket(portno);
			while(true)
			{
				Socket soc=ss.accept();
				DataInputStream din=new DataInputStream(soc.getInputStream());
				peernode.senderid=din.readInt();
				peernode.sendermsg=din.readUTF();
				din.close();
				soc.close();
				
				JOptionPane.showMessageDialog(null,"you hava a msg from sender with ID: "+peernode.senderid+"\nGet the key from kpa and decrypt the message.");
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
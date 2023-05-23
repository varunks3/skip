import java.util.*;
class shamir
{
	shamir()
	{
		int key=generatekey();
		dividekey(key);
		
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
	
	void dividekey(int key)
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
			
			System.out.println("key= "+key);
			for (int i=1;i<=6;i++)
			System.out.println("Part"+i+" = "+keys[i]);
			
			findkey(keys);
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	void findkey(int keys[])
	{
		try
		{
			String x0="2",y0=Integer.toString(keys[2]);
			String x1="4",y1=Integer.toString(keys[4]);
			String x2="5",y2=Integer.toString(keys[5]);
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
			
			
		}
		catch(Exception e)
		{
			System.out.println("findkey: "+e);
		}
	}
	
	public static void main(String args[])
	{
		new shamir();
	}
}
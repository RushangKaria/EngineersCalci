import java.io.*;

class AP
{
	String first,last,sum,n,d;

}

class Flag
{
	boolean first,last,sum,n,d;
}

class APFormulae
{

}

public class ArithmeticProgression
{
	static InputStreamReader r;
	static BufferedReader input;

	ArithmeticProgression()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);
		

		driver();		
	}

	void driver()throws IOException
	{
	AP a=new AP();

		a.first=takeInput("first element");
		a.last=takeInput("last element");
		a.sum=takeInput("sum");
		a.n=takeInput("number of terms");
		a.d=takeInput("common difference");

	int no_of_unknowns=findUnknowns(a);

		if(no_of_unknowns>2)
		{
		System.out.println("IMPOSSIBLE TO COMPUTE SERIES");
		return;
		}

	computeSeries(a);
	}

	int findUnknowns(AP a)
	{
	int count=0;

		if(a.first.equals("null"))
		count++;

		if(a.last.equals("null"))
		count++;

		if(a.sum.equals("null"))
		count++;

		if(a.n.equals("null"))
		count++;

		if(a.d.equals("null"))
		count++;
		

	return count;
	}

	void computeSeries(AP a)
	{
	double first,last,sum,n,d;
	Flag f=new Flag();
//---------------------------------------------------------------------------------------------------------------------
		if(!a.first.equals("null"))
		{
		first=Double.parseDouble(a.first);
		f.first=true;
		}

		if(!a.last.equals("null"))
		{
		last=Double.parseDouble(a.last);
		f.last=true;
		}

		if(!a.sum.equals("null"))
		{
		sum=Double.parseDouble(a.sum);
		f.sum=true;
		}

		if(!a.n.equals("null"))
		{
		n=Double.parseDouble(a.n);
		f.n=true;
		}

		if(!a.d.equals("null"))
		{
		d=Double.parseDouble(a.d);
		f.d=true;
		}
//---------------------------------------------------------------------------------------------------------------------

		
		
	}


	String takeInput(String name)throws IOException
	{
	String temp;

		System.out.println("Enter the "+name+" please. USE NULL IF UNKNOWN");
		temp=input.readLine();

	return temp;
	}
}
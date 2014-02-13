import java.io.*;

public class NthRoot
{
	static InputStreamReader r;
	static BufferedReader input;

	final double e=Math.E;

	NthRoot()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);

		driver();
	
	}

	void driver()throws IOException
	{
	double no,n;

		try
		{
		System.out.println("Enter the no");
		no=Double.parseDouble(input.readLine());
	
		System.out.println("Enter the root to be found");
		n=Double.parseDouble(input.readLine());
		}
		catch(Exception e)
		{
		System.out.println("ERROR...Input was not entered correctly");
		return;
		}

			double result=computeNthRoot(no,n);

	display(result,no,n);
	}

	double computeNthRoot(double no,double n)
	{
	double ans,temp;
		
		temp=Math.log(no)/n;

		ans=Math.pow(e,temp);
	
	return ans;	
	}

	void display(double result,double no,double n)
	{
	System.out.println("Computing "+n+((char)251)+no+" ...");
	System.out.println(n+""+((char)251)+no+"="+result);
	}
}
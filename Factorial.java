import java.io.*;

public class Factorial 
{
	static InputStreamReader r;
	static BufferedReader input;
	double answer;

	Factorial()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);

		//compute();
	}
	
	void compute()throws IOException
	{
	
	int n;
	
	System.out.println("Enter the no whose factorial is to be computed");
		
		try
		{	
		n=Integer.parseInt(input.readLine());
		}
		catch(NumberFormatException e)
		{
		System.out.println("ERROR...Number must be integer");
		return;
		}
		
	if(n<0)
	{
	System.out.println(n+"!="+(char)236);
	return;
	}	

	answer=Factorial(n);
		
		System.out.println(n+"!="+answer);

	
	}
/*----------------------------------------------------------------------------------------------------------------*/
	double Factorial(int n)
	{
		if(n==1||n==0)
		return 1;
	
	double a=n*Factorial(n-1);

	return a;
	}
/*----------------------------------------------------------------------------------------------------------------*/

}
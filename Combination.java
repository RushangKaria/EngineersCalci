import java.io.*;

public class Combination
{
	static InputStreamReader r;
	static BufferedReader input;
	int n,k;
	
	Combination()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);

	n=0;
	k=0;

		drive();
	}

	void drive()throws IOException
	{
	String s;

		System.out.println("Enter the desired permutation in the form nCk... n,r=integers");
		s=input.readLine();

	extract(s);

	double result;
		result=combination();

	printResult(result);
	}

	double combination()throws IOException
	{
	Factorial f=new Factorial();
			
		double a=f.Factorial(n);
		double b=f.Factorial(n-k);
		double c=f.Factorial(k);
		double d=a/(b*c);
		
	return d;
	}
	
	void extract(String s)
	{
	int pointer=s.indexOf('C');
	int length=s.length();

		n=Integer.parseInt(s.substring(0,pointer));
		k=Integer.parseInt(s.substring(pointer+1,length));
	}

	void printResult(double result)
	{
	System.out.println(n+"C"+k+"="+result);
	}

}
import java.io.*;

class NegativeNumberException extends Exception
{
	public String toString()
	{
	return "ERROR...Number cannot be negative";
	}
}

class NumberOneException extends Exception
{
	public String toString()
	{
	return "Neither PRIME nor NOT PRIME";
	}
}


public class IsPrime
{
	static InputStreamReader r;
	static BufferedReader input;

	IsPrime()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);

		driver();
	}

	void driver()throws IOException
	{
	int no;

		try
		{
		System.out.println("Please enter the no");
		no=Integer.parseInt(input.readLine());

			if(no<1)
			throw new NegativeNumberException();

			if(no==1)
			throw new NumberOneException();

			if(no==2)
			{
			display(true);
			return;
			}
		}
		catch(NumberFormatException e)	//Note: you cannot have Exception e here and then later a specialized
		{				//exception since (Exception e) captures all Exceptions...ie compile
						//error will result else. try it..rename block to Exception e
		System.out.println("ERROR..input was not entered correctly or exceeds range");
		return;
		}
		catch(NegativeNumberException e)
		{
		System.out.println(e);
		return;
		}
		catch(NumberOneException e)
		{
		System.out.println(e);
		return;
		}

				boolean prime=check(no);

	
	display(prime);
	}

	boolean check(int no)
	{
	int range;

		if(no%2==0)
		return false;

		range=(int)Math.sqrt(no);

			if(range%2==0)
			range--;


		while(range>1)
		{
			if(no%range==0)
			return false;
			else
			range-=2;
		}

	return true;
	}

	void display(boolean prime)
	{
		if(prime)
		System.out.println("PRIME");
		else
		System.out.println("NOT PRIME");


	}


}
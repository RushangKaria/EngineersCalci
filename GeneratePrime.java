import java.io.*;

class NotIntegerException extends Exception
{
	public String toString()
	{
	return "ERROR...Please enter an integer";
	}
}

/*class NegativeNumberException extends Exception
{
	public String toString()
	{
	return "ERROR...The number cannot be negative";
	}
}*/


public class GeneratePrime
{
	static InputStreamReader r;
	static BufferedReader input;

	GeneratePrime()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);

		driver();
	}

	void driver()throws IOException
	{
	String temp;
	
		try
		{
		System.out.println("Enter the no of prime nos to be generated");
		temp=input.readLine();

			if(notInteger(temp))
			throw new NotIntegerException();
		
		}
		catch(NotIntegerException e)
		{
		System.out.println(e);
		return;
		}

	double n; //it is double since we want a huge range of only integers though...an exception is therfore defined
	n=0;      //since we are calling it by parsing it we need to initialize it..try to compile without this statement
		try
		{
		n=Double.parseDouble(temp);
			if(n<0)
			throw new NegativeNumberException();
		}
		catch(NegativeNumberException e)
		{
		System.out.println("ERROR...The number cannot be negative");
		}

	double result[]=generator(n);


	//display(result,n);	We discard display method since for very large n it will take time
	//			try it...remove sopln statements in generator method and run display for large n
	}

	boolean notInteger(String temp)
	{
	int index_of_dot;

	index_of_dot=temp.indexOf('.');
	//System.out.println(index_of_dot); DEBUGGING

		if(index_of_dot==-1)
		return false;

			for(int i=index_of_dot+1;i<temp.length();i++)
			{
			int x=(int)temp.charAt(i)-48;
				if(x!=0)
				return true;
			}

	return false;
	}

	double[] generator(double n)
	{
	double temp[]=new double[(int)n];	//give loss of precision error since array size is always int
						//so do type conversion to prevent compilation error
						//it is legal since it will take only floor value
						//therefore due to this limitation only prime nos uptil integer range
						//can be generated... in furthur versions this will be corrected

System.out.println("============================================================================");

	temp[0]=2;
	int size_pointer=1;	//type int since array size is limited to int only
	int count=1;		//to keep track of how many values found
	double trial_value=3;

System.out.println("1\t|"+temp[0]);

		while(count<n)	//since we already defined 2 as a prime no...so now (n-1) need to be generated
		{
			if(divisible(trial_value,temp,size_pointer))
			{
			trial_value+=2;
			}
			else
			{
			temp[size_pointer]=trial_value;
		
				System.out.println((size_pointer+1)+"\t|"+temp[size_pointer]);				

			trial_value+=2;
			size_pointer++;
			count++;
			}
	
		}		

System.out.println("============================================================================");

	return temp;
	}

	boolean divisible(double trial_value,double temp[],int size_pointer)
	{
		for(int i=0;i<size_pointer;i++)
			if(trial_value%temp[i]==0)
			return true;

	return false;
	}

	void display(double result[],double n)
	{
	System.out.println("============================================================================");

		for(int i=0;i<n;i++)
		{
		System.out.println((i+1)+"\t|"+result[i]);
		}

	System.out.println("============================================================================");	
	}
}
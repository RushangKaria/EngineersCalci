import java.io.*;

class Variables
{
	int a,b,c;

	Variables()
	{
	a=b=c=0;
	}
}

class Result
{
	String ans1,ans2;

	Result()
	{
	ans1=ans2="";
	}
}

class PlusMinusException extends Exception
{
	public String toString()
	{
	return "Please enter +/- at the appropriate positions";
	}
}

class PowerException extends Exception
{
	public String toString()
	{
	return "The power of the equation is not Quadratic";
	}
}

class notQuadraticException extends Exception
{
	public String toString()
	{
	return "Power of X2 cannot be zero";
	}
}

public class QuadraticEquations
{
	static InputStreamReader r;
	static BufferedReader input;

	QuadraticEquations()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);

		driver();
	}

	void driver()throws IOException
	{
	System.out.println("Enter the equation in the form ?X2+?X1+?X0=0");

	String io;
	io=input.readLine();

		if(!valid(io))
		{
		System.out.println("Please Enter the equation in the correct form");
		return;
		}
		else
		{
		Variables v=getVariables(io);
		Result r=compute(v);
		display(r);
		}
		
	}

	boolean valid(String io)
	{
	int size=io.length();
	int index=-1;
	int X_pointer=0;
	int X_power=2;
	String temp;
	int no=0;
	int io_power=0;
	int operator_pointer=0;
			try
			{
				for(int count=0;count<3;count++)
				{
				X_pointer=io.indexOf('X',index+1);
				//System.out.println("index="+index+"||X_Pointer="+X_pointer);
			
					try
					{
					temp=io.substring(index+1,X_pointer);
					}
					catch(StringIndexOutOfBoundsException e)
					{
					temp=io.substring(index+1,size-1);
					}
				no=Integer.parseInt(temp);

					if(no==0&&X_power==2)
					throw new notQuadraticException();

				//System.out.println(no);

				X_pointer++;
		
				//System.out.println((int)io.charAt(X_pointer)-48);
				//System.out.println(X_pointer);
				
					if((int)io.charAt(X_pointer)-48==X_power)
					{
					X_power--;
					}
					else
					{
					throw new PowerException();
					}

				index=X_pointer+1;

					if(index>=size)
					break;

					if(io.charAt(index)=='+'||io.charAt(index)=='-')
					{
					
					}
					else
					{
					throw new PlusMinusException();
					}

				//System.out.println("X_pointer= "+X_pointer+"\n"+temp);
				}
		
			
			}
//###################################################################################################################
			catch(NumberFormatException e)
			{
			//System.out.println("Please enter the statement in a valid form");
			return false;
			}
			catch(PowerException e)
			{
			System.out.println(e);
			return false;
			}
			catch(PlusMinusException e)
			{
			System.out.println(e);
			return false;
			}
			catch(notQuadraticException e)
			{
			System.out.println(e);
			return false;
			}
			catch(StringIndexOutOfBoundsException e)
			{
			//System.out.println("Please enter statement in a valid form");
			return false;
			}
//###################################################################################################################
	
	
	return true;
	}

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	Variables getVariables(String io)
	{
	int X_pointer=0;
	int index=0;
	String temp;
	int var[]=new int[3];
	int i=0;
	int no;
	

			for(int count=0;count<3;count++)
			{
			X_pointer=io.indexOf('X',index);
			temp=io.substring(index,X_pointer);
			
//System.out.println(temp);
				if(temp.charAt(0)=='+')
				no=Integer.parseInt(temp.substring(1,temp.length()));
				else
				no=Integer.parseInt(temp);
			var[i++]=no;

			index=X_pointer+2;
			}

/*
for(i=0;i<3;i++)
System.out.print(var[i]+" ");
*/


	Variables d=new Variables();
	d.a=var[0];
	d.b=var[1];
	d.c=var[2];

	return d;	
	}
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

	Result compute(Variables v)
	{
	Result d=new Result();
	
		boolean negative_root=false;
		double a,b,delta;
		
		a=v.a*2;
		b=v.b*-1;

			double x,y,z;

			x=v.b*v.b;
			y=4*v.a*v.c;

			z=x-y;

			if(z<0)
			{
			negative_root=true;
			z=z*(-1);
			}
	
	d.ans1+="("+b+"+";
	d.ans2+="("+b+"-";

		if(negative_root)
		{
		d.ans1+="i"+(char)251;
		d.ans2+="i"+(char)251;
		}
		else
		{
		d.ans1+=(char)251;
		d.ans2+=(char)251;
		}

	d.ans1+=z+")"+"/"+a;
	d.ans2+=z+")"+"/"+a;


		if(!negative_root)
		{
		double a1,a2;
		a1=(b+Math.sqrt(z))/(a);
		a2=(b-Math.sqrt(z))/(a);

		d.ans1+=" = "+a1;
		d.ans2+=" = "+a2;
		
		}

	
	//System.out.println(d.ans1+"\n\n"+d.ans2);

	return d;
	}	

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

	void display(Result r)
	{
	System.out.println("==================RESULT====================");

	System.out.println("");

		System.out.println(r.ans1+"\n\n"+r.ans2);

	System.out.println("");
	System.out.println("============================================");

	}

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
}
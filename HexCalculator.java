import java.io.*;

class NotAValidStringException extends Exception
{
	public String toString()
	{
	return "Not a Valid HEX value";
	}
}

class HexCalculator
{
	static InputStreamReader r;
	static BufferedReader input;

	String io;
	double ans; //double since hex can really handle large values in small no of bits

	HexCalculator()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);

	io=new String();
	ans=0;

		driver();
	}

	void driver()throws IOException
	{
	String valid_string=new String();

			takeInput();
		
		while(true)
		{
			try
			{

			valid_string=validate();
	
				if(!valid_string.equals("error"))
				{
				compute(valid_string);
				break;
				}
				else
				throw new NotAValidStringException();
			}
			catch(NotAValidStringException e)
			{
			System.out.println(e);
			takeInput();
			}
		}
	display();		
	}

	void takeInput()throws IOException
	{
		System.out.println("Enter the HEX value");
		io=input.readLine();
	}				

	String validate()
	{
	int pointer=-1;
	int size=-1;
	String result=new String("error");
	boolean valid=false;

	types:
	{
		type_decimal_1234:
		{
			try
			{
			double x=Double.parseDouble(io);
			result=io.substring(0,io.length());
			break types; //if no exception then guranteed only decimal units...thus valid HEX string
			}
			catch(Exception e)
			{
			// blank..so that we can check for the other types
			}
		}
		
		type_1234H:
		{
			if(io.charAt(io.length()-1)=='H'||io.charAt(io.length()-1)=='h')
			{
			io=io.toUpperCase();
			pointer=0;
			size=io.length()-1; 			//since we want to validate string before H			
			valid=check_valid_Hex_digits(pointer,size);

				if(valid)
				result=io.substring(0,size);

			break types; //since we knew it was this type
			}
		

		}

		type_0xFFFF:
		{
		int x=io.indexOf('x');
		io=io.toUpperCase(); // this statement comes later since we first check for small x and then convert to	
				      // to Uppercase else x will always be -1

				if(x!=-1)	
				{
					int zero_checker=Integer.parseInt(io.substring(0,x));//bug found by Shrijal
					if(zero_checker!=0)		//so that 0123xFFFF will still be invalid
					break types;			//string...without the above three lines
									//0123xFFFF would be passed as valid string

				pointer=x+1;

				valid=check_valid_Hex_digits(pointer,io.length());

					if(valid)
					{
					result=io.substring(pointer,io.length());
					break types;
					}
				}
		}

		type_decimal_hex_mix_12AB3:
		{
			valid=check_valid_Hex_digits(0,io.length());
			result=io.substring(0,io.length());
			break types;
		}

	}//end types label

	return result;
	}

	boolean check_valid_Hex_digits(int pointer,int size)
	{
	char c;
		while(pointer<size)
		{		
		c=io.charAt(pointer);
		//System.out.print(c);	DEBUGGING
	
			if(c>='0'&&c<='9'||c>='A'&&c<='F')
			pointer++;
			else
			return false;	
		}
	return true;
	}

	void compute(String s)
	{
		int power=0;
		char x;
		int decimal_representation=0;

		for(int i=s.length()-1;i>=0;i--)
		{
		x=s.charAt(i);
			decimal_representation=get_decimal_value(x);
			ans+=(Math.pow(16,power))*decimal_representation;
			power++;
		}
	}
	
	int get_decimal_value(char x)
	{
		switch(x)
		{
		case '0':return 0;
		case '1':return 1;
		case '2':return 2;
		case '3':return 3;
		case '4':return 4;
		case '5':return 5;
		case '6':return 6;
		case '7':return 7;
		case '8':return 8;
		case '9':return 9;

		case 'a':
		case 'A':return 10;
		case 'b':
		case 'B':return 11;
		case 'c':
		case 'C':return 12;
		case 'd':
		case 'D':return 13;
		case 'e':
		case 'E':return 14;
		case 'f':
		case 'F':return 15;

		default: return -1;	//not required but compiler does not know runtime value of x so it thinks that
					//a default value should be provided...if you remove it then append return -1
					//to end of function since compiler expects it if the switch case does not
					//execute...try removing and compiling
		}

	//return -1; //if default case not provided
	}

	void display()
	{
	System.out.println("Decimal Value="+ans);
	}
}

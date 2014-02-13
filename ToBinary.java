import java.io.*;

class ToBinary
{
	static InputStreamReader r;
	static BufferedReader input;

	int no,size_in_bits;
	int result[];

	ToBinary()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);

	no=0;
	
		driver();
	}

	void driver()throws IOException
	{
		takeInput();
		compute();
		display();
	}

	void takeInput()throws IOException
	{
	boolean exception_triggered=false;

		do
		{
			try
			{
			System.out.println("Enter the integer value");
			no=Integer.parseInt(input.readLine());
			exception_triggered=false;
			}
			catch(Exception e)
			{
				System.out.println("Please enter in correct form");
				exception_triggered=true;
			}
		}
		while(exception_triggered);

	}

	void compute()
	{
	boolean negative=false;
		size_in_bits=determineBits();

		if(no<0)		// so that the while loop does not compute remainders for negative
		{			// values....this would corrupt the result array and the complement
		negative=true;		// would not be computed successfully
		no=no*(-1);
		}


	int temp=no;		//since we dont want the original data to get lost...its good programming practice

		int refreshing_factor=2;
		size_in_bits+=refreshing_factor;	// since (2)=10 ...using this (2)=0010...a little nice to see
							// it can be changed as per wish but it should be greater than
							// or equal to 1..since if negative no...then we need atleast
							// one extra bit to indicate that it is a -ve no.
		result=new int[size_in_bits];

		/*-------------------------------------------------------------------------------------------
		for(int i=0;i<size_in_bits;i++)	JAVA default initializes array objects to their default
		result[i]=0;			values...and for int it is 0.
		---------------------------------------------------------------------------------------------*/

		int pointer=size_in_bits-1;	//to start from last so that we do not have to reverse array
						//for the answer to be in correct form..set to 0 and try


		/*----------------LOGIC TO COMPUTE BINARY-------------------*/
			while(temp!=0)
			{
			result[pointer]=temp%2;
			temp=temp/2;
			pointer--;
			}

		if(negative)
		complement();	//if negative then we have to display the 2's complement												
	}

	void complement()
	{
		for(int i=0;i<size_in_bits;i++)
		result[i]=1-result[i];	//first compute the 1's complement of the no..[x_complement={base-1}-bit]

			//display();	DEBUGGING

			/*-------2's complement-------*/

		int carry=0;

		int sum=0;
		int pointer=size_in_bits-1;
			sum=result[pointer]+1;	//initial permutation

		while(true)	//cant put pointer>=0 over here...algo fails when value is 1 for last two lines
		{
			switch(sum)		//since we are only adding carry to one no..sum is in range [0,2]
			{
			case 0:	sum=0;		//CORE LOGIC
				carry=0;
				break;
			case 1: sum=1;
				carry=0;
				break;
			case 2:
				sum=0;
				carry=1;
				break;
			}

		result[pointer--]=sum;	//assign it with the value and decrement pointer
				
			if(pointer>=0)	//check if end reached else compute for next element
			sum=result[pointer]+carry;
			else
			break;
		}
		
	}

	int determineBits()
	{
	int power=0;

			while(Math.abs(no)>=Math.pow(2,power))	//abs since we only want +ve nos dealing
			power++;				//and since we already add refreshing factor
								//extra bit required by -ve values is taken care of

		//System.out.println("No of bits required is:"+power);	DEBUGGING

	return power;
	}

	void display()
	{
		for(int i=0;i<size_in_bits;i++)
		System.out.print(result[i]);
	}

	
}
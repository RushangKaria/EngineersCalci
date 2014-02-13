import java.io.*;

class EigenVariable
{
	int sign;
	int value;
	char name;
	int power;

	EigenVariable()
	{
	value=0;
	name='X';
	power=1;
	}
}



public class EigenValues
{
	static InputStreamReader r;
	static BufferedReader input;
	int a[][];
	int answer[];
	int determinant;
	int cross_product;

	EigenValues()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);

	a=new int[3][3];
	answer=new int[3];

		initialize();

	}

	void initialize()throws IOException	
	{
		fill_matrix();
		determinant=calculate_determinant();
		cross_product=calculate_cross_product();

	EigenVariable v[];
	v=formEquation();
	int index=0;
	int x;

		while(index<3)
		{
		x=computeEigenVariable(v);

			if(x==-134)
			{
			System.out.println("ERROR..calculator not powerful enough to solve this equation");
			return;
			}

			answer[index++]=x;
			v=synthetic_division(x,v);
			
		}

	display();
	}

	int computeEigenVariable(EigenVariable v[])
	{
	int trial_value=-50;		//if you want to increase the range of trial_value make sure you change
	int sum=0;			//the type to double to handle the large powers of large numbers

		while(trial_value<50)
		{
		sum=0;

			for(int i=0;i<v.length;i++)
			{
			int a=v[i].value;
			int b=(int)Math.pow(trial_value,v[i].power);	//if not type cast loss of precision error
			int x=a*b;					//even though it is correct
			sum+=x;
			//System.out.println("sum is "+sum+" trial_value "+trial_value);
			}

		if(sum==0)
		return trial_value;
		else
		trial_value++;		
		}

	return -134;	
	}

	EigenVariable[] synthetic_division(int x,EigenVariable v[])
	{
	EigenVariable temp[]=new EigenVariable[v.length-1];

	temp[0]=new EigenVariable();
	temp[0].value=v[0].value;

		for(int i=1;i<v.length-1;i++)
		{
		temp[i]=new EigenVariable();
		temp[i].value=x*temp[i-1].value+v[i].value;
		}


	int power=0;

		for(int i=temp.length-1;i>=0;i--)
		temp[i].power=power++;

		//System.out.println(temp[2].value); DEBUGGING
	return temp;
	}

	EigenVariable[] formEquation()
	{
	EigenVariable v[]=new EigenVariable[4];
	for(int i=0;i<4;i++)
	v[i]=new EigenVariable();

		v[0].value=1;
		v[1].value=(a[0][0]+a[1][1]+a[2][2])*(-1);
		v[2].value=cross_product;
		v[3].value=determinant*(-1);


	int power=0;

		for(int i=3;i>=0;i--)
		v[i].power=power++;

	return v;
	}

	void fill_matrix()throws IOException
	{
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
			System.out.println("Enter the element at index ["+i+","+j+"]:");
			a[i][j]=Integer.parseInt(input.readLine());
			}
	}

	int calculate_determinant()
	{
	int x=0;

		int x1=determinant(splitMatrix(0));
		int x2=determinant(splitMatrix(1));
		int x3=determinant(splitMatrix(2));


		//System.out.println(x1+"---"+x2+"---"+x3+"---"); DEBUGGING

			x=a[0][0]*x1+a[0][1]*(-1)*x2+a[0][2]*x3;

	//System.out.println("==="+determinant); DEBUGGING

	return x;
	}

	int determinant(int a[][])
	{
	int determinant=-1;
			/*
			DETERMINANT OF |A	B|
				       |C	D|====(AD-BC)
			*/

		determinant=(a[0][0]*a[1][1])-(a[0][1]*a[1][0]);	

	return determinant;
	}

	int [][] splitMatrix(int cut_column)
	{
	int x,y;
	x=0;
	y=0;
		int b[][]=new int[2][2];

		for(int i=1;i<3;i++)
			for(int j=0;j<3;j++)
				if(j==cut_column)
				continue;
				else
				{
				//System.out.println("b["+x+"]["+y+"]="+a[i][j]);  DEBUGGING
	
				b[x][y++]=a[i][j];
				
					if(y>1)		//y>=1 causes error
					{
					y=0;
					x=1;
					}
				}
/* #########################DEBUGGING##############################

	System.out.println("=========================");
	for(int i=0;i<2;i++)
	{
		for(int j=0;j<2;j++)
		System.out.print(b[i][j]+"  ");

	System.out.println("");
	}

################################################################ */ 

	return b;
	}

	int calculate_cross_product()
	{
	
		int x=(a[0][0]*a[1][1])+(a[1][1]*a[2][2])+(a[0][0]*a[2][2]);
		int y=(a[0][1]*a[1][0])+(a[0][2]*a[2][0])+(a[1][2]*a[2][1]);
		int z=x-y;

	return z;
	}
	void display()
	{
		System.out.println("The eigen values are:");
		for(int i=0;i<2;i++)
		System.out.print(answer[i]+",");
		System.out.print(answer[2]);
	}
}


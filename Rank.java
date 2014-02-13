import java.io.*;

public class Rank
{
	static InputStreamReader r;
	static BufferedReader input;

	Rank()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);

		driver();
	}

	void driver()throws IOException
	{
	double a[][];
	
		System.out.println("Enter the order of the matrix");
		int m=Integer.parseInt(input.readLine());

	a=takeInput(m);

	double rank=computeRank(a,m);

	}

	int computeRank(double a[][],int m)
	{
	int result=-1;

	//matrixDisplay(a,m);

		for(int i=0;i<m;i++)
		{
		if(a[i][i]==0)	
		continue;

		if(a[i][i]!=1)
		a=divide(a,i,m);

		//matrixDisplay(a,m);
			
			a=subtract(i,a,m);	
		}

	matrixDisplay(a,m);

	a=columnTransforms(a,m);

	matrixDisplay(a,m);

	return result;
	}

	double[][] columnTransforms(double a[][],int m)
	{
	double x[][]=a;

		for(int i=0;i<m;i++)
		{
		if(a[i][i]==0)	
			continue;


		if(a[i][i]!=1)
		a=divide_columns(a,i,m);

		//matrixDisplay(a,m);
			
			a=subtract_columns(i,a,m);	
		}		

	return x;
	}

	double[][] switcher(double a[][],int updated,int old,int m)
	{
	double x[][]=a;
	double temp[]=new double[m];

		for(int i=0;i<m;i++)
		{
		temp[i]=x[old][i];
		x[old][i]=x[updated][i];
		x[updated][i]=temp[i];
		}


	return x;
	}

	double[][] switcher_columns(double a[][],int updated,int old,int m)
	{
	double x[][]=a;
	double temp[]=new double[m];

		for(int i=0;i<m;i++)
		{
		temp[i]=x[i][old];
		x[i][old]=x[i][updated];
		x[i][updated]=temp[i];
		}


	return x;
	}

	double[][] subtract(int row,double temp[][],int m)
	{
	double x[][]=temp;
	double MF;
	int sign=1;

	int column=row;

		for(int i=0;i<m;i++)
		{
		if(i==row)
		continue;

		MF=temp[i][column];
	
			for(int j=0;j<m;j++)
			{
			x[i][j]=temp[i][j]-(temp[row][j]*MF);
			}
		}

	return x;
	}

	double[][] subtract_columns(int column,double temp[][],int m)
	{
	double x[][]=temp;
	double MF;
	int sign=1;

	int row=column;

		for(int i=0;i<m;i++)
		{
		if(i==row)
		continue;

		MF=temp[row][i];
	
			for(int j=0;j<m;j++)
			{
			x[j][i]=temp[j][i]-(temp[j][row]*MF);
			}
		}

	return x;
	}

	double[][] divide(double temp[][],int row,int m)
	{
	double x[][]=temp;

	double DIVISOR=temp[row][row];

	//System.out.println(DIVISOR);

		for(int i=0;i<m;i++)
		x[row][i]=temp[row][i]/DIVISOR;

	return x;
	}

	double[][] divide_columns(double temp[][],int column,int m)
	{
	double x[][]=temp;

	double DIVISOR=temp[column][column];

	//System.out.println(DIVISOR);

		for(int i=0;i<m;i++)
		x[i][column]=temp[i][column]/DIVISOR;

	return x;
	}

	double[][] takeInput(int m)
	{
	double temp[][]=new double[m][m];

	System.out.println("Enter the elements of the matrix");

		try
		{	

			for(int i=0;i<m;i++)
				for(int j=0;j<m;j++)
				{
				System.out.println("Enter the element at ["+i+","+j+"]");
				temp[i][j]=Double.parseDouble(input.readLine());
				}
		     
		}
		catch(Exception e)
		{
		System.out.println("ERROR...please enter elements properly");
		}
	
	//matrixDisplay(temp,m);
	
	return temp;

	}

	void matrixDisplay(double temp[][],int m)
	{
		for(int i=0;i<m;i++)
		{
		System.out.print("|");

			for(int j=0;j<m;j++)
			if(j<m-1)
			System.out.print(temp[i][j]+"\t");
			else
			System.out.print(temp[i][j]);			
			

		System.out.println("|");
		}

	System.out.println("-------------------------------------------------------------------------");
	}
}
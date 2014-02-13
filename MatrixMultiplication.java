import java.io.*;

class Matrix
{
	int a[];
	int b[][];
	int m,n;

	BufferedReader input=new BufferedReader(new InputStreamReader(System.in));

	Matrix()
	{

	}

	Matrix(int p)throws IOException
	{
	a=new int[p];
	m=p;
	
	input(p);
	}

	Matrix(int p,int q)throws IOException
	{
	b=new int[p][q];
	m=p;
	n=q;

		input(p,q);
	}

	void input(int p)throws IOException
	{

		for(int i=0;i<p;i++)
		{
		System.out.println("Enter the element at ["+i+"]:");
		a[i]=Integer.parseInt(input.readLine());
		}
	}

	void input(int p,int q)throws IOException
	{
		for(int i=0;i<p;i++)
			for(int j=0;j<q;j++)
			{
			System.out.println("Enter the element at ["+i+","+j+"]:");
			b[i][j]=Integer.parseInt(input.readLine());
			}
	}

	void display(int a[],int n)
	{
	System.out.print("[ ");
		
		for(int i=0;i<n;i++)
		System.out.print(a[i]+"   ");

	System.out.println("]");
	}

	void display(int a[][],int m,int n)
	{
	String x=new String("|");

		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			{
			if(j==0)
			System.out.print("[ ");

				System.out.print(a[i][j]+"   ");

			if(j==n-1)
			System.out.print("]");

			}
		System.out.println("");
		}	
	}

}


public class MatrixMultiplication
{
	static InputStreamReader r;
	static BufferedReader input;
	
	Matrix matrix[];
	int matrix_size;

	MatrixMultiplication()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);

	matrix=new Matrix[100];
	matrix_size=0;

		initialize();
		
	}

	void initialize()throws IOException
	{
		take_input(0);
		take_input(1);

		if(!possible())
		{
		System.out.println("ERROR...matrices cannot be multiplied...make sure they are of the type mxn..nxp");
		return;
		}

		Matrix answer=multiply();

	System.out.println("=================ANSWER=================");		
	answer.display(answer.b,answer.m,answer.n);
	}

	void take_input(int index)throws IOException
	{

	int m,n;

		System.out.println("Enter the order of matrix "+index);
		m=Integer.parseInt(input.readLine());
		n=Integer.parseInt(input.readLine());

		matrix[matrix_size++]=new Matrix(m,n);

		System.out.println("=================MATRIX "+index+"=================");

		matrix[index].display(matrix[index].b,matrix[index].m,matrix[index].n);

	}

	boolean possible()
	{
		if(matrix[0].n==matrix[1].m)
		return true;

	return false;
	}

	Matrix multiply()
	{
	Matrix a=new Matrix();
	a.m=matrix[0].m;
	a.n=matrix[1].n;
	a.b=new int[a.m][a.n];

		for(int i=0;i<matrix[0].m;i++)
			for(int j=0;j<matrix[1].n;j++)
			{
			a.b[i][j]=0;
				for(int k=0;k<matrix[1].m;k++)
				a.b[i][j]+=matrix[0].b[i][k]*matrix[1].b[k][j];
			}

	return a;
		
	}

		
}
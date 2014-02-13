import java.io.*;

class ComplexNo
{
	double real,imaginary;

	ComplexNo()
	{
	real=0;
	imaginary=0;
	}
}

class ComplexConverter
{
	static InputStreamReader d;	
	static BufferedReader input;

	ComplexNo cx;
	double r,theta;
	char angle;

	ComplexConverter()throws IOException
	{
	d=new InputStreamReader(System.in);
	input=new BufferedReader(d);

	r=0;
	theta=0;

	cx=new ComplexNo();
	angle='/';

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
	System.out.println("FORM x+iy");

		System.out.println("Enter x");
		cx.real=Double.parseDouble(input.readLine());	

		System.out.println("Enter y");
		cx.imaginary=Double.parseDouble(input.readLine());
	}

	void compute()
	{	
	double x=cx.real;
	double y=cx.imaginary;

		r=Math.sqrt((x*x)+(y*y));
		theta=Math.atan((y/x));	// NOTE: theta is in radians

		theta*=(180/Math.PI);
	}

	void display()
	{
	System.out.println(r+""+angle+""+theta);
	}




}
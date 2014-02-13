import java.io.*;

class CircleVariable
{
	//int sign;
	int value;
	char name;
	int power;

	CircleVariable()
	{
	value=0;
	name='X';
	power=1;
	}
}

public class CirclePointChecker
{
	static InputStreamReader r;
	static BufferedReader input;

	int x,y,radius;

	CirclePointChecker()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);

	x=0;
	y=0;
	radius=0;

		start();		
	}

	void start()throws IOException
	{
	take_input();

	CircleVariable c[];

		c=formEquation();

	int x1,y1;

	System.out.println("Enter the x-point");
	x1=Integer.parseInt(input.readLine());

	System.out.println("Enter the y-point");
	y1=Integer.parseInt(input.readLine());

	String result;


		result=check_where_point_lies(c,x1,y1);

	printResult(result,x1,y1);
	}

	String check_where_point_lies(CircleVariable c[],int x1,int y1)
	{
	int sum=0;
	int temp[]={x1,y1};

/* ========================

	int temp[]=new int[2];
	temp[0]=x1;
	temp[1]=y1;

==========================*/

		for(int i=0;i<c.length;i++)
		{
		int x=c[i].value;
		int y=(int)Math.pow(temp[i%2],c[i].power);
		int z=x*y;
		sum+=z;
		}

	if(sum==0)
	return "on";
	else
	if(sum<0)
	return "inside";
	else
	return "outside";

		//return "outside".toUpperCase(); DO IT FOR ALL RETURNS...JUST FOR EXTRA LEARNING...CHECK PRINT METHOD		
	}	//				  FOR A MORE CONVINIENT AND SMART WAY

	void take_input()throws IOException
	{
		System.out.println("Enter the x-center of the circle");
		x=Integer.parseInt(input.readLine());

		System.out.println("Enter the y-center of the circle");
		y=Integer.parseInt(input.readLine());

		System.out.println("Enter the radius of the circle");
		radius=Integer.parseInt(input.readLine());

			if(radius==0)
			{
			System.out.println("CIRCLE IS A POINT");
			return;
			}
			else
				if(radius<0)
				{
				System.out.println("ERROR...RADIUS CANNOT BE -VE");
				return;
				}

		//System.out.println(x+""+y+""+radius);	 DEBUGGING
	}

	CircleVariable[] formEquation()
	{
	int _2gx,_2fy,c;
	boolean flipper=false;

	_2gx=2*x*(-1);
	_2fy=2*y*(-1);
	c=x*x+y*y-radius*radius;

	int temp[]={1,1,_2gx,_2fy,c};
	
/* ---------------OR------------------

	int temp[]=new int[5];
	temp[0]=1;
	temp[1]=1;
	temp[2]=_2gx;
	temp[3]=_2fy;
	temp[4]=c;

---------------------------------------*/		

	//System.out.println("2g="+_2gx+"\n2f="+_2fy+"\nc="+c); DEBUGGING

		CircleVariable z[]=new CircleVariable[5];
		for(int i=0;i<5;i++)
		z[i]=new CircleVariable();

	int power=2;

			for(int i=0;i<5;i++)
			{
			z[i].value=temp[i];
			z[i].power=power;

				if(flipper)
				{
				z[i].name='Y';
				flipper=false;
				power--;
				continue;
				}
			flipper=true;
			}

	//displayEquation(z);	IF YOU WANT TO DISPLAY THE EQUATION


	return z;			
	}

	void displayEquation(CircleVariable z[])
	{
	System.out.println("The Equation is:");

		for(int i=0;i<5;i++)
		if(z[i].value>=0)
		System.out.print("+"+z[i].value+""+z[i].name+""+z[i].power+"");
		else
		System.out.print(z[i].value+""+z[i].name+""+z[i].power+"");

		System.out.print("=0");

	System.out.println("");
	}

	void printResult(String result,int x1,int y1)
	{
	result=result.toUpperCase();
	System.out.println("Point ("+x1+","+y1+") is "+result+" the circle");

		//System.out.println("Point ("+x1+","+y1+") is "+result.toUpperCase()+" the circle"); if 1st line is
		//						--------------------		      not to be included
	}
}


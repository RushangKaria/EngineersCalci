/*
########################REASON FOR USING DOUBLE EVERYWHERE###########################

	During the calculation of inverse matrix
	if everything was int
	then
	a[i][j]/determinant=0 if determinant > a[i][j]
	therefore this would create errors in the result
	since even the decimal pts are and not just the quotient	
	for the eqns to be consistent

	eg. 1X+1Y=3
	    1X-1Y=1

		would give 
	    X=0		correct is X=2
	    Y=0		correct is Y=1

	therefore everything defined as double so that
	a[i][j]/determinant give correct decimal answers 
	and also enables input of ?.?X+?.?Y=?.? 
#########################################################################################
*/



import java.io.*;

class Variable		//NOTE:this class is not public therefore it is available only to this class		
{			//it makes sense since only x+y=d only comes from linear eqns and not for other functions
	double x,y,d;
				/*
				This class is made so that 3 values can be returned from a function	
				Basically we only need to manipulate the object and return it
				to get the values of x,y,d
				ALL VALUES DECLARED DOUBLE SO THAT EVEN ?.?X+?.?Y=?.? EQNS ARE VALID
				*/
	Variable()		
	{					
	x=0;			
	y=0;			
	d=0;						
	}			
} 
/*-------------------------------------------------------------------------------------------------------------*/

public class LinearEquations	//declared public since it is what the main module will call
{
	static InputStreamReader r;
	static BufferedReader input;

	LinearEquations()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);
		
		initialize();
	}
/*-------------------------------------------------------------------------------------------------------------*/

	void initialize()throws IOException
	{
	String e1=new String();
	String e2=new String();

		System.out.println("Please enter the equations in the form ?X+-?Y=?");
		e1=input.readLine();
		e2=input.readLine();

	compute_variable(e1,e2);
	}
/*-------------------------------------------------------------------------------------------------------------*/

	void compute_variable(String e1,String e2)
	{
		Variable v1=new Variable();
		Variable v2=new Variable();	


	
	v1=parser(v1,e1);

	//System.out.println(v1.x+"  "+v1.y+"  "+v1.d); DEBUGGING

	v2=parser(v2,e2);

	//System.out.println(v2.x+"  "+v2.y+"  "+v2.d); DEBUGGING

	compute(v1,v2);
	
	}
/*-------------------------------------------------------------------------------------------------------------*/

	Variable parser(Variable v,String eqn)	//to fill Variable with values
	{
	int pointer=0;	//for getting the index of 'X','Y'
	int operation_ptr=0;	//for getting the index of '='

		pointer=eqn.indexOf('X');	//first parse till X
		v.x=Double.parseDouble(eqn.substring(0,pointer)); //since substring =0-till-X=> EXCLUDING X
	
	pointer++; //since after pointer obviously it is +- acc to ?X+-?Y=?
	
		operation_ptr=pointer; //copy pointer to operation_ptr so that pointer can be changed to point to Y
		pointer=eqn.indexOf('Y');

		v.y=Double.parseDouble(eqn.substring(operation_ptr,pointer)); //from ('+/-')till-Y=>EXCLUDING Y

	operation_ptr=eqn.indexOf('=');
	pointer=eqn.length();
	v.d=Double.parseDouble(eqn.substring(operation_ptr+1,pointer));//from '='+1 till end of (String-1) including
									/*
									NOTE:no need for sign here as if =-34
									then substring is -34
									which is parsed as -34!!
									*/

	return v;
	}
/*-------------------------------------------------------------------------------------------------------------*/

	void compute(Variable v1,Variable v2)
	{
	double matrix_a[][]=new double[2][2];	//original matrix 2*2
	double inverse[][]=new double[2][2];	//inverse matrix  2*2
	double variable[]=new double[2];	//variable matrix 2*1 simulated here by 1-D array
	double result[]=new double[2];		//result matrix   2*1 simulated here by 1-D array

		double determinant=-1;		//initially set determinant to -1

		matrix_a=fill(v1,v2);		//fill both the matrices with
		variable=fill(v1.d,v2.d);	//their initial values

		determinant=determinant(matrix_a);	//find the determinant of the matrix
							//NOTE:determinant only possible for square matrix!!
							//therefore matrix_a always =[n][n]

	if(determinant==0)	//if indeterminate then print and end
	{
		System.out.println("EQUATIONS CANNOT BE SOLVED...");
		return;
	}
	//else...not reqd here since once if-loop executes program ends or if if-loop doesnt
	//execute then next statements will automatically get executed

		inverse=inverse(matrix_a,determinant);	//to compute inverse
		result=compute(inverse,variable);	//get the result

	System.out.println("========================ANSWER IS==============================");
	System.out.println("X="+result[0]);
	System.out.println("Y="+result[1]);

	}
/*-------------------------------------------------------------------------------------------------------------*/


	double [][] fill(Variable v1,Variable v2)
	{
	double a[][]=new double[2][2];
	double v[]=new double[2];
					/*
					MATRIX FILLED AS
					|X1	Y1|
					|X2	Y2|
					*/					


		a[0][0]=v1.x;		
		a[0][1]=v1.y;
		a[1][0]=v2.x;
		a[1][1]=v2.y;

	return a;
	}
/*-------------------------------------------------------------------------------------------------------------*/

	double [] fill(double d1,double d2)
	{
	double a[]=new double[2];
					/*
					MATRIX FILLED AS
					|D1|
					|D2|
					*/
	a[0]=d1;
	a[1]=d2;
	
	return a;	

	}
/*-------------------------------------------------------------------------------------------------------------*/

	double determinant(double a[][])
	{
	double determinant=-1;
			/*
			DETERMINANT OF |A	B|
				       |C	D|====(AD-BC)
			*/

		determinant=(a[0][0]*a[1][1])-(a[0][1]*a[1][0]);	

	return determinant;
	}
/*-------------------------------------------------------------------------------------------------------------*/

	double [][] inverse(double a[][],double determinant)
	{
	double inverse[][]=new double[2][2];

		/*
		INVERSE OF |A	B|
			   |C	D|
		IS
	
		(1/|A|)*|D     -B|
			|-C	A|

		*/

		inverse[0][0]=(a[1][1]/determinant);	//NOTE:If double were not used then type conversion 	
		inverse[1][1]=(a[0][0]/determinant);	//would be reqd here (double)a[1][1].......		

		inverse[0][1]=((a[0][1]*(-1))/determinant);
		inverse[1][0]=((a[1][0]*(-1))/determinant);

						/*-----DEBUGGING TO CHECK IF INVERSE IS PROPER--------
						for(int i=0;i<2;i++)
						{
							for(int j=0;j<2;j++)
							System.out.print(inverse[i][j]+"     ");
						
						System.out.println("");
						}
						------------------------------------------------------*/

	return inverse;

	}
/*-------------------------------------------------------------------------------------------------------------*/

	double [] compute(double a[][],double b[])
	{
	int i,j;
	double result[]=new double[2];

	/*
	since matrices to be multiplied are of the form 2*2 and 2(1-D)ie 2*1 we dont need three for loops

	normally,for m*n n*p
		for(i=0;i<m;i++)
			for(j=0;j<p;j++)
			c[i][j]=0;
				for(k=0;k<n;k++)
				c[i][j]+=a[i][k]*b[k][j]

	since here j<1 that means the j for loop gets executed only once and therefore can be omitted
	*/


		for(i=0;i<2;i++)
		{
		result[i]=0;	
			for(j=0;j<2;j++)	
			{
			result[i]+=a[i][j]*b[j];
			//System.out.println("a["+i+"]["+j+"]="+a[i][j]+"b["+j+"]="+b[j]+"r="+result[i]);DEBUGGING...
			}
		
		}

	return result;	
	}
/*-------------------------------------------------------------------------------------------------------------*/

}
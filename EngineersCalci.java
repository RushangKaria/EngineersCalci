/*
#######################################ENGINEER'S CALCI v 6.9 ######################################################
Changelog:-
------------
1. The Calci is extended to perform Binary, Octal and Hex Conversions

REVISION 6.9.1
1. Decimal to Binary conversion added	

REVISION 6.9.2
1. Hex Calculator added
   It is possible to convert hex to decimal values

###################################################################################################################
*/

import java.io.*;

class EngineersCalci
{
	static BufferedReader input=new BufferedReader(new InputStreamReader(System.in));

	public static void main(String args[])throws IOException
	{

		System.out.println("--------------- MENU ---------------");
		System.out.println("1. Linear Equations");
		System.out.println("2. Factorial");
		System.out.println("3. Matrix multiplication");
		System.out.println("4. Eigen Values");
		System.out.println("5. Check if a point is inside the circle");
		System.out.println("6. Permutation");
		System.out.println("7. Combination");
		System.out.println("8. Quadratic Equations");
		System.out.println("9. Nth root of a no");
		System.out.println("10. Check if a no is prime");
		System.out.println("11. Generate prime nos");
		System.out.println("12. Find the rank of a matrix");
		System.out.println("13. Complex calculator");
		System.out.println("14. Hex calculator");
		System.out.println("15. Linear Equations");
		System.out.println("Enter your option");


	
	int x=Integer.parseInt(input.readLine());

			switch(x)
			{
				case 1: LinearEquations d=new LinearEquations();
				break;

				case 2: Factorial f=new Factorial();
				break;

				case 3: MatrixMultiplication m=new MatrixMultiplication();
				break;

				case 4: EigenValues dfx=new EigenValues();
				break;

				case 5: CirclePointChecker c=new CirclePointChecker();
				break;

				case 6: Permutation p=new Permutation();
				break;

				case 7: Combination cdf=new Combination();
				break;

				case 8: QuadraticEquations q=new QuadraticEquations();
				break;

				case 9: NthRoot n=new NthRoot();
				break;

				case 10: IsPrime xfd=new IsPrime();
				break;

				case 11: GeneratePrime g=new GeneratePrime();
				break;

				case 12: Rank r=new Rank();
				break;

				case 13: ComplexConverter cadf=new ComplexConverter();
				break;

				case 14: HexCalculator adf=new HexCalculator();
				break;

				case 15: LinearEquations l=new LinearEquations();
				break;

			}
	


	
	//Logic l=new Logic();

	//ArithmeticProgression p=new ArithmeticProgression();
	

	
	}
}
import java.io.*;

public class Logic
{
	static InputStreamReader r;
	static BufferedReader input;

	static String symbol[];

	Logic()throws IOException
	{
	r=new InputStreamReader(System.in);
	input=new BufferedReader(r);
	
	initialize();
		driver();
	}

	void initialize()
	{
	symbol=new String[4];
	
		symbol[0]="^";
		symbol[1]="U";
		symbol[2]="->";
		symbol[3]="<->";
	}

	void driver()throws IOException
	{
	String x;
	System.out.println("Please enter the logic statement");
	x=input.readLine();

		boolean valid;
			
		valid=ensureValidity(x);

		if(!valid)
		{
		System.out.println("NOT A VALID STATEMENT");
		return;
		}

	pass2(x);
	
	}

	boolean ensureValidity(String x)
	{
	boolean operator=false;
	boolean operand=false;
	int depth=0;

		for(int i=0;i<x.length();i++)
		{
		String temp=x.substring(i,i+1);

		if(temp.equals("("))
		{
		depth++;
		continue;
		}
		else 
		if(temp.equals(")"))
		{
		depth--;
			if(depth<0)
			return false;
			
		continue;
		}

			if(isSpecial(temp,i,x))
			{
				int value=(int)temp.charAt(0);
			
					if(value==(int)'-')
					i++;
					  else
					    if(value==(int)'<')
					    i+=2;
					
				if(!operator)
				{
				operator=true;
				operand=false;
				}
				else
				return false;
			}
			else
			{
				if(!validGrammar(temp))
				return false;
					
					if(!operand)
					{
					operand=true;
					operator=false;
					}
					else
					return false;
			}
		}		
	
		if(depth==0)
		return true;

	return false;
	}

	boolean validGrammar(String temp)
	{

	int x=(int)temp.charAt(0);
		
		if(x>=(int)'a'&&x<=(int)'z')
		return true;

	return false;
	}

	boolean isSpecial(String temp,int index,String original)
	{
		if(temp.equals("^")||temp.equals("U"))
			if((int)original.charAt(index+1)>=(int)'a'||(int)original.charAt(index+1)<=(int)'z')
			return true;

		if(temp.equals("-")&&(original.substring(index,index+2)).equals("->"))
		return true;

		if(temp.equals("<")&&(original.substring(index,index+3)).equals("<->"))
		return true;

	return false;

	}

	void pass2(String x)throws IOException
	{
	int no=0;
	char variables[];
	variables=getVariables(x);

	for(int i=0;i<variables.length;i++)
	if((int)variables[i]!=0)
	no++;
	else
	break;

		generateTruthTable(x,no,variables);
	}

	char[] getVariables(String x)
	{
	char temp[]=new char[100];
	int temp_pointer=0;

		for(int i=0;i<x.length();i++)
	loop:	{
		char c=x.charAt(i);
		if(!((int)c>=(int)'a'&&(int)c<=(int)'z'))
		continue;

			for(int j=0;j<10;j++)
			{
			//System.out.println("COMPARING "+c+" AND "+temp[j]+" -->"+(c-temp[j]));   DEBUGGING
			if(c==temp[j])
			break loop;
			}

		temp[temp_pointer++]=c;	
		}
/*************************************************DEBUGGING*********************************************************

	for(int i=0;i<temp_pointer;i++)
	System.out.print(temp[i]+"  ");
	
	System.out.println("TEMP_POINTER= "+temp_pointer);

*********************************************************************************************************************/
	
	return temp;
	}

	void generateTruthTable(String x,int no,char variables[])throws IOException
	{
	String output=new String("result=");

		for(int i=0;i<x.length();i++)
		{
			if(x.charAt(i)=='('||x.charAt(i)==')')
			{
			output+=x.charAt(i);
			continue;	
			}

			switch((int)x.charAt(i))
			{
			case (int)'^': output+="&";
			continue;

			case (int)'U': output+="|";
			continue;

			case (int)'<': output+="!^";
				       i+=2;
			continue;
			}

			for(int j=0;j<no;j++)
			if(variables[j]==x.charAt(i))
			output+="variables["+j+"]"; 
			
		}
		
	//System.out.println(output);
	
		makeFile(output,no,variables);
	}

	void makeFile(String output,int no,char variables[])throws IOException
	{
		
	String n1="int variables[]=new int["+no+"];";
	String n2="int truth_table=new int["+(int)Math.pow(2,no)+"]["+no+"];";
	String n3="int no=variables.length;";
	String n4="int no_of_rows=(int)Math.pow(2,no);";
	String n5="\tfor(int column=0;column<no;column++)\n\t\tfor(int row=0;row<no_of_rows;row++)"
		  +"\n\t\t\tif(j<no_of_rows/2)\n\t\t\ttruth_table[row][column]=1;"
		  +"\n\t\t\telse\n\t\t\ttruth_table[row][column]=0;";

	System.out.println(n1+"\n"+n2+"\n"+n3+"\n"+n4+"\n\n\n"+n5);

	 	 FileWriter fstream = new FileWriter("out.txt");
  		BufferedWriter out = new BufferedWriter(fstream);
 		 out.write("Hello Java");
	}
}
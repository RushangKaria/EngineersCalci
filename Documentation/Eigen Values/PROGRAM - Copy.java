
	void initialize()throws IOException	
	{

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

	}
}


package Task1;

public abstract class Phone
{
	String phoneNumber;
	
	abstract void dial(int dialNumber);
	
	public int ring()
	{
		System.out.println("Method Ring");
		return 0;
	}
	
}

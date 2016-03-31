package Task1;

public class SmartPhone extends Phone implements BasicFeatures
{
	private int antenna[];
	public String mic;
	
	public SmartPhone(String welcomeMessage)
	{
		System.out.println("Constructor");
	}
	
	public void dial(int dialNumber)
	{
		System.out.println("Method Dial");
	}

	public void call(String callNumber)
	{
		System.out.println("Method Call");
	}

	public String text(String message, String messageNumber)
	{
		System.out.println("Method Text");
		return null;
	}
	
	public void respondToTouch(int[] touches)
	{
		System.out.println("Method Respond To Touch");
	}
	
	private int bootUp()
	{
		System.out.println("Method Boot Up");
		return 0;
	}
}

package Task2;

public class OrderRunnable implements Runnable
{
	private PizzaMakingMachine pizzaMakingMachine;
	private String order;
	private int DELAY = 5000;
//	private int DELAY = 0;

	public OrderRunnable(PizzaMakingMachine pizzaMakingMachine, String order)
	{
		this.pizzaMakingMachine = pizzaMakingMachine;
		this.order = order;
	}

	public void run()
	{
		try
		{
			// forever create the requested pizza
			while (true)
			{
				if("Mushroom".equals(order))
				{
					pizzaMakingMachine.createPizzaMushroom(order);
				}
				else if("Anchovy".equals(order))
				{
					pizzaMakingMachine.createPizzaAnchovy(order);
				}
				
				Thread.sleep(DELAY);
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}

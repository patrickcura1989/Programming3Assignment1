package Task2;

public class OrderRunnable implements Runnable
{
	private PizzaMakingMachine pizzaMakingMachine;
	private String order;
	private int DELAY = 5000;

	public OrderRunnable(PizzaMakingMachine pizzaMakingMachine, String order)
	{
		this.pizzaMakingMachine = pizzaMakingMachine;
		this.order = order;
	}

	public void run()
	{
		try
		{
			while (true)
			{
				pizzaMakingMachine.createPizza(order);
				Thread.sleep(DELAY);
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}

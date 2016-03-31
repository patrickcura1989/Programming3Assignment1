package Task2;

public class ReplenishSuppliesRunnable implements Runnable
{
	private PizzaMakingMachine pizzaMakingMachine;
	private String ingredient;
	private int DELAY = 5000;

	public ReplenishSuppliesRunnable(PizzaMakingMachine pizzaMakingMachine, String ingredient)
	{
		this.pizzaMakingMachine = pizzaMakingMachine;
		this.ingredient = ingredient;
	}

	public void run()
	{
		try
		{
			while(true)
			{
				pizzaMakingMachine.replenishIngredient(ingredient);
				Thread.sleep(DELAY);
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

	}

}

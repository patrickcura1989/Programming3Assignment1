package Task2;

public class ReplenishSuppliesRunnable implements Runnable
{
	private PizzaMakingMachine pizzaMakingMachine;
	private String ingredient;
	private int DELAY = 5000;
//	private int DELAY = 0;

	public ReplenishSuppliesRunnable(PizzaMakingMachine pizzaMakingMachine, String ingredient)
	{
		this.pizzaMakingMachine = pizzaMakingMachine;
		this.ingredient = ingredient;
	}

	public void run()
	{
		try
		{
			// forever replenish the said ingredient
			while(true)
			{
				if("Garlic".equals(ingredient))
				{
					pizzaMakingMachine.replenishGarlic(ingredient);
				}
				else if("Olives".equals(ingredient))
				{
					pizzaMakingMachine.replenishOlives(ingredient);
				}
				else if("Mushrooms".equals(ingredient))
				{
					pizzaMakingMachine.replenishMushrooms(ingredient);
				}
				else if("Anchovies".equals(ingredient))
				{
					pizzaMakingMachine.replenishAnchovies(ingredient);
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

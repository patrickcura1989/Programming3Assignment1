package Task2;

public class Main
{

	public static void main(String[] args)
	{
		PizzaMakingMachine pizzaMakingMachine = new PizzaMakingMachine();

		// start the threads that will re-supply the ingredients
		for (int i = 0; i < 1; i++)
		{
			ReplenishSuppliesRunnable g = new ReplenishSuppliesRunnable(pizzaMakingMachine, "Garlic");
			Thread gt = new Thread(g);
			gt.start();

			ReplenishSuppliesRunnable o = new ReplenishSuppliesRunnable(pizzaMakingMachine, "Olives");
			Thread ot = new Thread(o);
			ot.start();

			ReplenishSuppliesRunnable m = new ReplenishSuppliesRunnable(pizzaMakingMachine, "Mushrooms");
			Thread mt = new Thread(m);
			mt.start();

			ReplenishSuppliesRunnable a = new ReplenishSuppliesRunnable(pizzaMakingMachine, "Anchovies");
			Thread at = new Thread(a);
			at.start();
		}

		// orders to randomly choose from
		String order[] =
		{ "Mushroom", "Anchovy" };

		for (int i = 0; i < 10; i++)
		{

			OrderRunnable o = new OrderRunnable(pizzaMakingMachine,
					// randomly choose an order and pass it to OrderRunnable
//					order[(int) Math.floor(Math.random() * 2)]);
					order[0]);

			Thread ot = new Thread(o);
			ot.start();
		}
	}

}

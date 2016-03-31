package Task2;

public class Main
{

	public static void main(String[] args)
	{
		PizzaMakingMachine pizzaMakingMachine = new PizzaMakingMachine();

		for (int i = 0; i < 10; i++)
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
		
		String order[] = {"Mushroom","Anchovy"};
		
		for (int i = 0; i < 10; i++)
		{
			OrderRunnable o = new OrderRunnable(pizzaMakingMachine, order[(int) Math.floor(Math.random() * 2)]);
			Thread ot = new Thread(o);
			ot.start();
		}
	}

}

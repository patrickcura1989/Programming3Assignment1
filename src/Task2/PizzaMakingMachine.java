package Task2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class PizzaMakingMachine
{
	private HashMap<String, int[]> ingredients;
	private HashMap<String, int[]> pizzas;

	public PizzaMakingMachine()
	{
		ingredients = new HashMap<String, int[]>();
		ingredients.put("Garlic", new int[] { 6, 6 }); // {current amount, limit}
		ingredients.put("Olives", new int[] { 8, 8 });
		ingredients.put("Mushrooms", new int[] { 10, 10 });
		ingredients.put("Anchovies", new int[] { 6, 6 });

		pizzas = new HashMap<String, int[]>(); // contains the recipe to make the pizza i.e. how many ingredients to make it
		pizzas.put("Mushroom", new int[] { 1, 0, 4, 0 }); // {garlic, olives, mushrooms, anchovies}
		pizzas.put("Anchovy", new int[] { 2, 2, 0, 3 });
	}

	// use synchronized keyword so that no other thread can update
	public synchronized void replenishIngredient(String ingredient) throws InterruptedException
	{
		while (ingredients.get(ingredient)[0] >= ingredients.get(ingredient)[1]) // while the ingredients current amount is greater than or equal to the stock limit, wait
			wait();
		ingredients.get(ingredient)[0]++; // increment the number of the ingredient
		System.out.println(ingredient + " was replenished. Current Amount: " + ingredients.get(ingredient)[0]);
		notifyAll();
	}

	// use synchronized keyword so that the creation of another pizza will not mess up the current ingredient values
	public synchronized void createPizza(String order) throws InterruptedException
	{
		System.out.println();
		System.out.println("----------------- " + order + " pizza was ordered. Creating it... -----------------\n");
		
		// while the current amount of ingredients does not meet the requirements to make the pizza, wait
		while (ingredients.get("Garlic")[0] < pizzas.get(order)[0])
			wait();

		while (ingredients.get("Olives")[0] < pizzas.get(order)[1])
			wait();

		while (ingredients.get("Mushrooms")[0] < pizzas.get(order)[2])
			wait();

		while (ingredients.get("Anchovies")[0] < pizzas.get(order)[3])
			wait();

		// subtract the amount consumed from the current amount of the ingredients
		ingredients.get("Garlic")[0] -= pizzas.get(order)[0];
		ingredients.get("Olives")[0] -= pizzas.get(order)[1];
		ingredients.get("Mushrooms")[0] -= pizzas.get(order)[2];
		ingredients.get("Anchovies")[0] -= pizzas.get(order)[3];

		System.out.println();
		System.out.print(">>>>>>>>>>>>>>>>> " + order + " pizza was created.");

		Iterator<Entry<String, int[]>> it = ingredients.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry<String, int[]> pair = (Map.Entry<String, int[]>) it.next();
			System.out.print(" " + pair.getKey() + ": " + pair.getValue()[0]);
			//it.remove(); // avoids a ConcurrentModificationException
		}

		System.out.println("\n");

		notifyAll();
	}

}

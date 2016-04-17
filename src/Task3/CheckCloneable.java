package Task3;

/* References:
 * Eckel, B. Thinking in Enterprise Java, 3rd Edition (http://www.mindviewinc.com/Books/) 
 */

//: appendixa:CheckCloneable.java
// Checking to see if a reference can be cloned.

// Can't clone this because it doesn't override clone():
class Ordinary
{
}

// Overrides clone, but doesn't implement Cloneable:
class WrongClone extends Ordinary
{
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone(); // Throws exception
	}
}

// Does all the right things for cloning:
class IsCloneable extends Ordinary implements Cloneable
{
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}

// Turn off cloning by throwing the exception:
class NoMore extends IsCloneable
{
	public Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}
}

class TryMore extends NoMore
{
	public Object clone() throws CloneNotSupportedException
	{
		// Calls NoMore.clone(), throws exception:
		return super.clone();
	}
}

class BackOn extends NoMore
{
	private BackOn duplicate(BackOn b)
	{
		// Somehow make a copy of b and return that copy.
		// This is a dummy copy, just to make the point:
		return new BackOn();
	}

	public Object clone()
	{
		// Doesn't call NoMore.clone():
		return duplicate(this);
	}
}

// You can't inherit from this, so you can't override
// the clone method as you can in BackOn:
final class ReallyNoMore extends NoMore
{
}

//class tryToInherit extends ReallyNoMore
//{
//	
//}

public class CheckCloneable
{

	public static Ordinary tryToClone(Ordinary ord)
	{
		String id = ord.getClass().getName();
		System.out.println("Attempting " + id);
		Ordinary x = null;
		if (ord instanceof Cloneable)
		{
			try
			{
				x = (Ordinary) ((IsCloneable) ord).clone();
				System.out.println("Cloned " + id);
			}
			catch (CloneNotSupportedException e)
			{
				System.err.println("Could not clone " + id);
			}
		}
		else
		{
			System.out.println("Doesn't implement Cloneable");
		}
		return x;
	}

	public static void main(String[] args)
	{
		// Upcasting:
		Ordinary[] ord =
		{ new IsCloneable(), new WrongClone(), new NoMore(), new TryMore(), new BackOn(), new ReallyNoMore(), };
		Ordinary x = new Ordinary();
//		WrongClone wc = new WrongClone();
//		try
//		{
//			WrongClone wc2 = (WrongClone) wc.clone();
//		}
//		catch (CloneNotSupportedException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// This won't compile; clone() is protected in Object:
		// ! x = (Ordinary)x.clone();
		// Checks first to see if a class implements Cloneable:
		for (int i = 0; i < ord.length; i++)
			tryToClone(ord[i]);
	    System.out.println("---------------------------------\n"+
	    	      "Attempting IsCloneable\n"+
	    	      "Cloned IsCloneable\n"+
	    	      "Attempting WrongClone\n"+
	    	      "Doesn't implement Cloneable\n"+
	    	      "Attempting NoMore\n"+
	    	      "Could not clone NoMore\n"+
	    	      "Attempting TryMore\n"+
	    	      "Could not clone TryMore\n"+
	    	      "Attempting BackOn\n"+
	    	      "Cloned BackOn\n"+
	    	      "Attempting ReallyNoMore\n"+
	    	      "Could not clone ReallyNoMore"
	    	    );
	}
} /// :~
package Task3;

//: appendixa:Snake.java
// Tests cloning to see if destination
// of references are also cloned.

public class Snake implements Cloneable
{
	private Snake next;
	private char c;

	// Value of i == number of segments
	public Snake(int i, char x)
	{
		c = x;		
		if (--i > 0)
			next = new Snake(i, (char) (x + 1));
	}

	public void increment()
	{
		c++;
		if (next != null)
			next.increment();
	}

	public String toString()
	{
		String s = ":" + c;
		if (next != null)
			s += next.toString();
		return s;
	}

	public Object clone()
	{
		Object o = null;
		try
		{
			o = super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			System.err.println("Snake can't clone");
		}
		return o;
	}

	public static void main(String[] args)
	{
		Snake s = new Snake(5, 'a');
		System.out.println("s = " + s);
		Snake s2 = (Snake) s.clone();
		System.out.println("s2 = " + s2);
		s.increment();
		System.out.println("after s.increment, s2 = " + s2);
	    System.out.println("---------------------------------------------\n" +
	    	      "s = :a:b:c:d:e\n" +
	    	      "s2 = :a:b:c:d:e\n" +
	    	      "after s.increment, s2 = :a:c:d:e:f"
	    	    );
	}
} /// :~